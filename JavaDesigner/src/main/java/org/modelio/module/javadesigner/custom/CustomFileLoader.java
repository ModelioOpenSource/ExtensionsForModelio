package org.modelio.module.javadesigner.custom;

import java.util.HashMap;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.module.javadesigner.custom.JavaTypeManager.JavaBaseElements;
import org.modelio.module.javadesigner.custom.JavaTypeManager.JavaMultiplicity;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class CustomFileLoader extends DefaultHandler {
    private String currentPatternType;

    private boolean modelTypeMode;

    private boolean getterMode;

    private boolean isInterfaceContainer;

    private HashMap<String, JavaCustomType> modelTypes;

    private HashMap<JavaBaseElements, JavaElements> javaElements;

    private StringBuilder buffer;

    private JavaCustomType currentType;

    private JavaElements currentElements;

    private JavaElement currentElement;

    private DoubleMap<VisibilityMode,VisibilityMode> propertyVisibilityMap = new DoubleMap<>();

    public CustomFileLoader() {
        super ();
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        // Check the first XML tags
        if (qName.equals ("ModelTypes")) {
            this.modelTypeMode = true;
            return;
        } else if (qName.equals ("Elements")) {
            this.modelTypeMode = false;
            return;
        } else if (qName.equals ("Customization")) {
            // This is the XML root, just ignore it
            return;
        } else if (qName.equals ("Property")) {
            return;
        }
        
        if (this.modelTypeMode) { // Below <ModelTypes>
            if (qName.equals ("ModelType")) {
                this.currentType = new JavaCustomType (attributes.getValue ("name"));
            } else if (qName.equals ("JavaType")) {
                this.currentType.setJavaType (attributes.getValue ("name"));
            } else if (qName.equals ("wrapper")) {
                this.currentType.setWrappedType (attributes.getValue ("name"));
            } else if (qName.equals ("import")) {
                this.currentType.setJavaImport (attributes.getValue ("name"));
            } else {
                // JavaDesignerModule.getInstance().getModuleContext().getLogService().info("(ModelTypeMode) Not handled element: " +
                // qName);
            }
        } else { // Below <Elements>
            if (qName.equals ("Attribute")) {
                this.currentElements = getJavaElementsFromBaseElement (JavaBaseElements.Attribute);
                this.currentElement = getJavaElementForMultiplicity (attributes.getValue ("card"));
            } else if (qName.equals ("AssociationEnd")) {
                this.currentElements = getJavaElementsFromBaseElement (JavaBaseElements.AssociationEnd);
                this.currentElement = getJavaElementForMultiplicity (attributes.getValue ("card"));
            } else if (qName.equals ("IOParameter")) {
                this.currentElements = getJavaElementsFromBaseElement (JavaBaseElements.IOParameter);
                this.currentElement = getJavaElementForMultiplicity (attributes.getValue ("card"));
            } else if (qName.equals ("ReturnParameter")) {
                this.currentElements = getJavaElementsFromBaseElement (JavaBaseElements.ReturnParameter);
                this.currentElement = getJavaElementForMultiplicity (attributes.getValue ("card"));
            } else if (qName.equals ("Class")) {
                this.currentElements = getJavaElementsFromBaseElement (JavaBaseElements.Class);
            } else if (qName.equals ("setter")) {
                this.getterMode = false;
            } else if (qName.equals ("getter")) {
                this.getterMode = true;
            } else if (qName.equals ("defaultPattern")) {
                // Create a new buffer to write the value
                this.buffer = new StringBuilder ();
            } else if (qName.equals ("defaultInterfaceContainer")) {
                this.currentElement.setDefaultInterfaceContainer (attributes.getValue ("name"));
            } else if (qName.equals ("defaultImplementationContainer")) {
                this.currentElement.setDefaultImplementationContainer (attributes.getValue ("name"));
            } else if (qName.equals ("import")) {
                if (this.isInterfaceContainer) {
                    this.currentElement.setDefaultInterfaceContainerImport (attributes.getValue ("name"));
                } else {
                    this.currentElement.setDefaultImplementationContainerImport (attributes.getValue ("name"));
                }
            } else if (qName.equals ("variants")) {
                // There are variants for the current getter or setter.
                // This node is just a container, nothing to do.
            } else if (qName.equals ("pattern")) {
                // Create a new buffer to write the value
                this.buffer = new StringBuilder ();
                // Store the type attribute
                this.currentPatternType = attributes.getValue ("type");
            } else if (qName.equals ("Visibility")) {
                PropertyVisibility modelVisibility = PropertyVisibility.valueOf(attributes.getValue ("model"));
                PropertyVisibility codeVisibility = PropertyVisibility.valueOf(attributes.getValue ("code"));
        
                this.propertyVisibilityMap.put(getObVisibility(modelVisibility), getObVisibility(codeVisibility));
            } else {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error("(ElementsMode) Not handled element: " + qName);
            }
        }
    }

    private JavaElement getJavaElementForMultiplicity(final String value) {
        JavaMultiplicity multiplicity = JavaMultiplicity.valueOf (value);
        JavaElement ret = this.currentElements.getJavaElementForMultiplicity (multiplicity);
        if (ret == null) {
            ret = new JavaElement ();
            this.currentElements.addJavaElementForMultiplicity (multiplicity, ret);
        }
        return ret;
    }

    /**
     * Get the element from the map or create it if necessary.
     * @param baseElement The base element to find.
     */
    private JavaElements getJavaElementsFromBaseElement(final JavaBaseElements baseElement) {
        JavaElements newJavaElement = this.javaElements.get (baseElement);
        
        if (newJavaElement == null) {
            newJavaElement = new JavaElements (baseElement);
        }
        return newJavaElement;
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (this.modelTypeMode) { // Below <ModelTypes>
            if (qName.equals ("ModelType")) {
                this.modelTypes.put (this.currentType.getId (), this.currentType);
                this.currentType = null;
            }
        } else { // Below <Elements>
            if (qName.equals ("Attribute")) {
                this.javaElements.put (JavaBaseElements.Attribute, this.currentElements);
                this.currentElements = null;
            } else if (qName.equals ("AssociationEnd")) {
                this.javaElements.put (JavaBaseElements.AssociationEnd, this.currentElements);
                this.currentElements = null;
            } else if (qName.equals ("IOParameter")) {
                this.javaElements.put (JavaBaseElements.IOParameter, this.currentElements);
                this.currentElements = null;
            } else if (qName.equals ("ReturnParameter")) {
                this.javaElements.put (JavaBaseElements.ReturnParameter, this.currentElements);
                this.currentElements = null;
            } else if (qName.equals ("Class")) {
                this.javaElements.put (JavaBaseElements.Class, this.currentElements);
                this.currentElements = null;
            } else if (qName.equals ("defaultPattern")) {
                // Use the value from the buffer and remove it
                if (this.getterMode) {
                    this.currentElement.setDefaultGetterPattern (this.buffer.toString ());
                } else {
                    this.currentElement.setDefaultSetterPattern (this.buffer.toString ());
                }
                this.buffer = null;
            } else if (qName.equals ("pattern")) {
                // Use the value from the buffer and remove it
                if (this.getterMode) {
                    this.currentElement.addGetterVariant (this.currentPatternType, this.buffer.toString ());
                } else {
                    this.currentElement.addSetterVariant (this.currentPatternType, this.buffer.toString ());
                }
                this.buffer = null;
                this.currentPatternType = null;
            }
        }
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        // Ignore characters if no buffer is available
        if (this.buffer != null) {
            String lecture = new String (ch, start, length);
            this.buffer.append (lecture);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        this.modelTypes = new HashMap<> ();
        this.javaElements = new HashMap<> ();
        this.isInterfaceContainer = true;
    }

    @Override
    public void endDocument() throws SAXException {
        // JavaDesignerModule.getInstance().getModuleContext().getLogService().info("\n*** Default model types ***\n");
        // for(Entry <String, JavaCustomType> modelType :
        // this.modelTypes.entrySet()){
        // JavaDesignerModule.getInstance().getModuleContext().getLogService().info(modelType.getKey() + " -> " +
        // modelType.getValue());
        // }
        //
        // JavaDesignerModule.getInstance().getModuleContext().getLogService().info("\n*** Default java elements ***\n");
        // for(Entry <JavaBaseElements, JavaElements> javaElement :
        // this.javaElements.entrySet()){
        // JavaDesignerModule.getInstance().getModuleContext().getLogService().info(javaElement.getValue());
        // }
    }

    HashMap<String, JavaCustomType> getModelTypes() {
        return this.modelTypes;
    }

    HashMap<JavaBaseElements, JavaElements> getJavaElements() {
        return this.javaElements;
    }

    /**
     * Treat validation errors as fatal
     */
    @Override
    public void error(final SAXParseException e) throws SAXParseException {
        throw e;
    }

    private VisibilityMode getObVisibility(final PropertyVisibility visibility) {
        switch (visibility) {
        case Package:
            return VisibilityMode.PACKAGEVISIBILITY;
        case Private:
            return VisibilityMode.PRIVATE;
        case Protected:
            return VisibilityMode.PROTECTED;
        case Public:
            return VisibilityMode.PUBLIC;
        case Undefined:
            return VisibilityMode.VISIBILITYUNDEFINED;
        default:
            // Should never happen
            return null;
        }
    }

    public DoubleMap<VisibilityMode,VisibilityMode> getPropertyVisibilityMap() {
        return this.propertyVisibilityMap;
    }

    private enum PropertyVisibility {
        Public,
        Protected,
        Private,
        Undefined,
        Package;
    }

}
