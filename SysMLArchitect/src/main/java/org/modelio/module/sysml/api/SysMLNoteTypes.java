/**
 * Java Class : SysMLNoteTypes.java
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

/**
 * @author ebrosse
 */
@objid ("ae4db5c6-bf5d-4e6b-a3ee-b24b2c5cd692")
public interface SysMLNoteTypes {
    @objid ("ed5b76da-e234-4fc4-a8c1-30aa9c4a2ad1")
    public static final String MODELELEMENT_PROBLEM = "Problem";

    @objid ("d8bc7e1e-573c-44a4-909a-b4f36696e306")
    public static final String MODELELEMENT_RATIONALE = "Rationale";

}
