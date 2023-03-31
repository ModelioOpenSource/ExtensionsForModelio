package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbConstraint;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;


public class ConstraintStrategy extends ModelElementStrategy implements IReverseBox<JaxbConstraint,Constraint> {
    
    public ConstraintStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Constraint getCorrespondingElement(final JaxbConstraint jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        UmlModelElement treeOwner = (UmlModelElement) owner;
        
        for(Constraint sub_tree : treeOwner.getConstraintDefinition()){
            if(sub_tree.getName().equals(jaxb_element.getName())){
                for(Object obj : jaxb_element.getContent()){
                    if(obj instanceof String){
                        if(sub_tree.getBody().equals(obj)){
                            return sub_tree;
                        }
                    }
                }    
            }
        }    
        Constraint new_element = this.model.createConstraint();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbConstraint jaxb_element, final Constraint modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        UmlModelElement treeOwner = (UmlModelElement) owner;
        modelio_element.getConstrainedElement().add(treeOwner);
        
        modelio_element.setName(jaxb_element.getName());        
        for(Object obj : jaxb_element.getContent()){
            if(obj instanceof String){    
                modelio_element.setBody((String)obj);            
            }
        }
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbConstraint jaxb_element, final Constraint modelio_element, final IReadOnlyRepository repository) {
        // NA
    }

    
    @Override
    public void deleteSubElements(final JaxbConstraint jaxb_element, final Constraint modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
