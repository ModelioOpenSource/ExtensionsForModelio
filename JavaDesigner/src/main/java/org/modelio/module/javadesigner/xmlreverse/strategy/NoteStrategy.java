package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbNote;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.vcore.smkernel.mapi.MObject;


public class NoteStrategy extends ModelElementStrategy implements IReverseBox<JaxbNote,Note> {
    
    public NoteStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Note getCorrespondingElement(final JaxbNote jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelElement treeOwner = (ModelElement) owner;
        
        String noteType = jaxb_element.getNoteType();
        for (Note note : treeOwner.getDescriptor()) {
            NoteType mdl = note.getModel();
            if (mdl != null) {
                if (mdl.getName().equals(noteType)) {
                    return note;
                }
            }
        }
        
        // Type not found...
        List<NoteType> types = this.session.getMetamodelExtensions().findNoteTypes(noteType, owner.getMClass());
        if (types.size() > 0) {
            Note taggedValue = this.model.createNote();
            taggedValue.setModel(types.get(0));
            return taggedValue;
        } else {
            repository.getReportWriter().addError("Note type not found : " + noteType, null, "Unable to find note type: " + noteType);
            return null;
        }
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbNote jaxb_element, final Note modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelElement treeOwner = (ModelElement) owner;
        modelio_element.setSubject(treeOwner);
        
        for(Object content : jaxb_element.getStereotypeOrTaggedValueOrContent()){
            if(content instanceof String){            
                modelio_element.setContent((String)content);    
            }
        }
        
        String noteType = jaxb_element.getNoteType();
        List<NoteType> types = this.session.getMetamodelExtensions().findNoteTypes(noteType, modelio_element.getSubject().getMClass());
        if (types.size() > 0) {
            modelio_element.setModel(types.get(0));
        } else {
            repository.getReportWriter().addError("Note type not found : " + noteType, null, "Unable to find note type: " + noteType);
        }
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbNote jaxb_element, final Note modelio_element, final IReadOnlyRepository repository) {
        // NA
    }

    
    @Override
    public void deleteSubElements(final JaxbNote jaxb_element, final Note modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
        
        if (modelio_element.getModel() == null) {
            modelio_element.delete();
        }
    }

}
