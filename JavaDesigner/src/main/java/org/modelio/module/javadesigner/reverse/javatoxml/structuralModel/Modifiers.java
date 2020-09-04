package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel;

import java.util.ArrayList;
import java.util.List;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;

/**
 * Java modifier
 * 
 * Allow a simpler access to modifiers on any Java element.
 */
public class Modifiers {
    private List<String> mods = new ArrayList<>();

    public Modifiers(final ASTTree ast) {
        if (ast == null) {
            // Package visibility
            return;
        }
        assert ast.getType() == JavaParser.MODIFIERS;
        for (ASTTree mod : ast.getChildrenSafe()) {
            this.mods.add(mod.getText());
        }
    }

    public boolean isPublic() {
        return this.mods.contains("public");
    }

    public boolean isProtected() {
        return this.mods.contains("protected");
    }

    public boolean isPrivate() {
        return this.mods.contains("private");
    }

    /**
     * Check if the modifier list contains any visibility.
     * 
     * This help to know if an element is of package visibility.
     * @return true if the modifier list does not contains any visibility
     * modifier. false otherwise.
     */
    public boolean isPackageVisibility() {
        return !isPublic() && !isProtected() && !isPrivate();
    }

    public boolean isStatic() {
        return this.mods.contains("static");
    }

    public boolean isFinal() {
        return this.mods.contains("final");
    }

    public boolean isAbstract() {
        return this.mods.contains("abstract");
    }

    public boolean isTransient() {
        return this.mods.contains("transient");
    }

    public boolean isNative() {
        return this.mods.contains("native");
    }

    public boolean isThreadsafe() {
        return this.mods.contains("threadsafe");
    }

    public boolean isSynchronized() {
        return this.mods.contains("synchronized");
    }

    public boolean isVolatile() {
        return this.mods.contains("volatile");
    }

    public boolean isStrictfp() {
        return this.mods.contains("strictfp");
    }

    public void getXMLAttributeVisibility(final StringBuilder buffer) {
        assert buffer != null;
        if (this.isPublic()) {
            buffer.append(" visibility=\"Public\""); //$NON-NLS-1$
        } else if (this.isProtected()) {
            buffer.append(" visibility=\"Protected\""); //$NON-NLS-1$
        } else if (this.isPrivate()) {
            buffer.append(" visibility=\"Private\""); //$NON-NLS-1$
        } else {
            buffer.append(" visibility=\"Package_Visibility\""); //$NON-NLS-1$
        }
    }

}
