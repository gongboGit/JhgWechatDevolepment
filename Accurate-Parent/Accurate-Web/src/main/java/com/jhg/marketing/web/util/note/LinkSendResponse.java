
package com.jhg.marketing.web.util.note;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LinkSendResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "linkSendResult"
})
@XmlRootElement(name = "LinkSendResponse")
public class LinkSendResponse {

    @XmlElement(name = "LinkSendResult")
    protected String linkSendResult;

    /**
     * Gets the value of the linkSendResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkSendResult() {
        return linkSendResult;
    }

    /**
     * Sets the value of the linkSendResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkSendResult(String value) {
        this.linkSendResult = value;
    }

}
