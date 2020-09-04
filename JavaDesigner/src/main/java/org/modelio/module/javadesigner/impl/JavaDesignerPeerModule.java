package org.modelio.module.javadesigner.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.ant.AntExecutor;
import org.modelio.module.javadesigner.ant.AntGenerator;
import org.modelio.module.javadesigner.api.CustomException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.automation.AccessorManager;
import org.modelio.module.javadesigner.dialog.InfoDialogManager;
import org.modelio.module.javadesigner.dialog.JConsoleWithDialog;
import org.modelio.module.javadesigner.editor.EditorManager;
import org.modelio.module.javadesigner.generator.Generator;
import org.modelio.module.javadesigner.javadoc.JavadocManager;
import org.modelio.module.javadesigner.report.ReportManager;
import org.modelio.module.javadesigner.report.ReportModel;
import org.modelio.module.javadesigner.reverse.Reversor;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaDesignerPeerModule implements IJavaDesignerPeerModule {
    protected JavaDesignerModule module;

    protected IModuleAPIConfiguration peerConfiguration;

    public JavaDesignerPeerModule(final JavaDesignerModule module, final IModuleAPIConfiguration peerConfiguration) {
        this.module = module;
        this.peerConfiguration = peerConfiguration;
    }

    @Override
    public IModuleAPIConfiguration getConfiguration() {
        return this.peerConfiguration;
    }

    @Override
    public String getDescription() {
        return this.module.getDescription ();
    }

    @Override
    public String getName() {
        return this.module.getName ();
    }

    @Override
    public Version getVersion() {
        return this.module.getVersion ();
    }

    @Override
    public void generate(final NameSpace element, final boolean withGUI) {
        HashSet<NameSpace> elements = new HashSet<> ();
        elements.add (element);
        this.generate (elements, withGUI);
    }

    @Override
    public void generate(final Collection<NameSpace> elements, final boolean withGUI) {
        try {
            JavaDesignerUtils.initCurrentGenRoot (elements);
        } catch (InterruptedException e) {
            return;
        }
        
        ReportModel report = ReportManager.getNewReport ();
        
        Generator generator = new Generator (JavaDesignerUtils.getAllComponentsToTreat (elements, this.module), this.module);
        generator.generate (report);
        
        if (withGUI) {
            ReportManager.showGenerationReport (report);
        } else {
            ReportManager.printGenerationReport (report);
        }
        
        JavaDesignerUtils.setProjectGenRoot (null);
    }

    @Override
    public void generateAntFile(final Artifact artifact, final boolean withGUI) {
        AntGenerator antGenerator;
        if (withGUI) {
            antGenerator = new AntGenerator(this.module, new JConsoleWithDialog (InfoDialogManager.getExecuteAntTargetDialog ()));
        } else {
            antGenerator = new AntGenerator(this.module);
        }
        
        antGenerator.generateBuildXmlFile (artifact);
    }

    @Override
    public String executeTarget(final Artifact artifact, final String target, final boolean withGUI) {
        AntExecutor antGenerator;
        if (withGUI) {
            antGenerator = new AntExecutor(this.module, new JConsoleWithDialog (InfoDialogManager.getExecuteAntTargetDialog ()));
        } else {
            antGenerator = new AntExecutor(this.module);
        }
        antGenerator.executeTarget(artifact, target);
        return "";
    }

    @Override
    public void updateModel(final Collection<NameSpace> elements, final boolean withGUI) {
        try {
            JavaDesignerUtils.initCurrentGenRoot (elements);
        } catch (InterruptedException e) {
            return;
        }
        
        ReportModel report = ReportManager.getNewReport ();
        
        Reversor reversor = new Reversor (this.module, report);
        Set<NameSpace> elementsToUpdate = new HashSet<> ();
        
        for (NameSpace element : elements) {
            NameSpace producingParent = JavaDesignerUtils.getNearestNameSpace (element);
            if (producingParent != null) {
                elementsToUpdate.add (producingParent);
            }
        }
        
        reversor.update (JavaDesignerUtils.getAllComponentsToTreat (elementsToUpdate, this.module), EditorManager.getInstance ());
        
        if (withGUI) {
            ReportManager.showGenerationReport (report);
        } else {
            ReportManager.printGenerationReport (report);
        }
        
        JavaDesignerUtils.setProjectGenRoot (null);
    }

    @Override
    public File getFilename(final NameSpace element) {
        return JavaDesignerUtils.getFilename (element, this.module);
    }

    @Override
    public String executeTarget(final Artifact artifact, final String target) {
        return executeTarget (artifact, target, true);
    }

    @Override
    public void generate(final NameSpace element) {
        generate (element, true);
    }

    @Override
    public void generate(final Collection<NameSpace> elements) {
        generate (elements, true);
    }

    @Override
    public void generateAntFile(final Artifact artifact) {
        generateAntFile (artifact, true);
    }

    @Override
    public void updateModel(final Collection<NameSpace> elements) {
        updateModel (elements, true);
    }

    @Override
    public boolean deleteAccessors(final Classifier theClassifier) {
        IModuleUserConfiguration javaConfig = this.module.getModuleContext().getConfiguration ();
        
        AccessorManager accessorManager = new AccessorManager (this.module.getModuleContext().getModelingSession());
        accessorManager.init (javaConfig);
        accessorManager.deleteAccessors(theClassifier);
        return true;
    }

    @Override
    public boolean updateAccessors(final Attribute theAttribute, final boolean createNewAccessors) throws CustomException, ElementNotUniqueException, ExtensionNotFoundException {
        if (!theAttribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
            IModuleUserConfiguration javaConfig = this.module.getModuleContext().getConfiguration ();
        
            AccessorManager accessorManager = new AccessorManager (this.module.getModuleContext().getModelingSession());
            accessorManager.init (javaConfig);
            accessorManager.updateAccessors (theAttribute, createNewAccessors);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateAccessors(final AssociationEnd theAssociationEnd, final boolean createNewAccessors) throws CustomException, ElementNotUniqueException, ExtensionNotFoundException {
        if (!theAssociationEnd.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
            IModuleUserConfiguration javaConfig = this.module.getModuleContext().getConfiguration ();
        
            AccessorManager accessorManager = new AccessorManager (this.module.getModuleContext().getModelingSession());
            accessorManager.init (javaConfig);
            accessorManager.updateAccessors (theAssociationEnd, createNewAccessors);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void generateJavaDoc(final Package element, final boolean withGUI) {
        JavadocManager javadocManager;
        if (withGUI) {
            javadocManager = new JavadocManager(this.module, new JConsoleWithDialog (InfoDialogManager.getJavaDocDialog ()));
        } else {
            javadocManager = new JavadocManager(this.module);
        }
        
        List<MObject> selectedElements = new ArrayList<>();
        selectedElements.add(element);
        javadocManager.generateDoc(selectedElements);
    }

}
