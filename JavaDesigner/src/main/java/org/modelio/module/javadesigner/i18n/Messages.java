package org.modelio.module.javadesigner.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;

public class Messages {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle ("org.modelio.module.javadesigner.i18n.messages");

    /**
     * Empty private constructor, services are accessed through static methods.
     */
    private Messages() {
        // Nothing to do
    }

    public static String getString(final String key, final Object... params) {
        try {
            return MessageFormat.format (RESOURCE_BUNDLE.getString (key), params);
        } catch (MissingResourceException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error("Missing key: " + key);
            return '!' + key + '!';
        } catch (IllegalArgumentException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            return '!' + key + '!';
        }
    }

    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString (key);
        } catch (MissingResourceException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error("Missing key: " + key);
            return '!' + key + '!';
        }
    }

}
