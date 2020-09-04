package org.modelio.module.javadesigner.reverse.newwizard.binary;

import org.eclipse.jface.viewers.TreePathViewerSorter;

class BinaryTreeSorter extends TreePathViewerSorter {
    @Override
    public int category(final Object element) {
        // Packages should always come first
        if (element instanceof com.modelio.module.xmlreverse.model.JaxbPackage) {
            return 0;
        }
        return 1;
    }

}
