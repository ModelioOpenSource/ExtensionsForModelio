package org.modelio.module.javadesigner.xmlreverse.revers;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.i18n.Messages;
import org.modelio.module.javadesigner.xmlreverse.model.IVisitorElement;
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
import org.modelio.module.javadesigner.xmlreverse.model.defaultvisitor.DefaultReverseModelVisitor;
import org.modelio.module.javadesigner.xmlreverse.model.defaultvisitor.IReverseModelVisitor;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;


public class RefIdHandler extends DefaultReverseModelVisitor {
    
    private Map<String, IVisitorElement> foundIds;

    
    private Map<String, IVisitorElement> pendingRefids;

    
    private Repository repository;

    
    private IModelingSession session;

    
    public RefIdHandler(final Repository repository, final IModelingSession session) {
        this.foundIds = new HashMap<>();
        this.pendingRefids = new HashMap<>();
        this.repository = repository;
        this.session = session;
    }

    
    public void mergeRefifWithId(final JaxbReversedData reversdata) {
        // clean all lists
        this.foundIds.clear();
        this.pendingRefids.clear();
        
        reversdata.accept(this);
        
        // Compute unresolved refids
        if (!this.pendingRefids.isEmpty()) {
            for (String refid : this.pendingRefids.keySet()) {
                handleRefid(refid, this.pendingRefids.get(refid));
            }
        }
        
        this.foundIds.clear();
        this.pendingRefids.clear();
    }

    
    private void handleObjid(final java.lang.Class<? extends MObject> metaclass, final String objid, final String newName, final String id) {
        ModelElement foundElement = (ModelElement) this.session.findElementById(metaclass, objid);
        try {
            if (foundElement != null && foundElement.isValid()) {
                if (id != null) {
                    this.repository.recordId(id, foundElement);
                }
                this.repository.recordId(objid, foundElement);
            }
        } catch (InvalidParameterException e) { // TODO exception
            if (this.repository.getReportWriter() != null) {
                MObject mappedElement;
                if (id != null) {
                    mappedElement = this.repository.getElementById(id);
                } else {
                    mappedElement = this.repository.getElementById(objid);
                }
                
                String oldName = mappedElement!=null?((ModelElement)mappedElement).getName():"";
        
                this.repository.getReportWriter().addError(Messages.getString("Error.duplicatedObjid.Title", metaclass, newName, oldName), foundElement,
                        Messages.getString("Error.duplicatedObjid.Description", metaclass, oldName, newName, objid));
            }
        }
    }

    
    private void handleRefid(final String refid, final IVisitorElement visitorElement) {
        if (visitorElement instanceof JaxbAssociationEnd) {
            handleRefid(refid, (JaxbAssociationEnd) visitorElement);
        } else if (visitorElement instanceof JaxbAttribute) {
            handleRefid(refid, (JaxbAttribute) visitorElement);
        } else if (visitorElement instanceof JaxbClass) {
            handleRefid(refid, (JaxbClass) visitorElement);
        } else if (visitorElement instanceof JaxbDataType) {
            handleRefid(refid, (JaxbDataType) visitorElement);
        } else if (visitorElement instanceof JaxbEnumeration) {
            handleRefid(refid, (JaxbEnumeration) visitorElement);
        } else if (visitorElement instanceof JaxbInterface) {
            handleRefid(refid, (JaxbInterface) visitorElement);
        } else if (visitorElement instanceof JaxbOperation) {
            handleRefid(refid, (JaxbOperation) visitorElement);
        } else if (visitorElement instanceof JaxbPackage) {
            handleRefid(refid, (JaxbPackage) visitorElement);
        } else if (visitorElement instanceof JaxbSignal) {
            handleRefid(refid, (JaxbSignal) visitorElement);
        } else if (visitorElement instanceof JaxbTemplateParameter) {
            handleRefid(refid, (JaxbTemplateParameter) visitorElement);
        } else {
            this.repository.getReportWriter().addError("Invalid refid found", null, "Invalid refid found in reversed XML file.");
        }
    }

    
    private void handleRefid(final String refid, final JaxbAssociationEnd refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbAssociationEnd) {
            JaxbAssociationEnd realElement = (JaxbAssociationEnd) foundElement;
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getMultiplicityMin() != null) {
                realElement.setMultiplicityMin(refElement.getMultiplicityMin());
            }
            if (refElement.getMultiplicityMax() != null) {
                realElement.setMultiplicityMax(refElement.getMultiplicityMax());
            }
            if (refElement.getAggregation() != null) {
                realElement.setAggregation(refElement.getAggregation());
            }
            if (refElement.getChangeable() != null) {
                realElement.setChangeable(refElement.getChangeable());
            }
            if (refElement.getVisibility() != null) {
                realElement.setVisibility(refElement.getVisibility());
            }
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.getObjid() != null) {
                realElement.setObjid(refElement.getObjid());
            }
            if (refElement.isIsOrdered() != null) {
                realElement.setIsOrdered(refElement.isIsOrdered());
            }
            if (refElement.isIsNavigable() != null) {
                realElement.setIsNavigable(refElement.isIsNavigable());
            }
            if (refElement.isIsChangeable() != null) {
                realElement.setIsChangeable(refElement.isIsChangeable());
            }
            if (refElement.isIsAbstract() != null) {
                realElement.setIsAbstract(refElement.isIsAbstract());
            }
            if (refElement.isIsClass() != null) {
                realElement.setIsClass(refElement.isIsClass());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
            
            List<Object> subelements = realElement.getBaseTypeOrClassTypeOrNote();
            subelements.addAll(refElement.getBaseTypeOrClassTypeOrNote());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    private void handleRefid(final String refid, final JaxbAttribute refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbAttribute) {
            JaxbAttribute realElement = (JaxbAttribute) foundElement;
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getMultiplicity() != null) {
                realElement.setMultiplicity(refElement.getMultiplicity());
            }
            if (refElement.getTypeConstraint() != null) {
                realElement.setTypeConstraint(refElement.getTypeConstraint());
            }
            if (refElement.getChangeable() != null) {
                realElement.setChangeable(refElement.getChangeable());
            }
            if (refElement.getVisibility() != null) {
                realElement.setVisibility(refElement.getVisibility());
            }
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.getObjid() != null) {
                realElement.setObjid(refElement.getObjid());
            }
            if (refElement.isIsDerived() != null) {
                realElement.setIsDerived(refElement.isIsDerived());
            }
            if (refElement.isIsSet() != null) {
                realElement.setIsSet(refElement.isIsSet());
            }
            if (refElement.isTargetIsClass() != null) {
                realElement.setTargetIsClass(refElement.isTargetIsClass());
            }
            if (refElement.isIsAbstract() != null) {
                realElement.setIsAbstract(refElement.isIsAbstract());
            }
            if (refElement.isIsClass() != null) {
                realElement.setIsClass(refElement.isIsClass());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
            
            List<Object> subelements = realElement.getValueOrBaseTypeOrClassType();
            subelements.addAll(refElement.getValueOrBaseTypeOrClassType());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    private void handleRefid(final String refid, final JaxbClass refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbClass) {
            JaxbClass realElement = (JaxbClass) foundElement;
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getVisibility() != null) {
                realElement.setVisibility(refElement.getVisibility());
            }
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.getObjid() != null) {
                realElement.setObjid(refElement.getObjid());
            }
            if (refElement.isIsMain() != null) {
                realElement.setIsMain(refElement.isIsMain());
            }
            if (refElement.isIsActive() != null) {
                realElement.setIsActive(refElement.isIsActive());
            }
            if (refElement.isIsAbstract() != null) {
                realElement.setIsAbstract(refElement.isIsAbstract());
            }
            if (refElement.isIsLeaf() != null) {
                realElement.setIsLeaf(refElement.isIsLeaf());
            }
            if (refElement.isIsElementary() != null) {
                realElement.setIsElementary(refElement.isIsElementary());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
            
            List<Object> subelements = realElement.getClazzOrInterfaceOrInstance();
            subelements.addAll(refElement.getClazzOrInterfaceOrInstance());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    private void handleRefid(final String refid, final JaxbDataType refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbDataType) {
            JaxbDataType realElement = (JaxbDataType) foundElement;
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getVisibility() != null) {
                realElement.setVisibility(refElement.getVisibility());
            }
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.getObjid() != null) {
                realElement.setObjid(refElement.getObjid());
            }
            if (refElement.isIsAbstract() != null) {
                realElement.setIsAbstract(refElement.isIsAbstract());
            }
            if (refElement.isIsLeaf() != null) {
                realElement.setIsLeaf(refElement.isIsLeaf());
            }
            if (refElement.isIsElementary() != null) {
                realElement.setIsElementary(refElement.isIsElementary());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
            
            List<Object> subelements = realElement.getOperationOrTemplateBindingOrTemplateParameter();
            subelements.addAll(refElement.getOperationOrTemplateBindingOrTemplateParameter());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    private void handleRefid(final String refid, final JaxbEnumeration refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbEnumeration) {
            JaxbEnumeration realElement = (JaxbEnumeration) foundElement;
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getVisibility() != null) {
                realElement.setVisibility(refElement.getVisibility());
            }
            if (refElement.getObjid() != null) {
                realElement.setObjid(refElement.getObjid());
            }
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.isIsAbstract() != null) {
                realElement.setIsAbstract(refElement.isIsAbstract());
            }
            if (refElement.isIsLeaf() != null) {
                realElement.setIsLeaf(refElement.isIsLeaf());
            }
            if (refElement.isIsElementary() != null) {
                realElement.setIsElementary(refElement.isIsElementary());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
            
            List<Object> subelements = realElement.getNoteOrConstraintOrStereotype();
            subelements.addAll(refElement.getNoteOrConstraintOrStereotype());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    private void handleRefid(final String refid, final JaxbInterface refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbInterface) {
            JaxbInterface realElement = (JaxbInterface) foundElement;
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getVisibility() != null) {
                realElement.setVisibility(refElement.getVisibility());
            }
            if (refElement.getObjid() != null) {
                realElement.setObjid(refElement.getObjid());
            }
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.isIsAbstract() != null) {
                realElement.setIsAbstract(refElement.isIsAbstract());
            }
            if (refElement.isIsLeaf() != null) {
                realElement.setIsLeaf(refElement.isIsLeaf());
            }
            if (refElement.isIsElementary() != null) {
                realElement.setIsElementary(refElement.isIsElementary());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
            
            List<Object> subelements = realElement.getClazzOrInterfaceOrEnumeration();
            subelements.addAll(refElement.getClazzOrInterfaceOrEnumeration());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    private void handleRefid(final String refid, final JaxbOperation refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbOperation) {
            JaxbOperation realElement = (JaxbOperation) foundElement;
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getPassing() != null) {
                realElement.setPassing(refElement.getPassing());
            }
            if (refElement.getVisibility() != null) {
                realElement.setVisibility(refElement.getVisibility());
            }
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.getObjid() != null) {
                realElement.setObjid(refElement.getObjid());
            }
            if (refElement.isFinal() != null) {
                realElement.setFinal(refElement.isFinal());
            }
            if (refElement.isConcurrency() != null) {
                realElement.setConcurrency(refElement.isConcurrency());
            }
            if (refElement.isIsAbstract() != null) {
                realElement.setIsAbstract(refElement.isIsAbstract());
            }
            if (refElement.isIsClass() != null) {
                realElement.setIsClass(refElement.isIsClass());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
        
            List<Object> subelements = realElement.getParameterOrTemplateParameterOrReturnParameter();
            subelements.addAll(refElement.getParameterOrTemplateParameterOrReturnParameter());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    private void handleRefid(final String refid, final JaxbPackage refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbPackage) {
            JaxbPackage realElement = (JaxbPackage) foundElement;
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getVisibility() != null) {
                realElement.setVisibility(refElement.getVisibility());
            }
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.getObjid() != null) {
                realElement.setObjid(refElement.getObjid());
            }
            if (refElement.isIsAbstract() != null) {
                realElement.setIsAbstract(refElement.isIsAbstract());
            }
            if (refElement.isIsLeaf() != null) {
                realElement.setIsLeaf(refElement.isIsLeaf());
            }
            if (refElement.isIsInstantiable() != null) {
                realElement.setIsInstantiable(refElement.isIsInstantiable());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
        
            List<Object> subelements = realElement.getGroupOrPackageOrClazz();
            subelements.addAll(refElement.getGroupOrPackageOrClazz());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    private void handleRefid(final String refid, final JaxbSignal refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbSignal) {
            JaxbSignal realElement = (JaxbSignal) foundElement;
            if (refElement.getVisibility() != null) {
                realElement.setVisibility(refElement.getVisibility());
            }
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.getObjid() != null) {
                realElement.setObjid(refElement.getObjid());
            }
            if (refElement.isIsClass() != null) {
                realElement.setIsClass(refElement.isIsClass());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
        
            List<Object> subelements = realElement.getOperationRepresentationOrNoteOrConstraint();
            subelements.addAll(refElement.getOperationRepresentationOrNoteOrConstraint());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    private void handleRefid(final String refid, final JaxbTemplateParameter refElement) {
        IVisitorElement foundElement = this.foundIds.get(refid);
        if (foundElement instanceof JaxbTemplateParameter) {
            JaxbTemplateParameter realElement = (JaxbTemplateParameter) foundElement;
            if (refElement.getId() != null) {
                realElement.setId(refElement.getId());
            }
            if (refElement.getName() != null) {
                realElement.setName(refElement.getName());
            }
            if (refElement.getRefid() != null) {
                realElement.setRefid(refElement.getRefid());
            }
        
            List<Object> subelements = realElement.getPackageOrClazzOrOperation();
            subelements.addAll(refElement.getPackageOrClazzOrOperation());
        } else {
            this.pendingRefids.put(refid, refElement);
        }
    }

    
    @Override
    public Object visitAssociationEnd(final JaxbAssociationEnd element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(AssociationEnd.class, objid, element.getName(), element.getId());
        }
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitAttribute(final JaxbAttribute element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(Attribute.class, objid, element.getName(), element.getId());
        }
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitClass(final JaxbClass element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(Class.class, objid, element.getName(), element.getId());
        }
        
        List<IVisitorElement> toRemove = new ArrayList<> ();
        
        for (Object collection : element.getClazzOrInterfaceOrInstance()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                Object ret = c_element.accept(this);
        
                // Store elements having a refid
                if (ret != null && true == (Boolean) ret) {
                    toRemove.add(c_element);
                }
            }
        }
        
        // Remove elements having a refid from the class's children
        element.getClazzOrInterfaceOrInstance().removeAll(toRemove);
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitDataType(final JaxbDataType element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(DataType.class, objid, element.getName(), element.getId());
        }
        
        List<IVisitorElement> toRemove = new ArrayList<> ();
        
        for (Object collection : element.getOperationOrTemplateBindingOrTemplateParameter()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                Object ret = c_element.accept(this);
        
                // Store elements having a refid
                if (ret != null && true == (Boolean) ret) {
                    toRemove.add(c_element);
                }
            }
        }
        
        // Remove elements having a refid from the class's children
        element.getOperationOrTemplateBindingOrTemplateParameter().removeAll(toRemove);
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitEnumeration(final JaxbEnumeration element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(Enumeration.class, objid, element.getName(), element.getId());
        }
        
        List<IVisitorElement> toRemove = new ArrayList<> ();
        
        for (Object collection : element.getNoteOrConstraintOrStereotype()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                Object ret = c_element.accept(this);
        
                // Store elements having a refid
                if (ret != null && true == (Boolean) ret) {
                    toRemove.add(c_element);
                }
            }
        }
        
        // Remove elements having a refid from the class's children
        element.getNoteOrConstraintOrStereotype().removeAll(toRemove);
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitEnumerationLiteral(final JaxbEnumerationLiteral element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(EnumerationLiteral.class, objid, element.getName(), null);
        }
        return false;
    }

    
    @Override
    public Object visitInterface(final JaxbInterface element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(Interface.class, objid, element.getName(), element.getId());
        }
        
        List<IVisitorElement> toRemove = new ArrayList<> ();
        
        for (Object collection : element.getClazzOrInterfaceOrEnumeration()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                Object ret = c_element.accept(this);
        
                // Store elements having a refid
                if (ret != null && true == (Boolean) ret) {
                    toRemove.add(c_element);
                }
            }
        }
        
        // Remove elements having a refid from the class's children
        element.getClazzOrInterfaceOrEnumeration().removeAll(toRemove);
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitOperation(final JaxbOperation element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(Operation.class, objid, element.getName(), element.getId());
        }
        
        List<IVisitorElement> toRemove = new ArrayList<> ();
        
        for (Object collection : element.getParameterOrTemplateParameterOrReturnParameter()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                Object ret = c_element.accept(this);
        
                // Store elements having a refid
                if (ret != null && true == (Boolean) ret) {
                    toRemove.add(c_element);
                }
            }
        }
        
        // Remove elements having a refid from the class's children
        element.getParameterOrTemplateParameterOrReturnParameter().removeAll(toRemove);
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitPackage(final JaxbPackage element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(Package.class, objid, element.getName(), element.getId());
        }
        
        List<IVisitorElement> toRemove = new ArrayList<> ();
        
        for (Object collection : element.getGroupOrPackageOrClazz()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                Object ret = c_element.accept(this);
        
                // Store elements having a refid
                if (ret != null && true == (Boolean) ret) {
                    toRemove.add(c_element);
                }
            }
        }
        
        // Remove elements having a refid from the class's children
        element.getGroupOrPackageOrClazz().removeAll(toRemove);
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitSignal(final JaxbSignal element) {
        String objid = element.getObjid();
        if (objid != null) {
            handleObjid(Signal.class, objid, element.getName(), element.getId());
        }
        
        List<IVisitorElement> toRemove = new ArrayList<> ();
        
        for (Object collection : element.getOperationRepresentationOrNoteOrConstraint()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                Object ret = c_element.accept(this);
        
                // Store elements having a refid
                if (ret != null && true == (Boolean) ret) {
                    toRemove.add(c_element);
                }
            }
        }
        
        // Remove elements having a refid from the class's children
        element.getOperationRepresentationOrNoteOrConstraint().removeAll(toRemove);
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitTemplateParameter(final JaxbTemplateParameter element) {
        List<IVisitorElement> toRemove = new ArrayList<> ();
        
        for (Object collection : element.getPackageOrClazzOrOperation()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                Object ret = c_element.accept(this);
        
                // Store elements having a refid
                if (ret != null && true == (Boolean) ret) {
                    toRemove.add(c_element);
                }
            }
        }
        
        // Remove elements having a refid from the class's children
        element.getPackageOrClazzOrOperation().removeAll(toRemove);
        
        String refid = element.getRefid();
        String id = element.getId();
        if (refid != null) {
            handleRefid(refid, element);
            return true;
        } else if (id != null) {
            this.foundIds.put(id, element);
        }
        return false;
    }

    
    @Override
    public Object visitModel(final JaxbModel element) {
        for (Object collection : element.getPackageOrClazzOrInterface()) {
            if (collection instanceof IVisitorElement) {
                IVisitorElement c_element = (IVisitorElement) collection;
                c_element.accept(this);
            }
        }
        return null;
    }

    
    @Override
    public Object visitReversedData(final JaxbReversedData element) {
        JaxbModel c_element = element.getModel();
        c_element.accept(this);
        return null;
    }

    
    @Override
    public Object visitGroup(final JaxbGroup element) {
        reportSubElements(element);
        
        List<IVisitorElement> toRemove = new ArrayList<> ();
        
        for (Object collection : element.getPackageOrClazzOrInterface()) {
            IVisitorElement c_element = (IVisitorElement) collection;
            Object ret = c_element.accept(this);
        
            // Store elements having a refid
            if (ret != null && true == (Boolean) ret) {
                toRemove.add(c_element);
            }
        }
        
        // Remove elements having a refid from the class's children
        element.getPackageOrClazzOrInterface().removeAll(toRemove);
        return null;
    }

    
    private void reportSubElements(final JaxbGroup jaxb_element) {
        List<JaxbInterface> interfaces = new ArrayList<>();
        List<JaxbPackage> packages = new ArrayList<>();
        List<JaxbClass> classes = new ArrayList<>();
        List<JaxbEnumeration> enumerations = new ArrayList<>();
        List<JaxbDataType> dataTypes = new ArrayList<>();
        
        List<Object> toBeReported = new ArrayList<>();
        
        for(Object sub :  jaxb_element.getPackageOrClazzOrInterface()){
            if(sub instanceof JaxbInterface){
                interfaces.add((JaxbInterface) sub);
            }else if(sub instanceof JaxbPackage){
                packages.add((JaxbPackage) sub);
            }else if(sub instanceof JaxbClass){
                classes.add((JaxbClass) sub);
            }else if( sub instanceof JaxbEnumeration){
                enumerations.add((JaxbEnumeration) sub);
            }else if(sub instanceof JaxbDataType){
                dataTypes.add((JaxbDataType) sub);
            }else{
                toBeReported.add(sub);
            }
        }
        
        for (Object subElement : toBeReported) {
            jaxb_element.getPackageOrClazzOrInterface().remove(subElement);
        
            for (JaxbInterface theInterface : interfaces) {
                Object duplicatedElement = duplicate(subElement);
                theInterface.getClazzOrInterfaceOrEnumeration().add(duplicatedElement);
            }
        
            for (JaxbPackage thePackage : packages) {
                Object duplicatedElement = duplicate(subElement);
                thePackage.getGroupOrPackageOrClazz().add(duplicatedElement);
            }
        
            for (JaxbClass theClass : classes) {
                Object duplicatedElement = duplicate(subElement);
                theClass.getClazzOrInterfaceOrInstance().add(duplicatedElement);
            }
        
            for (JaxbEnumeration theEnumeration : enumerations) {
                Object duplicatedElement = duplicate(subElement);
                theEnumeration.getNoteOrConstraintOrStereotype().add(duplicatedElement);
            }
        
            for (JaxbDataType theDataType : dataTypes) {
                Object duplicatedElement = duplicate(subElement);
                theDataType.getOperationOrTemplateBindingOrTemplateParameter().add(duplicatedElement);
            }    
        }
    }

    
    Object duplicate(final Object subElement) {
        if (subElement instanceof IVisitorElement) {
            return ((IVisitorElement) subElement).accept(new IReverseModelVisitor() {
        
                @Override
                public Object visitAssociationEnd(JaxbAssociationEnd element) {
                    JaxbAssociationEnd duplicatedElement = new JaxbAssociationEnd();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setAggregation(element.getAggregation());
                    duplicatedElement.setChangeable(element.getChangeable());
                    duplicatedElement.setId(element.getId());
                    duplicatedElement.setMultiplicityMax(element.getMultiplicityMax());
                    duplicatedElement.setMultiplicityMin(element.getMultiplicityMin());
                    duplicatedElement.setObjid(element.getObjid());
                    duplicatedElement.setVisibility(element.getVisibility());
        
                    List<Object> duplicatedSons = duplicatedElement.getBaseTypeOrClassTypeOrNote();
                    for (Object son : element.getBaseTypeOrClassTypeOrNote()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitAttribute(JaxbAttribute element) {
                    JaxbAttribute duplicatedElement = new JaxbAttribute();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setChangeable(element.getChangeable());
                    duplicatedElement.setId(element.getId());
                    duplicatedElement.setMultiplicity(element.getMultiplicity());
                    duplicatedElement.setObjid(element.getObjid());
                    duplicatedElement.setTypeConstraint(element.getTypeConstraint());
                    duplicatedElement.setVisibility(element.getVisibility());
        
                    List<Object> duplicatedSons = duplicatedElement.getValueOrBaseTypeOrClassType();
                    for (Object son : element.getValueOrBaseTypeOrClassType()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitClass(JaxbClass element) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitClassType(JaxbClassType element) {
                    JaxbClassType duplicatedElement = new JaxbClassType();
                    duplicatedElement.setDestination((JaxbDestination) duplicate(element.getDestination()));
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitConstraint(JaxbConstraint element) {
                    JaxbConstraint duplicatedElement = new JaxbConstraint();
                    duplicatedElement.setName(element.getName());
        
                    List<Object> duplicatedSons = duplicatedElement.getContent();
                    for (Object son : element.getContent()) {
                        duplicatedSons.add(duplicate(son));
                    }
                    return duplicatedElement;
                }
        
                @Override
                public Object visitDataType(JaxbDataType element) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitDefaultType(JaxbDefaultType type) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitDependency(JaxbDependency element) {
                    JaxbDependency duplicatedElement = new JaxbDependency();
                    List<Object> duplicatedSons = duplicatedElement.getClassTypeOrStereotypeOrTaggedValue();
                    for (Object son : element.getClassTypeOrStereotypeOrTaggedValue()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitDestination(JaxbDestination element) {
                    JaxbDestination duplicatedElement = new JaxbDestination();
                    duplicatedElement.setClazz(element.getClazz());
                    duplicatedElement.setFeature(element.getFeature());
                    duplicatedElement.setPackage(element.getPackage());
                    duplicatedElement.setParameter(element.getParameter());
                    duplicatedElement.setRefid(element.getRefid());
                    duplicatedElement.setTemplateParameter(element.getTemplateParameter());
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitDir(JaxbDir dir) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitElementImport(JaxbElementImport element) {
                    JaxbElementImport duplicatedElement = new JaxbElementImport();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setUsedClass((JaxbUsedClass) duplicate(element.getUsedClass()));
                    duplicatedElement.setUsedPackage((JaxbUsedPackage) duplicate(element.getUsedPackage()));
        
                    List<Object> duplicatedSons = duplicatedElement.getNoteOrConstraintOrStereotype();
                    for (Object son : element.getNoteOrConstraintOrStereotype()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitEnumeration(JaxbEnumeration element) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitEnumerationLiteral(JaxbEnumerationLiteral element) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitExternalElement(JaxbExternalElement element) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitGeneralization(JaxbGeneralization element) {
                    JaxbGeneralization duplicatedElement = new JaxbGeneralization();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setDiscriminator(element.getDiscriminator());
        
                    List<Object> duplicatedSons = duplicatedElement.getNoteOrStereotypeOrTaggedValue();
                    for (Object son : element.getNoteOrStereotypeOrTaggedValue()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitGroup(JaxbGroup group) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitImplementedClass(JaxbImplementedClass class1) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitInstance(JaxbInstance element) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitInterface(JaxbInterface element) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitModel(JaxbModel model) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitNote(JaxbNote element) {
                    JaxbNote duplicatedElement = new JaxbNote();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setNoteType(element.getNoteType());
        
                    List<Object> duplicatedSons = duplicatedElement.getStereotypeOrTaggedValueOrContent();
                    for (Object son : element.getStereotypeOrTaggedValueOrContent()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitOPerationRepresentation(
                        JaxbOperationRepresentation representation) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitOperation(JaxbOperation element) {
                    JaxbOperation duplicatedElement = new JaxbOperation();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setId(element.getId());
                    duplicatedElement.setObjid(element.getObjid());
                    duplicatedElement.setPassing(element.getPassing());
                    duplicatedElement.setVisibility(element.getVisibility());
        
                    List<Object> duplicatedSons = duplicatedElement.getParameterOrTemplateParameterOrReturnParameter();
                    for (Object son : element.getParameterOrTemplateParameterOrReturnParameter()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitPackage(JaxbPackage element) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitPackageImport(JaxbPackageImport element) {
                    JaxbPackageImport duplicatedElement = new JaxbPackageImport();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setUsedPackage((JaxbUsedPackage) duplicate(element.getUsedPackage()));
        
                    List<Object> duplicatedSons = duplicatedElement.getNoteOrConstraintOrStereotype();
                    for (Object son : element.getNoteOrConstraintOrStereotype()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitParameter(JaxbParameter element) {
                    JaxbParameter duplicatedElement = new JaxbParameter();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setMultiplicity(element.getMultiplicity());
                    duplicatedElement.setPassingMode(element.getPassingMode());
                    duplicatedElement.setTypeConstraint(element.getTypeConstraint());
        
                    List<Object> duplicatedSons = duplicatedElement.getBaseTypeOrClassTypeOrNote();
                    for (Object son : element.getBaseTypeOrClassTypeOrNote()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitRaisedException(JaxbRaisedException element) {
                    JaxbRaisedException duplicatedElement = new JaxbRaisedException();
                    duplicatedElement.setUsedClass((JaxbUsedClass) duplicate(element.getUsedClass()));
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitRealization(JaxbRealization element) {
                    JaxbRealization duplicatedElement = new JaxbRealization();
                    duplicatedElement.setName(element.getName());
        
                    List<Object> duplicatedSons = duplicatedElement.getNoteOrStereotypeOrTaggedValue();
                    for (Object son : element.getNoteOrStereotypeOrTaggedValue()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitReportItem(JaxbReportItem item) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitReportList(JaxbReportList list) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitReturnParameter(JaxbReturnParameter element) {
                    JaxbReturnParameter duplicatedElement = new JaxbReturnParameter();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setMultiplicity(element.getMultiplicity());
                    duplicatedElement.setPassingMode(element.getPassingMode());
                    duplicatedElement.setTypeConstraint(element.getTypeConstraint());
        
                    List<Object> duplicatedSons = duplicatedElement.getBaseTypeOrClassTypeOrNote();
                    for (Object son : element.getBaseTypeOrClassTypeOrNote()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitReversedData(JaxbReversedData data) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitSignal(JaxbSignal element) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitStereotype(JaxbStereotype element) {
                    JaxbStereotype duplicatedElement = new JaxbStereotype();
                    duplicatedElement.setStereotypeType(element.getStereotypeType());
        
                    return duplicatedElement ;
                }
        
                @Override
                public Object visitSuperType(JaxbSuperType type) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitTaggedValue(JaxbTaggedValue element) {
                    JaxbTaggedValue duplicatedElement = new JaxbTaggedValue();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setTagType(element.getTagType());
        
                    List<String> duplicatedSons = duplicatedElement.getTagParameter();
                    for (String son : element.getTagParameter()) {
                        duplicatedSons.add(son);
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitTargetItem(JaxbTargetItem item) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitTemplateBinding(JaxbTemplateBinding binding) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitTemplateParameter(JaxbTemplateParameter element) {
                    JaxbTemplateParameter duplicatedElement = new JaxbTemplateParameter();
                    duplicatedElement.setName(element.getName());
                    duplicatedElement.setId(element.getId());
        
                    List<Object> duplicatedSons = duplicatedElement.getPackageOrClazzOrOperation();
                    for (Object son : element.getPackageOrClazzOrOperation()) {
                        duplicatedSons.add(duplicate(son));
                    }
                    return duplicatedElement;
                }
        
                @Override
                public Object visitTemplateParameterSubstitution(
                        JaxbTemplateParameterSubstitution substitution) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitType(JaxbType type) {
                    // TODO Auto-generated method stub
                    return null;
                }
        
                @Override
                public Object visitUse(JaxbUse element) {
                    JaxbUse duplicatedElement = new JaxbUse();
                    duplicatedElement.setName(element.getName());
        
                    List<Object> duplicatedSons = duplicatedElement.getNoteOrConstraintOrStereotype();
                    for (Object son : element.getNoteOrConstraintOrStereotype()) {
                        duplicatedSons.add(duplicate(son));
                    }
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitUsedClass(JaxbUsedClass element) {
                    JaxbUsedClass duplicatedElement = new JaxbUsedClass();
                    duplicatedElement.setDestination((JaxbDestination) duplicate(element.getDestination()));
        
                    return duplicatedElement;
                }
        
                @Override
                public Object visitUsedPackage(JaxbUsedPackage element) {
                    JaxbUsedPackage duplicatedElement = new JaxbUsedPackage();
                    duplicatedElement.setDestination((JaxbDestination) duplicate(element.getDestination()));
        
                    return duplicatedElement;
                }
        
            });
        }
        return subElement;
    }

}
