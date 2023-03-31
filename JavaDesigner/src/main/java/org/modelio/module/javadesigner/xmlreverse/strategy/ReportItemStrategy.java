package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReportWriter;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReportItem;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTargetItem;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;


public class ReportItemStrategy implements IReverseBox<JaxbReportItem,MObject> {
    
    @Override
    public MObject getCorrespondingElement(final JaxbReportItem jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        return owner;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbReportItem jaxb_element, final MObject modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        // N/A
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbReportItem jaxb_element, final MObject modelio_element, final IReadOnlyRepository repository) {
        IReportWriter report = repository.getReportWriter();
        
        if (report != null) {
            MObject target;
            JaxbTargetItem item = jaxb_element.getTargetItem();
            if (item != null) {
                target = repository.getElementById(item.getDestination().getRefid());
            } else {
                target = null;
            }
            
            String type = jaxb_element.getType ();
            if (type.equals ("warning")) {
                report.addWarning (jaxb_element.getTitle (), target, jaxb_element.getReport ().trim());
            } else if (type.equals ("error")) {
                report.addError (jaxb_element.getTitle (), target, jaxb_element.getReport ().trim());
            } else if (type.equals ("info")) {
                report.addInfo (jaxb_element.getTitle (), target, jaxb_element.getReport ().trim());
            } 
        }
    }

    
    @Override
    public void deleteSubElements(final JaxbReportItem jaxb_element, final MObject modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        // N/A
    }

}
