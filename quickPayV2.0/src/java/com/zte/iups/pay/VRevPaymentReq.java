/**
 * VRevPaymentReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zte.iups.pay;

public class VRevPaymentReq  implements java.io.Serializable {
    private java.lang.String SNID;

    private java.lang.String reqparam;

    private java.lang.String digest;

    public VRevPaymentReq() {
    }

    public VRevPaymentReq(
           java.lang.String SNID,
           java.lang.String reqparam,
           java.lang.String digest) {
           this.SNID = SNID;
           this.reqparam = reqparam;
           this.digest = digest;
    }


    /**
     * Gets the SNID value for this VRevPaymentReq.
     * 
     * @return SNID
     */
    public java.lang.String getSNID() {
        return SNID;
    }


    /**
     * Sets the SNID value for this VRevPaymentReq.
     * 
     * @param SNID
     */
    public void setSNID(java.lang.String SNID) {
        this.SNID = SNID;
    }


    /**
     * Gets the reqparam value for this VRevPaymentReq.
     * 
     * @return reqparam
     */
    public java.lang.String getReqparam() {
        return reqparam;
    }


    /**
     * Sets the reqparam value for this VRevPaymentReq.
     * 
     * @param reqparam
     */
    public void setReqparam(java.lang.String reqparam) {
        this.reqparam = reqparam;
    }


    /**
     * Gets the digest value for this VRevPaymentReq.
     * 
     * @return digest
     */
    public java.lang.String getDigest() {
        return digest;
    }


    /**
     * Sets the digest value for this VRevPaymentReq.
     * 
     * @param digest
     */
    public void setDigest(java.lang.String digest) {
        this.digest = digest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VRevPaymentReq)) return false;
        VRevPaymentReq other = (VRevPaymentReq) obj;
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
            ((this.reqparam==null && other.getReqparam()==null) || 
             (this.reqparam!=null &&
              this.reqparam.equals(other.getReqparam()))) &&
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
        if (getReqparam() != null) {
            _hashCode += getReqparam().hashCode();
        }
        if (getDigest() != null) {
            _hashCode += getDigest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VRevPaymentReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SNID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SNID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reqparam");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reqparam"));
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
