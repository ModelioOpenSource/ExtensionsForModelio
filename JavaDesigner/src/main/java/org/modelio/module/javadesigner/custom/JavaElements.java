package org.modelio.module.javadesigner.custom;

import java.util.HashMap;
import java.util.Map.Entry;
import org.modelio.module.javadesigner.custom.JavaTypeManager.JavaBaseElements;
import org.modelio.module.javadesigner.custom.JavaTypeManager.JavaMultiplicity;

class JavaElements {
    private JavaBaseElements baseElement;

    private HashMap<JavaMultiplicity, JavaElement> elements;

    public JavaElements(final JavaBaseElements baseElement) {
        this.elements = new HashMap<> ();
        this.baseElement = baseElement;
    }

    public JavaElement getJavaElementForMultiplicity(final JavaMultiplicity multiplicity) {
        return this.elements.get (multiplicity);
    }

    public void addJavaElementForMultiplicity(final JavaMultiplicity multiplicity, final JavaElement element) {
        this.elements.put (multiplicity, element);
    }

    public JavaBaseElements getBaseElement() {
        return this.baseElement;
    }

    @Override
    public String toString() {
        String ret = this.baseElement + "\n";
        
        for (Entry<JavaMultiplicity, JavaElement> element : this.elements.entrySet ()) {
            ret += element.getKey () + ": \n" + element.getValue ();
        }
        return ret;
    }

}
