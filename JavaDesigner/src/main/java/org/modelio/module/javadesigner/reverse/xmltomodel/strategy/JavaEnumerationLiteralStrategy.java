package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumerationLiteral;
import org.modelio.module.javadesigner.xmlreverse.strategy.EnumerationLiteralStrategy;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaEnumerationLiteralStrategy extends EnumerationLiteralStrategy {
    public JavaEnumerationLiteralStrategy(final IModelingSession session) {
        super (session);
    }

    @Override
    public List<MObject> updateProperties(final JaxbEnumerationLiteral jaxb_element, final EnumerationLiteral modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        Enumeration treeOwner = (Enumeration) owner;
        treeOwner.getValue().remove(modelio_element);
        modelio_element.setValuated (treeOwner);
        return super.updateProperties (jaxb_element, modelio_element, owner, repository);
    }

}
