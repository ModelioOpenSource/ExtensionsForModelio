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
 * Sequence diagram palette configurer.
 */
@objid ("d01e8774-0ca2-4f78-b2d1-655bc8b5e1f1")
public class SysMLSequenceDiagramCustomizer extends SysMLDiagramCustomizer implements IDiagramCustomizer {
    @objid ("c0a6a723-ed17-424f-948a-61d321bc0d8e")
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
        for (Object children : paletteRoot.getChildren()){
            if (children instanceof PaletteDrawer){
                PaletteDrawer currentDrawer = (PaletteDrawer) children ;
                String drawerLabel = currentDrawer.getLabel();
                if  (drawerLabel.equals(I18nMessageService.getString("SequencePaletteGroup.Common"))){
                    currentDrawer.add(0, SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getRegisteredTool(ISysMLCustomizerPredefinedField.Rationale));                 
                    currentDrawer.add(0, SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getRegisteredTool(ISysMLCustomizerPredefinedField.Problem));
                }
            }
        
        }
    }

    @objid ("54de3cb9-4aa0-40c5-b202-7d467a7853c4")
    @Override
    public boolean keepBasePalette() {
        return true;
    }

    @objid ("7af58bf0-9ca8-4907-a1cf-aab1ed4b6ee2")
    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }

    @objid ("4399a2fa-48d5-4844-9f08-e19cd5ea79d4")
    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
