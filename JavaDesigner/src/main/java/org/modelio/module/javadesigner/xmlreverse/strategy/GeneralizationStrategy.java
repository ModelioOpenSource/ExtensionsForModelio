package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseLink;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbGeneralization;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;


public class GeneralizationStrategy extends ModelElementStrategy implements IReverseLink<JaxbGeneralization,Generalization,NameSpace,NameSpace> {
    
    public GeneralizationStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public Generalization getCorrespondingElement(final JaxbGeneralization jaxb_element, final NameSpace source, final NameSpace target, final IReadOnlyRepository repository) {
        // Search for a generalization between those elements
        for (Generalization link: source.getParent()) {
            NameSpace targetNameSpace = link.getSuperType();
            if (targetNameSpace.equals(target)) {
                return link;
            }
                    }
                
        // No link found, create a new one
        return this.model.createGeneralization();
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbGeneralization jaxb_element, final Generalization modelio_element, final NameSpace source, final NameSpace target, final IReadOnlyRepository repository) {
        modelio_element.setSubType(source);
        modelio_element.setSuperType(target);
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbGeneralization jaxb_element, final Generalization modelio_element, final IReadOnlyRepository repository) {
        // Nothing to do
    }

    
    @Override
    public void deleteSubElements(final JaxbGeneralization jaxb_element, final Generalization modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
