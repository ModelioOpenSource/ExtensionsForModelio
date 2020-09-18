package org.modelio.module.sysml.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.parameter.IParameterEditionModel;
import org.modelio.api.module.parameter.impl.ParametersEditionModel;
import org.modelio.gproject.ramc.core.model.IModelComponent;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.utils.SysMLResourcesManager;

/**
 * This is the main class of the SysML module
 * 
 * @author ebrosse
 */
@objid ("b391784d-5d74-4a53-b2f5-21fb0f352784")
public class SysMLModule extends AbstractJavaModule {
    @objid ("a19a4388-4a27-4448-8704-8ad8f8df190d")
    private SysMLPeerModule peerMdac = null;

    @objid ("f64bbb23-5280-4f36-97aa-2c2dfee1d17a")
    private SysMLSession session = null;

    @objid ("3b615423-3f0a-45cd-8be1-97beeeffd39f")
    public static SysMLLogService logService;

    @objid ("bf412805-5017-4f35-9736-a4c8ff927282")
    private static SysMLModule instance;

    @objid ("0e5d653e-9ca8-46b5-b62c-1de20ac4bd2f")
    public static SysMLModule getInstance() {
        return instance;
    }

    @objid ("55314e47-f2ac-4ca1-bf87-74cc395fda69")
    public SysMLModule(IModuleContext moduleContext) {
        super(moduleContext);
        this.session = new SysMLSession(this);
        this.peerMdac = new SysMLPeerModule(this, moduleContext.getPeerConfiguration());
        SysMLModule.logService = new SysMLLogService(this.getModuleContext().getLogService(), this);
        instance = this;
    }

    @objid ("15f7a875-3b19-4a54-9008-8e8c683eb84f")
    @Override
    public SysMLSession getLifeCycleHandler() {
        return this.session;
    }

    @objid ("5e932b9b-b727-49ed-bf2e-10ac113299db")
    @Override
    public SysMLPeerModule getPeerModule() {
        return this.peerMdac;
    }

    /**
     * Method automatically called just after the creation of the module. The
     * module is automatically instanciated at the beginning of the MDA
     * lifecycle and constructor implementation is not accessible to the module
     * developer. The <code>init</code> method allows the developer to execute
     * the desired initialization.
     */
    @objid ("72e17dda-26c2-49c5-a087-291bdd4863ee")
    @Override
    public void init() {
        // Add the module initialization code
        logService.info(I18nMessageService.getString("Mdac.Gui.Init"));
        SysMLResourcesManager.getInstance().setJMDAC(this);
        super.init();
    }

    @objid ("7f0501ca-aa68-4aa9-b263-137707fbd325")
    @Override
    public IParameterEditionModel getParametersEditionModel() {
        if (this.parameterEditionModel == null) {
            ParametersEditionModel parameters = new ParametersEditionModel(this);
            this.parameterEditionModel = parameters;
        }
        return this.parameterEditionModel;
    }

    @objid ("f1e1ccad-f39c-4cfa-a1ce-de3ef9852cc1")
    @Override
    public String getModuleImagePath() {
        return "/res/icons/sysml.png";
    }

    @objid ("aac02806-ca5e-4979-a14c-49d12004a7ca")
    @Override
    public IModelComponentContributor getModelComponentContributor(IModelComponent mc) {
        return new SysMLModelComponentContributor(this);
    }

}
