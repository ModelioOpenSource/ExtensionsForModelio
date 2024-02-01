package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbAttribute;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClassType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JxbVisibilityMode;
import org.modelio.module.javadesigner.xmlreverse.utils.TypeConverter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;


public class AttributStrategy extends ModelElementStrategy implements IReverseBox<JaxbAttribute,Attribute> {
    
    public AttributStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Attribute getCorrespondingElement(final JaxbAttribute jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        if(owner instanceof Classifier){
            Classifier treeOwner = (Classifier) owner;
            for(Attribute sub_tree : treeOwner.getOwnedAttribute()){
                if(sub_tree.getName().equals(jaxb_element.getName())){
                    if (!repository.isRecordedElement(sub_tree)) {
                        return sub_tree;
                    }
                }
            }
        } else if(owner instanceof AssociationEnd){
            AssociationEnd treeOwner = (AssociationEnd) owner;
            for(Attribute sub_tree : treeOwner.getQualifier ()){
                if(sub_tree.getName().equals(jaxb_element.getName())){
                    if (!repository.isRecordedElement(sub_tree)) {
                        return sub_tree;
                    }
                }
            }
        }
        
        
        
        Attribute new_element = this.model.createAttribute();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbAttribute jaxb_element, final Attribute modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        if(owner instanceof Classifier){
            modelio_element.setOwner((Classifier)owner);
        } else if(owner instanceof AssociationEnd){
            modelio_element.setQualified ((AssociationEnd)owner);
        }else{
            modelio_element.setOwnerTemplateParameter((TemplateParameter)owner);
        }
        
        Boolean isIsAbstract = jaxb_element.isIsAbstract();
        if (isIsAbstract != null) {
            modelio_element.setIsAbstract(isIsAbstract);
        }
        
        Boolean isIsClass = jaxb_element.isIsClass();
        if (isIsClass != null) {
            modelio_element.setIsClass(isIsClass);
        }
        
        Boolean isIsDerived = jaxb_element.isIsDerived();
        if (isIsDerived != null) {
            modelio_element.setIsDerived(isIsDerived);
        }
        
        Boolean isTargetIsClass = jaxb_element.isTargetIsClass();
        if (isTargetIsClass != null) {
            modelio_element.setTargetIsClass(isTargetIsClass);
        }
        
        String getMultiplicity = jaxb_element.getMultiplicity();
        if (getMultiplicity != null) {
            modelio_element.setMultiplicityMax(getMultiplicity);
        }
        
        String getName = jaxb_element.getName();
        if (getName != null) {
            modelio_element.setName(jaxb_element.getName());
        }
        
        String constraint = jaxb_element.getTypeConstraint();
        if (constraint != null) {
            modelio_element.setTypeConstraint(constraint);
        }
        
        String visibility = jaxb_element.getVisibility();
        if (visibility != null) {
            if(visibility.equalsIgnoreCase(JxbVisibilityMode.Public.name())){
                modelio_element.setVisibility(VisibilityMode.PUBLIC);            
            }else if(visibility.equalsIgnoreCase(JxbVisibilityMode.Private.name())){
                modelio_element.setVisibility(VisibilityMode.PRIVATE);            
            }else if(visibility.equalsIgnoreCase(JxbVisibilityMode.Protected.name())){
                modelio_element.setVisibility(VisibilityMode.PROTECTED);        
            }else if(visibility.equalsIgnoreCase(JxbVisibilityMode.Package_Visibility.name())){
                modelio_element.setVisibility(VisibilityMode.PACKAGEVISIBILITY);        
            }else if(visibility.equalsIgnoreCase(JxbVisibilityMode.Visibility_Undefined.name())){
                modelio_element.setVisibility(VisibilityMode.VISIBILITYUNDEFINED);    
            }
        }
        String changeable = jaxb_element.getChangeable();
        if (changeable != null) {
            changeable = changeable.toUpperCase();
            if (changeable.equals (KindOfAccess.ACCESNONE.name ())) {
                modelio_element.setChangeable(KindOfAccess.ACCESNONE);
            } else if (changeable.equals (KindOfAccess.READ.name ())) {
                modelio_element.setChangeable(KindOfAccess.READ);
            } else if (changeable.equals (KindOfAccess.READWRITE.name ())) {
                modelio_element.setChangeable(KindOfAccess.READWRITE);
            } else if (changeable.equals (KindOfAccess.WRITE.name ())) {
                modelio_element.setChangeable(KindOfAccess.WRITE);    
            }
        }
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbAttribute jaxb_element, final Attribute modelio_element, final IReadOnlyRepository repository) {
        // Typage de l'attribut
        for(Object sub : jaxb_element.getValueOrBaseTypeOrClassType()){
            if(sub instanceof JAXBElement) {
                JAXBElement<?> jaxb_sub = (JAXBElement<?>) sub;
                if("base-type".equals(jaxb_sub.getName().getLocalPart())){
                    String type = (String)jaxb_sub.getValue();        
                    modelio_element.setType(TypeConverter.getBaseType(type,this.model));        
                }else if("value".equals(jaxb_sub.getName().getLocalPart())){
                    modelio_element.setValue((String)jaxb_sub.getValue());
                }
            }else if(sub instanceof JaxbClassType){
                JaxbDestination jaxb_destination = ((JaxbClassType)sub).getDestination();
        
                MObject type = repository.getElementById(jaxb_destination.getRefid());
                if(type == null){
                    type = repository.getElementByNamespace(jaxb_destination, GeneralClass.class, this.session);
                    if(type == null){
                        type =  repository.createNamespace(jaxb_destination,(ModelTree) repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                    }
                    
                    ((Repository)repository).recordId(jaxb_destination.getRefid(), type);
                }
        
                modelio_element.setType((GeneralClass)type);
            }
        }
    }

    
    @Override
    public void deleteSubElements(final JaxbAttribute jaxb_element, final Attribute modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
