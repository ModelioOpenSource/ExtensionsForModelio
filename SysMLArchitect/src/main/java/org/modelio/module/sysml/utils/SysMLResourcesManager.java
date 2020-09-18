/**
 * Java Class : ResourcesManager.java
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
 * @category   Util
 * @package    com.modeliosoft.modelio.sysml.utils
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.utils;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.module.sysml.i18n.I18nMessageService;

/**
 * This class handles the SysML resources i.e. images, styles, property names, etc.
 * @author ebrosse
 */
@objid ("802c2f11-7010-4f30-9a5f-e39b6d47b650")
public class SysMLResourcesManager {
    @objid ("ef89ac06-d1e7-4ad5-9279-fef0280b4ad7")
    private static SysMLResourcesManager instance = null;

    @objid ("a93068f6-9b77-4752-9cfc-10e848756e5d")
    private IModule _mdac;

    /**
     * Method ResourcesManager
     * @author ebrosse
     */
    @objid ("354fd345-00b0-4fff-b692-2a8467ba3a09")
    private SysMLResourcesManager() {
    }

    /**
     * Method getInstance
     * @author ebrosse
     * 
     * @return the SysMLResourcesManager instance
     */
    @objid ("3e956d33-c5e0-4ddf-acc5-59c39ffbdce7")
    public static SysMLResourcesManager getInstance() {
        if(instance == null){
            instance =  new SysMLResourcesManager();
        }
        return instance;
    }

    /**
     * This method sets the current module
     * 
     * @param module : the current module
     */
    @objid ("36fcf8b9-b09e-4ece-a72a-750d5a322334")
    public void setJMDAC(IModule module) {
        this._mdac = module;
    }

    /**
     * Method getImage
     * @author ebrosse
     * 
     * @param imageName : the name of the image file
     * @return the complete path of the image file
     */
    @objid ("e2d20548-26fa-40b9-b056-091dbbce3660")
    public String getImage(String imageName) {
        return this._mdac.getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator + "res" + File.separator + "icons" + File.separator + imageName;
    }

    /**
     * Method getStyle
     * @author ebrosse
     * 
     * @param styleName : the name of the style file
     * @return the absolute path of the style file
     */
    @objid ("7a9dc7e4-77a1-43d5-a114-e34f31f5d093")
    public String getStyle(String styleName) {
        return this._mdac.getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator  + "res" + File.separator + "style" + File.separator + styleName;
    }

    /**
     * Method getPropertyName
     * @author ebrosse
     * 
     * @param propertyName : the name of the property
     * @return the internationalized name of the property
     */
    @objid ("23a95b18-5d51-45fa-a0dc-bcfd38266728")
    public String getPropertyName(String propertyName) {
        return I18nMessageService.getString("Ui.Property." + propertyName + ".Name" );
    }

}
