package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseLink;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDependency;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;


public class DependencyStrategy extends ModelElementStrategy implements IReverseLink<JaxbDependency,Dependency,ModelElement,ModelElement> {
    
    public DependencyStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Dependency getCorrespondingElement(final JaxbDependency jaxb_element, final ModelElement source, final ModelElement target, final IReadOnlyRepository repository) {
        // Search for a dependency between those elements
        for (Dependency link: source.getDependsOnDependency(Dependency.class)) {
            ModelElement targetModelElement = link.getDependsOn();
            if (targetModelElement.equals(target)) {
                return link;
            }
        }
                
        // No link found, create a new one
        return this.model.createDependency();
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbDependency jaxb_element, final Dependency modelio_element, final ModelElement source, final ModelElement target, final IReadOnlyRepository repository) {
        modelio_element.setImpacted(source);
        modelio_element.setDependsOn(target);
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbDependency jaxb_element, final Dependency modelio_element, final IReadOnlyRepository repository) {
        // N/A
    }

    
    @Override
    public void deleteSubElements(final JaxbDependency jaxb_element, final Dependency modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
