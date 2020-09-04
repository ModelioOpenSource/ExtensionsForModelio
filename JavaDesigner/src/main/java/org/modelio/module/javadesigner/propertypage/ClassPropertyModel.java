package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;

/**
 * This class provides the list of properties for the <i>Class</i> metaclass.
 */
class ClassPropertyModel extends PropertyModel implements IPropertyModel {
    private String[] retentionValues = { "SOURCE", "CLASS", "RUNTIME" };

    private Class selectedClass = null;

    public ClassPropertyModel(final IModule module, final Class selectedClass) {
        super (module);
        this.selectedClass = selectedClass;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        
        if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE) ||
                property.contentEquals (JavaDesignerTagTypes.CLASS_JAVASTATIC) ||
                property.contentEquals (JavaDesignerTagTypes.JAVAANNOTATION_JAVADOCUMENTEDANNOTATION) ||
                property.contentEquals (JavaDesignerTagTypes.JAVAANNOTATION_JAVAINHERITEDANNOTATION)) {
            boolean isActive = Boolean.parseBoolean (value);
            changePropertyBooleanTaggedValue (this.selectedClass, IJavaDesignerPeerModule.MODULE_NAME, property, isActive);
        
            // Property "JavaAnnotation"
        } else if (property.contentEquals (JavaDesignerStereotypes.JAVAANNOTATION) ||
                (property.contentEquals (JavaDesignerStereotypes.JAVACLASS))) {
            changeStereotype (this.selectedClass, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean (value));
        } else if (property.contentEquals (JavaDesignerTagTypes.JAVAANNOTATION_JAVARETENTIONANNOTATION)) {
            changePropertyStringTaggedValue (this.selectedClass, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAANNOTATION_JAVARETENTIONANNOTATION, value);
        } else if (property.contentEquals ("TYPE") ||
                property.contentEquals ("FIELD") ||
                property.contentEquals ("METHOD") ||
                property.contentEquals ("PARAMETER") ||
                property.contentEquals ("CONSTRUCTOR") ||
                property.contentEquals ("LOCAL_VARIABLE") ||
                property.contentEquals ("ANNOTATION_TYPE") ||
                property.contentEquals ("PACKAGE") ||
                property.contentEquals ("TYPE_PARAMETER") ||
                property.contentEquals ("TYPE_USE")) {
        
            if (Boolean.parseBoolean (value)) {
                addPropertyValue (this.selectedClass, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property);
            } else {
                removePropertyValue (this.selectedClass, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property);
            }
        }
    }

    /**
     * Properties to display for <i>Class</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>No code
     * <li>JavaAnnotation: only if the option is 'JDK 5'
     * <li>JavaStatic: only for inner classes
     * <li>JavaMain
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<> ();
        
        properties.add (JavaDesignerStereotypes.JAVACLASS);
        
        if (this.selectedClass.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACLASS)) {
            properties.add (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        
            if (!this.selectedClass.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                properties.add (JavaDesignerStereotypes.JAVAANNOTATION);
                if (this.selectedClass.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAANNOTATION)) {
                    properties.add (JavaDesignerTagTypes.JAVAANNOTATION_JAVADOCUMENTEDANNOTATION);
                    properties.add (JavaDesignerTagTypes.JAVAANNOTATION_JAVAINHERITEDANNOTATION);
                    properties.add (JavaDesignerTagTypes.JAVAANNOTATION_JAVARETENTIONANNOTATION);
                    properties.add ("TYPE");
                    properties.add ("FIELD");
                    properties.add ("METHOD");
                    properties.add ("PARAMETER");
                    properties.add ("CONSTRUCTOR");
                    properties.add ("LOCAL_VARIABLE");
                    properties.add ("ANNOTATION_TYPE");
                    properties.add ("PACKAGE");
                    properties.add ("TYPE_PARAMETER");
                    properties.add ("TYPE_USE");
                }
        
                if (JavaDesignerUtils.isInner (this.selectedClass)) {
                    properties.add (JavaDesignerTagTypes.CLASS_JAVASTATIC);
                }
                // properties.add (this.JAVA_MAIN);
            }
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                table.addProperty (Messages.getString ("Gui.Property.NoCode"), this.selectedClass.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerStereotypes.JAVACLASS)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaAutomation"), this.selectedClass.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACLASS)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.CLASS_JAVASTATIC)) {
                table.addProperty (Messages.getString ("Gui.Property.Static"), this.selectedClass.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVASTATIC)); //$NON-NLS-1$
        
                // } else if (property.contentEquals (JAVA_MAIN)) {
                // table.addProperty (Messages.getString ("Gui.Property.JavaMain"), isJavaMain (element)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerStereotypes.JAVAANNOTATION)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaAnnotation"), this.selectedClass.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAANNOTATION)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.JAVAANNOTATION_JAVADOCUMENTEDANNOTATION)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaDocumentedAnnotation"), this.selectedClass.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAANNOTATION_JAVADOCUMENTEDANNOTATION)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.JAVAANNOTATION_JAVAINHERITEDANNOTATION)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaInheritedAnnotation"), this.selectedClass.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAANNOTATION_JAVAINHERITEDANNOTATION)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.JAVAANNOTATION_JAVARETENTIONANNOTATION)) {
                String retention = ModelUtils.getFirstTagParameter (this.selectedClass, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAANNOTATION_JAVARETENTIONANNOTATION);
                // Set a default value if nothing is set
                if (retention == null || retention.isEmpty ()) {
                    retention = this.retentionValues[0];
                }
                table.addProperty (Messages.getString ("Gui.Property.JavaRetentionAnnotation"), retention, this.retentionValues); //$NON-NLS-1$
            } else if (property.contentEquals ("TYPE")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.TYPE"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            } else if (property.contentEquals ("FIELD")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.FIELD"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            } else if (property.contentEquals ("METHOD")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.METHOD"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            } else if (property.contentEquals ("PARAMETER")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.PARAMETER"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            } else if (property.contentEquals ("CONSTRUCTOR")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.CONSTRUCTOR"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            } else if (property.contentEquals ("LOCAL_VARIABLE")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.LOCAL_VARIABLE"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            } else if (property.contentEquals ("ANNOTATION_TYPE")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.ANNOTATION_TYPE"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            } else if (property.contentEquals ("PACKAGE")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.PACKAGE"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            } else if (property.contentEquals ("TYPE_PARAMETER")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.TYPE_PARAMETER"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            } else if (property.contentEquals ("TYPE_USE")) {
                table.addProperty (Messages.getString ("Gui.Property.Target.TYPE_USE"), ModelUtils.hasTagParameter (this.selectedClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION, property)); //$NON-NLS-1$
            }
        }
    }

}
