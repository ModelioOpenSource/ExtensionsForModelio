package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbOperation;
import org.modelio.module.javadesigner.xmlreverse.model.JxbVisibilityMode;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.MethodPassingMode;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;


public class OperationStrategy extends ModelElementStrategy implements IReverseBox<JaxbOperation,Operation> {
    
    public OperationStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Operation getCorrespondingElement(final JaxbOperation jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        if(owner instanceof Classifier){
            Classifier treeOwner = (Classifier) owner;
            for(Feature sub_tree : treeOwner.getOwnedOperation()){
                if(sub_tree.getName().equals(jaxb_element.getName())){
                    if (!repository.isRecordedElement(sub_tree)) {
                        return (Operation)sub_tree;
                    }
                }
            }
        }
        
        
        Operation new_element = this.model.createOperation();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbOperation jaxb_element, final Operation modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        if (owner instanceof Classifier) {
            modelio_element.setOwner((Classifier)owner);
        } else if (owner instanceof TemplateParameter) {
            modelio_element.setOwnerTemplateParameter((TemplateParameter)owner);
        }
        
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        
        Boolean isAbstract = jaxb_element.isIsAbstract();
        if (isAbstract != null) {
            modelio_element.setIsAbstract(isAbstract);
        }
        
        Boolean isClass = jaxb_element.isIsClass();
        if (isClass != null) {
            modelio_element.setIsClass(isClass);
        }
        
        Boolean isConcurrency = jaxb_element.isConcurrency();
        if (isConcurrency != null) {
            modelio_element.setConcurrency(isConcurrency);
        }
        
        Boolean isFinal = jaxb_element.isFinal();
        if (isFinal != null) {
            modelio_element.setFinal(isFinal);
        }
        
        String passing = jaxb_element.getPassing();    
        if (passing != null) {
            if(passing.equals(MethodPassingMode.METHODIN.name())){
                modelio_element.setPassing(MethodPassingMode.METHODIN);
            }else if(passing.equals(MethodPassingMode.METHODOUT.name())){
                modelio_element.setPassing(MethodPassingMode.METHODOUT);
            }
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
    public void postTreatment(final JaxbOperation jaxb_element, final Operation modelio_element, final IReadOnlyRepository repository) {
        // NA
    }

    
    @Override
    public void deleteSubElements(final JaxbOperation jaxb_element, final Operation modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
