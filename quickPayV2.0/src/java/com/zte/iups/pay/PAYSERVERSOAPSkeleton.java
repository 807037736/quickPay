/**
 * PAYSERVERSOAPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zte.iups.pay;

public class PAYSERVERSOAPSkeleton implements com.zte.iups.pay.PAYSERVERSOAP, org.apache.axis.wsdl.Skeleton {
    private com.zte.iups.pay.PAYSERVERSOAP impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPushOrderInfoReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPushOrderInfoReq"), com.zte.iups.pay.VPushOrderInfoReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("vPushOrderInfo", _params, new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPushOrderInfoRsp"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPushOrderInfoRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "vPushOrderInfo"));
        _oper.setSoapAction("http://iups.zte.com/pay/vPushOrderInfo");
        _myOperationsList.add(_oper);
        if (_myOperations.get("vPushOrderInfo") == null) {
            _myOperations.put("vPushOrderInfo", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("vPushOrderInfo")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vNotifyPayRsltReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vNotifyPayRsltReq"), com.zte.iups.pay.VNotifyPayRsltReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("vNotifyPayRslt", _params, new javax.xml.namespace.QName("http://iups.zte.com/pay", "vNotifyPayRsltRsp"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vNotifyPayRsltRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "vNotifyPayRslt"));
        _oper.setSoapAction("http://iups.zte.com/pay/vNotifyPayRslt");
        _myOperationsList.add(_oper);
        if (_myOperations.get("vNotifyPayRslt") == null) {
            _myOperations.put("vNotifyPayRslt", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("vNotifyPayRslt")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReq"), com.zte.iups.pay.VRevPaymentReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("vRevPayment", _params, new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentRsp"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "vRevPayment"));
        _oper.setSoapAction("http://iups.zte.com/pay/vRevPayment");
        
        _myOperationsList.add(_oper);
        if (_myOperations.get("vRevPayment") == null) {
            _myOperations.put("vRevPayment", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("vRevPayment")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPaymentCancelReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentCancelReq"), com.zte.iups.pay.VPaymentCancelReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("vPaymentCancel", _params, new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPaymentCancelRsp"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentCancelRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "vPaymentCancel"));
        _oper.setSoapAction("http://iups.zte.com/pay/vPaymentCancel");
        _myOperationsList.add(_oper);
        if (_myOperations.get("vPaymentCancel") == null) {
            _myOperations.put("vPaymentCancel", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("vPaymentCancel")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentCancelReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentCancelReq"), com.zte.iups.pay.VRevPaymentCancelReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("vRevPaymentCancel", _params, new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentCancelRsp"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentCancelRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "vRevPaymentCancel"));
        _oper.setSoapAction("http://iups.zte.com/pay/vRevPaymentCancel");
        _myOperationsList.add(_oper);
        if (_myOperations.get("vRevPaymentCancel") == null) {
            _myOperations.put("vRevPaymentCancel", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("vRevPaymentCancel")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPaymentReturnReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentReturnReq"), com.zte.iups.pay.VPaymentReturnReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("vPaymentReturn", _params, new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPaymentReturnRsp"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPaymentReturnRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "vPaymentReturn"));
        _oper.setSoapAction("http://iups.zte.com/pay/vPaymentReturn");
        _myOperationsList.add(_oper);
        if (_myOperations.get("vPaymentReturn") == null) {
            _myOperations.put("vPaymentReturn", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("vPaymentReturn")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentReturnReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReturnReq"), com.zte.iups.pay.VRevPaymentReturnReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("vRevPaymentReturn", _params, new javax.xml.namespace.QName("http://iups.zte.com/pay", "vRevPaymentReturnRsp"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vRevPaymentReturnRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "vRevPaymentReturn"));
        _oper.setSoapAction("http://iups.zte.com/pay/vRevPaymentReturn");
        _myOperationsList.add(_oper);
        if (_myOperations.get("vRevPaymentReturn") == null) {
            _myOperations.put("vRevPaymentReturn", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("vRevPaymentReturn")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPreAuthOperReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPreAuthOperReq"), com.zte.iups.pay.VPreAuthOperReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("vPreAuthOper", _params, new javax.xml.namespace.QName("http://iups.zte.com/pay", "vPreAuthOperRsp"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vPreAuthOperRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "vPreAuthOper"));
        _oper.setSoapAction("http://iups.zte.com/pay/vPreAuthOper");
        _myOperationsList.add(_oper);
        if (_myOperations.get("vPreAuthOper") == null) {
            _myOperations.put("vPreAuthOper", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("vPreAuthOper")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://iups.zte.com/pay", "vConfirmRiskReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vConfirmRiskReq"), com.zte.iups.pay.VConfirmRiskReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("vConfirmRisk", _params, new javax.xml.namespace.QName("http://iups.zte.com/pay", "vConfirmRiskRsp"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://iups.zte.com/pay", ">vConfirmRiskRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "vConfirmRisk"));
        _oper.setSoapAction("http://iups.zte.com/pay/vConfirmRisk");
        _myOperationsList.add(_oper);
        if (_myOperations.get("vConfirmRisk") == null) {
            _myOperations.put("vConfirmRisk", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("vConfirmRisk")).add(_oper);
    }

    public PAYSERVERSOAPSkeleton() {
        this.impl = new com.zte.iups.pay.PAYSERVERSOAPImpl();
    }

    public PAYSERVERSOAPSkeleton(com.zte.iups.pay.PAYSERVERSOAP impl) {
        this.impl = impl;
    }
    public com.zte.iups.pay.VPushOrderInfoRsp vPushOrderInfo(com.zte.iups.pay.VPushOrderInfoReq req) throws java.rmi.RemoteException
    {
        com.zte.iups.pay.VPushOrderInfoRsp ret = impl.vPushOrderInfo(req);
        return ret;
    }

    public com.zte.iups.pay.VNotifyPayRsltRsp vNotifyPayRslt(com.zte.iups.pay.VNotifyPayRsltReq req) throws java.rmi.RemoteException
    {
        com.zte.iups.pay.VNotifyPayRsltRsp ret = impl.vNotifyPayRslt(req);
        return ret;
    }

    public com.zte.iups.pay.VRevPaymentRsp vRevPayment(com.zte.iups.pay.VRevPaymentReq req) throws java.rmi.RemoteException
    {
        com.zte.iups.pay.VRevPaymentRsp ret = impl.vRevPayment(req);
        return ret;
    }

    public com.zte.iups.pay.VPaymentCancelRsp vPaymentCancel(com.zte.iups.pay.VPaymentCancelReq req) throws java.rmi.RemoteException
    {
        com.zte.iups.pay.VPaymentCancelRsp ret = impl.vPaymentCancel(req);
        return ret;
    }

    public com.zte.iups.pay.VRevPaymentCancelRsp vRevPaymentCancel(com.zte.iups.pay.VRevPaymentCancelReq req) throws java.rmi.RemoteException
    {
        com.zte.iups.pay.VRevPaymentCancelRsp ret = impl.vRevPaymentCancel(req);
        return ret;
    }

    public com.zte.iups.pay.VPaymentReturnRsp vPaymentReturn(com.zte.iups.pay.VPaymentReturnReq req) throws java.rmi.RemoteException
    {
        com.zte.iups.pay.VPaymentReturnRsp ret = impl.vPaymentReturn(req);
        return ret;
    }

    public com.zte.iups.pay.VRevPaymentReturnRsp vRevPaymentReturn(com.zte.iups.pay.VRevPaymentReturnReq req) throws java.rmi.RemoteException
    {
        com.zte.iups.pay.VRevPaymentReturnRsp ret = impl.vRevPaymentReturn(req);
        return ret;
    }

    public com.zte.iups.pay.VPreAuthOperRsp vPreAuthOper(com.zte.iups.pay.VPreAuthOperReq req) throws java.rmi.RemoteException
    {
        com.zte.iups.pay.VPreAuthOperRsp ret = impl.vPreAuthOper(req);
        return ret;
    }

    public com.zte.iups.pay.VConfirmRiskRsp vConfirmRisk(com.zte.iups.pay.VConfirmRiskReq req) throws java.rmi.RemoteException
    {
        com.zte.iups.pay.VConfirmRiskRsp ret = impl.vConfirmRisk(req);
        return ret;
    }

}
