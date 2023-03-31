package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReportList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;


public class ReportListStrategy implements IReverseBox<JaxbReportList,MObject> {
    
    @Override
    public MObject getCorrespondingElement(final JaxbReportList jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        return owner;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbReportList jaxb_element, final MObject modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        // N/A
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbReportList jaxb_element, final MObject modelio_element, final IReadOnlyRepository repository) {
        // N/A
    }

    
    @Override
    public void deleteSubElements(final JaxbReportList jaxb_element, final MObject modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        // N/A
    }

}
