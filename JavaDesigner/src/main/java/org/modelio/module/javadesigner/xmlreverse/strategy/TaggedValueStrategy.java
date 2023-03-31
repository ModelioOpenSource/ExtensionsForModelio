package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTaggedValue;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.smkernel.mapi.MObject;


public class TaggedValueStrategy extends ModelElementStrategy implements IReverseBox<JaxbTaggedValue,TaggedValue> {
    
    public TaggedValueStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public TaggedValue getCorrespondingElement(final JaxbTaggedValue jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelElement treeOwner = (ModelElement) owner;
        
        String tagType = jaxb_element.getTagType();
        for (TaggedValue tag : treeOwner.getTag()) {
            TagType definition = tag.getDefinition();
            if (definition != null) {
                if (definition.getName().equals(tagType)) {
                    return tag;
                }
            }
        }
        
        // Type not found...
        List<TagType> types = this.session.getMetamodelExtensions().findTagTypes(tagType, owner.getMClass());
        if (types.size() > 0) {
            TaggedValue taggedValue = this.model.createTaggedValue();
            taggedValue.setDefinition(types.get(0));
            return taggedValue;
        } else {
            repository.getReportWriter().addError("Tag type not found : " + tagType, null, "Unable to find tag type: " + tagType);
            return null;
        }
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbTaggedValue jaxb_element, final TaggedValue modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        TaggedValue taggedValue = modelio_element;
        ModelElement treeOwner = (ModelElement) owner;
        String tagType = jaxb_element.getTagType();
        if (taggedValue != null) {
            treeOwner.getTag().add(taggedValue);
        } else {
            List<TagType> types = this.session.getMetamodelExtensions().findTagTypes(tagType, owner.getMClass());
            if (types.size() > 0) {
                taggedValue = this.model.createTaggedValue();
                taggedValue.setDefinition(types.get(0));
                taggedValue.setAnnoted(treeOwner);
            } else {
                repository.getReportWriter().addError("Tag type not found : " + tagType, null, "Unable to find tag type: " + tagType);
                return null;
            }
        }
        
        if (jaxb_element.getTagParameter() != null && !jaxb_element.getTagParameter().isEmpty()) {
            try {
                treeOwner.putTagValues(getOwnerModuleName(taggedValue.getDefinition()), tagType, jaxb_element.getTagParameter());
            } catch (ExtensionNotFoundException e) {
                repository.getReportWriter().addError("Tag type not found : " + tagType, null, "Unable to find tag type: " + tagType);
            }
        } else {
            List<TagParameter> parameters = new ArrayList<>(taggedValue.getActual());
            for (TagParameter parameter: parameters) {
                parameter.delete();
            }
        }
        return null;
    }

    
    private String getOwnerModuleName(final TagType type) throws ExtensionNotFoundException {
        if (type != null) {
            MetaclassReference ownerReference = type.getOwnerReference();
            Profile ownerProfile = (ownerReference != null) ? ownerReference.getOwnerProfile() : type.getOwnerStereotype().getOwner();
            return ownerProfile.getOwnerModule().getName();
        }
        
        throw new ExtensionNotFoundException();
    }

    
    @Override
    public void postTreatment(final JaxbTaggedValue jaxb_element, final TaggedValue modelio_element, final IReadOnlyRepository repository) {
        // NA
    }

    
    @Override
    public void deleteSubElements(final JaxbTaggedValue jaxb_element, final TaggedValue modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
        
        if (modelio_element != null) {
            if (modelio_element.getDefinition() == null) {
                modelio_element.delete();
            }
        }
    }

}
