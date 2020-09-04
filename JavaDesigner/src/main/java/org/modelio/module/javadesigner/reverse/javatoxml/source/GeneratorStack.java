package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PackageDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.StructuralTree;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.Symbol;

/**
 * Stack of elements that are under XML generation
 */
class GeneratorStack {
    /**
     * Current parsed file
     */
    private File currentFile;

    /**
     * Current parsed package : no stack because 'package' statement in Java file can't be nested
     */
    private PackageDef currentPackage = null;

    private ClassifierStack currentTypeStack = new ClassifierStack();

    /**
     * Stack of current Symbol : reflects the current namespacing of the analysis, 'currentTypeStack' being a subset of this one.
     */
    private Deque<Symbol> nsStack = new ArrayDeque<>();

    public ClassifierStack getCurrentTypeStack() {
        return this.currentTypeStack;
    }

    public void clear() {
        this.currentFile = null;
        this.currentPackage = null;
        this.currentTypeStack.clear();
        this.nsStack.clear();
    }

    public void push(final Symbol symbol) {
        this.nsStack.push(symbol);
        // StruturalType are also pushed on their stack to give some context to Structural Model.
        if (symbol instanceof ClassifierDef) {
            this.currentTypeStack.push((ClassifierDef) symbol);
        }
    }

    public void pop() {
        Symbol top = this.nsStack.pop();
        // Keep the currentTypeStack in sync
        if (top == this.currentTypeStack.peek()) {
            this.currentTypeStack.pop();
        }
    }

    public Symbol peek() {
        return this.nsStack.peek();
    }

    public boolean isEmpty() {
        return this.nsStack.isEmpty();
    }

    public PackageDef getCurrentPackage() {
        return this.currentPackage;
    }

    public void setCurrentPackage(final PackageDef pack) {
        this.currentPackage = pack;
    }

    public File getCurrentFile() {
        return this.currentFile;
    }

    public void setCurrentFile(final File curFile) {
        this.currentFile = curFile;
    }

    public ClassifierDef peekType() {
        return this.currentTypeStack.peek();
    }

    /**
     * Get the current structural element
     * the top of the ClassifierDef is not empty and the current package in last resort
     * @return
     */
    public StructuralTree getCurrentStructuralElement() {
        if (!this.currentTypeStack.isEmpty()) {
            return peekType();
        } else {
            return this.currentPackage;
        }
    }

    /**
     * Stack of current ClassifierDef : it reflects the nested state of the
     * current Type (Class, Interface, Enum, Annotation) being XML generated
     */
    public static class ClassifierStack implements Iterable<ClassifierDef> {
        protected Deque<ClassifierDef> stack = new ArrayDeque<>();

        public void clear() {
            this.stack.clear();
        }

        public void push(final ClassifierDef symbol) {
            this.stack.push(symbol);
        }

        public ClassifierDef peek() {
            return this.stack.peek();
        }

        public void pop() {
            this.stack.pop();
        }

        public boolean isEmpty() {
            return this.stack.isEmpty();
        }

        @Override
        public Iterator<ClassifierDef> iterator() {
            return new ClassifierStackIterator();
        }

        public class ClassifierStackIterator implements Iterator<ClassifierDef> {
            private Iterator<ClassifierDef> iter = ClassifierStack.this.stack.iterator();

            public ClassifierStackIterator() {
                // skip the first (last pushed in fact) element because it is the current classifier
                if (this.iter.hasNext()) {
                    this.iter.next();
                }
            }

            @Override
            public boolean hasNext() {
                return this.iter.hasNext();
            }

            @Override
            public ClassifierDef next() {
                return this.iter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        }

    }

}
