package com.demandware.xml.impex.catalog._2006_10_31;
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.06 at 05:15:13 PM NZDT 
//




import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sharedType.SiteSpecificCustomAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sharedType.SiteSpecificCustomAttribute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.demandware.com/xml/impex/catalog/2006-10-31}sharedType.CustomAttribute"&gt;
 *       &lt;attribute name="site-id" type="{http://www.demandware.com/xml/impex/catalog/2006-10-31}simpleType.Generic.NonEmptyString.32" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sharedType.SiteSpecificCustomAttribute")
public class SharedTypeSiteSpecificCustomAttribute
    extends SharedTypeCustomAttribute
{

    @XmlAttribute(name = "site-id")
    protected String siteId;

    /**
     * Gets the value of the siteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Sets the value of the siteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteId(String value) {
        this.siteId = value;
    }

}
