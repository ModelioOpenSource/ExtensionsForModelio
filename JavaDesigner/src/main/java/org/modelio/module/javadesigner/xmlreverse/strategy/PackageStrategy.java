package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbPackage;
import org.modelio.module.javadesigner.xmlreverse.model.JxbVisibilityMode;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;


public class PackageStrategy extends ModelElementStrategy implements IReverseBox<JaxbPackage,Package> {
    
    public PackageStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Package getCorrespondingElement(final JaxbPackage jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelTree treeOwner = (ModelTree) owner;
        
        for(ModelTree sub_tree : treeOwner.getOwnedElement(Package.class)){    
            if(sub_tree.getName().equals(jaxb_element.getName())){
                if (!repository.isRecordedElement(sub_tree)) {
                    return (Package)sub_tree;
                }
            }
        }
        
        Package new_element = this.model.createPackage();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbPackage jaxb_element, final Package modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelTree treeOwner = (ModelTree) owner;
        
        modelio_element.setOwner(treeOwner);
        
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        Boolean isAbstract = jaxb_element.isIsAbstract();
        if (isAbstract != null) {
            modelio_element.setIsAbstract (isAbstract);
        }
        Boolean isLeaf = jaxb_element.isIsLeaf();
        if (isLeaf != null) {
            modelio_element.setIsLeaf (isLeaf);
        }
        Boolean isInstantiable = jaxb_element.isIsInstantiable();
        if (isLeaf != null) {
            modelio_element.setIsInstantiable (isInstantiable);
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
    public void postTreatment(final JaxbPackage jaxb_element, final Package modelio_element, final IReadOnlyRepository repository) {
        // N/A
    }

    
    @Override
    public void deleteSubElements(final JaxbPackage jaxb_element, final Package modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        Collection<MObject> to_delete = new ArrayList<>();        
        
        // On a package, we only delete MDA extensions
        for(MObject element : element_todelete){
            if (element instanceof Stereotype) {
                to_delete.add(element);
            } else if (element instanceof TaggedValue) {
                to_delete.add(element);
            } else if (element instanceof Note) {
                to_delete.add(element);
            } else if (element instanceof Constraint) {
                to_delete.add(element);
            }
        }
        
        super.deleteSubElements(modelio_element, to_delete, repository);
    }

}
