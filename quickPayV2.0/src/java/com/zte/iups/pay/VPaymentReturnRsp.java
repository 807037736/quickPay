/**
 * VPaymentReturnRsp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zte.iups.pay;

public class VPaymentReturnRsp  implements java.io.Serializable {
    private java.lang.String SNID;

    private java.lang.String result;

    private java.lang.String resInfo;

    private java.lang.String resparam;

    private java.lang.String digest;

    public VPaymentReturnRsp() {
    }

    public VPaymentReturnRsp(
           java.lang.String SNID,
           java.lang.String result,
           java.lang.String resInfo,
           java.lang.String resparam,
           java.lang.String digest) {
           this.SNID = SNID;
           this.result = result;
           this.resInfo = resInfo;
           this.resparam = resparam;
           this.digest = digest;
    }


    /**
     * Gets the SNID value for this VPaymentReturnRsp.
     * 
     * @return SNID
     */
    public java.lang.String getSNID() {
        return SNID;
    }


    /**
     * Sets the SNID value for this VPaymentReturnRsp.
     * 
     * @param SNID
     */
    public void setSNID(java.lang.String SNID) {
        this.SNID = SNID;
    }


    /**
     * Gets the result value for this VPaymentReturnRsp.
     * 
     * @return result
     */
    public java.lang.String getResult() {
        return result;
    }


    /**
     * Sets the result value for this VPaymentReturnRsp.
     * 
     * @param result
     */
    public void setResult(java.lang.String result) {
        this.result = result;
    }


    /**
     * Gets the resInfo value for this VPaymentReturnRsp.
     * 
     * @return resInfo
     */
    public java.lang.String getResInfo() {
        return resInfo;
    }


    /**
     * Sets the resInfo value for this VPaymentReturnRsp.
     * 
     * @param resInfo
     */
    public void setResInfo(java.lang.String resInfo) {
        this.resInfo = resInfo;
    }


    /**
     * Gets the resparam value for this VPaymentReturnRsp.
     * 
     * @return resparam
     */
    public java.lang.String getResparam() {
        return resparam;
    }


    /**
     * Sets the resparam value for this VPaymentReturnRsp.
     * 
     * @param resparam
     */
    public void setResparam(java.lang.String resparam) {
        this.resparam = resparam;
    }


    /**
     * Gets the digest value for this VPaymentReturnRsp.
     * 
     * @return digest
     */
    public java.lang.String getDigest() {
        return digest;
    }


    /**
     * Sets the digest value for this VPaymentReturnRsp.
     * 
     * @param digest
     */
    public void setDigest(java.lang.String digest) {
        this.digest = digest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VPaymentReturnRsp)) return false;
        VPaymentReturnRsp other = (VPaymentReturnRsp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SNID==null && other.getSNID()==null) || 
             (this.SNID!=null &&
              this.SNID.equals(other.getSNID()))) &&
            ((this.result==null && other.getResult()==null) || 
             (this.result!=null &&
              this.result.equals(other.getResult()))) &&
            ((this.resInfo==null && other.getResInfo()==null) || 
             (this.resInfo!=null &&
              this.resInfo.equals(other.getResInfo()))) &&
            ((this.resparam==null && other.getResparam()==null) || 
             (this.resparam!=null &&
              this.resparam.equals(other.getResparam()))) &&
            ((this.digest==null && other.getDigest()==null) || 
             (this.digest!=null &&
              this.digest.equals(other.getDigest())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getSNID() != null) {
            _hashCode += getSNID().hashCode();
        }
        if (getResult() != null) {
            _hashCode += getResult().hashCode();
        }
        if (getResInfo() != null) {
            _hashCode += getResInfo().hashCode();
        }
        if (getResparam() != null) {
            _hashCode += getResparam().hashCode();
        }
        if (getDigest() != null) {
            _hashCode += getDigest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VPaymentReturnRsp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentReturnRsp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SNID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SNID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("", "result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resparam");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resparam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "digest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
