package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbOperationRepresentation;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbSignal;
import org.modelio.module.javadesigner.xmlreverse.model.JxbVisibilityMode;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;


public class SignalStrategy extends ModelElementStrategy implements IReverseBox<JaxbSignal,Signal> {
    
    public SignalStrategy(final IModelingSession session) {
        super(session);
        this.session = session;
        this.model = session.getModel();
    }

    
    @Override
    public Signal getCorrespondingElement(final JaxbSignal jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelTree treeOwner = (ModelTree) owner;
        
        for(ModelTree sub_tree : treeOwner.getOwnedElement()){
            if(sub_tree instanceof Signal && sub_tree.getName().equals(jaxb_element.getName())){
                if (!repository.isRecordedElement(sub_tree)) {
                return (Signal)sub_tree;
            }
        }
        }
            
        Signal new_element = this.model.createSignal();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbSignal jaxb_element, final Signal modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelTree treeOwner = (ModelTree) owner;
        modelio_element.setOwner(treeOwner);
        
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        String visibility = jaxb_element.getVisibility();
        if (visibility != null) {
            visibility = visibility.toUpperCase();        
            if(visibility.equalsIgnoreCase(JxbVisibilityMode.Public.name())){
                modelio_element.setVisibility(VisibilityMode.PUBLIC);            
            }else if(visibility.equalsIgnoreCase(JxbVisibilityMode.Private.name())){
                modelio_element.setVisibility(VisibilityMode.PRIVATE);            
            }else if(visibility.equalsIgnoreCase(JxbVisibilityMode.Protected.name())){
                modelio_element.setVisibility(VisibilityMode.PROTECTED);        
            }else if(visibility.equalsIgnoreCase(JxbVisibilityMode.Package_Visibility.name())){
                modelio_element.setVisibility(VisibilityMode.PACKAGEVISIBILITY);        
            }else if(visibility.equalsIgnoreCase(JxbVisibilityMode.Visibility_Undefined.name())){
                modelio_element.setVisibility(VisibilityMode.VISIBILITYUNDEFINED);    
            }
        }
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbSignal jaxb_element, final Signal modelio_element, final IReadOnlyRepository repository) {
        for(Object obj : jaxb_element.getOperationRepresentationOrNoteOrConstraint()){
            if(obj instanceof JaxbOperationRepresentation){
                JaxbOperationRepresentation jaxb_oper = (JaxbOperationRepresentation)obj;
                JaxbDestination jaxb_destination = jaxb_oper.getDestination();
        
                GeneralClass base_type = createBaseType(jaxb_destination, repository);
                if(base_type != null){
                    modelio_element.setBase(base_type);
                    
                    Operation operation = createOperation(jaxb_destination.getFeature(), base_type);
                    if(operation != null){
                        modelio_element.setOBase(operation);
                        
                        Parameter param = createParameter(jaxb_destination.getParameter(), operation);
                        if(param != null){
                            modelio_element.setPBase(param);
                        }
                    }
                }
                break;
            }
        }
    }

    
    private GeneralClass createBaseType(final JaxbDestination jaxb_destination, final IReadOnlyRepository repository) {
        MObject type = repository.getElementById(jaxb_destination.getRefid());
        if(type == null){
            type = repository.getElementByNamespace(jaxb_destination,GeneralClass.class, this.session);
            if(type == null){
                type =  repository.createNamespace(jaxb_destination,(ModelTree) repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
            }
            
            ((Repository)repository).recordId(jaxb_destination.getRefid(), type);
        }
        return (GeneralClass)type;
    }

    
    private Operation createOperation(final String jb_feature, final GeneralClass base) {
        if(jb_feature != null){
            Operation new_operation = null;
            for(Operation feat : (base).getOwnedOperation()){
                if(feat.getName().equals(jb_feature)){
                    new_operation = feat;
                }
            }    
            
            if(new_operation == null){
                new_operation = this.model.createOperation();
                new_operation.setName(jb_feature);
            }
            return new_operation;
        }
        return null;
    }

    
    private Parameter createParameter(final String jb_param, final Operation new_operation) {
        if(jb_param != null){
            Parameter new_parameter = null;
            if(jb_param.equals("Return")){
                new_parameter = new_operation.getReturn();
                if( new_parameter == null){
                    new_parameter = this.model.createParameter();
                    new_operation.setReturn(new_parameter);
                }
                
                return new_parameter;
            }else{
                for(Parameter param : new_operation.getIO()){
                    if(param.getName().equals(jb_param)){
                        new_parameter = param;
                    }
                }
                
                if(new_parameter == null){
                    new_parameter = this.model.createParameter();
                    new_parameter.setName(jb_param);
                    new_operation.getIO().add(new_parameter);
                }        
                return new_parameter;
            }    
        }
        return null;
    }

    
    @Override
    public void deleteSubElements(final JaxbSignal jaxb_element, final Signal modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
