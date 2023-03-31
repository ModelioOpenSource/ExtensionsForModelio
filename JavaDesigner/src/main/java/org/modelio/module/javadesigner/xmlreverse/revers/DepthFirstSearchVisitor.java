package org.modelio.module.javadesigner.xmlreverse.revers;

import org.modelio.module.javadesigner.xmlreverse.model.IVisitorElement;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbAssociationEnd;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbAttribute;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClass;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbConstraint;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDataType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDependency;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbElementImport;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumeration;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumerationLiteral;
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
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTargetItem;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateBinding;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameter;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameterSubstitution;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUse;
import org.modelio.module.javadesigner.xmlreverse.model.defaultvisitor.DefaultReverseModelVisitor;
import com.modeliosoft.modelio.javadesigner.annotations.objid;


class DepthFirstSearchVisitor extends DefaultReverseModelVisitor {
    
    @Override
    public Object visitAssociationEnd(final JaxbAssociationEnd element) {
        for (Object collection : element.getBaseTypeOrClassTypeOrNote()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitAssociationEnd(element);
    }

    
    @Override
    public Object visitAttribute(final JaxbAttribute element) {
        for (Object collection : element.getValueOrBaseTypeOrClassType()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitAttribute(element);
    }

    
    @Override
    public Object visitClass(final JaxbClass element) {
        for (Object collection : element.getClazzOrInterfaceOrInstance()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitClass(element);
    }

    
    @Override
    public Object visitConstraint(final JaxbConstraint element) {
        for (Object collection : element.getContent()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitConstraint(element);
    }

    
    @Override
    public Object visitDataType(final JaxbDataType element) {
        for (Object collection : element.getOperationOrTemplateBindingOrTemplateParameter()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitDataType(element);
    }

    
    @Override
    public Object visitDependency(final JaxbDependency element) {
        // for(Object collection :
        // element.getClassTypeOrStereotypeOrTaggedValue()){
        // if(collection instanceof IVisitorElement){
        // IVisitorElement c_element = (IVisitorElement)collection;
        // c_element.accept(this);
        // }
        // }
        return super.visitDependency(element);
    }

    
    @Override
    public Object visitElementImport(final JaxbElementImport element) {
        // for(Object collection : element.getNoteOrConstraintOrStereotype()){
        // if(collection instanceof IVisitorElement){
        // IVisitorElement c_element = (IVisitorElement)collection;
        // c_element.accept(this);
        // }
        // }
        return super.visitElementImport(element);
    }

    
    @Override
    public Object visitEnumeration(final JaxbEnumeration element) {
        for (Object collection : element.getNoteOrConstraintOrStereotype()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitEnumeration(element);
    }

    
    @Override
    public Object visitEnumerationLiteral(final JaxbEnumerationLiteral element) {
        for (Object collection : element.getContent()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitEnumerationLiteral(element);
    }

    
    @Override
    public Object visitGeneralization(final JaxbGeneralization element) {
        // for(Object collection : element.getNoteOrStereotypeOrTaggedValue()){
        // if(collection instanceof IVisitorElement){
        // IVisitorElement c_element = (IVisitorElement)collection;
        // c_element.accept(this);
        // }
        // }
        return super.visitGeneralization(element);
    }

    
    @Override
    public Object visitInstance(final JaxbInstance element) {
        for (Object collection : element.getNoteOrConstraintOrStereotype()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitInstance(element);
    }

    
    @Override
    public Object visitInterface(final JaxbInterface element) {
        for (Object collection : element.getClazzOrInterfaceOrEnumeration()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitInterface(element);
    }

    
    @Override
    public Object visitNote(final JaxbNote element) {
        for (Object collection : element.getStereotypeOrTaggedValueOrContent()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitNote(element);
    }

    
    @Override
    public Object visitOperation(final JaxbOperation element) {
        for (Object collection : element.getParameterOrTemplateParameterOrReturnParameter()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitOperation(element);
    }

    
    @Override
    public Object visitPackage(final JaxbPackage element) {
        for (Object collection : element.getGroupOrPackageOrClazz()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitPackage(element);
    }

    
    @Override
    public Object visitPackageImport(final JaxbPackageImport element) {
        // for(Object collection : element.getNoteOrConstraintOrStereotype()){
        // if(collection instanceof IVisitorElement){
        // IVisitorElement c_element = (IVisitorElement)collection;
        // c_element.accept(this);
        // }
        // }
        return super.visitPackageImport(element);
    }

    
    @Override
    public Object visitParameter(final JaxbParameter element) {
        for (Object collection : element.getBaseTypeOrClassTypeOrNote()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitParameter(element);
    }

    
    @Override
    public Object visitRaisedException(final JaxbRaisedException element) {
        // UsedClass c_element = element.getUsedClass();
        // if(c_element != null){
        // c_element.accept(this);
        // }
        // for(Object collection : element.getNoteOrConstraintOrStereotype()){
        // if(collection instanceof IVisitorElement){
        // IVisitorElement c_element = (IVisitorElement)collection;
        // c_element.accept(this);
        // }
        // }
        return super.visitRaisedException(element);
    }

    
    @Override
    public Object visitRealization(final JaxbRealization element) {
        // for(Object collection : element.getNoteOrStereotypeOrTaggedValue()){
        // if(collection instanceof IVisitorElement){
        // IVisitorElement c_element = (IVisitorElement)collection;
        // c_element.accept(this);
        // }
        // }
        return super.visitRealization(element);
    }

    
    @Override
    public Object visitReturnParameter(final JaxbReturnParameter element) {
        for (Object collection : element.getBaseTypeOrClassTypeOrNote()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitReturnParameter(element);
    }

    
    @Override
    public Object visitSignal(final JaxbSignal element) {
        for (Object collection : element.getOperationRepresentationOrNoteOrConstraint()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitSignal(element);
    }

    
    @Override
    public Object visitStereotype(final JaxbStereotype element) {
        return super.visitStereotype(element);
    }

    
    @Override
    public Object visitTaggedValue(final JaxbTaggedValue element) {
        return super.visitTaggedValue(element);
    }

    
    @Override
    public Object visitTemplateParameter(final JaxbTemplateParameter element) {
        for (Object collection : element.getPackageOrClazzOrOperation()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitTemplateParameter(element);
    }

    
    @Override
    public Object visitUse(final JaxbUse element) {
        // for(Object collection : element.getNoteOrConstraintOrStereotype()){
        // if(collection instanceof IVisitorElement){
        // IVisitorElement c_element = (IVisitorElement)collection;
        // c_element.accept(this);
        // }
        // }
        return super.visitUse(element);
    }

    
    @Override
    public Object visitModel(final JaxbModel element) {
        for (Object collection : element.getPackageOrClazzOrInterface()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitModel(element);
    }

    
    @Override
    public Object visitReportItem(final JaxbReportItem item) {
        JaxbTargetItem c_element = item.getTargetItem();
        if (c_element != null) {
            c_element.accept(this);
        }
        return super.visitReportItem(item);
    }

    
    @Override
    public Object visitReportList(final JaxbReportList element) {
        for (Object collection : element.getReportItem()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitReportList(element);
    }

    
    @Override
    public Object visitReversedData(final JaxbReversedData element) {
        for (Object collection : element.getExternalElement()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        
        JaxbReportList reportlist = element.getReportList();
        if (reportlist != null) {
            reportlist.accept(this);
        }
        
        JaxbModel model = element.getModel();
        if (model != null) {
            model.accept(this);
        }
        return super.visitReversedData(element);
    }

    
    @Override
    public Object visitTemplateParameterSubstitution(final JaxbTemplateParameterSubstitution element) {
        JaxbDestination destination = element.getDestination();
        if (destination != null) {
            destination.accept(this);
        }
        return super.visitTemplateParameterSubstitution(element);
    }

    
    @Override
    public Object visitGroup(final JaxbGroup element) {
        for (Object collection : element.getPackageOrClazzOrInterface()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitGroup(element);
    }

    
    @Override
    public Object visitTemplateBinding(final JaxbTemplateBinding element) {
        for (Object collection : element.getTemplateParameterSubstitutionOrNoteOrConstraint()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return super.visitTemplateBinding(element);
    }

}
