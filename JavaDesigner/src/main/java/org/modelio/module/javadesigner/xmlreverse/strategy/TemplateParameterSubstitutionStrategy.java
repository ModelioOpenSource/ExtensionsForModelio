package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameterSubstitution;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.vcore.smkernel.mapi.MObject;


public class TemplateParameterSubstitutionStrategy extends ModelElementStrategy implements IReverseBox<JaxbTemplateParameterSubstitution,TemplateParameterSubstitution> {
    
    public TemplateParameterSubstitutionStrategy(final IModelingSession session) {
        super (session);
    }

    
    @Override
    public TemplateParameterSubstitution getCorrespondingElement(final JaxbTemplateParameterSubstitution jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        TemplateBinding treeOwner = (TemplateBinding) owner;
                
        // Search by name
        for(TemplateParameterSubstitution sub_tree : treeOwner.getParameterSubstitution()){
            if(sub_tree.getName().equals(jaxb_element.getName())){
                if (!repository.isRecordedElement(sub_tree)) {
                    return sub_tree;
                }
            }
        }
                
        TemplateParameterSubstitution new_element = this.model.createTemplateParameterSubstitution();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbTemplateParameterSubstitution jaxb_element, final TemplateParameterSubstitution modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        modelio_element.setOwner((TemplateBinding) owner);
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        String value = jaxb_element.getValue();
        if (name != null) {
            modelio_element.setValue(value);
        }
        return null;
    }

    
    @Override
    public void deleteSubElements(final JaxbTemplateParameterSubstitution jaxb_element, final TemplateParameterSubstitution modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

    
    @Override
    public void postTreatment(final JaxbTemplateParameterSubstitution jaxb_element, final TemplateParameterSubstitution modelio_element, final IReadOnlyRepository repository) {
        JaxbDestination jaxb_destination = jaxb_element.getDestination();
        MObject type = repository.getElementById(jaxb_destination.getRefid());
        if(type == null){
            type = repository.getElementByNamespace(jaxb_destination, null, this.session);
            if(type == null){
                type =  repository.createNamespace(jaxb_destination, (ModelTree) repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
            }
            
            ((Repository)repository).recordId(jaxb_destination.getRefid(), type);
        }    
        modelio_element.setActual((UmlModelElement)type);
    }

}
