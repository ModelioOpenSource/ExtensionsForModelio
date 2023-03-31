package org.modelio.module.javadesigner.xmlreverse;

import java.util.Collection;
import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.javadesigner.xmlreverse.model.IVisitorElement;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.utils.ModelElementDeleteStrategy;
import org.modelio.vcore.smkernel.mapi.MObject;

public interface IReadOnlyRepository {

    MObject getElement(final IVisitorElement jaxb_element);

    MObject getElementById(final String id);

    boolean isRecordedElement(final MObject modelio_element);

    MObject getRoot();

    IReportWriter getReportWriter();

    MObject createNamespace(final JaxbDestination destination, final ModelTree root, final Class<? extends Classifier> classifierType, final IModelingSession session);

    String getNamespace(final ModelTree modelio_element, final IModelingSession session);

    MObject getElementByNamespace(final JaxbDestination destination, final Class<? extends Classifier> classifierType, final IModelingSession session);

    MObject getElementByNameSpace(final String targetFullName, final Class<? extends Classifier> classifierType, final IModelingSession session);

    ModelElementDeleteStrategy getModelElementDeleteStrategy();

    <T extends MObject> T resolveMultipleNamespaces(final List<T> possibleElements);

    Collection<MObject> getElementValues();

}
