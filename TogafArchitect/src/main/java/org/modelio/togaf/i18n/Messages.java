package org.modelio.togaf.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("org.modelio.togaf.i18n.messages");

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static String getString(String key, String... params) {
		try {
			return MessageFormat.format(RESOURCE_BUNDLE.getString(key), (Object[]) params);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
