package org.modelio.module.javadesigner.reverse;

import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.reverse.ReverseConfig.GeneralReverseMode;

public class ReverseStrategyConfiguration {
    public boolean DESCRIPTIONASJAVADOC;

    public boolean ERRORONFIRSTWARNING;

    public boolean GENERATEINVARIANTS;

    public boolean GENERATEPREPOSTCONDITIONS;

    public boolean FULLNAMEGENERATION;

    public String GENERATIONMODE;

    public boolean GENERATIONMODE_MODELDRIVEN;

    public boolean GENERATIONMODE_ROUNDTRIP;

    public String INVARIANTSNAME;

    public boolean GENERATEJAVADOC;

    public String ENCODING;

    public String COPYRIGHTFILE;

    public boolean GENERATEFINALPARAMETERS;

    public GeneralReverseMode reverseMode = GeneralReverseMode.COMPLETE_REVERSE;

    private IModuleUserConfiguration javaConfiguration;

    public ReverseStrategyConfiguration(final IModuleUserConfiguration moduleConfiguration) {
        this.javaConfiguration = moduleConfiguration;
        
        this.DESCRIPTIONASJAVADOC = getBooleanParameterValue (JavaDesignerParameters.DESCRIPTIONASJAVADOC);
        this.ERRORONFIRSTWARNING = getBooleanParameterValue (JavaDesignerParameters.ERRORONFIRSTWARNING);
        this.GENERATEINVARIANTS = getBooleanParameterValue (JavaDesignerParameters.GENERATEINVARIANTS);
        this.GENERATEPREPOSTCONDITIONS = getBooleanParameterValue (JavaDesignerParameters.GENERATEPREPOSTCONDITIONS);
        this.FULLNAMEGENERATION = getBooleanParameterValue (JavaDesignerParameters.FULLNAMEGENERATION);
        this.GENERATIONMODE = getStringParameterValue (JavaDesignerParameters.GENERATIONMODE);
        this.GENERATIONMODE_MODELDRIVEN = this.GENERATIONMODE.equals (JavaDesignerParameters.GenerationMode.ModelDriven.toString ());
        this.GENERATIONMODE_ROUNDTRIP = this.GENERATIONMODE.equals (JavaDesignerParameters.GenerationMode.RoundTrip.toString ());
        this.INVARIANTSNAME = getStringParameterValue (JavaDesignerParameters.INVARIANTSNAME);
        this.GENERATEJAVADOC = getBooleanParameterValue (JavaDesignerParameters.GENERATEJAVADOC);
        this.COPYRIGHTFILE = getStringParameterValue (JavaDesignerParameters.COPYRIGHTFILE);
        this.GENERATEFINALPARAMETERS = getBooleanParameterValue (JavaDesignerParameters.GENERATEFINALPARAMETERS);
        
        if (this.INVARIANTSNAME.length () == 0) {
            this.INVARIANTSNAME = "invariant";
        }
        
        try {
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
                break;
            }
        } catch (IllegalArgumentException e) {
            this.ENCODING = "UTF-8";
        }
    }

    private String getStringParameterValue(final String key) {
        return this.javaConfiguration.getParameterValue (key);
    }

    private boolean getBooleanParameterValue(final String key) {
        String value = this.javaConfiguration.getParameterValue (key);
        return "TRUE".equalsIgnoreCase (value);
    }

}
