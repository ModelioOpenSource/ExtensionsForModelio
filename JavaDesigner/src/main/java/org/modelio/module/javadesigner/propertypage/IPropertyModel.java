package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;

public interface IPropertyModel {
    void changeProperty(final int row, final String value);

    ArrayList<String> getProperties();

    void update(final IModulePropertyTable table);

}
