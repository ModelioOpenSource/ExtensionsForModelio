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
@objid ("463ead25-4cde-4d69-98da-8bb1d69ab2e5")
public class I18nMessageService {
    @objid ("97374cab-78ac-4562-a6c4-a49a3869a7b4")
    private static final String FILE_NAME_MESSAGES = "org.modelio.module.sysml.i18n.messages";

    @objid ("aab38df2-69e5-4a7d-bf73-30e44411dac6")
    private static I18nMessageService instance;

    @objid ("278e1bb6-71a0-41e8-bead-81e79a26bad7")
    private ResourceBundle messageResource;

    /**
     * Private constructor.
     */
    @objid ("cd46e10c-e9e0-48a4-b96b-80e1d4631204")
    private I18nMessageService() {
        Locale locale = Locale.getDefault();
        this.messageResource = ResourceBundle.getBundle(FILE_NAME_MESSAGES, locale);
    }

    /**
     * Singleton creation.
     */
    @objid ("b28ccc9f-0a98-4b21-8f44-d216db2efa96")
    private static I18nMessageService getInstance() {
        if (null == instance) { // Premier appel
            instance = new I18nMessageService();
        }
        return instance;
    }

    /**
     * @return the messageResource
     */
    @objid ("e721ad78-9cf0-45a8-be66-46b9255bc591")
    private ResourceBundle getMessageResource() {
        return this.messageResource;
    }

    /**
     * Get message value from key.
     * 
     * @param key the key for the desired string.
     * @return the string for the given key.
     */
    @objid ("1aaa6349-375a-4dd4-b2b5-da987ccb1e8f")
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
    @objid ("0e3d1b43-4456-4b94-9e36-69400402ad17")
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
