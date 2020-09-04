package org.modelio.module.javadesigner.reverse.javatoxml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;

public class XMLBuffer {
    public static BufferedWriter model;

    public static void open(final File file, final String encoding) {
        try {
            model = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
            model.write ("<?xml version=\"1.0\" encoding=\"" + encoding + "\" ?>\n"); //$NON-NLS-1$
            model.write ("<reversed-data xmlns=\"http://www.modeliosoft.com/rev-modele.xsd\">\n"); //$NON-NLS-1$
        } catch (IOException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    public static void close() {
        try {
        
            // close the outermost tag
            model.write ("</reversed-data>\n"); //$NON-NLS-1$
        } catch (IOException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        } finally {
            try {
                model.close ();
            } catch (IOException e1) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e1);
            }
        }
    }

}
