package org.modelio.module.javadesigner.reverse;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Collection;
import com.modelio.module.xmlreverse.IReportWriter;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.progress.ProgressBar;
import org.modelio.module.javadesigner.reverse.retrieve.IRetrieveData;
import org.modelio.module.javadesigner.reverse.retrieve.RetrieveParser;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;

public class MDReverseProgressBar extends ProgressBar implements IRunnableWithProgress {
    private Collection<NameSpace> elementsToReverse;

    private IReportWriter report;

    public MDReverseProgressBar(final IModule module, final Collection<NameSpace> elementsToReverse, final IReportWriter report) {
        super (module, elementsToReverse.size ());
        this.elementsToReverse = elementsToReverse;
        this.report = report;
    }

    @Override
    public void run(final IProgressMonitor localMonitor) throws InterruptedException, InvocationTargetException {
        init (true);
        monitor = localMonitor;
        monitor.beginTask ("Reversing", this.elementsToReverse.size ());
        
        // Reverse in Model Driven
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        RetrieveParser parser = new RetrieveParser(this.report);
        for (NameSpace eltToRetrieve : this.elementsToReverse) {
            File fileToRetrieve = JavaDesignerUtils.getFilename (eltToRetrieve, this.module);
            monitor.setTaskName (Messages.getString ("Info.ProgressBar.Updating", fileToRetrieve)); //$NON-NLS-1$
            try {
                for (IRetrieveData retrieveData : parser.retrieve(fileToRetrieve, getEncoding())) {
                    retrieveData.inject(session, eltToRetrieve);
                }
        
                JavaDesignerUtils.updateDate (session, eltToRetrieve, Calendar.getInstance ().getTimeInMillis ());
            } catch (Exception e) {
                this.report.addError(Messages.getString ("Error.RetrieveError.Message", fileToRetrieve), eltToRetrieve, Messages.getString ("Error.RetrieveError.Description", e.getMessage()));
                monitor.setCanceled (true);
            }
        
            updateProgressBar (null);
            if (monitor.isCanceled ()) {
                throw new InterruptedException ();
            }
        }
        
        monitor.done ();
    }

    private String getEncoding() {
        try {
            switch (this.module.getModuleContext().getConfiguration().getParameterValue(JavaDesignerParameters.ENCODING)) {
            case "ISO_8859_1":
            case "ISO-8859-1":
                return "ISO-8859-1";
            case "US_ASCII":
            case "US-ASCII":
                return "US-ASCII";
            case "UTF_16":
            case "UTF-16":
                return "UTF-16";
            case "UTF_16BE":
            case "UTF-16BE":
                return "UTF-16BE";
            case "UTF_16LE":
            case "UTF-16LE":
                return "UTF-16LE";
            case "UTF_8":
            case "UTF-8":
            default:
                return "UTF-8";
            }
        } catch (IllegalArgumentException e) {
            return "UTF-8";
        }
    }

}
