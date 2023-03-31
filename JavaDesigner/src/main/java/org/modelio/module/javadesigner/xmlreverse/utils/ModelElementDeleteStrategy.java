package org.modelio.module.javadesigner.xmlreverse.utils;

import java.util.Collection;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MObject;


public class ModelElementDeleteStrategy {
    
    public ModelElementDeleteStrategy() {
        // Nothing to do
    }

    
    public void deleteSubElements(final ModelElement modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        for(MObject element : element_todelete){
            if (element instanceof Stereotype) {
                modelio_element.getExtension().remove(element);
            } else if (element instanceof Constraint) {
                Constraint theConstraint = (Constraint) element;
                theConstraint.getConstrainedElement().remove(modelio_element);
                
                // A constraint must be deleted only if it has no more parents
                if (theConstraint.getConstrainedElement().size() == 0) {
                    theConstraint.delete();
                }
            } else {
                element.delete();
            }
        }
    }

}
