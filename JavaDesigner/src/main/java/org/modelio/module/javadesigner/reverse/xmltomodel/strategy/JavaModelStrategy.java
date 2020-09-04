package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.IReportWriter;
import com.modelio.module.xmlreverse.model.IVisitorElement;
import com.modelio.module.xmlreverse.model.JaxbAssociationEnd;
import com.modelio.module.xmlreverse.model.JaxbAttribute;
import com.modelio.module.xmlreverse.model.JaxbClass;
import com.modelio.module.xmlreverse.model.JaxbClassType;
import com.modelio.module.xmlreverse.model.JaxbConstraint;
import com.modelio.module.xmlreverse.model.JaxbDataType;
import com.modelio.module.xmlreverse.model.JaxbDefaultType;
import com.modelio.module.xmlreverse.model.JaxbDependency;
import com.modelio.module.xmlreverse.model.JaxbDestination;
import com.modelio.module.xmlreverse.model.JaxbElementImport;
import com.modelio.module.xmlreverse.model.JaxbEnumeration;
import com.modelio.module.xmlreverse.model.JaxbGeneralization;
import com.modelio.module.xmlreverse.model.JaxbGroup;
import com.modelio.module.xmlreverse.model.JaxbInstance;
import com.modelio.module.xmlreverse.model.JaxbInterface;
import com.modelio.module.xmlreverse.model.JaxbModel;
import com.modelio.module.xmlreverse.model.JaxbNote;
import com.modelio.module.xmlreverse.model.JaxbOperation;
import com.modelio.module.xmlreverse.model.JaxbPackage;
import com.modelio.module.xmlreverse.model.JaxbPackageImport;
import com.modelio.module.xmlreverse.model.JaxbParameter;
import com.modelio.module.xmlreverse.model.JaxbRaisedException;
import com.modelio.module.xmlreverse.model.JaxbRealization;
import com.modelio.module.xmlreverse.model.JaxbReturnParameter;
import com.modelio.module.xmlreverse.model.JaxbSignal;
import com.modelio.module.xmlreverse.model.JaxbTemplateBinding;
import com.modelio.module.xmlreverse.model.JaxbTemplateParameter;
import com.modelio.module.xmlreverse.model.JaxbTemplateParameterSubstitution;
import com.modelio.module.xmlreverse.model.JaxbType;
import com.modelio.module.xmlreverse.model.JaxbUse;
import com.modelio.module.xmlreverse.model.JaxbUsedClass;
import com.modelio.module.xmlreverse.model.JaxbUsedPackage;
import com.modelio.module.xmlreverse.model.defaultvisitor.DefaultReverseModelVisitor;
import com.modelio.module.xmlreverse.strategy.ModelStrategy;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.ReverseConfig.GeneralReverseMode;
import org.modelio.module.javadesigner.reverse.ReverseStrategyConfiguration;
import org.modelio.module.javadesigner.reverse.xmltomodel.JavaNameSpaceFinder;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaModelStrategy extends ModelStrategy {
    protected List<IVisitorElement> elementsToKeep = null;

    protected JavaNameSpaceFinder javaNameSpaceFinder;

    protected ReverseStrategyConfiguration config;

    @Override
    public List<MObject> updateProperties(final JaxbModel jaxb_element, final MObject modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        if (repository.getReportWriter().hasErrors()) {
            // If there are errors in the report, something went wrong in the parser...
            // We have to remove all sub elements to skip the "model" reverse
            // but allow the report to be completed.
            jaxb_element.getPackageOrClazzOrInterface().clear();
        }
        
        // In some case, we must remove elements from the jaxb model
        if (isFilterActive()) {
            ElementFilterVisitor visitor = new ElementFilterVisitor();
            jaxb_element.accept(visitor);
        }
        return super.updateProperties(jaxb_element, modelio_element, owner, repository);
    }

    public JavaModelStrategy(final JavaNameSpaceFinder nameSpaceFinder, final ReverseStrategyConfiguration config) {
        this.javaNameSpaceFinder = nameSpaceFinder;
        this.config = config;
    }

    @Override
    public void postTreatment(final JaxbModel jaxb_element, final MObject modelio_element, final IReadOnlyRepository repository) {
        // Generate one info per external class reversed
        IReportWriter report = repository.getReportWriter();
        for (Classifier elt : this.javaNameSpaceFinder.getExternalClassifiers()) {
            // Some of these elements may not be EXTERN anymore (if they have
            // been reversed in between) that's why the tagged value is checked once again.
            if (!elt.isDeleted() && (elt).isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVAEXTERN)) {
                report.addInfo(Messages.getString("Info.ExternClassifierCreation.Message",
                        JavaDesignerUtils.getJavaName(elt)),
                        elt,
                        Messages.getString("Info.ExternClassifierCreation.Description"));
            }
        }
        super.postTreatment(jaxb_element, modelio_element, repository);
    }

    public List<IVisitorElement> getElementsToKeep() {
        return this.elementsToKeep;
    }

    public void setElementsToKeep(final List<IVisitorElement> elementsToKeep) {
        this.elementsToKeep = elementsToKeep;
    }

    /**
     * Filter is active when reverse mode is not Complete, or elements have been unselected in the reverse wizard.
     * @return <code>true</code> when the filter is active.
     */
    protected boolean isFilterActive() {
        return JavaModelStrategy.this.config.reverseMode != GeneralReverseMode.COMPLETE_REVERSE || this.elementsToKeep != null;
    }

    private class ElementFilterVisitor extends DefaultReverseModelVisitor {
        public ElementFilterVisitor() {
            // Empty c'tor
        }

        private boolean keepElement(final Object element) {
            switch (JavaModelStrategy.this.config.reverseMode) {
            case SIMPLE_STRUCTURAL_REVERSE: {
                if ((element instanceof JaxbAssociationEnd)
                        || (element instanceof JaxbAttribute)
                        || (element instanceof JaxbClassType)
                        || (element instanceof JaxbDefaultType)
                        || (element instanceof JaxbDependency)
                        || (element instanceof JaxbDestination)
                        || (element instanceof JaxbGeneralization)
                        || (element instanceof JaxbOperation)
                        || (element instanceof JaxbParameter)
                        || (element instanceof JaxbRaisedException)
                        || (element instanceof JaxbRealization)
                        || (element instanceof JaxbReturnParameter)
                        || (element instanceof JaxbTemplateBinding)
                        || (element instanceof JaxbTemplateParameter)
                        || (element instanceof JaxbTemplateParameterSubstitution)
                        || (element instanceof JaxbType)) {
                    return false;
                }
            }
            //$FALL-THROUGH$
            case COMPLETE_STRUCTURAL_REVERSE: {
                if ((element instanceof JaxbConstraint)
                        || (element instanceof JaxbElementImport)
                        || (element instanceof JaxbInstance)
                        || (element instanceof JaxbNote)
                        || (element instanceof JaxbPackageImport)
                        || (element instanceof JaxbUse)
                        || (element instanceof JaxbUsedClass)
                        || (element instanceof JaxbUsedPackage)) {
                    return false;
                } else if (element instanceof JAXBElement) {
                    // Ignore init values
                    JAXBElement<?> jaxb_sub = (JAXBElement<?>) element;
                    String localPart = jaxb_sub.getName().getLocalPart();
                    if ("value".equals(localPart) || "default-value".equals(localPart)) {
                        return false;
                    }
                }
            }
            //$FALL-THROUGH$
            default:
            case COMPLETE_REVERSE: {
                // Ignore elements unchecked in the reverse wizard
                if (JavaModelStrategy.this.elementsToKeep != null) {
                    if ((element instanceof JaxbClass)
                            || (element instanceof JaxbDataType)
                            || (element instanceof JaxbEnumeration)
                            || (element instanceof JaxbInterface)) {
                        return JavaModelStrategy.this.elementsToKeep.contains(element);
                    }
                }
            }
            }
            return true;
        }

        @Override
        public Object visitClass(final JaxbClass element) {
            for (Object c_element : new ArrayList<>(element.getClazzOrInterfaceOrInstance())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getClazzOrInterfaceOrInstance().remove(c_element);
                }
            }
            return null;
        }

        @Override
        public Object visitDataType(final JaxbDataType element) {
            for (Object c_element : new ArrayList<>(element.getOperationOrTemplateBindingOrTemplateParameter())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getOperationOrTemplateBindingOrTemplateParameter().remove(c_element);
                }
            }
            return null;
        }

        @Override
        public Object visitEnumeration(final JaxbEnumeration element) {
            for (Object c_element : new ArrayList<>(element.getNoteOrConstraintOrStereotype())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getNoteOrConstraintOrStereotype().remove(c_element);
                }
            }
            return null;
        }

        @Override
        public Object visitInterface(final JaxbInterface element) {
            for (Object c_element : new ArrayList<>(element.getClazzOrInterfaceOrEnumeration())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getClazzOrInterfaceOrEnumeration().remove(c_element);
                }
            }
            return null;
        }

        @Override
        public Object visitPackage(final JaxbPackage element) {
            for (Object c_element : new ArrayList<>(element.getGroupOrPackageOrClazz())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getGroupOrPackageOrClazz().remove(c_element);
                }
            }
            return null;
        }

        @Override
        public Object visitModel(final JaxbModel element) {
            for (Object c_element : new ArrayList<>(element.getPackageOrClazzOrInterface())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getPackageOrClazzOrInterface().remove(c_element);
                }
            }
            return null;
        }

        @Override
        public Object visitGroup(final JaxbGroup element) {
            for (Object c_element : new ArrayList<>(element.getPackageOrClazzOrInterface())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getPackageOrClazzOrInterface().remove(c_element);
                }
            }
            return null;
        }

        @Override
        public Object visitAssociationEnd(final JaxbAssociationEnd element) {
            for (Object c_element : new ArrayList<>(element.getBaseTypeOrClassTypeOrNote())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getBaseTypeOrClassTypeOrNote().remove(c_element);
                }
            }
            return super.visitAssociationEnd(element);
        }

        @Override
        public Object visitAttribute(final JaxbAttribute element) {
            for (Object c_element : new ArrayList<>(element.getValueOrBaseTypeOrClassType())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getValueOrBaseTypeOrClassType().remove(c_element);
                }
            }
            return super.visitAttribute(element);
        }

        @Override
        public Object visitInstance(final JaxbInstance element) {
            for (Object c_element : new ArrayList<>(element.getNoteOrConstraintOrStereotype())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getNoteOrConstraintOrStereotype().remove(c_element);
                }
            }
            return super.visitInstance(element);
        }

        @Override
        public Object visitOperation(final JaxbOperation element) {
            for (Object c_element : new ArrayList<>(element.getParameterOrTemplateParameterOrReturnParameter())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getParameterOrTemplateParameterOrReturnParameter().remove(c_element);
                }
            }
            return super.visitOperation(element);
        }

        @Override
        public Object visitSignal(final JaxbSignal element) {
            for (Object c_element : new ArrayList<>(element.getOperationRepresentationOrNoteOrConstraint())) {
                if (keepElement(c_element)) {
                    if (c_element instanceof IVisitorElement) {
                        ((IVisitorElement) c_element).accept(this);
                    }
                } else {
                    element.getOperationRepresentationOrNoteOrConstraint().remove(c_element);
                }
            }
            return super.visitSignal(element);
        }

    }

}
