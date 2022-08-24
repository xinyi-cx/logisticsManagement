
drop table if exists package_dpd_mapping;
CREATE TABLE `package_dpd_mapping`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `package_field` varchar(200) DEFAULT NULL comment 'package字段',
    `dpd_field`     varchar(200) DEFAULT NULL comment 'dpd字段',
    `import_field`  varchar(200) DEFAULT NULL comment '导入字段',
    `import_name`   varchar(200) DEFAULT NULL comment '导入名字',
    `country_code`  varchar(200) DEFAULT NULL comment '国家代码',
    `table_name`    varchar(200) DEFAULT NULL comment '表名字',
    `back_up_field` varchar(200) DEFAULT NULL comment '备用字段',
    `remark`        varchar(200) DEFAULT NULL comment '备注',
    `create_user`   varchar(50)  DEFAULT NULL comment '创建人',
    `update_user`   varchar(50)  DEFAULT NULL comment '更新人',
    `created_time`  datetime     DEFAULT NULL comment '创建时间',
    `updated_time`  datetime     DEFAULT NULL comment '更新时间',
    `is_delete`     tinyint(1)   DEFAULT '0' comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '面单dpd对应关系';

alter table packages_generation_response add column msg TEXT default null;

INSERT INTO package_dpd_mapping (package_field, dpd_field, import_field, import_name, country_code, table_name, back_up_field, remark, create_user, update_user, created_time, updated_time, is_delete) VALUES ('postalCode', 'ReceiverPostalCode', 'receiverPostalCode', 'Recipient Postal code', 'PL', 'AddressReceiver', null, null, null, null, null, null, 0);


drop table if exists redirect_rel;
CREATE TABLE `redirect_rel`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `old_waybill`  varchar(200) DEFAULT NULL comment '旧物流单号',
    `back_order`   varchar(200) DEFAULT NULL comment '回退单号',
    `new_order`    varchar(200) DEFAULT NULL comment '新的单号',
    `new_waybill`  varchar(200) DEFAULT NULL comment '新物流单号',
    `country_code` varchar(200) DEFAULT NULL comment '国家代码',
    `remark`       varchar(200) DEFAULT NULL comment '备注',
    `create_user`  varchar(50)  DEFAULT NULL comment '创建人',
    `update_user`  varchar(50)  DEFAULT NULL comment '更新人',
    `created_time` datetime     DEFAULT NULL comment '创建时间',
    `updated_time` datetime     DEFAULT NULL comment '更新时间',
    `is_delete`    tinyint(1)   DEFAULT '0' comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '转寄关联关系';