package org.modelio.module.javadesigner.reverse.javatoxml.source;

import org.modelio.module.javadesigner.reverse.javatoxml.identification.Identified;

class OperationTemplateParameterXMLGenerator extends TemplateParameterXMLGenerator {
    @Override
    protected Identified getIdentifiedTemplateParameter(final String templateName, final Context ctx) {
        return ctx.getTFinder().registerOperationTemplateParameter(templateName);
    }

}
