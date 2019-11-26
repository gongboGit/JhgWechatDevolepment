
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
 *         &lt;element name="CorpID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="verify_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vtpl_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="show_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "corpID",
    "pwd",
    "mobile",
    "verifyCode",
    "vtplId",
    "showNum"
})
@XmlRootElement(name = "VoiceNotify")
public class VoiceNotify {

    @XmlElement(name = "CorpID")
    protected String corpID;
    @XmlElement(name = "Pwd")
    protected String pwd;
    @XmlElement(name = "Mobile")
    protected String mobile;
    @XmlElement(name = "verify_code")
    protected String verifyCode;
    @XmlElement(name = "vtpl_id")
    protected String vtplId;
    @XmlElement(name = "show_num")
    protected String showNum;

    /**
     * Gets the value of the corpID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorpID() {
        return corpID;
    }

    /**
     * Sets the value of the corpID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorpID(String value) {
        this.corpID = value;
    }

    /**
     * Gets the value of the pwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Sets the value of the pwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPwd(String value) {
        this.pwd = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the verifyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * Sets the value of the verifyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerifyCode(String value) {
        this.verifyCode = value;
    }

    /**
     * Gets the value of the vtplId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVtplId() {
        return vtplId;
    }

    /**
     * Sets the value of the vtplId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVtplId(String value) {
        this.vtplId = value;
    }

    /**
     * Gets the value of the showNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShowNum() {
        return showNum;
    }

    /**
     * Sets the value of the showNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowNum(String value) {
        this.showNum = value;
    }

}
