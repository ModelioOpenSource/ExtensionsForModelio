package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateBinding;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.vcore.smkernel.mapi.MObject;


public class TemplateBindingStrategy extends ModelElementStrategy implements IReverseBox<JaxbTemplateBinding,TemplateBinding> {
    
    public TemplateBindingStrategy(final IModelingSession session) {
        super (session);
    }

    
    @Override
    public TemplateBinding getCorrespondingElement(final JaxbTemplateBinding jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        if (owner instanceof NameSpace) {
            NameSpace treeOwner = (NameSpace) owner;
                
            // Search by name
            for(TemplateBinding sub_tree : treeOwner.getTemplateInstanciation()){    
                if(sub_tree.getName().equals(jaxb_element.getName())){
                    if (!repository.isRecordedElement(sub_tree)) {
                        return sub_tree;
                    }
                }
            }
        } else {
            Operation treeOwner = (Operation) owner;
                
            // Search by name
            for(TemplateBinding sub_tree : treeOwner.getTemplateInstanciation()){    
                if(sub_tree.getName().equals(jaxb_element.getName())){
                    if (!repository.isRecordedElement(sub_tree)) {
                        return sub_tree;
                    }
                }
            }
        }
                
        TemplateBinding new_element = this.model.createTemplateBinding();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbTemplateBinding jaxb_element, final TemplateBinding modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        // Set owner
        if (owner instanceof NameSpace) {
            NameSpace treeOwner = (NameSpace) owner;
            modelio_element.setBoundElement(treeOwner);
        } else {
            Operation treeOwner = (Operation) owner;
            modelio_element.setBoundOperation(treeOwner);
        }
                
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        return null;
    }

    
    @Override
    public void deleteSubElements(final JaxbTemplateBinding jaxb_element, final TemplateBinding modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

    
    @Override
    public void postTreatment(final JaxbTemplateBinding jaxb_element, final TemplateBinding modelio_element, final IReadOnlyRepository repository) {
        // N/A
    }

}
