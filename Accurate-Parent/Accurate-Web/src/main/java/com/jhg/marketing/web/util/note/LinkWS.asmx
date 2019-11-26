<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:element name="HelloWorld">
        <s:complexType />
      </s:element>
      <s:element name="HelloWorldResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="HelloWorldResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchSend">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Cell" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchSendResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="BatchSendResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReportFail">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cell" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReportFailResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetReportFailResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Reg">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CorpName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="LinkMan" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Tel" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Email" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Memo" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="RegResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="RegResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UpdPwd">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="NewPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UpdPwdResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="UpdPwdResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UpdReg">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CorpName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="LinkMan" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Tel" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Email" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Memo" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UpdRegResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="UpdRegResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SelSum">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SelSumResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="SelSumResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetBalance">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetBalanceResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetBalanceResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Send">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Cell" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="SendResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Get">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UnReg">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UnRegResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="UnRegResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ChargeUp">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CardNo" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CardPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ChargeUpResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="ChargeUpResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="AgentChangeAccount">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="LoginName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="LoginPwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="SmsBalance" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="MmsBalance" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="AgentChangeAccountResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="AgentChangeAccountResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BalanceMMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BalanceMMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BalanceMMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendMMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Base64Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Title" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ExtCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendMMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendMMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchSendMMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobiles" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Base64Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Title" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ExtCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchSendMMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BatchSendMMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReportFailMMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="cell" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReportFailMMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetReportFailMMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetMMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetMMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetMMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ChargeUpMMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CardNo" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CardPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ChargeUpMMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ChargeUpMMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="AgentMakeAccount">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="LoginName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="LoginPwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CorpName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="LinkMan" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Tel" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Email" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Memo" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pass" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="AgentMakeAccountResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="AgentMakeAccountResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReportSMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReportSMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetReportSMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReportMMS">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReportMMSResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetReportMMSResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Send2">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Cell" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Send2Response">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Send2Result" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendMMS2">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Base64Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Title" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ExtCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendMMS2Response">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendMMS2Result" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchSend2">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Cell" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchSend2Response">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BatchSend2Result" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IntlSend">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Cell" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IntlSendResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="IntlSendResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchSendMMS2">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobiles" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Base64Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Title" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ExtCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchSendMMS2Response">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BatchSendMMS2Result" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="VoiceNotify">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="verify_code" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="vtpl_id" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="show_num" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="VoiceNotifyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="VoiceNotifyResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="NotSend">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="NotSendResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="NotSendResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchContentSms">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="JsonString" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="BatchContentSmsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="BatchContentSmsResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="LinkSend">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CorpID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Pwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Mobile" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Content" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Cell" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SendTime" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="LinkSendResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="LinkSendResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="string" nillable="true" type="s:string" />
      <s:element name="int" type="s:int" />
    </s:schema>
  </wsdl:types>
  <wsdl:message name="HelloWorldSoapIn">
    <wsdl:part name="parameters" element="tns:HelloWorld" />
  </wsdl:message>
  <wsdl:message name="HelloWorldSoapOut">
    <wsdl:part name="parameters" element="tns:HelloWorldResponse" />
  </wsdl:message>
  <wsdl:message name="BatchSendSoapIn">
    <wsdl:part name="parameters" element="tns:BatchSend" />
  </wsdl:message>
  <wsdl:message name="BatchSendSoapOut">
    <wsdl:part name="parameters" element="tns:BatchSendResponse" />
  </wsdl:message>
  <wsdl:message name="GetReportFailSoapIn">
    <wsdl:part name="parameters" element="tns:GetReportFail" />
  </wsdl:message>
  <wsdl:message name="GetReportFailSoapOut">
    <wsdl:part name="parameters" element="tns:GetReportFailResponse" />
  </wsdl:message>
  <wsdl:message name="RegSoapIn">
    <wsdl:part name="parameters" element="tns:Reg" />
  </wsdl:message>
  <wsdl:message name="RegSoapOut">
    <wsdl:part name="parameters" element="tns:RegResponse" />
  </wsdl:message>
  <wsdl:message name="UpdPwdSoapIn">
    <wsdl:part name="parameters" element="tns:UpdPwd" />
  </wsdl:message>
  <wsdl:message name="UpdPwdSoapOut">
    <wsdl:part name="parameters" element="tns:UpdPwdResponse" />
  </wsdl:message>
  <wsdl:message name="UpdRegSoapIn">
    <wsdl:part name="parameters" element="tns:UpdReg" />
  </wsdl:message>
  <wsdl:message name="UpdRegSoapOut">
    <wsdl:part name="parameters" element="tns:UpdRegResponse" />
  </wsdl:message>
  <wsdl:message name="SelSumSoapIn">
    <wsdl:part name="parameters" element="tns:SelSum" />
  </wsdl:message>
  <wsdl:message name="SelSumSoapOut">
    <wsdl:part name="parameters" element="tns:SelSumResponse" />
  </wsdl:message>
  <wsdl:message name="GetBalanceSoapIn">
    <wsdl:part name="parameters" element="tns:GetBalance" />
  </wsdl:message>
  <wsdl:message name="GetBalanceSoapOut">
    <wsdl:part name="parameters" element="tns:GetBalanceResponse" />
  </wsdl:message>
  <wsdl:message name="SendSoapIn">
    <wsdl:part name="parameters" element="tns:Send" />
  </wsdl:message>
  <wsdl:message name="SendSoapOut">
    <wsdl:part name="parameters" element="tns:SendResponse" />
  </wsdl:message>
  <wsdl:message name="GetSoapIn">
    <wsdl:part name="parameters" element="tns:Get" />
  </wsdl:message>
  <wsdl:message name="GetSoapOut">
    <wsdl:part name="parameters" element="tns:GetResponse" />
  </wsdl:message>
  <wsdl:message name="UnRegSoapIn">
    <wsdl:part name="parameters" element="tns:UnReg" />
  </wsdl:message>
  <wsdl:message name="UnRegSoapOut">
    <wsdl:part name="parameters" element="tns:UnRegResponse" />
  </wsdl:message>
  <wsdl:message name="ChargeUpSoapIn">
    <wsdl:part name="parameters" element="tns:ChargeUp" />
  </wsdl:message>
  <wsdl:message name="ChargeUpSoapOut">
    <wsdl:part name="parameters" element="tns:ChargeUpResponse" />
  </wsdl:message>
  <wsdl:message name="AgentChangeAccountSoapIn">
    <wsdl:part name="parameters" element="tns:AgentChangeAccount" />
  </wsdl:message>
  <wsdl:message name="AgentChangeAccountSoapOut">
    <wsdl:part name="parameters" element="tns:AgentChangeAccountResponse" />
  </wsdl:message>
  <wsdl:message name="BalanceMMSSoapIn">
    <wsdl:part name="parameters" element="tns:BalanceMMS" />
  </wsdl:message>
  <wsdl:message name="BalanceMMSSoapOut">
    <wsdl:part name="parameters" element="tns:BalanceMMSResponse" />
  </wsdl:message>
  <wsdl:message name="SendMMSSoapIn">
    <wsdl:part name="parameters" element="tns:SendMMS" />
  </wsdl:message>
  <wsdl:message name="SendMMSSoapOut">
    <wsdl:part name="parameters" element="tns:SendMMSResponse" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMSSoapIn">
    <wsdl:part name="parameters" element="tns:BatchSendMMS" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMSSoapOut">
    <wsdl:part name="parameters" element="tns:BatchSendMMSResponse" />
  </wsdl:message>
  <wsdl:message name="GetReportFailMMSSoapIn">
    <wsdl:part name="parameters" element="tns:GetReportFailMMS" />
  </wsdl:message>
  <wsdl:message name="GetReportFailMMSSoapOut">
    <wsdl:part name="parameters" element="tns:GetReportFailMMSResponse" />
  </wsdl:message>
  <wsdl:message name="GetMMSSoapIn">
    <wsdl:part name="parameters" element="tns:GetMMS" />
  </wsdl:message>
  <wsdl:message name="GetMMSSoapOut">
    <wsdl:part name="parameters" element="tns:GetMMSResponse" />
  </wsdl:message>
  <wsdl:message name="ChargeUpMMSSoapIn">
    <wsdl:part name="parameters" element="tns:ChargeUpMMS" />
  </wsdl:message>
  <wsdl:message name="ChargeUpMMSSoapOut">
    <wsdl:part name="parameters" element="tns:ChargeUpMMSResponse" />
  </wsdl:message>
  <wsdl:message name="AgentMakeAccountSoapIn">
    <wsdl:part name="parameters" element="tns:AgentMakeAccount" />
  </wsdl:message>
  <wsdl:message name="AgentMakeAccountSoapOut">
    <wsdl:part name="parameters" element="tns:AgentMakeAccountResponse" />
  </wsdl:message>
  <wsdl:message name="GetReportSMSSoapIn">
    <wsdl:part name="parameters" element="tns:GetReportSMS" />
  </wsdl:message>
  <wsdl:message name="GetReportSMSSoapOut">
    <wsdl:part name="parameters" element="tns:GetReportSMSResponse" />
  </wsdl:message>
  <wsdl:message name="GetReportMMSSoapIn">
    <wsdl:part name="parameters" element="tns:GetReportMMS" />
  </wsdl:message>
  <wsdl:message name="GetReportMMSSoapOut">
    <wsdl:part name="parameters" element="tns:GetReportMMSResponse" />
  </wsdl:message>
  <wsdl:message name="Send2SoapIn">
    <wsdl:part name="parameters" element="tns:Send2" />
  </wsdl:message>
  <wsdl:message name="Send2SoapOut">
    <wsdl:part name="parameters" element="tns:Send2Response" />
  </wsdl:message>
  <wsdl:message name="SendMMS2SoapIn">
    <wsdl:part name="parameters" element="tns:SendMMS2" />
  </wsdl:message>
  <wsdl:message name="SendMMS2SoapOut">
    <wsdl:part name="parameters" element="tns:SendMMS2Response" />
  </wsdl:message>
  <wsdl:message name="BatchSend2SoapIn">
    <wsdl:part name="parameters" element="tns:BatchSend2" />
  </wsdl:message>
  <wsdl:message name="BatchSend2SoapOut">
    <wsdl:part name="parameters" element="tns:BatchSend2Response" />
  </wsdl:message>
  <wsdl:message name="IntlSendSoapIn">
    <wsdl:part name="parameters" element="tns:IntlSend" />
  </wsdl:message>
  <wsdl:message name="IntlSendSoapOut">
    <wsdl:part name="parameters" element="tns:IntlSendResponse" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMS2SoapIn">
    <wsdl:part name="parameters" element="tns:BatchSendMMS2" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMS2SoapOut">
    <wsdl:part name="parameters" element="tns:BatchSendMMS2Response" />
  </wsdl:message>
  <wsdl:message name="VoiceNotifySoapIn">
    <wsdl:part name="parameters" element="tns:VoiceNotify" />
  </wsdl:message>
  <wsdl:message name="VoiceNotifySoapOut">
    <wsdl:part name="parameters" element="tns:VoiceNotifyResponse" />
  </wsdl:message>
  <wsdl:message name="NotSendSoapIn">
    <wsdl:part name="parameters" element="tns:NotSend" />
  </wsdl:message>
  <wsdl:message name="NotSendSoapOut">
    <wsdl:part name="parameters" element="tns:NotSendResponse" />
  </wsdl:message>
  <wsdl:message name="BatchContentSmsSoapIn">
    <wsdl:part name="parameters" element="tns:BatchContentSms" />
  </wsdl:message>
  <wsdl:message name="BatchContentSmsSoapOut">
    <wsdl:part name="parameters" element="tns:BatchContentSmsResponse" />
  </wsdl:message>
  <wsdl:message name="LinkSendSoapIn">
    <wsdl:part name="parameters" element="tns:LinkSend" />
  </wsdl:message>
  <wsdl:message name="LinkSendSoapOut">
    <wsdl:part name="parameters" element="tns:LinkSendResponse" />
  </wsdl:message>
  <wsdl:message name="HelloWorldHttpGetIn" />
  <wsdl:message name="HelloWorldHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetReportFailHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="cell" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetReportFailHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="RegHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="CorpName" type="s:string" />
    <wsdl:part name="LinkMan" type="s:string" />
    <wsdl:part name="Tel" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Email" type="s:string" />
    <wsdl:part name="Memo" type="s:string" />
  </wsdl:message>
  <wsdl:message name="RegHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="UpdPwdHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="NewPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="UpdPwdHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="UpdRegHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="CorpName" type="s:string" />
    <wsdl:part name="LinkMan" type="s:string" />
    <wsdl:part name="Tel" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Email" type="s:string" />
    <wsdl:part name="Memo" type="s:string" />
  </wsdl:message>
  <wsdl:message name="UpdRegHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="SelSumHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SelSumHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetBalanceHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetBalanceHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="UnRegHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="UnRegHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="ChargeUpHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="CardNo" type="s:string" />
    <wsdl:part name="CardPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="ChargeUpHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="AgentChangeAccountHttpGetIn">
    <wsdl:part name="LoginName" type="s:string" />
    <wsdl:part name="LoginPwd" type="s:string" />
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="SmsBalance" type="s:string" />
    <wsdl:part name="MmsBalance" type="s:string" />
  </wsdl:message>
  <wsdl:message name="AgentChangeAccountHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BalanceMMSHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BalanceMMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendMMSHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Base64Content" type="s:string" />
    <wsdl:part name="Title" type="s:string" />
    <wsdl:part name="ExtCode" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendMMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMSHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobiles" type="s:string" />
    <wsdl:part name="Base64Content" type="s:string" />
    <wsdl:part name="Title" type="s:string" />
    <wsdl:part name="ExtCode" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetReportFailMMSHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="cell" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetReportFailMMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetMMSHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetMMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="ChargeUpMMSHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="CardNo" type="s:string" />
    <wsdl:part name="CardPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="ChargeUpMMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="AgentMakeAccountHttpGetIn">
    <wsdl:part name="LoginName" type="s:string" />
    <wsdl:part name="LoginPwd" type="s:string" />
    <wsdl:part name="CorpName" type="s:string" />
    <wsdl:part name="LinkMan" type="s:string" />
    <wsdl:part name="Tel" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Email" type="s:string" />
    <wsdl:part name="Memo" type="s:string" />
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pass" type="s:string" />
  </wsdl:message>
  <wsdl:message name="AgentMakeAccountHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetReportSMSHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetReportSMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetReportMMSHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetReportMMSHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="Send2HttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="Send2HttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendMMS2HttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Base64Content" type="s:string" />
    <wsdl:part name="Title" type="s:string" />
    <wsdl:part name="ExtCode" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendMMS2HttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchSend2HttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchSend2HttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="IntlSendHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="IntlSendHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMS2HttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobiles" type="s:string" />
    <wsdl:part name="Base64Content" type="s:string" />
    <wsdl:part name="Title" type="s:string" />
    <wsdl:part name="ExtCode" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMS2HttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="VoiceNotifyHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="verify_code" type="s:string" />
    <wsdl:part name="vtpl_id" type="s:string" />
    <wsdl:part name="show_num" type="s:string" />
  </wsdl:message>
  <wsdl:message name="VoiceNotifyHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="NotSendHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="NotSendHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchContentSmsHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="JsonString" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchContentSmsHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="LinkSendHttpGetIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="LinkSendHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="HelloWorldHttpPostIn" />
  <wsdl:message name="HelloWorldHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetReportFailHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="cell" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetReportFailHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="RegHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="CorpName" type="s:string" />
    <wsdl:part name="LinkMan" type="s:string" />
    <wsdl:part name="Tel" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Email" type="s:string" />
    <wsdl:part name="Memo" type="s:string" />
  </wsdl:message>
  <wsdl:message name="RegHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="UpdPwdHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="NewPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="UpdPwdHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="UpdRegHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="CorpName" type="s:string" />
    <wsdl:part name="LinkMan" type="s:string" />
    <wsdl:part name="Tel" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Email" type="s:string" />
    <wsdl:part name="Memo" type="s:string" />
  </wsdl:message>
  <wsdl:message name="UpdRegHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="SelSumHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SelSumHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetBalanceHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetBalanceHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="UnRegHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="UnRegHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="ChargeUpHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="CardNo" type="s:string" />
    <wsdl:part name="CardPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="ChargeUpHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="AgentChangeAccountHttpPostIn">
    <wsdl:part name="LoginName" type="s:string" />
    <wsdl:part name="LoginPwd" type="s:string" />
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="SmsBalance" type="s:string" />
    <wsdl:part name="MmsBalance" type="s:string" />
  </wsdl:message>
  <wsdl:message name="AgentChangeAccountHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BalanceMMSHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BalanceMMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendMMSHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Base64Content" type="s:string" />
    <wsdl:part name="Title" type="s:string" />
    <wsdl:part name="ExtCode" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendMMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMSHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobiles" type="s:string" />
    <wsdl:part name="Base64Content" type="s:string" />
    <wsdl:part name="Title" type="s:string" />
    <wsdl:part name="ExtCode" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetReportFailMMSHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="cell" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetReportFailMMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetMMSHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetMMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="ChargeUpMMSHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="CardNo" type="s:string" />
    <wsdl:part name="CardPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="ChargeUpMMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="AgentMakeAccountHttpPostIn">
    <wsdl:part name="LoginName" type="s:string" />
    <wsdl:part name="LoginPwd" type="s:string" />
    <wsdl:part name="CorpName" type="s:string" />
    <wsdl:part name="LinkMan" type="s:string" />
    <wsdl:part name="Tel" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Email" type="s:string" />
    <wsdl:part name="Memo" type="s:string" />
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pass" type="s:string" />
  </wsdl:message>
  <wsdl:message name="AgentMakeAccountHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetReportSMSHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetReportSMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetReportMMSHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetReportMMSHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="Send2HttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="Send2HttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="SendMMS2HttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Base64Content" type="s:string" />
    <wsdl:part name="Title" type="s:string" />
    <wsdl:part name="ExtCode" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SendMMS2HttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchSend2HttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchSend2HttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="IntlSendHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="IntlSendHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMS2HttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobiles" type="s:string" />
    <wsdl:part name="Base64Content" type="s:string" />
    <wsdl:part name="Title" type="s:string" />
    <wsdl:part name="ExtCode" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchSendMMS2HttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="VoiceNotifyHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="verify_code" type="s:string" />
    <wsdl:part name="vtpl_id" type="s:string" />
    <wsdl:part name="show_num" type="s:string" />
  </wsdl:message>
  <wsdl:message name="VoiceNotifyHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="NotSendHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="NotSendHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="BatchContentSmsHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="JsonString" type="s:string" />
  </wsdl:message>
  <wsdl:message name="BatchContentSmsHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="LinkSendHttpPostIn">
    <wsdl:part name="CorpID" type="s:string" />
    <wsdl:part name="Pwd" type="s:string" />
    <wsdl:part name="Mobile" type="s:string" />
    <wsdl:part name="Content" type="s:string" />
    <wsdl:part name="Cell" type="s:string" />
    <wsdl:part name="SendTime" type="s:string" />
  </wsdl:message>
  <wsdl:message name="LinkSendHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:portType name="LinkWSSoap">
    <wsdl:operation name="HelloWorld">
      <wsdl:input message="tns:HelloWorldSoapIn" />
      <wsdl:output message="tns:HelloWorldSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSend">
      <wsdl:input message="tns:BatchSendSoapIn" />
      <wsdl:output message="tns:BatchSendSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportFail">
      <wsdl:input message="tns:GetReportFailSoapIn" />
      <wsdl:output message="tns:GetReportFailSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Reg">
      <wsdl:input message="tns:RegSoapIn" />
      <wsdl:output message="tns:RegSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="UpdPwd">
      <wsdl:input message="tns:UpdPwdSoapIn" />
      <wsdl:output message="tns:UpdPwdSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="UpdReg">
      <wsdl:input message="tns:UpdRegSoapIn" />
      <wsdl:output message="tns:UpdRegSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SelSum">
      <wsdl:input message="tns:SelSumSoapIn" />
      <wsdl:output message="tns:SelSumSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetBalance">
      <wsdl:input message="tns:GetBalanceSoapIn" />
      <wsdl:output message="tns:GetBalanceSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Send">
      <wsdl:input message="tns:SendSoapIn" />
      <wsdl:output message="tns:SendSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Get">
      <wsdl:input message="tns:GetSoapIn" />
      <wsdl:output message="tns:GetSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="UnReg">
      <wsdl:input message="tns:UnRegSoapIn" />
      <wsdl:output message="tns:UnRegSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ChargeUp">
      <wsdl:input message="tns:ChargeUpSoapIn" />
      <wsdl:output message="tns:ChargeUpSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="AgentChangeAccount">
      <wsdl:input message="tns:AgentChangeAccountSoapIn" />
      <wsdl:output message="tns:AgentChangeAccountSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BalanceMMS">
      <wsdl:input message="tns:BalanceMMSSoapIn" />
      <wsdl:output message="tns:BalanceMMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SendMMS">
      <wsdl:input message="tns:SendMMSSoapIn" />
      <wsdl:output message="tns:SendMMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS">
      <wsdl:input message="tns:BatchSendMMSSoapIn" />
      <wsdl:output message="tns:BatchSendMMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportFailMMS">
      <wsdl:input message="tns:GetReportFailMMSSoapIn" />
      <wsdl:output message="tns:GetReportFailMMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetMMS">
      <wsdl:input message="tns:GetMMSSoapIn" />
      <wsdl:output message="tns:GetMMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ChargeUpMMS">
      <wsdl:input message="tns:ChargeUpMMSSoapIn" />
      <wsdl:output message="tns:ChargeUpMMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="AgentMakeAccount">
      <wsdl:input message="tns:AgentMakeAccountSoapIn" />
      <wsdl:output message="tns:AgentMakeAccountSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportSMS">
      <wsdl:input message="tns:GetReportSMSSoapIn" />
      <wsdl:output message="tns:GetReportSMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportMMS">
      <wsdl:input message="tns:GetReportMMSSoapIn" />
      <wsdl:output message="tns:GetReportMMSSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Send2">
      <wsdl:input message="tns:Send2SoapIn" />
      <wsdl:output message="tns:Send2SoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SendMMS2">
      <wsdl:input message="tns:SendMMS2SoapIn" />
      <wsdl:output message="tns:SendMMS2SoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSend2">
      <wsdl:input message="tns:BatchSend2SoapIn" />
      <wsdl:output message="tns:BatchSend2SoapOut" />
    </wsdl:operation>
    <wsdl:operation name="IntlSend">
      <wsdl:input message="tns:IntlSendSoapIn" />
      <wsdl:output message="tns:IntlSendSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS2">
      <wsdl:input message="tns:BatchSendMMS2SoapIn" />
      <wsdl:output message="tns:BatchSendMMS2SoapOut" />
    </wsdl:operation>
    <wsdl:operation name="VoiceNotify">
      <wsdl:input message="tns:VoiceNotifySoapIn" />
      <wsdl:output message="tns:VoiceNotifySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="NotSend">
      <wsdl:input message="tns:NotSendSoapIn" />
      <wsdl:output message="tns:NotSendSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchContentSms">
      <wsdl:input message="tns:BatchContentSmsSoapIn" />
      <wsdl:output message="tns:BatchContentSmsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="LinkSend">
      <wsdl:input message="tns:LinkSendSoapIn" />
      <wsdl:output message="tns:LinkSendSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="LinkWSHttpGet">
    <wsdl:operation name="HelloWorld">
      <wsdl:input message="tns:HelloWorldHttpGetIn" />
      <wsdl:output message="tns:HelloWorldHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSend">
      <wsdl:input message="tns:BatchSendHttpGetIn" />
      <wsdl:output message="tns:BatchSendHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportFail">
      <wsdl:input message="tns:GetReportFailHttpGetIn" />
      <wsdl:output message="tns:GetReportFailHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="Reg">
      <wsdl:input message="tns:RegHttpGetIn" />
      <wsdl:output message="tns:RegHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="UpdPwd">
      <wsdl:input message="tns:UpdPwdHttpGetIn" />
      <wsdl:output message="tns:UpdPwdHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="UpdReg">
      <wsdl:input message="tns:UpdRegHttpGetIn" />
      <wsdl:output message="tns:UpdRegHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SelSum">
      <wsdl:input message="tns:SelSumHttpGetIn" />
      <wsdl:output message="tns:SelSumHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetBalance">
      <wsdl:input message="tns:GetBalanceHttpGetIn" />
      <wsdl:output message="tns:GetBalanceHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="Send">
      <wsdl:input message="tns:SendHttpGetIn" />
      <wsdl:output message="tns:SendHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="Get">
      <wsdl:input message="tns:GetHttpGetIn" />
      <wsdl:output message="tns:GetHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="UnReg">
      <wsdl:input message="tns:UnRegHttpGetIn" />
      <wsdl:output message="tns:UnRegHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="ChargeUp">
      <wsdl:input message="tns:ChargeUpHttpGetIn" />
      <wsdl:output message="tns:ChargeUpHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="AgentChangeAccount">
      <wsdl:input message="tns:AgentChangeAccountHttpGetIn" />
      <wsdl:output message="tns:AgentChangeAccountHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="BalanceMMS">
      <wsdl:input message="tns:BalanceMMSHttpGetIn" />
      <wsdl:output message="tns:BalanceMMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SendMMS">
      <wsdl:input message="tns:SendMMSHttpGetIn" />
      <wsdl:output message="tns:SendMMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS">
      <wsdl:input message="tns:BatchSendMMSHttpGetIn" />
      <wsdl:output message="tns:BatchSendMMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportFailMMS">
      <wsdl:input message="tns:GetReportFailMMSHttpGetIn" />
      <wsdl:output message="tns:GetReportFailMMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetMMS">
      <wsdl:input message="tns:GetMMSHttpGetIn" />
      <wsdl:output message="tns:GetMMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="ChargeUpMMS">
      <wsdl:input message="tns:ChargeUpMMSHttpGetIn" />
      <wsdl:output message="tns:ChargeUpMMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="AgentMakeAccount">
      <wsdl:input message="tns:AgentMakeAccountHttpGetIn" />
      <wsdl:output message="tns:AgentMakeAccountHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportSMS">
      <wsdl:input message="tns:GetReportSMSHttpGetIn" />
      <wsdl:output message="tns:GetReportSMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportMMS">
      <wsdl:input message="tns:GetReportMMSHttpGetIn" />
      <wsdl:output message="tns:GetReportMMSHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="Send2">
      <wsdl:input message="tns:Send2HttpGetIn" />
      <wsdl:output message="tns:Send2HttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SendMMS2">
      <wsdl:input message="tns:SendMMS2HttpGetIn" />
      <wsdl:output message="tns:SendMMS2HttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSend2">
      <wsdl:input message="tns:BatchSend2HttpGetIn" />
      <wsdl:output message="tns:BatchSend2HttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="IntlSend">
      <wsdl:input message="tns:IntlSendHttpGetIn" />
      <wsdl:output message="tns:IntlSendHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS2">
      <wsdl:input message="tns:BatchSendMMS2HttpGetIn" />
      <wsdl:output message="tns:BatchSendMMS2HttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="VoiceNotify">
      <wsdl:input message="tns:VoiceNotifyHttpGetIn" />
      <wsdl:output message="tns:VoiceNotifyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="NotSend">
      <wsdl:input message="tns:NotSendHttpGetIn" />
      <wsdl:output message="tns:NotSendHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchContentSms">
      <wsdl:input message="tns:BatchContentSmsHttpGetIn" />
      <wsdl:output message="tns:BatchContentSmsHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="LinkSend">
      <wsdl:input message="tns:LinkSendHttpGetIn" />
      <wsdl:output message="tns:LinkSendHttpGetOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="LinkWSHttpPost">
    <wsdl:operation name="HelloWorld">
      <wsdl:input message="tns:HelloWorldHttpPostIn" />
      <wsdl:output message="tns:HelloWorldHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSend">
      <wsdl:input message="tns:BatchSendHttpPostIn" />
      <wsdl:output message="tns:BatchSendHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportFail">
      <wsdl:input message="tns:GetReportFailHttpPostIn" />
      <wsdl:output message="tns:GetReportFailHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="Reg">
      <wsdl:input message="tns:RegHttpPostIn" />
      <wsdl:output message="tns:RegHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="UpdPwd">
      <wsdl:input message="tns:UpdPwdHttpPostIn" />
      <wsdl:output message="tns:UpdPwdHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="UpdReg">
      <wsdl:input message="tns:UpdRegHttpPostIn" />
      <wsdl:output message="tns:UpdRegHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SelSum">
      <wsdl:input message="tns:SelSumHttpPostIn" />
      <wsdl:output message="tns:SelSumHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetBalance">
      <wsdl:input message="tns:GetBalanceHttpPostIn" />
      <wsdl:output message="tns:GetBalanceHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="Send">
      <wsdl:input message="tns:SendHttpPostIn" />
      <wsdl:output message="tns:SendHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="Get">
      <wsdl:input message="tns:GetHttpPostIn" />
      <wsdl:output message="tns:GetHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="UnReg">
      <wsdl:input message="tns:UnRegHttpPostIn" />
      <wsdl:output message="tns:UnRegHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="ChargeUp">
      <wsdl:input message="tns:ChargeUpHttpPostIn" />
      <wsdl:output message="tns:ChargeUpHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="AgentChangeAccount">
      <wsdl:input message="tns:AgentChangeAccountHttpPostIn" />
      <wsdl:output message="tns:AgentChangeAccountHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="BalanceMMS">
      <wsdl:input message="tns:BalanceMMSHttpPostIn" />
      <wsdl:output message="tns:BalanceMMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SendMMS">
      <wsdl:input message="tns:SendMMSHttpPostIn" />
      <wsdl:output message="tns:SendMMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS">
      <wsdl:input message="tns:BatchSendMMSHttpPostIn" />
      <wsdl:output message="tns:BatchSendMMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportFailMMS">
      <wsdl:input message="tns:GetReportFailMMSHttpPostIn" />
      <wsdl:output message="tns:GetReportFailMMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetMMS">
      <wsdl:input message="tns:GetMMSHttpPostIn" />
      <wsdl:output message="tns:GetMMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="ChargeUpMMS">
      <wsdl:input message="tns:ChargeUpMMSHttpPostIn" />
      <wsdl:output message="tns:ChargeUpMMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="AgentMakeAccount">
      <wsdl:input message="tns:AgentMakeAccountHttpPostIn" />
      <wsdl:output message="tns:AgentMakeAccountHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportSMS">
      <wsdl:input message="tns:GetReportSMSHttpPostIn" />
      <wsdl:output message="tns:GetReportSMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReportMMS">
      <wsdl:input message="tns:GetReportMMSHttpPostIn" />
      <wsdl:output message="tns:GetReportMMSHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="Send2">
      <wsdl:input message="tns:Send2HttpPostIn" />
      <wsdl:output message="tns:Send2HttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SendMMS2">
      <wsdl:input message="tns:SendMMS2HttpPostIn" />
      <wsdl:output message="tns:SendMMS2HttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSend2">
      <wsdl:input message="tns:BatchSend2HttpPostIn" />
      <wsdl:output message="tns:BatchSend2HttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="IntlSend">
      <wsdl:input message="tns:IntlSendHttpPostIn" />
      <wsdl:output message="tns:IntlSendHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS2">
      <wsdl:input message="tns:BatchSendMMS2HttpPostIn" />
      <wsdl:output message="tns:BatchSendMMS2HttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="VoiceNotify">
      <wsdl:input message="tns:VoiceNotifyHttpPostIn" />
      <wsdl:output message="tns:VoiceNotifyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="NotSend">
      <wsdl:input message="tns:NotSendHttpPostIn" />
      <wsdl:output message="tns:NotSendHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="BatchContentSms">
      <wsdl:input message="tns:BatchContentSmsHttpPostIn" />
      <wsdl:output message="tns:BatchContentSmsHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="LinkSend">
      <wsdl:input message="tns:LinkSendHttpPostIn" />
      <wsdl:output message="tns:LinkSendHttpPostOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="LinkWSSoap" type="tns:LinkWSSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="HelloWorld">
      <soap:operation soapAction="http://tempuri.org/HelloWorld" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSend">
      <soap:operation soapAction="http://tempuri.org/BatchSend" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportFail">
      <soap:operation soapAction="http://tempuri.org/GetReportFail" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Reg">
      <soap:operation soapAction="http://tempuri.org/Reg" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UpdPwd">
      <soap:operation soapAction="http://tempuri.org/UpdPwd" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UpdReg">
      <soap:operation soapAction="http://tempuri.org/UpdReg" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SelSum">
      <soap:operation soapAction="http://tempuri.org/SelSum" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetBalance">
      <soap:operation soapAction="http://tempuri.org/GetBalance" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Send">
      <soap:operation soapAction="http://tempuri.org/Send" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Get">
      <soap:operation soapAction="http://tempuri.org/Get" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UnReg">
      <soap:operation soapAction="http://tempuri.org/UnReg" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ChargeUp">
      <soap:operation soapAction="http://tempuri.org/ChargeUp" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AgentChangeAccount">
      <soap:operation soapAction="http://tempuri.org/AgentChangeAccount" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BalanceMMS">
      <soap:operation soapAction="http://tempuri.org/BalanceMMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendMMS">
      <soap:operation soapAction="http://tempuri.org/SendMMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS">
      <soap:operation soapAction="http://tempuri.org/BatchSendMMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportFailMMS">
      <soap:operation soapAction="http://tempuri.org/GetReportFailMMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetMMS">
      <soap:operation soapAction="http://tempuri.org/GetMMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ChargeUpMMS">
      <soap:operation soapAction="http://tempuri.org/ChargeUpMMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AgentMakeAccount">
      <soap:operation soapAction="http://tempuri.org/AgentMakeAccount" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportSMS">
      <soap:operation soapAction="http://tempuri.org/GetReportSMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportMMS">
      <soap:operation soapAction="http://tempuri.org/GetReportMMS" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Send2">
      <soap:operation soapAction="http://tempuri.org/Send2" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendMMS2">
      <soap:operation soapAction="http://tempuri.org/SendMMS2" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSend2">
      <soap:operation soapAction="http://tempuri.org/BatchSend2" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IntlSend">
      <soap:operation soapAction="http://tempuri.org/IntlSend" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS2">
      <soap:operation soapAction="http://tempuri.org/BatchSendMMS2" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VoiceNotify">
      <soap:operation soapAction="http://tempuri.org/VoiceNotify" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NotSend">
      <soap:operation soapAction="http://tempuri.org/NotSend" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchContentSms">
      <soap:operation soapAction="http://tempuri.org/BatchContentSms" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="LinkSend">
      <soap:operation soapAction="http://tempuri.org/LinkSend" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="LinkWSSoap12" type="tns:LinkWSSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="HelloWorld">
      <soap12:operation soapAction="http://tempuri.org/HelloWorld" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSend">
      <soap12:operation soapAction="http://tempuri.org/BatchSend" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportFail">
      <soap12:operation soapAction="http://tempuri.org/GetReportFail" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Reg">
      <soap12:operation soapAction="http://tempuri.org/Reg" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UpdPwd">
      <soap12:operation soapAction="http://tempuri.org/UpdPwd" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UpdReg">
      <soap12:operation soapAction="http://tempuri.org/UpdReg" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SelSum">
      <soap12:operation soapAction="http://tempuri.org/SelSum" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetBalance">
      <soap12:operation soapAction="http://tempuri.org/GetBalance" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Send">
      <soap12:operation soapAction="http://tempuri.org/Send" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Get">
      <soap12:operation soapAction="http://tempuri.org/Get" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UnReg">
      <soap12:operation soapAction="http://tempuri.org/UnReg" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ChargeUp">
      <soap12:operation soapAction="http://tempuri.org/ChargeUp" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AgentChangeAccount">
      <soap12:operation soapAction="http://tempuri.org/AgentChangeAccount" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BalanceMMS">
      <soap12:operation soapAction="http://tempuri.org/BalanceMMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendMMS">
      <soap12:operation soapAction="http://tempuri.org/SendMMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS">
      <soap12:operation soapAction="http://tempuri.org/BatchSendMMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportFailMMS">
      <soap12:operation soapAction="http://tempuri.org/GetReportFailMMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetMMS">
      <soap12:operation soapAction="http://tempuri.org/GetMMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ChargeUpMMS">
      <soap12:operation soapAction="http://tempuri.org/ChargeUpMMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AgentMakeAccount">
      <soap12:operation soapAction="http://tempuri.org/AgentMakeAccount" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportSMS">
      <soap12:operation soapAction="http://tempuri.org/GetReportSMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportMMS">
      <soap12:operation soapAction="http://tempuri.org/GetReportMMS" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Send2">
      <soap12:operation soapAction="http://tempuri.org/Send2" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendMMS2">
      <soap12:operation soapAction="http://tempuri.org/SendMMS2" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSend2">
      <soap12:operation soapAction="http://tempuri.org/BatchSend2" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IntlSend">
      <soap12:operation soapAction="http://tempuri.org/IntlSend" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS2">
      <soap12:operation soapAction="http://tempuri.org/BatchSendMMS2" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VoiceNotify">
      <soap12:operation soapAction="http://tempuri.org/VoiceNotify" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NotSend">
      <soap12:operation soapAction="http://tempuri.org/NotSend" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchContentSms">
      <soap12:operation soapAction="http://tempuri.org/BatchContentSms" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="LinkSend">
      <soap12:operation soapAction="http://tempuri.org/LinkSend" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="LinkWSHttpGet" type="tns:LinkWSHttpGet">
    <http:binding verb="GET" />
    <wsdl:operation name="HelloWorld">
      <http:operation location="/HelloWorld" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSend">
      <http:operation location="/BatchSend" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportFail">
      <http:operation location="/GetReportFail" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Reg">
      <http:operation location="/Reg" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UpdPwd">
      <http:operation location="/UpdPwd" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UpdReg">
      <http:operation location="/UpdReg" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SelSum">
      <http:operation location="/SelSum" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetBalance">
      <http:operation location="/GetBalance" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Send">
      <http:operation location="/Send" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Get">
      <http:operation location="/Get" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UnReg">
      <http:operation location="/UnReg" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ChargeUp">
      <http:operation location="/ChargeUp" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AgentChangeAccount">
      <http:operation location="/AgentChangeAccount" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BalanceMMS">
      <http:operation location="/BalanceMMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendMMS">
      <http:operation location="/SendMMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS">
      <http:operation location="/BatchSendMMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportFailMMS">
      <http:operation location="/GetReportFailMMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetMMS">
      <http:operation location="/GetMMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ChargeUpMMS">
      <http:operation location="/ChargeUpMMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AgentMakeAccount">
      <http:operation location="/AgentMakeAccount" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportSMS">
      <http:operation location="/GetReportSMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportMMS">
      <http:operation location="/GetReportMMS" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Send2">
      <http:operation location="/Send2" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendMMS2">
      <http:operation location="/SendMMS2" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSend2">
      <http:operation location="/BatchSend2" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IntlSend">
      <http:operation location="/IntlSend" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS2">
      <http:operation location="/BatchSendMMS2" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VoiceNotify">
      <http:operation location="/VoiceNotify" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NotSend">
      <http:operation location="/NotSend" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchContentSms">
      <http:operation location="/BatchContentSms" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="LinkSend">
      <http:operation location="/LinkSend" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="LinkWSHttpPost" type="tns:LinkWSHttpPost">
    <http:binding verb="POST" />
    <wsdl:operation name="HelloWorld">
      <http:operation location="/HelloWorld" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSend">
      <http:operation location="/BatchSend" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportFail">
      <http:operation location="/GetReportFail" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Reg">
      <http:operation location="/Reg" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UpdPwd">
      <http:operation location="/UpdPwd" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UpdReg">
      <http:operation location="/UpdReg" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SelSum">
      <http:operation location="/SelSum" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetBalance">
      <http:operation location="/GetBalance" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Send">
      <http:operation location="/Send" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Get">
      <http:operation location="/Get" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UnReg">
      <http:operation location="/UnReg" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ChargeUp">
      <http:operation location="/ChargeUp" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AgentChangeAccount">
      <http:operation location="/AgentChangeAccount" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BalanceMMS">
      <http:operation location="/BalanceMMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendMMS">
      <http:operation location="/SendMMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS">
      <http:operation location="/BatchSendMMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportFailMMS">
      <http:operation location="/GetReportFailMMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetMMS">
      <http:operation location="/GetMMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ChargeUpMMS">
      <http:operation location="/ChargeUpMMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AgentMakeAccount">
      <http:operation location="/AgentMakeAccount" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportSMS">
      <http:operation location="/GetReportSMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReportMMS">
      <http:operation location="/GetReportMMS" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Send2">
      <http:operation location="/Send2" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendMMS2">
      <http:operation location="/SendMMS2" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSend2">
      <http:operation location="/BatchSend2" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IntlSend">
      <http:operation location="/IntlSend" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchSendMMS2">
      <http:operation location="/BatchSendMMS2" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="VoiceNotify">
      <http:operation location="/VoiceNotify" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="NotSend">
      <http:operation location="/NotSend" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="BatchContentSms">
      <http:operation location="/BatchContentSms" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="LinkSend">
      <http:operation location="/LinkSend" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="LinkWS">
    <wsdl:port name="LinkWSSoap" binding="tns:LinkWSSoap">
      <soap:address location="https://sdk2.028lk.com/sdk2/LinkWS.asmx" />
    </wsdl:port>
    <wsdl:port name="LinkWSSoap12" binding="tns:LinkWSSoap12">
      <soap12:address location="https://sdk2.028lk.com/sdk2/LinkWS.asmx" />
    </wsdl:port>
    <wsdl:port name="LinkWSHttpGet" binding="tns:LinkWSHttpGet">
      <http:address location="https://sdk2.028lk.com/sdk2/LinkWS.asmx" />
    </wsdl:port>
    <wsdl:port name="LinkWSHttpPost" binding="tns:LinkWSHttpPost">
      <http:address location="https://sdk2.028lk.com/sdk2/LinkWS.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>