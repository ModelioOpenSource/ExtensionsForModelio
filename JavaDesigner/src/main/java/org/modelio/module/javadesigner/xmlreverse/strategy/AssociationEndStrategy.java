package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbAssociationEnd;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClassType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JxbVisibilityMode;
import org.modelio.module.javadesigner.xmlreverse.utils.TypeConverter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;


public class AssociationEndStrategy extends ModelElementStrategy implements IReverseBox<JaxbAssociationEnd,AssociationEnd> {
    
    public AssociationEndStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public AssociationEnd getCorrespondingElement(final JaxbAssociationEnd jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        Classifier classifierOwner = (Classifier) owner;
        
        for(AssociationEnd feat : classifierOwner.getOwnedEnd()){
            if(feat.getName().equals(jaxb_element.getName()) && (feat.isNavigable())){
                if (!repository.isRecordedElement(feat)) {
                    return feat;
                }
            }
        }
        
        AssociationEnd new_element = this.model.createAssociationEnd();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbAssociationEnd jaxb_element, final AssociationEnd modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        Classifier classifierOwner = (Classifier) owner;
        modelio_element.setSource(classifierOwner);
        
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        
        String changeable = jaxb_element.getChangeable();
        if (changeable != null) {
            changeable = changeable.toUpperCase();
            if(changeable.equalsIgnoreCase(KindOfAccess.ACCESNONE.name())){
                modelio_element.setChangeable(KindOfAccess.ACCESNONE);
            } else if(changeable.equalsIgnoreCase(KindOfAccess.READ.name())){
                modelio_element.setChangeable(KindOfAccess.READ);
            }else if(changeable.equalsIgnoreCase(KindOfAccess.READWRITE.name())){
                modelio_element.setChangeable(KindOfAccess.READWRITE);
            }else if(changeable.equalsIgnoreCase(KindOfAccess.WRITE.name())){
                modelio_element.setChangeable(KindOfAccess.WRITE);
            }
        }
        
        String multiplicityMin = jaxb_element.getMultiplicityMin();
        if (multiplicityMin != null) {
            modelio_element.setMultiplicityMin(multiplicityMin);
        }
        
        String multiplicityMax = jaxb_element.getMultiplicityMax();
        if (multiplicityMax != null) {
            modelio_element.setMultiplicityMax(multiplicityMax);
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
        
        Boolean isAbstract = jaxb_element.isIsAbstract();
        if (isAbstract != null) {
            modelio_element.setIsAbstract(isAbstract);
        }
        
        Boolean isIsChangeable = jaxb_element.isIsChangeable();
        if (isIsChangeable != null) {
            modelio_element.setIsChangeable(isIsChangeable);
        }
        
        Boolean isIsClass = jaxb_element.isIsClass();
        if (isIsClass != null) {
            modelio_element.setIsClass(isIsClass);    
        }
        
        Boolean isIsOrdered = jaxb_element.isIsOrdered();
        if (isIsOrdered != null) {
            modelio_element.setIsOrdered(isIsOrdered);
        }
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbAssociationEnd jaxb_element, final AssociationEnd modelio_element, final IReadOnlyRepository repository) {
        // Typage de l'attribut
        
        for(Object sub : jaxb_element.getBaseTypeOrClassTypeOrNote()){
            if(sub instanceof JAXBElement){
                JAXBElement<?> jaxb_sub = (JAXBElement<?>) sub;
                if("base-type".equals(jaxb_sub.getName().getLocalPart())){
                    DataType type = TypeConverter.getBaseType((String)jaxb_sub.getValue(), this.model);
        
                    AssociationEnd opposite = modelio_element.getOpposite();
                    if(opposite == null){
                        opposite = this.model.createAssociationEnd();
                        opposite.setOpposite(modelio_element);
                        modelio_element.setOpposite(opposite);
                    }
                    modelio_element.setTarget(type, true);
                }
            }else if(sub instanceof JaxbClassType){
                JaxbDestination jaxb_destination = ((JaxbClassType)sub).getDestination();
                MObject type = repository.getElementById(jaxb_destination.getRefid());
                if(type == null){
                    type = repository.getElementByNamespace(jaxb_destination, Classifier.class, this.session);
                    if(type == null){
                        type =  repository.createNamespace(jaxb_destination,(ModelTree) repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                    }
        
                    ((Repository)repository).recordId(jaxb_destination.getRefid(), type);
                }
                // Should never happen
                if (!(type instanceof Enumeration) && !(type instanceof TemplateParameter)) {
                    AssociationEnd opposite = modelio_element.getOpposite();
                    if(opposite == null){
                        opposite = this.model.createAssociationEnd();
                        opposite.setOpposite(modelio_element);
                        modelio_element.setOpposite(opposite);
                    }
        
                    modelio_element.setTarget((Classifier)type, true);
                } else {
                    repository.getReportWriter().addError("Unable to create an association towards " + type.toString(), null, "");
                }
            }
        }
        
        if (modelio_element != null) {
            Association new_assoc = modelio_element.getAssociation();
            if (new_assoc == null) {
                new_assoc = this.model.createAssociation();
                new_assoc.getEnd().add(modelio_element);
                new_assoc.getEnd().add(modelio_element.getOpposite());
            }
        }
        
        if(modelio_element != null && !modelio_element.isDeleted() && modelio_element.getOpposite() == null){
            modelio_element.delete();
        }
    }

    
    @Override
    public void deleteSubElements(final JaxbAssociationEnd jaxb_element, final AssociationEnd modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
