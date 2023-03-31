package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseLink;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUse;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.vcore.smkernel.mapi.MObject;


public class UseStrategy extends ModelElementStrategy implements IReverseLink<JaxbUse,Usage,ModelElement,ModelElement> {
    
    public UseStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Usage getCorrespondingElement(final JaxbUse jaxb_element, final ModelElement source, final ModelElement target, final IReadOnlyRepository repository) {
        // Search for a dependency between those elements
        for (Dependency link: source.getDependsOnDependency(Usage.class)) {
            ModelElement targetModelElement = link.getDependsOn();
            if (targetModelElement.equals(target)) {
                return (Usage) link;
            }
        }
        
        // No link found, create a new one
        return this.model.createUsage();
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbUse jaxb_element, final Usage modelio_element, final ModelElement source, final ModelElement target, final IReadOnlyRepository repository) {
        modelio_element.setImpacted(source);
        modelio_element.setDependsOn(target);
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbUse jaxb_element, final Usage modelio_element, final IReadOnlyRepository repository) {
        // Nothing to do
    }

    
    @Override
    public void deleteSubElements(final JaxbUse jaxb_element, final Usage modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
