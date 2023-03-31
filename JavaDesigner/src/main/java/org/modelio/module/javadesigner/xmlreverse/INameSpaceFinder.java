package org.modelio.module.javadesigner.xmlreverse;

import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.vcore.smkernel.mapi.MObject;


public interface INameSpaceFinder {
    
    MObject createNamespace(final JaxbDestination destination, final ModelTree root, final Class<? extends Classifier> type, final IModelingSession session);

    
    String getNamespace(final ModelTree element, final IModelingSession session);

    
    List<MObject> getElementByNamespace(final JaxbDestination destination, final Class<? extends Classifier> classifierType, final IModelingSession session);

    
    <T extends MObject> T resolveMultipleNamespaces(final List<T> possibleElements);

    
    MObject getElementByNameSpace(final String targetFullName, final Class<? extends Classifier> classifierType, final IModelingSession session);

}
