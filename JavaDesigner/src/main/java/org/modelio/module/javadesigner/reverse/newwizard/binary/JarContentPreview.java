package org.modelio.module.javadesigner.reverse.newwizard.binary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import com.modelio.module.xmlreverse.model.JaxbReversedData;
import com.modelio.module.xmlreverse.model.serialization.ModelUnmarshaller;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.RTReverseProgressBar;
import org.modelio.module.javadesigner.reverse.ReverseConfig;
import org.modelio.module.javadesigner.reverse.ui.ElementStatus.ElementType;
import org.modelio.module.javadesigner.reverse.ui.ElementStatus.ReverseStatus;
import org.modelio.module.javadesigner.reverse.ui.ElementStatus;

public class JarContentPreview {
    /**
     * Computes a jaxb XML model from a list of jars.
     * @param assemblyFilesToReverse the jars to preview content.
     * @param config the reverse configuration.
     * @return The jaxb model corresponding to the jars content.
     */
    public JaxbReversedData computePreview(final Set<File> assemblyFilesToReverse, final ReverseConfig config) {
        if (assemblyFilesToReverse.size() > 0) {
            // Fill the files to reverse with the jars content
            config.setFilesToReverse (new Hashtable<String, ElementStatus>());
            for (File f : assemblyFilesToReverse) {
                if (f.isFile()) {
                    TreeSet<String> jarTree = createJarTree(f);
                    if (jarTree != null) {
                        for (String entry : jarTree) {
                            config.getFilesToReverse().put (entry, new ElementStatus(entry, ElementType.CLASS_FILE, ReverseStatus.REVERSE));                    
                        }
                    }
                }
            }
        
            // Before the binary reverse, we have to add the jars to reverse to the classpath
            List<File> classpath = config.getClasspath();
            
            // Store the old classpath for further reset
            List<File> oldClasspath = new ArrayList<> (classpath);
            
            // Fill classpath
            classpath.addAll(assemblyFilesToReverse);
            
            // Do the reverse
            RTReverseProgressBar reverser = new RTReverseProgressBar(null, config);
            reverser.launchReverseFromJar();
        
            // Reset classpath to its old value
            config.setClasspath(oldClasspath);
            
            // Load the XML Model itself
            File outputFile = config.getOutputFile();
            if (outputFile != null && outputFile.exists()) {
                ModelUnmarshaller unmarshaller = new ModelUnmarshaller(config.getReport());
                JaxbReversedData reversdata =  (JaxbReversedData)unmarshaller.load(outputFile, config.getStrategyConfiguration ().ENCODING);
        
                if (reversdata != null) {
                    return reversdata;
                }
            }
        }
        return null;
    }

    private TreeSet<String> createJarTree(final File rootFile) {
        try (ZipFile zipFile = new ZipFile (rootFile)) {
            return sortFileEntries (zipFile.entries ());
        } catch (IOException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        return null;
    }

    private TreeSet<String> sortFileEntries(final Enumeration<? extends ZipEntry> enumeration) {
        Comparator<String> stringCmp = new Comparator<String> () {
            @Override
            public int compare (String o1, String o2) {
                String s1 = o1;
                String s2 = o2;
                int len1 = s1.length ();
                int len2 = s2.length ();
                for (int i = 0, n = Math.min (len1, len2) ; i < n ; i++) {
                    char c1 = s1.charAt (i);
                    char c2 = s2.charAt (i);
                    if (c1 != c2) {
                        if ((c1 >= 'a' && c1 <= 'z' && c2 >= 'A' && c2 <= 'Z') ||
                                (c2 >= 'a' && c2 <= 'z' && c1 >= 'A' && c1 <= 'Z')) {
                            return c2 - c1;
                        } else {
                            return c1 - c2;
                        }
                    }
                }
                return len1 - len2;
            }
        };
        
        TreeSet<String> set = new TreeSet<> (stringCmp);
        
        for (Enumeration<? extends ZipEntry> e = enumeration ; e.hasMoreElements () ;) {
            String thisEntry = (e.nextElement ()).getName ();
            if (thisEntry.endsWith (".class") && thisEntry.indexOf ("$") == -1) {
                set.add (thisEntry);
            }
        }
        return set;
    }

}
