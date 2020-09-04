package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.modelio.module.xmlreverse.IReportWriter;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.progress.ProgressBar;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.identification.IdentifierManager;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.SourceStructuralModel;

public class XMLGeneratorFromSource {
    private Context context;

    private IReportWriter report;

    public XMLGeneratorFromSource(final List<File> srcFiles, final List<File> sourcepath, final List<File> classpath, final IReportWriter report, final String encoding) {
        this.report = report;
        GeneratorStack gs = new GeneratorStack();
        SourceStructuralModel sm = new SourceStructuralModel(srcFiles, sourcepath, classpath, report, encoding);
        this.context = new Context(sm,
                new IdentifierManager(),
                report,
                new TypeFinder(sm, gs),
                gs,
                new XMLGeneratorFactory());
    }

    /**
     * Start XML file generation
     * @param srcFiles
     * @param outputFile
     * @throws IOException
     * @throws NameCollisionException
     * @param encoding @return
     */
    public boolean generateModel(final List<File> srcFiles, final File outputFile, final String encoding) throws IOException, NameCollisionException {
        XMLBuffer.open (outputFile, encoding);
        XMLBuffer.model.write ("<model>\n"); //$NON-NLS-1$
        
        boolean retval = true;
        int fileIdx = 0;
        for (File file : srcFiles) {
            ProgressBar.setTaskName(Messages.getString("Gui.Reverse.ParsingFile", file.getName()));
            this.context.getGStack().setCurrentFile(file);
            // Create the structural model for the file already known to SModel
            ASTTree root = this.context.getSModel().addFileToStructuralModel(fileIdx++);
            // reset the state of the type finder
            this.context.getTFinder().clear();
            // Generate XML tags.
            generateElementXML(root);
            if (!ProgressBar.updateProgressBar(null)) {
                retval = false;
                break;
            }
        }
        
        XMLBuffer.model.write ("</model>\n"); //$NON-NLS-1$
        
        //Finalize the XML file by giving it the list of undefined identifiers
        GeneratorUtils.generateFullExternalReferences(this.context.getIdManager().getUndefinedEntries());
        GeneratorUtils.generateReportList();
        XMLBuffer.close();
        return retval;
    }

    private void generateElementXML(final ASTTree root) {
        try {
            // generate the intermediate XML file
            CompilationUnitXMLGenerator compUnitXmlGenerator = new CompilationUnitXMLGenerator();
            compUnitXmlGenerator.generateXML(root, this.context);
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            String filename = this.context.getGStack().getCurrentFile().getAbsolutePath();
            StringBuilder sBuffer = new StringBuilder ();
            sBuffer.append (Messages.getString ("reverse.File", filename));
        
            String message = e.getMessage ();
            sBuffer.append (message != null ? message : e.getClass().getName());
        
            this.report.addError(Messages.getString ("reverse.Parser_exception", filename), null, sBuffer.toString ());
        }
        ProgressBar.updateProgressBar (null);
    }

}
