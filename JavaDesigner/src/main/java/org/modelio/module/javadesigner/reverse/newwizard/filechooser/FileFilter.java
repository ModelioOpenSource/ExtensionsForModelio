package org.modelio.module.javadesigner.reverse.newwizard.filechooser;

import java.io.File;
import java.util.List;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

class FileFilter extends ViewerFilter {
    private List<String> extensions;

    public FileFilter(final List<String> extensions) {
        this.extensions = extensions;
    }

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        if (element instanceof File) {
            File f = (File) element;
            
            // Filter hidden files
            if (f.isHidden()) {
                return false;
            }
            
            // Accept directories
            if (f.isDirectory()) {
                return true;
            }
        
            // Accepts file with valid extensions
            String name = f.getName();
            for (String extension : this.extensions) {
                if (name.endsWith (extension)) { //$NON-NLS-1$
                    return true;
                }
            }
            
            // Filter elements with invalid extensions
            return false;
        }
        
        // Filter elements that aren't files
        return false;
    }

}
