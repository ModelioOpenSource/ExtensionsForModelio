package org.modelio.module.javadesigner.xmlreverse;

import org.modelio.module.javadesigner.xmlreverse.model.JaxbReversedData;

public interface IReverseSessionHandler {

    void executePreTreatment(final JaxbReversedData reversdata);

    void executePostTreatment(final JaxbReversedData reversdata);

}
