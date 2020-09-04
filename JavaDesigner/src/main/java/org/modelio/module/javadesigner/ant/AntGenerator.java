package org.modelio.module.javadesigner.ant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.dialog.JConsoleWithDialog;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;

public class AntGenerator {
    private String NL = System.getProperty ("line.separator"); // $NON-NLS-1$

    private IModule module;

    private JConsoleWithDialog console;

    public AntGenerator(final IModule module) {
        this(module, new JConsoleWithDialog(null));
    }

    public AntGenerator(final IModule module, final JConsoleWithDialog console) {
        this.module = module;
        this.console = console;
    }

    public boolean generateBuildXmlFile(final Artifact jarFile) {
        String buildXmlContent;
        
        buildXmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        buildXmlContent += this.NL;
        buildXmlContent += createProjectTarget (jarFile);
        return saveAntFile (buildXmlContent, JavaDesignerUtils.getAntFileName(jarFile, this.module));
    }

    private boolean saveAntFile(final String fileContent, final File targetFile) {
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        
        try (FileWriter fw = new FileWriter(targetFile)) {
            fw.write(fileContent);
            this.console.writeInfo("The \"" + targetFile + "\" file was created.\n");
            
            fw.close();
            
            return true;
        } catch (IOException e) {
            // TODO i18n error message
            this.console.writeError("An error occured during ant file's saving:\n" + e.getMessage());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        return false;
    }

    private String createProjectTarget(final Artifact jarFile) {
        String ret = "<project basedir=\".\" name=\"" + jarFile.getName () +
        "\" default=\"build\" >" + this.NL;
        try {
            ret += createConfigs (jarFile);
        } catch (Exception e) {
            // TODO i18n error message
            this.console.writeError("An error occured during configuration's creation:\n" + e.getMessage());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        
        try {
            ret += createInitTarget (jarFile);
        } catch (Exception e) {
            // TODO i18n error message
            this.console.writeError("An error occured during init target's creation:\n" + e.getMessage());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        
        try {
            ret += createCleanTarget (jarFile);
        } catch (Exception e) {
            // TODO i18n error message
            this.console.writeError("An error occured during clean target's creation:\n" + e.getMessage());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        
        try {
            ret += createResourcesTarget (jarFile);
        } catch (Exception e) {
            // TODO i18n error message
            this.console.writeError("An error occured during resource target's creation:\n" + e.getMessage());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        
        try {
            ret += createBuildTarget (jarFile);
        } catch (Exception e) {
            // TODO i18n error message
            this.console.writeError("An error occured during build target's creation:\n" + e.getMessage());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        
        try {
            ret += createJarTarget (jarFile);
        } catch (Exception e) {
            // TODO i18n error message
            this.console.writeError("An error occured during archive target's creation:\n" + e.getMessage());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        
        try {
            ret += createRMITarget (jarFile);
        } catch (Exception e) {
            // TODO i18n error message
            this.console.writeError("An error occured during RMI target's creation:\n" + e.getMessage());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        
        if (isUseJavah ()) {
            try {
                // Do not generate javah target when no native classes are found
                List<GeneralClass> natives = getNativeClasses (jarFile);
                if (natives.size() > 0) {
                    ret += createJavahTarget (jarFile, natives) + this.NL;
                }
            } catch (Exception e) {
                // TODO i18n error message
                this.console.writeError("An error occured during javah target's creation:\n" + e.getMessage());
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
        
        try {
            ret += createPersonnalTarget (jarFile);
        } catch (Exception e) {
            // TODO i18n error message
            this.console.writeError("An error occured during custom target's creation:\n" + e.getMessage());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        
        ret += "</project>";
        ret += this.NL;
        return ret;
    }

    private String createPersonnalTarget(final Artifact jarFile) {
        String perso = jarFile.getNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.JARFILE_ANTTARGET);
        
        String ret = "";
        if (perso != null && !perso.isEmpty()) {
            ret = "<!-- " + beginZoneId () + "-->" + this.NL;
            ret += perso;
            ret += this.NL;
            ret += "<!-- " + endZoneId () + "-->" + this.NL;
        }
        return ret;
    }

    private String endZoneId() {
        return "end of custom ant target";
    }

    private String beginZoneId() {
        return "begin of custom ant target";
    }

    private String createJavahTarget(final Artifact jarFile, final List<GeneralClass> natives) {
        String ret = "    <target name=\"javah\" depends=\"build\">" + this.NL;
        if (natives.size () > 0) {
            ret += "        <mkdir dir=\"${JNIout}\"/>" + this.NL;
        }
        ret += "        <javah destdir=\"${JNIout}\" classpath=\"${classpath}\">" + this.NL +
        "            <classpath>" + this.NL +
        "                <pathelement location=\"" + JavaDesignerUtils.getCompilationPath(jarFile, this.module) + "\"/>" + this.NL +
        "                <path refid=\"project.classpath\"/>" + this.NL +
        "            </classpath>" + this.NL;
        
        for (GeneralClass nat : natives) {
            ret += "            <class name=\"" + JavaDesignerUtils.getFullJavaName (this.module.getModuleContext().getModelingSession(), nat) + "\"/>" + this.NL;
        }
        ret += "        </javah>" + this.NL +
        "    </target>" + this.NL;
        return ret;
    }

    private List<GeneralClass> getNativeClasses(final Artifact jarFile) {
        List<GeneralClass> ret = new ArrayList<> ();
        
        for (Manifestation manif : jarFile.getUtilized()) {
            ModelElement utilized = manif.getUtilizedElement();
            if (utilized instanceof NameSpace) {
                ret.addAll(getNativeClasses((NameSpace) utilized));
            }
        }
        return ret;
    }

    private List<GeneralClass> getNativeClasses(final NameSpace ns) {
        List<GeneralClass> ret = new ArrayList<> ();
        
        for (ModelTree child : ns.getOwnedElement()) {
            if (child instanceof GeneralClass) {
                if (isNative((GeneralClass) child)) {
                    ret.add((GeneralClass) child);
                }
            } else if (child instanceof Package) {
                ret.addAll(getNativeClasses((Package) child));
            }
        }
        return ret;
    }

    private boolean isNative(final GeneralClass cls) {
        for (Operation op : cls.getOwnedOperation()) {
            if (op.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.OPERATION_JAVANATIVE)) {
                return true;
            }
        }
        return false;
    }

    private String createRMITarget(final Artifact jarFile) {
        String ret = "    <target name=\"rmi\" depends=\"build\">"+ this.NL +
        "        <rmic base=\"${compilation.path}\">"+ this.NL;
        
        for (GeneralClass elt : getUnicastRemoteObjectClasses (jarFile)) {
            String className = JavaDesignerUtils.getFullJavaName (this.module.getModuleContext().getModelingSession(), elt);
            className = className.replace (".", "/");
            
            ret += "          <include name=\"" + className + ".class\"/>"+ this.NL;
        }
        
        ret += "        </rmic>"+ this.NL +
        "    </target>"+ this.NL;
        return ret;
    }

    private List<GeneralClass> getUnicastRemoteObjectClasses(final Artifact jarFile) {
        List<GeneralClass> ret = new ArrayList<> ();
        
        for (Manifestation manif : jarFile.getUtilized()) {
            ModelElement utilized = manif.getUtilizedElement();
            if (utilized instanceof NameSpace) {
                ret.addAll(getAllUnicastRemoteObject ((NameSpace) utilized));
            }
        }
        return ret;
    }

    private List<GeneralClass> getAllUnicastRemoteObject(final ModelTree utilized) {
        List<GeneralClass> ret = new ArrayList<> ();
        
        for (ModelTree ownedElement : utilized.getOwnedElement()) {
            if (ownedElement instanceof GeneralClass) {
                if (isUnicastRemoteObject ((GeneralClass) ownedElement)) {
                    ret.add((GeneralClass) ownedElement);
                }
            } else {
                ret.addAll(getAllUnicastRemoteObject (ownedElement));
            }
        }
        return ret;
    }

    private boolean isUnicastRemoteObject(final GeneralClass ownedElement) {
        String targetName = "UnicastRemoteObject";
        
        
        if (JavaDesignerUtils.getJavaName(ownedElement).equals(targetName)) {
            return true;
        } else {
            for (Generalization gen : ownedElement.getParent()) {
                NameSpace superType = gen.getSuperType();
                if (superType instanceof GeneralClass) {
                    if (isUnicastRemoteObject((GeneralClass) superType)) {
                        return true;
                    }
                }
            }
            
            List<String> tagValues = ownedElement.getTagValues(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVAEXTENDS);
            return tagValues != null && tagValues.contains(targetName);
        }
    }

    private String createBuildTarget(final Artifact jarFile) {
        String ret = "    <target name=\"build\" depends=\"init, resources \">"+ this.NL +
        "        <echo message=\"Building *.java\"/>"+ this.NL +
        "        <javac includeAntRuntime=\"false\" srcdir=\"${generation.path}\" destdir=\"${compilation.path}\">"+ this.NL;
        
        // Mantis 5223, for the compilation options
        String options = this.module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.COMPILATIONOPTIONS);
        if (!options.isEmpty()) {
            ret += "            <compilerarg value=\"" + options + "\"/>"+ this.NL;
        }
        
        ret += "            <classpath refid=\"project.classpath\"/>" + this.NL;
        
        for (Manifestation manif : jarFile.getUtilized()) {
            ModelElement elt = manif.getUtilizedElement();
            
            if (elt instanceof Package) {
                String fileName = JavaDesignerUtils.getFullJavaName(this.module.getModuleContext().getModelingSession(), elt);
                fileName = fileName.replace(".", "/");
                if( !fileName.isEmpty() ) {
                    fileName += "/";
                }
                
                ret += "            <include name=\"" + fileName + "**/*.java\"/>" + this.NL;
            }
            
            if (elt instanceof GeneralClass && ! (elt instanceof Artifact)) {
                String fileName = JavaDesignerUtils.getFullJavaName(this.module.getModuleContext().getModelingSession(), elt);
                fileName = fileName.replace(".", "/");
                
                ret += "            <include name=\"" + fileName + ".java\"/>" + this.NL;
            }
        }
        
        ret += "        </javac>" + this.NL +
        "    </target>"+ this.NL;
        return ret;
    }

    private String createJarTarget(final Artifact jarFile) throws IOException {
        File jarFilename = JavaDesignerUtils.getFilename(jarFile, this.module);
        
        String ret =  "    <target name=\"archive\" depends=\"build\">"+ this.NL +
        "        <echo message=\"Archiving jar file\"/>"+ this.NL +
        "        <mkdir dir=\"" + jarFilename.getParent() + "\"/>"+ this.NL +
        "        <jar destfile=\"" + jarFilename + "\" index=\"true\" >"+ this.NL +
        "            <manifest>"+ this.NL +
        "                <attribute name=\"Built-By\" value=\"Modelio\"/>"+ this.NL;
        
        String mainClass = ModelUtils.getFirstTagParameter(jarFile, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS);
        if (mainClass != null && !mainClass.isEmpty()) {
            ret += "                <attribute name=\"Main-Class\" value=\"" + mainClass + "\"/>"+ this.NL;
        }
        
        ret += "            </manifest>"+ this.NL + createFileSetElement (jarFile);
        
        /* a second fileset for resources  */
        List <String> resources = getAllResources (jarFile);
        if (resources.size () > 0) {
            ret += "            <fileset dir=\"${compilation.path}\" >"+ this.NL;
            
            for (String resource : resources) {
                ret += "          <include name=\"" + resource + "\"/>"+ this.NL;
            }
            
            ret += "            </fileset>"+ this.NL;
        }
        
        ret += "        </jar>"+ this.NL +
        "    </target>"+ this.NL;
        return ret;
    }

    private String createFileSetElement(final Artifact jarFile) {
        String ret = "";
        
        for (Manifestation manif : jarFile.getUtilized()) {
            ModelElement elt = manif.getUtilizedElement();
            
            if (elt instanceof Package) {
                String fileName = JavaDesignerUtils.getFullJavaName(this.module.getModuleContext().getModelingSession(), elt);
                fileName = fileName.replace(".", "/");
                if( !fileName.isEmpty() ) {
                    fileName += "/";
                }
                
                ret += "                <include name=\"" + fileName + "**/*.class\"/>" + this.NL;
            }
            
            if (elt instanceof GeneralClass && ! (elt instanceof Artifact)) {
                String fileName = JavaDesignerUtils.getFullJavaName(this.module.getModuleContext().getModelingSession(), elt);
                fileName = fileName.replace(".", "/");
                
                ret += "                <include name=\"" + fileName + ".class\"/>" + this.NL;
            }
        }
        
        
        if (!ret.isEmpty()) {
            ret  = "            <fileset dir=\"${compilation.path}\">" + this.NL + ret;
            
            ret += "            </fileset>" + this.NL;
        }
        return ret;
    }

    private List<String> getAllResources(final Artifact jarFile) throws IOException {
        List<String> ret = new ArrayList<> ();
        
        for (Manifestation manif : jarFile.getUtilized()) {
            ModelElement utilized = manif.getUtilizedElement();
            if (utilized instanceof ModelTree) {
                ret.addAll(getAllResources ((ModelTree) utilized));
            }
        }
        return ret;
    }

    private List<String> getAllResources(final ModelTree elt) throws IOException {
        List<String> resources = new ArrayList <> (); 
        
        for (ModelTree ownedElement : elt.getOwnedElement()) {
            if (ownedElement instanceof Artifact && ownedElement.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVARESOURCE)) {
                File directory = JavaDesignerUtils.getDirectory(this.module.getModuleContext().getModelingSession(), ownedElement);
                resources.add(new File(directory, JavaDesignerUtils.getJavaName (ownedElement)).getPath()); 
            } else if (ownedElement instanceof Package) {
                resources.addAll(getAllResources(ownedElement));
            }
        }
        return resources;
    }

    private String createResourcesTarget(final Artifact jarFile) throws IOException {
        String ret = "    <target name=\"resources\" depends=\"init\">"+ this.NL +
        "        <echo message=\"Copying resources\"/>"+ this.NL;
        
        // The resources target copy resources files from source dir to class dir.
        List <String> resources = getAllResources (jarFile);
        if (resources.size () != 0) {
            ret += "        <copy toDir=\"${compilation.path}\" failonerror=\"false\" preservelastmodified=\"true\" >"+ this.NL +
            "            <fileset dir=\"${generation.path}\" >"+ this.NL;
            
            for (String resource : resources) {
                ret += "                <include name=\"" + resource + "\"/>"+ this.NL;
            }
            
            ret += "            </fileset>"+ this.NL +
            "        </copy>"+ this.NL;
        }
        
        ret += "    </target>"+ this.NL;
        return ret;
    }

    private String createCleanTarget(final Artifact jarFile) {
        String ret = "    <target name=\"clean\" depends=\"init\"> "+ this.NL +
        "        <delete>"+ this.NL +
        createFileSetElement(jarFile) +
        "        </delete>"+ this.NL +
        "    </target>"+ this.NL;
        return ret;
    }

    private String createInitTarget(final Artifact jarFile) throws IOException {
        File JNIOut          = JavaDesignerUtils.getJavahGenerationPath (this.module);
        File compilationpath = JavaDesignerUtils.getCompilationPath (jarFile, this.module);
        File generationpath  = JavaDesignerUtils.getGenerationPath (jarFile, this.module);
        
        String ret;
        ret =  "    <target name=\"init\" >"+ this.NL;
        ret += "        <echo message=\"Initializing Ant properties\"/>"+ this.NL;
        ret += "        <property name=\"generation.path\" location=\"" + generationpath.getCanonicalPath() + "\"/>"+ this.NL;
        ret += "        <property name=\"compilation.path\" location=\"" + compilationpath.getCanonicalPath() + "\"/>"+ this.NL;
        ret += "        <property name=\"JNIout\" location=\"" + JNIOut.getCanonicalPath() + "\"/>"+ this.NL;
        ret += "    </target>"+ this.NL;
        return ret;
    }

    private String createConfigs(final Artifact jarFile) throws IOException {
        List <File> classpath = JavaDesignerUtils.getClassPath(this.module);
        
        File binDir = JavaDesignerUtils.getCompilationPath(jarFile, this.module);
        
        String javadesignerjar = this.module.getModuleContext().getConfiguration().getModuleResourcesPath() + "/bin/javadesigner.jar";
        
        String ret = "  <path id=\"project.classpath\">"+ this.NL;
        
        ret += "    <pathelement location=\"" + javadesignerjar + "\"/>"+ this.NL;
        
        ret += "    <pathelement location=\"" + binDir + "\"/>"+ this.NL;
        
        for (File entry : classpath) {
            ret += "    <pathelement location=\"" + entry.getCanonicalPath() + "\"/>"+ this.NL;
        }
        
        for (File entry : getUsedJar(jarFile)) {
            ret += "    <pathelement location=\"" + entry.getCanonicalPath() + "\"/>"+ this.NL;
        }
        
        ret += "  </path>"+ this.NL;
        return ret;
    }

    private List<File> getUsedJar(final Artifact jarFile) {
        List<File> ret = new ArrayList<>();
        
        for (Dependency dep : jarFile.getDependsOnDependency()) {
            ModelElement dependsOnElt = dep.getDependsOn();
            
            if (dependsOnElt.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE)) {
                ret.add(JavaDesignerUtils.getFilename((Artifact)dependsOnElt, this.module));
            }
        }
        return ret;
    }

    private boolean isUseJavah() {
        return this.module.getModuleContext().getConfiguration().getParameterValue(JavaDesignerParameters.USEJAVAH).equalsIgnoreCase("TRUE");
    }

}
