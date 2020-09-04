package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.model.JaxbPackage;
import com.modelio.module.xmlreverse.strategy.PackageStrategy;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.xmltomodel.JavaNameSpaceFinder;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaPackageStrategy extends PackageStrategy {
    private JavaNameSpaceFinder javaNameSpaceFinder;

    public JavaPackageStrategy(final IModelingSession session, final JavaNameSpaceFinder nameSpaceFinder) {
        super(session);
        this.javaNameSpaceFinder = nameSpaceFinder;
    }

    @Override
    public Package getCorrespondingElement(final JaxbPackage jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        List<Package> cpack = this.javaNameSpaceFinder.getPackageByNamespace(jaxb_element.getName(), this.session);
        
        // We should ignore "ramc" packages
        for (Package pack : new ArrayList<>(cpack)) {
            if (ModelUtils.isLibrary(pack)) {
                cpack.remove(pack);
            }
        }
        
        // No package found, create a new one
        if (cpack.isEmpty()) {
            return this.model.createPackage();
        }
        
        // Only one package found, return it
        if (cpack.size() == 1) {
            return cpack.get(0);
        }
        
        // Is one package found in the namespace of "owner"
        if (owner instanceof ModelTree) {
            for (Package p : cpack) {
                if (JavaNameSpaceFinder.isOwner(p, (ModelTree) owner))
                    return p;
            }
        }
        
        // Fallback: the repository knows how to choose...
        return repository.resolveMultipleNamespaces(cpack);
    }

    @Override
    public List<MObject> updateProperties(final JaxbPackage jaxb_element, final Package modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        List<MObject> ret = null;
        ModelTree treeOwner = (ModelTree) owner;
        
        // A new package has no parent yet, update it.
        // (note : modelio_element may not be a child of owner at all)
        if (modelio_element.getOwner() == null) {
            modelio_element.setOwner(treeOwner);
        
            // initialize the name
            String jxbname = jaxb_element.getName();
            if (jxbname != null) {
                String[] jxbnames = jxbname.split("\\.");
                jxbname = jxbnames[jxbnames.length - 1]; // get the last part of jaxb full name
                if (!jxbname.equals(modelio_element.getName())) {
                    modelio_element.setName(jxbname);
                }
            }
        }
        
        try {
            modelio_element.removeTags(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PACKAGE_JAVAEXTERN);
            ModelUtils.addStereotype(modelio_element, JavaDesignerStereotypes.JAVAPACKAGE);
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVAPACKAGE)); //$NON-NLS-1$
        }
        return ret;
    }

    @Override
    public void deleteSubElements(final JaxbPackage jaxb_element, final Package modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        // super.deleteSubElements(jaxb_element, modelio_element, element_todelete, repository);
        if (modelio_element == null) {
            return;
        }
        if (modelio_element.getOwnedElement ().size() == 0 && modelio_element.getProduct ().size() == 0) {
            modelio_element.delete();
            return;
        }
        
        List<ModelTree> children = new ArrayList<> ();
        List<String> childrenNames = new ArrayList<> ();
        List<ModelTree> toCheckAtEnd = new ArrayList<> ();
        
        // Check all java classes and datatypes
        for (ModelTree child : new ArrayList<>(modelio_element.getOwnedElement ())) {
            if (child.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACLASS) ||
                    child.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVADATATYPE)) {
        
                // Store recorded elements, they come from the reversed model
                if (repository.isRecordedElement (child)) {
                    children.add (child);
                    childrenNames.add (child.getName ());
                } else {
                    // Non recorded elements are still in the model, but not reversed
                    // If it has the same name as a recorded element, delete it
                    String childName = child.getName ();
                    if (childrenNames.contains (childName)) {
                        child.delete();
                    } else {
                        // No match found, store this element for further check:
                        // all recorded elements aren't stored in the children list
                        toCheckAtEnd.add (child);
                    }
                }
            }
        }
        
        // Check all remaining elements with the recorded children list
        for (ModelTree child : toCheckAtEnd) {
            String childName = child.getName ();
            if (childrenNames.contains (childName)) {
                child.delete();
            }
        }
    }

    @Override
    public void postTreatment(final JaxbPackage jaxb_element, final Package modelio_element, final IReadOnlyRepository repository) {
        super.postTreatment (jaxb_element, modelio_element, repository);
        
        if (modelio_element == null) {
            // May happen when there are some packages named like "a.b.c"
            return;
        }
        
        // Remove all JavaExtern classes having some neighbour class/interface/enum with the same name
        for (ModelTree subElement : modelio_element.getOwnedElement (Class.class)) {
            if (subElement.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVAEXTERN)) {
                String currentName = subElement.getName ();
        
                for (ModelTree otherSubElement : modelio_element.getOwnedElement ()) {
                    if ((otherSubElement instanceof GeneralClass) &&
                            otherSubElement.getName ().equals (currentName)) {
                        if (JavaDesignerUtils.isJavaElement (otherSubElement) &&
                                !otherSubElement.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVAEXTERN)) {
                            // Match found, we must move all links...
                            reportLinksFromExternalElement ((GeneralClass) otherSubElement, (Class) subElement);
                            subElement.delete();
                            break;
                        }
                    }
                }
            }
        }
    }

    private void reportLinksFromExternalElement(final GeneralClass newElement, final Class externalElement) {
        for (Dependency theDep : externalElement.getImpactedDependency ()) {
            theDep.setDependsOn (newElement);
        }
        
        for (ElementImport theImport : externalElement.getImporting ()) {
            theImport.setImportedElement (newElement);
        }
        
        if (newElement instanceof Interface) {
            for (Generalization theGeneralization : externalElement.getSpecialization ()) {
                this.model.createInterfaceRealization (theGeneralization.getSubType (), (Interface) newElement);
            }
        } else {
            for (Generalization theGeneralization : externalElement.getSpecialization ()) {
                theGeneralization.setSuperType (newElement);
            }
        }
        
        for (RaisedException theThrow : externalElement.getThrowing ()) {
            theThrow.setThrownType (newElement);
        }
    }

}
