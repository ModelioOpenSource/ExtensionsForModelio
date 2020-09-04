package org.modelio.module.javadesigner.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelio.api.modelio.mc.AbstractModelComponentContributor;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.gproject.ramc.core.model.IModelComponent;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaRamcContributor extends AbstractModelComponentContributor {
    private IModelingSession session;

    private IModelComponent mc;

    private MMetamodel metamodel;

    public JavaRamcContributor(final IModule module, final IModelComponent mc) {
        super (module);
        this.session =  module.getModuleContext().getModelingSession ();
        this.mc = mc;
        this.metamodel = module.getModuleContext().getModelioServices().getMetamodelService().getMetamodel();
    }

    /**
     * Checks the value of the PACKAGESRCINRAMC module parameter.
     */
    private boolean mustIncludeSources() {
        return getModule ().getModuleContext().getConfiguration ().getParameterValue (JavaDesignerParameters.PACKAGESRCINRAMC).equalsIgnoreCase ("TRUE");
    }

    /**
     * Checks the value of the PACKAGEJARINRAMC module parameter.
     */
    private boolean mustIncludeJars() {
        return getModule ().getModuleContext().getConfiguration ().getParameterValue (JavaDesignerParameters.PACKAGEJARINRAMC).equalsIgnoreCase ("TRUE");
    }

    private void computeJarFiles(final Set<ExportedFileEntry> files) {
        String genroot = JavaDesignerUtils.getGenRoot (getModule ()).getAbsolutePath ();
        for (Artifact jarFile : getJarArtifacts (this.mc.getArtifact())) {
            Path jarFilePath = Paths.get(JavaDesignerUtils.getFilename(jarFile, getModule()).getAbsolutePath());
            String exportPath;
            if (jarFilePath.toString().startsWith(genroot)) {
                // The selected file belongs to the project, deploy
                // using the same path relative to the project
                exportPath = jarFilePath.toString().replace(genroot, "");
            } else {
                // The selected file does not belong to the project,
                // deploy it directly under the project
                exportPath = jarFilePath.toString().replace(jarFilePath.getParent().toString(), "");
            }
        
            JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Adding file : " + exportPath);
            files.add (new ExportedFileEntry(jarFilePath, exportPath));
        }
    }

    /**
     * Returns all Jar artifacts manifested by this artifact. Returns also Jar artifacts owned by the manifested
     * namespaces.
     */
    private List<Artifact> getJarArtifacts(final Artifact artifact) {
        List<Artifact> ret = new ArrayList<> ();
        
        for (Manifestation manif : artifact.getUtilized ()) {
            ModelElement utilizedElement = manif.getUtilizedElement ();
            if (utilizedElement instanceof NameSpace) {
                ret.addAll (getJarArtifacts ((NameSpace) utilizedElement));
            }
        }
        return ret;
    }

    /**
     * Returns all Jar artifacts owned by this namespace. If the namespace is an artifact, all its manifestations are
     * also checked.
     */
    private List<Artifact> getJarArtifacts(final NameSpace namespace) {
        List<Artifact> ret = new ArrayList<> ();
        
        if (namespace instanceof Artifact) {
            if (namespace.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE)) {
                ret.add ((Artifact) namespace);
                return ret;
            } else {
                return getJarArtifacts ((Artifact) namespace);
            }
        }
        
        for (ModelTree ownedElement : namespace.getOwnedElement ()) {
            if (ownedElement instanceof Artifact) {
                if (ownedElement.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE)) {
                    ret.add ((Artifact) ownedElement);
                }
            } else if (ownedElement instanceof NameSpace) {
                ret.addAll (getJarArtifacts ((NameSpace) ownedElement));
            }
        }
        return ret;
    }

    /**
     * Computes all java files available below the selected element.
     */
    private void computeSourceFiles(final Set<ExportedFileEntry> files) {
        for (Element manifestedElement : this.mc.getExportedElements()) {
            if (isJavaClass (manifestedElement)) {
                getFilesName ((GeneralClass) manifestedElement, files);
            } else if (manifestedElement instanceof Package) {
                getFilesName ((Package) manifestedElement, files);
            }
        }
    }

    private void getFilesName(final NameSpace utilizedElt, final Set<ExportedFileEntry> files) {
        Set<GeneralClass> classes = new HashSet<> ();
        
        // Search for all elements referenced by component
        if (isJavaClass (utilizedElt)) {
            addRecursiveUsedClasses ((GeneralClass) utilizedElt, classes);
        } else if (utilizedElt instanceof Package) {
            Package thePackage = (Package) utilizedElt;
            for (ModelTree ownedElement : thePackage.getOwnedElement ()) {
                if (isJavaClass (ownedElement)) {
                    addRecursiveUsedClasses ((GeneralClass) ownedElement, classes);
                } else if (ownedElement instanceof Package) {
                    getFilesName ((Package) ownedElement, files);
                }
            }
        }
        
        String genroot = JavaDesignerUtils.getGenRoot (getModule ()).getAbsolutePath ().replace ("\\", "/");
        
        // Compute all files
        for (GeneralClass theClass : classes) {
            ModelTree theOwner = theClass.getOwner ();
            if (!ModelUtils.isLibrary(theClass) && theOwner instanceof Package) {
                Path javaFilePath = Paths.get(JavaDesignerUtils.getFilename (theClass, getModule ()).getAbsolutePath ());
                String exportPath;
                if (javaFilePath.toString().startsWith(genroot)) {
                    // The selected file belongs to the project, deploy
                    // using the same path relative to the project
                    exportPath = javaFilePath.toString().replace(genroot, "");
                } else {
                    // The selected file does not belong to the project,
                    // deploy it directly under the project
                    exportPath = javaFilePath.toString().replace(javaFilePath.getParent().toString(), "");
                }
        
                JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Adding file : " + exportPath);
                files.add (new ExportedFileEntry(javaFilePath, exportPath));
            }
        }
    }

    private void addRecursiveUsedClasses(final GeneralClass utilizedElt, final Set<GeneralClass> classes) {
        // Don't process nocode classes
        if (!JavaDesignerUtils.isNoCode (utilizedElt)) {
            // Add the current class
            classes.add (utilizedElt);
        
            for (GeneralClass dependentClass : getDependentClasses (utilizedElt)) {
                // Avoid infinite loop
                if (!classes.contains (dependentClass)) {
                    addRecursiveUsedClasses (dependentClass, classes);
                }
            }
        }
    }

    private Set<GeneralClass> getDependentClasses(final GeneralClass utilizedElt) {
        Set<GeneralClass> ret = new HashSet<> ();
        
        // Used classes and interfaces
        for (ElementImport theImport : utilizedElt.getOwnedImport ()) {
            NameSpace importedElement = theImport.getImportedElement ();
            if (isJavaClass (importedElement)) {
                ret.add ((GeneralClass) importedElement);
            }
        }
        
        // Package imports
        for (PackageImport theImport : utilizedElt.getOwnedPackageImport ()) {
            Package importedPackage = theImport.getImportedPackage ();
            for (ModelTree ownedElement : importedPackage.getOwnedElement ()) {
                if (isJavaClass (ownedElement)) {
                    ret.add ((GeneralClass) ownedElement);
                }
            }
        }
        
        // Parent classes
        for (Generalization theGeneralization : utilizedElt.getParent ()) {
            NameSpace superType = theGeneralization.getSuperType ();
            if (isJavaClass (superType)) {
                ret.add ((GeneralClass) superType);
            }
        }
        
        // Implemented interfaces
        for (InterfaceRealization theRealization : utilizedElt.getRealized ()) {
            ret.add (theRealization.getImplemented ());
        }
        
        // Navigeable Associations
        for (AssociationEnd theEnd : utilizedElt.getOwnedEnd()) {
            if (theEnd.getTarget() != null) {
                AssociationEnd theOtherEnd = theEnd.getOpposite ();
                Classifier theOtherEndOwner = theOtherEnd.getSource ();
                if (isJavaClass (theOtherEndOwner)) {
                    ret.add ((GeneralClass) theOtherEndOwner);
                }
            }
        }
        
        // Attribute types
        for (Attribute theAttribute : utilizedElt.getOwnedAttribute()) {
            GeneralClass theType = theAttribute.getType ();
            if (isJavaClass (theType)) {
                ret.add (theType);
            }
        }
        
        //  Operation parameters
        for (Operation theOperation : utilizedElt.getOwnedOperation()) {
            // Operation IN Parameters
            for (Parameter theIOParameter : theOperation.getIO ()) {
                GeneralClass theType = theIOParameter.getType ();
                if (isJavaClass (theType)) {
                    ret.add (theType);
                }
            }
        
            // Operation OUT Parameters
            Parameter theReturnType = theOperation.getReturn ();
            if (theReturnType != null) {
                GeneralClass theType = theReturnType.getType ();
                if (isJavaClass (theType)) {
                    ret.add (theType);
                }
            }
        
            // Class used by operations
            for (ElementImport theImport : theOperation.getOwnedImport ()) {
                NameSpace importedElement = theImport.getImportedElement ();
                if (isJavaClass (importedElement)) {
                    ret.add ((GeneralClass) importedElement);
                }
            }
        
            // Class thrown by operations
            for (RaisedException theException : theOperation.getThrown ()) {
                Classifier theThrownType = theException.getThrownType ();
                if (isJavaClass (theThrownType)) {
                    ret.add ((GeneralClass) theThrownType);
                }
            }
        }
        return ret;
    }

    private boolean isJavaClass(final Element utilizedElt) {
        if (JavaDesignerUtils.isJavaElement (utilizedElt)) {
            if (utilizedElt instanceof Interface) {
                return true;
            } else if (utilizedElt instanceof Enumeration) {
                return true;
            } else if (utilizedElt instanceof DataType) {
                return true;
            } else if (utilizedElt instanceof Class) {
                if (!(utilizedElt instanceof Component)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get a TagType from the metamodel extensions.
     * @throws ExtensionNotFoundException
     * @throws ElementNotUniqueException
     */
    private TagType getTagType(final java.lang.Class<? extends Element> metaclass, final String tagTypeName) throws ElementNotUniqueException, ExtensionNotFoundException {
        return this.session.getMetamodelExtensions().getTagType(IJavaDesignerPeerModule.MODULE_NAME, tagTypeName, this.metamodel.getMClass(metaclass));
    }

    /**
     * Get a NoteType from the metamodel extensions.
     * @throws ExtensionNotFoundException
     * @throws ElementNotUniqueException
     */
    private NoteType getNoteType(final java.lang.Class<? extends Element> metaclass, final String noteTypeName) throws ElementNotUniqueException, ExtensionNotFoundException {
        return this.session.getMetamodelExtensions().getNoteType(IJavaDesignerPeerModule.MODULE_NAME, noteTypeName, this.metamodel.getMClass(metaclass));
    }

    @Override
    public Set<Stereotype> getDependencyStereotypes() {
        // No stereotypes to contribute...
        return Collections.emptySet();
    }

    @Override
    public Set<MObject> getElements() {
        // No elements to contribute...
        return Collections.emptySet();
    }

    @Override
    public Set<ExportedFileEntry> getFiles() {
        Set<ExportedFileEntry> files = new HashSet<>();
        // Add java files
        if (mustIncludeSources ()) {
            computeSourceFiles (files);
        }
        
        // Add jars
        if (mustIncludeJars ()) {
            computeJarFiles (files);
        }
        return files;
    }

    @Override
    public Set<NoteType> getNoteTypes() {
        // Add java note types
        Set<NoteType> noteTypes = new HashSet<>();
        try {
            noteTypes.add (getNoteType (AssociationEnd.class, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE));
            noteTypes.add (getNoteType (AssociationEnd.class, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUECOMMENT));
            noteTypes.add (getNoteType (Attribute.class, JavaDesignerNoteTypes.ATTRIBUTE_JAVAINITVALUECOMMENT));
            noteTypes.add (getNoteType (Class.class, JavaDesignerNoteTypes.CLASS_JAVADOC));
            noteTypes.add (getNoteType (DataType.class, JavaDesignerNoteTypes.DATATYPE_JAVADOC));
            noteTypes.add (getNoteType (ElementImport.class, JavaDesignerNoteTypes.ELEMENTIMPORT_JAVADOC));
            noteTypes.add (getNoteType (EnumerationLiteral.class, JavaDesignerNoteTypes.ENUMERATIONLITERAL_JAVADOC));
            noteTypes.add (getNoteType (Enumeration.class, JavaDesignerNoteTypes.ENUMERATION_JAVADOC));
            noteTypes.add (getNoteType (Feature.class, JavaDesignerNoteTypes.FEATURE_JAVADOC));
            noteTypes.add (getNoteType (Interface.class, JavaDesignerNoteTypes.INTERFACE_JAVADOC));
            noteTypes.add (getNoteType (Parameter.class, JavaDesignerNoteTypes.PARAMETER_JAVADOC));
            noteTypes.add (getNoteType (Class.class, JavaDesignerNoteTypes.CLASS_JAVAMEMBERS));
            noteTypes.add (getNoteType (DataType.class, JavaDesignerNoteTypes.DATATYPE_JAVAMEMBERS));
            noteTypes.add (getNoteType (Interface.class, JavaDesignerNoteTypes.INTERFACE_JAVAMEMBERS));
            noteTypes.add (getNoteType (Class.class, JavaDesignerNoteTypes.CLASS_JAVAANNOTATION));
            noteTypes.add (getNoteType (Feature.class, JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION));
            noteTypes.add (getNoteType (Interface.class, JavaDesignerNoteTypes.INTERFACE_JAVAANNOTATION));
            noteTypes.add (getNoteType (Package.class, JavaDesignerNoteTypes.PACKAGE_JAVAANNOTATION));
            noteTypes.add (getNoteType (Parameter.class, JavaDesignerNoteTypes.PARAMETER_JAVAANNOTATION));
            noteTypes.add (getNoteType (Dependency.class, JavaDesignerNoteTypes.DEPENDENCY_SEEJAVADOC));
            // noteTypes.add(getNoteType("specific-xml");
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
        } catch (ElementNotUniqueException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
        }
        return noteTypes;
    }

    @Override
    public Set<TagType> getTagTypes() {
        // Add java tag types
           Set<TagType> tagTypes = new HashSet<>();
           try {
               tagTypes.add (getTagType (AssociationEnd.class, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFULLNAME));
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVAFULLNAME));
               tagTypes.add (getTagType (ElementImport.class, JavaDesignerTagTypes.ELEMENTIMPORT_JAVAFULLNAME));
               tagTypes.add (getTagType (Generalization.class, JavaDesignerTagTypes.GENERALIZATION_JAVAFULLNAME));
               tagTypes.add (getTagType (InterfaceRealization.class, JavaDesignerTagTypes.INTERFACEREALIZATION_JAVAFULLNAME));
               tagTypes.add (getTagType (Parameter.class, JavaDesignerTagTypes.PARAMETER_JAVAFULLNAME));
        
               tagTypes.add (getTagType (AssociationEnd.class, JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND));
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVABIND));
               tagTypes.add (getTagType (Generalization.class, JavaDesignerTagTypes.GENERALIZATION_JAVABIND));
               tagTypes.add (getTagType (InterfaceRealization.class, JavaDesignerTagTypes.INTERFACEREALIZATION_JAVABIND));
               tagTypes.add (getTagType (Parameter.class, JavaDesignerTagTypes.PARAMETER_JAVABIND));
        
               tagTypes.add (getTagType (AssociationEnd.class, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAARRAYDIMENSION));
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVAARRAYDIMENSION));
               tagTypes.add (getTagType (Parameter.class, JavaDesignerTagTypes.PARAMETER_JAVAARRAYDIMENSION));
        
               tagTypes.add (getTagType (AssociationEnd.class, JavaDesignerTagTypes.ASSOCIATIONEND_JAVATRANSIENT));
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVATRANSIENT));
        
               tagTypes.add (getTagType (AssociationEnd.class, JavaDesignerTagTypes.ASSOCIATIONEND_JAVATYPEEXPR));
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR));
               tagTypes.add (getTagType (Parameter.class, JavaDesignerTagTypes.PARAMETER_JAVATYPEEXPR));
        
               tagTypes.add (getTagType (AssociationEnd.class, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAIMPLEMENTATIONTYPE));
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVAIMPLEMENTATIONTYPE));
        
               tagTypes.add (getTagType (AssociationEnd.class, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL));
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL));
               tagTypes.add (getTagType (Parameter.class, JavaDesignerTagTypes.PARAMETER_JAVAFINAL));
        
               tagTypes.add (getTagType (AssociationEnd.class, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAVOLATILE));
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE));
        
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVAECLIPSENLS));
        
               tagTypes.add (getTagType (Attribute.class, JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER));
               tagTypes.add (getTagType (Parameter.class, JavaDesignerTagTypes.PARAMETER_JAVAWRAPPER));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.CLASS_JAVAIMPORT));
               tagTypes.add (getTagType (DataType.class, JavaDesignerTagTypes.DATATYPE_JAVAIMPORT));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVAIMPORT));
               tagTypes.add (getTagType (Package.class, JavaDesignerTagTypes.PACKAGE_JAVAIMPORT));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.CLASS_JAVAIMPLEMENTS));
               tagTypes.add (getTagType (Enumeration.class, JavaDesignerTagTypes.ENUMERATION_JAVAIMPLEMENTS));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVAIMPLEMENTS));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.JAVAANNOTATION_JAVAINHERITEDANNOTATION));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVAINHERITEDANNOTATION));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.CLASS_JAVASTATIC));
               tagTypes.add (getTagType (DataType.class, JavaDesignerTagTypes.DATATYPE_JAVASTATIC));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVASTATIC));
        
               // tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.CLASS_JAVAEXTERN));
               // tagTypes.add (getTagType (DataType.class, JavaDesignerTagTypes.DATATYPE_JAVAEXTERN));
               // tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVAEXTERN));
               // tagTypes.add (getTagType (Package.class, JavaDesignerTagTypes.PACKAGE_JAVAEXTERN));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.CLASS_JAVAEXTENDS));
               tagTypes.add (getTagType (DataType.class, JavaDesignerTagTypes.DATATYPE_JAVAEXTENDS));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVAEXTENDS));
               tagTypes.add (getTagType (TemplateParameter.class, JavaDesignerTagTypes.TEMPLATEPARAMETER_JAVAEXTENDS));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVATARGETANNOTATION));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.JAVAANNOTATION_JAVARETENTIONANNOTATION));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVARETENTIONANNOTATION));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.JAVAANNOTATION_JAVADOCUMENTEDANNOTATION));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVADOCUMENTEDANNOTATION));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.CLASS_JAVAFILENAME));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVAFILENAME));
        
               tagTypes.add (getTagType (Operation.class, JavaDesignerTagTypes.OPERATION_JAVASYNCHRONIZED));
        
               tagTypes.add (getTagType (Operation.class, JavaDesignerTagTypes.OPERATION_JAVASTRICT));
        
               tagTypes.add (getTagType (Operation.class, JavaDesignerTagTypes.OPERATION_JAVANATIVE));
        
               tagTypes.add (getTagType (Operation.class, JavaDesignerTagTypes.OPERATION_JAVATHROWNEXCEPTION));
        
               tagTypes.add (getTagType (Operation.class, JavaDesignerTagTypes.OPERATION_JAVATEMPLATEPARAMETERS));
        
               tagTypes.add (getTagType (Parameter.class, JavaDesignerTagTypes.PARAMETER_JAVAVARARGS));
        
               tagTypes.add (getTagType (Feature.class, JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE));
        
               tagTypes.add (getTagType (Artifact.class, JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS));
        
               tagTypes.add (getTagType (Package.class, JavaDesignerTagTypes.PACKAGE_JAVANOPACKAGE));
        
               tagTypes.add (getTagType (Class.class, JavaDesignerTagTypes.CLASS_JAVANAME));
               tagTypes.add (getTagType (DataType.class, JavaDesignerTagTypes.DATATYPE_JAVANAME));
               tagTypes.add (getTagType (Feature.class, JavaDesignerTagTypes.FEATURE_JAVANAME));
               tagTypes.add (getTagType (Interface.class, JavaDesignerTagTypes.INTERFACE_JAVANAME));
               tagTypes.add (getTagType (Package.class, JavaDesignerTagTypes.PACKAGE_JAVANAME));
        
               // Tag type from core.module
               tagTypes.add (getTagType (AssociationEnd.class, IOtherProfileElements.FEATURE_TYPE));
               tagTypes.add (getTagType (Attribute.class, IOtherProfileElements.FEATURE_TYPE));
               tagTypes.add (getTagType (Parameter.class, IOtherProfileElements.FEATURE_TYPE));
           } catch (ExtensionNotFoundException | ElementNotUniqueException e) {
               JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
           }
        return tagTypes;
    }

}
