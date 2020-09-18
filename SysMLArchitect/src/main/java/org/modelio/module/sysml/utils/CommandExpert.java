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

@objid ("323f1420-85a3-47ad-b8f7-df67cb8880b1")
public class CommandExpert {
    @objid ("8e989e6b-3447-49a9-856b-b56bc68ff9b4")
    public static boolean acceptBlock(MObject selectedElement) {
        return (accept(selectedElement))
                                        && ((selectedElement instanceof Package))
                                        || ((selectedElement instanceof Class) 
                                                && (BlockFilter.isABlock(selectedElement)
                                                        && (!ConstraintBlockFilter.isAConstraintBlock(selectedElement))));
    }

    @objid ("23dbff48-5df5-4006-8314-ad21d28ebc80")
    public static boolean acceptConnectorProperty(MObject selectedElement) {
        return (accept(selectedElement))
                                        && (BlockFilter.isABlock (selectedElement))
                                        && (!ConstraintBlockFilter.isAConstraintBlock(selectedElement));
    }

    @objid ("410794db-fb05-4a53-8969-606e1d849b9e")
    public static boolean acceptConstraintBlock(MObject selectedElement) {
        return (accept(selectedElement))
                                        && (selectedElement instanceof Package)
                                        && (!(selectedElement instanceof Profile));
    }

    @objid ("eaa3d2ce-de21-46ff-ab9e-35204e1aa38f")
    public static boolean acceptConstraintProperty(MObject selectedElement) {
        return (accept(selectedElement))
                                        && (BlockFilter.isABlock (selectedElement))
                                        || (ConstraintBlockFilter.isAConstraintBlock(selectedElement));
    }

    @objid ("ad7a4ecb-cea8-42fb-be62-910579d98932")
    public static boolean acceptDistributedProperty(MObject selectedElement) {
        return ((accept(selectedElement))
                                        && ((selectedElement instanceof Classifier)
                                                &&(BlockFilter.isABlock(selectedElement))
                                                && (!ConstraintBlockFilter.isAConstraintBlock(selectedElement))
                                                || (((Classifier) selectedElement).isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.VALUETYPE))));
    }

    @objid ("0223c4cf-cf29-4009-b3c7-9b5058af0e9c")
    public static boolean acceptContinuousParameter(MObject selectedElement) {
        return  ((selectedElement != null) 
                                        && (selectedElement.getStatus().isModifiable())
                                        && acceptInternal(selectedElement));
    }

    @objid ("dfb3e281-2869-4aa5-923b-d7d2c9608537")
    public static boolean accept(MObject selectedElement) {
        return  ((selectedElement != null) 
                                        && (selectedElement.getStatus().isModifiable())
                                        && acceptInternal(selectedElement));
    }

    @objid ("0d66bbdf-0e6e-4aa0-ae74-8490c62020e7")
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
