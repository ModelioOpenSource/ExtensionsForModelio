package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClass;
import org.modelio.module.javadesigner.xmlreverse.model.JxbVisibilityMode;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;


public class ClassStrategy extends ModelElementStrategy implements IReverseBox<JaxbClass,Class> {
    
    public ClassStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Class getCorrespondingElement(final JaxbClass jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelTree treeOwner = (ModelTree) owner;
        
        for(ModelTree sub_tree : treeOwner.getOwnedElement(Class.class)){    
            if(sub_tree.getName().equals(jaxb_element.getName())){
                if (!repository.isRecordedElement(sub_tree)) {
                return (Class)sub_tree;
            }
        }
        }
        
        
        Class new_element = this.model.createClass();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbClass jaxb_element, final Class modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelTree treeOwner = (ModelTree) owner;
        
        modelio_element.setOwner(treeOwner);
        
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        
        Boolean isIsAbstract = jaxb_element.isIsAbstract();
        if (isIsAbstract != null) {
            modelio_element.setIsAbstract(isIsAbstract);
        }
        
        Boolean isIsElementary = jaxb_element.isIsElementary();
        if (isIsElementary != null) {
            modelio_element.setIsElementary(isIsElementary);
        }
        
        Boolean isIsLeaf = jaxb_element.isIsLeaf();
        if (isIsLeaf != null) {
            modelio_element.setIsLeaf(isIsLeaf);
        }
        
        Boolean isIsActive = jaxb_element.isIsActive();
        if (isIsActive != null) {
            modelio_element.setIsActive(isIsActive);
        }
        
        Boolean isIsMain = jaxb_element.isIsMain();
        if (isIsMain != null) {
            modelio_element.setIsMain(isIsMain);
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
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbClass jaxb_element, final Class modelio_element, final IReadOnlyRepository repository) {
        // NA
    }

    
    @Override
    public void deleteSubElements(final JaxbClass jaxb_element, final Class modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
