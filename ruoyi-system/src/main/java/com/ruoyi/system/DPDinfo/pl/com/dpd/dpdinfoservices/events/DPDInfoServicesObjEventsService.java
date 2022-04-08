
package com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DPDInfoServicesObjEventsService", targetNamespace = "http://events.dpdinfoservices.dpd.com.pl/", wsdlLocation = "https://dpdinfoservices.dpd.com.pl/DPDInfoServicesObjEventsService/DPDInfoServicesObjEvents?wsdl")
public class DPDInfoServicesObjEventsService
    extends Service
{

    private final static URL DPDINFOSERVICESOBJEVENTSSERVICE_WSDL_LOCATION;
    private final static WebServiceException DPDINFOSERVICESOBJEVENTSSERVICE_EXCEPTION;
    private final static QName DPDINFOSERVICESOBJEVENTSSERVICE_QNAME = new QName("http://events.dpdinfoservices.dpd.com.pl/", "DPDInfoServicesObjEventsService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://dpdinfoservices.dpd.com.pl/DPDInfoServicesObjEventsService/DPDInfoServicesObjEvents?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DPDINFOSERVICESOBJEVENTSSERVICE_WSDL_LOCATION = url;
        DPDINFOSERVICESOBJEVENTSSERVICE_EXCEPTION = e;
    }

    public DPDInfoServicesObjEventsService() {
        super(__getWsdlLocation(), DPDINFOSERVICESOBJEVENTSSERVICE_QNAME);
    }

    public DPDInfoServicesObjEventsService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DPDINFOSERVICESOBJEVENTSSERVICE_QNAME, features);
    }

    public DPDInfoServicesObjEventsService(URL wsdlLocation) {
        super(wsdlLocation, DPDINFOSERVICESOBJEVENTSSERVICE_QNAME);
    }

    public DPDInfoServicesObjEventsService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DPDINFOSERVICESOBJEVENTSSERVICE_QNAME, features);
    }

    public DPDInfoServicesObjEventsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DPDInfoServicesObjEventsService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DPDInfoServicesObjEvents
     */
    @WebEndpoint(name = "DPDInfoServicesObjEventsPort")
    public DPDInfoServicesObjEvents getDPDInfoServicesObjEventsPort() {
        return super.getPort(new QName("http://events.dpdinfoservices.dpd.com.pl/", "DPDInfoServicesObjEventsPort"), DPDInfoServicesObjEvents.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DPDInfoServicesObjEvents
     */
    @WebEndpoint(name = "DPDInfoServicesObjEventsPort")
    public DPDInfoServicesObjEvents getDPDInfoServicesObjEventsPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://events.dpdinfoservices.dpd.com.pl/", "DPDInfoServicesObjEventsPort"), DPDInfoServicesObjEvents.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DPDINFOSERVICESOBJEVENTSSERVICE_EXCEPTION!= null) {
            throw DPDINFOSERVICESOBJEVENTSSERVICE_EXCEPTION;
        }
        return DPDINFOSERVICESOBJEVENTSSERVICE_WSDL_LOCATION;
    }

}
