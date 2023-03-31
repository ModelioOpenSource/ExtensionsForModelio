package org.modelio.module.javadesigner.xmlreverse.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;


public class Messages {
    
    private static final String BUNDLE_NAME = "org.modelio.module.javadesigner.xmlreverse.i18n.messages"; // $NON-NLS-1$

    
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

    /**
     * Empty private constructor, services are accessed through static methods.
     */
    
    private Messages() {
        // Nothing to do
    }

    
    public static String getString(final String key, final Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    
    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

}
