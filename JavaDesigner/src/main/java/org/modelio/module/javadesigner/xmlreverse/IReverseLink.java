package org.modelio.module.javadesigner.xmlreverse;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This interface defines all services that will be called during the reverse to make a Modelio link
 * match a JAXB element. See IReverseBox for reverse of other elements than links.
 * 
 * @param <OJAXB> Current JAXB type.
 * @param <OMODELIO> Current Modelio type.
 */

public interface IReverseLink<OJAXB, OMODELIO extends MObject, SOURCE extends MObject, TARGET extends MObject> {
    /**
     * From the JAXB element, compute a Modelio element to be returned.
     * It can be an already existing element or a new one.
     * Note that you mustn't create sub elements in this method, but rather in updateProperties.
     * @param jaxb_element The base JAXB element used to create the corresponding Modelio element.
     * @param source The new source of the link, according to the JAXB model.
     * @param target The new target of the link, according to the JAXB model.
     * @param repository Cache used to store created elements.
     * @return The created Modelio element.
     */
    
    OMODELIO getCorrespondingElement(final OJAXB jaxb_element, final SOURCE source, final TARGET target, final IReadOnlyRepository repository);

    /**
     * From the JAXB element, compute the content of the corresponding Modelio element.
     * If sub elements are created in this method, like tagged values, they must be returned to avoid deleting them later.
     * @param jaxb_element The base JAXB element used to create the corresponding Modelio element.
     * @param modelio_element The current Modelio element.
     * @param source The new source of the link, according to the JAXB model.
     * @param target The new target of the link, according to the JAXB model.
     * @param repository Cache used to store created elements.
     * @return List of all created Modelio sub elements.
     */
    
    List<MObject> updateProperties(final OJAXB jaxb_element, final OMODELIO modelio_element, final SOURCE source, final TARGET target, final IReadOnlyRepository repository);

    /**
     * Deletes all unwanted sub elements of the current Modelio element.
     * Elements returned in "getCorrespondingElement" and "updateProperties" aren't present in the given list.
     * The delete method is called only when the reversed model is complete, and before the postTreatment.
     * @param jaxb_element The base JAXB element used to create the corresponding Modelio element.
     * @param modelio_element The current Modelio element.
     * @param element_todelete List of all sub elements that might be deleted.
     * @param repository Cache used to store created elements.
     */
    
    void deleteSubElements(final OJAXB jaxb_element, final OMODELIO modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository);

    /**
     * Do some post treatment at the end of the reverse.
     * This method is called after all other methods.
     * @param jaxb_element The base JAXB element used to create the Modelio element.
     * @param modelio_element The current Modelio element.
     * @param repository Cache used to store created elements.
     */
    
    void postTreatment(final OJAXB jaxb_element, final OMODELIO modelio_element, final IReadOnlyRepository repository);

}
