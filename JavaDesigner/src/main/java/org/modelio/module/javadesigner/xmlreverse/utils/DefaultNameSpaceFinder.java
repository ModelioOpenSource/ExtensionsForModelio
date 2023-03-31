package org.modelio.module.javadesigner.xmlreverse.utils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.module.javadesigner.xmlreverse.INameSpaceFinder;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.vcore.smkernel.mapi.MObject;

public class DefaultNameSpaceFinder implements INameSpaceFinder {

    @Override
    public MObject createNamespace(final JaxbDestination destination, final ModelTree root, final Class<? extends Classifier> classifierType, final IModelingSession session) {
        String npackage = destination.getPackage();
        ModelTree new_package;
        // Create a package if necessary
        if (npackage.isEmpty()) {
            new_package = root;
        } else {
            new_package = createPackages(npackage, root, session);
        }
        if (new_package != null) {
            // Create a class if necessary
            if (destination.getClazz() == null) {
                return new_package;
            } else {
                // If we found a read only package, create the appropriate package namespacing
                if (new_package.getStatus().isRamc() || (!new_package.getStatus().isCmsManaged() && !new_package.getStatus().isModifiable())) {
                    ModelTree relativeroot = root;
                    for (String pc : npackage.split("\\.")) {
                        new_package = createPackage(pc, relativeroot, session);
                        relativeroot = new_package;
                    }
                }
                Classifier new_classifier = createClassifiers(destination.getClazz(), new_package, classifierType, session);
                if (new_classifier != null) {
                    // Create a feature if necessary
                    if (destination.getFeature() == null) {
                        return new_classifier;
                    } else {
                        Operation new_operation = createOperation(destination.getFeature(), new_classifier, session);
                        if (new_operation != null) {
                            // Create a parameter if necessary
                            if (destination.getParameter() == null) {
                                return new_operation;
                            } else {
                                return createParameter(destination.getParameter(), new_operation, session);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    protected Parameter createParameter(final String nparameter, final Operation root, final IModelingSession session) {
        IUmlModel model = session.getModel();
        Parameter new_parameter = null;
        for (Parameter sub : root.getIO()) {
            if (sub.getName().equals(nparameter)) {
                new_parameter = sub;
                break;
            }
        }

        if (new_parameter == null) {
            new_parameter = model.createParameter();
            new_parameter.setName(nparameter);
            new_parameter.setComposed(root);
        }
        return new_parameter;
    }

    protected Operation createOperation(final String feature, final Classifier root, final IModelingSession session) {
        IUmlModel model = session.getModel();
        Operation new_operation = null;
        for (Operation sub : root.getOwnedOperation()) {
            if (sub.getName().equals(feature)) {
                new_operation = sub;
                break;
            }
        }

        if (new_operation == null) {
            new_operation = model.createOperation();
            new_operation.setName(feature);
            new_operation.setOwner(root);
        }
        return new_operation;
    }

    protected Classifier createClassifier(final String nclass, final ModelTree owner, final Class<? extends Classifier> classifierType, final IModelingSession session) {
        if (classifierType == null) {
            return createClass(nclass, owner, session);
        } else if (classifierType.equals(Component.class)) {
            return createComponent(nclass, owner, session);
        } else if (classifierType.equals(Class.class)) {
            return createClass(nclass, owner, session);
        } else if (classifierType.equals(Interface.class)) {
            return createInterface(nclass, owner, session);
        } else if (classifierType.equals(Enumeration.class)) {
            return createEnumeration(nclass, owner, session);
        } else if (classifierType.equals(DataType.class)) {
            return createDataType(nclass, owner, session);
        } else { // Assume its a class...
            return createClass(nclass, owner, session);
        }
    }

    protected org.modelio.metamodel.uml.statik.Class createClass(final String nclass, final ModelTree owner, final IModelingSession session) {
        IUmlModel model = session.getModel();

        // Throw an error when creating elements in a ramc to avoid creating an orphan
        if (owner != null && owner.getStatus().isRamc()) {
            throw new InvalidParameterException();
        }

        org.modelio.metamodel.uml.statik.Class new_element = model.createClass();
        new_element.setName(nclass);
        new_element.setOwner(owner);
        return new_element;
    }

    protected Enumeration createEnumeration(final String nclass, final ModelTree owner, final IModelingSession session) {
        IUmlModel model = session.getModel();

        // Throw an error when creating elements in a ramc to avoid creating an orphan
        if (owner != null && owner.getStatus().isRamc()) {
            throw new InvalidParameterException();
        }

        Enumeration new_element = model.createEnumeration();
        new_element.setName(nclass);
        new_element.setOwner(owner);
        return new_element;
    }

    protected Component createComponent(final String nclass, final ModelTree owner, final IModelingSession session) {
        IUmlModel model = session.getModel();

        // Throw an error when creating elements in a ramc to avoid creating an orphan
        if (owner != null && owner.getStatus().isRamc()) {
            throw new InvalidParameterException();
        }

        Component new_element = model.createComponent();
        new_element.setName(nclass);
        new_element.setOwner(owner);
        return new_element;
    }

    protected DataType createDataType(final String nclass, final ModelTree owner, final IModelingSession session) {
        IUmlModel model = session.getModel();

        // Throw an error when creating elements in a ramc to avoid creating an orphan
        if (owner != null && owner.getStatus().isRamc()) {
            throw new InvalidParameterException();
        }

        DataType new_element = model.createDataType();
        new_element.setName(nclass);
        new_element.setOwner(owner);
        return new_element;
    }

    protected Interface createInterface(final String nclass, final ModelTree owner, final IModelingSession session) {
        IUmlModel model = session.getModel();

        // Throw an error when creating elements in a ramc to avoid creating an orphan
        if (owner != null && owner.getStatus().isRamc()) {
            throw new InvalidParameterException();
        }

        Interface new_element = model.createInterface();
        new_element.setName(nclass);
        new_element.setOwner(owner);
        return new_element;
    }

    protected Package createPackage(final String npackage, final ModelTree owner, final IModelingSession session) {
        IUmlModel model = session.getModel();

        // Throw an error when creating elements in a ramc to avoid creating an orphan
        if (owner != null && owner.getStatus().isRamc()) {
            throw new InvalidParameterException();
        }

        Package new_element = model.createPackage();
        new_element.setName(npackage);
        new_element.setOwner(owner);
        return new_element;
    }

    protected Package createPackages(final String npackage, final ModelTree root, final IModelingSession session) {
        String[] packages = npackage.split("\\.");

        // Empty namespace means no package to create
        if (packages.length == 0) {
            return null;
        }

        ModelTree curent = root;
        for (int i = 0; i < packages.length; i++) {
            Package new_package = null;
            for (ModelTree sub : curent.getOwnedElement(Package.class)) {
                if (sub.getName().equals(packages[i])) {
                    new_package = (Package) sub;
                    break;
                }
            }

            if (new_package == null) {
                new_package = createPackage(packages[i], curent, session);
            }

            curent = new_package;
        }
        return (Package) curent;
    }

    protected Classifier createClassifiers(final String nclass, final ModelTree root, final Class<? extends Classifier> type, final IModelingSession session) {
        ModelTree current = root;
        String[] composed_class = nclass.split("\\.");

        if (composed_class.length == 0) {
            current = createClassifier(nclass, current, type, session);
        } else {
            for (int i = 0; i < composed_class.length; i++) {
                ModelTree new_tree = null;
                for (ModelTree sub : current.getOwnedElement()) {
                    if (sub instanceof Classifier) {
                        // Check name
                        if (sub.getName().equals(composed_class[i])) {
                            // No inner classes for enumerations
                            if (i < composed_class.length - 1 && (sub instanceof Enumeration)) {
                                continue;
                            } else // For the last part of the name, try to match the type
                            if (i < composed_class.length - 1 || (type == null || type.isInstance(sub))) {
                                new_tree = sub;
                            }
                        }
                    }
                }

                if (new_tree == null) {
                    new_tree = createClassifier(composed_class[i], current, type, session);

                }
                current = new_tree;
            }

        }
        return (Classifier) current;
    }

    @Override
    public String getNamespace(final ModelTree element, final IModelingSession session) {
        return getNamespace(element.getOwner(), element.getName(), session);
    }

    private String getNamespace(final ModelTree element, final String namespace, final IModelingSession session) {
        if (element != null && element.getOwner() != null && element instanceof Package) {
            return getNamespace(element.getOwner(), element.getName() + "." + namespace, session);
        }
        return namespace;
    }

    @Override
    public List<MObject> getElementByNamespace(final JaxbDestination destination, final Class<? extends Classifier> classifierType, final IModelingSession session) {
        List<MObject> ret = new ArrayList<>();

        // Package
        for (Package dest_package : getPackageByNamespace(destination.getPackage(), session)) {
            if (dest_package != null && destination.getClazz() == null) {
                ret.add(dest_package);
            } else {
                if (dest_package == null || dest_package.isDeleted()) {
                    continue;
                }

                // Class
                for (ModelTree dest_classifier : getClassifierByNamespace(destination.getClazz(), dest_package, classifierType, session)) {
                    if (dest_classifier != null) {
                        if (destination.getFeature() == null) {
                            ret.add(dest_classifier);
                        } else if (dest_classifier instanceof Classifier) {
                            Feature dest_feature = getFeatureByNamespace(destination.getFeature(), (Classifier) dest_classifier);
                            if (dest_feature != null) {
                                if (destination.getParameter() == null || !(dest_feature instanceof Operation)) {
                                    ret.add(dest_feature);
                                } else {
                                    Parameter dest_parameter = getParameterByNamespace(destination.getParameter(), (Operation) dest_feature);
                                    ret.add(dest_parameter);
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    private Parameter getParameterByNamespace(final String nparameter, final Operation dest_feature) {
        for (Parameter parameter : dest_feature.getIO()) {
            if (parameter.getName().equals(nparameter)) {
                return parameter;
            }
        }
        return null;
    }

    protected Feature getFeatureByNamespace(final String feature, final Classifier classifier) {
        for (Feature feat : classifier.getOwnedAttribute()) {
            if (feat.getName().equals(feature)) {
                return feat;
            }
        }
        for (Feature feat : classifier.getOwnedOperation()) {
            if (feat.getName().equals(feature)) {
                return feat;
            }
        }
        for (Feature feat : classifier.getOwnedEnd()) {
            if (feat.getName().equals(feature)) {
                return feat;
            }
        }
        return null;
    }

    protected List<Package> getPackageByNamespace(final String npackage, final IModelingSession session) {
        List<Package> ret = new ArrayList<>();

        String packageToFind;
        String[] composed_packages = npackage.split("\\.");
        if (composed_packages.length > 0) {
            // Take the last package
            packageToFind = composed_packages[composed_packages.length - 1];
        } else {
            packageToFind = "";
        }

        Collection<Package> elements = session.findByAtt(Package.class, "Name", packageToFind);
        for (MObject currentElement : elements) {
            Package currentPackage = (Package) currentElement;
            if (npackage.matches(getNamespace(currentPackage, session)) && !currentPackage.isDeleted()) {
                ret.add(currentPackage);
            }
        }
        return ret;
    }

    protected List<ModelTree> getClassifierByNamespace(final String nclass, final Package initialDest_package, final Class<? extends Classifier> initialClassifierType, final IModelingSession session) {
        List<ModelTree> ret = new ArrayList<>();

        Class<? extends Classifier> classifierType = initialClassifierType;
        if (classifierType == null) {
            classifierType = Classifier.class;
        }

        // Class without namespace are to be found in the root packages
        List<Package> dest_packages;
        if (initialDest_package != null) {
            dest_packages = Arrays.asList(initialDest_package);
        } else {
            dest_packages = new ArrayList<>();
            for (Project project : session.findByClass(Project.class)) {
                dest_packages.addAll(project.getModel());
            }
        }

        for (Package dest_package : dest_packages) {
            String[] composed_class = nclass.split("\\.");
            if (composed_class.length == 0) {
                for (ModelTree element : dest_package.getOwnedElement()) {
                    if (element instanceof Classifier) {
                        if (element.getName().equals(nclass) && (classifierType.isInstance(element))) {
                            ret.add(element);
                        }
                    }
                }
            } else {
                ModelTree model_tree = dest_package;

                for (int i = 0; i < composed_class.length; i++) {
                    boolean find = false;
                    for (ModelTree sub_tree : model_tree.getOwnedElement()) {
                        if (sub_tree instanceof Classifier) {
                            // Check element name
                            if (sub_tree.getName().equals(composed_class[i])) {
                                // For the last part of the namespace, match the type
                                if (i < composed_class.length - 1 || (classifierType.isInstance(sub_tree))) {
                                    model_tree = sub_tree;
                                    find = true;
                                    break;
                                }
                            }
                        }
                    }
                    // MObject of the namespacing not found, no need to continue
                    if (!find) {
                        return ret;
                    }
                }

                if (model_tree instanceof Classifier) {
                    ret.add(model_tree);
                }
            }
        }
        return ret;
    }

    @Override
    public <T extends MObject> T resolveMultipleNamespaces(final List<T> possibleElements) {
        if (possibleElements.isEmpty()) {
            return null;
        } else {
            return possibleElements.get(0);
        }
    }

    @Override
    public MObject getElementByNameSpace(final String targetFullName, final Class<? extends Classifier> classifierType, final IModelingSession session) {
        String targetName;

        Class<? extends ModelElement> toFind;
        if (classifierType == null) {
            toFind = ModelTree.class;
        } else {
            toFind = classifierType;
        }

        int lastIndex = targetFullName.lastIndexOf(".");
        if (lastIndex > 0) {
            targetName = targetFullName.substring(lastIndex);
        } else {
            targetName = targetFullName;
        }

        List<MObject> ret = new ArrayList<>();
        Collection<? extends ModelElement> possibleElements = session.findByAtt(toFind, "Name", targetName);
        for (MObject element : possibleElements) {
            ModelTree currentElement = (ModelTree) element;
            if (targetFullName.matches(getNamespace(currentElement, session))) {
                ret.add(currentElement);
            }
        }
        return resolveMultipleNamespaces(ret);
    }

}
