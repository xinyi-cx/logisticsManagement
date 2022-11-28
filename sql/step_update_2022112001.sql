alter table logistics_info
    add column activation_time varchar(250) default null;

drop table if exists import_logic_content;
CREATE TABLE `import_logic_content`
(
    `id`               bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `document_file_id` bigint(20)   DEFAULT NULL comment 'document表id',
    `pack_id`          bigint(20)   DEFAULT NULL comment 'package表id',
    `batch_id`         bigint(20)   DEFAULT NULL comment 'batch_id',
    `logic_id`         bigint(20)   DEFAULT NULL comment 'logic_id',
    `client`           varchar(200) DEFAULT NULL comment 'CLIENT',
    `country`          varchar(200) DEFAULT NULL comment 'Country',
    `import_type`      varchar(200) DEFAULT NULL comment 'Type',
    `loginId`          varchar(200) DEFAULT NULL comment 'LoginID',
    `new_waybill`      varchar(200) DEFAULT NULL comment 'New waybill',
    `order_number`     varchar(200) DEFAULT NULL comment 'Order number',
    `value_pln_cod`    varchar(200) DEFAULT NULL comment 'value PLN (COD)',
    `weight_kg`        varchar(200) DEFAULT NULL comment 'Weight kg',
    `recipient_name`   varchar(200) DEFAULT NULL comment 'recipient name',
    `recipient_phone`  varchar(200) DEFAULT NULL comment 'Recipient phone',
    `recipient_email`  varchar(200) DEFAULT NULL comment 'Recipient E-mail',
    `description`      varchar(200) DEFAULT NULL comment 'Description',
    `need_box`         varchar(200) DEFAULT NULL comment 'Need box',
    `create_date`      varchar(200) DEFAULT NULL comment 'Create Date',
    `remark`           varchar(200) DEFAULT NULL comment '备注1',
    `remark2`          varchar(200) DEFAULT NULL comment '备注2',
    `remark3`          varchar(200) DEFAULT NULL comment '备注3',
    `remark4`          varchar(200) DEFAULT NULL comment '备注4',
    `remark5`          varchar(200) DEFAULT NULL comment '备注5',
    `actived_date`     varchar(200) DEFAULT NULL comment '激活时间',
    `last_status_date` varchar(200) DEFAULT NULL comment '最近物流时间',
    `status`           varchar(200) DEFAULT NULL comment '状态',
    `new_number`       varchar(200) DEFAULT NULL comment '新单号',
    `return_number`    varchar(200) DEFAULT NULL comment '退件单号',
    `is_delete`        tinyint(1)   DEFAULT '0' comment '是否删除',
    `create_by`        varchar(64)  default '' comment '创建者',
    `create_time`      datetime comment '创建时间',
    `update_by`        varchar(64)  default '' comment '更新者',
    `update_time`      datetime comment '更新时间',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '导入查询物流表';

