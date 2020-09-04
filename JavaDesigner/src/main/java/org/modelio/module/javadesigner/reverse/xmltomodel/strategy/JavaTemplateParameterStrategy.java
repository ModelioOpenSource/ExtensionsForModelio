package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.IReportWriter;
import com.modelio.module.xmlreverse.model.JaxbTemplateParameter;
import com.modelio.module.xmlreverse.strategy.TemplateParameterStrategy;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;

public class JavaTemplateParameterStrategy extends TemplateParameterStrategy {
    public JavaTemplateParameterStrategy(final IModelingSession session) {
        super(session);
    }

    @Override
    public void postTreatment(final JaxbTemplateParameter jaxb_element, final TemplateParameter modelio_element, final IReadOnlyRepository repository) {
        // Reset the type first
        modelio_element.setType(null);
        modelio_element.setDefaultType(null);
        
        super.postTreatment(jaxb_element, modelio_element, repository);
        
        // TODO template 'type'/'default type' management is messy, we need to homogenize it
        // As Java only uses 'type templates', a setType(null) is needed to avoid audit errors when typing a parameter.
        if (modelio_element.getParameterized() != null) {
            // Java uses the 'DefaultType' association for TemplateParameters on GeneralClasses 
            modelio_element.setDefaultType(null);
        } else {
            // Java uses the 'Type' association for TemplateParameters on Operations
            modelio_element.setType(null);
        }
        
        if (modelio_element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.TEMPLATEPARAMETER_JAVAEXTENDS)) {
            String javaext = modelio_element.getTagValue(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.TEMPLATEPARAMETER_JAVAEXTENDS);
            // Emit the warning only if the extends is not a generic type itself or an annotated type
            if (javaext != null && javaext.indexOf('<') == -1 && javaext.indexOf('@') == -1) {
                IReportWriter report = repository.getReportWriter();
                report.addWarning(Messages.getString("reverse.Type_not_found.title", javaext), modelio_element, Messages.getString("reverse.Type_not_found.description", javaext)); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
    }

}
