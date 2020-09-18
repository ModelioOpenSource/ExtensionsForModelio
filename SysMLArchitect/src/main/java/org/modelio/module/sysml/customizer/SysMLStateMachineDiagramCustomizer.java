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
 * State Machine diagram palette configurer.
 */
@objid ("2be36872-150f-4337-b491-049356134df5")
public class SysMLStateMachineDiagramCustomizer extends SysMLDiagramCustomizer implements IDiagramCustomizer {
    @objid ("c8e4ca9a-7fb0-4947-a675-d09c50dadb9a")
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
        for (Object children : paletteRoot.getChildren()){
            if ((children instanceof PaletteDrawer) 
                    && ((PaletteDrawer)children).getLabel().equals(I18nMessageService.getString("StatePaletteGroup.Common"))){
                PaletteDrawer common = (PaletteDrawer) children;
                common.add(0, SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getRegisteredTool(ISysMLCustomizerPredefinedField.Rationale));                 
                common.add(0, SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getRegisteredTool(ISysMLCustomizerPredefinedField.Problem));
             }
            
        }
    }

    @objid ("5cc2f3e8-5903-469c-bd6e-c64f8a203499")
    @Override
    public boolean keepBasePalette() {
        return true;
    }

    @objid ("9ce7d160-2bcf-4858-8780-8412cb916bc1")
    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }

    @objid ("d745f748-5234-47e5-9ae1-ab74391bec57")
    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
