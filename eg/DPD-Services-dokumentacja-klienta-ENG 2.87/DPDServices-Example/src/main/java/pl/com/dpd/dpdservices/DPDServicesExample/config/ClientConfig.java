package pl.com.dpd.dpdservices.DPDServicesExample.config;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.dpd.dpdservices.DPDPackageObjServices;
import pl.com.dpd.dpdservices.DPDServicesExample.common.TrustAllX509TrustManager;

import javax.net.ssl.TrustManager;

@Configuration
public class ClientConfig {

    @Value("${dpdservices.address}")
    private String address;


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

    //Workaround for self-signed ssl certificate
    private void httpsSetup(Client client){
        final TLSClientParameters tlsCP = new TLSClientParameters();

        tlsCP.setDisableCNCheck(true);
        TrustManager[] tsm = {new TrustAllX509TrustManager()};
        tlsCP.setTrustManagers(tsm);

        ((HTTPConduit) client.getConduit()).setTlsClientParameters(tlsCP);
    }
}
