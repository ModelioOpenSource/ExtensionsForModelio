package org.modelio.module.javadesigner.impl;

import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.gproject.ramc.core.model.IModelComponent;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;
import org.modelio.module.javadesigner.i18n.Messages;

public class JavaDesignerModule extends AbstractJavaModule {
    protected JavaDesignerPeerModule peerModule;

    protected JavaDesignerSession session;

    /**
     * Singleton instance of the JavaDesignerModule.
     */
    private static JavaDesignerModule instance;

    /**
     * Buids a new module.
     * <p>
     * <p>
     * This constructor must not be called by the user. It is automatically invoked by Modelio when the module is
     * installed, selected or started.
     */
    public JavaDesignerModule(IModuleContext moduleContext) {
        super(moduleContext);
        this.session  = new JavaDesignerSession(this);
        this.peerModule = new JavaDesignerPeerModule(this, moduleContext.getPeerConfiguration());
        
        JavaDesignerModule.instance = this;
    }

    @Override
    public JavaDesignerPeerModule getPeerModule() {
        return this.peerModule;
    }

    @Override
    public String getDescription() {
        return Messages.getString ("Module.Description"); //$NON-NLS-1$
    }

    @Override
    public String getLabel() {
        return Messages.getString ("Module.Label"); //$NON-NLS-1$
    }

    @Override
    public String getModuleImagePath() {
        return "/res/bmp/JavaDesigner.png";
    }

    @Override
    public IModelComponentContributor getModelComponentContributor(final IModelComponent mc) {
        return new JavaRamcContributor (this, mc);
    }

    @Override
    public JavaDesignerSession getLifeCycleHandler() {
        return this.session;
    }

    public static JavaDesignerModule getInstance() {
        return instance;
    }

}
