//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2010.10.27 at 01:27:01 PM CEST
//
package org.modelio.module.javadesigner.xmlreverse.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
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
 * &lt;element ref="{http://www.modeliosoft.com/rev-modele.xsd}used-package"/>
 * &lt;choice maxOccurs="unbounded" minOccurs="0">
 * &lt;element ref="{http://www.modeliosoft.com/rev-modele.xsd}note"/>
 * &lt;element ref="{http://www.modeliosoft.com/rev-modele.xsd}constraint"/>
 * &lt;element ref="{http://www.modeliosoft.com/rev-modele.xsd}stereotype"/>
 * &lt;element ref="{http://www.modeliosoft.com/rev-modele.xsd}tagged-value"/>
 * &lt;/choice>
 * &lt;/sequence>
 * &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usedPackage",
    "noteOrConstraintOrStereotype"
})
@XmlRootElement(name = "package-import")
public class JaxbPackageImport implements IVisitorElement {
    
    @XmlAttribute
    protected String name;

    
    @XmlElement(name = "used-package", required = true)
    protected JaxbUsedPackage usedPackage;

    
    @XmlElements({
    @XmlElement(name = "note", type = JaxbNote.class),
    @XmlElement(name = "stereotype", type = JaxbStereotype.class),
    @XmlElement(name = "constraint", type = JaxbConstraint.class),
    @XmlElement(name = "tagged-value", type = JaxbTaggedValue.class)
})
    protected List<Object> noteOrConstraintOrStereotype;

    /**
     * Gets the value of the usedPackage property.
     * @return
     * possible object is
     * {@link JaxbUsedPackage }
     */
    
    public JaxbUsedPackage getUsedPackage() {
        return this.usedPackage;
    }

    /**
     * Sets the value of the usedPackage property.
     * @param value allowed object is
     * {@link JaxbUsedPackage }
     */
    
    public void setUsedPackage(final JaxbUsedPackage value) {
        this.usedPackage = value;
    }

    /**
     * Gets the value of the noteOrConstraintOrStereotype property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the noteOrConstraintOrStereotype property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getNoteOrConstraintOrStereotype().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JaxbNote }
     * {@link JaxbStereotype }
     * {@link JaxbConstraint }
     * {@link JaxbTaggedValue }
     */
    
    public List<Object> getNoteOrConstraintOrStereotype() {
        if (this.noteOrConstraintOrStereotype == null) {
            this.noteOrConstraintOrStereotype = new ArrayList<>();
        }
        return this.noteOrConstraintOrStereotype;
    }

    /**
     * Gets the value of the name property.
     * @return
     * possible object is
     * {@link String }
     */
    
    public String getName() {
        return this.name;
    }

    /**
     * Sets the value of the name property.
     * @param value allowed object is
     * {@link String }
     */
    
    public void setName(final String value) {
        this.name = value;
    }

    
    @Override
    public Object accept(final IReverseModelVisitor visitor) {
        return visitor.visitPackageImport(this);
    }

}
