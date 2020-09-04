package org.modelio.module.javadesigner.generator;

import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;

class JavaConfiguration {
    public boolean DESCRIPTIONASJAVADOC;

    public boolean ERRORONFIRSTWARNING;

    public boolean GENERATEDEFAULTRETURN;

    public boolean GENERATEFINALPARAMETERS;

    public boolean GENERATEINVARIANTS;

    public boolean GENERATEPREPOSTCONDITIONS;

    public boolean FULLNAMEGENERATION;

    public String GENERATIONMODE;

    public boolean GENERATIONMODE_MODELDRIVEN;

    public boolean GENERATIONMODE_ROUNDTRIP;

    public String INVARIANTSNAME;

    public boolean GENERATEJAVADOC;
    
    /**
     * TAS added: do not generate JavaDoc markers in ModelDriven.
     */
    public boolean GENERATEJAVADOC_MARKERS = false;

    public String ENCODING;

    public String COPYRIGHTFILE;

    public boolean JAVA8COMPATIBILITY;

    private IModuleUserConfiguration javaConfiguration;

    public JavaConfiguration(IModuleUserConfiguration moduleConfiguration) {
        this.javaConfiguration = moduleConfiguration;
        
        this.DESCRIPTIONASJAVADOC = getBooleanParameterValue (JavaDesignerParameters.DESCRIPTIONASJAVADOC);
        this.ERRORONFIRSTWARNING = getBooleanParameterValue (JavaDesignerParameters.ERRORONFIRSTWARNING);
        this.GENERATEDEFAULTRETURN = getBooleanParameterValue (JavaDesignerParameters.GENERATEDEFAULTRETURN);
        this.GENERATEFINALPARAMETERS = getBooleanParameterValue (JavaDesignerParameters.GENERATEFINALPARAMETERS);
        this.GENERATEINVARIANTS = getBooleanParameterValue (JavaDesignerParameters.GENERATEINVARIANTS);
        this.GENERATEPREPOSTCONDITIONS = getBooleanParameterValue (JavaDesignerParameters.GENERATEPREPOSTCONDITIONS);
        this.FULLNAMEGENERATION = getBooleanParameterValue (JavaDesignerParameters.FULLNAMEGENERATION);
        this.GENERATIONMODE = getStringParameterValue (JavaDesignerParameters.GENERATIONMODE);
        this.GENERATIONMODE_MODELDRIVEN = this.GENERATIONMODE.equals (JavaDesignerParameters.GenerationMode.ModelDriven.toString ());
        this.GENERATIONMODE_ROUNDTRIP = this.GENERATIONMODE.equals (JavaDesignerParameters.GenerationMode.RoundTrip.toString ());
        this.INVARIANTSNAME = getStringParameterValue (JavaDesignerParameters.INVARIANTSNAME);
        this.GENERATEJAVADOC = getBooleanParameterValue (JavaDesignerParameters.GENERATEJAVADOC);
        // TAS added: do not generate JavaDoc markers in ModelDriven.
        this.GENERATEJAVADOC_MARKERS = getBooleanParameterValue (JavaDesignerParameters.GENERATEJAVADOC_MARKERS);
        this.COPYRIGHTFILE = getStringParameterValue (JavaDesignerParameters.COPYRIGHTFILE);
        this.JAVA8COMPATIBILITY = getStringParameterValue (JavaDesignerParameters.JAVACOMPATIBILITY).equals (JavaDesignerParameters.CompatibilityLevel.Java8.toString ());
        
        if (this.INVARIANTSNAME.length () == 0) {
            this.INVARIANTSNAME = "invariant";
        }
        
            switch (getStringParameterValue (JavaDesignerParameters.ENCODING)) {
            case "ISO_8859_1":
            case "ISO-8859-1":
                this.ENCODING = "ISO-8859-1";
                break;
            case "US_ASCII":
            case "US-ASCII":
                this.ENCODING = "US-ASCII";
                break;
            case "UTF_16":
            case "UTF-16":
                this.ENCODING = "UTF-16";
                break;
            case "UTF_16BE":
            case "UTF-16BE":
                this.ENCODING = "UTF-16BE";
                break;
            case "UTF_16LE":
            case "UTF-16LE":
                this.ENCODING = "UTF-16LE";
                break;
            case "UTF_8":
            case "UTF-8":
                default:
                    this.ENCODING = "UTF-8";
        }
    }

    private String getStringParameterValue(final String key) {
        String parameterValue = this.javaConfiguration.getParameterValue (key);
        return parameterValue != null ? parameterValue : "";
    }

    private boolean getBooleanParameterValue(final String key) {
        String value = this.javaConfiguration.getParameterValue (key);
        return "TRUE".equalsIgnoreCase (value);
    }

    public IModuleUserConfiguration getJavaConfiguration() {
        return this.javaConfiguration;
    }

}
