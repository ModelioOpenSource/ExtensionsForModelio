package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDefaultType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameter;
import org.modelio.module.javadesigner.xmlreverse.utils.TypeConverter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MObject;


public class TemplateParameterStrategy extends ModelElementStrategy implements IReverseBox<JaxbTemplateParameter,TemplateParameter> {
    
    public TemplateParameterStrategy(final IModelingSession session) {
        super (session);
    }

    
    @Override
    public TemplateParameter getCorrespondingElement(final JaxbTemplateParameter jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        if (owner instanceof NameSpace) {
            NameSpace treeOwner = (NameSpace) owner;
            
            // Search by name
            for(TemplateParameter sub_tree : treeOwner.getTemplate()){    
                if(sub_tree.getName().equals(jaxb_element.getName())){
                    if (!repository.isRecordedElement(sub_tree)) {
                        return sub_tree;
                    }
                }
            }
        } else {
            Operation treeOwner = (Operation) owner;
            
            // Search by name
            for(TemplateParameter sub_tree : treeOwner.getTemplate()){    
                if(sub_tree.getName().equals(jaxb_element.getName())){
                    if (!repository.isRecordedElement(sub_tree)) {
                        return sub_tree;
                    }
                }
            }
        }
                
        TemplateParameter new_element = this.model.createTemplateParameter();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbTemplateParameter jaxb_element, final TemplateParameter modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        // Set owner
        if (owner instanceof NameSpace) {
            NameSpace treeOwner = (NameSpace) owner;
            modelio_element.setParameterized(treeOwner);
        } else {
            Operation treeOwner = (Operation) owner;
            modelio_element.setParameterizedOperation(treeOwner);
        }
        
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        return null;
    }

    
    @Override
    public void deleteSubElements(final JaxbTemplateParameter jaxb_element, final TemplateParameter modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

    
    @Override
    public void postTreatment(final JaxbTemplateParameter jaxb_element, final TemplateParameter modelio_element, final IReadOnlyRepository repository) {
        // Typage de l'attribut
        for(Object sub : jaxb_element.getPackageOrClazzOrOperation()){
            if(sub instanceof JaxbDefaultType){
                JaxbDefaultType jaxb_defaultType = (JaxbDefaultType) sub;
                if (jaxb_defaultType.getBaseType() != null) {
                    String type = jaxb_defaultType.getBaseType();        
                    modelio_element.setDefaultType(TypeConverter.getBaseType(type,this.model));        
                }else if (jaxb_defaultType.getClassType() != null) {
                    JaxbDestination jaxb_destination = jaxb_defaultType.getClassType().getDestination();
        
                    MObject type = repository.getElementById(jaxb_destination.getRefid());
                    if(type == null){
                        type = repository.getElementByNamespace(jaxb_destination, GeneralClass.class, this.session);
                        if(type == null){
                            type =  repository.createNamespace(jaxb_destination,(ModelTree) repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                        }
                        
                        ((Repository)repository).recordId(jaxb_destination.getRefid(), type);
                    }
        
                    modelio_element.setType((GeneralClass)type);
                    modelio_element.setDefaultType((GeneralClass)type);
                }
            }
        }
    }

}
