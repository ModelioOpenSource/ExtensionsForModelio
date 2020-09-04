package org.modelio.module.javadesigner.reverse.xmltomodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.utils.ModelElementDeleteStrategy;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaModelElementDeleteStrategy extends ModelElementDeleteStrategy {
    private Set<String> tagList;

    private Set<String> noteList;

    private Set<String> stereotypeList;

    private Map<MObject, Set<Stereotype>> stereotypeUsages = new HashMap<>();

    public JavaModelElementDeleteStrategy() {
        super ();
        initNoteList ();
        initStereotypeList ();
        initTagList ();
    }

    @Override
    public void deleteSubElements(final ModelElement modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        Collection<MObject> todelete = new HashSet<> ();
        for (Stereotype stereo : new ArrayList<>(modelio_element.getExtension())) {
            if (isJavaStereotype (stereo)) {
                Set<Stereotype> list = this.stereotypeUsages.get(modelio_element);
                if (list == null || !list.contains(stereo)) {
                    todelete.add (stereo);
                }
            }
        }
        
        if (element_todelete.isEmpty() && todelete.isEmpty()) {
            return;
        }
        
        for (MObject elt : element_todelete) {
            if (!elt.isDeleted()) {
                if (elt instanceof TaggedValue) {
                    if (isJavaTag ((TaggedValue) elt)) {
                        todelete.add (elt);
                    }
                } else if (elt instanceof Stereotype) {
                    if (isJavaStereotype ((Stereotype) elt)) {
                        Set<Stereotype> list = this.stereotypeUsages.get(modelio_element);
                        if (list == null || !list.contains(elt)) {
                            todelete.add (elt);
                        }
                    }
                } else if (elt instanceof Note) {
                    if (isJavaNote ((Note) elt)) {
                        todelete.add (elt);
                    }
                } else if (elt instanceof Constraint) {
                    if (isJavaConstraint ((Constraint) elt)) {
                        todelete.add (elt);
                    }
                } else if (elt instanceof Dependency) {
                    // Keep dependencies
                } else if (elt instanceof ModelElement) {
                    ModelElement modelelt = (ModelElement) elt;
        
                    // Ignore no code elements
                    if (!JavaDesignerUtils.isNoCode (modelelt)) {
                        todelete.add (modelelt);
                    }
                } else {
                    todelete.add (elt);
                }
            }
        }
        
        super.deleteSubElements (modelio_element, todelete, repository);
    }

    private boolean isJavaStereotype(final Stereotype stereo) {
        return this.stereotypeList.contains (stereo.getName ()) || JavaDesignerUtils.isAnnotationStereotype(stereo);
    }

    private void initStereotypeList() {
        this.stereotypeList = new HashSet<> ();
        this.stereotypeList.add ("JarFile");
        this.stereotypeList.add ("JavaAnnotation");
        //this.stereotypeList.add ("JavaClass");
        //this.stereotypeList.add ("JavaDataType");
        this.stereotypeList.add ("JavaDocInvariant");
        //this.stereotypeList.add ("JavaEnumeration");
        this.stereotypeList.add ("JavaFileGroup");
        this.stereotypeList.add ("JavaGetter");
        //this.stereotypeList.add ("JavaInterface");
        this.stereotypeList.add ("JavaInvariant");
        //this.stereotypeList.add ("JavaPackage");
        //this.stereotypeList.add ("JavaComponent");
        this.stereotypeList.add ("JavaPostCondition");
        this.stereotypeList.add ("JavaPreCondition");
        this.stereotypeList.add ("JavaResource");
        this.stereotypeList.add ("JavaSetter");
        this.stereotypeList.add ("JavaStatic");
        this.stereotypeList.add ("SeeJavadoc");
        this.stereotypeList.add ("JavaAttributeProperty");
        this.stereotypeList.add ("JavaAssociationEndProperty");
        
        // Stereotypes from modeler module
        this.stereotypeList.add ("create");
        this.stereotypeList.add ("destroy");
    }

    private boolean isJavaConstraint(final Constraint constraint) {
        for (Stereotype stereo : constraint.getExtension ()) {
            if (isJavaStereotype (stereo)) {
                return true;
            }
        }
        return false;
    }

    private boolean isJavaNote(final Note note) {
        NoteType def = note.getModel ();
        if (def != null) {
            return this.noteList.contains (def.getName ());
        }
        return false;
    }

    private boolean isJavaTag(final TaggedValue tag) {
        TagType def = tag.getDefinition ();
        if (def != null) {
            return this.tagList.contains (def.getName ()) || (JavaDesignerUtils.isAnnotationStereotype(def.getOwnerStereotype()));
        }
        return false;
    }

    private void initNoteList() {
        this.noteList = new HashSet<> ();
        this.noteList.add ("JavaInitValue");
        this.noteList.add ("JavaInitValueComment");
        this.noteList.add ("JavaComment");
        this.noteList.add ("JavaTop");
        this.noteList.add ("Javadoc");
        this.noteList.add ("JavaAnnotation");
        this.noteList.add ("JavaHeader");
        this.noteList.add ("JavaMembers");
        this.noteList.add ("JavaBottom");
        this.noteList.add ("JavaMembers");
        this.noteList.add ("SeeJavadoc");
        this.noteList.add ("JavaCode");
        this.noteList.add ("JavaSuper");
        this.noteList.add ("JavaReturned");
        this.noteList.add ("AntTarget");
        this.noteList.add ("JavaImport");
    }

    private void initTagList() {
        this.tagList = new HashSet<> ();
        this.tagList.add ("JavaArrayDimension");
        this.tagList.add ("JavaBind");
        this.tagList.add ("JavaFinal");
        this.tagList.add ("JavaFullName");
        this.tagList.add ("JavaImplementationType");
        this.tagList.add ("JavaTransient");
        this.tagList.add ("JavaTypeExpr");
        this.tagList.add ("JavaVolatile");
        this.tagList.add ("JavaArrayDimension");
        this.tagList.add ("JavaBind");
        this.tagList.add ("JavaFinal");
        this.tagList.add ("JavaFullName");
        this.tagList.add ("JavaImplementationType");
        this.tagList.add ("JavaTransient");
        this.tagList.add ("JavaTypeExpr");
        this.tagList.add ("JavaVolatile");
        this.tagList.add ("JavaWrapper");
        this.tagList.add ("JavaName");
        this.tagList.add ("JavaExtends");
        this.tagList.add ("JavaExtern");
        this.tagList.add ("JavaImplements");
        this.tagList.add ("JavaImport");
        this.tagList.add ("JavaStatic");
        this.tagList.add ("JavaName");
        this.tagList.add ("JavaExtends");
        this.tagList.add ("JavaExtern");
        this.tagList.add ("JavaImport");
        this.tagList.add ("JavaStatic");
        this.tagList.add ("JavaFullName");
        this.tagList.add ("JavaImplements");
        this.tagList.add ("JavaImport");
        this.tagList.add ("JavaStatic");
        this.tagList.add ("JavaArguments");
        this.tagList.add ("JavaName");
        this.tagList.add ("JavaNoInitValue");
        this.tagList.add ("JavaNoInvariant");
        this.tagList.add ("JavaBind");
        this.tagList.add ("JavaFullName");
        this.tagList.add ("JavaNoCode");
        this.tagList.add ("JavaName");
        this.tagList.add ("JavaExtends");
        this.tagList.add ("JavaExtern");
        this.tagList.add ("JavaImport");
        this.tagList.add ("JavaStatic");
        this.tagList.add ("JavaBind");
        this.tagList.add ("JavaFullName");
        this.tagList.add ("JavaNoCode");
        this.tagList.add ("_toGenerate");
        this.tagList.add ("JavaNative");
        this.tagList.add ("JavaStrict");
        this.tagList.add ("JavaSynchronized");
        this.tagList.add ("JavaTemplateParameters");
        this.tagList.add ("JavaThrownException");
        this.tagList.add ("JavaName");
        this.tagList.add ("JavaExtern");
        this.tagList.add ("JavaImport");
        this.tagList.add ("JavaNoPackage");
        this.tagList.add ("JavaArrayDimension");
        this.tagList.add ("JavaBind");
        this.tagList.add ("JavaFinal");
        this.tagList.add ("JavaFullName");
        this.tagList.add ("JavaTypeExpr");
        this.tagList.add ("JavaVarArgs");
        this.tagList.add ("JavaWrapper");
        this.tagList.add ("JavaModule");
        this.tagList.add ("JavaExtends");
        this.tagList.add ("AntFilePath");
        this.tagList.add ("JavaMainClass");
        this.tagList.add ("JavaDocumentedAnnotation");
        this.tagList.add ("JavaInheritedAnnotation");
        this.tagList.add ("JavaRetentionAnnotation");
        this.tagList.add ("JavaTargetAnnotation");
        this.tagList.add ("GenerationPath");
        this.tagList.add ("JavaFilterAccessor");
        this.tagList.add ("JavaGenerateAccessor");
        this.tagList.add ("JavaNoAccessor");
        this.tagList.add ("JavaPublic");
        this.tagList.add ("JavaByte");
        this.tagList.add ("JavaFilterAccessor");
        this.tagList.add ("JavaGenerateAccessor");
        this.tagList.add ("JavaLong");
        this.tagList.add ("JavaNoAccessor");
        this.tagList.add ("JavaPublic");
        this.tagList.add ("JavaShort");
        this.tagList.add ("JavaEclipseNLS");
        this.tagList.add ("JavaBean");
        this.tagList.add ("JavaBeanResource");
        this.tagList.add ("JavaNoAccessor");
        this.tagList.add ("JavaNoImport");
        this.tagList.add ("JavaNonPublic");
        this.tagList.add ("JavaFileName");
        this.tagList.add ("JavaNoImport");
        this.tagList.add ("JavaNoImport");
        this.tagList.add ("JavaBeanResource");
        this.tagList.add ("JavaDocumentedAnnotation");
        this.tagList.add ("JavaInheritedAnnotation");
        this.tagList.add ("JavaNoAccessor");
        this.tagList.add ("JavaNoImport");
        this.tagList.add ("JavaNonPublic");
        this.tagList.add ("JavaRetentionAnnotation");
        this.tagList.add ("JavaTargetAnnotation");
        this.tagList.add ("JavaImplements");
        this.tagList.add ("JavaBean");
        this.tagList.add ("JavaFileName");
        this.tagList.add ("JavaBeanResource");
        this.tagList.add ("JavaRoot");
        this.tagList.add ("JavaByte");
        this.tagList.add ("JavaLong");
        this.tagList.add ("JavaShort");
        this.tagList.add ("_toDelete");
        this.tagList.add ("JavaSetterVisibility");
        this.tagList.add ("JavaGetterVisibility");
        
        // Tag from modeler module
        this.tagList.add ("type");
    }

    public void addJavaNoteType(final String noteName) {
        this.noteList.add (noteName);
    }

    public void addJavaTagType(final String tagName) {
        this.tagList.add (tagName);
    }

    public void addJavaStereotype(final String stereotypeName) {
        this.stereotypeList.add (stereotypeName);
    }

    public void addJavaConstraint(final String constraintName) {
        this.stereotypeList.add (constraintName);
    }

    public void putJavaStereotypeUsage(final ModelElement element, final Stereotype stereotype) {
        Set<Stereotype> list = this.stereotypeUsages.get(element);
        if (list == null) {
            list = new HashSet<>();
            this.stereotypeUsages.put(element, list);
        }
        list.add(stereotype);
    }

}
