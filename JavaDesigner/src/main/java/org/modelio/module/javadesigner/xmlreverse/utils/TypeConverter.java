package org.modelio.module.javadesigner.xmlreverse.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.statik.DataType;


public class TypeConverter {
    /**
     * Return the predefined type equivalent to the given type.
     * @param type the name of the type to convert.
     * @param model the uml model to convert the type into.
     * @return a predefined type having the same name as "type", or "Undefined".
     */
    
    public static DataType getBaseType(final String type, final IUmlModel model) {
        if(model.getUmlTypes().getSTRING().getName().equalsIgnoreCase(type)){
            return model.getUmlTypes().getSTRING();
        } else  if(model.getUmlTypes().getINTEGER().getName().equalsIgnoreCase(type) || "int".equalsIgnoreCase(type) ){
            return model.getUmlTypes().getINTEGER();
        }else if(model.getUmlTypes().getBOOLEAN().getName().equalsIgnoreCase(type)){
            return model.getUmlTypes().getBOOLEAN();
        }else  if(model.getUmlTypes().getBYTE().getName().equalsIgnoreCase(type)){
            return model.getUmlTypes().getBYTE();
        } else  if(model.getUmlTypes().getCHAR().getName().equalsIgnoreCase(type)){
            return model.getUmlTypes().getCHAR();
        } else  if(model.getUmlTypes().getDATE().getName().equalsIgnoreCase(type)){
            return model.getUmlTypes().getDATE();
        } else  if(model.getUmlTypes().getDOUBLE().getName().equalsIgnoreCase(type)){
            return model.getUmlTypes().getDOUBLE();
        } else  if(model.getUmlTypes().getLONG().getName().equalsIgnoreCase(type)){
            return model.getUmlTypes().getLONG();
        } else  if(model.getUmlTypes().getSHORT().getName().equalsIgnoreCase(type)){
            return model.getUmlTypes().getSHORT();
        } else  if(model.getUmlTypes().getFLOAT().getName().equalsIgnoreCase(type)){
            return model.getUmlTypes().getFLOAT();
        } else {
            return model.getUmlTypes().getUNDEFINED();
        }
    }

}
