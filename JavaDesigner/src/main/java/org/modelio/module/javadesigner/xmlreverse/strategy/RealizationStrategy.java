package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseLink;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbRealization;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;


public class RealizationStrategy extends ModelElementStrategy implements IReverseLink<JaxbRealization,InterfaceRealization,NameSpace,Interface> {
    
    public RealizationStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public InterfaceRealization getCorrespondingElement(final JaxbRealization jaxb_element, final NameSpace source, final Interface target, final IReadOnlyRepository repository) {
        // Search for an interface realization between those elements
        for (InterfaceRealization link: source.getRealized()) {
            Interface implementedInterface = link.getImplemented();
            if (implementedInterface.equals(target)) {
                return link;
            }
        }
                
        // No link found, create a new one
        return this.model.createInterfaceRealization(source, target);
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbRealization jaxb_element, final InterfaceRealization modelio_element, final NameSpace source, final Interface target, final IReadOnlyRepository repository) {
        modelio_element.setImplemented(target);
        modelio_element.setImplementer(source);
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbRealization jaxb_element, final InterfaceRealization modelio_element, final IReadOnlyRepository repository) {
        // NA
    }

    
    @Override
    public void deleteSubElements(final JaxbRealization jaxb_element, final InterfaceRealization modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
