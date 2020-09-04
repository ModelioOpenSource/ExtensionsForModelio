package org.modelio.module.javadesigner.editor;

import java.io.File;
import java.util.HashSet;
import org.modelio.api.modelio.editor.IMDAEditorListener;
import org.modelio.api.modelio.editor.IMDATextEditor;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.javadesigner.report.ReportManager;
import org.modelio.module.javadesigner.report.ReportModel;
import org.modelio.module.javadesigner.reverse.Reversor;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;

public class EditorListener implements IMDAEditorListener {
    private IModule module;

    public EditorListener() {
        // Nothing to initialize
    }

    public void setModule(final IModule module) {
        this.module = module;
    }

    @Override
    public void documentSaved(final IMDATextEditor editor, final ModelElement element, final File file) {
        if (element instanceof NameSpace) {
            HashSet<NameSpace> elementsToReverse = new HashSet<> ();
            elementsToReverse.add ((NameSpace) element);
        
            try {
                JavaDesignerUtils.initCurrentGenRoot (elementsToReverse);
            } catch (InterruptedException e) {
                return;
            }
            
            ReportModel report = ReportManager.getNewReport ();
        
            Reversor reversor = new Reversor (this.module, report);
            reversor.update (elementsToReverse, EditorManager.getInstance ());
        
            ReportManager.showGenerationReport (report);
            
            JavaDesignerUtils.setProjectGenRoot (null);
        }
    }

    @Override
    public void editorClosed(final IMDATextEditor editor) {
        EditorManager.getInstance ().removeEditor (editor);
    }

}
