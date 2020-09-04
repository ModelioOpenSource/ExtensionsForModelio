package org.modelio.module.javadesigner.custom;

import java.util.HashMap;
import java.util.Map.Entry;

class JavaElement {
    private String defaultGetterPattern;

    private String defaultSetterPattern;

    private String defaultInterfaceContainer;

    private String defaultImplementationContainer;

    private String defaultInterfaceContainerImport;

    private String defaultImplementationContainerImport;

    private HashMap<String, String> getterVariants;

    private HashMap<String, String> setterVariants;

    public JavaElement() {
        this.getterVariants = new HashMap<> ();
        this.setterVariants = new HashMap<> ();
        this.defaultGetterPattern = "";
        this.defaultSetterPattern = "";
        this.defaultInterfaceContainer = "";
        this.defaultImplementationContainer = "";
        this.defaultInterfaceContainerImport = "";
        this.defaultImplementationContainerImport = "";
    }

    private String getDefaultGetterPattern() {
        return this.defaultGetterPattern;
    }

    public String getGetterPattern(final String elementType) {
        String variantPattern = this.getterVariants.get (elementType);
        
        if (variantPattern == null) {
            return getDefaultGetterPattern ();
        }
        return variantPattern;
    }

    public void setDefaultGetterPattern(final String defaultGetterPattern) {
        this.defaultGetterPattern = defaultGetterPattern;
    }

    private String getDefaultSetterPattern() {
        return this.defaultSetterPattern;
    }

    public String getSetterPattern(final String elementType) {
        String variantPattern = this.setterVariants.get (elementType);
        
        if (variantPattern == null) {
            return getDefaultSetterPattern ();
        }
        return variantPattern;
    }

    public void setDefaultSetterPattern(final String defaultSetterPattern) {
        this.defaultSetterPattern = defaultSetterPattern;
    }

    public String getDefaultInterfaceContainer() {
        return this.defaultInterfaceContainer;
    }

    public void setDefaultInterfaceContainer(final String defaultInterfaceContainer) {
        this.defaultInterfaceContainer = defaultInterfaceContainer;
    }

    public String getDefaultImplementationContainer() {
        return this.defaultImplementationContainer;
    }

    public void setDefaultImplementationContainer(final String defaultImplementationContainer) {
        this.defaultImplementationContainer = defaultImplementationContainer;
    }

    public String getDefaultImplementationContainerImport() {
        return this.defaultInterfaceContainerImport;
    }

    public void setDefaultInterfaceContainerImport(final String defaultInterfaceContainerImport) {
        this.defaultInterfaceContainerImport = defaultInterfaceContainerImport;
    }

    public void setDefaultImplementationContainerImport(final String defaultImplementationContainerImport) {
        this.defaultImplementationContainerImport = defaultImplementationContainerImport;
    }

    @Override
    public String toString() {
        String ret = "\tdefaultInterfaceContainer=\"" +
                this.defaultInterfaceContainer + "\"\n";
        ret += "\tdefaultInterfaceContainerImport=\"" +
                this.defaultInterfaceContainerImport + "\"\n";
        ret += "\tdefaultImplementationContainer=\"" +
                this.defaultImplementationContainer + "\"\n";
        ret += "\tdefaultImplementationContainerImport=\"" +
                this.defaultImplementationContainerImport + "\"\n";
        ret += "\tdefaultGetterPattern=\"" + this.defaultGetterPattern + "\"\n";
        for (Entry<String, String> element : this.getterVariants.entrySet ()) {
            ret += "\t" + element.getKey () + " -> " + element.getValue () +
                    "\n";
        }
        ret += "\tdefaultSetterPattern=\"" + this.defaultSetterPattern + "\"\n";
        for (Entry<String, String> element : this.setterVariants.entrySet ()) {
            ret += "\t" + element.getKey () + " -> " + element.getValue () +
                    "\n";
        }
        return ret;
    }

    public void addGetterVariant(final String type, final String variantPattern) {
        this.getterVariants.put (type, variantPattern);
    }

    public void addSetterVariant(final String type, final String variantPattern) {
        this.setterVariants.put (type, variantPattern);
    }

}
