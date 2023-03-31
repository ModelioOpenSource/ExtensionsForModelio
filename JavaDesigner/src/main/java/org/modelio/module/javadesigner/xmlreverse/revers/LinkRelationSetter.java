package org.modelio.module.javadesigner.xmlreverse.revers;

import org.modelio.module.javadesigner.xmlreverse.IReverseLink;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.StrategyFactory;
import org.modelio.module.javadesigner.xmlreverse.model.IVisitorElement;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClassType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDependency;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbElementImport;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbGeneralization;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbImplementedClass;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbPackageImport;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbRaisedException;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbRealization;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbSuperType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUse;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUsedClass;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbUsedPackage;
import org.modelio.module.javadesigner.xmlreverse.model.defaultvisitor.DefaultReverseModelVisitor;
import org.modelio.module.javadesigner.xmlreverse.model.defaultvisitor.IReverseModelVisitor;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.vcore.smkernel.mapi.MObject;


@SuppressWarnings("unchecked")
public class LinkRelationSetter extends DefaultReverseModelVisitor {
    
    private IModelingSession session;

    
    private Repository repository;

    
    private StrategyFactory sFactory;

    
    private IReverseModelVisitor reverser;

    
    public LinkRelationSetter(final StrategyFactory sfactory, final Repository repository, final IModelingSession modelingSession, final IReverseModelVisitor reverser) {
        this.session = modelingSession;
        this.repository = repository;
        this.sFactory = sfactory;
        this.reverser = reverser;
    }

    
    @Override
    public Object visitDependency(final JaxbDependency element) {
        // Find the link ends
        MObject source = this.repository.getOwner(element);
        MObject target = null;
        
        for(Object obj : element.getClassTypeOrStereotypeOrTaggedValue()){
            if(obj instanceof JaxbClassType){
                JaxbClassType jaxb_type = (JaxbClassType) obj;
                JaxbDestination jaxb_destination = jaxb_type.getDestination();
                target = this.repository.getElementById(jaxb_destination.getRefid());
                if(target == null){
                    target = this.repository.getElementByNamespace(jaxb_destination, null, this.session);
                    if(target == null){
                        target =  this.repository.createNamespace(jaxb_destination, (ModelTree) this.repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                    }
                    
                    this.repository.recordId(jaxb_destination.getRefid(), target);
                }
                break;
            }
        }
        
        // Create and update the link
        IReverseLink strategy = this.sFactory.getDependencyStrategy();
        MObject modelio_element  = strategy.getCorrespondingElement(element, source, target, this.repository);            
        this.repository.recordElements(strategy.updateProperties(element, modelio_element, source, target, this.repository));
        
        this.repository.recordElement(element, modelio_element);
        this.repository.recordComposedElement(modelio_element, element.getClassTypeOrStereotypeOrTaggedValue());
        
        for(Object collection : element.getClassTypeOrStereotypeOrTaggedValue()){
            if(collection instanceof IVisitorElement){
                IVisitorElement c_element = (IVisitorElement)collection;
                c_element.accept(this.reverser);
            }
        }
        return super.visitDependency(element);
    }

    
    @Override
    public Object visitElementImport(final JaxbElementImport element) {
        // Find the link ends
        MObject source = this.repository.getOwner(element);
        MObject target = null;
        
        // Target could be a class
        JaxbUsedClass jaxb_uc = element.getUsedClass();
        if(jaxb_uc != null){
            JaxbDestination jaxb_destination = jaxb_uc.getDestination();
            target = this.repository.getElementById(jaxb_destination.getRefid());
            if(target == null){
                target = this.repository.getElementByNamespace(jaxb_destination, null, this.session);
                if(target == null){
                    target =  this.repository.createNamespace(jaxb_destination,(ModelTree) this.repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                }
                
                this.repository.recordId(jaxb_destination.getRefid(), target);
            }
        }
        
        // Target could be a package
        JaxbUsedPackage jaxb_up = element.getUsedPackage();
        if(jaxb_up != null){
            JaxbDestination jaxb_destination = jaxb_up.getDestination();
            target = this.repository.getElementById(jaxb_destination.getRefid());
            if(target == null){
                target = this.repository.getElementByNamespace(jaxb_destination, null, this.session);
                if(target == null){
                    target =  this.repository.createNamespace(jaxb_destination, (ModelTree) this.repository.getRoot(), null, this.session);
                }
                
                this.repository.recordId(jaxb_destination.getRefid(), target);
            }
        }
        
        // Create and update the link
        IReverseLink strategy = this.sFactory.getElementImportStrategy();
        
        MObject modelio_element  = strategy.getCorrespondingElement(element, source, target, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, modelio_element, source, target, this.repository));
        
        this.repository.recordElement(element, modelio_element);
        this.repository.recordComposedElement(modelio_element, element.getNoteOrConstraintOrStereotype());
        
        for(Object collection : element.getNoteOrConstraintOrStereotype()){
            if(collection instanceof IVisitorElement){
                IVisitorElement c_element = (IVisitorElement)collection;
                c_element.accept(this.reverser);
            }
        }
        return super.visitElementImport(element);
    }

    
    @Override
    public Object visitGeneralization(final JaxbGeneralization element) {
        // Find the link ends
        MObject source = this.repository.getOwner(element);
        MObject target = null;
        
        for(Object obj : element.getNoteOrStereotypeOrTaggedValue()){
            if(obj instanceof JaxbSuperType){
                JaxbSuperType jaxb_type = (JaxbSuperType) obj;
                JaxbDestination jaxb_destination = jaxb_type.getDestination();
                target = this.repository.getElementById(jaxb_destination.getRefid());
                if(target == null){
                    if (source instanceof Interface) {
                        target = this.repository.getElementByNamespace(jaxb_destination, Interface.class, this.session);
                    } else {
                        target = this.repository.getElementByNamespace(jaxb_destination, org.modelio.metamodel.uml.statik.Class.class, this.session);
                    }
                    if(target == null){
                        if (source instanceof Interface) {
                            target =  this.repository.createNamespace(jaxb_destination, (ModelTree) this.repository.getRoot(), Interface.class, this.session);
                        } else {
                            target =  this.repository.createNamespace(jaxb_destination, (ModelTree) this.repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                        }
                    }
                    
                    this.repository.recordId(jaxb_destination.getRefid(), target);
                }
                break;
            }
        }
        
        // Create and update the link
        IReverseLink strategy = this.sFactory.getGeneralizationStrategy();
        
        MObject modelio_element  = strategy.getCorrespondingElement(element, source, target, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, modelio_element, source, target, this.repository));
        
        this.repository.recordElement(element, modelio_element);
        this.repository.recordComposedElement(modelio_element, element.getNoteOrStereotypeOrTaggedValue());
        
        for(Object collection : element.getNoteOrStereotypeOrTaggedValue()){
            if(collection instanceof IVisitorElement){
                IVisitorElement c_element = (IVisitorElement)collection;
                c_element.accept(this.reverser);
            }
        }
        return super.visitGeneralization(element);
    }

    
    @Override
    public Object visitPackageImport(final JaxbPackageImport element) {
        // Find the link ends
        MObject source = this.repository.getOwner(element);
        MObject target = null;
        
        JaxbUsedPackage jaxb_up = element.getUsedPackage();
        if(jaxb_up != null){
            JaxbDestination jaxb_destination = jaxb_up.getDestination();
            target = this.repository.getElementById(jaxb_destination.getRefid());
            if(target == null){
                target = this.repository.getElementByNamespace(jaxb_destination, null, this.session);
                if(target == null){
                    target =  this.repository.createNamespace(jaxb_destination, (ModelTree) this.repository.getRoot(), null, this.session);
                }
                
                this.repository.recordId(jaxb_destination.getRefid(), target);
            }
        }
        
        // Create and update the link
        IReverseLink strategy = this.sFactory.getPackageImportStrategy();
        
        MObject modelio_element  = strategy.getCorrespondingElement(element, source, target, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, modelio_element, source, target, this.repository));
        
        this.repository.recordElement(element, modelio_element);
        this.repository.recordComposedElement(modelio_element, element.getNoteOrConstraintOrStereotype());
        
        for(Object collection : element.getNoteOrConstraintOrStereotype()){
            if(collection instanceof IVisitorElement){
                IVisitorElement c_element = (IVisitorElement)collection;
                c_element.accept(this.reverser);
            }
        }
        return super.visitPackageImport(element);
    }

    
    @Override
    public Object visitRaisedException(final JaxbRaisedException element) {
        // Find the link ends
        MObject source = this.repository.getOwner(element);
        MObject target = null;
        
        JaxbUsedClass jaxb_uc = element.getUsedClass();
        if(jaxb_uc != null){
            JaxbDestination jaxb_destination = jaxb_uc.getDestination();
            target = this.repository.getElementById(jaxb_destination.getRefid());
            if(target == null){
                target = this.repository.getElementByNamespace(jaxb_destination, Classifier.class, this.session);
                if(target == null){
                    target =  this.repository.createNamespace(jaxb_destination, (ModelTree) this.repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                }
                
                this.repository.recordId(jaxb_destination.getRefid(), target);
            }
        }
        
        // Create and update the link
        IReverseLink strategy = this.sFactory.getRaisedExceptionStrategy();
        
        MObject new_element  = strategy.getCorrespondingElement(element, source, target, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, source, target, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getNoteOrConstraintOrStereotype());
        
        for(Object collection : element.getNoteOrConstraintOrStereotype()) {
            if(collection instanceof IVisitorElement){
                IVisitorElement c_element = (IVisitorElement)collection;
                c_element.accept(this.reverser);
            }
        }
        return super.visitRaisedException(element);
    }

    
    @Override
    public Object visitRealization(final JaxbRealization element) {
        // Find the link ends
        MObject source = this.repository.getOwner(element);
        MObject target = null;
        
        for(Object obj : element.getNoteOrStereotypeOrTaggedValue()){
            if(obj instanceof JaxbImplementedClass){
                JaxbImplementedClass jaxb_type = (JaxbImplementedClass) obj;
                JaxbDestination jaxb_destination = jaxb_type.getDestination();
                target = this.repository.getElementById(jaxb_destination.getRefid());
                // Sometimes, we get some element that is not an interface... Make sure it is one.
                if(target == null || !(target instanceof Interface)){
                    target = this.repository.getElementByNamespace(jaxb_destination, Interface.class, this.session);
                    if(target == null){
                        target =  this.repository.createNamespace(jaxb_destination,(ModelTree) this.repository.getRoot(), Interface.class, this.session);
                    }
                    
                    this.repository.recordId(jaxb_destination.getRefid(), target);
                }
                break;
            }
        }
        
        // Create and update the link
        IReverseLink strategy = this.sFactory.getRealizationStrategy();
        
        MObject new_element  = strategy.getCorrespondingElement(element, source, target, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, source, target, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getNoteOrStereotypeOrTaggedValue());
        
        for(Object collection : element.getNoteOrStereotypeOrTaggedValue()){
            if(collection instanceof IVisitorElement){
                IVisitorElement c_element = (IVisitorElement)collection;
                c_element.accept(this.reverser);
            }
        }
        return super.visitRealization(element);
    }

    
    @Override
    public Object visitUse(final JaxbUse element) {
        // Find the link ends
        MObject source = this.repository.getOwner(element);
        MObject target = null;
        
        for(Object obj : element.getNoteOrConstraintOrStereotype()){
            if(obj instanceof JaxbUsedPackage){
                JaxbUsedPackage jaxb_type = (JaxbUsedPackage) obj;
                JaxbDestination jaxb_destination = jaxb_type.getDestination();
                target = this.repository.getElementById(jaxb_destination.getRefid());
                if(target == null){
                    target = this.repository.getElementByNamespace(jaxb_destination, null, this.session);
                    if(target == null){
                        target =  this.repository.createNamespace(jaxb_destination,(ModelTree) this.repository.getRoot(), null, this.session);
                    }
                    
                    this.repository.recordId(jaxb_destination.getRefid(), target);
                }
                break;
            }else if(obj instanceof JaxbUsedClass){
                JaxbUsedClass jaxb_type = (JaxbUsedClass) obj;
                JaxbDestination jaxb_destination = jaxb_type.getDestination();
                target = this.repository.getElementById(jaxb_destination.getRefid());
                if(target == null){
                    target = this.repository.getElementByNamespace(jaxb_destination, null, this.session);
                    if(target == null){
                        target =  this.repository.createNamespace(jaxb_destination,(ModelTree) this.repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                    }
                    
                    this.repository.recordId(jaxb_destination.getRefid(), target);
                }
                break;
            }
        }
        
        // Create and update the link
        IReverseLink strategy = this.sFactory.getUseStrategy();
        
        MObject new_element  = strategy.getCorrespondingElement(element, source, target, this.repository);
        this.repository.recordElements(strategy.updateProperties(element, new_element, source, target, this.repository));
        
        this.repository.recordElement(element, new_element);
        this.repository.recordComposedElement(new_element, element.getNoteOrConstraintOrStereotype());
        
        for(Object collection : element.getNoteOrConstraintOrStereotype()){
            if(collection instanceof IVisitorElement){
                IVisitorElement c_element = (IVisitorElement)collection;
                c_element.accept(this.reverser);
            }
        }
        return super.visitUse(element);
    }

}
