package org.modelio.module.javadesigner.xmlreverse.strategy;

import java.util.Collection;
import java.util.List;
import org.modelio.module.javadesigner.xmlreverse.IReadOnlyRepository;
import org.modelio.module.javadesigner.xmlreverse.IReverseBox;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbExternalElement;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.vcore.smkernel.mapi.MObject;


public class ExternalElementStrategy implements IReverseBox<JaxbExternalElement,MObject> {
    
    protected IUmlModel model;

    
    protected IModelingSession session;

    
    public ExternalElementStrategy(final IModelingSession session) {
        this.session = session;
        this.model = session.getModel();
    }

    
    @Override
    public MObject getCorrespondingElement(final JaxbExternalElement jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        MObject type = repository.getElementById(jaxb_element.getId());
        if (type == null) {
            if (jaxb_element.getPackage() != null || jaxb_element.getClazz() != null) {
                JaxbDestination jaxb_destination = new JaxbDestination();
                jaxb_destination.setPackage(jaxb_element.getPackage());
                jaxb_destination.setClazz(jaxb_element.getClazz());
        
                Class<? extends Classifier> classifierType = Classifier.class;
                if (jaxb_element.getMetaclass() != null) {
                    classifierType = (Class<? extends Classifier>) owner.getMClass().getMetamodel().getMClass(jaxb_element.getMetaclass()).getJavaInterface();
                } else {
                    classifierType = Classifier.class;
                }
                type = repository.getElementByNamespace(jaxb_destination, classifierType, this.session);
                if (type == null) {
                    if (jaxb_element.getMetaclass() != null) {
                        classifierType = (Class<? extends Classifier>) owner.getMClass().getMetamodel().getMClass(jaxb_element.getMetaclass()).getJavaInterface();
                    } else {
                        classifierType = org.modelio.metamodel.uml.statik.Class.class;
                    }
                    type = repository.createNamespace(jaxb_destination, (ModelTree) repository.getRoot(), classifierType, this.session);
                }
            }
        }
        return type;
    }

    
    @Override
    public List<MObject> updateProperties(final JaxbExternalElement jaxb_element, final MObject modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        // N/A
        return null;
    }

    
    @Override
    public void postTreatment(final JaxbExternalElement jaxb_element, final MObject modelio_element, final IReadOnlyRepository repository) {
        // N/A
    }

    
    @Override
    public void deleteSubElements(final JaxbExternalElement jaxb_element, final MObject modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        // N/A
    }

}
