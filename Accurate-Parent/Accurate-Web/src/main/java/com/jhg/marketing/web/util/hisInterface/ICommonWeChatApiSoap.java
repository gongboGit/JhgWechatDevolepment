
package com.jhg.marketing.web.util.hisInterface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.7-b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ICommonWeChatApiSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ICommonWeChatApiSoap {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HelloWorld", action = "http://tempuri.org/HelloWorld")
    @WebResult(name = "HelloWorldResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "HelloWorld", targetNamespace = "http://tempuri.org/", className = "wechat.HelloWorld")
    @ResponseWrapper(localName = "HelloWorldResponse", targetNamespace = "http://tempuri.org/", className = "wechat.HelloWorldResponse")
    public String helloWorld();

    /**
     * 
     * @param input
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "WeChatBusiness", action = "http://tempuri.org/WeChatBusiness")
    @WebResult(name = "WeChatBusinessResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "WeChatBusiness", targetNamespace = "http://tempuri.org/", className = "wechat.WeChatBusiness")
    @ResponseWrapper(localName = "WeChatBusinessResponse", targetNamespace = "http://tempuri.org/", className = "wechat.WeChatBusinessResponse")
    public String weChatBusiness(
            @WebParam(name = "input", targetNamespace = "http://tempuri.org/")
                    String input);

}
