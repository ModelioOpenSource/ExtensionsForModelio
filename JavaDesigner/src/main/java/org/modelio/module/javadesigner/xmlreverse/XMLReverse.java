package org.modelio.module.javadesigner.xmlreverse;

import java.io.File;
import java.util.Calendar;
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
import org.modelio.module.javadesigner.xmlreverse.model.defaultvisitor.IReverseModelVisitor;
import org.modelio.module.javadesigner.xmlreverse.model.serialization.ModelUnmarshaller;
import org.modelio.module.javadesigner.xmlreverse.revers.ElementCleaner;
import org.modelio.module.javadesigner.xmlreverse.revers.ElementCreation;
import org.modelio.module.javadesigner.xmlreverse.revers.ElementPostTraitement;
import org.modelio.module.javadesigner.xmlreverse.revers.LinkRelationSetter;
import org.modelio.module.javadesigner.xmlreverse.revers.RefIdHandler;
import org.modelio.module.javadesigner.xmlreverse.utils.ModelElementDeleteStrategy;
import org.modelio.vcore.smkernel.mapi.MObject;

public class XMLReverse {

    private StrategyFactory sfactory;


    private Repository repository;


    private IReverseSessionHandler handler;


    private IModelingSession session;


    public XMLReverse(final IModelingSession session) {
        this.repository = new Repository();
        this.sfactory = new StrategyFactory(session);
        this.session = session;
    }


    public void reverse(final File file, final MObject root, final String charsetName) {
        this.repository.setRoot(root);

        IReportWriter reportWriter = this.repository.getReportWriter();
        reportWriter.addTrace("Unmarshal at " + Calendar.getInstance ().getTime().toGMTString ());

        // XML serialisation towards JAXB model
        ModelUnmarshaller unmarshaller = new ModelUnmarshaller(this.repository.getReportWriter());
        JaxbReversedData reversdata =  (JaxbReversedData)unmarshaller.load(file, charsetName);

        if (reversdata != null) {
            RefIdHandler refIdHandler = new RefIdHandler(this.repository, this.session);
            refIdHandler.mergeRefifWithId(reversdata);

            if (this.handler != null) {
                this.handler.executePreTreatment(reversdata);
            }

            reportWriter.addTrace("Reverse elements at " + Calendar.getInstance ().getTime().toGMTString ());

            // Create elements and update their properties
            reversElements(reversdata);

            reportWriter.addTrace("Set links at " + Calendar.getInstance ().getTime().toGMTString ());

            // Create all links
            setLinks();

            reportWriter.addTrace("Cleaner at " + Calendar.getInstance ().getTime().toGMTString ());

            // Delete useless elements
            executeCleaner(reversdata);

            reportWriter.addTrace("Post treatment at " + Calendar.getInstance ().getTime().toGMTString ());

            // Execute post treatment
            executePostTraitement(reversdata);
        }

        if (this.handler != null) {
            this.handler.executePostTreatment(reversdata);
        }
    }


    public void reverse(final JaxbReversedData reversdata, final MObject root) {
        this.repository.setRoot(root);

        // Create elements / edit properties
        reversElements(reversdata);

        // Add links
        setLinks();

        // Delete all sub elements
        executeCleaner(reversdata);

        // PostTreatment
        executePostTraitement(reversdata);
    }


    private Object reversElements(final JaxbReversedData reversdata) {
        IReverseModelVisitor reverser = new ElementCreation(this.sfactory,this.repository, this.session);
        return reversdata.accept(reverser);
    }


    private void setLinks() {
        IReverseModelVisitor reverser = new ElementCreation(this.sfactory,this.repository, this.session);

        IReverseModelVisitor link_setter = new LinkRelationSetter(this.sfactory,this.repository, this.session, reverser);

        // We must handle realization links first, to avoid creating the default class creation on extern elements
        for(JaxbRealization element : this.repository.getRealizations()){
            element.accept(link_setter);
        }

        for(IVisitorElement element : this.repository.getLinks()){
            element.accept(link_setter);
        }
    }


    private void executeCleaner(final JaxbReversedData reversdata) {
        IReverseModelVisitor post_traitement = new ElementCleaner(this.sfactory,this.repository);
        reversdata.accept(post_traitement);
    }


    private void executePostTraitement(final JaxbReversedData reversdata) {
        IReverseModelVisitor post_traitement = new ElementPostTraitement(this.sfactory,this.repository);
        reversdata.accept(post_traitement);
    }


    public void addInstanceStrategy(final IReverseBox<JaxbInstance,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbInstance.class, strategy);
    }


    public void addReturnParameterStrategy(final IReverseBox<JaxbReturnParameter,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbReturnParameter.class, strategy);
    }


    public void addPackageStrategy(final IReverseBox<JaxbPackage,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbPackage.class, strategy);
    }


    public void addAssociationEndStrategy(final IReverseBox<JaxbAssociationEnd,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbAssociationEnd.class, strategy);
    }


    public void addAttributeStrategy(final IReverseBox<JaxbAttribute,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbAttribute.class, strategy);
    }


    public void addClassStrategy(final IReverseBox<JaxbClass,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbClass.class, strategy);
    }


    public void addConstraintStrategy(final IReverseBox<JaxbConstraint,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbConstraint.class, strategy);
    }


    public void addDataTypeStrategy(final IReverseBox<JaxbDataType,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbDataType.class, strategy);
    }


    public void addDependencyStrategy(final IReverseLink<JaxbDependency,?,?,?> strategy) {
        this.sfactory.addLinkStrategy(JaxbDependency.class, strategy);
    }


    public void addElementImportStrategy(final IReverseLink<JaxbElementImport,?,?,?> strategy) {
        this.sfactory.addLinkStrategy(JaxbElementImport.class, strategy);
    }


    public void addEnumerationStrategy(final IReverseBox<JaxbEnumeration,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbEnumeration.class, strategy);
    }


    public void addEnumerationLiteralStrategy(final IReverseBox<JaxbEnumerationLiteral,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbEnumerationLiteral.class, strategy);
    }


    public void addExternalElementStrategy(final IReverseBox<JaxbExternalElement,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbExternalElement.class, strategy);
    }


    public void addGeneralizationStrategy(final IReverseLink<JaxbGeneralization,?,?,?> strategy) {
        this.sfactory.addLinkStrategy(JaxbGeneralization.class, strategy);
    }


    public void addInterfaceStrategy(final IReverseBox<JaxbInterface,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbInterface.class, strategy);
    }


    public void addModelStrategy(final IReverseBox<JaxbModel,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbModel.class, strategy);
    }


    public void addNoteStrategy(final IReverseBox<JaxbNote,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbNote.class, strategy);
    }


    public void addOperationStrategy(final IReverseBox<JaxbOperation,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbOperation.class, strategy);
    }


    public void addPackageImportStrategy(final IReverseLink<JaxbPackageImport,?,?,?> strategy) {
        this.sfactory.addLinkStrategy(JaxbPackageImport.class, strategy);
    }


    public void addParameterStrategy(final IReverseBox<JaxbParameter,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbParameter.class, strategy);
    }


    public void addRaisedExceptionStrategy(final IReverseBox<JaxbRaisedException,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbRaisedException.class, strategy);
    }


    public void addRealizationStrategy(final IReverseLink<JaxbRealization,?,?,?> strategy) {
        this.sfactory.addLinkStrategy(JaxbRealization.class, strategy);
    }


    public void addReportItemStrategy(final IReverseBox<JaxbReportItem,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbReportItem.class, strategy);
    }


    public void addReportListStrategy(final IReverseBox<JaxbReportList,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbReportList.class, strategy);
    }


    public void addReversedDataStrategy(final IReverseBox<JaxbReversedData,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbReversedData.class, strategy);
    }


    public void addSignalStrategy(final IReverseBox<JaxbSignal,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbSignal.class, strategy);
    }


    public void addStereotypeStrategy(final IReverseBox<JaxbStereotype,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbStereotype.class, strategy);
    }


    public void addTaggedValueStrategy(final IReverseBox<JaxbTaggedValue,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbTaggedValue.class, strategy);
    }


    public void addTemplateBindingStrategy(final IReverseBox<JaxbTemplateBinding,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbTemplateBinding.class, strategy);
    }


    public void addTemplateParameterStrategy(final IReverseBox<JaxbTemplateParameter,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbTemplateParameter.class, strategy);
    }


    public void addTemplateParameterSubstitutionStrategy(final IReverseBox<JaxbTemplateParameterSubstitution,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbTemplateParameterSubstitution.class, strategy);
    }


    public void addUseSubstitutionStrategy(final IReverseLink<JaxbUse,?,?,?> strategy) {
        this.sfactory.addLinkStrategy(JaxbUse.class, strategy);
    }


    public void addGroupStrategy(final IReverseBox<JaxbGroup,?> strategy) {
        this.sfactory.addBoxStrategy(JaxbGroup.class, strategy);
    }


    public IReportWriter getReportWriter() {
        return this.repository.getReportWriter();
    }


    public void setReportWriter(final IReportWriter report) {
        this.repository.setReportWriter(report);
    }


    public INameSpaceFinder getNameSpaceFinder() {
        return this.repository.getNameSpaceFinder();
    }


    public void setNameSpaceFinder(final INameSpaceFinder namespaceUtils) {
        this.repository.setNameSpaceFinder(namespaceUtils);
    }


    public ModelElementDeleteStrategy getModelElementDeleteStrategy() {
        return this.repository.getModelElementDeleteStrategy();
    }


    public void setModelElementDeleteStrategy(final ModelElementDeleteStrategy modelElementDeleteStrategy) {
        this.repository.setModelElementDeleteStrategy(modelElementDeleteStrategy);
    }


    public void setHandler(final IReverseSessionHandler handler) {
        this.handler = handler;
    }

}
