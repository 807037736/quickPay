/**
 * PAYSERVERSOAP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zte.iups.pay;

public interface PAYSERVERSOAP extends java.rmi.Remote {
    public com.zte.iups.pay.VPushOrderInfoRsp vPushOrderInfo(com.zte.iups.pay.VPushOrderInfoReq req) throws java.rmi.RemoteException;
    public com.zte.iups.pay.VNotifyPayRsltRsp vNotifyPayRslt(com.zte.iups.pay.VNotifyPayRsltReq req) throws java.rmi.RemoteException;
    public com.zte.iups.pay.VRevPaymentRsp vRevPayment(com.zte.iups.pay.VRevPaymentReq req) throws java.rmi.RemoteException;
    public com.zte.iups.pay.VPaymentCancelRsp vPaymentCancel(com.zte.iups.pay.VPaymentCancelReq req) throws java.rmi.RemoteException;
    public com.zte.iups.pay.VRevPaymentCancelRsp vRevPaymentCancel(com.zte.iups.pay.VRevPaymentCancelReq req) throws java.rmi.RemoteException;
    public com.zte.iups.pay.VPaymentReturnRsp vPaymentReturn(com.zte.iups.pay.VPaymentReturnReq req) throws java.rmi.RemoteException;
    public com.zte.iups.pay.VRevPaymentReturnRsp vRevPaymentReturn(com.zte.iups.pay.VRevPaymentReturnReq req) throws java.rmi.RemoteException;
    public com.zte.iups.pay.VPreAuthOperRsp vPreAuthOper(com.zte.iups.pay.VPreAuthOperReq req) throws java.rmi.RemoteException;
    public com.zte.iups.pay.VConfirmRiskRsp vConfirmRisk(com.zte.iups.pay.VConfirmRiskReq req) throws java.rmi.RemoteException;
}
