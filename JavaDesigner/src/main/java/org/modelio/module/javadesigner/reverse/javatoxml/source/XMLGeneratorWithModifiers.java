package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.Modifiers;

/**
 * Base class for element that may have modifiers (class, operation, parameter, attribute, etc)
 */
abstract class XMLGeneratorWithModifiers extends XMLGenerator {
    /**
     * Return a Modifiers object build from the ast
     * @param ast the node to get Modifiers from.
     * @return the modifier object even if there is no modifier tag on ast.
     */
    public Modifiers compileModifier(final ASTTree ast) {
        ASTTree mod = (ASTTree)ast.getFirstChildWithType(JavaParser.MODIFIERS);
        return new Modifiers(mod);
    }

    /**
     * Generate XML attributes that depend on mods values
     * @param mods Modifiers
     * @throws java.io.IOException If an I/O error occurs
     */
    protected void generateModifierAttributes(final Modifiers mods) throws IOException {
        XMLBuffer.model.write(this.formatModifierAttributes(mods));
    }

    /**
     * Format modifiers that should appear as XML attributes of the element XML tag
     * Example : For class may return : is-leaf="false" is-abstract="false"
     * @param mods compiled modifiers
     * @return XML formatted attributes to be inserted in the element XML tag
     */
    protected abstract String formatModifierAttributes(final Modifiers mods);

    /**
     * Generate modifiers that should appear as XML tag on their own
     * @param mods : the modifier list
     * @throws java.io.IOException If an I/O error occurs
     */
    protected abstract void generateModifierXMLTags(final Modifiers mods) throws IOException;

}
