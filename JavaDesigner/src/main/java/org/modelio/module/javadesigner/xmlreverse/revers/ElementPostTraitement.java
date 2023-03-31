package org.modelio.module.javadesigner.xmlreverse.revers;

import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.StrategyFactory;
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
import org.modelio.vcore.smkernel.mapi.MObject;


public class ElementPostTraitement extends DepthFirstSearchVisitor {
    
    private Repository repository;

    
    private StrategyFactory sFactory;

    
    public ElementPostTraitement(final StrategyFactory sfactory, final Repository repository) {
        this.repository = repository;
        this.sFactory = sfactory;
    }

    
    @Override
    public Object visitAssociationEnd(final JaxbAssociationEnd element) {
        Object ret = super.visitAssociationEnd(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getAssociationEndStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitAttribute(final JaxbAttribute element) {
        Object ret = super.visitAttribute(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getAttributStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitClass(final JaxbClass element) {
        Object ret = super.visitClass(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getClassStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitConstraint(final JaxbConstraint element) {
        Object ret = super.visitConstraint(element);
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getConstraintStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitDataType(final JaxbDataType element) {
        Object ret = super.visitDataType(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getDataTypeStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitDependency(final JaxbDependency element) {
        Object ret = super.visitDependency(element);
        
        MObject modelio_element = this.repository.getElement(element);    
        this.sFactory.getDependencyStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitElementImport(final JaxbElementImport element) {
        Object ret = super.visitElementImport(element);
                
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getElementImportStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitEnumeration(final JaxbEnumeration element) {
        Object ret = super.visitEnumeration(element);
                
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getEnumerationStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitEnumerationLiteral(final JaxbEnumerationLiteral element) {
        Object ret = super.visitEnumerationLiteral(element);
                
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getEnumerationLiteralStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitGeneralization(final JaxbGeneralization element) {
        Object ret = super.visitGeneralization(element);
        
        MObject modelio_element = this.repository.getElement(element);        
        this.sFactory.getGeneralizationStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitInstance(final JaxbInstance element) {
        Object ret = super.visitInstance(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getInstanceStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitInterface(final JaxbInterface element) {
        Object ret = super.visitInterface(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getInterfaceStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitNote(final JaxbNote element) {
        Object ret = super.visitNote(element);
                
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getNoteStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitOperation(final JaxbOperation element) {
        Object ret =super.visitOperation(element);
        
        MObject modelio_element = this.repository.getElement(element);    
        this.sFactory.getOperationStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitPackage(final JaxbPackage element) {
        Object ret = super.visitPackage(element);
        
        MObject modelio_element = this.repository.getElement(element);        
        this.sFactory.getPackageStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitPackageImport(final JaxbPackageImport element) {
        Object ret = super.visitPackageImport(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getPackageImportStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitParameter(final JaxbParameter element) {
        Object ret = super.visitParameter(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getParameterStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitRaisedException(final JaxbRaisedException element) {
        Object ret = super.visitRaisedException(element);
                
        MObject modelio_element = this.repository.getElement(element);    
        this.sFactory.getRaisedExceptionStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitRealization(final JaxbRealization element) {
        Object ret = super.visitRealization(element);
                
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getRealizationStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitReturnParameter(final JaxbReturnParameter element) {
        Object ret = super.visitReturnParameter(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getReturnParameterStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitSignal(final JaxbSignal element) {
        Object ret = super.visitSignal(element);
                
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getSignalStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitStereotype(final JaxbStereotype element) {
        Object ret = super.visitStereotype(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getStereotypeStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitTaggedValue(final JaxbTaggedValue element) {
        Object ret = super.visitTaggedValue(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getTaggedValueStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitTemplateParameter(final JaxbTemplateParameter element) {
        Object ret = super.visitTemplateParameter(element);
                        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getTemplateParameterStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitUse(final JaxbUse element) {
        Object ret = super.visitUse(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getUseStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitModel(final JaxbModel element) {
        Object ret = super.visitModel(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getModelStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitReportItem(final JaxbReportItem element) {
        Object ret = super.visitReportItem(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getReportItemStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitReportList(final JaxbReportList element) {
        Object ret = super.visitReportList(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getReportListStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitReversedData(final JaxbReversedData element) {
        Object ret = super.visitReversedData(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getReversedDataStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitTemplateParameterSubstitution(final JaxbTemplateParameterSubstitution element) {
        Object ret = super.visitTemplateParameterSubstitution(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getTemplateParameterSubstitutionStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitGroup(final JaxbGroup element) {
        Object ret = super.visitGroup(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getGroupStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitTemplateBinding(final JaxbTemplateBinding element) {
        Object ret = super.visitTemplateBinding(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getTemplateBindingStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

    
    @Override
    public Object visitExternalElement(final JaxbExternalElement element) {
        Object ret = super.visitExternalElement(element);
        
        MObject modelio_element = this.repository.getElement(element);
        this.sFactory.getExternalElementStrategy().postTreatment(element, modelio_element, this.repository);
        return ret;
    }

}
