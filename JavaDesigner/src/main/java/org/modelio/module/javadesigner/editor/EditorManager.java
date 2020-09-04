package org.modelio.module.javadesigner.editor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.editor.EditorType;
import org.modelio.api.modelio.editor.IEditionService;
import org.modelio.api.modelio.editor.IMDATextEditor;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.javadesigner.api.IRefreshService;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;

public class EditorManager implements IRefreshService {
    private static EditorManager instance;

    protected EditorListener listener = null;

    protected IModule module = null;

    protected Vector<IMDATextEditor> editors;

    public Vector<IMDATextEditor> getEditors() {
        return this.editors;
    }

    public EditorManager() {
        this.editors = new Vector<>();
        this.listener = new EditorListener();
    }

    protected IMDATextEditor getEditor(final NameSpace element) {
        IMDATextEditor editor = null;
        for(Iterator<IMDATextEditor> iterator = this.editors.iterator() ; iterator.hasNext() &&
               (editor == null) ;) {
            IMDATextEditor localeditor = iterator.next();
            if(element.equals(localeditor.getElement())) {
                editor = localeditor;
            }
        }
        return editor;
    }

    public static EditorManager getInstance() {
        if(instance == null) {
            instance = new EditorManager();
        }
        return instance;
    }

    public void open(final NameSpace element, final IModule currentModule) {
        this.module = currentModule;
        final MStatus status = element.getStatus();
        
        final String encoding = this.module.getModuleContext().getConfiguration().getParameterValue(JavaDesignerParameters.ENCODING);
        
        final File file = JavaDesignerUtils.getFilename(element, this.module);
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                boolean isReadOnly = !status.isModifiable();
        
                // Retrieve the editor if already opened
                IMDATextEditor editor = getEditor(element);
                IEditionService editionService = EditorManager.this.module.getModuleContext().getModelioServices().getEditionService();
                if(editor == null) {
                    // Open a new editor
                    EditorType editorId;
                    if(JavaDesignerUtils.isRoundtripMode(currentModule)) {
                        editorId = EditorType.RTEditor;
                    } else if(JavaDesignerUtils.isModelDrivenMode(currentModule)) {
                        editorId = EditorType.MDDEditor;
                    } else {
                        // Release mode, use RT editor for coloration, but always in read only.
                        editorId = EditorType.RTEditor;
                        isReadOnly = true;
                    }
        
                    editor = editionService.openEditor(element, file, editorId, isReadOnly, encoding);
                    editor.setListener(EditorManager.this.listener);
                    EditorManager.this.listener.setModule(currentModule);
                    EditorManager.this.editors.add(editor);
                } else {
                    editionService.activateEditor(editor);
                }
            }
        });
    }

    public void removeEditor(final IMDATextEditor editor) {
        this.editors.remove(editor);
    }

    public void updateStatusForElement(final MObject element, final boolean isReadOnly, final String encoding) {
        if((element instanceof NameSpace)) {
            NameSpace modelelement =(NameSpace) element;
            final IMDATextEditor editor = getEditor(modelelement);
            if(editor != null) {
                editor.setReadonlyMode(isReadOnly);
                editor.setCharsetName(encoding);
            }
        }
    }

    public void updateEditorsFromElements() {
        if(EditorManager.this.editors.size() == 0) {
            return;
        }
        
        final ArrayList<IMDATextEditor> toDelete = new ArrayList<>();
        final ArrayList<IMDATextEditor> needModeUpdate = new ArrayList<>();
        
        // Get the current generation mode from the module
        final boolean isReleaseMode;
        final EditorType editorId;
        if(EditorManager.this.module != null && JavaDesignerUtils.isRoundtripMode(this.module)) {
            editorId = EditorType.RTEditor;
            isReleaseMode = false;
        } else if(EditorManager.this.module != null && JavaDesignerUtils.isModelDrivenMode(this.module)) {
            editorId = EditorType.MDDEditor;
            isReleaseMode = false;
        } else {
            // Release mode, use RT editor for coloration, but always in read only.
            editorId = EditorType.RTEditor;
            isReleaseMode = true;
        }
        
        // Get the current encoding
        final String encoding = this.module.getModuleContext().getConfiguration().getParameterValue(JavaDesignerParameters.ENCODING);
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                // For all editors, check the mode for update, and verifies the element
                for(IMDATextEditor editor : EditorManager.this.editors) {
                    ModelElement modelElement = editor.getElement();
        
                    if(modelElement == null || modelElement.isDeleted()) {
                        toDelete.add(editor);
                    } else {
                        File openedFile = editor.getFile();
                        String newPath = JavaDesignerUtils.getDirectory(EditorManager.this.module.getModuleContext().getModelingSession(),(NameSpace) editor.getElement()) + File.separator + JavaDesignerUtils.getJavaName(editor.getElement()) + ".java";
        
                        // Check if the file has changed for this element
                        if(!openedFile.getAbsolutePath().endsWith(newPath) || !openedFile.exists()) {
                            toDelete.add(editor);
                        } else if(editor.getType() != null && !editor.getType().equals(editorId)) {
                            needModeUpdate.add(editor);
                        } else {
                            boolean isReadOnly = isReleaseMode || !modelElement.getStatus().isModifiable();
                            updateStatusForElement(modelElement, isReadOnly, encoding);
                        }
                    }
                }
        
                // For all editors with an invalid element, close it
                IEditionService editionService = EditorManager.this.module.getModuleContext().getModelioServices().getEditionService();
                for(IMDATextEditor editor : toDelete) {
                    editionService.closeEditor(editor);
                    EditorManager.this.editors.remove(editor);
                }
        
                // For all editors having a bad mode, close the current editor and open it again
                for(IMDATextEditor textEditor : needModeUpdate) {
                    NameSpace theElement =(NameSpace) textEditor.getElement();
                    editionService.closeEditor(textEditor);
                    open(theElement, EditorManager.this.module);
                }
            }
        });
    }

    public void closeEditors() {
        if(!this.editors.isEmpty()) {
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    // Create a temp list to store editors to delete.
                    ArrayList<IMDATextEditor> toDelete = new ArrayList<>(EditorManager.this.editors);
        
                    IEditionService editionService = EditorManager.this.module.getModuleContext().getModelioServices().getEditionService();
                    for(IMDATextEditor editor : toDelete) {
                        editionService.closeEditor(editor);
                        EditorManager.this.editors.remove(editor);
                    }
                }
            });
        }
    }

    @Override
    public void refresh(final Collection<NameSpace> elements) {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                // First, validate all editors
                updateEditorsFromElements();
        
                // Check the dirty flag
                for(IMDATextEditor editor : getEditors()) {
                    if(!editor.isDirty() && elements.contains(editor.getElement())) {
                        editor.reload();
                    }
                }
            }
        });
    }

}
