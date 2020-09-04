package org.modelio.module.javadesigner.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;

public class StereotypedAnnotationsManager {
    private static final String NL = System.getProperty ("line.separator"); // $NON-NLS-1$

    private Map<Stereotype, JavaAnnotation> annotationCache = null;

    protected CharSequence computeStereotypeAnnotations(final ModelElement element, final CharSequence indentLevel) {
        StringBuilder ret = new StringBuilder();
        for (Stereotype stereotype : element.getExtension()) {
            JavaAnnotation annotation = buildAnnotation(stereotype);
            if (annotation != null) {
                ret.append(indentLevel);
                ret.append("@");
                ret.append(annotation.getName());
        
                Map<String, String> parameters = annotation.getParameters(element);
                if (parameters != null && parameters.size() > 0) {
                    ret.append("(");
        
                    for (Iterator<Entry<String, String>> iterator = parameters.entrySet().iterator(); iterator.hasNext();) {
                        Entry<String, String> parameter = iterator.next();
        
                        // It is permissible to omit the element name in a single-element annotation named value
                        if (parameters.size() != 1 || !parameter.getKey().equals("value")) {
                            ret.append(parameter.getKey());
                            ret.append(" = ");
                        }
                        ret.append(parameter.getValue());
        
                        if (iterator.hasNext()) {
                            ret.append(", ");        
                        }
                    }
        
                    ret.append(")");
                }
                ret.append (NL);
            }
        }
        return ret;
    }

    private JavaAnnotation buildAnnotation(final Stereotype stereotype) {
        // Ensure cache is initialized
        if (this.annotationCache == null) {
            // Gather all stereotypes inheriting from the <<JavaAnnotationInstance>> stereotype
            Stereotype annotationStereotype = JavaDesignerUtils.getAbstractAnnotationStereotype();
            this.annotationCache = new HashMap<>();
            this.annotationCache.put(annotationStereotype, new JavaAnnotation(annotationStereotype));
            for (Stereotype s : getChildren(annotationStereotype)) {
                this.annotationCache.put(s, new JavaAnnotation(s));    
            }
        }
        return this.annotationCache.get(stereotype);
    }

    private Collection<Stereotype> getChildren(final Stereotype annotationStereotype) {
        List<Stereotype> ret = new ArrayList<>();
        for (Stereotype child : annotationStereotype.getChild()) {
            ret.add(child);
            ret.addAll(getChildren(child));
        }
        return ret;
    }

    private class JavaAnnotation {
        private Stereotype annotationStereotype;

        private List<TagType> annotationParameters;

        public JavaAnnotation(final Stereotype annotationStereotype) {
            this.annotationStereotype = annotationStereotype;
            this.annotationParameters = annotationStereotype.getDefinedTagType();
        }

        public Object getName() {
            return this.annotationStereotype.getName();
        }

        public Map<String, String> getParameters(final ModelElement element) {
            Map<String, String> parameters = new HashMap<>();
            for (TaggedValue tag : element.getTag()) {
                if (this.annotationParameters.contains(tag.getDefinition())) {
                    EList<TagParameter> values = tag.getActual();
                    if (values.size() > 0) {
                        parameters.put(tag.getDefinition().getName(), values.get(0).getValue());
                    } else {
                        parameters.put(tag.getDefinition().getName(), "");
                    }
                }
            }
            return parameters;
        }

    }

}
