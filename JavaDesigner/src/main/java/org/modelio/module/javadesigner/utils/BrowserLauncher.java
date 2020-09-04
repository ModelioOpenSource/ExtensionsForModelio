package org.modelio.module.javadesigner.utils;

import java.io.File;
import java.lang.reflect.Method;

public class BrowserLauncher {
    public static void openURL(final File fileToOpen) throws Exception {
        // Error check
        if (!fileToOpen.exists ()) {
            throw new Exception ("JavaDoc File Not Found");
        }
        
        String osName = System.getProperty ("os.name");
        if (osName.startsWith ("Windows")) {
            Runtime.getRuntime ().exec ("rundll32 url.dll,FileProtocolHandler " +
                    fileToOpen.getAbsolutePath ());
        } else if (osName.startsWith ("Mac OS")) {
            Class<?> fileMgr = Class.forName ("com.apple.eio.FileManager");
            Method openURL = fileMgr.getDeclaredMethod ("openURL", new Class[] { String.class });
            openURL.invoke (null, new Object[] { fileToOpen.getAbsolutePath () });
        } else { // assume Unix or Linux
            // TODO file opening must be extended
            String[] browsers = {
                "firefox",
                "opera",
                "konqueror",
                "epiphany",
                "mozilla",
                "netscape" };
            String browser = null;
            for (int count = 0 ; (count < browsers.length) && (browser == null) ; count++) {
                if (Runtime.getRuntime ().exec (new String[] {
                    "which",
                    browsers[count] }).waitFor () == 0) {
                    browser = browsers[count];
                }
            }
        
            if (browser == null) {
                throw new Exception ("Browser Not Found");
            }
        
            Runtime.getRuntime ().exec (new String[] {
                browser,
                fileToOpen.getAbsolutePath () });
        }
    }

    public static void openExternalEditor(final String editor, final File fileToOpen) throws Exception {
        if (editor == null) {
            throw new Exception ("Editor Not Found");
        }
        
        Process p = Runtime.getRuntime ().exec (new String[] {
            editor,
            fileToOpen.getAbsolutePath () });
        // Wait for the process to end
        p.waitFor ();
    }

}
