
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
 *         &lt;element name="IntlSendResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "intlSendResult"
})
@XmlRootElement(name = "IntlSendResponse")
public class IntlSendResponse {

    @XmlElement(name = "IntlSendResult")
    protected String intlSendResult;

    /**
     * Gets the value of the intlSendResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntlSendResult() {
        return intlSendResult;
    }

    /**
     * Sets the value of the intlSendResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntlSendResult(String value) {
        this.intlSendResult = value;
    }

}