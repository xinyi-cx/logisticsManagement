package pl.com.dpd.dpdservices.DPDServicesExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.com.dpd.dpdservices.DPDServicesExample.common.TrustAllX509TrustManager;

import javax.net.ssl.X509TrustManager;

@SpringBootApplication
public class DpdServicesExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DpdServicesExampleApplication.class, args);
	}
}
