package org.modelio.module.javadesigner.reverse.xmltomodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modelio.module.xmlreverse.model.JaxbDestination;
import com.modelio.module.xmlreverse.utils.DefaultNameSpaceFinder;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.JavaElementStereotypeCreator;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaNameSpaceFinder extends DefaultNameSpaceFinder {
    private NameSpace reverseRoot;

    private Map<String, List<Package>> packageCache;

    private Collection<Classifier> externalClassifiers;

    public JavaNameSpaceFinder(final NameSpace reverseRoot) {
        this.reverseRoot = reverseRoot;
        this.packageCache = new HashMap<>();
        this.externalClassifiers = new ArrayList<>();
    }

    public Collection<Classifier> getExternalClassifiers() {
        return this.externalClassifiers;
    }

    @Override
    public String getNamespace(final ModelTree element, final IModelingSession session) {
        return JavaDesignerUtils.getFullJavaName (session, element);
    }

    @Override
    public <T extends MObject> T resolveMultipleNamespaces(final List<T> possibleElements) {
        // Try to find an element in the reverseRoot
        for (T obElement : possibleElements) {
            if (isOwner (obElement, this.reverseRoot)) {
                return obElement;
            }
        }
        
        // No element found, let the super class choose one namespace
        return super.resolveMultipleNamespaces (possibleElements);
    }

    public static boolean isOwner(final MObject element, final ModelTree potentialParent) {
        MObject currentParent = null;
        if (element instanceof ModelTree) {
            currentParent = ((ModelTree) element).getOwner ();
        } else if (element instanceof Feature) {
            currentParent = (((Feature) element).getCompositionOwner ());
        } else if (element instanceof EnumerationLiteral) {
            currentParent = (((EnumerationLiteral) element).getValuated ());
        }
        
        // If this is a plugin or a model component, we shouldn't look the root package...
        if (currentParent instanceof Component) {
            if (JavaDesignerUtils.isAJavaComponent ((Component) currentParent) ||
                    JavaDesignerUtils.isAModelComponent ((Component) currentParent)) {
                return potentialParent.equals (currentParent);
            }
        }
        return currentParent != null &&
                                                (potentialParent.equals (currentParent) || isOwner (currentParent, potentialParent));
    }

    @Override
    protected Package createPackage(final String npackage, final ModelTree owner, final IModelingSession session) {
        Package newPackage = super.createPackage (npackage, owner, session);
        if (newPackage != null) {
            JavaElementStereotypeCreator.addJavaStereotype (newPackage);
        
            try {
                ModelUtils.setTaggedValue (session, newPackage, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PACKAGE_JAVAEXTERN, true);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
            }
        }
        return newPackage;
    }

    @Override
    protected Class createClass(final String nclass, final ModelTree owner, final IModelingSession session) {
        Class newElement = super.createClass (nclass, owner, session);
        if (newElement != null) {
            JavaElementStereotypeCreator.addJavaStereotype (newElement);
        
            try {
                ModelUtils.setTaggedValue (session, newElement, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PACKAGE_JAVAEXTERN, true);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
            }
        
            this.externalClassifiers.add(newElement);
        }
        return newElement;
    }

    @Override
    protected Enumeration createEnumeration(final String nclass, final ModelTree owner, final IModelingSession session) {
        Enumeration newElement = super.createEnumeration (nclass, owner, session);
        if (newElement != null) {
            JavaElementStereotypeCreator.addJavaStereotype (newElement);
        
            try {
                ModelUtils.setTaggedValue (session, newElement, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PACKAGE_JAVAEXTERN, true);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
            }
        
            this.externalClassifiers.add(newElement);
        }
        return newElement;
    }

    @Override
    protected Interface createInterface(final String nclass, final ModelTree owner, final IModelingSession session) {
        Interface newElement = super.createInterface (nclass, owner, session);
        if (newElement != null) {
            JavaElementStereotypeCreator.addJavaStereotype (newElement);
        
            try {
                ModelUtils.setTaggedValue (session, newElement, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PACKAGE_JAVAEXTERN, true);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
            }
        
            this.externalClassifiers.add(newElement);
        }
        return newElement;
    }

    @Override
    protected DataType createDataType(final String nclass, final ModelTree owner, final IModelingSession session) {
        DataType newElement = super.createDataType (nclass, owner, session);
        if (newElement != null) {
            JavaElementStereotypeCreator.addJavaStereotype (newElement);
        
            try {
                ModelUtils.setTaggedValue (session, newElement, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PACKAGE_JAVAEXTERN, true);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
            }
        
            this.externalClassifiers.add(newElement);
        }
        return newElement;
    }

    @Override
    public MObject getElementByNameSpace(final String targetFullName, final java.lang.Class<? extends Classifier> classifierType, final IModelingSession session) {
        if ("java.util.Date".equals (targetFullName)) {
            return session.getModel ().getUmlTypes ().getDATE();
        }
        
        java.lang.Class<? extends MObject> metaclass;
        String targetName;
        
        int index = targetFullName.indexOf ("#");
        if (index > 0) { // Find a feature
            metaclass = classifierType != null && Feature.class.isAssignableFrom(classifierType)? classifierType : Feature.class;
            targetName = targetFullName.substring (index + 1);
        } else { // Find a namespace
            metaclass = classifierType != null && NameSpace.class.isAssignableFrom(classifierType)? classifierType : NameSpace.class;
        
            int lastIndex = targetFullName.lastIndexOf (".");
            if (lastIndex > 0) {
                targetName = targetFullName.substring (lastIndex + 1);
            } else {
                targetName = targetFullName;
            }
        }
        
        List<MObject> ret = new ArrayList<> ();
        
        for (MObject element : JavaDesignerModule.getInstance().getModuleContext().getModelingSession().findByAtt (metaclass, "Name", targetName)) {
            ModelElement currentElement = (ModelElement) element;
            if (targetFullName.matches (JavaDesignerUtils.getFullJavaName (session, currentElement))) {
                ret.add (currentElement);
            }
        }
        return resolveMultipleNamespaces (ret);
    }

    @Override
    public List<MObject> getElementByNamespace(final JaxbDestination destination, final java.lang.Class<? extends Classifier> classifierType, final IModelingSession session) {
        if ("Date".equals (destination.getClazz ()) &&
                "java.util".equals (destination.getPackage ()) &&
                (destination.getFeature () == null)) {
            List<MObject> ret = new ArrayList<> ();
            ret.add (session.getModel ().getUmlTypes ().getDATE());
            return ret;
        } else {
            return super.getElementByNamespace (destination, classifierType, session);
        }
    }

    @Override
    public List<Package> getPackageByNamespace(final String npackage, final IModelingSession session) {
        List<Package> possiblePackages = null;
        
        if (this.packageCache != null) {
            possiblePackages = this.packageCache.get (npackage);
        }
        
        if (possiblePackages == null) {
            possiblePackages = JavaDesignerUtils.getModelPackageFromJavaName(session, npackage);
        
            if (this.packageCache != null) {
                this.packageCache.put (npackage, possiblePackages);
            }
        }
        return possiblePackages;
    }

    @Override
    protected Package createPackages(final String npackage, final ModelTree root, final IModelingSession session) {
        ModelTree relativeroot = root;
        Package fp = null;
        String packaccu = "";
        List<String> packagetocreate = new ArrayList<>();
        for (String pname : npackage.split("\\.")) {
            packaccu = packaccu.isEmpty() ? pname : packaccu.concat(".")
                    .concat(pname);
            fp = resolveMultipleNamespaces(JavaDesignerUtils.getModelPackageFromJavaName(session, packaccu));
            if (fp != null) {
                // new relative root : empty the list of package to create and
                // change the root
                relativeroot = fp;
                packagetocreate.clear();
            } else {
                // memorize the package to create it if needed
                packagetocreate.add(pname);
            }
        }
        // Create the packages chain if needed
        // TODO add the packages to the cache
        if (fp == null) {
            try {
                for (String pc : packagetocreate) {
                    fp = createPackage(pc, relativeroot, session);
                    relativeroot = fp;
                }
            } catch (Exception e) {
                // Issue 0011595: When the Reverse doesn't find packages in the JDK Model Component, it try to create them in it....
                // FIXME find a better solution to this problem...
                fp = createPackage(npackage, root, session);
            }
        }
        return fp;
    }

}
