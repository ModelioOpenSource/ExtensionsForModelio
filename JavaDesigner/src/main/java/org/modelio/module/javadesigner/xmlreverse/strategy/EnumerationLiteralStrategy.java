package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbEnumerationLiteral;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.vcore.smkernel.mapi.MObject;


public class EnumerationLiteralStrategy extends ModelElementStrategy implements IReverseBox<JaxbEnumerationLiteral,EnumerationLiteral> {
    
    public EnumerationLiteralStrategy(final IModelingSession session) {
        super(session);
    }

    
    @Override
    public EnumerationLiteral getCorrespondingElement(final JaxbEnumerationLiteral jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        Enumeration treeOwner = (Enumeration) owner;
        
        for(EnumerationLiteral sub_tree : treeOwner.getValue()){
            if(sub_tree.getName().equals(jaxb_element.getName())){
                return sub_tree;
            }
        }
        EnumerationLiteral new_element = this.model.createEnumerationLiteral();
        return new_element;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbEnumerationLiteral jaxb_element, final EnumerationLiteral modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        Enumeration treeOwner = (Enumeration) owner;
        modelio_element.setValuated(treeOwner);
        
        String name = jaxb_element.getName();
        if (name != null) {
        modelio_element.setName(name);
        }
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbEnumerationLiteral jaxb_element, final EnumerationLiteral modelio_element, final IReadOnlyRepository repository) {
        // NA
    }

    
    @Override
    public void deleteSubElements(final JaxbEnumerationLiteral jaxb_element, final EnumerationLiteral modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
