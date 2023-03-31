package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseLink;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbElementImport;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;


public class ElementImportStrategy extends ModelElementStrategy implements IReverseLink<JaxbElementImport,ElementImport,NameSpace,NameSpace> {
    
    public ElementImportStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public ElementImport getCorrespondingElement(final JaxbElementImport jaxb_element, final NameSpace source, final NameSpace target, final IReadOnlyRepository repository) {
        // Search for an element import between those elements
        for (ElementImport link: source.getOwnedImport()) {
            NameSpace targetNameSpace = link.getImportedElement();
            if (targetNameSpace.equals(target)) {
                return link;
            }
        }
                
        // No link found, create a new one
        return this.model.createElementImport();
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbElementImport jaxb_element, final ElementImport modelio_element, final NameSpace source, final NameSpace target, final IReadOnlyRepository repository) {
        modelio_element.setImportingNameSpace(source);
        modelio_element.setImportedElement(target);
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbElementImport jaxb_element, final ElementImport modelio_element, final IReadOnlyRepository repository) {
        // Delete imports with same source & destination. May happen for element imports owned by a Group...
        if (modelio_element.getImportingNameSpace().equals(modelio_element.getImportedElement())) {
            modelio_element.delete();
        }
    }

    
    @Override
    public void deleteSubElements(final JaxbElementImport jaxb_element, final ElementImport modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
