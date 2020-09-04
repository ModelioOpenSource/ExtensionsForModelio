package org.modelio.module.javadesigner.reverse.javautil;


public class XMLStringWriter {
    /**
     * Don't try to be too smart but at least recognize the predefined entities.
     */
    protected String[] knownEntities = { "gt", "amp", "lt", "apos", "quot" }; // $NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

    private StringBuilder sb = new StringBuilder ();

    /**
     * Escape &lt;, &gt; &amp; &apos;, &quot; as their entities and drop characters that are illegal in XML documents.
     */
    public String encode(final String value) {
        this.sb.setLength (0);
        for (int i = 0 ; i < value.length () ; i++) {
            char c = value.charAt (i);
            switch (c) {
                case '<':
                    this.sb.append ("&lt;"); //$NON-NLS-1$
                    break;
                case '>':
                    this.sb.append ("&gt;"); //$NON-NLS-1$
                    break;
                case '\'':
                    this.sb.append ("&apos;"); //$NON-NLS-1$
                    break;
                case '\"':
                    this.sb.append ("&quot;"); //$NON-NLS-1$
                    break;
                case '&':
                    int nextSemi = value.indexOf (";", i); //$NON-NLS-1$
                    if (nextSemi < 0 ||
                            !isReference (value.substring (i, nextSemi + 1))) {
                        this.sb.append ("&amp;"); //$NON-NLS-1$
                    } else {
                        this.sb.append ('&');
                    }
                    break;
                default:
                    if (isLegalCharacter (c)) {
                        this.sb.append (c);
                    }
                    break;
            }
        }
        return this.sb.toString ();
    }

    /**
     * Drop characters that are illegal in XML documents.
     * 
     * <p>
     * Also ensure that we are not including an <code>]]&gt;</code> marker by replacing that sequence with
     * <code>&amp;#x5d;&amp;#x5d;&amp;gt;</code>.
     * </p>
     * 
     * <p>
     * See XML 1.0 2.2 <a href="http://www.w3.org/TR/1998/REC-xml-19980210#charsets"
     * >http://www.w3.org/TR/1998/REC-xml-19980210 #charsets</a> and 2.7 <a href=
     * "http://www.w3.org/TR/1998/REC-xml-19980210#sec-cdata-sect">http://www .w3.org/TR/1998
     * /REC-xml-19980210#sec-cdata-sect</a>.
     * </p>
     */
    public String encodedata(final String value) {
        this.sb.setLength (0);
        for (int i = 0 ; i < value.length () ; ++i) {
            char c = value.charAt (i);
            if (isLegalCharacter (c)) {
                this.sb.append (c);
            }
        }
        
        String result = this.sb.toString ();
        int cdEnd = result.indexOf ("]]>"); //$NON-NLS-1$ //$NON-NLS-2$
        while (cdEnd != -1) {
            this.sb.setLength (cdEnd);
            this.sb.append ("&#x5d;"); //$NON-NLS-1$
            this.sb.append ("&#x5d;&gt;"); //$NON-NLS-1$
            this.sb.append (result.substring (cdEnd + 3));
            result = this.sb.toString ();
            cdEnd = result.indexOf ("]]>"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return result;
    }

    /**
     * Is the given argument a character or entity reference?
     */
    public boolean isReference(final String ent) {
        if (!(ent.charAt (0) == '&') || !ent.endsWith (";")) { //$NON-NLS-1$
            return false;
        }
        
        if (ent.charAt (1) == '#') {
            if (ent.charAt (2) == 'x') {
                try {
                    Integer.parseInt (ent.substring (3, ent.length () - 1), 16);
                    return true;
                } catch (NumberFormatException nfe) {
                    return false;
                }
            } else {
                try {
                    Integer.parseInt (ent.substring (2, ent.length () - 1));
                    return true;
                } catch (NumberFormatException nfe) {
                    return false;
                }
            }
        }
        
        String name = ent.substring (1, ent.length () - 1);
        for (int i = 0 ; i < this.knownEntities.length ; i++) {
            if (name.equals (this.knownEntities[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is the given character allowed inside an XML document?
     * 
     * <p>
     * See XML 1.0 2.2 <a href="http://www.w3.org/TR/1998/REC-xml-19980210#charsets">
     * http://www.w3.org/TR/1998/REC-xml-19980210#charsets</a>.
     * </p>
     * @since 1.10, Ant 1.5
     */
    public boolean isLegalCharacter(final char c) {
        if (c == 0x9 || c == 0xA || c == 0xD) {
            return true;
        } else if (c < 0x20) {
            return false;
        } else if (c <= 0xD7FF) {
            return true;
        } else if (c < 0xE000) {
            return false;
        } else if (c <= 0xFFFD) {
            return true;
        }
        return false;
    }

}
