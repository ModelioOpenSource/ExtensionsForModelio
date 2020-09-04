package org.modelio.togaf.conf;

import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.i18n.Messages;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.applicationarchitecture.model.ApplicationArchitectureDomain;
import org.modelio.togaf.profile.businessarchitecture.model.BusinessOrganizationDomain;
import org.modelio.togaf.profile.structure.model.ApplicationArchitecture;
import org.modelio.togaf.profile.structure.model.ApplicationLayer;
import org.modelio.togaf.profile.structure.model.BusinessArchitecture;
import org.modelio.togaf.profile.structure.model.BusinessEntities;
import org.modelio.togaf.profile.structure.model.BusinessLayer;
import org.modelio.togaf.profile.structure.model.LogicalDataModel;
import org.modelio.togaf.profile.structure.model.TechnologyArchitecture;
import org.modelio.vcore.smkernel.mapi.MObject;

public class TogafModelContributor {

	public void createInitialModel() {
		ApplicationLayer applilayer = null;
		TechnologyArchitecture techno = null;
		BusinessLayer business = null;


		Package root = null;
		for(MObject obj : TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().getModelRoots()){
			if(obj instanceof Project){
				if(!((Project)obj).getModel().isEmpty()){
					root = ((Project)obj).getModel().get(0);
				}
				
				try {
				    
				    String version = ((Project)obj).getTagValue(TogafArchitectModule.MODULE_NAME, "togaf.modelVersion");
				    if(version == null || "".equals(version)){
				        ((Project)obj).putTagValue(TogafArchitectModule.MODULE_NAME, "togaf.modelVersion", TogafArchitectModule.LATEST_PROJECT_VERSION.toString());
				    }
                } catch (ExtensionNotFoundException e) {
                    e.printStackTrace();
                }
			}
		}

		if (root == null) {
			return;
		}

		for (ModelElement sub : root.getOwnedElement()) {
			if (sub.isStereotyped("TogafArchitect","BusinessLayer")) {
				business = new BusinessLayer((Package) sub);
			} else if (sub.isStereotyped("TogafArchitect","ApplicationLayer")) {
				applilayer = new ApplicationLayer((Package) sub);
			} else if (sub.isStereotyped("TogafArchitect","TechnologyArchitecture")) {
				techno = new TechnologyArchitecture((Package) sub);
			}
		}

		try {
			if(!root.isStereotyped("TogafArchitect", "TogafRoot")){
				root.addStereotype("TogafArchitect", "TogafRoot");
			}
			if (business == null) {
				business = new BusinessLayer();
				business.setParent(root);
				business.getElement().putNoteContent("ModelerModule", "description",Messages.getString("BusinessLayer_Template"));

				BusinessEntities entities = new BusinessEntities();
				entities.setParent(business.getElement());
				entities.getElement().putNoteContent("ModelerModule", "description",Messages.getString("BusinessEntities_Template"));

				BusinessArchitecture architecture = new BusinessArchitecture();
				architecture.setParent(business.getElement());
				architecture.getElement().putNoteContent("ModelerModule", "description",Messages.getString("BusinessArchitecture_Template"));

				BusinessOrganizationDomain organization = new BusinessOrganizationDomain();
				organization.getElement().setName("Organization");
				organization.setParent(architecture.getElement());
				organization.getElement().putNoteContent("ModelerModule", "description",Messages.getString("Organization_Template"));

				BusinessOrganizationDomain processes = new BusinessOrganizationDomain();
				processes.getElement().setName("Processes");
				processes.setParent(architecture.getElement());
				processes.getElement().putNoteContent("ModelerModule", "description",Messages.getString("Processes_Template"));

				BusinessOrganizationDomain businessfunction = new BusinessOrganizationDomain();
				businessfunction.getElement().setName("Business Function");
				businessfunction.setParent(architecture.getElement());
				businessfunction.getElement().putNoteContent("ModelerModule", "description",Messages.getString("Function_Template"));

				BusinessOrganizationDomain locations = new BusinessOrganizationDomain();
				locations.getElement().setName("Locations");
				locations.setParent(architecture.getElement());
				locations.getElement().putNoteContent("ModelerModule", "description",Messages.getString("Locations_Template"));
			}
			if (applilayer == null) {
				applilayer = new ApplicationLayer();
				applilayer.setParent(root);
				applilayer.getElement().putNoteContent("ModelerModule", "description",Messages.getString("ApplicationLayer_Template"));

				LogicalDataModel data = new LogicalDataModel();
				data.setParent(applilayer.getElement());
				data.getElement().putNoteContent("ModelerModule", "description",Messages.getString("Data_Template"));

				ApplicationArchitecture appliarchi = new ApplicationArchitecture();
				appliarchi.setParent(applilayer.getElement());
				appliarchi.getElement().putNoteContent("ModelerModule", "description",Messages.getString("ApplicationArchitecture_Template"));

				ApplicationArchitectureDomain servicedata = new ApplicationArchitectureDomain();
				servicedata.getElement().setName("Service Data");
				servicedata.setParent(appliarchi.getElement());
				servicedata.getElement().putNoteContent("ModelerModule", "description",Messages.getString("Service_Template"));

				ApplicationArchitectureDomain systemeusecase = new ApplicationArchitectureDomain();
				systemeusecase.getElement().setName("System Use Case");
				systemeusecase.setParent(appliarchi.getElement());
				systemeusecase.getElement().putNoteContent("ModelerModule", "description",Messages.getString("Systeme_Template"));
			}

			if (techno == null) {
				techno = new TechnologyArchitecture();
				techno.setParent(root);
				techno.getElement().putNoteContent("ModelerModule", "description",Messages.getString("TechnologyArchitecture_Template"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
