package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class InstanceXMLGenerator extends XMLGenerator {
    /**
     * (non-Javadoc)
     * @see org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator#generateXML(ASTTree, Context)
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws BadNodeTypeException, IOException, XMLGeneratorException {
        if (ast.getType () != JavaParser.INSTANCE_INIT) {
            throw new BadNodeTypeException (Messages.getString ("reverse.Node_must_be_INSTANCE_INIT"), ast); //$NON-NLS-1$
        }
        
        GeneratorUtils.generateNoteTag ("JavaMembers", ast.getSourceCode()); //$NON-NLS-1$
        
        //TODO fja add the comment        
        
        //        ASTTree child = (ASTTree) ast.getFirstChild ();
        //        StringBuilder content = new StringBuilder ();
        //        
        //        while (child != null) {
        //            if (child.getType () == JavaParser.COMMENTS) {
        //                content.append (GeneratorUtils.getCommentsDef (child, false));
        //            } else if (child.getType () == JavaParser.SLIST) {
        //                child.setFile (ast.getFile ());
        //                content.append ("{" + GeneratorUtils.getBodyCode (child) + "}\n"); //$NON-NLS-1$ //$NON-NLS-2$
        //                GeneratorUtils.generateNoteTag ("JavaMembers", content); //$NON-NLS-1$
        //            }
        //            child = (ASTTree) child.getNextSibling ();
        //        }
    }

}
