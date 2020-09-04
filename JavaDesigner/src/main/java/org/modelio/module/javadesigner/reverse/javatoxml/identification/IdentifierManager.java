package org.modelio.module.javadesigner.reverse.javatoxml.identification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

/**
 * Manage the identifiers generated in XML file.
 */
public class IdentifierManager {
    /**
     * { Id, XMLid } map
     */
    private Map<Object, String> declaredIdentifiers = new HashMap<>();

    /**
     * Store the XML id generated in the XML file Allows to know the generated
     * idref not having an id in the XML file
     */
    private List<String> resolvedIdentifiers = new ArrayList<>();

    /**
     * create the XML identifier associated with a namespace id, create & register it if it doesn't already exists
     * @return the XML identifier either created or retrieved from the table
     */
    public String declareReferenceIdentifier(final Class<?> aClass) {
        assert aClass != null;
        return _declareReferenceIdentifier(aClass);
    }

    public String declareReferenceIdentifier(final Identified anElt) {
        assert anElt != null;
        return _declareReferenceIdentifier(anElt);
    }

    /**
     * Add an id to the list of defined/generated id. It means the element
     * associated with the id has been defined inside the XML file in contrast
     * with external id which are refid without corresponding id.
     * @param id
     * the identifier to add
     */
    public String defineIdentifier(final Class<?> aClass) {
        assert aClass != null;
        return _defineIdentifier(aClass);
    }

    public String defineIdentifier(final Identified anElt) {
        assert anElt != null;
        return _defineIdentifier(anElt);
    }

    /**
     * return the list of undeclared identifier by removing all declared identifier of the list of defined identifiers.
     * @return
     */
    public Collection<String> getUndefinedIdentifiers() {
        Collection<String> undef = this.declaredIdentifiers.values();
        undef.removeAll(this.resolvedIdentifiers);
        return undef;
    }

    /**
     * return the list of undeclared identifier by removing all declared identifier of the list of defined identifiers.
     * @return
     */
    public Collection<Entry<Object,String>> getUndefinedEntries() {
        Collection<Entry<Object, String>> undefinedValues = new ArrayList<>();
        
        for (Entry<Object, String> entry : this.declaredIdentifiers.entrySet()) {
        if (!this.resolvedIdentifiers.contains(entry.getValue())) {
            undefinedValues.add(entry);
        }
        }
        return undefinedValues;
    }

    private String _declareReferenceIdentifier(final Object anObject) {
        // TODO use only getUndefinedEntries
        String xmlId = this.declaredIdentifiers.get(anObject);
        if (xmlId == null) {
            // The id is not stored : create one.
            StringBuilder value = new StringBuilder("_");
            value.append(this.declaredIdentifiers.size() + 1);
            xmlId = value.toString();
            this.declaredIdentifiers.put(anObject, xmlId);
            
        }
        return xmlId;
    }

    private String _defineIdentifier(final Object anObject) {
        String id = _declareReferenceIdentifier(anObject);
        this.resolvedIdentifiers.add(id);
        return id;
    }

}
