
package com.ruoyi.system.dpdservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.com.dpd.dpdservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DPDServiceException_QNAME = new QName("http://dpdservices.dpd.com.pl/", "DPDServiceException");
    private final static QName _DeniedAccessWSException_QNAME = new QName("http://dpdservices.dpd.com.pl/", "DeniedAccessWSException");
    private final static QName _Exception_QNAME = new QName("http://dpdservices.dpd.com.pl/", "Exception");
    private final static QName _SchemaValidationException_QNAME = new QName("http://dpdservices.dpd.com.pl/", "SchemaValidationException");
    private final static QName _AppendParcelsToPackageV1_QNAME = new QName("http://dpdservices.dpd.com.pl/", "appendParcelsToPackageV1");
    private final static QName _AppendParcelsToPackageV1Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "appendParcelsToPackageV1Response");
    private final static QName _FindPostalCodeV1_QNAME = new QName("http://dpdservices.dpd.com.pl/", "findPostalCodeV1");
    private final static QName _FindPostalCodeV1Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "findPostalCodeV1Response");
    private final static QName _GeneratePackagesNumbersV1_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generatePackagesNumbersV1");
    private final static QName _GeneratePackagesNumbersV1Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generatePackagesNumbersV1Response");
    private final static QName _GeneratePackagesNumbersV2_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generatePackagesNumbersV2");
    private final static QName _GeneratePackagesNumbersV2Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generatePackagesNumbersV2Response");
    private final static QName _GeneratePackagesNumbersV3_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generatePackagesNumbersV3");
    private final static QName _GeneratePackagesNumbersV3Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generatePackagesNumbersV3Response");
    private final static QName _GeneratePackagesNumbersV4_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generatePackagesNumbersV4");
    private final static QName _GeneratePackagesNumbersV4Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generatePackagesNumbersV4Response");
    private final static QName _GenerateProtocolV1_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateProtocolV1");
    private final static QName _GenerateProtocolV1Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateProtocolV1Response");
    private final static QName _GenerateProtocolV2_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateProtocolV2");
    private final static QName _GenerateProtocolV2Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateProtocolV2Response");
    private final static QName _GenerateProtocolsWithDestinationsV1_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateProtocolsWithDestinationsV1");
    private final static QName _GenerateProtocolsWithDestinationsV1Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateProtocolsWithDestinationsV1Response");
    private final static QName _GenerateProtocolsWithDestinationsV2_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateProtocolsWithDestinationsV2");
    private final static QName _GenerateProtocolsWithDestinationsV2Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateProtocolsWithDestinationsV2Response");
    private final static QName _GenerateSpedLabelsV1_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateSpedLabelsV1");
    private final static QName _GenerateSpedLabelsV1Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateSpedLabelsV1Response");
    private final static QName _GenerateSpedLabelsV2_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateSpedLabelsV2");
    private final static QName _GenerateSpedLabelsV2Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateSpedLabelsV2Response");
    private final static QName _GenerateSpedLabelsV3_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateSpedLabelsV3");
    private final static QName _GenerateSpedLabelsV3Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateSpedLabelsV3Response");
    private final static QName _GenerateSpedLabelsV4_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateSpedLabelsV4");
    private final static QName _GenerateSpedLabelsV4Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "generateSpedLabelsV4Response");
    private final static QName _GetCourierOrderAvailabilityV1_QNAME = new QName("http://dpdservices.dpd.com.pl/", "getCourierOrderAvailabilityV1");
    private final static QName _GetCourierOrderAvailabilityV1Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "getCourierOrderAvailabilityV1Response");
    private final static QName _ImportDeliveryBusinessEventV1_QNAME = new QName("http://dpdservices.dpd.com.pl/", "importDeliveryBusinessEventV1");
    private final static QName _ImportDeliveryBusinessEventV1Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "importDeliveryBusinessEventV1Response");
    private final static QName _PackagesPickupCallV1_QNAME = new QName("http://dpdservices.dpd.com.pl/", "packagesPickupCallV1");
    private final static QName _PackagesPickupCallV1Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "packagesPickupCallV1Response");
    private final static QName _PackagesPickupCallV2_QNAME = new QName("http://dpdservices.dpd.com.pl/", "packagesPickupCallV2");
    private final static QName _PackagesPickupCallV2Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "packagesPickupCallV2Response");
    private final static QName _PackagesPickupCallV3_QNAME = new QName("http://dpdservices.dpd.com.pl/", "packagesPickupCallV3");
    private final static QName _PackagesPickupCallV3Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "packagesPickupCallV3Response");
    private final static QName _PackagesPickupCallV4_QNAME = new QName("http://dpdservices.dpd.com.pl/", "packagesPickupCallV4");
    private final static QName _PackagesPickupCallV4Response_QNAME = new QName("http://dpdservices.dpd.com.pl/", "packagesPickupCallV4Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.com.dpd.dpdservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SessionDGRV2 }
     * 
     */
    public SessionDGRV2 createSessionDGRV2() {
        return new SessionDGRV2();
    }

    /**
     * Create an instance of {@link DocumentGenerationResponseV2 }
     * 
     */
    public DocumentGenerationResponseV2 createDocumentGenerationResponseV2() {
        return new DocumentGenerationResponseV2();
    }

    /**
     * Create an instance of {@link DeliveryDestination }
     * 
     */
    public DeliveryDestination createDeliveryDestination() {
        return new DeliveryDestination();
    }

    /**
     * Create an instance of {@link PackageDSPV2 }
     * 
     */
    public PackageDSPV2 createPackageDSPV2() {
        return new PackageDSPV2();
    }

    /**
     * Create an instance of {@link SessionDSPV2 }
     * 
     */
    public SessionDSPV2 createSessionDSPV2() {
        return new SessionDSPV2();
    }

    /**
     * Create an instance of {@link DpdServicesParamsV2 }
     * 
     */
    public DpdServicesParamsV2 createDpdServicesParamsV2() {
        return new DpdServicesParamsV2();
    }

    /**
     * Create an instance of {@link ParcelPGRV2 }
     * 
     */
    public ParcelPGRV2 createParcelPGRV2() {
        return new ParcelPGRV2();
    }

    /**
     * Create an instance of {@link PackagePGRV2 }
     * 
     */
    public PackagePGRV2 createPackagePGRV2() {
        return new PackagePGRV2();
    }

    /**
     * Create an instance of {@link SessionPGRV2 }
     * 
     */
    public SessionPGRV2 createSessionPGRV2() {
        return new SessionPGRV2();
    }

    /**
     * Create an instance of {@link DPDServiceException }
     * 
     */
    public DPDServiceException createDPDServiceException() {
        return new DPDServiceException();
    }

    /**
     * Create an instance of {@link DeniedAccessWSException }
     * 
     */
    public DeniedAccessWSException createDeniedAccessWSException() {
        return new DeniedAccessWSException();
    }

    /**
     * Create an instance of {@link Exception }
     *
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link SchemaValidationException }
     *
     */
    public SchemaValidationException createSchemaValidationException() {
        return new SchemaValidationException();
    }

    /**
     * Create an instance of {@link AppendParcelsToPackageV1 }
     *
     */
    public AppendParcelsToPackageV1 createAppendParcelsToPackageV1() {
        return new AppendParcelsToPackageV1();
    }

    /**
     * Create an instance of {@link AppendParcelsToPackageV1Response }
     *
     */
    public AppendParcelsToPackageV1Response createAppendParcelsToPackageV1Response() {
        return new AppendParcelsToPackageV1Response();
    }

    /**
     * Create an instance of {@link FindPostalCodeV1 }
     *
     */
    public FindPostalCodeV1 createFindPostalCodeV1() {
        return new FindPostalCodeV1();
    }

    /**
     * Create an instance of {@link FindPostalCodeV1Response }
     *
     */
    public FindPostalCodeV1Response createFindPostalCodeV1Response() {
        return new FindPostalCodeV1Response();
    }

    /**
     * Create an instance of {@link GeneratePackagesNumbersV1 }
     *
     */
    public GeneratePackagesNumbersV1 createGeneratePackagesNumbersV1() {
        return new GeneratePackagesNumbersV1();
    }

    /**
     * Create an instance of {@link GeneratePackagesNumbersV1Response }
     *
     */
    public GeneratePackagesNumbersV1Response createGeneratePackagesNumbersV1Response() {
        return new GeneratePackagesNumbersV1Response();
    }

    /**
     * Create an instance of {@link GeneratePackagesNumbersV2 }
     *
     */
    public GeneratePackagesNumbersV2 createGeneratePackagesNumbersV2() {
        return new GeneratePackagesNumbersV2();
    }

    /**
     * Create an instance of {@link GeneratePackagesNumbersV2Response }
     *
     */
    public GeneratePackagesNumbersV2Response createGeneratePackagesNumbersV2Response() {
        return new GeneratePackagesNumbersV2Response();
    }

    /**
     * Create an instance of {@link GeneratePackagesNumbersV3 }
     *
     */
    public GeneratePackagesNumbersV3 createGeneratePackagesNumbersV3() {
        return new GeneratePackagesNumbersV3();
    }

    /**
     * Create an instance of {@link GeneratePackagesNumbersV3Response }
     *
     */
    public GeneratePackagesNumbersV3Response createGeneratePackagesNumbersV3Response() {
        return new GeneratePackagesNumbersV3Response();
    }

    /**
     * Create an instance of {@link GeneratePackagesNumbersV4 }
     *
     */
    public GeneratePackagesNumbersV4 createGeneratePackagesNumbersV4() {
        return new GeneratePackagesNumbersV4();
    }

    /**
     * Create an instance of {@link GeneratePackagesNumbersV4Response }
     *
     */
    public GeneratePackagesNumbersV4Response createGeneratePackagesNumbersV4Response() {
        return new GeneratePackagesNumbersV4Response();
    }

    /**
     * Create an instance of {@link GenerateProtocolV1 }
     *
     */
    public GenerateProtocolV1 createGenerateProtocolV1() {
        return new GenerateProtocolV1();
    }

    /**
     * Create an instance of {@link GenerateProtocolV1Response }
     *
     */
    public GenerateProtocolV1Response createGenerateProtocolV1Response() {
        return new GenerateProtocolV1Response();
    }

    /**
     * Create an instance of {@link GenerateProtocolV2 }
     *
     */
    public GenerateProtocolV2 createGenerateProtocolV2() {
        return new GenerateProtocolV2();
    }

    /**
     * Create an instance of {@link GenerateProtocolV2Response }
     *
     */
    public GenerateProtocolV2Response createGenerateProtocolV2Response() {
        return new GenerateProtocolV2Response();
    }

    /**
     * Create an instance of {@link GenerateProtocolsWithDestinationsV1 }
     *
     */
    public GenerateProtocolsWithDestinationsV1 createGenerateProtocolsWithDestinationsV1() {
        return new GenerateProtocolsWithDestinationsV1();
    }

    /**
     * Create an instance of {@link GenerateProtocolsWithDestinationsV1Response }
     *
     */
    public GenerateProtocolsWithDestinationsV1Response createGenerateProtocolsWithDestinationsV1Response() {
        return new GenerateProtocolsWithDestinationsV1Response();
    }

    /**
     * Create an instance of {@link GenerateProtocolsWithDestinationsV2 }
     *
     */
    public GenerateProtocolsWithDestinationsV2 createGenerateProtocolsWithDestinationsV2() {
        return new GenerateProtocolsWithDestinationsV2();
    }

    /**
     * Create an instance of {@link GenerateProtocolsWithDestinationsV2Response }
     *
     */
    public GenerateProtocolsWithDestinationsV2Response createGenerateProtocolsWithDestinationsV2Response() {
        return new GenerateProtocolsWithDestinationsV2Response();
    }

    /**
     * Create an instance of {@link GenerateSpedLabelsV1 }
     *
     */
    public GenerateSpedLabelsV1 createGenerateSpedLabelsV1() {
        return new GenerateSpedLabelsV1();
    }

    /**
     * Create an instance of {@link GenerateSpedLabelsV1Response }
     *
     */
    public GenerateSpedLabelsV1Response createGenerateSpedLabelsV1Response() {
        return new GenerateSpedLabelsV1Response();
    }

    /**
     * Create an instance of {@link GenerateSpedLabelsV2 }
     *
     */
    public GenerateSpedLabelsV2 createGenerateSpedLabelsV2() {
        return new GenerateSpedLabelsV2();
    }

    /**
     * Create an instance of {@link GenerateSpedLabelsV2Response }
     *
     */
    public GenerateSpedLabelsV2Response createGenerateSpedLabelsV2Response() {
        return new GenerateSpedLabelsV2Response();
    }

    /**
     * Create an instance of {@link GenerateSpedLabelsV3 }
     *
     */
    public GenerateSpedLabelsV3 createGenerateSpedLabelsV3() {
        return new GenerateSpedLabelsV3();
    }

    /**
     * Create an instance of {@link GenerateSpedLabelsV3Response }
     *
     */
    public GenerateSpedLabelsV3Response createGenerateSpedLabelsV3Response() {
        return new GenerateSpedLabelsV3Response();
    }

    /**
     * Create an instance of {@link GenerateSpedLabelsV4 }
     *
     */
    public GenerateSpedLabelsV4 createGenerateSpedLabelsV4() {
        return new GenerateSpedLabelsV4();
    }

    /**
     * Create an instance of {@link GenerateSpedLabelsV4Response }
     *
     */
    public GenerateSpedLabelsV4Response createGenerateSpedLabelsV4Response() {
        return new GenerateSpedLabelsV4Response();
    }

    /**
     * Create an instance of {@link GetCourierOrderAvailabilityV1 }
     *
     */
    public GetCourierOrderAvailabilityV1 createGetCourierOrderAvailabilityV1() {
        return new GetCourierOrderAvailabilityV1();
    }

    /**
     * Create an instance of {@link GetCourierOrderAvailabilityV1Response }
     *
     */
    public GetCourierOrderAvailabilityV1Response createGetCourierOrderAvailabilityV1Response() {
        return new GetCourierOrderAvailabilityV1Response();
    }

    /**
     * Create an instance of {@link ImportDeliveryBusinessEventV1 }
     *
     */
    public ImportDeliveryBusinessEventV1 createImportDeliveryBusinessEventV1() {
        return new ImportDeliveryBusinessEventV1();
    }

    /**
     * Create an instance of {@link ImportDeliveryBusinessEventV1Response }
     *
     */
    public ImportDeliveryBusinessEventV1Response createImportDeliveryBusinessEventV1Response() {
        return new ImportDeliveryBusinessEventV1Response();
    }

    /**
     * Create an instance of {@link PackagesPickupCallV1 }
     *
     */
    public PackagesPickupCallV1 createPackagesPickupCallV1() {
        return new PackagesPickupCallV1();
    }

    /**
     * Create an instance of {@link PackagesPickupCallV1Response }
     *
     */
    public PackagesPickupCallV1Response createPackagesPickupCallV1Response() {
        return new PackagesPickupCallV1Response();
    }

    /**
     * Create an instance of {@link PackagesPickupCallV2 }
     *
     */
    public PackagesPickupCallV2 createPackagesPickupCallV2() {
        return new PackagesPickupCallV2();
    }

    /**
     * Create an instance of {@link PackagesPickupCallV2Response }
     *
     */
    public PackagesPickupCallV2Response createPackagesPickupCallV2Response() {
        return new PackagesPickupCallV2Response();
    }

    /**
     * Create an instance of {@link PackagesPickupCallV3 }
     *
     */
    public PackagesPickupCallV3 createPackagesPickupCallV3() {
        return new PackagesPickupCallV3();
    }

    /**
     * Create an instance of {@link PackagesPickupCallV3Response }
     *
     */
    public PackagesPickupCallV3Response createPackagesPickupCallV3Response() {
        return new PackagesPickupCallV3Response();
    }

    /**
     * Create an instance of {@link PackagesPickupCallV4 }
     *
     */
    public PackagesPickupCallV4 createPackagesPickupCallV4() {
        return new PackagesPickupCallV4();
    }

    /**
     * Create an instance of {@link PackagesPickupCallV4Response }
     *
     */
    public PackagesPickupCallV4Response createPackagesPickupCallV4Response() {
        return new PackagesPickupCallV4Response();
    }

    /**
     * Create an instance of {@link OpenUMLFeV1 }
     *
     */
    public OpenUMLFeV1 createOpenUMLFeV1() {
        return new OpenUMLFeV1();
    }

    /**
     * Create an instance of {@link PackageOpenUMLFeV1 }
     *
     */
    public PackageOpenUMLFeV1 createPackageOpenUMLFeV1() {
        return new PackageOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ParcelOpenUMLFeV1 }
     *
     */
    public ParcelOpenUMLFeV1 createParcelOpenUMLFeV1() {
        return new ParcelOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link PackageAddressOpenUMLFeV1 }
     *
     */
    public PackageAddressOpenUMLFeV1 createPackageAddressOpenUMLFeV1() {
        return new PackageAddressOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServicesOpenUMLFeV2 }
     *
     */
    public ServicesOpenUMLFeV2 createServicesOpenUMLFeV2() {
        return new ServicesOpenUMLFeV2();
    }

    /**
     * Create an instance of {@link ServiceCarryInOpenUMLFeV1 }
     *
     */
    public ServiceCarryInOpenUMLFeV1 createServiceCarryInOpenUMLFeV1() {
        return new ServiceCarryInOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceCODOpenUMLFeV1 }
     *
     */
    public ServiceCODOpenUMLFeV1 createServiceCODOpenUMLFeV1() {
        return new ServiceCODOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceCUDOpenUMLeFV1 }
     *
     */
    public ServiceCUDOpenUMLeFV1 createServiceCUDOpenUMLeFV1() {
        return new ServiceCUDOpenUMLeFV1();
    }

    /**
     * Create an instance of {@link ServiceDeclaredValueOpenUMLFeV1 }
     *
     */
    public ServiceDeclaredValueOpenUMLFeV1 createServiceDeclaredValueOpenUMLFeV1() {
        return new ServiceDeclaredValueOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceDedicatedDeliveryOpenUMLFeV1 }
     *
     */
    public ServiceDedicatedDeliveryOpenUMLFeV1 createServiceDedicatedDeliveryOpenUMLFeV1() {
        return new ServiceDedicatedDeliveryOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServicePalletOpenUMLFeV1 }
     *
     */
    public ServicePalletOpenUMLFeV1 createServicePalletOpenUMLFeV1() {
        return new ServicePalletOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceDutyOpenUMLeFV1 }
     *
     */
    public ServiceDutyOpenUMLeFV1 createServiceDutyOpenUMLeFV1() {
        return new ServiceDutyOpenUMLeFV1();
    }

    /**
     * Create an instance of {@link ServiceGuaranteeOpenUMLFeV1 }
     *
     */
    public ServiceGuaranteeOpenUMLFeV1 createServiceGuaranteeOpenUMLFeV1() {
        return new ServiceGuaranteeOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceInPersOpenUMLFeV1 }
     *
     */
    public ServiceInPersOpenUMLFeV1 createServiceInPersOpenUMLFeV1() {
        return new ServiceInPersOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServicePrivPersOpenUMLFeV1 }
     *
     */
    public ServicePrivPersOpenUMLFeV1 createServicePrivPersOpenUMLFeV1() {
        return new ServicePrivPersOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceRODOpenUMLFeV1 }
     *
     */
    public ServiceRODOpenUMLFeV1 createServiceRODOpenUMLFeV1() {
        return new ServiceRODOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceSelfColOpenUMLFeV1 }
     *
     */
    public ServiceSelfColOpenUMLFeV1 createServiceSelfColOpenUMLFeV1() {
        return new ServiceSelfColOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceTiresOpenUMLFeV1 }
     *
     */
    public ServiceTiresOpenUMLFeV1 createServiceTiresOpenUMLFeV1() {
        return new ServiceTiresOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceTiresExportOpenUMLFeV1 }
     *
     */
    public ServiceTiresExportOpenUMLFeV1 createServiceTiresExportOpenUMLFeV1() {
        return new ServiceTiresExportOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link AuthDataV1 }
     *
     */
    public AuthDataV1 createAuthDataV1() {
        return new AuthDataV1();
    }

    /**
     * Create an instance of {@link PackagesGenerationResponseV1 }
     *
     */
    public PackagesGenerationResponseV1 createPackagesGenerationResponseV1() {
        return new PackagesGenerationResponseV1();
    }

    /**
     * Create an instance of {@link SessionPGRV1 }
     *
     */
    public SessionPGRV1 createSessionPGRV1() {
        return new SessionPGRV1();
    }

    /**
     * Create an instance of {@link PackagePGRV1 }
     *
     */
    public PackagePGRV1 createPackagePGRV1() {
        return new PackagePGRV1();
    }

    /**
     * Create an instance of {@link InvalidFieldPGRV1 }
     *
     */
    public InvalidFieldPGRV1 createInvalidFieldPGRV1() {
        return new InvalidFieldPGRV1();
    }

    /**
     * Create an instance of {@link ParcelPGRV1 }
     *
     */
    public ParcelPGRV1 createParcelPGRV1() {
        return new ParcelPGRV1();
    }

    /**
     * Create an instance of {@link DpdPickupCallParamsV3 }
     *
     */
    public DpdPickupCallParamsV3 createDpdPickupCallParamsV3() {
        return new DpdPickupCallParamsV3();
    }

    /**
     * Create an instance of {@link PickupCallSimplifiedDetailsDPPV1 }
     *
     */
    public PickupCallSimplifiedDetailsDPPV1 createPickupCallSimplifiedDetailsDPPV1() {
        return new PickupCallSimplifiedDetailsDPPV1();
    }

    /**
     * Create an instance of {@link PickupPackagesParamsDPPV1 }
     *
     */
    public PickupPackagesParamsDPPV1 createPickupPackagesParamsDPPV1() {
        return new PickupPackagesParamsDPPV1();
    }

    /**
     * Create an instance of {@link PickupCustomerDPPV1 }
     *
     */
    public PickupCustomerDPPV1 createPickupCustomerDPPV1() {
        return new PickupCustomerDPPV1();
    }

    /**
     * Create an instance of {@link PickupPayerDPPV1 }
     *
     */
    public PickupPayerDPPV1 createPickupPayerDPPV1() {
        return new PickupPayerDPPV1();
    }

    /**
     * Create an instance of {@link PickupSenderDPPV1 }
     *
     */
    public PickupSenderDPPV1 createPickupSenderDPPV1() {
        return new PickupSenderDPPV1();
    }

    /**
     * Create an instance of {@link PackagesPickupCallResponseV3 }
     *
     */
    public PackagesPickupCallResponseV3 createPackagesPickupCallResponseV3() {
        return new PackagesPickupCallResponseV3();
    }

    /**
     * Create an instance of {@link StatusInfoPCRV2 }
     *
     */
    public StatusInfoPCRV2 createStatusInfoPCRV2() {
        return new StatusInfoPCRV2();
    }

    /**
     * Create an instance of {@link ErrorDetailsPCRV2 }
     *
     */
    public ErrorDetailsPCRV2 createErrorDetailsPCRV2() {
        return new ErrorDetailsPCRV2();
    }

    /**
     * Create an instance of {@link SenderPlaceV1 }
     *
     */
    public SenderPlaceV1 createSenderPlaceV1() {
        return new SenderPlaceV1();
    }

    /**
     * Create an instance of {@link GetCourierOrderAvailabilityResponseV1 }
     *
     */
    public GetCourierOrderAvailabilityResponseV1 createGetCourierOrderAvailabilityResponseV1() {
        return new GetCourierOrderAvailabilityResponseV1();
    }

    /**
     * Create an instance of {@link CourierOrderAvailabilityRangeV1 }
     *
     */
    public CourierOrderAvailabilityRangeV1 createCourierOrderAvailabilityRangeV1() {
        return new CourierOrderAvailabilityRangeV1();
    }

    /**
     * Create an instance of {@link DpdPickupCallParamsV2 }
     *
     */
    public DpdPickupCallParamsV2 createDpdPickupCallParamsV2() {
        return new DpdPickupCallParamsV2();
    }

    /**
     * Create an instance of {@link PackagesPickupCallResponseV2 }
     *
     */
    public PackagesPickupCallResponseV2 createPackagesPickupCallResponseV2() {
        return new PackagesPickupCallResponseV2();
    }

    /**
     * Create an instance of {@link OpenUMLFeV3 }
     *
     */
    public OpenUMLFeV3 createOpenUMLFeV3() {
        return new OpenUMLFeV3();
    }

    /**
     * Create an instance of {@link PackageOpenUMLFeV3 }
     *
     */
    public PackageOpenUMLFeV3 createPackageOpenUMLFeV3() {
        return new PackageOpenUMLFeV3();
    }

    /**
     * Create an instance of {@link ServicesOpenUMLFeV4 }
     *
     */
    public ServicesOpenUMLFeV4 createServicesOpenUMLFeV4() {
        return new ServicesOpenUMLFeV4();
    }

    /**
     * Create an instance of {@link ServiceFlagOpenUMLF }
     *
     */
    public ServiceFlagOpenUMLF createServiceFlagOpenUMLF() {
        return new ServiceFlagOpenUMLF();
    }

    /**
     * Create an instance of {@link ServiceDpdPickupOpenUMLFeV1 }
     *
     */
    public ServiceDpdPickupOpenUMLFeV1 createServiceDpdPickupOpenUMLFeV1() {
        return new ServiceDpdPickupOpenUMLFeV1();
    }

    /**
     * Create an instance of {@link ServiceDutyOpenUMLeFV2 }
     *
     */
    public ServiceDutyOpenUMLeFV2 createServiceDutyOpenUMLeFV2() {
        return new ServiceDutyOpenUMLeFV2();
    }

    /**
     * Create an instance of {@link PackagesGenerationResponseV2 }
     *
     */
    public PackagesGenerationResponseV2 createPackagesGenerationResponseV2() {
        return new PackagesGenerationResponseV2();
    }

    /**
     * Create an instance of {@link ValidationInfoPGRV2 }
     *
     */
    public ValidationInfoPGRV2 createValidationInfoPGRV2() {
        return new ValidationInfoPGRV2();
    }

    /**
     * Create an instance of {@link DpdPickupCallParamsV1 }
     *
     */
    public DpdPickupCallParamsV1 createDpdPickupCallParamsV1() {
        return new DpdPickupCallParamsV1();
    }

    /**
     * Create an instance of {@link ContactInfoDPPV1 }
     *
     */
    public ContactInfoDPPV1 createContactInfoDPPV1() {
        return new ContactInfoDPPV1();
    }

    /**
     * Create an instance of {@link PickupAddressDSPV1 }
     *
     */
    public PickupAddressDSPV1 createPickupAddressDSPV1() {
        return new PickupAddressDSPV1();
    }

    /**
     * Create an instance of {@link ProtocolDPPV1 }
     *
     */
    public ProtocolDPPV1 createProtocolDPPV1() {
        return new ProtocolDPPV1();
    }

    /**
     * Create an instance of {@link PackagesPickupCallResponseV1 }
     *
     */
    public PackagesPickupCallResponseV1 createPackagesPickupCallResponseV1() {
        return new PackagesPickupCallResponseV1();
    }

    /**
     * Create an instance of {@link ProtocolPCRV1 }
     *
     */
    public ProtocolPCRV1 createProtocolPCRV1() {
        return new ProtocolPCRV1();
    }

    /**
     * Create an instance of {@link StatusInfoPCRV1 }
     *
     */
    public StatusInfoPCRV1 createStatusInfoPCRV1() {
        return new StatusInfoPCRV1();
    }

    /**
     * Create an instance of {@link ParcelsAppendV1 }
     *
     */
    public ParcelsAppendV1 createParcelsAppendV1() {
        return new ParcelsAppendV1();
    }

    /**
     * Create an instance of {@link ParcelsAppendSearchCriteriaPAV1 }
     *
     */
    public ParcelsAppendSearchCriteriaPAV1 createParcelsAppendSearchCriteriaPAV1() {
        return new ParcelsAppendSearchCriteriaPAV1();
    }

    /**
     * Create an instance of {@link ParcelAppendPAV1 }
     *
     */
    public ParcelAppendPAV1 createParcelAppendPAV1() {
        return new ParcelAppendPAV1();
    }

    /**
     * Create an instance of {@link ParcelsAppendResponseV1 }
     *
     */
    public ParcelsAppendResponseV1 createParcelsAppendResponseV1() {
        return new ParcelsAppendResponseV1();
    }

    /**
     * Create an instance of {@link InvalidFieldPAV1 }
     *
     */
    public InvalidFieldPAV1 createInvalidFieldPAV1() {
        return new InvalidFieldPAV1();
    }

    /**
     * Create an instance of {@link ParcelsAppendParcelPAV1 }
     *
     */
    public ParcelsAppendParcelPAV1 createParcelsAppendParcelPAV1() {
        return new ParcelsAppendParcelPAV1();
    }

    /**
     * Create an instance of {@link OpenUMLFeV2 }
     *
     */
    public OpenUMLFeV2 createOpenUMLFeV2() {
        return new OpenUMLFeV2();
    }

    /**
     * Create an instance of {@link PackageOpenUMLFeV2 }
     *
     */
    public PackageOpenUMLFeV2 createPackageOpenUMLFeV2() {
        return new PackageOpenUMLFeV2();
    }

    /**
     * Create an instance of {@link ServicesOpenUMLFeV3 }
     *
     */
    public ServicesOpenUMLFeV3 createServicesOpenUMLFeV3() {
        return new ServicesOpenUMLFeV3();
    }

    /**
     * Create an instance of {@link DpdParcelBusinessEventV1 }
     *
     */
    public DpdParcelBusinessEventV1 createDpdParcelBusinessEventV1() {
        return new DpdParcelBusinessEventV1();
    }

    /**
     * Create an instance of {@link DpdParcelBusinessEventDataV1 }
     *
     */
    public DpdParcelBusinessEventDataV1 createDpdParcelBusinessEventDataV1() {
        return new DpdParcelBusinessEventDataV1();
    }

    /**
     * Create an instance of {@link ImportDeliveryBusinessEventResponseV1 }
     *
     */
    public ImportDeliveryBusinessEventResponseV1 createImportDeliveryBusinessEventResponseV1() {
        return new ImportDeliveryBusinessEventResponseV1();
    }

    /**
     * Create an instance of {@link DpdServicesParamsV1 }
     *
     */
    public DpdServicesParamsV1 createDpdServicesParamsV1() {
        return new DpdServicesParamsV1();
    }

    /**
     * Create an instance of {@link SessionDSPV1 }
     *
     */
    public SessionDSPV1 createSessionDSPV1() {
        return new SessionDSPV1();
    }

    /**
     * Create an instance of {@link PackageDSPV1 }
     *
     */
    public PackageDSPV1 createPackageDSPV1() {
        return new PackageDSPV1();
    }

    /**
     * Create an instance of {@link ParcelDSPV1 }
     *
     */
    public ParcelDSPV1 createParcelDSPV1() {
        return new ParcelDSPV1();
    }

    /**
     * Create an instance of {@link DocumentGenerationResponseV1 }
     *
     */
    public DocumentGenerationResponseV1 createDocumentGenerationResponseV1() {
        return new DocumentGenerationResponseV1();
    }

    /**
     * Create an instance of {@link SessionDGRV1 }
     *
     */
    public SessionDGRV1 createSessionDGRV1() {
        return new SessionDGRV1();
    }

    /**
     * Create an instance of {@link PackageDGRV1 }
     *
     */
    public PackageDGRV1 createPackageDGRV1() {
        return new PackageDGRV1();
    }

    /**
     * Create an instance of {@link ParcelDGRV1 }
     *
     */
    public ParcelDGRV1 createParcelDGRV1() {
        return new ParcelDGRV1();
    }

    /**
     * Create an instance of {@link StatusInfoDGRV1 }
     *
     */
    public StatusInfoDGRV1 createStatusInfoDGRV1() {
        return new StatusInfoDGRV1();
    }

    /**
     * Create an instance of {@link PostalCodeV1 }
     *
     */
    public PostalCodeV1 createPostalCodeV1() {
        return new PostalCodeV1();
    }

    /**
     * Create an instance of {@link FindPostalCodeResponseV1 }
     *
     */
    public FindPostalCodeResponseV1 createFindPostalCodeResponseV1() {
        return new FindPostalCodeResponseV1();
    }

    /**
     * Create an instance of {@link ParcelDSPV2 }
     *
     */
    public ParcelDSPV2 createParcelDSPV2() {
        return new ParcelDSPV2();
    }

    /**
     * Create an instance of {@link PickupAddressDSPV2 }
     *
     */
    public PickupAddressDSPV2 createPickupAddressDSPV2() {
        return new PickupAddressDSPV2();
    }

    /**
     * Create an instance of {@link ProtocolDepot }
     *
     */
    public ProtocolDepot createProtocolDepot() {
        return new ProtocolDepot();
    }

    /**
     * Create an instance of {@link DestinationsData }
     *
     */
    public DestinationsData createDestinationsData() {
        return new DestinationsData();
    }

    /**
     * Create an instance of {@link NonMatchingData }
     *
     */
    public NonMatchingData createNonMatchingData() {
        return new NonMatchingData();
    }

    /**
     * Create an instance of {@link PackageDGRV2 }
     *
     */
    public PackageDGRV2 createPackageDGRV2() {
        return new PackageDGRV2();
    }

    /**
     * Create an instance of {@link ParcelDGRV2 }
     *
     */
    public ParcelDGRV2 createParcelDGRV2() {
        return new ParcelDGRV2();
    }

    /**
     * Create an instance of {@link StatusInfoDGRV2 }
     *
     */
    public StatusInfoDGRV2 createStatusInfoDGRV2() {
        return new StatusInfoDGRV2();
    }

    /**
     * Create an instance of {@link SessionDGRV2 .Packages }
     *
     */
    public SessionDGRV2 .Packages createSessionDGRV2Packages() {
        return new SessionDGRV2 .Packages();
    }

    /**
     * Create an instance of {@link DocumentGenerationResponseV2 .DestinationDataList }
     *
     */
    public DocumentGenerationResponseV2 .DestinationDataList createDocumentGenerationResponseV2DestinationDataList() {
        return new DocumentGenerationResponseV2 .DestinationDataList();
    }

    /**
     * Create an instance of {@link DeliveryDestination.DepotList }
     *
     */
    public DeliveryDestination.DepotList createDeliveryDestinationDepotList() {
        return new DeliveryDestination.DepotList();
    }

    /**
     * Create an instance of {@link PackageDSPV2 .Parcels }
     *
     */
    public PackageDSPV2 .Parcels createPackageDSPV2Parcels() {
        return new PackageDSPV2 .Parcels();
    }

    /**
     * Create an instance of {@link SessionDSPV2 .Packages }
     *
     */
    public SessionDSPV2 .Packages createSessionDSPV2Packages() {
        return new SessionDSPV2 .Packages();
    }

    /**
     * Create an instance of {@link DpdServicesParamsV2 .DeliveryDestinations }
     *
     */
    public DpdServicesParamsV2 .DeliveryDestinations createDpdServicesParamsV2DeliveryDestinations() {
        return new DpdServicesParamsV2 .DeliveryDestinations();
    }

    /**
     * Create an instance of {@link ParcelPGRV2 .ValidationDetails }
     *
     */
    public ParcelPGRV2 .ValidationDetails createParcelPGRV2ValidationDetails() {
        return new ParcelPGRV2 .ValidationDetails();
    }

    /**
     * Create an instance of {@link PackagePGRV2 .ValidationDetails }
     *
     */
    public PackagePGRV2 .ValidationDetails createPackagePGRV2ValidationDetails() {
        return new PackagePGRV2 .ValidationDetails();
    }

    /**
     * Create an instance of {@link PackagePGRV2 .Parcels }
     *
     */
    public PackagePGRV2 .Parcels createPackagePGRV2Parcels() {
        return new PackagePGRV2 .Parcels();
    }

    /**
     * Create an instance of {@link SessionPGRV2 .Packages }
     *
     */
    public SessionPGRV2 .Packages createSessionPGRV2Packages() {
        return new SessionPGRV2 .Packages();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DPDServiceException }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "DPDServiceException")
    public JAXBElement<DPDServiceException> createDPDServiceException(DPDServiceException value) {
        return new JAXBElement<DPDServiceException>(_DPDServiceException_QNAME, DPDServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeniedAccessWSException }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "DeniedAccessWSException")
    public JAXBElement<DeniedAccessWSException> createDeniedAccessWSException(DeniedAccessWSException value) {
        return new JAXBElement<DeniedAccessWSException>(_DeniedAccessWSException_QNAME, DeniedAccessWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchemaValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "SchemaValidationException")
    public JAXBElement<SchemaValidationException> createSchemaValidationException(SchemaValidationException value) {
        return new JAXBElement<SchemaValidationException>(_SchemaValidationException_QNAME, SchemaValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppendParcelsToPackageV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "appendParcelsToPackageV1")
    public JAXBElement<AppendParcelsToPackageV1> createAppendParcelsToPackageV1(AppendParcelsToPackageV1 value) {
        return new JAXBElement<AppendParcelsToPackageV1>(_AppendParcelsToPackageV1_QNAME, AppendParcelsToPackageV1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppendParcelsToPackageV1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "appendParcelsToPackageV1Response")
    public JAXBElement<AppendParcelsToPackageV1Response> createAppendParcelsToPackageV1Response(AppendParcelsToPackageV1Response value) {
        return new JAXBElement<AppendParcelsToPackageV1Response>(_AppendParcelsToPackageV1Response_QNAME, AppendParcelsToPackageV1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindPostalCodeV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "findPostalCodeV1")
    public JAXBElement<FindPostalCodeV1> createFindPostalCodeV1(FindPostalCodeV1 value) {
        return new JAXBElement<FindPostalCodeV1>(_FindPostalCodeV1_QNAME, FindPostalCodeV1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindPostalCodeV1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "findPostalCodeV1Response")
    public JAXBElement<FindPostalCodeV1Response> createFindPostalCodeV1Response(FindPostalCodeV1Response value) {
        return new JAXBElement<FindPostalCodeV1Response>(_FindPostalCodeV1Response_QNAME, FindPostalCodeV1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePackagesNumbersV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generatePackagesNumbersV1")
    public JAXBElement<GeneratePackagesNumbersV1> createGeneratePackagesNumbersV1(GeneratePackagesNumbersV1 value) {
        return new JAXBElement<GeneratePackagesNumbersV1>(_GeneratePackagesNumbersV1_QNAME, GeneratePackagesNumbersV1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePackagesNumbersV1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generatePackagesNumbersV1Response")
    public JAXBElement<GeneratePackagesNumbersV1Response> createGeneratePackagesNumbersV1Response(GeneratePackagesNumbersV1Response value) {
        return new JAXBElement<GeneratePackagesNumbersV1Response>(_GeneratePackagesNumbersV1Response_QNAME, GeneratePackagesNumbersV1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePackagesNumbersV2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generatePackagesNumbersV2")
    public JAXBElement<GeneratePackagesNumbersV2> createGeneratePackagesNumbersV2(GeneratePackagesNumbersV2 value) {
        return new JAXBElement<GeneratePackagesNumbersV2>(_GeneratePackagesNumbersV2_QNAME, GeneratePackagesNumbersV2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePackagesNumbersV2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generatePackagesNumbersV2Response")
    public JAXBElement<GeneratePackagesNumbersV2Response> createGeneratePackagesNumbersV2Response(GeneratePackagesNumbersV2Response value) {
        return new JAXBElement<GeneratePackagesNumbersV2Response>(_GeneratePackagesNumbersV2Response_QNAME, GeneratePackagesNumbersV2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePackagesNumbersV3 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generatePackagesNumbersV3")
    public JAXBElement<GeneratePackagesNumbersV3> createGeneratePackagesNumbersV3(GeneratePackagesNumbersV3 value) {
        return new JAXBElement<GeneratePackagesNumbersV3>(_GeneratePackagesNumbersV3_QNAME, GeneratePackagesNumbersV3 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePackagesNumbersV3Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generatePackagesNumbersV3Response")
    public JAXBElement<GeneratePackagesNumbersV3Response> createGeneratePackagesNumbersV3Response(GeneratePackagesNumbersV3Response value) {
        return new JAXBElement<GeneratePackagesNumbersV3Response>(_GeneratePackagesNumbersV3Response_QNAME, GeneratePackagesNumbersV3Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePackagesNumbersV4 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generatePackagesNumbersV4")
    public JAXBElement<GeneratePackagesNumbersV4> createGeneratePackagesNumbersV4(GeneratePackagesNumbersV4 value) {
        return new JAXBElement<GeneratePackagesNumbersV4>(_GeneratePackagesNumbersV4_QNAME, GeneratePackagesNumbersV4 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePackagesNumbersV4Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generatePackagesNumbersV4Response")
    public JAXBElement<GeneratePackagesNumbersV4Response> createGeneratePackagesNumbersV4Response(GeneratePackagesNumbersV4Response value) {
        return new JAXBElement<GeneratePackagesNumbersV4Response>(_GeneratePackagesNumbersV4Response_QNAME, GeneratePackagesNumbersV4Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateProtocolV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateProtocolV1")
    public JAXBElement<GenerateProtocolV1> createGenerateProtocolV1(GenerateProtocolV1 value) {
        return new JAXBElement<GenerateProtocolV1>(_GenerateProtocolV1_QNAME, GenerateProtocolV1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateProtocolV1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateProtocolV1Response")
    public JAXBElement<GenerateProtocolV1Response> createGenerateProtocolV1Response(GenerateProtocolV1Response value) {
        return new JAXBElement<GenerateProtocolV1Response>(_GenerateProtocolV1Response_QNAME, GenerateProtocolV1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateProtocolV2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateProtocolV2")
    public JAXBElement<GenerateProtocolV2> createGenerateProtocolV2(GenerateProtocolV2 value) {
        return new JAXBElement<GenerateProtocolV2>(_GenerateProtocolV2_QNAME, GenerateProtocolV2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateProtocolV2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateProtocolV2Response")
    public JAXBElement<GenerateProtocolV2Response> createGenerateProtocolV2Response(GenerateProtocolV2Response value) {
        return new JAXBElement<GenerateProtocolV2Response>(_GenerateProtocolV2Response_QNAME, GenerateProtocolV2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateProtocolsWithDestinationsV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateProtocolsWithDestinationsV1")
    public JAXBElement<GenerateProtocolsWithDestinationsV1> createGenerateProtocolsWithDestinationsV1(GenerateProtocolsWithDestinationsV1 value) {
        return new JAXBElement<GenerateProtocolsWithDestinationsV1>(_GenerateProtocolsWithDestinationsV1_QNAME, GenerateProtocolsWithDestinationsV1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateProtocolsWithDestinationsV1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateProtocolsWithDestinationsV1Response")
    public JAXBElement<GenerateProtocolsWithDestinationsV1Response> createGenerateProtocolsWithDestinationsV1Response(GenerateProtocolsWithDestinationsV1Response value) {
        return new JAXBElement<GenerateProtocolsWithDestinationsV1Response>(_GenerateProtocolsWithDestinationsV1Response_QNAME, GenerateProtocolsWithDestinationsV1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateProtocolsWithDestinationsV2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateProtocolsWithDestinationsV2")
    public JAXBElement<GenerateProtocolsWithDestinationsV2> createGenerateProtocolsWithDestinationsV2(GenerateProtocolsWithDestinationsV2 value) {
        return new JAXBElement<GenerateProtocolsWithDestinationsV2>(_GenerateProtocolsWithDestinationsV2_QNAME, GenerateProtocolsWithDestinationsV2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateProtocolsWithDestinationsV2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateProtocolsWithDestinationsV2Response")
    public JAXBElement<GenerateProtocolsWithDestinationsV2Response> createGenerateProtocolsWithDestinationsV2Response(GenerateProtocolsWithDestinationsV2Response value) {
        return new JAXBElement<GenerateProtocolsWithDestinationsV2Response>(_GenerateProtocolsWithDestinationsV2Response_QNAME, GenerateProtocolsWithDestinationsV2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateSpedLabelsV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateSpedLabelsV1")
    public JAXBElement<GenerateSpedLabelsV1> createGenerateSpedLabelsV1(GenerateSpedLabelsV1 value) {
        return new JAXBElement<GenerateSpedLabelsV1>(_GenerateSpedLabelsV1_QNAME, GenerateSpedLabelsV1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateSpedLabelsV1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateSpedLabelsV1Response")
    public JAXBElement<GenerateSpedLabelsV1Response> createGenerateSpedLabelsV1Response(GenerateSpedLabelsV1Response value) {
        return new JAXBElement<GenerateSpedLabelsV1Response>(_GenerateSpedLabelsV1Response_QNAME, GenerateSpedLabelsV1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateSpedLabelsV2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateSpedLabelsV2")
    public JAXBElement<GenerateSpedLabelsV2> createGenerateSpedLabelsV2(GenerateSpedLabelsV2 value) {
        return new JAXBElement<GenerateSpedLabelsV2>(_GenerateSpedLabelsV2_QNAME, GenerateSpedLabelsV2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateSpedLabelsV2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateSpedLabelsV2Response")
    public JAXBElement<GenerateSpedLabelsV2Response> createGenerateSpedLabelsV2Response(GenerateSpedLabelsV2Response value) {
        return new JAXBElement<GenerateSpedLabelsV2Response>(_GenerateSpedLabelsV2Response_QNAME, GenerateSpedLabelsV2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateSpedLabelsV3 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateSpedLabelsV3")
    public JAXBElement<GenerateSpedLabelsV3> createGenerateSpedLabelsV3(GenerateSpedLabelsV3 value) {
        return new JAXBElement<GenerateSpedLabelsV3>(_GenerateSpedLabelsV3_QNAME, GenerateSpedLabelsV3 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateSpedLabelsV3Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateSpedLabelsV3Response")
    public JAXBElement<GenerateSpedLabelsV3Response> createGenerateSpedLabelsV3Response(GenerateSpedLabelsV3Response value) {
        return new JAXBElement<GenerateSpedLabelsV3Response>(_GenerateSpedLabelsV3Response_QNAME, GenerateSpedLabelsV3Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateSpedLabelsV4 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateSpedLabelsV4")
    public JAXBElement<GenerateSpedLabelsV4> createGenerateSpedLabelsV4(GenerateSpedLabelsV4 value) {
        return new JAXBElement<GenerateSpedLabelsV4>(_GenerateSpedLabelsV4_QNAME, GenerateSpedLabelsV4 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateSpedLabelsV4Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "generateSpedLabelsV4Response")
    public JAXBElement<GenerateSpedLabelsV4Response> createGenerateSpedLabelsV4Response(GenerateSpedLabelsV4Response value) {
        return new JAXBElement<GenerateSpedLabelsV4Response>(_GenerateSpedLabelsV4Response_QNAME, GenerateSpedLabelsV4Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCourierOrderAvailabilityV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "getCourierOrderAvailabilityV1")
    public JAXBElement<GetCourierOrderAvailabilityV1> createGetCourierOrderAvailabilityV1(GetCourierOrderAvailabilityV1 value) {
        return new JAXBElement<GetCourierOrderAvailabilityV1>(_GetCourierOrderAvailabilityV1_QNAME, GetCourierOrderAvailabilityV1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCourierOrderAvailabilityV1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "getCourierOrderAvailabilityV1Response")
    public JAXBElement<GetCourierOrderAvailabilityV1Response> createGetCourierOrderAvailabilityV1Response(GetCourierOrderAvailabilityV1Response value) {
        return new JAXBElement<GetCourierOrderAvailabilityV1Response>(_GetCourierOrderAvailabilityV1Response_QNAME, GetCourierOrderAvailabilityV1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportDeliveryBusinessEventV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "importDeliveryBusinessEventV1")
    public JAXBElement<ImportDeliveryBusinessEventV1> createImportDeliveryBusinessEventV1(ImportDeliveryBusinessEventV1 value) {
        return new JAXBElement<ImportDeliveryBusinessEventV1>(_ImportDeliveryBusinessEventV1_QNAME, ImportDeliveryBusinessEventV1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportDeliveryBusinessEventV1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "importDeliveryBusinessEventV1Response")
    public JAXBElement<ImportDeliveryBusinessEventV1Response> createImportDeliveryBusinessEventV1Response(ImportDeliveryBusinessEventV1Response value) {
        return new JAXBElement<ImportDeliveryBusinessEventV1Response>(_ImportDeliveryBusinessEventV1Response_QNAME, ImportDeliveryBusinessEventV1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackagesPickupCallV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "packagesPickupCallV1")
    public JAXBElement<PackagesPickupCallV1> createPackagesPickupCallV1(PackagesPickupCallV1 value) {
        return new JAXBElement<PackagesPickupCallV1>(_PackagesPickupCallV1_QNAME, PackagesPickupCallV1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackagesPickupCallV1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "packagesPickupCallV1Response")
    public JAXBElement<PackagesPickupCallV1Response> createPackagesPickupCallV1Response(PackagesPickupCallV1Response value) {
        return new JAXBElement<PackagesPickupCallV1Response>(_PackagesPickupCallV1Response_QNAME, PackagesPickupCallV1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackagesPickupCallV2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "packagesPickupCallV2")
    public JAXBElement<PackagesPickupCallV2> createPackagesPickupCallV2(PackagesPickupCallV2 value) {
        return new JAXBElement<PackagesPickupCallV2>(_PackagesPickupCallV2_QNAME, PackagesPickupCallV2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackagesPickupCallV2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "packagesPickupCallV2Response")
    public JAXBElement<PackagesPickupCallV2Response> createPackagesPickupCallV2Response(PackagesPickupCallV2Response value) {
        return new JAXBElement<PackagesPickupCallV2Response>(_PackagesPickupCallV2Response_QNAME, PackagesPickupCallV2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackagesPickupCallV3 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "packagesPickupCallV3")
    public JAXBElement<PackagesPickupCallV3> createPackagesPickupCallV3(PackagesPickupCallV3 value) {
        return new JAXBElement<PackagesPickupCallV3>(_PackagesPickupCallV3_QNAME, PackagesPickupCallV3 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackagesPickupCallV3Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "packagesPickupCallV3Response")
    public JAXBElement<PackagesPickupCallV3Response> createPackagesPickupCallV3Response(PackagesPickupCallV3Response value) {
        return new JAXBElement<PackagesPickupCallV3Response>(_PackagesPickupCallV3Response_QNAME, PackagesPickupCallV3Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackagesPickupCallV4 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "packagesPickupCallV4")
    public JAXBElement<PackagesPickupCallV4> createPackagesPickupCallV4(PackagesPickupCallV4 value) {
        return new JAXBElement<PackagesPickupCallV4>(_PackagesPickupCallV4_QNAME, PackagesPickupCallV4 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackagesPickupCallV4Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dpdservices.dpd.com.pl/", name = "packagesPickupCallV4Response")
    public JAXBElement<PackagesPickupCallV4Response> createPackagesPickupCallV4Response(PackagesPickupCallV4Response value) {
        return new JAXBElement<PackagesPickupCallV4Response>(_PackagesPickupCallV4Response_QNAME, PackagesPickupCallV4Response.class, null, value);
    }

}
