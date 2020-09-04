package org.modelio.module.javadesigner.ant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.custom.CustomFileException;
import org.modelio.module.javadesigner.dialog.JConsoleWithDialog;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ProcessManager;
import org.xml.sax.SAXException;

public class AntExecutor {
    private IModule module;

     JConsoleWithDialog console;

    public AntExecutor(final IModule module) {
        this(module, new JConsoleWithDialog(null));
    }

    public AntExecutor(final IModule module, final JConsoleWithDialog console) {
        this.module = module;
        this.console = console;
    }

    public boolean executeTarget(final Artifact antArtifact) {
        File antFile = JavaDesignerUtils.getAntFileName(antArtifact, this.module);
        
        try {
            List<String> targets = loadTargets(antFile);
            
            if (!targets.isEmpty()) {
                String target = AntTargetExplorer.getTarget(Display.getDefault().getActiveShell(), targets);
                
                return executeTarget(antArtifact, target);
            } else {
                this.console.writeError("No target found\n");
            }
        } catch (CustomFileException e) {
            this.console.writeError(e.getMessage());
            this.console.writeError("\n");
            return false;
        } catch (InterruptedException e) {
            // Box canceled
        }
        return false;
    }

    public boolean executeTarget(final Artifact antArtifact, final String target) {
        File antFile = JavaDesignerUtils.getAntFileName(antArtifact, this.module);
        
        IModuleUserConfiguration config = this.module.getModuleContext().getConfiguration();
        
        File jdkPath = JavaDesignerUtils.getJDKPath(this.module);
        
        File projectDir = JavaDesignerUtils.getCompilationPath (antArtifact, this.module);
        projectDir.mkdirs();
        
        // Complete classpath with Antlr jars
        String toolsJar = jdkPath + "/lib/tools.jar";
        String sep = System.getProperty ("os.name").startsWith ("Windows") ? ";" : ":";
        
        String classpath = config.getParameterValue(JavaDesignerParameters.ACCESSIBLECLASSES);
        
        // Get ANTLR directory
        String antDir = config.getModuleResourcesPath() + "/bin/ant/";
        for (File antJarFile : new File(antDir).listFiles()) {
            classpath += sep + antJarFile;
        }
        
        classpath += sep + toolsJar;
        
        // Build command
        String javaCmd = "\"" + jdkPath + "/bin/java\"";
        String command = javaCmd + " -cp \"" + classpath + "\" -Dant.home=\""+ antDir + "\" -Dbasedir=\"" + projectDir + "\" org.apache.tools.ant.Main -f \"" + antFile +"\" " + target;
        
        // Write command before execution
        this.console.writeInfo(command + "\n\n");
        
        // Execute command
        ProcessManager manager = new ProcessManager(this.console);
        manager.execute (command, false);
        
        this.console.writeInfo("\n");
        return true;
    }

    /**
     * Start the XML parsing of the file in argument.
     * @param antFile The file to parse.
     * @throws org.modelio.module.javadesigner.custom.CustomFileException If an error has occurred during the loading.
     */
    public List<String> loadTargets(final File antFile) throws CustomFileException {
        // Use a SAX event handler
        AntTargetLoader handler = new AntTargetLoader ();
        
        // Use the validating parser
        SAXParserFactory factory = SAXParserFactory.newInstance ();
        factory.setNamespaceAware (true);
        
        try {
            // Parse the input
            SAXParser saxParser = factory.newSAXParser ();
            saxParser.parse (antFile, handler);
            
            // Save data from the handler
            return handler.getTargets();
        } catch (SAXException e) {
            throw new CustomFileException ("Error when parsing file \"" + antFile.getAbsolutePath () + "\": " + e.getMessage ());
        } catch (FileNotFoundException e) {
            throw new CustomFileException ("Ant file not found: \"" + antFile.getAbsolutePath () + "\" does not exist");
        } catch (IOException e) {
            throw new CustomFileException ("Error when loading file: \"" + antFile.getAbsolutePath () + "\"");
        } catch (ParserConfigurationException e) {
            throw new CustomFileException ("Error when loading file: \"" + antFile.getAbsolutePath () + "\"");
        }
    }

}
