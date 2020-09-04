package org.modelio.togaf.profile.utils;

import org.modelio.api.module.IPeerModule;
import org.modelio.togaf.impl.TogafArchitectModule;

public class Module {
	
	public static IPeerModule getPeer(){
		try{
			return TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getModuleService().getPeerModule("TogafArchitect");
		}catch(Exception e){
			
		}
		
		
		try{
			return TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getModuleService().getPeerModule("EABPM");
		}catch(Exception e){
			
		}
	
		return null;	
	}

}
