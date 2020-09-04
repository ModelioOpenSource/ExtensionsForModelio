/**
 * Code retrieved from the Modelio binary
 */
package org.modelio.module.javadesigner.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.modelio.module.javadesigner.impl.JavaDesignerModule;

public class JDKFinder {

    public static List<String> searchForJDKPath() {
        List<String> jdkList = null;
        
        String os = System.getProperty ("os.name"); //$NON-NLS-1$
        if (os.startsWith("Windows")) { //$NON-NLS-1$
            jdkList = searchForJDKPathWin ();
        } else if (os.equals ("Linux")) { //$NON-NLS-1$
            jdkList = searchForJDKPathLinux ();
        } else {
            jdkList = new ArrayList<> ();
        }
        return jdkList;
    }

    private static List<String> searchForJDKPathLinux() {
        List<String> jdkList = new ArrayList<> ();
        
        String javaHome = System.getenv ("JAVA_HOME"); //$NON-NLS-1$
        
        if (javaHome != null) {
            String[] javaHomes = javaHome.split (":"); //$NON-NLS-1$
        
            for (int i = 0 ; i < javaHomes.length ; i++) {
                jdkList.add (javaHomes[i]);
            }
        }
        return jdkList;
    }

    private static List<String> searchForJDKPathWin() {
        List<String> jdkList = new ArrayList<> ();
        
        String REGQUERY_UTIL = "reg query "; //$NON-NLS-1$
        String COMPUTER_WINDOWS_FAVORITES_FOLDER = REGQUERY_UTIL +
                "\"HKLM\\SOFTWARE\\JavaSoft\\Java Development Kit\" /s"; //$NON-NLS-1$
        
        try {
            Process process = Runtime.getRuntime ().exec (COMPUTER_WINDOWS_FAVORITES_FOLDER);
        
            BufferedReader reader = new BufferedReader (new InputStreamReader (process.getInputStream ()));
        
            String line = reader.readLine ();
        
            int pathIndex = 0;
        
            while (line != null) {
                if (line.indexOf ("JavaHome") != -1) { //$NON-NLS-1$
                    pathIndex = line.indexOf ("REG_SZ") + 6; //$NON-NLS-1$
                    line = line.substring (pathIndex);
                    line = line.trim ();
                    if (!jdkList.contains (line)) {
                        jdkList.add (line);
                    }
                }
                line = reader.readLine ();
            }
        } catch (IOException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        return jdkList;
    }

}
