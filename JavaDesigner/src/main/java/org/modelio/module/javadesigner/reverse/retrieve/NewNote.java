package org.modelio.module.javadesigner.reverse.retrieve;

import com.modelio.module.xmlreverse.IReportWriter;
import com.modelio.module.xmlreverse.Repository;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.reverse.xmltomodel.NoteReverseUtils;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;

public class NewNote extends NoteData {
    private String parentId = null;

    @Override
    public void inject(final IModelingSession session, final ModelElement elementToRetrieve) throws ElementNotUniqueException, ExtensionNotFoundException {
        ModelElement parentElement = session.findElementById(ModelElement.class, this.parentId);
        
        if (parentElement != null && !this.noteContent.isEmpty()) {
            if (this.noteType.equals (JavaDesignerNoteTypes.CLASS_JAVADOC) ||
                    this.noteType.equals (JavaDesignerNoteTypes.ATTRIBUTE_JAVAINITVALUECOMMENT) ||
                    this.noteType.equals (IOtherProfileElements.MODELELEMENT_DESCRIPTION)) {
                // Remove all comment marks and indent
                StringBuilder finalNoteContent = new StringBuilder();
                NoteReverseUtils.getInstance().cleanUpComments (finalNoteContent, this.noteContent);
        
                String tempContent = finalNoteContent.toString();
        
                if (parentElement instanceof GeneralClass) {
                    tempContent = NoteReverseUtils.getInstance ().reverseJavadoc (session, tempContent, (GeneralClass) parentElement, new Repository());
                } else if (parentElement instanceof Operation) {
                    String moduleName = this.noteType.equals (IOtherProfileElements.MODELELEMENT_DESCRIPTION) ?IOtherProfileElements.MODULE_NAME:IJavaDesignerPeerModule.MODULE_NAME;
                    tempContent = NoteReverseUtils.getInstance ().reverseJavadoc (session, tempContent, (Operation) parentElement, moduleName, this.noteType, new Repository());
                }
        
                if (!tempContent.isEmpty ()) {
                    String module = this.noteType.equals (IOtherProfileElements.MODELELEMENT_DESCRIPTION) ? IOtherProfileElements.MODULE_NAME : IJavaDesignerPeerModule.MODULE_NAME;
                    parentElement.putNoteContent(module, this.noteType, tempContent);
                }
            } else {
                // Remove indent
                StringBuilder finalNoteContent = new StringBuilder();
                NoteReverseUtils.getInstance().cleanUpCode (finalNoteContent, this.noteContent);
        
                NoteType type = session.getMetamodelExtensions().getNoteType(".*", this.noteType, parentElement.getMClass());
                parentElement.putNoteContent(type.getModule().getName(), this.noteType, finalNoteContent.toString());
            }
        }
    }

    public NewNote(final String noteType, final String noteContent, final String parentId, final IReportWriter report) {
        super(noteType, noteContent, report);
        this.parentId = parentId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(final String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "NewNote : type=\"" + this.noteType + "\" parent=\"" + this.parentId + "\"";
    }

}
