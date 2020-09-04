package org.modelio.module.javadesigner.api;


public interface JavaDesignerParameters {
    public static final String ACCESSIBLECLASSES = "AccessibleClasses";

    public static final String ACCESSORSGENERATION = "AccessorsGeneration";

    public static final String AUTOMATICALLYOPENJAVADOC = "AutomaticallyOpenJavadoc";

    public static final String COMPILATIONOPTIONS = "CompilationOptions";

    public static final String COMPILATIONPATH = "CompilationPath";

    public static final String CUSTOMIZATIONFILE = "CustomizationFile";

    public static final String DESCRIPTIONASJAVADOC = "DescriptionAsJavadoc";

    public static final String ENCODING = "Encoding";

    public static final String ERRORONFIRSTWARNING = "ErrorOnFirstWarning";

    public static final String EXTERNALEDITORCOMMANDLINE = "ExternalEditorCommandLine";

    public static final String FRIENDLYACCESSORVISIBILITY = "FriendlyAccessorVisibility";

    public static final String FRIENDLYMODIFIERVISIBILITY = "FriendlyModifierVisibility";

    public static final String FULLNAMEGENERATION = "FullNameGeneration";

    public static final String GENDOCPATH = "GenDocPath";

    public static final String GENERATEDEFAULTRETURN = "GenerateDefaultReturn";

    public static final String GENERATEFINALPARAMETERS = "GenerateFinalParameters";

    public static final String GENERATEINVARIANTS = "GenerateInvariants";

    public static final String GENERATEJAVADOC = "GenerateJavadoc";

    public static final String GENERATEJAVADOC_MARKERS = "GenerateJavadocMarkers";

    public static final String GENERATEPREPOSTCONDITIONS = "GeneratePrePostConditions";

    public static final String GENERATIONMODE = "GenerationMode";

    public static final String GENERATIONPATH = "GenerationPath";

    public static final String INTERFACEIMPLEMENTATION = "InterfaceImplementation";

    public static final String INVARIANTSNAME = "InvariantsName";

    public static final String JARFILEPATH = "JarFilePath";

    public static final String JAVADOCOPTIONS = "JavaDocOptions";

    public static final String JAVAHGENERATIONPATH = "JavahGenerationPath";

    public static final String JDKPATH = "JDKPath";

    public static final String PACKAGEJARINRAMC = "PackageJarInRamc";

    public static final String PACKAGESRCINRAMC = "PackageSrcInRamc";

    public static final String PRIVATEACCESSORVISIBILITY = "PrivateAccessorVisibility";

    public static final String PRIVATEMODIFIERVISIBILITY = "PrivateModifierVisibility";

    public static final String PROTECTEDACCESSORVISIBILITY = "ProtectedAccessorVisibility";

    public static final String PROTECTEDMODIFIERVISIBILITY = "ProtectedModifierVisibility";

    public static final String PUBLICACCESSORVISIBILITY = "PublicAccessorVisibility";

    public static final String PUBLICMODIFIERVISIBILITY = "PublicModifierVisibility";

    public static final String RETRIEVEDEFAULTBEHAVIOUR = "RetrieveDefaultBehaviour";

    public static final String RUNPARAMETERS = "RunParameters";

    public static final String USEEXTERNALEDITION = "UseExternalEdition";

    public static final String USEJAVAH = "UseJavah";

    public static final String COPYRIGHTFILE = "CopyrightFile";

    public static final String COMPONENTSUBPATH = "ComponentSubPath";

    public static final String JAVACOMPATIBILITY = "JavaCompatibility";

    enum AccessorsGenerationMode {
        Always,
        Smart,
        Never;
    }

    enum InterfaceImplementationMode {
        Ask,
        Always,
        Never;
    }

    enum AccessorVisibility {
        Public,
        Protected,
        Friendly,
        Private;
    }

    enum AccessMode {
        Read,
        Write,
        ReadWrite,
        None,
        Default;
    }

    enum DefaultVisibility {
        Public,
        Protected,
        Friendly,
        Private,
        Default;
    }

    enum RetrieveMode {
        Ask,
        Retrieve,
        Keep;

        public static RetrieveMode fromString(String value) {
            if (value.contentEquals (JavaDesignerParameters.RetrieveMode.Retrieve.toString ())) {
                return RetrieveMode.Retrieve;
            } else if (value.contentEquals (JavaDesignerParameters.RetrieveMode.Keep.toString ())) {
                return RetrieveMode.Keep;
            } else {
                return RetrieveMode.Ask;
            }
        }

    }

    enum GenerationMode {
        RoundTrip,
        ModelDriven,
        Release;
    }

    enum Encodings {
        ISO_8859_1,
        US_ASCII,
        UTF_8,
        UTF_16BE,
        UTF_16LE,
        UTF_16;
    }

    enum CompatibilityLevel {
        Java7,
        Java8;
    }

}
