package org.modelio.module.javadesigner.custom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.module.javadesigner.api.CustomException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.xml.sax.SAXException;

public class JavaTypeManager {
     static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

     static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

     static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    private ArrayList<String> containersWithKeyList;

    private HashMap<String, JavaCustomType> modelTypes;

    private HashMap<JavaBaseElements, JavaElements> javaElements;

    private static final JavaTypeManager INSTANCE = new JavaTypeManager ();

    private DoubleMap<VisibilityMode,VisibilityMode> propertyVisibility;

    private JavaTypeManager() {
        super ();
        
        // Fill the list of containers with two parameters: key, type
        this.containersWithKeyList = new ArrayList<> ();
        this.containersWithKeyList.add ("Map");
        this.containersWithKeyList.add ("SortedMap");
        this.containersWithKeyList.add ("Hashtable");
        this.containersWithKeyList.add ("ConcurrentHashMap");
        this.containersWithKeyList.add ("EnumMap");
        this.containersWithKeyList.add ("HashMap");
        this.containersWithKeyList.add ("IdentityHashMap");
        this.containersWithKeyList.add ("LinkedHashMap");
        this.containersWithKeyList.add ("TreeMap");
        this.containersWithKeyList.add ("WeakHashMap");
    }

    public static JavaTypeManager getInstance() {
        
        return INSTANCE;
    }

    /**
     * Start the XML parsing of the file in argument.
     * @param initialPath The custom file to parse. Might be relative to the module resource path, or use $(Project).
     * @throws org.modelio.module.javadesigner.custom.CustomFileException If an error has occurred during the loading.
     */
    public void loadCustomizationFile(IModule module, String initialPath) throws CustomFileException {
        File configFile;
        
        // Check $(Project)
        if (initialPath.startsWith("$(Project)")) {
            String projectSpacePath = module.getModuleContext().getProjectStructure().getPath().toAbsolutePath().toString();
            configFile = new File(projectSpacePath + initialPath.substring(10));
        } else {
            configFile = new File(initialPath);
        }
        
        // Try a relative file if the given one doesn't exist
        Path resourcePath = module.getModuleContext().getConfiguration().getModuleResourcesPath ();
        if (!configFile.exists()) {
            configFile = new File (resourcePath + File.separator + configFile); //$NON-NLS-1$
        }
        
        // Get the schema file
        File schemaFile = new File (resourcePath +
                File.separator + "res" + //$NON-NLS-1$
                File.separator + "custom" + //$NON-NLS-1$
                File.separator + "customFile.xsd"); //$NON-NLS-1$
        
        loadCustomizationFile (configFile, schemaFile);
    }

    /**
     * Start the XML parsing of the file in argument.
     * @param configFile The file to parse.
     * @param schemaSource The XSD file used for validating.
     * @throws org.modelio.module.javadesigner.custom.CustomFileException If an error has occurred during the loading.
     */
    private void loadCustomizationFile(final File configFile, final File schemaSource) throws CustomFileException {
        // Reset all data
        this.modelTypes = null;
        this.javaElements = null;
        
        // Use an instance of ourselves as the SAX event handler
        CustomFileLoader handler = new CustomFileLoader ();
        
        // Use the validating parser
        SAXParserFactory factory = SAXParserFactory.newInstance ();
        if (schemaSource != null) {
            factory.setValidating (true);
        }
        factory.setNamespaceAware (true);
        
        try {
            // Parse the input
            SAXParser saxParser = factory.newSAXParser ();
        
            if (schemaSource != null) {
                saxParser.setProperty (JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
                saxParser.setProperty (JAXP_SCHEMA_SOURCE, schemaSource);
            }
        
            saxParser.parse (configFile, handler);
        
            // Save data from the handler
            this.modelTypes = handler.getModelTypes ();
            this.javaElements = handler.getJavaElements ();
            this.propertyVisibility = handler.getPropertyVisibilityMap();
        
        } catch (SAXException e) {
            throw new CustomFileException ("Error when parsing file \"" +
                    configFile.getAbsolutePath () + "\": " + e.getMessage ());
        } catch (FileNotFoundException e) {
            throw new CustomFileException ("Customization file not found: \"" +
                    configFile.getAbsolutePath () + "\" does not exist");
        } catch (IOException e) {
            throw new CustomFileException ("Error when loading file: \"" +
                    configFile.getAbsolutePath () + "\"");
        } catch (ParserConfigurationException e) {
            throw new CustomFileException ("Error when loading file: \"" +
                    configFile.getAbsolutePath () + "\"");
        }
    }

    /**
     * Get the equivalent to the base UML type from the current loaded customization file.
     * @param key The base type, corresponding to the UML type in most cases.
     * @param useWrapper Indicates if the type must be wrapped.
     * @return The java translation of the base type.
     * @throws org.modelio.module.javadesigner.custom.CustomFileException If no customization file is loaded.
     */
    private String translateType(final String key, final boolean useWrapper) throws CustomFileException {
        if (this.modelTypes == null) {
            throw new CustomFileException ("No customization file loaded");
        }
        
        JavaCustomType javaType = this.modelTypes.get (key);
        if (javaType != null) {
            // get the translation type from the config file
            return javaType.computeType (useWrapper);
        }
        
        // If no type is found, return nothing
        return "";
    }

    private JavaElement getElementFromTypeAndMultiplicity(final JavaMultiplicity javaMultiplicity, final JavaBaseElements baseElement) throws CustomFileException {
        if (this.javaElements == null) {
            throw new CustomFileException ("No customization file loaded");
        }
        
        JavaElements typeContent = this.javaElements.get (baseElement);
        if (typeContent != null) {
            return typeContent.getJavaElementForMultiplicity (javaMultiplicity);
        }
        return null;
    }

    public String getDefaultInterfaceTranslateClass(final JavaMultiplicity javaMultiplicity, final JavaBaseElements baseElement) throws CustomFileException {
        JavaElement computedElement = getElementFromTypeAndMultiplicity (javaMultiplicity, baseElement);
        if (computedElement != null) {
            return computedElement.getDefaultInterfaceContainer ();
        }
        return "";
    }

    private String getDefaultImplementationTranslateClass(final JavaMultiplicity javaMultiplicity, final JavaBaseElements baseElement) throws CustomFileException {
        JavaElement computedElement = getElementFromTypeAndMultiplicity (javaMultiplicity, baseElement);
        if (computedElement != null) {
            return computedElement.getDefaultImplementationContainer ();
        }
        return "";
    }

    public String getGetterName(final JavaMultiplicity javaMultiplicity, final JavaBaseElements baseElement, final String elementType, final String elementName) throws CustomFileException {
        JavaElement computedElement = getElementFromTypeAndMultiplicity (javaMultiplicity, baseElement);
        if (computedElement != null) {
            return replaceMarkers (computedElement.getGetterPattern (elementType), elementName);
        }
        return "";
    }

    public String getSetterName(final JavaMultiplicity javaMultiplicity, final JavaBaseElements baseElement, final String elementType, final String elementName) throws CustomFileException {
        JavaElement computedElement = getElementFromTypeAndMultiplicity (javaMultiplicity, baseElement);
        if (computedElement != null) {
            return replaceMarkers (computedElement.getSetterPattern (elementType), elementName);
        }
        return "";
    }

    /**
     * Replaces the macros $name and $Name in the pattern.
     * @param pattern
     * @param elementName @return
     */
    private String replaceMarkers(final String pattern, final String elementName) {
        // Replace
        String firstReplace = pattern.replaceAll ("\\$name", elementName);
        String nameWithFirstCapitalized;
        
        if (elementName.length () > 0) {
            nameWithFirstCapitalized = elementName.substring (0, 1).toUpperCase () +
                    elementName.substring (1);
        } else {
            nameWithFirstCapitalized = "";
        }
        return firstReplace.replaceAll ("\\$Name", nameWithFirstCapitalized);
    }

    private JavaMultiplicity computeMultiplicity(final AssociationEnd theAssociationEnd) {
        String multiplicityMin = theAssociationEnd.getMultiplicityMin ();
        String multiplicityMax = theAssociationEnd.getMultiplicityMax ();
        return getJavaMultiplicity (multiplicityMin, multiplicityMax);
    }

    private JavaMultiplicity computeMultiplicity(final Attribute theAttribute) {
        String multiplicityMin = theAttribute.getMultiplicityMin ();
        String multiplicityMax = theAttribute.getMultiplicityMax ();
        return getJavaMultiplicity (multiplicityMin, multiplicityMax);
    }

    private JavaMultiplicity computeMultiplicity(final Parameter theParameter) {
        String multiplicityMin = theParameter.getMultiplicityMin ();
        String multiplicityMax = theParameter.getMultiplicityMax ();
        return getJavaMultiplicity (multiplicityMin, multiplicityMax);
    }

    /**
     * OptionalSimple: 0 - 1 MandatorySimple: 1 - 1 OptionalMultiple: 0 - * MandatoryMultiple: n - * Finite: n - m
     */
    private JavaMultiplicity getJavaMultiplicity(final String multiplicityMin, final String multiplicityMax) {
        JavaMultiplicity lMultiplicity;
        
        if ((multiplicityMin.equals ("0")) && (multiplicityMax.equals ("1"))) {
            lMultiplicity = JavaMultiplicity.OptionalSimple;
        } else if ((multiplicityMin.equals ("1")) &&
                (multiplicityMax.equals ("1"))) {
            lMultiplicity = JavaMultiplicity.MandatorySimple;
        } else if ((multiplicityMin.equals ("0")) &&
                (multiplicityMax.equals ("*"))) {
            lMultiplicity = JavaMultiplicity.OptionalMultiple;
        } else if ((!multiplicityMin.equals ("0")) &&
                (multiplicityMax.equals ("*"))) {
            lMultiplicity = JavaMultiplicity.MandatoryMultiple;
        } else {
            lMultiplicity = JavaMultiplicity.Finite;
        }
        return lMultiplicity;
    }

    public String getDefaultSetterName(final IModelingSession session, final Attribute theAttribute, final boolean generateFullName) {
        String simpleType;
        try {
            simpleType = generateSimpleDeclaration (session, theAttribute, generateFullName).toString ();
        } catch (CustomException e) {
            simpleType = "";
        }
        
        try {
            return getSetterName (computeMultiplicity (theAttribute), JavaBaseElements.Attribute, simpleType, JavaDesignerUtils.getJavaName (theAttribute));
        } catch (CustomFileException e) {
            return "";
        }
    }

    public String getDefaultSetterName(final IModelingSession session, final AssociationEnd theAssociationEnd, final boolean generateFullName) {
        String simpleType;
        try {
            simpleType = generateSimpleDeclaration (session, theAssociationEnd, generateFullName).toString ();
        } catch (CustomException e) {
            simpleType = "";
        }
        
        try {
            return getSetterName (computeMultiplicity (theAssociationEnd), JavaBaseElements.Attribute, simpleType, JavaDesignerUtils.getJavaName (theAssociationEnd));
        } catch (CustomFileException e) {
            return "";
        }
    }

    public String getDefaultGetterName(final IModelingSession session, final Attribute theAttribute, final boolean generateFullName) {
        String simpleType;
        try {
            simpleType = generateSimpleDeclaration (session, theAttribute, generateFullName).toString ();
        } catch (CustomException e) {
            simpleType = "";
        }
        
        try {
            return getGetterName (computeMultiplicity (theAttribute), JavaBaseElements.Attribute, simpleType, JavaDesignerUtils.getJavaName (theAttribute));
        } catch (CustomFileException e) {
            return "";
        }
    }

    public String getDefaultGetterName(final IModelingSession session, final AssociationEnd theAssociationEnd, final boolean generateFullName) {
        String simpleType;
        try {
            simpleType = generateSimpleDeclaration (session, theAssociationEnd, generateFullName).toString ();
        } catch (CustomException e) {
            simpleType = "";
        }
        
        try {
            return getGetterName (computeMultiplicity (theAssociationEnd), JavaBaseElements.Attribute, simpleType, JavaDesignerUtils.getJavaName (theAssociationEnd));
        } catch (CustomFileException e) {
            return "";
        }
    }

    private CharSequence generateSimpleDeclaration(final IModelingSession session, final AssociationEnd theAssociationEnd, final boolean generateFullName) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        TaggedValue tag = theAssociationEnd.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVATYPEEXPR);
        if (tag != null) {
            for (TagParameter tagParameter : tag.getActual ()) {
                ret.append (tagParameter.getValue ());
                break;
            }
        } else {
            Classifier theOtherEnd = theAssociationEnd.getTarget();
            if (theOtherEnd != null) {
                ret.append (getJavaType (session, theOtherEnd, generateFullName, false));
            }
        }
        
        CharSequence bindings = getBindingParameters (theAssociationEnd);
        if (bindings.length () > 0) {
            ret.append ("<");
            ret.append (bindings);
            ret.append (">");
        }
        return ret;
    }

    @SuppressWarnings("deprecation")
    private CharSequence generateSimpleDeclaration(final IModelingSession session, final Attribute theAttribute, final boolean generateFullName) throws CustomException {
        String javaType = "";
        
        // Get the JavaTypeExpr tag value
        TaggedValue tag = theAttribute.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR);
        if (tag != null) {
            for (TagParameter tagParameter : tag.getActual ()) {
                javaType = tagParameter.getValue ();
                break;
            }
        } else {
            // If no tag is found, compute the type
            String typeKey = "";
            boolean useWrapper = useWrapper (theAttribute);
        
            GeneralClass theBaseType = theAttribute.getType ();
            // If no valid tag was found, get the type from the Attribute.
            if (theBaseType != null) {
                typeKey = JavaDesignerUtils.getJavaName (theBaseType);
        
                // Check tags to modify the type key
                if (typeKey.equals ("integer")) {
                    if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                        typeKey = "long";
                    } else if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVASHORT)) {
                        typeKey = "short";
                    } else if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVABYTE)) {
                        typeKey = "byte";
                    }
                } else if (typeKey.equals ("float") ||
                        typeKey.equals ("real")) {
                    if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                        typeKey = "double";
                    } else {
                        typeKey = "float";
                    }
                }
        
                try {
                    // Translate the type
                    javaType = translateType (typeKey, useWrapper);
                } catch (CustomFileException e) {
                    throw new CustomException (e.getMessage ());
                }
            } else {
                throw new CustomException (Messages.getString ("Error.ClassUndefinedTypeAttribute", JavaDesignerUtils.getJavaName (theAttribute.getOwner ()), JavaDesignerUtils.getJavaName (theAttribute)));
            }
        
            // Set the simple element type when no translation was found
            if (javaType.length () == 0 && !typeKey.equals ("undefined")) {
                if (generateFullName) {
                    javaType = JavaDesignerUtils.getFullJavaName (session, theBaseType);
                } else {
                    javaType = typeKey;
                }
            }
        }
        
        CharSequence bindings = getBindingParameters (theAttribute);
        if (bindings.length () > 0) {
            javaType += "<" + bindings + ">";
        }
        return javaType;
    }

    @SuppressWarnings("deprecation")
    private String generateSimpleDeclaration(final IModelingSession session, final Parameter theParameter, final boolean generateFullName) throws CustomException {
        String javaType = "";
        
        // Get the JavaTypeExpr tag value
        TaggedValue tag = theParameter.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVATYPEEXPR);
        if (tag != null) {
            for (TagParameter tagParameter : tag.getActual ()) {
                javaType = tagParameter.getValue ();
                break;
            }
        } else {
            // If no tag is found, compute the type
            String typeKey = "";
            boolean useWrapper = useWrapper (theParameter);
        
            GeneralClass theBaseType = theParameter.getType ();
            // If no valid tag was found, get the type from the Parameter type.
            if (theBaseType != null) {
                typeKey = JavaDesignerUtils.getJavaName (theBaseType);
        
                // Check tags to modify the type key
                if (typeKey.equals ("integer")) {
                    if (theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                        typeKey = "long";
                    } else if (theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVASHORT)) {
                        typeKey = "short";
                    } else if (theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVABYTE)) {
                        typeKey = "byte";
                    }
                } else if (typeKey.equals ("float") ||
                        typeKey.equals ("real")) {
                    if (theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                        typeKey = "double";
                    } else {
                        typeKey = "float";
                    }
                }
        
                try {
                    // Translate the type
                    javaType = translateType (typeKey, useWrapper);
                } catch (CustomFileException e) {
                    throw new CustomException (e.getMessage ());
                }
            } else {
                throw new CustomException (Messages.getString ("Error.ClassUndefinedTypeParameter", JavaDesignerUtils.getJavaName ((ModelElement) theParameter.getCompositionOwner ()), JavaDesignerUtils.getJavaName (theParameter)));
            }
        
            // Set the simple element type when no translation was found
            if (javaType.length () == 0 && !typeKey.equals ("undefined")) {
                if (generateFullName) {
                    javaType = JavaDesignerUtils.getFullJavaName (session, theBaseType);
                } else {
                    javaType = typeKey;
                }
            }
        }
        
        CharSequence bindings = getBindingParameters (theParameter);
        if (bindings.length () > 0) {
            javaType += "<" + bindings + ">";
        }
        return javaType;
    }

    public CharSequence getJavaType(final IModelingSession session, final Classifier baseClass, final boolean generateFullName, final boolean useWrapper) throws CustomException {
        String ret;
        String theBaseType = JavaDesignerUtils.getJavaName (baseClass);
        
        try {
            // Translate the type
            ret = translateType (theBaseType, useWrapper);
        } catch (CustomFileException e) {
            throw new CustomException (e.getMessage ());
        }
        
        if (ret.length () == 0 && !theBaseType.equals ("undefined")) {
            if (generateFullName) {
                ret = JavaDesignerUtils.getFullJavaName (session, baseClass);
            } else {
                ret = theBaseType;
            }
        }
        return ret;
    }

    private CharSequence getBindingParameters(final AssociationEnd theAssociationEnd) {
        StringBuilder ret = new StringBuilder ();
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theAssociationEnd, JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND)) {
            for (Iterator<TagParameter> iterator = tag.getActual ().iterator () ; iterator.hasNext () ;) {
                TagParameter theTagParameter = iterator.next ();
                ret.append (theTagParameter.getValue ());
        
                if (iterator.hasNext ()) {
                    ret.append (",");
                }
            }
        }
        return ret;
    }

    private CharSequence getBindingParameters(final Attribute theAttribute) {
        StringBuilder ret = new StringBuilder ();
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theAttribute, JavaDesignerTagTypes.ATTRIBUTE_JAVABIND)) {
            for (Iterator<TagParameter> iterator = tag.getActual ().iterator () ; iterator.hasNext () ;) {
                TagParameter theTagParameter = iterator.next ();
                ret.append (theTagParameter.getValue ());
        
                if (iterator.hasNext ()) {
                    ret.append (",");
                }
            }
        }
        return ret;
    }

    private boolean useWrapper(final Attribute theAttribute) throws CustomException {
        String MultiplicityMin = theAttribute.getMultiplicityMin ();
        String MultiplicityMax = theAttribute.getMultiplicityMax ();
        
        if ((MultiplicityMin.equals ("0") && MultiplicityMax.equals ("1") && !isArray (theAttribute)) ||
                theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER)) {
            return true;
        }
        return false;
    }

    private boolean useWrapper(final Parameter theParameter) throws CustomException {
        String MultiplicityMin = theParameter.getMultiplicityMin ();
        String MultiplicityMax = theParameter.getMultiplicityMax ();
        
        if ((MultiplicityMin.equals ("0") && MultiplicityMax.equals ("1") && !isArray (theParameter)) ||
                theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVAWRAPPER)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the attribute has a Multiple or Finite mode and no interface defined. Returns also true if the
     * container type is Array.
     */
    public boolean isArray(final AssociationEnd theAssociationEnd) throws CustomException {
        String interfaceType = getInterfaceType (theAssociationEnd);
        
        // Array is set on the type tag
        if (interfaceType.equals ("Array")) {
            return true;
        }
        
        JavaMultiplicity multiplicity = computeMultiplicity (theAssociationEnd);
        switch (multiplicity) {
        case MandatorySimple:
        case OptionalSimple:
            // Simple case, this is not an array
            return false;
        case OptionalMultiple:
        case MandatoryMultiple:
        case Finite:
            // For "multiple" cases, check if an interface is defined on the
            // element
            return interfaceType.length () == 0;
        default:
            return false;
        }
    }

    /**
     * Returns true if the attribute has a Multiple or Finite mode and no interface defined. Returns also true if the
     * container type is Array.
     */
    public boolean isArray(final Attribute theAttribute) throws CustomException {
        String interfaceType = getInterfaceType (theAttribute);
        
        // Array is set on the type tag
        if (interfaceType.equals ("Array")) {
            return true;
        }
        
        JavaMultiplicity multiplicity = computeMultiplicity (theAttribute);
        switch (multiplicity) {
        case MandatorySimple:
        case OptionalSimple:
            // Simple case, this is not an array
            return false;
        case OptionalMultiple:
        case MandatoryMultiple:
        case Finite:
            // For "multiple" cases, check if an interface is defined on the
            // element
            return interfaceType.length () == 0;
        default:
            return false;
        }
    }

    /**
     * Returns true if the parameter has a Multiple or Finite mode and no interface defined. Returns also true if the
     * container type is Array.
     */
    public boolean isArray(final Parameter theParameter) throws CustomException {
        String interfaceType = getInterfaceType (theParameter);
        
        // Array is set on the type tag
        if (interfaceType.equals ("Array")) {
            return true;
        }
        
        JavaMultiplicity multiplicity = computeMultiplicity (theParameter);
        switch (multiplicity) {
        case MandatorySimple:
        case OptionalSimple:
            // Simple case, this is not an array
            return false;
        case OptionalMultiple:
        case MandatoryMultiple:
        case Finite:
            // For "multiple" cases, check if an interface is defined on the
            // element
            return interfaceType.length () == 0;
        default:
            return false;
        }
    }

    public String getImplementationType(final AssociationEnd theAssociationEnd, final String interfaceType) throws CustomException {
        String ret = "";
        
        if (interfaceType.equals ("Array") ||
                (theAssociationEnd.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE) != null) ||
                theAssociationEnd.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
            return ret;
        }
        
        TaggedValue tag = theAssociationEnd.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAIMPLEMENTATIONTYPE);
        if (tag != null) {
            for (TagParameter tagParameter : tag.getActual ()) {
                ret = tagParameter.getValue ();
                break;
            }
        }
        
        if (!this.containersWithKeyList.contains(ret) && theAssociationEnd.getQualifier ().size() > 0) {
            return "HashMap";
        }
        
        // If no tag was found, go to the customization file
        if (ret.length () == 0) {
            try {
                // Translate the type
                ret = getDefaultImplementationTranslateClass (computeMultiplicity (theAssociationEnd), JavaTypeManager.JavaBaseElements.AssociationEnd);
            } catch (CustomFileException e) {
                throw new CustomException (e.getMessage ());
            }
        }
        return ret;
    }

    public String getImplementationType(final Attribute theAttribute, final String interfaceType) throws CustomException {
        String ret = "";
        
        if (interfaceType.equals ("Array") ||
                !theAttribute.getValue ().isEmpty () ||
                theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
            return ret;
        }
        
        TaggedValue tag = theAttribute.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAIMPLEMENTATIONTYPE);
        if (tag != null) {
            for (TagParameter tagParameter : tag.getActual ()) {
                ret = tagParameter.getValue ();
                break;
            }
        }
        
        // If no tag was found, go to the customization file
        if (ret.length () == 0) {
            try {
                // Translate the type
                ret = getDefaultImplementationTranslateClass (computeMultiplicity (theAttribute), JavaTypeManager.JavaBaseElements.Attribute);
            } catch (CustomFileException e) {
                throw new CustomException (e.getMessage ());
            }
        }
        return ret;
    }

    public String getInterfaceType(final AssociationEnd theAssociationEnd) throws CustomException {
        TaggedValue typeTag = theAssociationEnd.getTag (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        
        if (typeTag != null) {
            for (TagParameter tagParameter : typeTag.getActual ()) {
                String type = tagParameter.getValue ();
                if (type.equals ("Array")) { // No interface for Array
                    return "Array";
                } else {
                    return type;
                }
            }
        }
        
        if (theAssociationEnd.getQualifier ().size() > 0) {
            return "Map";
        }
        
        // If no tag was found, go to the customization file
        try {
            // Translate the type
            return getDefaultInterfaceTranslateClass (computeMultiplicity (theAssociationEnd), JavaTypeManager.JavaBaseElements.AssociationEnd);
        } catch (CustomFileException e) {
            throw new CustomException (e.getMessage ());
        }
    }

    public String getInterfaceType(final Attribute theAttribute) throws CustomException {
        TaggedValue typeTag = theAttribute.getTag (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        
        if (typeTag != null) {
            for (TagParameter tagParameter : typeTag.getActual ()) {
                String type = tagParameter.getValue ();
                if (type.equals ("Array")) { // No interface for Array
                    return "Array";
                } else {
                    return type;
                }
            }
        }
        
        // If no tag was found, go to the customization file
        try {
            // Translate the type
            return getDefaultInterfaceTranslateClass (computeMultiplicity (theAttribute), JavaTypeManager.JavaBaseElements.Attribute);
        } catch (CustomFileException e) {
            throw new CustomException (e.getMessage ());
        }
    }

    public String getInterfaceType(final Parameter theParameter) throws CustomException {
        TaggedValue typeTag = theParameter.getTag (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        
        if (typeTag != null) {
            for (TagParameter tagParameter : typeTag.getActual ()) {
                String type = tagParameter.getValue ();
                if (type.equals ("Array")) { // No interface for Array
                    return "Array";
                } else {
                    return type;
                }
            }
        }
        
        // If no tag was found, go to the customization file
        try {
            // Translate the type
            JavaTypeManager.JavaBaseElements paramType;
            if (theParameter.getReturned () == null) {
                paramType = JavaTypeManager.JavaBaseElements.IOParameter;
            } else {
                paramType = JavaTypeManager.JavaBaseElements.ReturnParameter;
            }
        
            return getDefaultInterfaceTranslateClass (computeMultiplicity (theParameter), paramType);
        } catch (CustomFileException e) {
            throw new CustomException (e.getMessage ());
        }
    }

    public String computeInitialValue(final IModelingSession session, final AssociationEnd theAssociationEnd, final boolean generateFullName) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        CharSequence lInitialValue;
        
        String noteContent = theAssociationEnd.getNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE);
        
        if (noteContent != null && noteContent.length () != 0) {
            lInitialValue = " = " + noteContent;
        } else {
            lInitialValue = "";
        }
        
        if (!theAssociationEnd.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
            if (lInitialValue.length () > 0) {
                ret.append (lInitialValue);
            } else {
                String maxSize = theAssociationEnd.getMultiplicityMax ();
                if (isArray (theAssociationEnd)) {
                    if (maxSize.equals ("*")) {
                        //ret.append (" = null");
                    } else {
                        ret.append (" = new ");
                        ret.append (generateSimpleDeclaration (session, theAssociationEnd, generateFullName));
                        ret.append ("[");
                        ret.append (maxSize);
                        ret.append ("]");
                    }
                } else {
                    if (maxSize.equals ("*")) {
                        maxSize = "";
                    }
        
                    String interfaceType = getInterfaceType (theAssociationEnd);
                    if (interfaceType.length () == 0) {
                        //if (!JavaDesignerUtils.isJavaBaseType (computeSimpleType (theAssociationEnd, generateFullName).toString ())) {
                        //    ret.append (" = null");
                        //}
                    } else {
                        String implementationType = getImplementationType (theAssociationEnd, interfaceType);
        
                        ret.append (" = new ");
                        if (implementationType.length () > 0) {
                            ret.append (implementationType);
                        } else {
                            if (interfaceType.equals("List") ||
                                    interfaceType.equals("Set") ||
                                    interfaceType.equals("Map")) {
                                return "";
                            } else {
                                ret.append (interfaceType);
                            }
                        }
                        ret.append (getTemplatePart (session, theAssociationEnd, generateFullName));
                        ret.append (" (");
                        // ret.append (maxSize);
                        ret.append (")");
                    }
                }
            }
        }
        return ret.toString ();
    }

    public CharSequence getTypeDeclaration(final IModelingSession session, final AssociationEnd theAssociationEnd, final boolean generateFullName) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        if (isArray (theAssociationEnd)) {
            ret.append (generateSimpleDeclaration (session, theAssociationEnd, generateFullName));
            ret.append (BracketsDimensions (theAssociationEnd));
            ret.append (" ");
        } else {
            String interfaceType = getInterfaceType (theAssociationEnd);
            if (interfaceType.length () == 0) {
                ret.append (generateSimpleDeclaration (session, theAssociationEnd, generateFullName));
                ret.append (" ");
            } else {
                ret.append (generateContainerDeclaration (session, theAssociationEnd, generateFullName, interfaceType));
            }
        }
        return ret;
    }

    public String computeInitialValue(final IModelingSession session, final Attribute theAttribute, final boolean generateFullName) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        String lInitialValue = "";
        
        if (!theAttribute.isIsDerived ()) {
            String value = theAttribute.getValue ();
            if (value.length () != 0) {
                lInitialValue = " = " + value;
            }
        }
        
        if (!theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
            if (lInitialValue.length () > 0) {
                ret.append (lInitialValue);
            } else {
                String maxSize = theAttribute.getMultiplicityMax ();
                if (isArray (theAttribute)) {
                    if (maxSize.equals ("*")) {
                        //ret.append (" = null");
                    } else {
                        ret.append (" = new ");
                        ret.append (generateSimpleDeclaration (session, theAttribute, generateFullName));
                        ret.append ("[");
                        ret.append (maxSize);
                        ret.append ("]");
                    }
                } else {
                    if (maxSize.equals ("*")) {
                        maxSize = "";
                    }
        
                    String interfaceType = getInterfaceType (theAttribute);
                    if (interfaceType.length () == 0) {
                        //if (!JavaDesignerUtils.isJavaBaseType (computeSimpleType (theAttribute, generateFullName).toString ())) {
                        //    ret.append (" = null");
                        //}
                    } else {
                        String implementationType = getImplementationType (theAttribute, interfaceType);
        
                        ret.append (" = new ");
                        if (implementationType.length () > 0) {
                            ret.append (implementationType);
                        } else {
                            if (interfaceType.equals("List") ||
                                    interfaceType.equals("Set") ||
                                    interfaceType.equals("Map")) {
                                return "";
                            } else {
                                ret.append (interfaceType);
                            }
                        }
                        ret.append (getTemplatePart (session, theAttribute, generateFullName));
                        ret.append (" (");
                        // ret.append (maxSize);
                        ret.append (")");
                    }
                }
            }
        }
        return ret.toString ();
    }

    public CharSequence getTypeDeclaration(final IModelingSession session, final Attribute theAttribute, final boolean generateFullName) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        if (isArray (theAttribute)) {
            TaggedValue typeTag = theAttribute.getTag (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        
            if (typeTag != null) {
                List<TagParameter> params = typeTag.getActual ();
                // append {type} parameters, skipping the first (which is "Array")
                for (int i = 1 ; i < params.size () ; i++) {
                    ret.append (params.get (i).getValue ());
                }
            }
        
            ret.append (generateSimpleDeclaration (session, theAttribute, generateFullName));
            if (ret.length () == 0) {
                throw new CustomException (Messages.getString ("Error.ClassUndefinedTypeAttribute", JavaDesignerUtils.getJavaName (theAttribute.getOwner ()), JavaDesignerUtils.getJavaName (theAttribute)));
            }
        
            ret.append (BracketsDimensions (theAttribute));
            ret.append (" ");
        } else {
            String interfaceType = getInterfaceType (theAttribute);
            if (interfaceType.length () == 0) {
                ret.append (generateSimpleDeclaration (session, theAttribute, generateFullName));
                if (ret.length () == 0) {
                    throw new CustomException (Messages.getString ("Error.ClassUndefinedTypeAttribute", JavaDesignerUtils.getJavaName (theAttribute.getOwner ()), JavaDesignerUtils.getJavaName (theAttribute)));
                }
                ret.append (" ");
            } else {
                ret.append (generateContainerDeclaration (session, theAttribute, generateFullName));
            }
        }
        return ret;
    }

    private CharSequence BracketsDimensions(final AssociationEnd theAssociation) {
        StringBuilder ret = new StringBuilder ();
        int nbDimensions = 1;
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theAssociation, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAARRAYDIMENSION)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                try {
                    nbDimensions = Integer.parseInt (tagParameter.getValue ());
                    break;
                } catch (Exception e) {
                    // Ignore parse error, look for another tag
                }
            }
        }
        
        for (int i = 0 ; i < nbDimensions ; i++) {
            ret.append ("[]");
        }
        return ret;
    }

    private CharSequence BracketsDimensions(final Attribute theAttribute) {
        StringBuilder ret = new StringBuilder ();
        int nbDimensions = 1;
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theAttribute, JavaDesignerTagTypes.ATTRIBUTE_JAVAARRAYDIMENSION)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                try {
                    nbDimensions = Integer.parseInt (tagParameter.getValue ());
                    break;
                } catch (Exception e) {
                    // Ignore parse error, look for another tag
                }
            }
        }
        
        for (int i = 0 ; i < nbDimensions ; i++) {
            ret.append ("[]");
        }
        return ret;
    }

    private CharSequence BracketsDimensions(final Parameter theParameter) {
        StringBuilder ret = new StringBuilder ();
        int nbDimensions = 1;
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theParameter, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAARRAYDIMENSION)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                try {
                    nbDimensions = Integer.parseInt (tagParameter.getValue ());
                    break;
                } catch (Exception e) {
                    // Ignore parse error, look for another tag
                }
            }
        }
        
        for (int i = 0 ; i < nbDimensions ; i++) {
            ret.append ("[]");
        }
        return ret;
    }

    private CharSequence generateContainerDeclaration(final IModelingSession session, final AssociationEnd theAssociationEnd, final boolean generateFullName, final String interfaceType) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (interfaceType);
        ret.append (getTemplatePart (session, theAssociationEnd, generateFullName));
        ret.append (" ");
        return ret;
    }

    private CharSequence generateContainerDeclaration(final IModelingSession session, final Attribute theAttribute, final boolean generateFullName) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        String interfaceType = getInterfaceType (theAttribute);
        
        ret.append (interfaceType);
        ret.append (getTemplatePart (session, theAttribute, generateFullName));
        ret.append (" ");
        return ret;
    }

    private CharSequence getTemplatePart(final IModelingSession session, final AssociationEnd theAssociationEnd, final boolean generateFullName) throws CustomException {
        String keyType = "";
        
        StringBuilder ret = new StringBuilder ();
        
        /*
         * A qualifier serves as a key for maps
         */
        StringBuilder qualifierType = new StringBuilder ();
        for (Attribute qualifier : theAssociationEnd.getQualifier ()) {
            qualifierType.append(this.computeAnnotations(qualifier, false, true));
        
            boolean isFullNameQualifier = qualifier.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFULLNAME);
            qualifierType.append(getTypeDeclaration(session, qualifier, isFullNameQualifier || generateFullName));
        
            // strip the trailing whitespace
            int qtl = qualifierType.length() - 1;
            if (qtl > 0 && qualifierType.charAt(qtl) == ' ') {
                qualifierType = qualifierType.deleteCharAt(qtl);
            }
        }
        
        /* Get the key for map from the type tagged value */
        TaggedValue tag = theAssociationEnd.getTag (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        if (tag != null) {
            List<TagParameter> parameters = tag.getActual ();
            // Key is the second parameter of this tag
            if (parameters.size () > 1) {
                keyType = parameters.get (1).getValue ();
            }
        }
        
        String interfaceType = getInterfaceType (theAssociationEnd);
        
        if (keyType.length () != 0) {
            qualifierType = new StringBuilder(keyType);
        }
        
        StringBuilder typeFound = new StringBuilder ();
        // Get value of the javaTypeExpr tag
        tag = theAssociationEnd.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVATYPEEXPR);
        if (tag != null) {
            for (TagParameter parameter : tag.getActual ()) {
                typeFound.append (parameter.getValue ());
            }
        }
        
        CharSequence bindingParameters = getBindingParameters (theAssociationEnd);
        if (typeFound.length () == 0) { // Nothing found, compute the real type
            if (bindingParameters.length () == 0) {
                typeFound.append (getTemplateType (session, theAssociationEnd, generateFullName));
            } else {
                typeFound.append (generateSimpleDeclaration (session, theAssociationEnd, generateFullName));
            }
        } else {
            if (bindingParameters.length () > 0) {
                typeFound.append ("<");
                typeFound.append (bindingParameters);
                typeFound.append (">");
            }
        }
        
        if (typeFound.length () > 0) {
            ret.append ("<");
        
            // for well known container type, the default key is Object
            if (qualifierType.length () == 0 &&
                    isTypeWithKey (interfaceType)) {
                qualifierType = new StringBuilder("Object");
            }
        
            if (qualifierType.length () != 0) {
                ret.append (qualifierType);
                ret.append (", ");
            }
        
            ret.append (typeFound);
            ret.append (">");
        }
        return ret;
    }

    private CharSequence getTemplatePart(final IModelingSession session, final Attribute theAttribute, final boolean generateFullName) throws CustomException {
        String keyType = "";
        
        StringBuilder ret = new StringBuilder ();
        
        /* Key for map types */
        TaggedValue tag = theAttribute.getTag (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        if (tag != null) {
            List<TagParameter> parameters = tag.getActual ();
            // The key is the second parameter
            if (parameters.size () > 1) {
                keyType = parameters.get (1).getValue ();
            }
        }
        
        String interfaceType = getInterfaceType (theAttribute);
        
        StringBuilder typeFound = new StringBuilder ();
        // Handle the javaTypeExpr tag
        tag = theAttribute.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR);
        if (tag != null) {
            for (TagParameter parameter : tag.getActual ()) {
                typeFound.append (parameter.getValue ());
            }
        }
        
        CharSequence bindingParameters = getBindingParameters (theAttribute);
        
        GeneralClass type = theAttribute.getType ();
        if (tag != null ||
                (type != null && !type.getName ().equals ("undefined"))) {
            if (typeFound.length () == 0) { // Nothing found, compute the real type
                if (bindingParameters.length () == 0) {
                    typeFound.append (getTemplateType (session, theAttribute, generateFullName));
                } else {
                    typeFound.append (generateSimpleDeclaration (session, theAttribute, generateFullName));
                }
            } else {
                if (bindingParameters.length () > 0) {
                    typeFound.append ("<");
                    typeFound.append (bindingParameters);
                    typeFound.append (">");
                }
            }
        
            if (typeFound.length () > 0) {
                ret.append ("<");
                // for well known container type, the default key is Object
                if (keyType.length () == 0 &&
                        isTypeWithKey (interfaceType)) {
                    keyType = "Object";
                }
        
                if (!keyType.isEmpty ()) {
                    ret.append (keyType);
                    ret.append (", ");
                }
                ret.append (typeFound);
        
                ret.append (">");
            }
        } else {
            // for well known container type, the default key is Object
            if (keyType.length () == 0 &&
                    isTypeWithKey (interfaceType) && bindingParameters.length () > 0) {
                keyType = "Object";
            }
        
            // There is no type, only template bindings
            if (!keyType.isEmpty ()) {
                ret.append ("<");
                ret.append (keyType);
                ret.append (", ");
        
                if (bindingParameters.length () > 0) {
                    ret.append (bindingParameters);
                }
        
                ret.append (">");
            } else if (bindingParameters.length () > 0) {
                ret.append ("<");
                ret.append (bindingParameters);
                ret.append (">");
            }
        }
        return ret;
    }

    private boolean isTypeWithKey(final String interfaceType) {
        
        return this.containersWithKeyList.contains (interfaceType);
    }

    private CharSequence getTemplatePart(final IModelingSession session, final Parameter theParameter, final boolean generateFullName) throws CustomException {
        String keyType = "";
        
        StringBuilder ret = new StringBuilder ();
        
        /* Key for map types */
        TaggedValue tag = theParameter.getTag (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        if (tag != null) {
            List<TagParameter> parameters = tag.getActual ();
            // The key is the second parameter
            if (parameters.size () > 1) {
                keyType = parameters.get (1).getValue ();
            }
        }
        
        String interfaceType = getInterfaceType (theParameter);
        
        StringBuilder typeFound = new StringBuilder ();
        // Handle the javaTypeExpr tag
        tag = theParameter.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVATYPEEXPR);
        if (tag != null) {
            for (TagParameter parameter : tag.getActual ()) {
                typeFound.append (parameter.getValue ());
            }
        }
        
        CharSequence bindingParameters = getBindingParameters (theParameter);
        GeneralClass type = theParameter.getType ();
        if (tag != null ||
                (type != null && !type.getName ().equals ("undefined"))) {
            if (typeFound.length () == 0) { // Nothing found, compute the real type
                // reel
                if (bindingParameters.length () == 0) {
                    typeFound.append (getTemplateType (session, theParameter, generateFullName));
                } else {
                    typeFound.append (generateSimpleDeclaration (session, theParameter, generateFullName));
                }
            } else {
                if (bindingParameters.length () > 0) {
                    typeFound.append ("<");
                    typeFound.append (bindingParameters);
                    typeFound.append (">");
                }
            }
        
            if (typeFound.length () > 0) {
                // for well known container type, the default key is Object
                if (keyType.length () == 0 &&
                        isTypeWithKey (interfaceType)) {
                    keyType = "Object";
                }
        
                ret.append ("<");
                if (keyType.length () != 0) {
                    ret.append (keyType);
                    ret.append (", ");
                }
                ret.append (typeFound);
                ret.append (">");
            }
        } else {
            // for well known container type, the default key is Object
            if (keyType.length () == 0 &&
                    isTypeWithKey (interfaceType) && bindingParameters.length () > 0) {
                keyType = "Object";
            }
        
            // There is no type, only tempate bindings
            if (!keyType.isEmpty ()) {
                ret.append ("<");
                ret.append (keyType);
                ret.append (", ");
        
                if (bindingParameters.length () > 0) {
                    ret.append (bindingParameters);
                }
        
                ret.append (">");
            } else if (bindingParameters.length () > 0) {
                ret.append ("<");
                ret.append (bindingParameters);
                ret.append (">");
            }
        }
        return ret;
    }

    public CharSequence computeSimpleType(final IModelingSession session, final AssociationEnd theAssociationEnd, final boolean generateFullName) throws CustomException {
        
        return getJavaType (session, theAssociationEnd.getTarget(), generateFullName, false);
    }

    @SuppressWarnings("deprecation")
    public CharSequence computeSimpleType(final IModelingSession session, final Attribute theAttribute, final boolean generateFullName) throws CustomException {
        if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR)) {
            return ModelUtils.getFirstTagParameter(theAttribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR);
        }
        
        GeneralClass attType = theAttribute.getType ();
        if (attType != null) {
            String typeKey = JavaDesignerUtils.getJavaName (attType);
        
            IUMLTypes umlTypes = session.getModel().getUmlTypes();
        
            // Check tags to modify the type key
            if (typeKey.equals ("integer")) {
                if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                    attType = umlTypes.getLONG();
                } else if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVASHORT)) {
                    attType = umlTypes.getSHORT();
                } else if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVABYTE)) {
                    attType = umlTypes.getBYTE();
                }
            } else if (typeKey.equals ("float") ||
                    typeKey.equals ("real")) {
                if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                    attType = umlTypes.getDOUBLE();
                } else {
                    attType = umlTypes.getFLOAT();
                }
            }
        
            return getJavaType (session, attType, generateFullName, theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER));
        } else {
            return "";
        }
    }

    @SuppressWarnings("deprecation")
    public CharSequence computeSimpleType(final IModelingSession session, final Parameter parameter, final boolean generateFullName) throws CustomException {
        Operation methode = parameter.getReturned ();
        if (methode == null) {
            methode = parameter.getComposed ();
        }
        
        StringBuilder type = new StringBuilder();
        TaggedValue tag = parameter.getTag(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVATYPEEXPR);
        if (tag != null) {
            for (TagParameter tagParam : tag.getActual ()) {
                type.append (tagParam.getValue ());
            }
        }
        
        if (type.length () == 0) {
            GeneralClass paramType = parameter.getType ();
            if (paramType != null) {
                String typeKey = JavaDesignerUtils.getJavaName (paramType);
        
                IUMLTypes umlTypes = session.getModel().getUmlTypes();
        
                // Check tags to modify the type key
                if (typeKey.equals ("integer")) {
                    if (parameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                        paramType = umlTypes.getLONG();
                    } else if (parameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVASHORT)) {
                        paramType = umlTypes.getSHORT();
                    } else if (parameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVABYTE)) {
                        paramType = umlTypes.getBYTE();
                    }
                } else if (typeKey.equals ("float") ||
                        typeKey.equals ("real")) {
                    if (parameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                        paramType = umlTypes.getDOUBLE();
                    } else {
                        paramType = umlTypes.getFLOAT();
                    }
                }
        
                type.append(getJavaType (session, paramType, generateFullName, parameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVAWRAPPER)));
            }
        }
        return type;
    }

    private CharSequence getTemplateType(final IModelingSession session, final AssociationEnd theAssociationEnd, final boolean generateFullName) throws CustomException {
        
        return getTemplateJavaType (session, theAssociationEnd.getTarget(), generateFullName);
    }

    @SuppressWarnings("deprecation")
    private CharSequence getTemplateType(final IModelingSession session, final Attribute theAttribute, final boolean generateFullName) throws CustomException {
        IUMLTypes umlTypes = session.getModel().getUmlTypes();
        
        GeneralClass type = theAttribute.getType ();
        // Check tags to modify the type key
        if (type.equals (umlTypes.getINTEGER())) {
            if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                type = umlTypes.getLONG();
            } else if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVASHORT)) {
                type = umlTypes.getSHORT();
            } else if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVABYTE)) {
                type = umlTypes.getBYTE();
            }
        } else if (type.equals (umlTypes.getFLOAT())) {
            if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                type = umlTypes.getDOUBLE();
            } else {
                type = umlTypes.getFLOAT();
            }
        }
        return getTemplateJavaType (session, type, generateFullName);
    }

    @SuppressWarnings("deprecation")
    private CharSequence getTemplateType(final IModelingSession session, final Parameter theParameter, final boolean generateFullName) throws CustomException {
        IUMLTypes umlTypes = session.getModel().getUmlTypes();
        
        GeneralClass type = theParameter.getType ();
        // Check tags to modify the type key
        if (type.equals (umlTypes.getINTEGER())) {
            if (theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                type = umlTypes.getLONG();
            } else if (theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVASHORT)) {
                type = umlTypes.getSHORT();
            } else if (theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVABYTE)) {
                type = umlTypes.getBYTE();
            }
        } else if (type.equals (umlTypes.getFLOAT())) {
            if (theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVALONG)) {
                type = umlTypes.getDOUBLE();
            } else {
                type = umlTypes.getFLOAT();
            }
        }
        return getTemplateJavaType (session, type, generateFullName);
    }

    private CharSequence getTemplateJavaType(final IModelingSession session, final Classifier theType, final boolean generateFullName) throws CustomException {
        String lElementName;
        
        if (theType != null) {
            lElementName = JavaDesignerUtils.getJavaName (theType);
        } else {
            lElementName = "";
        }
        
        String ret;
        try {
            ret = translateType (lElementName, true);
        } catch (CustomFileException e) {
            throw new CustomException (e.getMessage ());
        }
        
        if (ret.length () == 0 && !lElementName.equals ("undefined")) {
            if (generateFullName) {
                ret = JavaDesignerUtils.getFullJavaName (session, theType);
            } else {
                ret = lElementName;
            }
        }
        return ret;
    }

    private CharSequence getBindingParameters(final Parameter theParameter) {
        StringBuilder ret = new StringBuilder ();
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theParameter, JavaDesignerTagTypes.PARAMETER_JAVABIND)) {
            for (Iterator<TagParameter> iterator = tag.getActual ().iterator () ; iterator.hasNext () ;) {
                TagParameter theTagParameter = iterator.next ();
                ret.append (theTagParameter.getValue ());
        
                if (iterator.hasNext ()) {
                    ret.append (",");
                }
            }
        }
        return ret;
    }

    public CharSequence getTypeDeclaration(final IModelingSession session, final Parameter theParameter, final boolean generateFullName) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        String interfaceType = getInterfaceType (theParameter);
        if (isArray (theParameter)) {
            TaggedValue typeTag = theParameter.getTag (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        
            if (typeTag != null) {
                List<TagParameter> params = typeTag.getActual ();
                for (int i = 1 ; i < params.size () ; i++) {
                    ret.append (params.get (i).getValue ());
                }
            }
        
            ret.append (generateSimpleDeclaration (session, theParameter, generateFullName));
            if (ret.length () == 0) {
                throw new CustomException (Messages.getString ("Error.ClassUndefinedTypeParameter", JavaDesignerUtils.getJavaName ((ModelElement) theParameter.getCompositionOwner ()), JavaDesignerUtils.getJavaName (theParameter)));
            }
            ret.append (BracketsDimensions (theParameter));
        } else {
            if (interfaceType.length () == 0) {
                ret.append (generateSimpleDeclaration (session, theParameter, generateFullName));
                if (ret.length () == 0) {
                    throw new CustomException (Messages.getString ("Error.ClassUndefinedTypeParameter", JavaDesignerUtils.getJavaName ((ModelElement) theParameter.getCompositionOwner ()), JavaDesignerUtils.getJavaName (theParameter)));
                }
            } else {
                ret.append (interfaceType);
                ret.append (getTemplatePart (session, theParameter, generateFullName));
            }
        }
        ret.append(computeVarArgs(theParameter));
        return ret;
    }

    public VisibilityMode getPropertyModelVisibility(final VisibilityMode codeVisibility) {
        VisibilityMode ret;
        
        List<VisibilityMode> keys = this.propertyVisibility.keysForValue(codeVisibility);
        if (keys == null) {
            // TODO throw exception
            ret = null;
        } else if (keys.size() == 1) {
            ret = keys.get(0);
        } else {
            ret = keys.get(0);
            for (VisibilityMode key : keys) {
                if (key.ordinal() < ret.ordinal()) {
                    ret = key;
                }
            }
        }
        return ret;
    }

    public VisibilityMode getPropertyCodeVisibility(final VisibilityMode modelValue) {
        
        return this.propertyVisibility.get(modelValue);
    }

    private CharSequence computeVarArgs(final Parameter parameter) {
        StringBuilder ret = new StringBuilder ();
        if (parameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVAVARARGS)) {
            ret.append ("...");
        }
        return ret;
    }

    public CharSequence computeAnnotations(final ModelElement theElement, final boolean withSpaceInFront, final boolean withSpaceAfter) {
        /*
         * This method is in this class only because this class needs it (along with ClassTemplate)
         */
        StringBuffer ret = new StringBuffer();
        List<Note> annots =  ModelUtils.getAllNotes (theElement, JavaDesignerNoteTypes.PARAMETER_JAVAANNOTATION);
        int asize = annots.size();
        if (withSpaceInFront && asize > 0) {
            ret.append(" ");
        }
        int idx = 1;
        for (Note note : annots) {
            ret.append(note.getContent().replace('\n',  ' '));
            if (idx++ < asize) {
                ret.append(" ");
            }
        }
        if (withSpaceAfter && idx > 1) {
            ret.append(" ");
        }
        return ret;
    }

    enum JavaBaseElements {
        Attribute,
        AssociationEnd,
        IOParameter,
        ReturnParameter,
        Class;
    }

    /**
     * OptionalSimple: 0 - 1 MandatorySimple: 1 - 1 OptionalMultiple: 0 - * MandatoryMultiple: n - * Finite: n - m
     */
    enum JavaMultiplicity {
        OptionalSimple,
        MandatorySimple,
        OptionalMultiple,
        MandatoryMultiple,
        Finite;
    }

}
