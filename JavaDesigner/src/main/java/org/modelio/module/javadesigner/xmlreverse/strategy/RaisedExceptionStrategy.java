package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseLink;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbRaisedException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.vcore.smkernel.mapi.MObject;


public class RaisedExceptionStrategy extends ModelElementStrategy implements IReverseLink<JaxbRaisedException,RaisedException,Operation,Classifier> {
    
    public RaisedExceptionStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public RaisedException getCorrespondingElement(final JaxbRaisedException jaxb_element, final Operation source, final Classifier target, final IReadOnlyRepository repository) {
        // Search for a raised exception between those elements
        for (RaisedException link: source.getThrown()) {
            Classifier targetNameSpace = link.getThrownType();
            if (targetNameSpace.equals(target)) {
                return link;
            }
        }
                
        // No link found, create a new one
        return this.model.createRaisedException();
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbRaisedException jaxb_element, final RaisedException modelio_element, final Operation source, final Classifier target, final IReadOnlyRepository repository) {
        modelio_element.setThrower(source);
        modelio_element.setThrownType(target);
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbRaisedException jaxb_element, final RaisedException modelio_element, final IReadOnlyRepository repository) {
        // N/A
    }

    
    @Override
    public void deleteSubElements(final JaxbRaisedException jaxb_element, final RaisedException modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
