package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;


public abstract class ModelElementStrategy {
    
    protected IUmlModel model;

    
    protected IModelingSession session;

    
    public ModelElementStrategy(final IModelingSession session) {
        this.session = session;
        this.model = session.getModel();
    }

    
    public void deleteSubElements(final ModelElement modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        repository.getModelElementDeleteStrategy().deleteSubElements(modelio_element, element_todelete, repository);
    }

}
