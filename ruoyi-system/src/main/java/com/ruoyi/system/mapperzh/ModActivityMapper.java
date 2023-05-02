package com.ruoyi.system.mapperzh;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModActivityMapper {
//ModActivityMapper.MAPPER.from(modTestDriveD2dPreAssign, modTestDrive);
    ModActivityMapper MAPPER = Mappers.getMapper(ModActivityMapper.class);

    ActivityInfo modelDActivityToActivityInfo(ModActivity modActivity);

    ModActivityInfo modActivityToActivityInfo(ModActivity modActivity);

    @Mapping(source = "testDriveD2dPreAssign.modLeadId", target = "leadId")
    @Mapping(source = "testDriveD2dPreAssign.requestServiceTime", target = "requestServiceTime")
    @Mapping(source = "testDriveD2dPreAssign.requestServiceProvince", target = "requestServiceProvince")
    @Mapping(source = "testDriveD2dPreAssign.requestServiceCity", target = "requestServiceCity")
    @Mapping(source = "testDriveD2dPreAssign.requestServiceAddress", target = "requestServiceAddress")
    @Mapping(source = "testDriveVehicle.brandDescriptionZh", target = "brandZh")
    @Mapping(source = "testDriveVehicle.classDescriptionZh", target = "classZh")
    @Mapping(source = "testDriveVehicle.modelDescriptionZh", target = "modelZh")
    ModActivityDTO from(ModTestDriveD2dPreAssign testDriveD2dPreAssign, ModTestDriveVehicle testDriveVehicle);

    @Mapping(source = "modTestDriveD2dPreAssign.modLeadId", target = "leadId")
    @Mapping(source = "modTestDriveD2dPreAssign.requestServiceTime", target = "requestServiceTime")
    @Mapping(source = "modTestDriveD2dPreAssign.requestServiceProvince", target = "requestServiceProvince")
    @Mapping(source = "modTestDriveD2dPreAssign.requestServiceCity", target = "requestServiceCity")
    @Mapping(source = "modTestDriveD2dPreAssign.requestServiceAddress", target = "requestServiceAddress")
    @Mapping(source = "modTestDrive.brandDescriptionZh", target = "brandZh")
    @Mapping(source = "modTestDrive.classDescriptionZh", target = "classZh")
    @Mapping(source = "modTestDrive.modelDescriptionZh", target = "modelZh")
    ModActivityDTO from(ModTestDriveD2dPreAssign modTestDriveD2dPreAssign, ModTestDrive modTestDrive);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "testDriveD2dUpdateTag", ignore = true)
    @Mapping(target = "modTestDriveLeadId", ignore = true)
    @Mapping(target = "modTestDriveId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "cancelReason", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "requestFullAddress", expression = "java(java.lang.String.join(\"\",modTestDriveD2dDTO.getRequestServiceProvince(),modTestDriveD2dDTO.getRequestServiceCity(),modTestDriveD2dDTO.getRequestServiceAddress()))")
    @Mapping(source = "modTestDriveD2dDTO.requestServiceProvince", target = "requestServiceProvince")
    @Mapping(source = "modTestDriveD2dDTO.requestServiceCity", target = "requestServiceCity")
    @Mapping(source = "modTestDriveD2dDTO.requestServiceAddress", target = "requestServiceAddress")
    @Mapping(source = "modTestDriveD2dDTO.requestServiceTime", target = "requestServiceTime")
    @Mapping(source = "testDriveD2dStatus", target = "testDriveStatus")
    @Mapping(source = "modTestDriveD2dDTO.testDriveVehicleId", target = "testDriveVehicleId")
    @Mapping(source = "modTestDriveD2dDTO.leadId", target = "modLeadId")
    @Mapping(source = "modTestDriveD2dDTO.scId", target = "scId")
    ModTestDriveD2dPreAssign updateModTestDriveD2dPreAssign(@MappingTarget ModTestDriveD2dPreAssign testDriveD2dPreAssign,
                                                            ModTestDriveD2dDTO modTestDriveD2dDTO,
                                                            TestDriveD2dStatus testDriveD2dStatus);

}
