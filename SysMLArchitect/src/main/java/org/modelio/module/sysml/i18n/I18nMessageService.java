package org.modelio.module.sysml.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Message service
 * <br>Use of "i18n/messages"
 */

public class I18nMessageService {
    
    private static final String FILE_NAME_MESSAGES = "org.modelio.module.sysml.i18n.messages";

    
    private static I18nMessageService instance;

    
    private ResourceBundle messageResource;

    /**
     * Private constructor.
     */
    
    private I18nMessageService() {
        Locale locale = Locale.getDefault();
        this.messageResource = ResourceBundle.getBundle(FILE_NAME_MESSAGES, locale);
    }

    /**
     * Singleton creation.
     */
    
    private static I18nMessageService getInstance() {
        if (null == instance) { // Premier appel
            instance = new I18nMessageService();
        }
        return instance;
    }

    /**
     * @return the messageResource
     */
    
    private ResourceBundle getMessageResource() {
        return this.messageResource;
    }

    /**
     * Get message value from key.
     * 
     * @param key the key for the desired string.
     * @return the string for the given key.
     */
    
    public static String getString(String key) {
        String message = null;
        try {
            message = getInstance().getMessageResource().getString(key);
        } catch (@SuppressWarnings ("unused") MissingResourceException e) {
            message = '!' + key + '!';
        }
        return message;
    }

    /**
     * Get list of messages values from key with parameters.
     * 
     * @param key the key for the desired string.
     * @param params an array of objects to be formatted and substituted.
     * @return the string for the given key.
     */
    
    public static String getString(String key, String... params) {
        String message = null;
        try {
            String value = getString(key);
            message = MessageFormat.format(value, (Object[]) params);
        } catch (@SuppressWarnings ("unused") MissingResourceException e) {
            message = '!' + key + '!';
        }
        return message;
    }

}
