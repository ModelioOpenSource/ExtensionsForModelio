package org.modelio.togaf.impl;

import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.parameter.IParameterEditionModel;
import org.modelio.togaf.conf.TogafModelContributor;
import org.modelio.vbasic.version.Version;

public class TogafArchitectModule extends AbstractJavaModule {
	public static final String MODULE_NAME = "TogafArchitect";

    public static final Version LATEST_PROJECT_VERSION = new Version("4.0.00");

    private TogafArchitectPeerModule peerMdac = null;

	private TogafArchitectSession session = null;
	
	
	private static  TogafArchitectModule instance;

	public static TogafArchitectModule getInstance() {
	    return instance;
	}

	    public TogafArchitectModule(IModuleContext moduleContext) {
	        super(moduleContext);
	        this.session = new TogafArchitectSession(this);
	        this.peerMdac = new TogafArchitectPeerModule(this, moduleContext.getPeerConfiguration());
	        this.peerMdac.init();
	        
	        instance = this;
	    }
		@Override
		public TogafArchitectSession getLifeCycleHandler() {
			return this.session;
		}


	@Override
	public IPeerModule getPeerModule() {
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
		try (ITransaction transaction = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().createTransaction("TogafArchitect")) {
			TogafModelContributor modelContributor = new TogafModelContributor();
			modelContributor.createInitialModel();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IParameterEditionModel getParametersEditionModel() {
	    if (this.parameterEditionModel == null) {
	        this.parameterEditionModel = super.getParametersEditionModel();
	        
	    }
		return this.parameterEditionModel;
	}

	@Override
	public String getModuleImagePath() {
		return "/res/bmp/togaf16.png";
	}
}
