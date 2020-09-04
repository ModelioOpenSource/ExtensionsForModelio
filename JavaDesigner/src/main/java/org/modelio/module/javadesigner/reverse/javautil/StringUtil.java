package org.modelio.module.javadesigner.reverse.javautil;


/**
 * Utility class without dependencies (except jdk)
 */
public class StringUtil {
    /**
     * Shorten a Java full Name by removing the package part. (ex
     * java.lang.Integer => Integer) If the name is already a short name, it is
     * returned unchanged.
     * @param name the Java full name
     * @return the short name or str if it is not a Java full name
     */
    public static String shortenFullName(final String name) {
        String shortname;
        int lastpoint = name.lastIndexOf('.');
        if (lastpoint != -1 && lastpoint < name.length()) {
            shortname = name.substring(lastpoint + 1);
        } else {
            shortname = name;
        }
        return shortname;
    }

}
