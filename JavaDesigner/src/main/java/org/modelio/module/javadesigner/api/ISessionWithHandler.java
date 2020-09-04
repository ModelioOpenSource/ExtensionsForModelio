package org.modelio.module.javadesigner.api;

import org.modelio.api.modelio.model.event.IModelChangeHandler;

public interface ISessionWithHandler {

    IModelChangeHandler getModelChangeHandler();

}
