package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbStereotype;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MObject;


public class StereotypeStrategy extends ModelElementStrategy implements IReverseBox<JaxbStereotype,Stereotype> {
    
    public StereotypeStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Stereotype getCorrespondingElement(final JaxbStereotype jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelElement treeOwner = (ModelElement) owner;
        
        for(Stereotype stereotype : treeOwner.getExtension()){
            if(stereotype.getName().equals(jaxb_element.getStereotypeType())){
                return stereotype;
            }
        }
                    
        String type = jaxb_element.getStereotypeType();
        List<Stereotype> types = this.session.getMetamodelExtensions().findStereotypes(type, owner.getMClass());
        if (types.size() > 0) {
            return types.get(0);
        } else {
            repository.getReportWriter().addError("Stereotype not found : " + type, null, "Unable to find stereotype: " + type);
        }
        return null;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbStereotype jaxb_element, final Stereotype modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        ModelElement treeOwner = (ModelElement) owner;
        if (modelio_element != null) {
            treeOwner.getExtension().add(modelio_element);
        }
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbStereotype jaxb_element, final Stereotype modelio_element, final IReadOnlyRepository repository) {
        // N/A
    }

    
    @Override
    public void deleteSubElements(final JaxbStereotype jaxb_element, final Stereotype modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
