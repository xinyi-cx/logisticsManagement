package com.ruoyi.system.dpdservices;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2017-12-21T02:56:29.318+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebServiceClient(name = "DPDPackageObjServicesService", 
                  wsdlLocation = "file:/mnt/veracrypt1/projects/DPDServices-Example/src/main/resources/wsdl/DPDPackageXmlServices.wsdl",
                  targetNamespace = "http://dpdservices.dpd.com.pl/") 
public class DPDPackageObjServicesService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://dpdservices.dpd.com.pl/", "DPDPackageObjServicesService");
    public final static QName DPDPackageObjServicesPort = new QName("http://dpdservices.dpd.com.pl/", "DPDPackageObjServicesPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/mnt/veracrypt1/projects/DPDServices-Example/src/main/resources/wsdl/DPDPackageXmlServices.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(DPDPackageObjServicesService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/mnt/veracrypt1/projects/DPDServices-Example/src/main/resources/wsdl/DPDPackageXmlServices.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public DPDPackageObjServicesService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DPDPackageObjServicesService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DPDPackageObjServicesService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public DPDPackageObjServicesService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public DPDPackageObjServicesService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public DPDPackageObjServicesService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns DPDPackageObjServices
     */
    @WebEndpoint(name = "DPDPackageObjServicesPort")
    public DPDPackageObjServices getDPDPackageObjServicesPort() {
        return super.getPort(DPDPackageObjServicesPort, DPDPackageObjServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DPDPackageObjServices
     */
    @WebEndpoint(name = "DPDPackageObjServicesPort")
    public DPDPackageObjServices getDPDPackageObjServicesPort(WebServiceFeature... features) {
        return super.getPort(DPDPackageObjServicesPort, DPDPackageObjServices.class, features);
    }

}