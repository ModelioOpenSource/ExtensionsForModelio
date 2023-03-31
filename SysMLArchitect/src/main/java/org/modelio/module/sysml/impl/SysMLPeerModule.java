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


public class SysMLPeerModule implements ISysMLPeerModule {
    
    private IModuleAPIConfiguration peerConfiguration;

    
    private SysMLModule module;

    /**
     * Operation SysMLPeerMdac
     * @author ebrosse
     * @return
     */
    
    public SysMLPeerModule(SysMLModule module, IModuleAPIConfiguration peerConfiguration) {
        this.module = module;
        this.peerConfiguration = peerConfiguration;
    }

    /**
     * Operation getConfiguration
     * @author ebrosse
     * @return
     */
    
    @Override
    public IModuleAPIConfiguration getConfiguration() {
        return this.peerConfiguration;
    }

    /**
     * Operation getDescription
     * @author ebrosse
     * @return
     */
    
    @Override
    public String getDescription() {
        return this.module.getDescription();
    }

    /**
     * Operation getName
     * @author ebrosse
     * @return
     */
    
    @Override
    public String getName() {
        return this.module.getName();
    }

    /**
     * Operation getVersion
     * @author ebrosse
     * @return
     */
    
    @Override
    public Version getVersion() {
        return this.module.getVersion();
    }

    /**
     * Operation getMdac
     * @author ebrosse
     * @return
     */
    
    public SysMLModule getMdac() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.module;
    }

}
