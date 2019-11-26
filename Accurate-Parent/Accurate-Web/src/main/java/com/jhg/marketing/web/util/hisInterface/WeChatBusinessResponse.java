
package com.jhg.marketing.web.util.hisInterface;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WeChatBusinessResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "weChatBusinessResult"
})
@XmlRootElement(name = "WeChatBusinessResponse")
public class WeChatBusinessResponse {

    @XmlElement(name = "WeChatBusinessResult")
    protected String weChatBusinessResult;

    /**
     * ��ȡweChatBusinessResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeChatBusinessResult() {
        return weChatBusinessResult;
    }

    /**
     * ����weChatBusinessResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeChatBusinessResult(String value) {
        this.weChatBusinessResult = value;
    }

}
