package org.modelio.module.sysml.customizer;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.api.modelio.diagram.IDiagramCustomizer;
import org.modelio.api.module.IModule;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.ISysMLCustomizerPredefinedField;

/**
 * Use Case diagram palette configurer.
 */

public class SysMLUseCaseDiagramCustomizer extends SysMLDiagramCustomizer implements IDiagramCustomizer {
    
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
        for (Object children : paletteRoot.getChildren()){
            if (children instanceof PaletteDrawer){
                PaletteDrawer currentDrawer = (PaletteDrawer) children ;
                String drawerLabel = currentDrawer.getLabel();
                if  (drawerLabel.equals(I18nMessageService.getString("UseCasePaletteGroup.Common"))){
                    currentDrawer.add(0, SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getRegisteredTool(ISysMLCustomizerPredefinedField.Rationale));                 
                    currentDrawer.add(0, SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getRegisteredTool(ISysMLCustomizerPredefinedField.Problem));
                }
                else if  (drawerLabel.equals(I18nMessageService.getString("UseCasePaletteGroup.InformationFlow"))){
                    currentDrawer.add(0, SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getRegisteredTool(ISysMLCustomizerPredefinedField.ItemFlow));     
                }
            }
        }
    }

    
    @Override
    public boolean keepBasePalette() {
        return true;
    }

    
    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }

    
    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
