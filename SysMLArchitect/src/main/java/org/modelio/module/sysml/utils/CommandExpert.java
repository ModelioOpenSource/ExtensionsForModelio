package org.modelio.module.sysml.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.filters.BlockFilter;
import org.modelio.module.sysml.filters.ConstraintBlockFilter;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;


public class CommandExpert {
    
    public static boolean acceptBlock(MObject selectedElement) {
        return (accept(selectedElement))
                                        && ((selectedElement instanceof Package))
                                        || ((selectedElement instanceof Class) 
                                                && (BlockFilter.isABlock(selectedElement)
                                                        && (!ConstraintBlockFilter.isAConstraintBlock(selectedElement))));
    }

    
    public static boolean acceptConnectorProperty(MObject selectedElement) {
        return (accept(selectedElement))
                                        && (BlockFilter.isABlock (selectedElement))
                                        && (!ConstraintBlockFilter.isAConstraintBlock(selectedElement));
    }

    
    public static boolean acceptConstraintBlock(MObject selectedElement) {
        return (accept(selectedElement))
                                        && (selectedElement instanceof Package)
                                        && (!(selectedElement instanceof Profile));
    }

    
    public static boolean acceptConstraintProperty(MObject selectedElement) {
        return (accept(selectedElement))
                                        && (BlockFilter.isABlock (selectedElement))
                                        || (ConstraintBlockFilter.isAConstraintBlock(selectedElement));
    }

    
    public static boolean acceptDistributedProperty(MObject selectedElement) {
        return ((accept(selectedElement))
                                        && ((selectedElement instanceof Classifier)
                                                &&(BlockFilter.isABlock(selectedElement))
                                                && (!ConstraintBlockFilter.isAConstraintBlock(selectedElement))
                                                || (((Classifier) selectedElement).isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.VALUETYPE))));
    }

    
    public static boolean acceptContinuousParameter(MObject selectedElement) {
        return  ((selectedElement != null) 
                                        && (selectedElement.getStatus().isModifiable())
                                        && acceptInternal(selectedElement));
    }

    
    public static boolean accept(MObject selectedElement) {
        return  ((selectedElement != null) 
                                        && (selectedElement.getStatus().isModifiable())
                                        && acceptInternal(selectedElement));
    }

    
    private static boolean acceptInternal(MObject selectedElement) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
        
        for (MObject libRoot : model.getLibraryRoots()){
            if (selectedElement.equals(libRoot)){
                return false;
            }
        }
        
        for (MObject modelRoot : model.getModelRoots()){
            if (selectedElement.equals(modelRoot)){
                return false;
            }
        }
        return !((selectedElement.equals(model)));
    }

}
