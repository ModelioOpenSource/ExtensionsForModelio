/**
 * Java Class : SysMLSession.java
 *
 * Description :
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for itional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *    KIND, either express or implied.  See the License for the
 *    specific language governing permissions and limitations
 *    under the License.
 *
 * @category   Modelio Impl
 * @package    org.modelio.module.sysml.impl
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    1.2.15
 **/
package org.modelio.module.sysml.impl;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.modelio.api.modelio.mc.IModelComponentDescriptor;
import org.modelio.api.modelio.mc.IModelComponentService;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.lifecycle.DefaultModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class represents the life of SysMl module
 * 
 * @author ebrosse
 */
@objid ("32e56309-bc6a-41fc-aa41-641775756f69")
public class SysMLSession extends DefaultModuleLifeCycleHandler {
    @objid ("f25706f3-f04b-4f75-8395-a77020cff01e")
    private SysMLModelChangeHandler modelChangeHandler = null;

    /**
     * Constructor
     * @author ebrosse
     * 
     * @param module : the SysML Mdac
     */
    @objid ("6df96b95-98f7-4605-84eb-a6f6602b0c56")
    public SysMLSession(SysMLModule module) {
        super(module);
    }

    @objid ("6eaf1975-1976-4f9e-9a1f-1d4e41fade4c")
    @Override
    public boolean start() throws ModuleException {
        /*
         * Copyright
         */
        // Remove the metamodelVersion
        Version version = this.module.getVersion();
        String completeVersion = version.toString();
        // Display the copyright
        // SysMLInfo cannot be used in this operation, because there is a Java
        // error when the MDAC is deployed
        SysMLModule.logService.info("Modelio/" + this.module.getName() + " " + completeVersion + " - Copyright 2011-2015 Modeliosoft");
        
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        this.modelChangeHandler = new SysMLModelChangeHandler();
        session.addModelHandler(this.modelChangeHandler);
        
        installStyles();
        installRamc();
        return super.start();
    }

    @objid ("814f92e8-8bcd-40b1-b716-dddb988d977b")
    @Override
    public void stop() throws ModuleException {
        this.module.getModuleContext().getModelingSession().removeModelHandler(this.modelChangeHandler);
        this.modelChangeHandler = null;
        super.stop();
    }

    @objid ("f277201f-b185-42fa-b5c6-e68f0ac3c333")
    private void installRamc() {
        Path mdaplugsPath = this.module.getModuleContext().getConfiguration().getModuleResourcesPath();
        
        final IModelComponentService modelComponentService = SysMLModule.getInstance().getModuleContext().getModelioServices().getModelComponentService();
        for (IModelComponentDescriptor mc : modelComponentService.getModelComponents()) {
            if (mc.getName().equals("SIDefinitions")) {
                if (new Version(mc.getVersion()).isOlderThan(new Version("3.8.00"))) {
                    modelComponentService.deployModelComponent(new File(mdaplugsPath.resolve("res" + File.separator + "ramc" + File.separator + "SIDefinitions.ramc").toString()), new NullProgressMonitor());
                } else {
                    // Ramc already deployed...
                    return;
                }
            }
        }
        
        // No ramc found, deploy it
        modelComponentService.deployModelComponent(new File(mdaplugsPath.resolve("res" + File.separator + "ramc" + File.separator + "SIDefinitions.ramc").toString()), new NullProgressMonitor());
    }

    @objid ("6b984ec9-69c1-41f8-9888-05ac97a1c044")
    private void installStyles() {
        Path mdaplugsPath = this.module.getModuleContext().getConfiguration().getModuleResourcesPath();
        
        SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().registerStyle("sysml", "default", new File(mdaplugsPath.resolve("res" + File.separator + "style" + File.separator + "sysml.style").toString()));
        
        SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().registerStyle("sysmlinternal", "default", new File(mdaplugsPath.resolve("res" + File.separator + "style" + File.separator + "sysmlinternal.style").toString()));
        
        SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService().registerStyle("sysmlpackage", "default", new File(mdaplugsPath.resolve("res" + File.separator + "style" + File.separator + "sysmlpackage.style").toString()));
    }

    @objid ("bab0a697-fbed-499d-b4a9-fba204e1b193")
    @Override
    public boolean select() throws ModuleException {
        return super.select();
    }

    @objid ("7b42efb9-eca7-4476-af3c-b6aba0b47b59")
    @Override
    public void upgrade(Version oldVersion, Map<String, String> oldParameters) {
        Version lastVersion = new Version("2.1.10");
        if (oldVersion.isOlderThan(lastVersion)) {
            IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
            Collection<? extends MObject> existingPorts = session.findByClass(SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Port.class));
            for (MObject existingPort : existingPorts) {
                if (existingPort instanceof Port) {
                    Port currentPort = (Port) existingPort;
        
                    Stereotype flowPortSter = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWPORT, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Port.class));
        
                    if (currentPort.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWIN)) {
        
                        Stereotype flowINSter = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWIN, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Port.class));
                        currentPort.setDirection(PortOrientation.IN);
                        currentPort.getExtension().remove(flowINSter);
        
                    } else if (currentPort.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWOUT)) {
        
                        Stereotype flowOUTSter = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWOUT, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Port.class));
                        currentPort.setDirection(PortOrientation.OUT);
                        currentPort.getExtension().remove(flowOUTSter);
        
                    } else if (currentPort.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWINOUT)) {
        
                        Stereotype flowINOUTSter = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWINOUT, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Port.class));
                        currentPort.setDirection(PortOrientation.INOUT);
                        currentPort.getExtension().remove(flowINOUTSter);
                    }
        
                    currentPort.getExtension().add(flowPortSter);
        
                }
            }
        }
    }

}
