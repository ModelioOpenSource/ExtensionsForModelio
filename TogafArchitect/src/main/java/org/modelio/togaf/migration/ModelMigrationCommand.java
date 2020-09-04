package org.modelio.togaf.migration;

import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.migration.migration37.Migration_3610_3700;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class is in charge of migrating Togaf old annotation models to the
 * latest one.
 * <p/>
 * 3.6 to 3.7 is currently the only supported migration, replacing every
 * {@link TaggedValue} with a {@link PropertyTableDefinition}.
 */
public class ModelMigrationCommand extends DefaultModuleCommandHandler {
    /**
     * Default version for a {@link Project} having no
     * {@link MODELIOSTUDIO_MODEL_VERSION} tag.
     */
    private static Version DEFAULT_VERSION = new Version(3, 7, 0);

    /**
     * Command is displayed when:
     * <ul>
     * <li>Selection only contains {@link Project} or {@link Package}.</li>
     * <li>At least one project has a version older than the
     * {@link #LATEST_PROJECT_VERSION}.</li>
     * </ul>
     */
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if (super.accept(selectedElements, module) == false) {
            return false;
        }
        
        if (selectedElements.size() < 1) {
            return false;
        }
        
        for (MObject selectedElement : selectedElements) {
            Project selectedProject;
            if (selectedElement instanceof Project) {
                selectedProject = ((Project) selectedElement);
            } else {
                Package selectedPackage = (Package) selectedElement;
                selectedProject = selectedPackage.getRepresented();
                if (selectedProject == null) {
                    // Not the root package
                    return false;
                }
            }
        
            Version projectVersion = getProjectVersion(selectedProject);
            if (projectVersion.isOlderThan(TogafArchitectModule.LATEST_PROJECT_VERSION)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Migrate Togaf's annotation model from old versions to the latest one.
     */
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
        try (ITransaction tr = session.createTransaction("Migrate Togaf annotations")) {
            for (MObject selectedElement : selectedElements) {
                Project selectedProject;
                if (selectedElement instanceof Project) {
                    selectedProject = ((Project) selectedElement);
                } else {
                    Package selectedPackage = (Package) selectedElement;
                    selectedProject = selectedPackage.getRepresented();
                }
        
                Version projectVersion = getProjectVersion(selectedProject);
                if (projectVersion.isOlderThan(new Version(3, 7, 0))) {
                    Migration_3610_3700 migration = new Migration_3610_3700(selectedProject);
                    migration.migrate();
                }
        
                // Update model version
                selectedProject.putTagValue(TogafArchitectModule.MODULE_NAME, "togaf.modelVersion", TogafArchitectModule.LATEST_PROJECT_VERSION.toString());
            }
            tr.commit();
        } catch (Exception e) {
            TogafArchitectModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }



    /**
     * Get the project's version from its {@link MODELIOSTUDIO_MODEL_VERSION}
     * tag. If no tag is found, returns {@link DEFAULT_VERSION}.
     * @param current the project to get the version from.
     */
    private Version getProjectVersion(Project current) {
        String modelVersion = current.getTagValue(TogafArchitectModule.MODULE_NAME, "togaf.modelVersion");
        if (modelVersion == null) {
            return ModelMigrationCommand.DEFAULT_VERSION;
        }
        return new Version(modelVersion);
    }

    /**
     * Command is grayed when:
     * <ul>
     * <li>At least one project has a version older than the
     * {@link #LATEST_PROJECT_VERSION} and can't be modified.</li>
     * </ul>
     */
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        if (super.isActiveFor(selectedElements, module) == false) {
            return false;
        }
        
        for (MObject selectedElement : selectedElements) {
            Project selectedProject;
            if (selectedElement instanceof Project) {
                selectedProject = ((Project) selectedElement);
            } else {
                Package selectedPackage = (Package) selectedElement;
                selectedProject = selectedPackage.getRepresented();
                if (selectedProject == null) {
                    // Not the root package
                    return false;
                }
            }
        
            Version projectVersion = getProjectVersion(selectedProject);
            if (projectVersion.isOlderThan(TogafArchitectModule.LATEST_PROJECT_VERSION) && !selectedProject.isModifiable()) {
                return false;
            }
        }
        return true;
    }


}
