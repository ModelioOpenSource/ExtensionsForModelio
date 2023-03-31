package org.modelio.module.javadesigner.xmlreverse.revers;

import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.StrategyFactory;
import org.modelio.module.javadesigner.xmlreverse.i18n.Messages;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbAssociationEnd;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbAttribute;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClass;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbConstraint;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDataType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDependency;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbElementImport;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumeration;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumerationLiteral;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbExternalElement;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbGeneralization;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbGroup;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbInstance;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbInterface;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbModel;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbNote;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbOperation;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbPackage;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbPackageImport;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbParameter;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbRaisedException;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbRealization;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReportItem;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReportList;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReturnParameter;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReversedData;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbSignal;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbStereotype;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTaggedValue;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateBinding;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameter;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameterSubstitution;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUse;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.vcore.smkernel.mapi.MObject;


@SuppressWarnings( {"rawtypes", "unchecked"})
public class ElementCreation extends DepthFirstSearchVisitor {
    
    private Repository repository;

    
    private StrategyFactory sFactory;

    
    private IModelingSession session;

    
    public ElementCreation(final StrategyFactory sfactory, final Repository repository, final IModelingSession session) {
        this.repository = repository;
        this.sFactory = sfactory;
        this.session = session;
    }

    
    @Override
    public Object visitAssociationEnd(final JaxbAssociationEnd element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getId()!=null?element.getId():element.getObjid());
        
        IReverseBox strategy = this.sFactory.getAssociationEndStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getBaseTypeOrClassTypeOrNote());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitAssociationEnd(element);
        return new_element;
    }

    
    @Override
    public Object visitAttribute(final JaxbAttribute element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getId()!=null?element.getId():element.getObjid());
        
        IReverseBox strategy = this.sFactory.getAttributStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getValueOrBaseTypeOrClassType());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitAttribute(element);
        return new_element;
    }

    
    @Override
    public Object visitClass(final JaxbClass element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getId()!=null?element.getId():element.getObjid());
        if (new_element != null && ! (new_element instanceof org.modelio.metamodel.uml.statik.Class)) {
            this.repository.getReportWriter().addError (Messages.getString("Error.sameNamespaceUsed.Title", getFullName(new_element)), null, Messages.getString("Error.sameNamespaceUsed.Description", getFullName(new_element)));
            new_element = null;
        }
        
        IReverseBox strategy = this.sFactory.getClassStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getClazzOrInterfaceOrInstance());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitClass(element);
        return new_element;
    }

    
    @Override
    public Object visitConstraint(final JaxbConstraint element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getConstraintStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getContent());
        
        super.visitConstraint(element);
        return new_element;
    }

    
    @Override
    public Object visitDataType(final JaxbDataType element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getId()!=null?element.getId():element.getObjid());
        if (new_element != null && ! (new_element instanceof org.modelio.metamodel.uml.statik.DataType)) {
            this.repository.getReportWriter().addError (Messages.getString("Error.sameNamespaceUsed.Title", getFullName(new_element)), null, Messages.getString("Error.sameNamespaceUsed.Description", getFullName(new_element)));
            new_element = null;
        }
        
        IReverseBox strategy = this.sFactory.getDataTypeStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getOperationOrTemplateBindingOrTemplateParameter());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitDataType(element);
        return new_element;
    }

    
    @Override
    public Object visitDependency(final JaxbDependency element) {
        this.repository.recordLink(element);
        
        super.visitDependency(element);
        return null;
    }

    
    @Override
    public Object visitElementImport(final JaxbElementImport element) {
        this.repository.recordLink(element);
        
        super.visitElementImport(element);
        return null;
    }

    
    @Override
    public Object visitEnumeration(final JaxbEnumeration element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getId()!=null?element.getId():element.getObjid());
        if (new_element != null && ! (new_element instanceof org.modelio.metamodel.uml.statik.Enumeration)) {
            this.repository.getReportWriter().addError (Messages.getString("Error.sameNamespaceUsed.Title", getFullName(new_element)), null, Messages.getString("Error.sameNamespaceUsed.Description", getFullName(new_element)));
            new_element = null;
        }
        
        IReverseBox strategy = this.sFactory.getEnumerationStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getNoteOrConstraintOrStereotype());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitEnumeration(element);
        return new_element;
    }

    
    @Override
    public Object visitEnumerationLiteral(final JaxbEnumerationLiteral element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getObjid());
        if (new_element != null && ! (new_element instanceof org.modelio.metamodel.uml.statik.EnumerationLiteral)) {
            this.repository.getReportWriter().addError (Messages.getString("Error.sameNamespaceUsed.Title", getFullName(new_element)), null, Messages.getString("Error.sameNamespaceUsed.Description", getFullName(new_element)));
            new_element = null;
        }
        
        IReverseBox strategy = this.sFactory.getEnumerationLiteralStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getContent());
        
        super.visitEnumerationLiteral(element);
        return new_element;
    }

    
    @Override
    public Object visitGeneralization(final JaxbGeneralization element) {
        this.repository.recordLink(element);
        
        super.visitGeneralization(element);
        return null;
    }

    
    @Override
    public Object visitInstance(final JaxbInstance element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getInstanceStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getNoteOrConstraintOrStereotype());
        
        super.visitInstance(element);
        return new_element;
    }

    
    @Override
    public Object visitInterface(final JaxbInterface element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getId()!=null?element.getId():element.getObjid());
        if (new_element != null && ! (new_element instanceof org.modelio.metamodel.uml.statik.Interface)) {
            this.repository.getReportWriter().addError (Messages.getString("Error.sameNamespaceUsed.Title", getFullName(new_element)), null, Messages.getString("Error.sameNamespaceUsed.Description", getFullName(new_element)));
            new_element = null;
        }
        
        IReverseBox strategy = this.sFactory.getInterfaceStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getClazzOrInterfaceOrEnumeration());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitInterface(element);
        return new_element;
    }

    
    @Override
    public Object visitNote(final JaxbNote element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getNoteStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(this.sFactory.getNoteStrategy().updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getStereotypeOrTaggedValueOrContent());
        
        super.visitNote(element);
        return new_element;
    }

    
    @Override
    public Object visitOperation(final JaxbOperation element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getId()!=null?element.getId():element.getObjid());
        if (new_element != null && ! (new_element instanceof org.modelio.metamodel.uml.statik.Operation)) {
            this.repository.getReportWriter().addError (Messages.getString("Error.sameNamespaceUsed.Title", getFullName(new_element)), null, Messages.getString("Error.sameNamespaceUsed.Description", getFullName(new_element)));
            new_element = null;
        }
        
        IReverseBox strategy = this.sFactory.getOperationStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getParameterOrTemplateParameterOrReturnParameter());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitOperation(element);
        return new_element;
    }

    
    @Override
    public Object visitPackage(final JaxbPackage element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getId()!=null?element.getId():element.getObjid());
        if (new_element != null && ! (new_element instanceof org.modelio.metamodel.uml.statik.Package)) {
            this.repository.getReportWriter().addError (Messages.getString("Error.sameNamespaceUsed.Title", getFullName(new_element)), null, Messages.getString("Error.sameNamespaceUsed.Description", getFullName(new_element)));
            new_element = null;
        }
        
        IReverseBox strategy = this.sFactory.getPackageStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getGroupOrPackageOrClazz());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitPackage(element);
        return new_element;
    }

    
    @Override
    public Object visitPackageImport(final JaxbPackageImport element) {
        this.repository.recordLink(element);
        
        super.visitPackageImport(element);
        return null;
    }

    
    @Override
    public Object visitParameter(final JaxbParameter element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getParameterStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getBaseTypeOrClassTypeOrNote());
        
        super.visitParameter(element);
        return new_element;
    }

    
    @Override
    public Object visitRaisedException(final JaxbRaisedException element) {
        this.repository.recordLink(element);
        
        super.visitRaisedException(element);
        return null;
    }

    
    @Override
    public Object visitRealization(final JaxbRealization element) {
        this.repository.recordLink(element);
        
        super.visitRealization(element);
        return null;
    }

    
    @Override
    public Object visitReturnParameter(final JaxbReturnParameter element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getReturnParameterStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getBaseTypeOrClassTypeOrNote());
        
        super.visitReturnParameter(element);
        return new_element;
    }

    
    @Override
    public Object visitSignal(final JaxbSignal element) {
        MObject owner = this.repository.getOwner(element);
        
        MObject new_element = this.repository.getElementById(element.getId()!=null?element.getId():element.getObjid());
        if (new_element != null && ! (new_element instanceof Signal)) {
            this.repository.getReportWriter().addError (Messages.getString("Error.sameNamespaceUsed.Title", getFullName(new_element)), null, Messages.getString("Error.sameNamespaceUsed.Description", getFullName(new_element)));
            new_element = null;
        }
        
        IReverseBox strategy = this.sFactory.getSignalStrategy();
        if (new_element == null) {
            new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        }
        
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getOperationRepresentationOrNoteOrConstraint());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitSignal(element);
        return new_element;
    }

    
    private String getFullName(final MObject new_element) {
        if (new_element instanceof ModelTree) {
            return this.repository.getNamespace((ModelTree) new_element, this.session);
        } else {
            return new_element.getName();
        }
    }

    
    @Override
    public Object visitStereotype(final JaxbStereotype element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getStereotypeStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        
        if (new_element != null)
            this.repository.recordElement(element, new_element);
        
        super.visitStereotype(element);
        return new_element;
    }

    
    @Override
    public Object visitTaggedValue(final JaxbTaggedValue element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getTaggedValueStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        super.visitTaggedValue(element);
        return new_element;
    }

    
    @Override
    public Object visitTemplateParameter(final JaxbTemplateParameter element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getTemplateParameterStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        this.repository.recordComposedElement(new_element, element.getPackageOrClazzOrOperation());
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitTemplateParameter(element);
        return new_element;
    }

    
    @Override
    public Object visitUse(final JaxbUse element) {
        this.repository.recordLink(element);
        
        super.visitUse(element);
        return null;
    }

    
    @Override
    public Object visitModel(final JaxbModel element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getModelStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        super.visitModel(element);
        return new_element;
    }

    
    @Override
    public Object visitReportItem(final JaxbReportItem element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getReportItemStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        super.visitReportItem(element);
        return new_element;
    }

    
    @Override
    public Object visitReportList(final JaxbReportList element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getReportListStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        super.visitReportList(element);
        return new_element;
    }

    
    @Override
    public Object visitReversedData(final JaxbReversedData element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getReversedDataStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        super.visitReversedData(element);
        return new_element;
    }

    
    @Override
    public Object visitTemplateParameterSubstitution(final JaxbTemplateParameterSubstitution element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getTemplateParameterSubstitutionStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        super.visitTemplateParameterSubstitution(element);
        return new_element;
    }

    
    @Override
    public Object visitTemplateBinding(final JaxbTemplateBinding element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getTemplateBindingStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        this.repository.recordComposedElement(new_element, element.getTemplateParameterSubstitutionOrNoteOrConstraint());
        
        super.visitTemplateBinding(element);
        return new_element;
    }

    
    @Override
    public Object visitGroup(final JaxbGroup element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getGroupStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        // The owner is set to the group parent
        this.repository.recordComposedElement(owner, element.getPackageOrClazzOrInterface());
        
        super.visitGroup(element);
        return new_element;
    }

    
    @Override
    public Object visitExternalElement(final JaxbExternalElement element) {
        MObject owner = this.repository.getOwner(element);
        
        IReverseBox strategy = this.sFactory.getExternalElementStrategy();
        MObject new_element = strategy.getCorrespondingElement(element, owner, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, owner, this.repository));
        this.repository.recordElement(element, new_element);
        
        if (element.getId() != null) {
            this.repository.recordId (element.getId(), new_element);    
        }
        
        super.visitExternalElement(element);
        return new_element;
    }

}
