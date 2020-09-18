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
@objid ("6a350353-9cc6-4679-b04e-1dde9c0893ae")
public class SysMLUseCaseDiagramCustomizer extends SysMLDiagramCustomizer implements IDiagramCustomizer {
    @objid ("798e3f96-90b9-4351-ad40-e4b511640400")
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

    @objid ("ea6d3302-1248-462c-9767-03a322626cc2")
    @Override
    public boolean keepBasePalette() {
        return true;
    }

    @objid ("d046d60b-3fb6-48f4-b281-dab9ee398f1b")
    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }

    @objid ("effb3ecf-2b1a-4df4-8b0a-9f16cc60caa5")
    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
