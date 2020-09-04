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
package org.modelio.togaf.properties;

public enum HabilitationKind {

	C___("C---"), CR__("CR--"), CRU_("CRU-"), CRUD("CRUD"), _R__("-R--"), _RU_("-RU-"), _RUD("-RUD"), __U_("--U-"), __UD("--UD"), ___D("---D"), C_U_("C-U-"), C__D("C--D"), C_UD("C-UD"), _R_D("-R-D"), none("none");

	private String value;

	private HabilitationKind(String value) {
		this.value = value;
	}

	public static String[] getValues() {
		return new String[] { "none", "C---", "CR--", "C-U-", "C--D", "CRU-", "C-UD", "-R--", "-RU-", "--U-", "-RUD", "--UD", "---D" };
	}

	public String getValue() {
		return this.value;
	}

	public static HabilitationKind getHabilitationKind(String value) {
		if (value.equals(CRUD.getValue())) {
			return CRUD;
		} else if (value.equals(C___.getValue())) {
			return C___;
		} else if (value.equals(CR__.getValue())) {
			return CR__;
		} else if (value.equals(C_U_.getValue())) {
			return C_U_;
		} else if (value.equals(CRU_.getValue())) {
			return CRU_;
		} else if (value.equals(_R__.getValue())) {
			return _R__;
		} else if (value.equals(_RU_.getValue())) {
			return _RU_;
		} else if (value.equals(_R_D.getValue())) {
			return _R_D;
		} else if (value.equals(__U_.getValue())) {
			return __U_;
		} else if (value.equals(_RUD.getValue())) {
			return _RUD;
		} else if (value.equals(__UD.getValue())) {
			return __UD;
		} else if (value.equals(___D.getValue())) {
			return ___D;
		}
		return none;
	}

}
