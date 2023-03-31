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

public class SysMLModule extends AbstractJavaModule {
    
    private SysMLPeerModule peerMdac = null;

    
    private SysMLSession session = null;

    
    public static SysMLLogService logService;

    
    private static SysMLModule instance;

    
    public static SysMLModule getInstance() {
        return instance;
    }

    
    public SysMLModule(IModuleContext moduleContext) {
        super(moduleContext);
        this.session = new SysMLSession(this);
        this.peerMdac = new SysMLPeerModule(this, moduleContext.getPeerConfiguration());
        SysMLModule.logService = new SysMLLogService(this.getModuleContext().getLogService(), this);
        instance = this;
    }

    
    @Override
    public SysMLSession getLifeCycleHandler() {
        return this.session;
    }

    
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
    
    @Override
    public void init() {
        // Add the module initialization code
        logService.info(I18nMessageService.getString("Mdac.Gui.Init"));
        SysMLResourcesManager.getInstance().setJMDAC(this);
        super.init();
    }

    
    @Override
    public IParameterEditionModel getParametersEditionModel() {
        if (this.parameterEditionModel == null) {
            ParametersEditionModel parameters = new ParametersEditionModel(this);
            this.parameterEditionModel = parameters;
        }
        return this.parameterEditionModel;
    }

    
    @Override
    public String getModuleImagePath() {
        return "/res/icons/sysml.png";
    }

    
    @Override
    public IModelComponentContributor getModelComponentContributor(IModelComponent mc) {
        return new SysMLModelComponentContributor(this);
    }

}
