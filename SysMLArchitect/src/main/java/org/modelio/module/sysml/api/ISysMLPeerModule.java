/**
 * Java Class : ISysMLPeerMdac.java
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
 * @category   Modelio API
 * @package    org.modelio.module.sysml.api
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IPeerModule;

/**
 * @author ebrosse
 */
@objid ("2c59b75c-6480-48b9-a2fa-ac88acb9753b")
public interface ISysMLPeerModule extends IPeerModule {
    @objid ("80ec3b88-e143-4459-984f-26cbb0c2c3fa")
    public static final String MODULE_NAME = "SysMLArchitect";

}
