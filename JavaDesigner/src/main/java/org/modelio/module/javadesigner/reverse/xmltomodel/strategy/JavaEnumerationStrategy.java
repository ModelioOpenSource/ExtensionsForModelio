package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.ArrayList;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReportWriter;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumeration;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTaggedValue;
import org.modelio.module.javadesigner.xmlreverse.strategy.EnumerationStrategy;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.ReverseStrategyConfiguration;
import org.modelio.module.javadesigner.reverse.xmltomodel.NoteReverseUtils;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaEnumerationStrategy extends EnumerationStrategy {
    public ReverseStrategyConfiguration reverseConfig;

    public JavaEnumerationStrategy(final IModelingSession session, final ReverseStrategyConfiguration reverseConfig) {
        super (session);
        this.reverseConfig = reverseConfig;
    }

    @Override
    public List<MObject> updateProperties(final JaxbEnumeration jaxb_element, final Enumeration modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelTree oldOwner = modelio_element.getOwner ();
        
        String oldName = modelio_element.getName ();
        boolean hasJavaName = modelio_element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME);
        
        List<MObject> ret = super.updateProperties (jaxb_element, modelio_element, owner, repository);
        
        modelio_element.setIsLeaf (true);
        modelio_element.setIsElementary (true);
        
        try {
            ModelUtils.addStereotype (modelio_element, JavaDesignerStereotypes.JAVAENUMERATION);
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVAENUMERATION)); //$NON-NLS-1$
        }
        
        if (oldOwner != null) {
            ModelTree newOwner = modelio_element.getOwner ();
            if (JavaDesignerUtils.getFullJavaName (this.session, oldOwner).equals (JavaDesignerUtils.getFullJavaName (this.session, newOwner))) {
                modelio_element.setOwner (oldOwner);
            }
        }
        
        if (modelio_element.getOwner () instanceof Interface) {
            // Inner elements of an Interface should always be public
            modelio_element.setVisibility (VisibilityMode.PUBLIC);
        }
        
        handleMultipleTags (jaxb_element, modelio_element, repository);
        
        String jaxbName = jaxb_element.getName ();
        if (jaxbName != null) {
            if (jaxbName.equalsIgnoreCase ("Boolean") ||
                    jaxbName.equalsIgnoreCase ("Byte") ||
                    jaxbName.equalsIgnoreCase ("Char") ||
                    jaxbName.equalsIgnoreCase ("Date") ||
                    jaxbName.equalsIgnoreCase ("Double") ||
                    jaxbName.equalsIgnoreCase ("Float") ||
                    jaxbName.equalsIgnoreCase ("Integer") ||
                    jaxbName.equalsIgnoreCase ("Long") ||
                    jaxbName.equalsIgnoreCase ("Short") ||
                    jaxbName.equalsIgnoreCase ("String")) {
                hasJavaName = true;
                oldName = "_" + jaxbName;
            } else {
                modelio_element.setName (jaxbName);
            }
        }
        
        if (hasJavaName) {
            try {
                modelio_element.setName (oldName);
        
                ModelUtils.setFirstTagParameter (this.session, modelio_element, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME, jaxbName);
                if (ret == null) {
                    ret = new ArrayList<> ();
                }
                ret.add (modelio_element.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME));
            } catch (ExtensionNotFoundException | ElementNotUniqueException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
        return ret;
    }

    /**
     * TODO this should be done in the ANTLR -> XML part...
     */
    private void handleMultipleTags(final JaxbEnumeration jaxb_element, final Enumeration modelio_element, final IReadOnlyRepository repository) {
        JaxbTaggedValue firstImportTag = null;
        JaxbTaggedValue firstImplementTag = null;
        
        List<JaxbTaggedValue> toRemove = new ArrayList<> ();
        List<Object> sub_elements = jaxb_element.getNoteOrConstraintOrStereotype ();
        for (Object sub_element : sub_elements) {
            if (sub_element instanceof JaxbTaggedValue) {
                JaxbTaggedValue currentTag = (JaxbTaggedValue) sub_element;
        
                if (currentTag.getTagType ().equals (JavaDesignerTagTypes.ENUMERATION_JAVAIMPORT)) {
                    if (firstImportTag == null) {
                        firstImportTag = currentTag;
                    } else {
                        firstImportTag.getTagParameter ().addAll (currentTag.getTagParameter ());
                        toRemove.add (currentTag);
                    }
                    IReportWriter report = repository.getReportWriter ();
                    for (String javaimport : currentTag.getTagParameter ()) {
                        report.addWarning (Messages.getString("reverse.Import_clause_warning.title", javaimport), modelio_element, Messages.getString("reverse.Import_clause_warning.description", javaimport)); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                } else if (currentTag.getTagType ().equals (JavaDesignerTagTypes.ENUMERATION_JAVAIMPLEMENTS)) {
                    if (firstImplementTag == null) {
                        firstImplementTag = currentTag;
                    } else {
                        firstImplementTag.getTagParameter ().addAll (currentTag.getTagParameter ());
                        toRemove.add (currentTag);
                    }
                    IReportWriter report = repository.getReportWriter ();
                    for (String javaimport : currentTag.getTagParameter ()) {
                        report.addWarning (Messages.getString("reverse.Implement_clause_warning.title", javaimport), modelio_element, Messages.getString("reverse.Implement_clause_warning.title", javaimport)); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                }
            }
        }
        
        sub_elements.removeAll (toRemove);
    }

    @Override
    public void postTreatment(final JaxbEnumeration jaxb_element, final Enumeration modelio_element, final IReadOnlyRepository repository) {
        super.postTreatment (jaxb_element, modelio_element, repository);
        
        try {
            computeJavaDoc (modelio_element, repository);
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.SEEJAVADOC)); //$NON-NLS-1$
        }
    }

    private void computeJavaDoc(final Enumeration modelio_element, final IReadOnlyRepository repository) throws ExtensionNotFoundException {
        String noteType;
        if (!this.reverseConfig.DESCRIPTIONASJAVADOC) {
            noteType = JavaDesignerNoteTypes.CLASS_JAVADOC;
        } else {
            noteType = IOtherProfileElements.MODELELEMENT_DESCRIPTION;
        }
        
        for (Note note : ModelUtils.getAllNotes (modelio_element, noteType)) {
            String tempContent = note.getContent ();
            tempContent = NoteReverseUtils.getInstance ().reverseJavadoc (this.session, tempContent, modelio_element, repository);
            if (tempContent.isEmpty ()) {
                note.delete();
            } else {
                note.setContent (tempContent.trim ());
            }
        }
    }

}
