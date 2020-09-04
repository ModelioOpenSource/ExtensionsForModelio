package org.modelio.module.javadesigner.commands.configuration;

import java.io.File;
import java.util.List;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.reverse.newwizard.ImageManager;
import org.modelio.module.javadesigner.reverse.newwizard.api.IExternalJarsModel;
import org.modelio.module.javadesigner.reverse.newwizard.externaljars.ExternalJarsClasspathModel;
import org.modelio.module.javadesigner.reverse.newwizard.wizard.ClassPathEditionWizardView;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class EditClasspath extends DefaultModuleCommandHandler {
    /**
     * The command is displayed if the selected element is the root package.
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if(!super.accept(selectedElements, module)) {
            return false;
        }
        boolean result =(selectedElements.size() != 0);
        
        // Button only for Root package
        for(MObject element : selectedElements) {
            if(!((element instanceof Package) &&((Package) element).getOwner() == null) &&
                    !JavaDesignerUtils.isAJavaComponent((ModelElement) element) &&
                    !JavaDesignerUtils.isAModule((ModelElement) element)) {
                result = false;
            }
        }
        return result;
    }

    /**
     * This method precizes if a command has to be desactivated. If the command has to be displayed(which means that
     * the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on
     * specific constraints that are specific to the module.
     */
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if(!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        String modulePath = module.getModuleContext().getConfiguration().getModuleResourcesPath().toAbsolutePath().toString();
        ImageManager.setModulePath(modulePath);
        
        IExternalJarsModel externalJarsClasspathModel = new ExternalJarsClasspathModel();
        
        List<File> classpath = externalJarsClasspathModel.getClasspath();
        classpath.addAll(JavaDesignerUtils.getClassPath(module));
        
        ClassPathEditionWizardView rw = new ClassPathEditionWizardView(Display.getDefault().getActiveShell(), externalJarsClasspathModel);
        if(rw.open() == 0) {
            IModelingSession session = module.getModuleContext().getModelingSession();
            try(ITransaction t = session.createTransaction("update classpath")) {
                updateClassPath(classpath, module);
                t.commit();
            }
        }
    }

    private void updateClassPath(final List<File> classpath, final IModule module) {
        String sep = System.getProperty("os.name").startsWith("Windows") ? ";" : ":";
        
        StringBuilder buffer = new StringBuilder();
        for(File path : classpath) {
            buffer.append(path.getAbsolutePath());
            buffer.append(sep); //$NON-NLS-1$
        }
        if(buffer.length() != 0) {
            module.getModuleContext().getConfiguration().setParameterValue(JavaDesignerParameters.ACCESSIBLECLASSES, buffer.substring(0, buffer.length() - 1));
        } else {
            module.getModuleContext().getConfiguration().setParameterValue(JavaDesignerParameters.ACCESSIBLECLASSES, ""); //$NON-NLS-1$
        }
    }

}
