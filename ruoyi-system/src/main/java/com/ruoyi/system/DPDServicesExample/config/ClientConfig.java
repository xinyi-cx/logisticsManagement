package com.ruoyi.system.DPDServicesExample.config;

import com.ruoyi.system.DPDServicesExample.common.TrustAllX509TrustManager;
import com.ruoyi.system.DPDinfo.pl.com.dpd.dpdinfoservices.events.DPDInfoServicesObjEvents;
import com.ruoyi.system.dpdservices.DPDPackageObjServices;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.TrustManager;

@Configuration
public class ClientConfig {

    @Value("${dpdservices.address}")
    private String address;

    @Value("${dpdservices.addressInfo}")
    private String addressInfo;

    @Bean
    public DPDPackageObjServices dpdPackageXmlServices() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(DPDPackageObjServices.class);
        jaxWsProxyFactoryBean.setAddress(address);

        DPDPackageObjServices dpdPackageObjServices = (DPDPackageObjServices) jaxWsProxyFactoryBean.create();

        if(address.contains("https")){
            httpsSetup(ClientProxy.getClient(dpdPackageObjServices));
        }

        return dpdPackageObjServices;
    }

    @Bean
    public DPDInfoServicesObjEvents dpdInfoServicesObjEvents() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(DPDInfoServicesObjEvents.class);
        jaxWsProxyFactoryBean.setAddress(addressInfo);

        DPDInfoServicesObjEvents dpdInfoServicesObjEvents = (DPDInfoServicesObjEvents) jaxWsProxyFactoryBean.create();

        if(address.contains("https")){
            httpsSetup(ClientProxy.getClient(dpdInfoServicesObjEvents));
        }
        return dpdInfoServicesObjEvents;
    }

    //Workaround for self-signed ssl certificate
    private void httpsSetup(Client client){
        if (client != null) {
            HTTPConduit conduit = (HTTPConduit) client.getConduit();
            HTTPClientPolicy policy = new HTTPClientPolicy();
            policy.setConnectionTimeout(6000000);
            policy.setReceiveTimeout(6000000);
            conduit.setClient(policy);
        }
        final TLSClientParameters tlsCP = new TLSClientParameters();

        tlsCP.setDisableCNCheck(true);
        TrustManager[] tsm = {new TrustAllX509TrustManager()};
        tlsCP.setTrustManagers(tsm);

        ((HTTPConduit) client.getConduit()).setTlsClientParameters(tlsCP);
    }
}
