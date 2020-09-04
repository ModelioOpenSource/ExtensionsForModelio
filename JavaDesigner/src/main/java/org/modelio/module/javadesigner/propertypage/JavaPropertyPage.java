package org.modelio.module.javadesigner.propertypage;

import java.util.List;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.AbstractModulePropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaPropertyPage extends AbstractModulePropertyPage {
    public JavaPropertyPage(final IModule module, final String name, final String label, final String bitmap) {
        super (module, name, label, bitmap);
    }

    /**
     * This method is called when the current selection changes and that the property box contents requires an update.
     * The "selectedElements" parameter contains the list of the newly selected elements. The "table" parameter is the
     * table that must be filled with the updated contents of the property box before returning.
     */
    @Override
    public void update(final List<MObject> selectedElements, final IModulePropertyTable table) {
        if (selectedElements.size () == 1) {
            IPropertyModel propertyModel = null;
            MObject element = selectedElements.get (0);
        
            if (element instanceof Package && !(element instanceof Profile)) {
                propertyModel = new PackagePropertyModel (this.getModule (), (Package) element);
        
            } else if (element instanceof Component &&
                    !(element instanceof ModuleComponent)) {
                propertyModel = new ComponentPropertyModel (this.getModule (), (Component) element);
        
            } else if (element instanceof Class &&
                    !(element instanceof ModuleComponent)) {
                propertyModel = new ClassPropertyModel (this.getModule (), (Class) element);
        
            } else if (element instanceof Interface) {
                propertyModel = new InterfacePropertyModel (this.getModule (), (Interface) element);
        
            } else if (element instanceof DataType) {
                propertyModel = new DataTypePropertyModel (this.getModule (), (DataType) element);
        
            } else if (element instanceof Enumeration) {
                propertyModel = new EnumerationPropertyModel (this.getModule (), (Enumeration) element);
        
            } else if (element instanceof Attribute) {
                propertyModel = new AttributePropertyModel (this.getModule (), (Attribute) element);
        
            } else if (element instanceof Operation) {
                propertyModel = new OperationPropertyModel (this.getModule (), (Operation) element);
        
            } else if (element instanceof Parameter) {
                propertyModel = new ParameterPropertyModel (this.getModule (), (Parameter) element);
        
            } else if (element instanceof AssociationEnd) {
                propertyModel = new AssociationEndPropertyModel (this.getModule (), (AssociationEnd) element);
        
            } else if (element instanceof Artifact) {
                propertyModel = new ArtifactPropertyModel (this.getModule (), (Artifact) element);
            }
        
            // Launch the property modification
            if (propertyModel != null) {
                propertyModel.update (table);
            }
        
        }
    }

    /**
     * This method is called when a value has been edited in the property box in the row "row". The "selectedElements"
     * parameter contains the list of the currently selected elements. The "row" parameter is the row number of the
     * modified value. The "value" parameter is the new value the user has set to the given row.
     */
    @Override
    public void changeProperty(final List<MObject> selectedElements, final int row, final String value) {
        if (selectedElements.size () == 1) {
            IPropertyModel propertyModel = null;
            MObject element = selectedElements.get (0);
        
            if (element instanceof Package && !(element instanceof Profile)) {
                propertyModel = new PackagePropertyModel (this.getModule (), (Package) element);
        
            } else if (element instanceof Component &&
                    !(element instanceof ModuleComponent)) {
                propertyModel = new ComponentPropertyModel (this.getModule (), (Component) element);
        
            } else if (element instanceof Class &&
                    !(element instanceof ModuleComponent)) {
                propertyModel = new ClassPropertyModel (this.getModule (), (Class) element);
        
            } else if (element instanceof Interface) {
                propertyModel = new InterfacePropertyModel (this.getModule (), (Interface) element);
        
            } else if (element instanceof DataType) {
                propertyModel = new DataTypePropertyModel (this.getModule (), (DataType) element);
        
            } else if (element instanceof Enumeration) {
                propertyModel = new EnumerationPropertyModel (this.getModule (), (Enumeration) element);
        
            } else if (element instanceof Attribute) {
                propertyModel = new AttributePropertyModel (this.getModule (), (Attribute) element);
        
            } else if (element instanceof Operation) {
                propertyModel = new OperationPropertyModel (this.getModule (), (Operation) element);
        
            } else if (element instanceof Parameter) {
                propertyModel = new ParameterPropertyModel (this.getModule (), (Parameter) element);
        
            } else if (element instanceof AssociationEnd) {
                propertyModel = new AssociationEndPropertyModel (this.getModule (), (AssociationEnd) element);
        
            } else if (element instanceof Artifact) {
                propertyModel = new ArtifactPropertyModel (this.getModule (), (Artifact) element);
            }
        
            // Launch the property modification
            if (propertyModel != null) {
                propertyModel.changeProperty (row, value);
            }
        }
    }

}
