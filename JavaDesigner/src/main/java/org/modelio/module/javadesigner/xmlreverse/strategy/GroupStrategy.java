package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbGroup;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.vcore.smkernel.mapi.MObject;


public class GroupStrategy implements IReverseBox<JaxbGroup,MObject> {
    
    protected IUmlModel model;

    
    protected IModelingSession session;

    
    public GroupStrategy(final IModelingSession session) {
        this.session = session;
        this.model = session.getModel();
    }

    
    @Override
    public MObject getCorrespondingElement(final JaxbGroup jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        // N/A
        return null;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbGroup jaxb_element, final MObject modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        // N/A
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbGroup jaxb_element, final MObject modelio_element, final IReadOnlyRepository repository) {
        // N/A
    }

    
    @Override
    public void deleteSubElements(final JaxbGroup jaxb_element, final MObject modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        // N/A
    }

}
