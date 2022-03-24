package com.ruoyi.system.DPDServicesExample.client;

import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.dpdservices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DPDServicesXMLClient {

    @Value("${dpdservices.authdata.login}")
    private String login;

    @Value("${dpdservices.authdata.password}")
    private String password;

    @Value("${dpdservices.authdata.fid}")
    private Integer fid;

    @Autowired
    private DPDPackageObjServices xmlServices;

    private long sessionId;
    private long packageId;

//    @PostConstruct
    public void runExamples() {
        findPostalCode();
    }

    private void findPostalCode() {
        AuthDataV1 authData = getAuthData();

        generatePackagesNumber(authData);

        generateSpedLabels(sessionId, authData);

        generateProtocol(authData);

        findPostalCode(authData);
    }

    private void generateProtocol(AuthDataV1 authData) {
        PackageDSPV1 pkg5 = new PackageDSPV1();
        pkg5.setPackageId(packageId);

        SessionDSPV1 session = new SessionDSPV1();
        session.getPackages().add(pkg5);
        session.setSessionType(SessionTypeDSPEnumV1.DOMESTIC);

        DpdServicesParamsV1 param = new DpdServicesParamsV1();
        param.setPolicy(PolicyDSPEnumV1.STOP_ON_FIRST_ERROR);
        param.setSession(session);

        try {
            DocumentGenerationResponseV1 ret = xmlServices.generateProtocolV1(param, OutputDocFormatDSPEnumV1.PDF, OutputDocPageFormatDSPEnumV1.A_4, authData);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成pdf文件
     * @param authData
     * @param paramPackageId
     */
    private void generateProtocolByPackageId(AuthDataV1 authData, long paramPackageId) {
        PackageDSPV1 pkg5 = new PackageDSPV1();
        pkg5.setPackageId(paramPackageId);

        SessionDSPV1 session = new SessionDSPV1();
        session.getPackages().add(pkg5);
        session.setSessionType(SessionTypeDSPEnumV1.DOMESTIC);

        DpdServicesParamsV1 param = new DpdServicesParamsV1();
        param.setPolicy(PolicyDSPEnumV1.STOP_ON_FIRST_ERROR);
        param.setSession(session);

        try {
            DocumentGenerationResponseV1 ret = xmlServices.generateProtocolV1(param, OutputDocFormatDSPEnumV1.PDF, OutputDocPageFormatDSPEnumV1.A_4, authData);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    private void generateSpedLabels(long sessionId, AuthDataV1 authData) {
        SessionDSPV1 session = new SessionDSPV1();
        DpdServicesParamsV1 param = new DpdServicesParamsV1();
        param.setPolicy(PolicyDSPEnumV1.STOP_ON_FIRST_ERROR);

        // Na podstawie sessionId
        session.setSessionId(sessionId);
        session.setSessionType(SessionTypeDSPEnumV1.DOMESTIC);
        param.setSession(session);

        try {
            DocumentGenerationResponseV1 ret = xmlServices.generateSpedLabelsV4(param, OutputDocFormatDSPEnumV1.PDF, OutputDocPageFormatDSPEnumV1.A_4, OutputLabelTypeEnumV1.BIC_3, "", authData);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成返回值
     * @param authData
     * @param packages
     * @return
     */
    private List<PackagesGenerationResponse> generatePackagesNumberByBusiness(AuthDataV1 authData, List<Package> packages) {
        OpenUMLFeV3 umlf = new OpenUMLFeV3(); // Ilość przesyłek
        for (Package aPackage : packages) {
            AddressReceiver receiver = aPackage.getReceiver();
            AddressSender sender = aPackage.getSender();
            Services ser = aPackage.getService();
            List<Parcel> parcels = aPackage.getParcels();

            PackageOpenUMLFeV3 pkg = new PackageOpenUMLFeV3();
            pkg.setPayerType(PayerTypeEnumOpenUMLFeV1.SENDER);

            PackageAddressOpenUMLFeV1 addressSender = new PackageAddressOpenUMLFeV1();
            addressSender.setAddress(sender.getAddress());
            addressSender.setCity(sender.getCity());
            addressSender.setCompany(sender.getCompany());
            addressSender.setCountryCode(sender.getCountryCode());
            addressSender.setEmail(sender.getEmail());
            addressSender.setFid(Integer.valueOf(sender.getFid().toString()));
            addressSender.setName(sender.getName());
            addressSender.setPhone(sender.getName());
            addressSender.setPostalCode(sender.getPostalCode());
            pkg.setSender(addressSender);

            PackageAddressOpenUMLFeV1 addressReceiver = new PackageAddressOpenUMLFeV1();
            addressReceiver.setAddress(receiver.getAddress());
            addressReceiver.setCity(receiver.getCity());
            addressReceiver.setCompany(receiver.getCompany());
            addressReceiver.setCountryCode(receiver.getCountryCode());
            addressReceiver.setEmail(receiver.getEmail());
            addressReceiver.setName(receiver.getName());
            addressReceiver.setPhone(receiver.getPhone());
            addressReceiver.setPostalCode(receiver.getPostalCode());
            pkg.setReceiver(addressReceiver);

            pkg.setRef1(aPackage.getRef1());
            pkg.setRef2(aPackage.getRef2());

            ServicesOpenUMLFeV4 services = new ServicesOpenUMLFeV4();
            ServiceCODOpenUMLFeV1 cod = new ServiceCODOpenUMLFeV1();
            cod.setAmount(ser.getCodAmount());
            cod.setCurrency(ServiceCurrencyEnum.PLN);
            services.setCod(cod);

            ServiceRODOpenUMLFeV1 rod = new ServiceRODOpenUMLFeV1();
            services.setRod(rod);

            pkg.setServices(services);

            for (Parcel parcel : parcels) {
                ParcelOpenUMLFeV1 parcel1 = new ParcelOpenUMLFeV1();
                parcel1.setSizeX(parcel.getSizeX());
                parcel1.setSizeY(parcel.getSizeY());
                parcel1.setSizeZ(parcel.getSizeZ());
                parcel1.setContent(parcel.getContent());//id
                parcel1.setCustomerData1(parcel.getCustomerData1());//id
                parcel1.setReference(parcel.getReference()); //parametr opcjonalny
                parcel1.setWeight(Double.valueOf(parcel.getWeight().toString()));
                pkg.getParcels().add(parcel1);
            }
            umlf.getPackages().add(pkg);
        }

        PackagesGenerationResponseV2 documentGenerationResponse = null;
        try {
            documentGenerationResponse = xmlServices.generatePackagesNumbersV4(umlf, PkgNumsGenerationPolicyV1.IGNORE_ERRORS, "PL", authData);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }

        List<PackagesGenerationResponse> returnResponses = new ArrayList<>();
        List<PackagePGRV2> packagePGRV2s = documentGenerationResponse.getPackages().getPackage();
        for (int i = 0; i < packages.size(); i++) {
            PackagesGenerationResponse packagesGenerationResponse = new PackagesGenerationResponse();
            packagesGenerationResponse.setSessionId(documentGenerationResponse.getSessionId());
            packagesGenerationResponse.setStatus(documentGenerationResponse.getStatus());
            mapResult(packagePGRV2s.get(i), packages.get(i), packagesGenerationResponse);
            returnResponses.add(packagesGenerationResponse);
        }
        return returnResponses;
    }

    /**
     * 处理返回值
     * @param sourse
     * @param targetPackage
     * @param targetPackagesGenerationResponse
     */
    private void mapResult(PackagePGRV2 sourse, Package targetPackage, PackagesGenerationResponse targetPackagesGenerationResponse){
        // 处理 PackagesGenerationResponse
        targetPackagesGenerationResponse.setPackageId(sourse.getPackageId());
        targetPackagesGenerationResponse.setPackId(targetPackage.getId());
        targetPackagesGenerationResponse.setPkgReference(sourse.getReference());
        targetPackagesGenerationResponse.setPkgStatus(sourse.getStatus());

        // 处理 Package
        List<Parcel> parcels = targetPackage.getParcels();
        List<ParcelPGRV2> parcelPGRV2s = sourse.getParcels().getParcel();
        for (int i = 0; i < parcels.size(); i++) {
            Parcel parcel = parcels.get(i);
            ParcelPGRV2 parcelPGRV2 = parcelPGRV2s.get(i);
            parcel.setWaybill(parcelPGRV2.getWaybill());
            parcel.setStatus(parcelPGRV2.getStatus());
            parcel.setPackageId(sourse.getPackageId());
        }

    }

    private void generatePackagesNumber(AuthDataV1 authData) {
        OpenUMLFeV3 umlf = new OpenUMLFeV3(); // Ilość przesyłek
        PackageOpenUMLFeV3 pkg = new PackageOpenUMLFeV3();
        pkg.setPayerType(PayerTypeEnumOpenUMLFeV1.SENDER);

        PackageAddressOpenUMLFeV1 addressSender = new PackageAddressOpenUMLFeV1();
        addressSender.setAddress("Ul. Mineralna 15");
        addressSender.setCity("Warszawa");
        addressSender.setCompany("DPD Polska Sp. z o.o.");
        addressSender.setCountryCode("PL");
        addressSender.setEmail("dpd@com.pl");
        addressSender.setFid(1495);
        addressSender.setName("Jan Kowalski");
        addressSender.setPhone("022 577 55 00");
        addressSender.setPostalCode("02274");
        pkg.setSender(addressSender);

        PackageAddressOpenUMLFeV1 addressReceiver = new PackageAddressOpenUMLFeV1();
        addressReceiver.setAddress("ul. Brzeziñska 59");
        addressReceiver.setCity("Mysłowice");
        addressReceiver.setCompany("Oddział Regionalny w Katowicach");
        addressReceiver.setCountryCode("PL");
        addressReceiver.setEmail("dpd@com.pl");
        addressReceiver.setName("Jan Malinowski)");
        addressReceiver.setPhone("32 202-40-11");
        addressReceiver.setPostalCode("41404");
        pkg.setReceiver(addressReceiver);

        pkg.setRef1("5h163K1");
        pkg.setRef2("FV/2017/12/1234");

        ServicesOpenUMLFeV4 services = new ServicesOpenUMLFeV4();
        ServiceCODOpenUMLFeV1 cod = new ServiceCODOpenUMLFeV1();
        cod.setAmount("1234");
        cod.setCurrency(ServiceCurrencyEnum.PLN);
        services.setCod(cod);

        ServiceRODOpenUMLFeV1 rod = new ServiceRODOpenUMLFeV1();
        services.setRod(rod);

        pkg.setServices(services);

        ParcelOpenUMLFeV1 parcel1 = new ParcelOpenUMLFeV1();
        parcel1.setSizeX(7);
        parcel1.setSizeY(5);
        parcel1.setSizeZ(4);
        parcel1.setContent("telefon");
        parcel1.setCustomerData1("imei 1234");
        parcel1.setReference("Nr kl. 11"); //parametr opcjonalny
        parcel1.setWeight(0.3);

        pkg.getParcels().add(parcel1);

        ParcelOpenUMLFeV1 parcel2 = new ParcelOpenUMLFeV1();
        parcel2.setSizeX(20);
        parcel2.setSizeY(30);
        parcel2.setSizeZ(1);
        parcel2.setContent("Umowa");
        parcel2.setCustomerData1("Nr kl. 001234");
        parcel2.setReference("Umowa nr 11"); //parametr opcjonalny
        parcel2.setWeight(0.1);

        pkg.getParcels().add(parcel2);

        umlf.getPackages().add(pkg);

        PackagesGenerationResponseV2 documentGenerationResponse = null;
        try {
            documentGenerationResponse = xmlServices.generatePackagesNumbersV4(umlf, PkgNumsGenerationPolicyV1.IGNORE_ERRORS, "PL", authData);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }

        // interpretacja wyniku
        sessionId = documentGenerationResponse.getSessionId();
        packageId = documentGenerationResponse.getPackages().getPackage().get(0).getPackageId();

        String waybill = documentGenerationResponse.getPackages().getPackage().get(0).getParcels().getParcel().get(1).getWaybill();
        String umlfStatus = documentGenerationResponse.getStatus(); // status całej sesji

        System.out.println("Status sesji: " + umlfStatus);
        // statusy poszczególnych

        for (PackagePGRV2 pkgs : documentGenerationResponse.getPackages().getPackage()) {

        }


        for (PackagePGRV2 pkgs : documentGenerationResponse.getPackages().getPackage()) {
            System.out.println("Status package: " + pkgs.getStatus());
            for (ParcelPGRV2 parcel : pkgs.getParcels().getParcel()) {
                System.out.println("Status parcel: " + parcel.getStatus());
            }
        }
    }

    private void findPostalCode(AuthDataV1 authDataV1) {
        PostalCodeV1 postalCodeV1 = new PostalCodeV1();
        postalCodeV1.setCountryCode("PL");
        postalCodeV1.setZipCode("01-864");

        FindPostalCodeResponseV1 postalCodeV11 = null;
        try {
            postalCodeV11 = xmlServices.findPostalCodeV1(postalCodeV1, authDataV1);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }

        System.out.println(postalCodeV11.getStatus());
    }

    private AuthDataV1 getAuthData() {
        AuthDataV1 authDataV1 = new AuthDataV1();
        authDataV1.setLogin(login);
        authDataV1.setPassword(password);
        authDataV1.setMasterFid(fid);
        return authDataV1;
    }
}
