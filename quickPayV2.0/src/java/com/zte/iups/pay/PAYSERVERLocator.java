/**
 * PAYSERVERLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zte.iups.pay;

public class PAYSERVERLocator extends org.apache.axis.client.Service implements com.zte.iups.pay.PAYSERVER {

    public PAYSERVERLocator() {
    }


    public PAYSERVERLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PAYSERVERLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PAYSERVERSOAP
    private java.lang.String PAYSERVERSOAP_address = "http://localhost:8088/payserver";

    public java.lang.String getPAYSERVERSOAPAddress() {
        return PAYSERVERSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PAYSERVERSOAPWSDDServiceName = "PAYSERVERSOAP";

    public java.lang.String getPAYSERVERSOAPWSDDServiceName() {
        return PAYSERVERSOAPWSDDServiceName;
    }

    public void setPAYSERVERSOAPWSDDServiceName(java.lang.String name) {
        PAYSERVERSOAPWSDDServiceName = name;
    }

    public com.zte.iups.pay.PAYSERVERSOAP getPAYSERVERSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PAYSERVERSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPAYSERVERSOAP(endpoint);
    }

    public com.zte.iups.pay.PAYSERVERSOAP getPAYSERVERSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zte.iups.pay.PAYSERVERSOAPStub _stub = new com.zte.iups.pay.PAYSERVERSOAPStub(portAddress, this);
            _stub.setPortName(getPAYSERVERSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPAYSERVERSOAPEndpointAddress(java.lang.String address) {
        PAYSERVERSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.zte.iups.pay.PAYSERVERSOAP.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zte.iups.pay.PAYSERVERSOAPStub _stub = new com.zte.iups.pay.PAYSERVERSOAPStub(new java.net.URL(PAYSERVERSOAP_address), this);
                _stub.setPortName(getPAYSERVERSOAPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PAYSERVERSOAP".equals(inputPortName)) {
            return getPAYSERVERSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://iups.zte.com/pay", "PAYSERVER");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://iups.zte.com/pay", "PAYSERVERSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PAYSERVERSOAP".equals(portName)) {
            setPAYSERVERSOAPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
