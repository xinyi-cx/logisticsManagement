drop table if exists cod_report;
CREATE TABLE `cod_report`
(
    `id`                            bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `document_file_id`              bigint(20)   DEFAULT NULL comment 'document表id',
    `pack_id`                       bigint(20)   DEFAULT NULL comment 'package表id',
    `batch_id`                      bigint(20)   DEFAULT NULL comment 'batch_id',
    `logic_id`                      bigint(20)   DEFAULT NULL comment 'logic_id',
    `client`                        varchar(200) DEFAULT NULL comment 'CLIENT',
    `country`                       varchar(200) DEFAULT NULL comment 'Country',
    `import_type`                   varchar(200) DEFAULT NULL comment 'Type',
    `loginId`                       varchar(200) DEFAULT NULL comment 'LoginID',

    `nr_klienta`                    varchar(200) DEFAULT NULL comment 'nr_klienta',
    `download_recipient`            varchar(200) DEFAULT NULL comment '下载收件人',
    `waybill`                       varchar(200) DEFAULT NULL comment '运单号码（货件）',
    `waybill_posting_date`          varchar(200) DEFAULT NULL comment '运单（过账）日期',
    `cash_on_delivery`              varchar(200) DEFAULT NULL comment '货到付款金额 （货到付款）',
    `goods_effective_delivery_date` varchar(200) DEFAULT NULL comment '货件有效交付日期',
    `addressee`                     varchar(200) DEFAULT NULL comment '货件收件人',
    `postal_code`                   varchar(200) DEFAULT NULL comment '邮政编码',
    `area`                          varchar(200) DEFAULT NULL comment '地区',
    `street`                        varchar(200) DEFAULT NULL comment '街',
    `unisoft_Client_content_field`  varchar(500) DEFAULT NULL comment 'Unisoft-Client 中的内容字段',
    `batch_transmission`            varchar(200) DEFAULT NULL comment '批量传输',
    `collective_transfer_date`      varchar(200) DEFAULT NULL comment '集体移送日期',
    `settlement_number`             varchar(200) DEFAULT NULL comment '结算编号 （怀俄明州）',

    `sys_actived_date`              varchar(200) DEFAULT NULL comment '激活时间',
    `sys_last_status_date`          varchar(200) DEFAULT NULL comment '最近物流时间',
    `sys_sign_date`                 varchar(200) DEFAULT NULL comment '签收时间',

    `remark`                        varchar(200) DEFAULT NULL comment '备注1',
    `remark2`                       varchar(200) DEFAULT NULL comment '备注2',
    `remark3`                       varchar(200) DEFAULT NULL comment '备注3',
    `remark4`                       varchar(200) DEFAULT NULL comment '备注4',
    `remark5`                       varchar(200) DEFAULT NULL comment '备注5',
    `is_delete`                     tinyint(1)   DEFAULT '0' comment '是否删除',
    `create_by`                     varchar(64)  default '' comment '创建者',
    `create_time`                   datetime comment '创建时间',
    `update_by`                     varchar(64)  default '' comment '更新者',
    `update_time`                   datetime comment '更新时间',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = 'COD报告表';

