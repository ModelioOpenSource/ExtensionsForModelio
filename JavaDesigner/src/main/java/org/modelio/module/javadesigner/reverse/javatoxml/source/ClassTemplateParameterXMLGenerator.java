package org.modelio.module.javadesigner.reverse.javatoxml.source;

import org.modelio.module.javadesigner.reverse.javatoxml.identification.Identified;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.StructuralModelUtils;

class ClassTemplateParameterXMLGenerator extends TemplateParameterXMLGenerator {
    @Override
    protected Identified getIdentifiedTemplateParameter(final String templateName, final Context ctx) {
        return StructuralModelUtils.getClassTemplateParameter(templateName, ctx.getGStack().getCurrentStructuralElement());
    }

}
