//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2010.10.27 at 01:27:01 PM CEST
//
package org.modelio.module.javadesigner.xmlreverse.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.modelio.module.javadesigner.xmlreverse.model.defaultvisitor.IReverseModelVisitor;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element ref="{http://www.modeliosoft.com/rev-modele.xsd}destination"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "destination"
})
@XmlRootElement(name = "class-type")
public class JaxbClassType implements IVisitorElement {
    
    @XmlElement(required = true)
    protected JaxbDestination destination;

    /**
     * Gets the value of the destination property.
     * @return
     * possible object is
     * {@link JaxbDestination }
     */
    
    public JaxbDestination getDestination() {
        return this.destination;
    }

    /**
     * Sets the value of the destination property.
     * @param value allowed object is
     * {@link JaxbDestination }
     */
    
    public void setDestination(final JaxbDestination value) {
        this.destination = value;
    }

    
    @Override
    public Object accept(final IReverseModelVisitor visitor) {
        return visitor.visitClassType(this);
    }

}
