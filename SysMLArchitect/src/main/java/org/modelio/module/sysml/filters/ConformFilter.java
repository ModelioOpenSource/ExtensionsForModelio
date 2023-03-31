/**
 * Java Class : ConformFilter.java
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
 * @category   Filter
 * @package    org.modelio.module.sysml.filters
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.filters;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class filters SysML conform dependency
 * 
 * @author ebrosse
 */

public class ConformFilter implements IObjectFilter {
    /**
     * Method isABlock
     * @author ebrosse
     * @param MObject
     * : the tested MObject
     * 
     * @return true if the given MObject is a conform dependency
     */
    
    public static boolean isAConform(MObject element) {
        return ((element instanceof Dependency) && ((ModelElement) element).isStereotyped(ISysMLPeerModule.MODULE_NAME,
                                                                        SysMLStereotypes.CONFORM));
    }

    
    @Override
    public boolean accept(MObject element) {
        return isAConform(element);
    }

}
