package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.List;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.IReportWriter;
import com.modelio.module.xmlreverse.model.JaxbReportItem;
import com.modelio.module.xmlreverse.model.JaxbTargetItem;
import com.modelio.module.xmlreverse.strategy.ReportItemStrategy;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaReportItemStrategy extends ReportItemStrategy {
    @Override
    public List<MObject> updateProperties(final JaxbReportItem jaxb_element, final MObject modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        String message = jaxb_element.getReport ();
        message = message.trim ();
        
        if (jaxb_element.getTitle().isEmpty()) {
            int firstLineEnd = message.indexOf ("\n");
            if (firstLineEnd > 0) {
                jaxb_element.setTitle (message.substring (0, firstLineEnd));
            } else {
                jaxb_element.setTitle (message);
            }
        }
        
        IReportWriter report = repository.getReportWriter ();
        String type = jaxb_element.getType ();
        if (type.equals ("error")) {
            if (jaxb_element.getTargetItem () == null) {
                report.addError (jaxb_element.getTitle (), null, jaxb_element.getReport ().trim ());
            }
        }
        return super.updateProperties (jaxb_element, modelio_element, owner, repository);
    }

    @Override
    public void postTreatment(final JaxbReportItem jaxb_element, final MObject modelio_element, final IReadOnlyRepository repository) {
        IReportWriter report = repository.getReportWriter ();
        
        if (report != null) {
            MObject target;
            JaxbTargetItem item = jaxb_element.getTargetItem ();
            if (item != null && item.getDestination () != null) {
                target = repository.getElementById (item.getDestination ().getRefid ());
            } else {
                target = null;
            }
        
            String type = jaxb_element.getType ();
            if (type.equals ("warning")) {
                report.addWarning (jaxb_element.getTitle (), target, jaxb_element.getReport ().trim ());
            } else if (type.equals ("error")) {
                if (jaxb_element.getTargetItem () != null) {
                    report.addError (jaxb_element.getTitle (), target, jaxb_element.getReport ().trim ());
                }
            } else if (type.equals ("info")) {
                report.addInfo (jaxb_element.getTitle (), target, jaxb_element.getReport ().trim ());
            }
        }
    }

}
