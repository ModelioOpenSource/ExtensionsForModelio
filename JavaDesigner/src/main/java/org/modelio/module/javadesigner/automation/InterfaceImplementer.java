package org.modelio.module.javadesigner.automation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.module.javadesigner.api.JavaDesignerParameters.InterfaceImplementationMode;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;

/**
 * Service class used to synchronize operations between Interfaces and their implementing Classifiers.
 */
public class InterfaceImplementer {
    public InterfaceImplementationMode INTERFACEIMPLEMENTATION;

    private IUmlModel model;

    private IModelingSession session;

    /**
     * Default constructor, initializing the fields.
     */
    public InterfaceImplementer(final IModelingSession session) {
        this.session = session;
        this.model = this.session.getModel();
        this.INTERFACEIMPLEMENTATION = JavaDesignerParameters.InterfaceImplementationMode.Always;
    }

    public boolean updateImplementingClassifiers(final Interface theInterface) {
        boolean result = false;
        
        for (InterfaceRealization theRealization : theInterface.getImplementedLink()) {
            NameSpace theNameSpace = theRealization.getImplementer();
            if (theNameSpace instanceof Classifier && theNameSpace.isModifiable()) {
                boolean newResult = implementInterface((Classifier) theNameSpace, theInterface);
                result = result || newResult;
            }
        }
        return result;
    }

    /**
     * Create Operations in the Class from those defined in the implemented
     * Interfaces.
     * @param futureOperationOwner The Class to create the Operations in.
     */
    public boolean implementInterfaces(final Classifier futureOperationOwner) {
        boolean ret = true;
        // Take all Interfaces
        for (InterfaceRealization realization : futureOperationOwner.getRealized()) {
            Interface itf = realization.getImplemented();
        
            ret = ret && implementInterface(futureOperationOwner, itf);
        }
        return ret;
    }

    /**
     * Create Operations in the Class from those defined in the Interface.
     * @param futureOperationOwner The Class to create the Operations in.
     * @param itf The Interface containing the Operations to redefine.
     */
    public boolean implementInterface(final Classifier futureOperationOwner, final Interface itf) {
        if (!futureOperationOwner.isModifiable()) {
            return false;
        }
        
        try {
            // Take all Operations
            for (Operation interfacefOperation : itf.getOwnedOperation()) {
                implementAnOperation(futureOperationOwner, interfacefOperation);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Implement an operation.
     * @param futureOperationOwner The Class to create the Operations in.
     * @param redefinedOperation The Operation to redefine
     */
    public void implementAnOperation(final Classifier futureOperationOwner, final Operation redefinedOperation) {
        // Check if an existing operation matches
        Operation implementingOperation = getImplementingOperation(redefinedOperation, futureOperationOwner);
        if (implementingOperation == null) {
            // Create a new Operation implementing the interface
            // operation if the class isn't Abstract
            if (!futureOperationOwner.isIsAbstract()) {
                implementingOperation = this.model.createOperation(redefinedOperation.getName(), futureOperationOwner);
                implementingOperation.setRedefines(redefinedOperation);
                // Update all content for a new operation
                copyOperationContent(redefinedOperation, implementingOperation);
            }
        } else {
            if (implementingOperation.getRedefines() == null) {
                // Add the redefine link
                implementingOperation.setRedefines(redefinedOperation);
            } else if (!redefinedOperation.equals(implementingOperation
                    .getRedefines())) {
                // matching operation but that redefines an operation from
                // another interface as it is
                // possible in Java (an operation can be an implementation for 2
                // or more operations from
                // different interfaces). In that case no need to update the
                // operation content.
                return;
            }
        
            // Update all content for an existing operation
            copyOperationContent(redefinedOperation, implementingOperation);
        }
    }

    /**
     * Implement an Operation in all the classifiers that realize the interface
     * owning the Operation
     */
    public void implementAnOperation(final Operation predefop) {
        try {
            Interface interf = (Interface) predefop.getOwner();
            for (Classifier clsfer : getImplementers(interf)) {
                implementAnOperation(clsfer, predefop);
            }
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    /**
     * Delete Operations in the Class from those defined in the implemented
     * Interfaces.
     * @param current The Class to create the Operations in.
     */
    public boolean unImplementInterfaces(final Classifier current) {
        boolean res = false;
        
        // Take all Operations
        for (Operation op : current.getOwnedOperation()) {
            // Take all Interfaces
            for (InterfaceRealization realization : current.getRealized()) {
                Interface itf = realization.getImplemented();
        
                // Check if an existing operation matches
                Operation superOperation = getSuperOperation(op, itf);
                if (superOperation != null) {
                    op.delete();
                    res = true;
                    break;
                }
            }
        }
        return res;
    }

    private boolean contentEquals(final Operation op1, final Operation op2) {
        boolean found = true;
        
        // Check return Parameter
        if (contentEquals(op1.getReturn(), op2.getReturn())) {
            // Check IO Parameters count
            if (op1.getIO().size() == op2.getIO().size()) {
                // Check each parameter content
                Iterator<Parameter> iterator = op2.getIO().iterator();
                for (Iterator<Parameter> iterator2 = op1.getIO().iterator(); iterator2.hasNext() && found;) {
                    Parameter oldParam = iterator.next();
                    Parameter newParam = iterator2.next();
        
                    if (!contentEquals(newParam, oldParam)) {
                        found = false;
                    }
                }
        
                // Check exceptions
                if (op1.getThrown().size() == op2.getThrown().size()) {
                    Iterator<RaisedException> itOldExcept = op2.getThrown().iterator();
                    for (Iterator<RaisedException> itNewExcept = op1.getThrown().iterator(); itNewExcept.hasNext() && found;) {
                        RaisedException oldExcept = itOldExcept.next();
                        RaisedException newExcept = itNewExcept.next();
        
                        if (!newExcept.getThrownType().equals(
                                oldExcept.getThrownType())) {
                            found = false;
                        }
                    }
        
                    // If everything is identical, return true
                    if (found) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if a specific Operation can be found into this Classifier.
     */
    private Operation getImplementingOperation(final Operation operationToMatch, final Classifier potentialParent) {
        String operationToMatchName = operationToMatch.getName();
        
        // Check all redefine links
        for (Operation op : potentialParent.getOwnedOperation()) {
            if (operationToMatch.equals(op.getRedefines())) {
                return op;
            }
        }
        
        // If no redefine link is found, check signatures
        for (Operation op : potentialParent.getOwnedOperation()) {
            String opName = op.getName();
        
            if (operationToMatchName.equals(opName)
                    && contentEquals(operationToMatch, op)) {
                // if the redefinition link is set to an operation with the same
                // signature but
                // that belongs to another interface, return that operation
                // anyway.
                // Java allows one operation to implement operations from
                // several implemented interfaces,
                // not UML.
                return op;
            }
        }
        return null;
    }

    /**
     * Checks if a specific Operation can be found into this Interface.
     */
    private Operation getSuperOperation(final Operation operationToMatch, final Interface potentialParent) {
        // Check the redefine links
        for (Operation op : potentialParent.getOwnedOperation()) {
            if (operationToMatch.getRedefines().equals(op)) {
                return op;
            }
        }
        
        // Check deeper to find a matching operation
        for (Operation op : potentialParent.getOwnedOperation()) {
            if (contentEquals(op, operationToMatch)) {
                return op;
            }
        }
        return null;
    }

    /**
     * Return true if the two parameters have the same type.
     */
    private boolean contentEquals(final Parameter p1, final Parameter p2) {
        return p1 == null && p2 == null || p1 != null && p2 != null
                                                && p1.getType() != null && p2.getType() != null && p1.getType()
                        .equals(p2.getType());
    }

    /**
     * Report all the content of the original Operation in the new Operation.
     * @param orig The operation to copy.
     * @param dest The owner of the Operation to create.
     */
    private void copyOperationContent(final Operation orig, final Operation dest) {
        dest.setName(orig.getName());
        dest.setIsClass(orig.isIsClass());
        dest.setConcurrency(orig.isConcurrency());
        dest.setPassing(orig.getPassing());
        dest.setVisibility(orig.getVisibility());
        dest.setFinal(orig.isFinal());
        copyTaggedValues(orig, dest);
        copyImports(orig, dest);
        copyParameters(orig, dest);
        copyExceptions(orig, dest);
        copyNotes(orig, dest);
    }

    private void copyParameters(final Operation orig, final Operation dest) {
        ArrayList<Parameter> destParameters = new ArrayList<>();
        for (Parameter theParameter : new ArrayList<>(dest.getIO())) {
            destParameters.add(theParameter);
            dest.getIO().remove(theParameter);
        }
        
        int idx = 0;
        int sizeDestPar = destParameters.size();
        for (Parameter origParam : orig.getIO()) {
            Parameter destParam = idx >= sizeDestPar ? this.model.createParameter() : destParameters.get(idx);
            copyParameterContent(origParam, destParam);
            dest.getIO().add(destParam);
            ++idx;
        }
        // remove remaining destParameter(in case there is more
        // parameters in desOperation)
        for (; idx < sizeDestPar; ++idx) {
            destParameters.get(idx).delete();
        }
        
        if (orig.getReturn() != null) {
            Parameter returnParam = dest.getReturn();
            if (returnParam == null) {
                returnParam = this.model.createReturnParameter("", null,
                        dest);
            }
            copyParameterContent(orig.getReturn(), returnParam);
        } else {
            Parameter returnParam = dest.getReturn();
            if (returnParam != null) {
                returnParam.delete();
            }
        }
    }

    private void copyExceptions(final Operation orig, final Operation dest) {
        // Update parameters
        for (RaisedException theParameter : new ArrayList<>(dest.getThrown())) {
            theParameter.delete();
        }
        
        for (RaisedException oldException : orig.getThrown()) {
            RaisedException newException = this.model.createRaisedException();
            newException.setThrownType(oldException.getThrownType());
            newException.setThrower(dest);
        
            copyNotes(oldException, newException);
            copyStereotypes(oldException, newException);
            copyTaggedValues(oldException, newException);
        }
    }

    private void copyStereotypes(final ModelElement orig, final ModelElement dest) {
        {
            // Remove stereotypes from destElement.
            for (Stereotype x : new ArrayList<>(dest.getExtension())) {
                dest.getExtension().remove(x);
            }
            // Update dest respecting the order of orig
            for (Stereotype x : orig.getExtension()) {
                dest.getExtension().add(x);
            }
        }
    }

    private void copyTaggedValues(final ModelElement orig, final ModelElement dest) {
        ArrayList<TaggedValue> destTags = new ArrayList<>();
        for (TaggedValue tag : new ArrayList<>(dest.getTag())) {
            destTags.add(tag);
            dest.getTag().remove(tag);
        }
        
        for (TaggedValue oldTag : orig.getTag()) {
            TaggedValue newTag = null;
            TagType oldTagType = oldTag.getDefinition();
            if (oldTagType != null /* && oldTagType.isBelongToPrototype() */) {
                // Check if the tag already exists
                for (TaggedValue t : destTags) {
                    TagType newTagType = t.getDefinition();
                    if (newTagType != null) {
                        if (oldTagType == newTagType) {
                            // matching tag found, reattach it
                            dest.getTag().add(t);
                            destTags.remove(t);
                            newTag = t;
                            break;
                        }
                    }
                }
        
                // If it doesn't exists, create it
                if (newTag == null) {
                    newTag = this.model.createTaggedValue(oldTagType, dest);
                }
        
                // Update parameters
                copyTagParameters(oldTag, newTag);
        
                // Update qualifier
                copyTagQualifier(oldTag, newTag);
            }
        }
        
        // Reattach remaining tags that do not belong to signature and delete others
        for (TaggedValue t : destTags) {
            t.delete();
        }
    }

    private void copyTagParameters(final TaggedValue orig, final TaggedValue dest) {
        ArrayList<TagParameter> destTagPars = new ArrayList<>();
        for (TagParameter param : new ArrayList<>(dest.getActual())) {
            destTagPars.add(param);
            dest.getActual().remove(param);
        }
        // Update dest respecting the parameter order
        int idx = 0;
        int sizeDestTags = destTagPars.size();
        for (TagParameter p : new ArrayList<>(orig.getActual())) {
            TagParameter dp = idx >= sizeDestTags ? this.model.createTagParameter() : destTagPars.get(idx);
            String oval = p.getValue();
            if (!oval.equals(dp.getValue())) {
                dp.setValue(oval);
            }
            dest.getActual().add(dp);
            ++idx;
        }
        // remove remaining destParameter(in case there is more
        // parameters in desOperation)
        for (; idx < sizeDestTags; ++idx) {
            destTagPars.get(idx).delete();
        }
    }

    private void copyTagQualifier(final TaggedValue orig, final TaggedValue dest) {
        TagParameter oQual = orig.getQualifier();
        TagParameter dQual = dest.getQualifier();
        if (oQual != null) {
            if (dQual == null) {
                dQual = this.model.createTagParameter();
                dest.setQualifier(dQual);
            }
            dQual.setValue(oQual.getValue());
        } else {
            if (dQual != null) {
                dQual.delete();
            }
        }
    }

    private void copyNotes(final ModelElement orig, final ModelElement dest) {
        for (Note oldNote : orig.getDescriptor()) {
            NoteType oldNoteType = oldNote.getModel();
            if (oldNoteType != null) {
                boolean exists = false;
        
                // Check if the note already exists on the target
                for (Note newNote : dest.getDescriptor()) {
                    NoteType newNoteType = newNote.getModel();
                    if (newNoteType != null) {
                        // If the note is found, update its content
                        if (oldNoteType == newNoteType) {
                            newNote.setContent(oldNote.getContent());
                            exists = true;
                            break;
                        }
                    }
                }
        
                // Create the note if it doesn't exists
                if (!exists) {
                    this.model.createNote(oldNoteType, dest, oldNote.getContent());
                }
            }
        }
    }

    private void copyParameterContent(final Parameter orig, final Parameter dest) {
        boolean isReturnParameter = orig.getReturned() != null;
        
        dest.setMultiplicityMin(orig.getMultiplicityMin());
        dest.setMultiplicityMax(orig.getMultiplicityMax());
        dest.setTypeConstraint(orig.getTypeConstraint());
        dest.setType(orig.getType());
        
        copyNotes(orig, dest);
        copyTaggedValues(orig, dest);
        
        if (!isReturnParameter) {
            dest.setName(orig.getName());
            dest.setDefaultValue(orig.getDefaultValue());
            dest.setParameterPassing(orig
                    .getParameterPassing());
        } else {
            dest.setParameterPassing(PassingMode.OUT);
        }
    }

    private void copyImports(final Operation orig, final Operation dest) {
        // ElementImports
        for (ElementImport originalImport : orig.getOwnedImport()) {
            boolean exists = false;
            for (ElementImport newImport : dest.getOwnedImport()) {
                // Check imported Element
                NameSpace newImportedElement = newImport.getImportedElement();
                NameSpace oldImportedElement = originalImport.getImportedElement();
                if (newImportedElement != null && oldImportedElement != null
                        && newImportedElement.equals(oldImportedElement)) {
                    exists = true;
                    break;
                }
            }
        
            if (!exists) {
                ElementImport newImport = this.model.createElementImport();
                newImport.setName(originalImport.getName());
                newImport.setVisibility(originalImport.getVisibility());
                newImport.setImportingOperation(dest);
                newImport.setImportedElement(originalImport
                        .getImportedElement());
        
                copyNotes(originalImport, newImport);
                copyStereotypes(originalImport, newImport);
                copyTaggedValues(originalImport, newImport);
            }
        }
        
        // PackageImports
        for (PackageImport originalImport : orig
                .getOwnedPackageImport()) {
            boolean exists = false;
            for (PackageImport newImport : dest.getOwnedPackageImport()) {
                // Check imported Package
                Package newImportedPackage = newImport.getImportedPackage();
                Package oldImportedPackage = originalImport.getImportedPackage();
                if (newImportedPackage != null && oldImportedPackage != null && newImportedPackage.equals(oldImportedPackage)) {
                    exists = true;
                    break;
                }
            }
        
            if (!exists) {
                PackageImport newImport = this.model.createPackageImport();
                newImport.setName(originalImport.getName());
                newImport.setVisibility(originalImport.getVisibility());
                newImport.setImportingOperation(dest);
                newImport.setImportedPackage(originalImport.getImportedPackage());
        
                copyNotes(originalImport, newImport);
                copyStereotypes(originalImport, newImport);
                copyTaggedValues(originalImport, newImport);
            }
        }
    }

    /**
     * Get the list of classifiers that implement 'pinterf' Interface
     * @return a list of classifer.
     */
    private List<Classifier> getImplementers(final Interface pinterf) {
        List<Classifier> res = new ArrayList<>();
        for (InterfaceRealization rel : pinterf.getImplementedLink()) {
            NameSpace ns = rel.getImplementer();
            if (ns instanceof Classifier) {
                res.add((Classifier) ns);
            }
        }
        return res;
    }

    private List<Operation> getImplementedOperations(final Classifier clsfr, final Interface interf) {
        List<Operation> res = new ArrayList<>();
        for (Feature ft : clsfr.getOwnedOperation()) {
            try {
                Operation op = (Operation) ft;
                if (interf.equals(op.getRedefines().getOwner())) {
                    res.add(op);
                }
            } catch (NullPointerException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
        return res;
    }

    /**
     * Checks all operations to remove redefine links bound to interfaces no
     * more implemented.
     * @param operationsOwner The classifier owning the operations.
     */
    public boolean updateRedefineLinks(final Classifier operationsOwner) {
        boolean hasDoneWork = false;
        
        List<Interface> implementedInterfaces = new ArrayList<>();
        // Take all Interfaces
        for (InterfaceRealization realization : operationsOwner.getRealized()) {
            Interface itf = realization.getImplemented();
            implementedInterfaces.add(itf);
        }
        
        // Take all Operations
        for (Operation operation : operationsOwner.getOwnedOperation()) {
            Operation redefinedOperation = operation.getRedefines();
            if (redefinedOperation != null) {
                Classifier redefinedOperationOwner = redefinedOperation.getOwner();
                if (redefinedOperationOwner instanceof Interface && !implementedInterfaces.contains(redefinedOperation.getOwner())) {
                    // Remove only the redefine link
                    operation.setRedefines(null);
                    hasDoneWork = true;
                }
            }
        }
        return hasDoneWork;
    }

    /**
     * Move all the operations belonging to 'porig' class and which redefines
     * operation from 'pinterf' to 'pdest' class
     * @param porig The Class where the operations are
     * @param pdest The Class where to operation should be copied to
     * @param pinterf The Interface to filter operations to move
     */
    public void moveOperations(final Classifier porig, final Classifier pdest, final Interface pinterf) {
        for (Operation op : getImplementedOperations(porig, pinterf)) {
            op.setOwner(pdest);
        }
    }

}
