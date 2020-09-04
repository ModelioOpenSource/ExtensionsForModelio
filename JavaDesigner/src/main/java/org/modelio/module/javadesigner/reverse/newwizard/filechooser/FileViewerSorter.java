package org.modelio.module.javadesigner.reverse.newwizard.filechooser;

import java.io.File;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * Orders the items in such a way that directories appear
 * before other files.
 */
public class FileViewerSorter extends ViewerSorter {
    @Override
    public int category(final Object element) {
        if(element instanceof File) {
            File f = (File) element;
            if (f.isDirectory()) {
                return 1;
            } else {
                return 2;
            }
        }
        // Should never happen
        return 3;
    }

}
