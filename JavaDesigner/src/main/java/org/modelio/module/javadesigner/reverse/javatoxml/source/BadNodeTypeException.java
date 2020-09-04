package org.modelio.module.javadesigner.reverse.javatoxml.source;

import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class BadNodeTypeException extends XMLGeneratorException {
    private static final long serialVersionUID = -1484110272412522876L;

     ASTTree ast;

    public BadNodeTypeException(final String s, final ASTTree ast) {
        super (s);
        this.ast = ast;
    }

    public BadNodeTypeException(final String s) {
        super (s);
    }

    public BadNodeTypeException(final ASTTree ast) {
        super ();
        this.ast = ast;
    }

    /**
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        StringBuilder sBuffer = new StringBuilder ();
        if (this.ast != null) {
            sBuffer.append (Messages.getString ("reverse.Unknown_type_node")); //$NON-NLS-1$
            sBuffer.append (Messages.getString ("reverse.Type"));
            sBuffer.append (" = "); //$NON-NLS-1$
            sBuffer.append (this.ast.getType ());
            sBuffer.append (" "); //$NON-NLS-1$
            sBuffer.append (Messages.getString ("reverse.Text")); //$NON-NLS-1$
            sBuffer.append (" = "); //$NON-NLS-1$
            sBuffer.append (this.ast.getText ());
            sBuffer.append (" "); //$NON-NLS-1$
            sBuffer.append (Messages.getString ("reverse.Line")); //$NON-NLS-1$
            sBuffer.append (" = "); //$NON-NLS-1$
            sBuffer.append (this.ast.getLine ());
            sBuffer.append (" "); //$NON-NLS-1$
            sBuffer.append (Messages.getString ("reverse.Column")); //$NON-NLS-1$
            sBuffer.append (" = "); //$NON-NLS-1$
            sBuffer.append (this.ast.getCharPositionInLine ());
        // TODO fja replace the code to catch the file
        //            if (this.ast.getFile() != null) {
        //                sBuffer.append(" "); //$NON-NLS-1$
        //                sBuffer.append(Messages.getString("reverse.File")); //$NON-NLS-1$
        //                sBuffer.append(" = "); //$NON-NLS-1$
        //                sBuffer.append(this.ast.getFile().getAbsoluteFile());
        //            }
        }
        String suite = super.getMessage ();
        if (suite != null) {
            sBuffer.append ("\n"); //$NON-NLS-1$
            sBuffer.append (suite);
        }
        return sBuffer.toString ();
    }

}
