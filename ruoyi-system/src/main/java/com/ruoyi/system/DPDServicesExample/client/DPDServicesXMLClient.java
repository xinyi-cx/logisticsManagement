package com.ruoyi.system.DPDServicesExample.client;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.domain.Package;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.dpdservices.*;
import com.ruoyi.system.mapper.DocumentsMapper;
import com.ruoyi.system.mapper.DpdMsgMapper;
import com.ruoyi.system.mapper.SequenceMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

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

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private DocumentsMapper documentsMapper;

    @Autowired
    private DpdMsgMapper dpdMsgMapper;

    private long sessionId;
    private long parcelId;
    private long packageId;

    //    @PostConstruct
    public void runExamples() {
//        Documents document = documentsMapper.selectDocumentsById((long) 119);
//        saveFileToLocal(document);
        findPostalCode("PL", "02274");
        findPostalCode("PL", "64500");
    }

    private void findPostalCode() {
        AuthDataV1 authData = getAuthData();

        generatePackagesNumber(authData);
////        generateSpedLabels2(authData);
        generateSpedLabelsBySessionId(sessionId);
//
//        generateProtocol(authData);

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
            saveFile(packageId, ret);
            System.out.println("test");
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????pdf??????
     *
     * @param paramPackageId
     */
    public DocumentGenerationResponseV1 generateProtocolByPackageId(long paramPackageId) {
        AuthDataV1 authData = getAuthData();
        PackageDSPV1 pkg5 = new PackageDSPV1();
        pkg5.setPackageId(paramPackageId);

        SessionDSPV1 session = new SessionDSPV1();
        session.getPackages().add(pkg5);
        session.setSessionType(SessionTypeDSPEnumV1.DOMESTIC);

        DpdServicesParamsV1 param = new DpdServicesParamsV1();
        param.setPolicy(PolicyDSPEnumV1.STOP_ON_FIRST_ERROR);
        param.setSession(session);
        DocumentGenerationResponseV1 ret = new DocumentGenerationResponseV1();
        try {
            ret = xmlServices.generateProtocolV1(param, OutputDocFormatDSPEnumV1.PDF, OutputDocPageFormatDSPEnumV1.LBL_PRINTER, authData);
            saveFile(paramPackageId, ret);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public DocumentGenerationResponseV1 generateSpedLabelsBySessionId(long sessionId) {
        AuthDataV1 authData = getAuthData();
        SessionDSPV1 session = new SessionDSPV1();
        DpdServicesParamsV1 param = new DpdServicesParamsV1();
        param.setPolicy(PolicyDSPEnumV1.STOP_ON_FIRST_ERROR);
//        param.setDocumentId();
        // Na podstawie sessionId
        session.setSessionId(sessionId);
        session.setSessionType(SessionTypeDSPEnumV1.DOMESTIC);
        param.setSession(session);

        DocumentGenerationResponseV1 ret = new DocumentGenerationResponseV1();
        try {
            ret = xmlServices.generateSpedLabelsV4(param, OutputDocFormatDSPEnumV1.PDF, OutputDocPageFormatDSPEnumV1.LBL_PRINTER, OutputLabelTypeEnumV1.BIC_3, "", authData);
            saveFile(sessionId, ret);
            System.out.println("test");
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void generateSpedLabels2(long parcelIdT, long sessionIdT) {
        AuthDataV1 authData = getAuthData();
//        245977011 0000000906700Q
        DpdServicesParamsV2 paramsV2 = new DpdServicesParamsV2();
        paramsV2.setPolicy(PolicyDSPEnumV2.STOP_ON_FIRST_ERROR);
        SessionDSPV2 sessionDSPV2 = new SessionDSPV2();

        PackageDSPV2 packageDSPV2 = new PackageDSPV2();
        ParcelDSPV2 parcelDSPV2 = new ParcelDSPV2();
        parcelDSPV2.setParcelId(parcelIdT);
        PackageDSPV2.Parcels parcels = new PackageDSPV2.Parcels();
        parcels.getParcel().add(parcelDSPV2);
        packageDSPV2.setParcels(parcels);
        SessionDSPV2.Packages packages = new SessionDSPV2.Packages();
        packages.getPackage().add(packageDSPV2);
        sessionDSPV2.setPackages(packages);
        sessionDSPV2.setSessionId(sessionIdT);
        paramsV2.setSession(sessionDSPV2);
        paramsV2.setGenProtForNonMatching(false);
        try {
            DocumentGenerationResponseV2 ret = xmlServices.generateProtocolsWithDestinationsV2(paramsV2, authData);
            System.out.println("test");
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }

    }

    private void saveFile(Long id, DocumentGenerationResponseV1 ret) {
        Documents documentsInsert = new Documents();
        documentsInsert.setId(sequenceMapper.selectNextvalByName("doc_seq"));
        documentsInsert.setPackageId(id);
        documentsInsert.setFileData(ret.getDocumentData());
        documentsInsert.setDocumentId(ret.getDocumentId());
        documentsInsert.setExtension("PDF");
        documentsInsert.setContentType("application/pdf");
        documentsInsert.setFileName("file");
        documentsInsert.setDisplayName(id.toString() + ".pdf");
        documentsMapper.insertDocuments(documentsInsert);

        saveFileToLocal(documentsInsert);
    }

    private static final Logger log = LoggerFactory.getLogger(DPDServicesXMLClient.class);

    /**
     * ???????????????
     *
     * @param packages
     * @return
     */
    public List<PackagesGenerationResponse> generatePackagesNumberByBusiness(List<Package> packages) throws Exception {
        Integer methodFid = null;
        OpenUMLFeV3 umlf = new OpenUMLFeV3(); // Ilo???? przesy??ek
        for (Package aPackage : packages) {
            AddressReceiver receiver = aPackage.getReceiver();
            AddressSender sender = aPackage.getSender();
            Services ser = aPackage.getService();
            List<Parcel> parcels = aPackage.getParcels();

            PackageOpenUMLFeV3 pkg = new PackageOpenUMLFeV3();
            if (ObjectUtils.isEmpty(aPackage.getPayerType())) {
                aPackage.setPayerType("SENDER");
            }
            pkg.setPayerType(PayerTypeEnumOpenUMLFeV1.fromValue(aPackage.getPayerType()));

            PackageAddressOpenUMLFeV1 addressSender = new PackageAddressOpenUMLFeV1();
            addressSender.setAddress(sender.getAddress());
            addressSender.setCity(sender.getCity());
            addressSender.setCompany(sender.getCompany());
            addressSender.setCountryCode(sender.getCountryCode());
            addressSender.setEmail(sender.getEmail());
            addressSender.setFid(Integer.valueOf(sender.getFid().toString()));
            methodFid = Integer.valueOf(sender.getFid().toString());
            addressSender.setName(sender.getName());
            addressSender.setPhone(sender.getPhone());
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
//            if (ObjectUtils.isEmpty(ser.getCodCurrency())) {
            //?????????PLN????????????
            ser.setCodCurrency("PLN");
//            }
            cod.setCurrency(ServiceCurrencyEnum.fromValue(ser.getCodCurrency()));
            services.setCod(cod);

            ServiceRODOpenUMLFeV1 rod = new ServiceRODOpenUMLFeV1();
            services.setRod(rod);

            pkg.setServices(services);

            for (Parcel parcel : parcels) {
                ParcelOpenUMLFeV1 parcel1 = new ParcelOpenUMLFeV1();
                parcel1.setSizeX(null == parcel.getSizeX() || 0 == parcel.getSizeX() ? 1 : parcel.getSizeX());
                parcel1.setSizeY(null == parcel.getSizeY() || 0 == parcel.getSizeY() ? 1 : parcel.getSizeY());
                parcel1.setSizeZ(null == parcel.getSizeZ() || 0 == parcel.getSizeZ() ? 1 : parcel.getSizeZ());
//                if (ObjectUtils.isEmpty(parcel.getContent())) {
//                    String uuid = IdUtils.fastSimpleUUID();
//                    parcel.setContent(uuid);
//                }
                if (ObjectUtils.isEmpty(parcel.getCustomerData1())) {
                    String uuid = IdUtils.fastSimpleUUID();
                    parcel.setCustomerData1(uuid);
                }
                if (ObjectUtils.isEmpty(parcel.getReference())) {
                    String uuid = IdUtils.fastSimpleUUID();
                    parcel.setReference(uuid);
                }
                parcel1.setContent(parcel.getReference());//id
                parcel1.setCustomerData1(parcel.getCustomerData1());//id
//                parcel1.setReference(parcel.getReference()); //parametr opcjonalny
                parcel1.setWeight((0 == Double.parseDouble(parcel.getWeight().toString())) ? 1 : Double.parseDouble(parcel.getWeight().toString()));
                pkg.getParcels().add(parcel1);
            }
            umlf.getPackages().add(pkg);
        }
        AuthDataV1 authData = getAuthDataByFid(methodFid);
        PackagesGenerationResponseV2 documentGenerationResponse = null;
        try {
            log.info("gen DPD" + JSONObject.toJSONString(umlf));
            //???????????????
            documentGenerationResponse = xmlServices.generatePackagesNumbersV4(umlf, PkgNumsGenerationPolicyV1.IGNORE_ERRORS, "PL", authData);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }

        DpdMsg dpdMsg = new DpdMsg();
        dpdMsg.setStatus(documentGenerationResponse.getStatus());
        dpdMsg.setMsg(JSONObject.toJSONString(documentGenerationResponse));
        dpdMsgMapper.insertDpdMsg(dpdMsg);

        if (ObjectUtils.isEmpty(documentGenerationResponse.getSessionId())) {
            //???????????????
            log.error("DPD DATA ERROR: " + documentGenerationResponse.getStatus());
            throw new Exception(documentGenerationResponse.getStatus());
        }

        DocumentGenerationResponseV1 ret = generateSpedLabelsBySessionId(documentGenerationResponse.getSessionId());

        Sequence sequence = sequenceMapper.selectSequenceBySeqName("doc_seq");
        Long currentVal = sequence.getCurrentVal();
        sequence.setCurrentVal(sequence.getCurrentVal() + (packages.size() + 1) * sequence.getIncrementVal());
        sequenceMapper.updateSequence(sequence);

        List<Documents> documentsList = new ArrayList<>();
        Documents documentsSession = new Documents();
        documentsSession.setId(++currentVal);
        documentsSession.setSessionId(documentGenerationResponse.getSessionId());
        documentsSession.setFileData(ret.getDocumentData());
        documentsSession.setDocumentId(ret.getDocumentId());
        documentsSession.setExtension("PDF");
        documentsSession.setContentType("application/pdf");
        documentsSession.setFileName("file");
        documentsSession.setDisplayName(documentGenerationResponse.getSessionId().toString() + ".pdf");
        documentsSession.setCreateUser(packages.get(0).getCreateUser());
        documentsSession.setUpdateUser(packages.get(0).getUpdateUser());
        documentsList.add(documentsSession);

        //??????sessionId??????pdf ???????????????pack???
        List<PackagesGenerationResponse> returnResponses = new ArrayList<>();
        List<PackagePGRV2> packagePGRV2s = documentGenerationResponse.getPackages().getPackage();
        Map<String, Sequence> nameMap = getSeqMap(packages.size());
        for (int i = 0; i < packages.size(); i++) {
            PackagesGenerationResponse packagesGenerationResponse = new PackagesGenerationResponse();
            packagesGenerationResponse.setId(getId(nameMap, "pack_gen_seq"));
            packagesGenerationResponse.setCreateUser(packages.get(0).getCreateUser());
            packagesGenerationResponse.setUpdateUser(packages.get(0).getUpdateUser());
            packagesGenerationResponse.setSessionId(documentGenerationResponse.getSessionId());
            packagesGenerationResponse.setStatus(documentGenerationResponse.getStatus());
            mapResult(packagePGRV2s.get(i), packages.get(i), packagesGenerationResponse);
            returnResponses.add(packagesGenerationResponse);

            Documents documentsOne = new Documents();
            documentsOne.setId(++currentVal);
            documentsOne.setSessionId(packagesGenerationResponse.getSessionId());
            documentsOne.setPackageId(packagesGenerationResponse.getPackageId());
            documentsOne.setFileData(getOnePagePDFFile(documentsSession, i + 1));
            documentsOne.setDocumentId(ret.getDocumentId());
            documentsOne.setExtension("PDF");
            documentsOne.setContentType("application/pdf");
            documentsOne.setFileName("file");
            documentsOne.setDisplayName(packagesGenerationResponse.getPackageId().toString() + ".pdf");
            documentsOne.setCreateUser(packages.get(0).getCreateUser());
            documentsOne.setUpdateUser(packages.get(0).getUpdateUser());
            documentsList.add(documentsOne);
        }
        for (Documents documents : documentsList) {
            saveFileToLocal(documents);
        }
        documentsMapper.batchInsert(documentsList);
        return returnResponses;
    }

    private byte[] getOnePagePDFFile(Documents documents, int pageNum) {
        Document document = null;
        PdfCopy copy = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfReader reader = new PdfReader(documents.getFileData());
            int n = reader.getNumberOfPages();
            if (pageNum > n) {
                pageNum = n;
            }
            document = new Document(reader.getPageSize(1));

            copy = new PdfCopy(document, byteArrayOutputStream);
            document.open();

            document.newPage();
            PdfImportedPage page = copy.getImportedPage(reader, pageNum);
            copy.addPage(page);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
            copy.close();
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Value("${frontPath}")
    private String frontPath;

    private void saveFileToLocal(Documents document) {
        //?????????????????????
        FileOutputStream fileOutputStream = null;
        try {
            String path = frontPath;
            //????????????
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            fileOutputStream = new FileOutputStream(path + document.getDisplayName());
            fileOutputStream.write(document.getFileData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] SEQ_NAMES = {"pack_gen_seq"};

    private Map<String, Sequence> getSeqMap(int addNum) {
        List<Sequence> sequences = sequenceMapper.selectSequenceList(new Sequence());
        Map<String, Sequence> nameMap = sequences.stream().collect(toMap(Sequence::getSeqName, Function.identity()));
        for (String seqName : SEQ_NAMES) {
            if (nameMap.containsKey(seqName)) {
                Sequence sequence = new Sequence();
                BeanUtils.copyProperties(nameMap.get(seqName), sequence);
                sequence.setCurrentVal(sequence.getCurrentVal() + addNum * sequence.getIncrementVal());
                sequenceMapper.updateSequence(sequence);
            }
        }
        return nameMap;
    }

    private Long getId(Map<String, Sequence> nameMap, String seqName) {
        Sequence sequence = nameMap.get(seqName);
        Long id = sequence.getCurrentVal() + sequence.getIncrementVal();
        sequence.setCurrentVal(id);
        return id;
    }

    /**
     * ???????????????
     *
     * @param sourse
     * @param targetPackage
     * @param targetPackagesGenerationResponse
     */
    private void mapResult(PackagePGRV2 sourse, Package targetPackage, PackagesGenerationResponse targetPackagesGenerationResponse) {
        // ?????? PackagesGenerationResponse
        targetPackagesGenerationResponse.setPackageId(sourse.getPackageId());
        targetPackagesGenerationResponse.setPackId(targetPackage.getId());
        targetPackagesGenerationResponse.setPkgReference(sourse.getReference());
        targetPackagesGenerationResponse.setPkgStatus(sourse.getStatus());

        // ?????? Package
        List<Parcel> parcels = targetPackage.getParcels();
        List<ParcelPGRV2> parcelPGRV2s = sourse.getParcels().getParcel();
        for (int i = 0; i < parcels.size(); i++) {
            Parcel parcel = parcels.get(i);
            ParcelPGRV2 parcelPGRV2 = parcelPGRV2s.get(i);
            parcel.setWaybill(parcelPGRV2.getWaybill());
            parcel.setStatus(parcelPGRV2.getStatus());
            parcel.setPackageId(sourse.getPackageId());
            parcel.setParcelId(parcelPGRV2.getParcelId());
        }

    }

    private void generatePackagesNumber(AuthDataV1 authData) {
        OpenUMLFeV3 umlf = new OpenUMLFeV3(); // Ilo???? przesy??ek
        for (int i = 0; i < 2; i++) {
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
            addressReceiver.setAddress("ul. Brzezi??ska 59");
            addressReceiver.setCity("Mys??owice");
            addressReceiver.setCompany("Oddzia?? Regionalny w Katowicach");
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
            parcel1.setContent(IdUtils.fastSimpleUUID());
            parcel1.setCustomerData1(IdUtils.fastSimpleUUID());
            parcel1.setReference(IdUtils.fastSimpleUUID()); //parametr opcjonalny
            parcel1.setWeight(0.3);

            pkg.getParcels().add(parcel1);

            ParcelOpenUMLFeV1 parcel2 = new ParcelOpenUMLFeV1();
            parcel2.setSizeX(20);
            parcel2.setSizeY(30);
            parcel2.setSizeZ(1);
            parcel2.setContent(IdUtils.fastSimpleUUID());
            parcel2.setCustomerData1(IdUtils.fastSimpleUUID());
            parcel2.setReference(IdUtils.fastSimpleUUID()); //parametr opcjonalny
            parcel2.setWeight(0.1);

            pkg.getParcels().add(parcel2);

            umlf.getPackages().add(pkg);
        }

        PackagesGenerationResponseV2 documentGenerationResponse = null;
        try {
            documentGenerationResponse = xmlServices.generatePackagesNumbersV4(umlf, PkgNumsGenerationPolicyV1.IGNORE_ERRORS, "PL", authData);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }

        // interpretacja wyniku
        sessionId = documentGenerationResponse.getSessionId();
        packageId = documentGenerationResponse.getPackages().getPackage().get(0).getPackageId();
//        245977011 0000000906700Q
        //????????????
        String waybill = documentGenerationResponse.getPackages().getPackage().get(0).getParcels().getParcel().get(1).getWaybill();
        parcelId = documentGenerationResponse.getPackages().getPackage().get(0).getParcels().getParcel().get(1).getParcelId();
        String umlfStatus = documentGenerationResponse.getStatus(); // status ca??ej sesji

        System.out.println("Status sesji: " + umlfStatus);
        // statusy poszczeg??lnych

        for (PackagePGRV2 pkgs : documentGenerationResponse.getPackages().getPackage()) {

        }


        for (PackagePGRV2 pkgs : documentGenerationResponse.getPackages().getPackage()) {
            System.out.println("Status package: " + pkgs.getStatus());
            for (ParcelPGRV2 parcel : pkgs.getParcels().getParcel()) {
                System.out.println("Status parcel: " + parcel.getStatus());
            }
        }
    }

    public String findPostalCode(String countryCode, String zipCode) {
        AuthDataV1 authData = getAuthData();
        PostalCodeV1 postalCodeV1 = new PostalCodeV1();
        postalCodeV1.setCountryCode(countryCode);
        postalCodeV1.setZipCode(zipCode);

        FindPostalCodeResponseV1 postalCodeV11 = null;
        try {
            postalCodeV11 = xmlServices.findPostalCodeV1(postalCodeV1, authData);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }
//        OK ??? post code is correct
//??? NONEXISTING_POSTAL_CODE ??? post code does not exist
//??? NONEXISTING_COUNTRY_CODE ??? country code does not exist
//??? WRONG_POSTAL_PATTERN ??? in the event the given parameters are wrong
        return postalCodeV11.getStatus();

    }

    public String checkPostalCode(String countryCode, String zipCode) {
        AuthDataV1 authDataV1 = getAuthData();
        PostalCodeV1 postalCodeV1 = new PostalCodeV1();
        postalCodeV1.setCountryCode(countryCode);
        postalCodeV1.setZipCode(zipCode);

        FindPostalCodeResponseV1 postalCodeV11 = null;
        try {
            postalCodeV11 = xmlServices.findPostalCodeV1(postalCodeV1, authDataV1);
        } catch (DPDServiceException_Exception e) {
            e.printStackTrace();
        }
        return postalCodeV11.getStatus();
    }

    private AuthDataV1 getAuthData() {
        AuthDataV1 authDataV1 = new AuthDataV1();
        authDataV1.setLogin(login);
        authDataV1.setPassword(password);
        authDataV1.setMasterFid(fid);
        return authDataV1;
    }

    private AuthDataV1 getAuthDataByFid(Integer fid) {
        AuthDataV1 authDataV1 = new AuthDataV1();
        authDataV1.setLogin(login);
        authDataV1.setPassword(password);
        authDataV1.setMasterFid(fid);
        return authDataV1;
    }
}
