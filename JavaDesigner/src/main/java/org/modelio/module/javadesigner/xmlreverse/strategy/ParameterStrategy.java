package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.Repository;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbClassType;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbParameter;
import org.modelio.module.javadesigner.xmlreverse.utils.TypeConverter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MObject;


public class ParameterStrategy extends ModelElementStrategy implements IReverseBox<JaxbParameter,Parameter> {
    
    public ParameterStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Parameter getCorrespondingElement(final JaxbParameter jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        if(owner instanceof Operation) {
            Operation owner_operation = (Operation) owner;    
            for(Parameter parameter : owner_operation.getIO()){
                if(parameter.getName().equals(jaxb_element.getName())){
                    if (!repository.isRecordedElement(parameter)) {
                        return parameter;
                    }
                }
            }
        }
        
        Parameter new_element = this.model.createParameter();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbParameter jaxb_element, final Parameter modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        if(owner instanceof Operation){
            modelio_element.setComposed((Operation)owner);
        }else{
            modelio_element.setOwnerTemplateParameter((TemplateParameter)owner);
        }
        
        String name = jaxb_element.getName();
        if (name != null) {
            modelio_element.setName(name);
        }
        
        if(jaxb_element.getMultiplicity() != null)
            modelio_element.setMultiplicityMax(jaxb_element.getMultiplicity());
        
        String passing = jaxb_element.getPassingMode();
        if (passing != null) {
            if(PassingMode.IN.name().equalsIgnoreCase(passing)){
                modelio_element.setParameterPassing(PassingMode.IN);
            } else if(PassingMode.OUT.name().equalsIgnoreCase(passing)){
                modelio_element.setParameterPassing(PassingMode.OUT);
            }else{
                modelio_element.setParameterPassing(PassingMode.INOUT);
            }
        }
        
        if(jaxb_element.getTypeConstraint() != null)
            modelio_element.setTypeConstraint(jaxb_element.getTypeConstraint());
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbParameter jaxb_element, final Parameter modelio_element, final IReadOnlyRepository repository) {
        // Typage de l'attribut
        for(Object sub : jaxb_element.getBaseTypeOrClassTypeOrNote()){
            if(sub instanceof JAXBElement){
                JAXBElement<?> jaxb_sub = (JAXBElement<?>) sub;
                if("base-type".equals(jaxb_sub.getName().getLocalPart())){
                    String type = (String)jaxb_sub.getValue();                
                    modelio_element.setType(TypeConverter.getBaseType(type,this.model));            
                } else if("default-value".equals(jaxb_sub.getName().getLocalPart())){
                    String value = (String)jaxb_sub.getValue();                
                    modelio_element.setDefaultValue(value);            
                }
            }else if(sub instanceof JaxbClassType){
                JaxbDestination jaxb_destination = ((JaxbClassType)sub).getDestination();
                MObject type = repository.getElementById(jaxb_destination.getRefid());
                if(type == null){
                    type = repository.getElementByNamespace(jaxb_destination, GeneralClass.class, this.session);
                    if(type == null){
                        type =  repository.createNamespace(jaxb_destination,(ModelTree) repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                    }
                    
                    ((Repository)repository).recordId(jaxb_destination.getRefid(), type);
                }
        
                modelio_element.setType((GeneralClass)type);
            }
        }
    }

    
    @Override
    public void deleteSubElements(final JaxbParameter jaxb_element, final Parameter modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
