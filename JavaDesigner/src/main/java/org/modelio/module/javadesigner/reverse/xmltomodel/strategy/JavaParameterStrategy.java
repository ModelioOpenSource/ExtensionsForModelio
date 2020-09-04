package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.ArrayList;
import java.util.List;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.IReportWriter;
import com.modelio.module.xmlreverse.model.JaxbParameter;
import com.modelio.module.xmlreverse.model.JaxbTaggedValue;
import com.modelio.module.xmlreverse.strategy.ParameterStrategy;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.ReverseStrategyConfiguration;
import org.modelio.module.javadesigner.reverse.xmltomodel.JavaFeatureReverseUtils;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaParameterStrategy extends ParameterStrategy {
    private boolean isInParameterFinalGenerationActive;

    public JavaParameterStrategy(final IModelingSession session, final ReverseStrategyConfiguration config) {
        super (session);
        this.isInParameterFinalGenerationActive = config.GENERATEFINALPARAMETERS;
    }

    @Override
    public List<MObject> updateProperties(final JaxbParameter jaxb_element, final Parameter modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        String oldName = modelio_element.getName ();
        boolean hasJavaName = modelio_element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME);
        
        String name = jaxb_element.getName ();
        if (name != null && !JavaDesignerUtils.getJavaName (modelio_element).equals (name)) {
            modelio_element.setName (name);
        }
        
        if (owner instanceof Operation) {
            modelio_element.setComposed (null);
            modelio_element.setComposed ((Operation) owner);
        } else {
            modelio_element.setOwnerTemplateParameter (null);
            modelio_element.setOwnerTemplateParameter ((TemplateParameter) owner);
        }
        
        // We must merge "type" tags into one tag with several parameters
        JaxbTaggedValue arrayTag = null;
        for (Object sub : new ArrayList<> (jaxb_element.getBaseTypeOrClassTypeOrNote ())) {
            if (sub instanceof JaxbTaggedValue) {
                JaxbTaggedValue tag = (JaxbTaggedValue) sub;
                if (IOtherProfileElements.FEATURE_TYPE.equals (tag.getTagType ())) {
                    if (arrayTag == null) {
                        arrayTag = tag;
                    } else {
                        arrayTag.getTagParameter ().addAll (tag.getTagParameter ());
                        jaxb_element.getBaseTypeOrClassTypeOrNote ().remove (tag);
                    }
                }
            }
        }
        
        // If Array is one of the values, it must be the first one in the list
        if (arrayTag != null) {
            List<String> parameters = arrayTag.getTagParameter ();
            for (int i = 1 ; i < parameters.size () ; i++) {
                // Swap values
                if (parameters.get (i).equals ("Array")) {
                    parameters.set (i, parameters.get (0));
                    parameters.set (0, "Array");
                }
            }
        }
        
        String multMin = modelio_element.getMultiplicityMin();
        String multMax = modelio_element.getMultiplicityMax();
        PassingMode oldPassingMode = modelio_element.getParameterPassing();
        
        List<MObject> ret = super.updateProperties(jaxb_element, modelio_element, owner, repository);
        
        // Reset the passing mode
        modelio_element.setParameterPassing(oldPassingMode);
        
        // Set multiplicity
        // undo super work.
        modelio_element.setMultiplicityMin(multMin);
        modelio_element.setMultiplicityMax(multMax);
        String jaxbParameterMultiplicity = jaxb_element.getMultiplicity();
        // the only case is when the multiplicity max changes from 0,1 to * and
        // vice-versa
        if ("1".equals(jaxbParameterMultiplicity)) {
            // jaxbmult is 1
            if (!"01".contains(multMax)) {
                modelio_element.setMultiplicityMax("1");
            }
            if (!"01".contains(multMin)) {
                modelio_element.setMultiplicityMin("0");
            }
        } else {
            // jaxbmult is *
            if ("01".contains(multMax)) {
                // mult. min. is forced to 0 to avoid reversing new parameters with a 1..* mult.
                // (newly created parameters have mult. min. set to 1)
                modelio_element.setMultiplicityMin("0");
                modelio_element.setMultiplicityMax(jaxbParameterMultiplicity);
            }
        }
        
        handleMultipleTags (jaxb_element);
        
        modelio_element.setType (null);
        
        if (name != null && hasJavaName) {
            try {
                modelio_element.setName (oldName);
        
                ModelUtils.setFirstTagParameter (this.session, modelio_element, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME, name);
                if (ret == null) {
                    ret = new ArrayList<> ();
                }
                ret.add (modelio_element.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME));
            } catch (ExtensionNotFoundException | ElementNotUniqueException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
        return ret;
    }

    /**
     * TODO this should be done in the ANTLR -> XML part...
     */
    private void handleMultipleTags(final JaxbParameter jaxb_element) {
        JaxbTaggedValue firstBindTag = null;
        
        List<JaxbTaggedValue> toRemove = new ArrayList<> ();
        List<Object> sub_elements = jaxb_element.getBaseTypeOrClassTypeOrNote ();
        for (Object sub_element : sub_elements) {
            if (sub_element instanceof JaxbTaggedValue) {
                JaxbTaggedValue currentTag = (JaxbTaggedValue) sub_element;
        
                if (currentTag.getTagType ().equals (JavaDesignerTagTypes.PARAMETER_JAVABIND)) {
                    if (firstBindTag == null) {
                        firstBindTag = currentTag;
                    } else {
                        firstBindTag.getTagParameter ().addAll (currentTag.getTagParameter ());
                        toRemove.add (currentTag);
                    }
                }
            }
        }
        
        sub_elements.removeAll (toRemove);
    }

    @Override
    public void postTreatment(final JaxbParameter jaxb_element, final Parameter modelio_element, final IReadOnlyRepository repository) {
        super.postTreatment (jaxb_element, modelio_element, repository);
        
        // Remove type if there is a type expr
        if (modelio_element.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVATYPEEXPR) != null) {
            modelio_element.setType (null);
            // Emit the warning
            String typexpr = ModelUtils.getFirstTagParameter(modelio_element, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR);
            IReportWriter report = repository.getReportWriter ();
            report.addWarning (Messages.getString("reverse.Type_not_found.title", typexpr), modelio_element, Messages.getString("reverse.Type_not_found.description", typexpr)); //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        // Change multiplicity for predefined atomic types
        GeneralClass type = modelio_element.getType ();
        if (JavaFeatureReverseUtils.getInstance ().isAtomicType (type) && modelio_element.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVAWRAPPER) == null ) {
            String modelioParameterMultiplicityMin = modelio_element.getMultiplicityMin ();
            String modelioParameterMultiplicityMax = modelio_element.getMultiplicityMax ();
        
            if (modelioParameterMultiplicityMin.equals ("0") &&
                    modelioParameterMultiplicityMax.equals ("1")) {
                modelio_element.setMultiplicityMin ("1");
            }
        }
        
        if (modelio_element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVAFINAL)) {
            modelio_element.setParameterPassing(PassingMode.IN);
        } else if (this.isInParameterFinalGenerationActive && modelio_element.getParameterPassing() == PassingMode.IN) {
            modelio_element.setParameterPassing(PassingMode.INOUT);
        }
    }

}
