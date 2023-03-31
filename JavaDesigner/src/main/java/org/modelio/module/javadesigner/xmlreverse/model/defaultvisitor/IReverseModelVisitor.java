package org.modelio.module.javadesigner.xmlreverse.model.defaultvisitor;

import org.modelio.module.javadesigner.xmlreverse.model.JaxbAssociationEnd;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbAttribute;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClass;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClassType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbConstraint;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDataType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDefaultType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDependency;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDir;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbElementImport;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumeration;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumerationLiteral;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbExternalElement;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbGeneralization;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbGroup;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbImplementedClass;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbInstance;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbInterface;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbModel;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbNote;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbOperation;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbOperationRepresentation;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbPackage;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbPackageImport;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbParameter;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbRaisedException;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbRealization;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReportItem;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReportList;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReturnParameter;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbReversedData;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbSignal;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbStereotype;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbSuperType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTaggedValue;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTargetItem;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateBinding;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameter;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameterSubstitution;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUse;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUsedClass;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUsedPackage;
import com.modeliosoft.modelio.javadesigner.annotations.objid;


public interface IReverseModelVisitor {
    
    Object visitAssociationEnd(final JaxbAssociationEnd element);

    
    Object visitAttribute(final JaxbAttribute element);

    
    Object visitClass(final JaxbClass element);

    
    Object visitConstraint(final JaxbConstraint element);

    
    Object visitDataType(final JaxbDataType element);

    
    Object visitDependency(final JaxbDependency element);

    
    Object visitElementImport(final JaxbElementImport element);

    
    Object visitEnumeration(final JaxbEnumeration element);

    
    Object visitEnumerationLiteral(final JaxbEnumerationLiteral element);

    
    Object visitGeneralization(final JaxbGeneralization element);

    
    Object visitInstance(final JaxbInstance element);

    
    Object visitInterface(final JaxbInterface element);

    
    Object visitNote(final JaxbNote element);

    
    Object visitPackage(final JaxbPackage element);

    
    Object visitPackageImport(final JaxbPackageImport element);

    
    Object visitOperation(final JaxbOperation element);

    
    Object visitParameter(final JaxbParameter element);

    
    Object visitRaisedException(final JaxbRaisedException element);

    
    Object visitRealization(final JaxbRealization element);

    
    Object visitReturnParameter(final JaxbReturnParameter element);

    
    Object visitSignal(final JaxbSignal element);

    
    Object visitStereotype(final JaxbStereotype element);

    
    Object visitTaggedValue(final JaxbTaggedValue element);

    
    Object visitTemplateParameter(final JaxbTemplateParameter element);

    
    Object visitUse(final JaxbUse element);

    
    Object visitReversedData(final JaxbReversedData data);

    
    Object visitModel(final JaxbModel model);

    
    Object visitReportList(final JaxbReportList list);

    
    Object visitReportItem(final JaxbReportItem item);

    
    Object visitTemplateParameterSubstitution(final JaxbTemplateParameterSubstitution substitution);

    
    Object visitClassType(final JaxbClassType type);

    
    Object visitDefaultType(final JaxbDefaultType type);

    
    Object visitDestination(final JaxbDestination destination);

    
    Object visitDir(final JaxbDir dir);

    
    Object visitExternalElement(final JaxbExternalElement element);

    
    Object visitImplementedClass(final JaxbImplementedClass class1);

    
    Object visitOPerationRepresentation(final JaxbOperationRepresentation representation);

    
    Object visitSuperType(final JaxbSuperType type);

    
    Object visitType(final JaxbType type);

    
    Object visitTargetItem(final JaxbTargetItem item);

    
    Object visitTemplateBinding(final JaxbTemplateBinding binding);

    
    Object visitUsedClass(final JaxbUsedClass class1);

    
    Object visitUsedPackage(final JaxbUsedPackage package1);

    
    Object visitGroup(final JaxbGroup group);

}
