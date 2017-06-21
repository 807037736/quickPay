/**
 * PAYSERVERSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zte.iups.pay;

public class PAYSERVERSOAPImpl implements com.zte.iups.pay.PAYSERVERSOAP{
    public com.zte.iups.pay.VPushOrderInfoRsp vPushOrderInfo(com.zte.iups.pay.VPushOrderInfoReq req) throws java.rmi.RemoteException {
    	System.out.println("diao yong cg");
    	return null;
    }

    public com.zte.iups.pay.VNotifyPayRsltRsp vNotifyPayRslt(com.zte.iups.pay.VNotifyPayRsltReq req) throws java.rmi.RemoteException {
        com.zte.iups.pay.VNotifyPayRsltRsp rsp = new com.zte.iups.pay.VNotifyPayRsltRsp();
        rsp.setSNID(req.getSNID());
        rsp.setResInfo("ok");
        rsp.setResparam("zexAST62Lw+UzWCLvQq2gU+KNqFlCD3zlxShwStBZ1HyJ0/HtbbOmDhZYdfV3ln4658jIdrx9ork7gOxVAUt+dMf3Pdxo5SFNK6K7NUaLbJKf6t6MZH/8C8an5kULI3KCu2TiGxi1xiLQCYqdFYB1Q==");
        rsp.setResult("000");
        rsp.setDigest("111");
        System.out.println("调用成功");
        return rsp;
    }

    public com.zte.iups.pay.VRevPaymentRsp vRevPayment(com.zte.iups.pay.VRevPaymentReq req) throws java.rmi.RemoteException {
    	System.out.println("diao yong cg");
    	return null;
    }

    public com.zte.iups.pay.VPaymentCancelRsp vPaymentCancel(com.zte.iups.pay.VPaymentCancelReq req) throws java.rmi.RemoteException {
    	System.out.println("diao yong cg");
    	return null;
    }

    public com.zte.iups.pay.VRevPaymentCancelRsp vRevPaymentCancel(com.zte.iups.pay.VRevPaymentCancelReq req) throws java.rmi.RemoteException {
    	System.out.println("diao yong cg");
    	return null;
    }

    public com.zte.iups.pay.VPaymentReturnRsp vPaymentReturn(com.zte.iups.pay.VPaymentReturnReq req) throws java.rmi.RemoteException {
    	System.out.println("diao yong cg");
    	return null;
    }

    public com.zte.iups.pay.VRevPaymentReturnRsp vRevPaymentReturn(com.zte.iups.pay.VRevPaymentReturnReq req) throws java.rmi.RemoteException {
        return null;
    }

    public com.zte.iups.pay.VPreAuthOperRsp vPreAuthOper(com.zte.iups.pay.VPreAuthOperReq req) throws java.rmi.RemoteException {
    	System.out.println("diao yong cg");
    	return null;
    }

    public com.zte.iups.pay.VConfirmRiskRsp vConfirmRisk(com.zte.iups.pay.VConfirmRiskReq req) throws java.rmi.RemoteException {
        System.out.println("diao yong cg");
    	return null;
    }

}
