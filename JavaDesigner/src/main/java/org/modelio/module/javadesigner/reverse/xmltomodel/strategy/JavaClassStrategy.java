package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.IReportWriter;
import com.modelio.module.xmlreverse.model.JaxbClass;
import com.modelio.module.xmlreverse.model.JaxbTaggedValue;
import com.modelio.module.xmlreverse.strategy.ClassStrategy;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.KindOfAccess;
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

public class JavaClassStrategy extends ClassStrategy {
    public ReverseStrategyConfiguration reverseConfig;

    public JavaClassStrategy(final IModelingSession session, final ReverseStrategyConfiguration reverseConfig) {
        super (session);
        this.reverseConfig = reverseConfig;
    }

    @Override
    public List<MObject> updateProperties(final JaxbClass jaxb_element, final Class modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        String oldName = modelio_element.getName ();
        boolean hasJavaName = modelio_element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME);
        
        ModelTree oldOwner = modelio_element.getOwner ();
        
        List<MObject> ret = super.updateProperties (jaxb_element, modelio_element, owner, repository);
        try {
            ModelUtils.addStereotype (modelio_element, JavaDesignerStereotypes.JAVACLASS);
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVACLASS)); //$NON-NLS-1$
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
        
        String jaxbName = jaxb_element.getName();
        if (jaxbName != null) {
            modelio_element.setName(jaxbName);
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
    private void handleMultipleTags(final JaxbClass jaxb_element, final Class modelio_element, final IReadOnlyRepository repository) {
        JaxbTaggedValue firstImportTag = null;
        JaxbTaggedValue firstImplementTag = null;
        JaxbTaggedValue firstJavaTargetAnnotation = null;
        
        List<JaxbTaggedValue> toRemove = new ArrayList<> ();
        List<Object> sub_elements = jaxb_element.getClazzOrInterfaceOrInstance ();
        for (Object sub_element : sub_elements) {
            if (sub_element instanceof JaxbTaggedValue) {
                JaxbTaggedValue currentTag = (JaxbTaggedValue) sub_element;
        
                if (currentTag.getTagType ().equals (JavaDesignerTagTypes.CLASS_JAVAIMPORT)) {
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
                } else if (currentTag.getTagType ().equals (JavaDesignerTagTypes.CLASS_JAVAIMPLEMENTS)) {
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
                } else if (currentTag.getTagType ().equals (JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION)) {
                    if (firstJavaTargetAnnotation == null) {
                        firstJavaTargetAnnotation = currentTag;
                    } else {
                        firstJavaTargetAnnotation.getTagParameter ().addAll (currentTag.getTagParameter ());
                        toRemove.add (currentTag);
                    }
                }
            }
        }
        
        sub_elements.removeAll (toRemove);
    }

    @Override
    public void postTreatment(final JaxbClass jaxb_element, final Class modelio_element, final IReadOnlyRepository repository) {
        super.postTreatment (jaxb_element, modelio_element, repository);
        
        IReportWriter report = repository.getReportWriter ();
        List<String> javaextends = modelio_element.getTagValues(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.DATATYPE_JAVAEXTENDS);
        if (javaextends != null) {
            for (String je : javaextends) {
                report.addWarning(Messages.getString("reverse.Extends_clause_warning.title", je), modelio_element, Messages.getString("reverse.Extends_clause_warning.title", je)); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        
        try {
            computeJavaDoc (modelio_element, repository);
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.SEEJAVADOC)); //$NON-NLS-1$
        }
    }

    private void computeJavaDoc(final Class modelio_element, final IReadOnlyRepository repository) throws ExtensionNotFoundException {
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

    @Override
    public void deleteSubElements(final JaxbClass jaxb_element, final Class modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        for (MObject elt : element_todelete) {
            if ((elt instanceof Attribute)) {
                Attribute newFeat = (Attribute) elt;
                // Update getter/setter links if necessary
                if (newFeat.getImpactedDependency().size() > 0) {
                    // Find homonyms to bind the links
                    boolean found = false;
                    for (Attribute oldFeat : modelio_element.getOwnedAttribute()) {
                        if (!newFeat.equals(oldFeat) && newFeat.getName().equals(oldFeat.getName())) {
                            for (Dependency dep : newFeat.getImpactedDependency()) {
                                dep.setDependsOn(oldFeat);
                            }
        
                            // Keep old access mode
                            KindOfAccess oldChangeable = oldFeat.getChangeable();
        
                            // Keep old mult
                            String oldMin = oldFeat.getMultiplicityMin();
                            String oldMax = oldFeat.getMultiplicityMax();
        
                            newFeat.setMultiplicityMin(oldMin);
                            newFeat.setChangeable(oldChangeable);
                            newFeat.setMultiplicityMax(oldMax);
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) {
                        // Remove stereotypes for orphan getter/setter
                        for (Dependency dep : newFeat.getImpactedDependency()) {
                            ModelElement source = dep.getImpacted();
                            source.removeStereotypes(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER);
                            source.removeStereotypes(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER);
                        }
                    }
                }
            } else if ((elt instanceof AssociationEnd)) {
                AssociationEnd newFeat = (AssociationEnd) elt;
                // Update getter/setter links if necessary
                if (newFeat.getImpactedDependency().size() > 0) {
                    // Find homonyms to bind the links
                    boolean found = false;
                    for (AssociationEnd oldFeat : modelio_element.getOwnedEnd()) {
                        if (!newFeat.equals(oldFeat) && newFeat.getName().equals(oldFeat.getName())) {
                            for (Dependency dep : newFeat.getImpactedDependency()) {
                                dep.setDependsOn(oldFeat);
                            }
        
                            // Keep old access mode
                            KindOfAccess oldChangeable = oldFeat.getChangeable();
        
                            // Keep old mult
                            String oldMin = oldFeat.getMultiplicityMin();
                            String oldMax = oldFeat.getMultiplicityMax();
        
                            newFeat.setMultiplicityMin(oldMin);
                            newFeat.setChangeable(oldChangeable);
                            newFeat.setMultiplicityMax(oldMax);
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) {
                        // Remove stereotypes for orphan getter/setter
                        for (Dependency dep : newFeat.getImpactedDependency()) {
                            ModelElement source = dep.getImpacted();
                            source.removeStereotypes(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER);
                            source.removeStereotypes(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER);
                        }
                    }
                }
            }
        }
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
