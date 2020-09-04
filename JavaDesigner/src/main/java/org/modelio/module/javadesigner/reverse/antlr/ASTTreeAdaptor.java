package org.modelio.module.javadesigner.reverse.antlr;

import com.modelio.module.xmlreverse.IReportWriter;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.CommonTreeAdaptor;

/**
 * The adaptor required by Antlr V3 when redefining default CommonTree See
 * ASTTree for details.
 */
public class ASTTreeAdaptor extends CommonTreeAdaptor {
    private IReportWriter report;

    /**
     * Default c'tor.
     */
    public ASTTreeAdaptor() {
        super();
    }

    /**
     * C'tor initializing the report writer.
     * @param report
     */
    public ASTTreeAdaptor(final IReportWriter report) {
        this.report = report;
    }

    @Override
    public Object create(final Token payload) {
        return new ASTTree(payload);
    }

    @Override
    public Object errorNode(final TokenStream input, final Token start, final Token stop, final RecognitionException e) {
        CommonErrorNode t = new CommonErrorNode(input, start, stop, e);
        
        if (this.report != null) {
            // FIXME handle parsing errors better...
            String message = "Parsing error in " + input.getSourceName() + " line " + e.line + ":" + e.charPositionInLine;
            this.report.addError(message, null, message);
        }
        return new ASTErrorNode(t);
    }

}
