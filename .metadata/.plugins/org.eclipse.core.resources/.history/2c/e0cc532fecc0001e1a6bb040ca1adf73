package org.modelio.module.javadesigner.xmlreverse;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBElement;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.javadesigner.xmlreverse.model.IVisitorElement;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbDestination;
import org.modelio.module.javadesigner.xmlreverse.model.JaxbRealization;
import org.modelio.module.javadesigner.xmlreverse.utils.DefaultNameSpaceFinder;
import org.modelio.module.javadesigner.xmlreverse.utils.ModelElementDeleteStrategy;
import org.modelio.vcore.smkernel.mapi.MObject;

public class Repository implements IReadOnlyRepository {

    private MObject root;

    private Map<IVisitorElement, MObject> element_map;

    private Set<MObject> element_set = new HashSet<>();

    private Map<String, MObject> id_map;

    private Map<IVisitorElement, MObject> owner_map;

    private List<MObject> not_to_delete_element_list;

    private List<IVisitorElement> link_list;

    private List<JaxbRealization> realization_list;

    private IReportWriter report;

    private INameSpaceFinder nameSpaceFinder;

    private ModelElementDeleteStrategy meds;

    public Repository() {
        this.element_map = new HashMap<>();
        this.link_list = new ArrayList<>();
        this.realization_list = new ArrayList<>();
        this.id_map = new HashMap<>();
        this.owner_map = new HashMap<>();
        this.not_to_delete_element_list = new ArrayList<>();
        this.nameSpaceFinder = new DefaultNameSpaceFinder();
        this.meds = new ModelElementDeleteStrategy();
    }

    public void recordComposedElement(final MObject element, final Collection<Object> sub_elements) {
        for (Object sub : sub_elements) {
            if (sub instanceof IVisitorElement) {
                this.owner_map.put((IVisitorElement) sub, element);
            }
        }
    }

    public MObject getOwner(final IVisitorElement element) {
        MObject owner = this.owner_map.get(element);

        if (owner == null || owner.isDeleted()) {
            owner = getRoot();
        }
        return owner;
    }

    public void recordElement(final IVisitorElement jaxb_element, final MObject modelio_element) {
        if (!(jaxb_element instanceof JAXBElement)) {
            this.element_map.put(jaxb_element, modelio_element);
            this.element_set.add(modelio_element);
        }
    }

    public void recordLink(final IVisitorElement jaxb_element) {
        if (jaxb_element instanceof JaxbRealization) {
            this.realization_list.add((JaxbRealization) jaxb_element);
        } else {
            this.link_list.add(jaxb_element);
        }
    }

    public void recordId(final String id, final MObject modelio_element) {
        if (id != null) {
            if (!id.startsWith("_") && !id.startsWith("n") && this.id_map.containsKey(id)) {
                throw new InvalidParameterException();
            }
            this.id_map.put(id, modelio_element);
        }
    }

    @Override
    public MObject getElement(final IVisitorElement jaxb_element) {
        MObject element = this.element_map.get(jaxb_element);
        if (element != null && !element.isDeleted()) {
            return element;
        }
        return null;
    }

    @Override
    public MObject getElementById(final String id) {
        MObject element = this.id_map.get(id);
        if (element != null && !element.isDeleted()) {
            return element;
        }
        return null;
    }

    public Set<IVisitorElement> getElements() {
        return this.element_map.keySet();
    }

    @Override
    public Collection<MObject> getElementValues() {
        return this.element_map.values();
    }

    public List<IVisitorElement> getLinks() {
        return this.link_list;
    }

    public List<JaxbRealization> getRealizations() {
        return this.realization_list;
    }

    public void setRoot(final MObject root) {
        this.root = root;
    }

    @Override
    public MObject getRoot() {
        return this.root;
    }

    /**
     * Returns true if the element is in the "not to delete" list, or in the element map.
     */

    @Override
    public boolean isRecordedElement(final MObject element) {
        return (this.id_map.get(element.getUuid()) != null) || this.element_set.contains(element) || this.not_to_delete_element_list.contains(element);
    }

    public void setReportWriter(final IReportWriter report) {
        this.report = report;
    }

    @Override
    public IReportWriter getReportWriter() {
        return this.report;
    }

    public void recordElement(final MObject newElement) {
        this.not_to_delete_element_list.add(newElement);
    }

    public void recordElements(final List<MObject> newElements) {
        if (newElements != null) {
            this.not_to_delete_element_list.addAll(newElements);
        }
    }

    public INameSpaceFinder getNameSpaceFinder() {
        return this.nameSpaceFinder;
    }

    public void setNameSpaceFinder(final INameSpaceFinder nameSpaceFinder) {
        this.nameSpaceFinder = nameSpaceFinder;
    }

    @Override
    public MObject createNamespace(final JaxbDestination destination, final ModelTree namespaceRoot, final Class<? extends Classifier> classifierType, final IModelingSession session) {
        return this.nameSpaceFinder.createNamespace(destination, namespaceRoot, classifierType, session);
    }

    @Override
    public MObject getElementByNamespace(final JaxbDestination destination, final Class<? extends Classifier> classifierType, final IModelingSession session) {
        List<MObject> elements = this.nameSpaceFinder.getElementByNamespace(destination, classifierType, session);

        if (elements.isEmpty()) {
            return null;
        } else {
            return this.nameSpaceFinder.resolveMultipleNamespaces(elements);
        }
    }

    @Override
    public String getNamespace(final ModelTree element, final IModelingSession session) {
        return this.nameSpaceFinder.getNamespace(element, session);
    }

    @Override
    public MObject getElementByNameSpace(final String targetFullName, final Class<? extends Classifier> classifierType, final IModelingSession session) {
        return this.nameSpaceFinder.getElementByNameSpace(targetFullName, classifierType, session);
    }

    @Override
    public <T extends MObject> T resolveMultipleNamespaces(final List<T> possibleElements) {
        return this.nameSpaceFinder.resolveMultipleNamespaces(possibleElements);
    }

    @Override
    public ModelElementDeleteStrategy getModelElementDeleteStrategy() {
        return this.meds;
    }

    public void setModelElementDeleteStrategy(final ModelElementDeleteStrategy medu) {
        this.meds = medu;
    }

}
