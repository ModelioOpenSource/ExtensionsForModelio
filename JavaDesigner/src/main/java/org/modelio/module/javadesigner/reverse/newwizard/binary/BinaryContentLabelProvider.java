package org.modelio.module.javadesigner.reverse.newwizard.binary;

import com.modelio.module.xmlreverse.model.JaxbClass;
import com.modelio.module.xmlreverse.model.JaxbDataType;
import com.modelio.module.xmlreverse.model.JaxbEnumeration;
import com.modelio.module.xmlreverse.model.JaxbGroup;
import com.modelio.module.xmlreverse.model.JaxbInterface;
import com.modelio.module.xmlreverse.model.JaxbPackage;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.modelio.module.javadesigner.reverse.newwizard.ImageManager;

class BinaryContentLabelProvider implements ILabelProvider {
    @Override
    public Image getImage(final Object element) {
        if (element instanceof JaxbClass) {
            return ImageManager.getInstance().getIcon("javaclass");
        } else if (element instanceof JaxbDataType) {
            return ImageManager.getInstance().getIcon("javadatatype");
        } else if (element instanceof JaxbEnumeration) {
            return ImageManager.getInstance().getIcon("javaenumeration");
        } else if (element instanceof JaxbGroup) {
            String name = ((JaxbGroup) element).getName();
            if (name.endsWith(".jar")) {
                return ImageManager.getInstance().getIcon("jar");
            }
        } else if (element instanceof JaxbInterface) {
            return ImageManager.getInstance().getIcon("javainterface");
        } else if (element instanceof JaxbPackage) {
            return ImageManager.getInstance().getIcon("javapackage");
        }
        return null;
    }

    @Override
    public String getText(final Object element) {
        String ret;
        if (element instanceof JaxbClass) {
            ret = ((JaxbClass) element).getName();
        } else if (element instanceof JaxbDataType) {
            ret = ((JaxbDataType) element).getName();
        } else if (element instanceof JaxbEnumeration) {
            ret = ((JaxbEnumeration) element).getName();
        } else if (element instanceof JaxbGroup) {
            ret = ((JaxbGroup) element).getName();
        } else if (element instanceof JaxbInterface) {
            ret = ((JaxbInterface) element).getName();
        } else if (element instanceof JaxbPackage) {
            ret = ((JaxbPackage) element).getName();
        } else {
            ret = element.toString();
        }
        
        int index = ret.lastIndexOf(".");
        if (index != -1) {
            if (ret.length() > index) {
                ret = ret.substring(index + 1);
            }
        }
        return ret;
    }

    @Override
    public void addListener(final ILabelProviderListener arg0) {
        // Nothing to do
    }

    @Override
    public void dispose() {
        // Nothing to do
    }

    @Override
    public boolean isLabelProperty(final Object arg0, final String arg1) {
        return false;
    }

    @Override
    public void removeListener(final ILabelProviderListener arg0) {
        // Nothing to do
    }

}
