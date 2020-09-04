package org.modelio.module.javadesigner.reverse.retrieve;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

public interface IRetrieveData {
    void inject(final IModelingSession session, final ModelElement elementToRetrieve) throws Exception;

}
