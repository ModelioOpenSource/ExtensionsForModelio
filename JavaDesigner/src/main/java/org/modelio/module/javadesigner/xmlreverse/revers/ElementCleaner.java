package org.modelio.module.javadesigner.xmlreverse.revers;

import java.util.Collection;
import java.util.HashSet;
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


public class ElementCleaner extends DepthFirstSearchVisitor {
    
    private Repository repository;

    
    private StrategyFactory sFactory;

    
    public ElementCleaner(final StrategyFactory sfactory, final Repository repository) {
        this.repository = repository;
        this.sFactory = sfactory;
    }

    
    @Override
    public Object visitAssociationEnd(final JaxbAssociationEnd element) {
        super.visitAssociationEnd(element);
        
        MObject modelio_element = this.repository.getElement(element);
        if (modelio_element != null) {
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            Collection<MObject> to_delete = cleaner.getResult();
            this.sFactory.getAssociationEndStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitAttribute(final JaxbAttribute element) {
        super.visitAttribute(element);
        
        MObject modelio_element = this.repository.getElement(element);
        if (modelio_element != null) {
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            Collection<MObject> to_delete = cleaner.getResult();
            this.sFactory.getAttributStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitClass(final JaxbClass element) {
        super.visitClass(element);
        
        Collection<MObject> to_delete = null;
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getClassStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitConstraint(final JaxbConstraint element) {
        super.visitConstraint(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getConstraintStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitDataType(final JaxbDataType element) {
        super.visitDataType(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getDataTypeStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitDependency(final JaxbDependency element) {
        super.visitDependency(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getDependencyStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitElementImport(final JaxbElementImport element) {
        super.visitElementImport(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getElementImportStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitEnumeration(final JaxbEnumeration element) {
        super.visitEnumeration(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getEnumerationStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitEnumerationLiteral(final JaxbEnumerationLiteral element) {
        super.visitEnumerationLiteral(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getEnumerationLiteralStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitGeneralization(final JaxbGeneralization element) {
        super.visitGeneralization(element);
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getGeneralizationStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitInstance(final JaxbInstance element) {
        super.visitInstance(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getInstanceStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitInterface(final JaxbInterface element) {
        super.visitInterface(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getInterfaceStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitNote(final JaxbNote element) {
        super.visitNote(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getNoteStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitOperation(final JaxbOperation element) {
        super.visitOperation(element);
        
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            Collection<MObject> to_delete = cleaner.getResult();
            this.sFactory.getOperationStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitPackage(final JaxbPackage element) {
        super.visitPackage(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getPackageStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitPackageImport(final JaxbPackageImport element) {
        super.visitPackageImport(element);
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getPackageImportStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitParameter(final JaxbParameter element) {
        super.visitParameter(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getParameterStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitRaisedException(final JaxbRaisedException element) {
        super.visitRaisedException(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getRaisedExceptionStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitRealization(final JaxbRealization element) {
        super.visitRealization(element);
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getRealizationStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitReturnParameter(final JaxbReturnParameter element) {
        super.visitReturnParameter(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getReturnParameterStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitSignal(final JaxbSignal element) {
        super.visitSignal(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getSignalStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitStereotype(final JaxbStereotype element) {
        super.visitStereotype(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();
            this.sFactory.getStereotypeStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitTaggedValue(final JaxbTaggedValue element) {
        super.visitTaggedValue(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getTaggedValueStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitTemplateParameter(final JaxbTemplateParameter element) {
        super.visitTemplateParameter(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getTemplateParameterStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitUse(final JaxbUse element) {
        super.visitUse(element);
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getUseStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitModel(final JaxbModel element) {
        super.visitModel(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getModelStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitReportItem(final JaxbReportItem element) {
        super.visitReportItem(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getReportItemStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitReportList(final JaxbReportList element) {
        super.visitReportList(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getReportListStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitReversedData(final JaxbReversedData element) {
        super.visitReversedData(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getReversedDataStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitTemplateParameterSubstitution(final JaxbTemplateParameterSubstitution element) {
        super.visitTemplateParameterSubstitution(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getTemplateParameterSubstitutionStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitGroup(final JaxbGroup element) {
        super.visitGroup(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getGroupStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitTemplateBinding(final JaxbTemplateBinding element) {
        super.visitTemplateBinding(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getTemplateBindingStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

    
    @Override
    public Object visitExternalElement(final JaxbExternalElement element) {
        super.visitExternalElement(element);
        
        Collection<MObject> to_delete = new HashSet<>();
        MObject modelio_element = this.repository.getElement(element);
        if(modelio_element != null){
            Cleaner cleaner = new Cleaner(this.repository);
            modelio_element.accept(cleaner);
            to_delete = cleaner.getResult();        
            this.sFactory.getExternalElementStrategy().deleteSubElements(element, modelio_element, to_delete, this.repository);
        }
        return null;
    }

}
