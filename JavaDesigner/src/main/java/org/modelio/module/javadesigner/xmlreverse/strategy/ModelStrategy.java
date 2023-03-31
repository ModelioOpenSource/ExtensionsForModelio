package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbModel;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;


public class ModelStrategy implements IReverseBox<JaxbModel,MObject> {
    
    @Override
    public MObject getCorrespondingElement(final JaxbModel jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        return owner;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbModel jaxb_element, final MObject modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        //    N/A
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbModel jaxb_element, final MObject modelio_element, final IReadOnlyRepository repository) {
        // N/A
    }

    
    @Override
    public void deleteSubElements(final JaxbModel jaxb_element, final MObject modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        // N/A
    }

}
