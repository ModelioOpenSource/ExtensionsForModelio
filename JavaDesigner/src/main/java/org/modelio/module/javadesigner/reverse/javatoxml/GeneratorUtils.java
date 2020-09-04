package org.modelio.module.javadesigner.reverse.javatoxml;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PackageDef;
import org.modelio.module.javadesigner.reverse.javautil.XMLStringWriter;

/**
 * Utility XML generation services
 */
public class GeneratorUtils {
    public static StringBuilder getNoteTag(final String type, final StringBuilder content) {
        StringBuilder sBuffer = new StringBuilder ();
        
        sBuffer.append ("<note note-type=\""); //$NON-NLS-1$
        sBuffer.append (type);
        sBuffer.append ("\">\n"); //$NON-NLS-1$
        XMLStringWriter xw = new XMLStringWriter ();
        sBuffer.append ("<content><![CDATA["); //$NON-NLS-1$
        sBuffer.append (xw.encodedata (content.toString ()));
        // sBuffer.append(content);
        sBuffer.append ("]" + "]></content>\n"); //$NON-NLS-1$
        sBuffer.append ("</note>\n"); //$NON-NLS-1$
        return sBuffer;
    }

    public static void generateNoteTag(final String type, final StringBuilder content) throws IOException {
        generateNoteTag(type, content.toString());
    }

    public static void generateNoteTag(final String type, final String content) throws IOException {
        XMLBuffer.model.write ("<note note-type=\""); //$NON-NLS-1$
        XMLBuffer.model.write (type);
        XMLBuffer.model.write ("\">\n"); //$NON-NLS-1$
        XMLStringWriter xw = new XMLStringWriter ();
        XMLBuffer.model.write ("<content><![CDATA["); //$NON-NLS-1$
        XMLBuffer.model.write (xw.encodedata (content.toString ()));
        XMLBuffer.model.write ("]" + "]></content>\n"); //$NON-NLS-1$
        XMLBuffer.model.write ("</note>\n"); //$NON-NLS-1$
    }

    public static void generateStereotypeTag(final String type) throws IOException {
        XMLBuffer.model.write ("<stereotype stereotype-type=\""); //$NON-NLS-1$
        XMLBuffer.model.write (type);
        XMLBuffer.model.write ("\"/>\n"); //$NON-NLS-1$
    }

    public static void generateTaggedValueTag(final String type) throws IOException {
        XMLBuffer.model.write ("<tagged-value tag-type=\""); //$NON-NLS-1$
        XMLBuffer.model.write (type);
        XMLBuffer.model.write ("\"/>\n"); //$NON-NLS-1$
    }

    public static void generateTaggedValueTagWithParam(final String type, final String value) throws IOException {
        XMLBuffer.model.write ("<tagged-value tag-type=\""); //$NON-NLS-1$
        XMLBuffer.model.write (type);
        XMLBuffer.model.write ("\">\n"); //$NON-NLS-1$
        XMLBuffer.model.write ("<tag-parameter>"); //$NON-NLS-1$
        XMLStringWriter xw = new XMLStringWriter ();
        XMLBuffer.model.write ("<![CDATA[");
        XMLBuffer.model.write (xw.encodedata (value));
        XMLBuffer.model.write ("]");
        XMLBuffer.model.write ("]");
        XMLBuffer.model.write (">");
        XMLBuffer.model.write ("</tag-parameter>\n"); //$NON-NLS-1$
        XMLBuffer.model.write ("</tagged-value>\n"); //$NON-NLS-1$
    }

    public static void generateTaggedValueTagWithParams(final String type, final List<String> values) throws IOException {
        String value;
        XMLBuffer.model.write ("<tagged-value tag-type=\""); //$NON-NLS-1$
        XMLBuffer.model.write (type);
        XMLBuffer.model.write ("\">\n"); //$NON-NLS-1$
        for (Iterator<String> i = values.iterator () ; i.hasNext () ;) {
            value = i.next ();
            XMLBuffer.model.write ("<tag-parameter>"); //$NON-NLS-1$
            XMLStringWriter xw = new XMLStringWriter ();
            XMLBuffer.model.write ("<![CDATA[");
            XMLBuffer.model.write (xw.encodedata (value));
            XMLBuffer.model.write ("]");
            XMLBuffer.model.write ("]");
            XMLBuffer.model.write (">");
            XMLBuffer.model.write ("</tag-parameter>\n"); //$NON-NLS-1$
        }
        XMLBuffer.model.write ("</tagged-value>\n"); //$NON-NLS-1$
    }

    public static void generateDestination(final String id) throws IOException {
        XMLBuffer.model.write ("<destination refid=\""); //$NON-NLS-1$
        XMLBuffer.model.write (id);
        XMLBuffer.model.write ("\"/>\n"); //$NON-NLS-1$
    }

    /**
     * Append the report-list.
     * @throws IOException
     */
    public static void generateReportList() throws IOException {
        XMLBuffer.model.write ("<report-list>\n"); //$NON-NLS-1$
        // Report is not written in this file anymore
        XMLBuffer.model.write ("</report-list>\n"); //$NON-NLS-1$
    }

    /**
     * Append the external references list.
     * @param externalReferences
     * @throws IOException
     */
    public static void generateExternalReferences(final Collection<String> externalReferences) throws IOException {
        // append the list of external elements
        for (String id : externalReferences) {
            XMLBuffer.model.write("<external-element id=\"" + id + "\"/>\n");
        }
    }

    /**
     * Append the external references list.
     * @param externalReferences
     * @throws IOException
     */
    public static void generateFullExternalReferences(final Collection<Entry<Object,String>> externalReferences) throws IOException {
        // append the list of external elements
        for (Entry<Object, String> reference : externalReferences) {
            XMLBuffer.model.write("<external-element id=\"" + reference.getValue() + "\"");
        
            Object key = reference.getKey();
            if (key instanceof ClassifierDef) {
                ClassifierDef classifierDef = (ClassifierDef)key;
                if (classifierDef.getPackageName() != null) {
                    XMLBuffer.model.write (" package=\""); //$NON-NLS-1$
                    XMLBuffer.model.write (classifierDef.getPackageName());
                    XMLBuffer.model.write ("\""); //$NON-NLS-1$
                }
                if (classifierDef.getTypeName() != null) {
                    XMLBuffer.model.write (" class=\""); //$NON-NLS-1$
                    XMLBuffer.model.write (classifierDef.getTypeName());
                    XMLBuffer.model.write ("\""); //$NON-NLS-1$
                }
                switch (classifierDef.getKind()) {
                case CLASS:
                    XMLBuffer.model.write (" metaclass=\""); //$NON-NLS-1$
                    XMLBuffer.model.write ("Class");
                    XMLBuffer.model.write ("\""); //$NON-NLS-1$
                    break;
                case DATATYPE:
                    XMLBuffer.model.write (" metaclass=\""); //$NON-NLS-1$
                    XMLBuffer.model.write ("DataType");
                    XMLBuffer.model.write ("\""); //$NON-NLS-1$
                    break;
                case ENUMERATION:
                    XMLBuffer.model.write (" metaclass=\""); //$NON-NLS-1$
                    XMLBuffer.model.write ("Enumeration");
                    XMLBuffer.model.write ("\""); //$NON-NLS-1$
                    break;
                case INTERFACE:
                    XMLBuffer.model.write (" metaclass=\""); //$NON-NLS-1$
                    XMLBuffer.model.write ("Interface");
                    XMLBuffer.model.write ("\""); //$NON-NLS-1$
                    break;
                case UNKNOWN:
                default:
                    // Nothing to do
                }
            } else if (key instanceof PackageDef) {
                PackageDef packageDef = (PackageDef)key;
                if (packageDef.getFullQualifiedName() != null) {
                    XMLBuffer.model.write (" package=\""); //$NON-NLS-1$
                    XMLBuffer.model.write (packageDef.getFullQualifiedName());
                    XMLBuffer.model.write ("\" metaclass=\""); //$NON-NLS-1$
                    XMLBuffer.model.write ("Package");
                    XMLBuffer.model.write ("\""); //$NON-NLS-1$
                }
            } else {
                System.out.println("Wrong element : " + key);
            }
        
            XMLBuffer.model.write(">\n</external-element>\n");
        }
    }

}
