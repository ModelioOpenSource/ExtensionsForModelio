/**
 * Java Class : SysMLPeerMdac.java
 *
 * Description :
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
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
 * @version    2.0.08
 **/
package org.modelio.module.sysml.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.vbasic.version.Version;

@objid ("1783d3d9-7bbd-4df0-bace-8f02c8de2252")
public class SysMLPeerModule implements ISysMLPeerModule {
    @objid ("3ebd8648-b762-4b67-85ae-234f2e61e036")
    private IModuleAPIConfiguration peerConfiguration;

    @objid ("ed991571-dc9b-48ac-a417-e7f8f88df567")
    private SysMLModule module;

    /**
     * Operation SysMLPeerMdac
     * @author ebrosse
     * @return
     */
    @objid ("e28886e5-625d-4109-b215-ef21d39866ba")
    public SysMLPeerModule(SysMLModule module, IModuleAPIConfiguration peerConfiguration) {
        this.module = module;
        this.peerConfiguration = peerConfiguration;
    }

    /**
     * Operation getConfiguration
     * @author ebrosse
     * @return
     */
    @objid ("31c51ff6-7a6d-4fc0-868a-2027d38f4d0a")
    @Override
    public IModuleAPIConfiguration getConfiguration() {
        return this.peerConfiguration;
    }

    /**
     * Operation getDescription
     * @author ebrosse
     * @return
     */
    @objid ("da0426ab-7b82-4f2e-b714-e5713538d09f")
    @Override
    public String getDescription() {
        return this.module.getDescription();
    }

    /**
     * Operation getName
     * @author ebrosse
     * @return
     */
    @objid ("a82d4a72-5111-4863-a343-d1d7db8e255d")
    @Override
    public String getName() {
        return this.module.getName();
    }

    /**
     * Operation getVersion
     * @author ebrosse
     * @return
     */
    @objid ("49e595c6-4533-4f6f-997c-97bab04ae1be")
    @Override
    public Version getVersion() {
        return this.module.getVersion();
    }

    /**
     * Operation getMdac
     * @author ebrosse
     * @return
     */
    @objid ("f6c3a9b2-0b9a-45b2-af59-2b05e34e10b5")
    public SysMLModule getMdac() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.module;
    }

}
