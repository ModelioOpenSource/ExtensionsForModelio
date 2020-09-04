package org.modelio.module.javadesigner.reverse.newwizard;

import java.util.List;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class is used to create TreeItem elements from the Modelio UML model.
 * Use the singleton pattern.
 */
public class ListContentProvider implements ITreeContentProvider {
    private static Object[] EMPTY_ARRAY = new Object[0];

    @Override
    public void dispose() {
        // Nothing to do
    }

    @Override
    public Object[] getChildren(final Object p_Object) {
        if (p_Object instanceof List<?>) {
            List<?> obj = (List<?>) p_Object;
        
            return obj.toArray();
        }
        return EMPTY_ARRAY;
    }

    @Override
    public Object[] getElements(final Object p_Object) {
        Object[] res = this.getChildren(p_Object);
        return res;
    }

    @Override
    public boolean hasChildren(final Object p_Object) {
        boolean res = (this.getChildren(p_Object).length > 0);
        return res;
    }

    @Override
    public void inputChanged(final Viewer arg0, final Object arg1, final Object arg2) {
        // Nothing to do
    }

    @Override
    public Object getParent(final Object arg0) {
        // Nothing to do
        return null;
    }

}
