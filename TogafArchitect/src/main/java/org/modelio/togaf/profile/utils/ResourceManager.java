/** 
 * Licensed to the Apache Software Foundation (ASF) under one 
 * or more contributor license agreements.  See the NOTICE file 
 * distributed with this work for additional information 
 * regarding copyright ownership.  The ASF licenses this file 
 * to you under the Apache License, Version 2.0 (the 
 * "License"); you may not use this file except in compliance 
 * with the License.  You may obtain a copy of the License at 
 * 
 *	http://www.apache.org/licenses/LICENSE-2.0 
 * 
 *	Unless required by applicable law or agreed to in writing, 
 *	software distributed under the License is distributed on an 
 *	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY 
 *	KIND, either express or implied.  See the License for the 
 *	specific language governing permissions and limitations 
 *	under the License. 
 * 
 * 
 * @package    org.modelio.togaf. 
 * @author     Modelio 
 * @license    http://www.apache.org/licenses/LICENSE-2.0 
 * @version  1.0.00 
 **/
package org.modelio.togaf.profile.utils;

import java.io.File;

import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

public class ResourceManager {

	public static String getName(String string) {
		return org.modelio.togaf.i18n.Messages.getString(string + "_NAME");
	}

	public static String getError(String string) {
		return org.modelio.togaf.i18n.Messages.getString(string + "_ERROR");
	}

	public static String getCommandeName(String string) {
		return org.modelio.togaf.i18n.Messages.getString(string + "_COMMANDENAME");
	}

	public static String getImage(Stereotype stereotype) {
	    return  stereotype.getIcon();
	}

	public static String getCommandeToolType(String string) {
		return org.modelio.togaf.i18n.Messages.getString(string + "_TOOLTYPE");
	}

	public static String getPropertyName(String string) {
		return org.modelio.togaf.i18n.Messages.getString(string + "_NAME");
	}

	public static File getStyle(String string) {
		IPeerModule peer = Module.getPeer();
		return peer.getConfiguration().getModuleResourcesPath().resolve(string).toFile();
	}

}
