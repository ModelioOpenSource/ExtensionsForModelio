package org.modelio.module.javadesigner.utils;

import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.vcore.smkernel.mapi.MObject;

class JavaElementIdentificator extends DefaultModelVisitor {
    private static final JavaElementIdentificator INSTANCE = new JavaElementIdentificator ();

    public static boolean isJavaElement(final MObject element) {
        if (element != null) {
            Object ret = element.accept (INSTANCE);
        
            return ret != null && ret.equals (true);
        } else {
            return false;
        }
    }

    @Override
    public Object visitArtifact(final Artifact theArtifact) {
        return theArtifact.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE);
    }

    @Override
    public Object visitClass(final Class theClass) {
        return theClass.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACLASS);
    }

    @Override
    public Object visitComponent(final Component theComponent) {
        return theComponent.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACOMPONENT) || theComponent.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODULE_IMPLEMENTATION);
    }

    @Override
    public Object visitDataType(final DataType theDataType) {
        return theDataType.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVADATATYPE);
    }

    @Override
    public Object visitEnumeration(final Enumeration theEnumeration) {
        return theEnumeration.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAENUMERATION);
    }

    @Override
    public Object visitInterface(final Interface theInterface) {
        return theInterface.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAINTERFACE);
    }

    @Override
    public Object visitPackage(final Package thePackage) {
        return thePackage.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAPACKAGE);
    }

}
