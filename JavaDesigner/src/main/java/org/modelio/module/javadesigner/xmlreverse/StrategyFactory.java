package org.modelio.module.javadesigner.xmlreverse;

import java.util.HashMap;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.module.javadesigner.xmlreverse.model.IVisitorElement;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbAssociationEnd;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbAttribute;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClass;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbConstraint;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDataType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDependency;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbElementImport;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumeration;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumerationLiteral;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbExternalElement;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbGeneralization;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbGroup;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbInstance;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbInterface;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbModel;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbNote;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbOperation;
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
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTaggedValue;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateBinding;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameter;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbTemplateParameterSubstitution;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUse;
import org.modelio.module.javadesigner.xmlreverse.strategy.AssociationEndStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.AttributStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ClassStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ConstraintStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.DataTypeStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.DependencyStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ElementImportStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.EnumerationLiteralStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.EnumerationStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ExternalElementStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.GeneralizationStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.GroupStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.InstanceStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.InterfaceStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ModelStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.NoteStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.OperationStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.PackageImportStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.PackageStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ParameterStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.RaisedExceptionStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.RealizationStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ReportItemStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ReportListStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ReturnParameterStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.ReversedDataStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.SignalStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.StereotypeStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.TaggedValueStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.TemplateBindingStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.TemplateParameterStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.TemplateParameterSubstitutionStrategy;
import org.modelio.module.javadesigner.xmlreverse.strategy.UseStrategy;

@SuppressWarnings ("rawtypes")
public class StrategyFactory {

    private HashMap<java.lang.Class<? extends IVisitorElement>, IReverseBox> sbox_map;

    private HashMap<java.lang.Class<? extends IVisitorElement>, IReverseLink> slink_map;

    private IModelingSession session;

    public StrategyFactory(final IModelingSession session) {
        this.sbox_map = new HashMap<>();
        this.slink_map = new HashMap<>();
        this.session = session;
    }

    public void addBoxStrategy(final java.lang.Class<? extends IVisitorElement> identifier, final IReverseBox strategy) {
        this.sbox_map.put(identifier, strategy);
    }

    public void addLinkStrategy(final java.lang.Class<? extends IVisitorElement> identifier, final IReverseLink strategy) {
        this.slink_map.put(identifier, strategy);
    }

    public IReverseBox getPackageStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbPackage.class);
        if (box_strategy == null) {
            box_strategy = new PackageStrategy(this.session);
            this.sbox_map.put(JaxbPackage.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getAssociationEndStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbAssociationEnd.class);
        if (box_strategy == null) {
            box_strategy = new AssociationEndStrategy(this.session);
            this.sbox_map.put(JaxbAssociationEnd.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getAttributStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbAttribute.class);
        if (box_strategy == null) {
            box_strategy = new AttributStrategy(this.session);
            this.sbox_map.put(JaxbAttribute.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getClassStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbClass.class);
        if (box_strategy == null) {
            box_strategy = new ClassStrategy(this.session);
            this.sbox_map.put(JaxbClass.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getGroupStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbGroup.class);
        if (box_strategy == null) {
            box_strategy = new GroupStrategy(this.session);
            this.sbox_map.put(JaxbGroup.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getConstraintStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbConstraint.class);
        if (box_strategy == null) {
            box_strategy = new ConstraintStrategy(this.session);
            this.sbox_map.put(JaxbConstraint.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getDataTypeStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbDataType.class);
        if (box_strategy == null) {
            box_strategy = new DataTypeStrategy(this.session);
            this.sbox_map.put(JaxbDataType.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseLink getDependencyStrategy() {
        IReverseLink link_strategy = this.slink_map.get(JaxbDependency.class);
        if (link_strategy == null) {
            link_strategy = new DependencyStrategy(this.session);
            this.slink_map.put(JaxbDependency.class, link_strategy);
        }
        return link_strategy;
    }

    public IReverseLink getElementImportStrategy() {
        IReverseLink link_strategy = this.slink_map.get(JaxbElementImport.class);
        if (link_strategy == null) {
            link_strategy = new ElementImportStrategy(this.session);
            this.slink_map.put(JaxbElementImport.class, link_strategy);
        }
        return link_strategy;
    }

    public IReverseBox getEnumerationStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbEnumeration.class);
        if (box_strategy == null) {
            box_strategy = new EnumerationStrategy(this.session);
            this.sbox_map.put(JaxbEnumeration.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getEnumerationLiteralStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbEnumerationLiteral.class);
        if (box_strategy == null) {
            box_strategy = new EnumerationLiteralStrategy(this.session);
            this.sbox_map.put(JaxbEnumerationLiteral.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseLink getGeneralizationStrategy() {
        IReverseLink link_strategy = this.slink_map.get(JaxbGeneralization.class);
        if (link_strategy == null) {
            link_strategy = new GeneralizationStrategy(this.session);
            this.slink_map.put(JaxbGeneralization.class, link_strategy);
        }
        return link_strategy;
    }

    public IReverseBox getInstanceStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbInstance.class);
        if (box_strategy == null) {
            box_strategy = new InstanceStrategy(this.session);
            this.sbox_map.put(JaxbInstance.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getInterfaceStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbInterface.class);
        if (box_strategy == null) {
            box_strategy = new InterfaceStrategy(this.session);
            this.sbox_map.put(JaxbInterface.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getModelStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbModel.class);
        if (box_strategy == null) {
            box_strategy = new ModelStrategy();
            this.sbox_map.put(JaxbModel.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getNoteStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbNote.class);
        if (box_strategy == null) {
            box_strategy = new NoteStrategy(this.session);
            this.sbox_map.put(JaxbNote.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getOperationStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbOperation.class);
        if (box_strategy == null) {
            box_strategy = new OperationStrategy(this.session);
            this.sbox_map.put(JaxbOperation.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseLink getPackageImportStrategy() {
        IReverseLink link_strategy = this.slink_map.get(JaxbPackageImport.class);
        if (link_strategy == null) {
            link_strategy = new PackageImportStrategy(this.session);
            this.slink_map.put(JaxbPackageImport.class, link_strategy);
        }
        return link_strategy;
    }

    public IReverseBox getParameterStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbParameter.class);
        if (box_strategy == null) {
            box_strategy = new ParameterStrategy(this.session);
            this.sbox_map.put(JaxbParameter.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseLink getRaisedExceptionStrategy() {
        IReverseLink box_strategy = this.slink_map.get(JaxbRaisedException.class);
        if (box_strategy == null) {
            box_strategy = new RaisedExceptionStrategy(this.session);
            this.slink_map.put(JaxbRaisedException.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseLink getRealizationStrategy() {
        IReverseLink link_strategy = this.slink_map.get(JaxbRealization.class);
        if (link_strategy == null) {
            link_strategy = new RealizationStrategy(this.session);
            this.slink_map.put(JaxbRealization.class, link_strategy);
        }
        return link_strategy;
    }

    public IReverseBox getReportItemStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbReportItem.class);
        if (box_strategy == null) {
            box_strategy = new ReportItemStrategy();
            this.sbox_map.put(JaxbReportItem.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getReportListStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbReportList.class);
        if (box_strategy == null) {
            box_strategy = new ReportListStrategy();
            this.sbox_map.put(JaxbReportList.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getReturnParameterStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbReturnParameter.class);
        if (box_strategy == null) {
            box_strategy = new ReturnParameterStrategy(this.session);
            this.sbox_map.put(JaxbReturnParameter.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getReversedDataStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbReversedData.class);
        if (box_strategy == null) {
            box_strategy = new ReversedDataStrategy();
            this.sbox_map.put(JaxbReversedData.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getSignalStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbSignal.class);
        if (box_strategy == null) {
            box_strategy = new SignalStrategy(this.session);
            this.sbox_map.put(JaxbSignal.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getStereotypeStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbStereotype.class);
        if (box_strategy == null) {
            box_strategy = new StereotypeStrategy(this.session);
            this.sbox_map.put(JaxbStereotype.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getTaggedValueStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbTaggedValue.class);
        if (box_strategy == null) {
            box_strategy = new TaggedValueStrategy(this.session);
            this.sbox_map.put(JaxbTaggedValue.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseLink getUseStrategy() {
        IReverseLink link_strategy = this.slink_map.get(JaxbUse.class);
        if (link_strategy == null) {
            link_strategy = new UseStrategy(this.session);
            this.slink_map.put(JaxbUse.class, link_strategy);
        }
        return link_strategy;
    }

    public IReverseBox getTemplateParameterSubstitutionStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbTemplateParameterSubstitution.class);
        if (box_strategy == null) {
            box_strategy = new TemplateParameterSubstitutionStrategy(this.session);
            this.sbox_map.put(JaxbTemplateParameterSubstitution.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getTemplateParameterStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbTemplateParameter.class);
        if (box_strategy == null) {
            box_strategy = new TemplateParameterStrategy(this.session);
            this.sbox_map.put(JaxbTemplateParameter.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getTemplateBindingStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbTemplateBinding.class);
        if (box_strategy == null) {
            box_strategy = new TemplateBindingStrategy(this.session);
            this.sbox_map.put(JaxbTemplateBinding.class, box_strategy);
        }
        return box_strategy;
    }

    public IReverseBox getExternalElementStrategy() {
        IReverseBox box_strategy = this.sbox_map.get(JaxbExternalElement.class);
        if (box_strategy == null) {
            box_strategy = new ExternalElementStrategy(this.session);
            this.sbox_map.put(JaxbExternalElement.class, box_strategy);
        }
        return box_strategy;
    }

}
