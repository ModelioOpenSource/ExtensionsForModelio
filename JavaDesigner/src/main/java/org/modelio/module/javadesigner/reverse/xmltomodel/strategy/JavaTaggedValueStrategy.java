package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.Collections;
import java.util.List;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.model.JaxbTaggedValue;
import com.modelio.module.xmlreverse.strategy.TaggedValueStrategy;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.ReverseStrategyConfiguration;
import org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaTaggedValueStrategy extends TaggedValueStrategy {
    public ReverseStrategyConfiguration reverseConfig;

    public JavaTaggedValueStrategy(final IModelingSession session, final ReverseStrategyConfiguration reverseConfig) {
        super (session);
        this.reverseConfig = reverseConfig;
    }

    @Override
    public TaggedValue getCorrespondingElement(final JaxbTaggedValue jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        if (owner instanceof Note) {
            ModelElement treeOwner = (ModelElement) owner.getCompositionOwner();
        
            final String jaxbType = jaxb_element.getTagType();
            int separatorIndex = jaxbType.indexOf(XMLGenerator.ANNOTATION_PARAMETER_SEPARATOR);
        
            String stereotypeName = jaxbType.substring(0, separatorIndex);
            String tagTypeName = jaxbType.substring(separatorIndex + 1);
            jaxb_element.setTagType(tagTypeName);
            
            for (TaggedValue tag : treeOwner.getTag()) {
                TagType definition = tag.getDefinition();
                if (definition != null) {
                    final Stereotype stereo = definition.getOwnerStereotype();
                    if (definition.getName().equals(tagTypeName) && stereotypeName.equals(stereo) && JavaDesignerUtils.isAnnotationStereotype(stereo)) {
                        return tag;
                    }
                }
            }
        
            // Type not found...
            for (TagType tagType : this.session.getMetamodelExtensions().findTagTypes(tagTypeName, treeOwner.getMClass())) {
                if (tagType.getOwnerStereotype() != null && tagType.getOwnerStereotype().getName().equals(stereotypeName) && JavaDesignerUtils.isAnnotationStereotype(tagType.getOwnerStereotype())) {
                    TaggedValue taggedValue = this.model.createTaggedValue();
                    taggedValue.setDefinition(tagType);
                    return taggedValue;
                }
            }
        
            if (cleanupAnnotationStereotype(stereotypeName, treeOwner)) {
                repository.getReportWriter().addWarning(Messages.getString("Warning.AnnotationParameterNotFound.title", tagTypeName), treeOwner, Messages.getString("Warning.AnnotationParameterNotFound.description", tagTypeName, stereotypeName));
            }
            return null;
        } else {
            return super.getCorrespondingElement(jaxb_element, owner, repository);
        }
    }

    private boolean cleanupAnnotationStereotype(final String stereotypeName, final ModelElement treeOwner) {
        for (Stereotype stereo : treeOwner.getExtension()) {
            if (stereo.getName().equals(stereotypeName) && JavaDesignerUtils.isAnnotationStereotype(stereo)) {
                treeOwner.getExtension().remove(stereo);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MObject> updateProperties(final JaxbTaggedValue jaxb_element, final TaggedValue modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        if (owner instanceof Note) {
            if (modelio_element != null) {
                return super.updateProperties(jaxb_element, modelio_element, owner.getCompositionOwner(), repository);
            } else {
                return Collections.emptyList();
            }
        } else {
            return super.updateProperties(jaxb_element, modelio_element, owner, repository);    
        }
    }

    @Override
    public void postTreatment(final JaxbTaggedValue jaxb_element, final TaggedValue modelio_element, final IReadOnlyRepository repository) {
        super.postTreatment(jaxb_element, modelio_element, repository);
    }

}
