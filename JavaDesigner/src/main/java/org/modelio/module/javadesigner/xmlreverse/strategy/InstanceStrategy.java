package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbInstance;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;


public class InstanceStrategy extends ModelElementStrategy implements IReverseBox<JaxbInstance,Instance> {
    
    public InstanceStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Instance getCorrespondingElement(final JaxbInstance jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        NameSpace treeOwner = (NameSpace) owner;
        
        for(Instance sub_tree : treeOwner.getDeclared()){
            if(sub_tree.getName().equals(jaxb_element.getName())){
                if (!repository.isRecordedElement(sub_tree)) {
                return sub_tree;
            }
        }
        }
        
        
        Instance new_element = this.model.createInstance();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbInstance jaxb_element, final Instance modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        NameSpace treeOwner = (NameSpace) owner;
        modelio_element.setOwner(treeOwner);
        
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbInstance jaxb_element, final Instance modelio_element, final IReadOnlyRepository repository) {
        // NA
    }

    
    @Override
    public void deleteSubElements(final JaxbInstance jaxb_element, final Instance modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
