drop table if exists pack_rel_local;
CREATE TABLE `pack_rel_local`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `old_package_id` bigint(20)   DEFAULT NULL comment '旧物流单号',
    `code1`          varchar(200) DEFAULT NULL comment 'code1',
    `code2`          varchar(200) DEFAULT NULL comment 'code2',
    `code3`          varchar(200) DEFAULT NULL comment 'code2',
    `country_code`   varchar(200) DEFAULT NULL comment '国家代码',
    `remark`         varchar(200) DEFAULT NULL comment '备注',
    `create_user`    varchar(50)  DEFAULT NULL comment '创建人',
    `update_user`    varchar(50)  DEFAULT NULL comment '更新人',
    `created_time`   datetime     DEFAULT NULL comment '创建时间',
    `updated_time`   datetime     DEFAULT NULL comment '更新时间',
    `is_delete`      tinyint(1)   DEFAULT '0' comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '本地关联关系';


CREATE TABLE `import_logic_content_backup` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `document_file_id` bigint(20) DEFAULT NULL COMMENT 'document表id',
  `pack_id` bigint(20) DEFAULT NULL COMMENT 'package表id',
  `batch_id` bigint(20) DEFAULT NULL COMMENT 'batch_id',
  `logic_id` bigint(20) DEFAULT NULL COMMENT 'logic_id',
  `client` varchar(200) DEFAULT NULL COMMENT 'CLIENT',
  `country` varchar(200) DEFAULT NULL COMMENT 'Country',
  `import_type` varchar(200) DEFAULT NULL COMMENT 'Type',
  `loginId` varchar(200) DEFAULT NULL COMMENT 'LoginID',
  `new_waybill` varchar(200) DEFAULT NULL COMMENT 'New waybill',
  `order_number` varchar(200) DEFAULT NULL COMMENT 'Order number',
  `value_pln_cod` varchar(200) DEFAULT NULL COMMENT 'value PLN (COD)',
  `weight_kg` varchar(200) DEFAULT NULL COMMENT 'Weight kg',
  `recipient_name` varchar(200) DEFAULT NULL COMMENT 'recipient name',
  `recipient_phone` varchar(200) DEFAULT NULL COMMENT 'Recipient phone',
  `recipient_email` varchar(200) DEFAULT NULL COMMENT 'Recipient E-mail',
  `description` varchar(200) DEFAULT NULL COMMENT 'Description',
  `need_box` varchar(200) DEFAULT NULL COMMENT 'Need box',
  `create_date` varchar(200) DEFAULT NULL COMMENT 'Create Date',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注1',
  `remark2` varchar(200) DEFAULT NULL COMMENT '备注2',
  `remark3` varchar(200) DEFAULT NULL COMMENT '备注3',
  `remark4` varchar(200) DEFAULT NULL COMMENT '备注4',
  `remark5` varchar(200) DEFAULT NULL COMMENT '备注5',
  `actived_date` varchar(200) DEFAULT NULL COMMENT '激活时间',
  `last_status_date` varchar(200) DEFAULT NULL COMMENT '最近物流时间',
  `status` varchar(200) DEFAULT NULL COMMENT '状态',
  `new_number` varchar(200) DEFAULT NULL COMMENT '新单号',
  `return_number` varchar(200) DEFAULT NULL COMMENT '退件单号',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_import_logic_content_new_waybill` (`new_waybill`),
  KEY `index_import_logic_content_document_file_id` (`document_file_id`),
  KEY `index_import_logic_content_document_pack_id` (`pack_id`),
  KEY `index_import_logic_content_document_batch_id` (`batch_id`),
  KEY `index_import_logic_content_document_document_file_id` (`document_file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=182709 DEFAULT CHARSET=utf8 COMMENT='导入查询物流表';

alter table package
    add column parcel_waybill varchar(100)  default null comment '物流单号';

alter table package
    add column qty_packages varchar(100)  default null comment 'Qty packages';

alter table package
    add column parcel_reference varchar(500)  default null comment '内部引用号';

