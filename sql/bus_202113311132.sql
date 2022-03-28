drop table if exists package;
CREATE TABLE `package`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '面单主键',
    `payer_type`   varchar(100) DEFAULT NULL comment '付款人类型',
    `sender_id`    bigint(20)   DEFAULT NULL comment '发件人id',
    `receiver_id`  bigint(20)   DEFAULT NULL comment '收货人id',
    `ref1`         varchar(200) DEFAULT NULL comment '分类1',
    `ref2`         varchar(200) DEFAULT NULL comment '分类2',
    `services_id`  bigint(20)   DEFAULT NULL comment '服务id',
    `phone`        varchar(100) DEFAULT NULL comment '手机号码',
    `postal_code`  varchar(100) DEFAULT NULL comment '邮政编码',
    `batch_id`     bigint(20)   DEFAULT NULL comment '批量id',
    `create_user`  varchar(50)  DEFAULT NULL comment '创建人',
    `update_user`  varchar(50)  DEFAULT NULL comment '更新人',
    `created_time` datetime     DEFAULT NULL comment '创建时间',
    `updated_time` datetime     DEFAULT NULL comment '更新时间',
    `is_delete`    tinyint(1)   DEFAULT '0'  comment '是否删除',
    `download_num`  int(10)      DEFAULT 0 comment '下载次数',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '面单';

drop table if exists address_sender;
CREATE TABLE `address_sender`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '发件人主键',
    `address`      varchar(500) DEFAULT NULL comment '发件人街道？地址',
    `city`         varchar(100) DEFAULT NULL comment '发件人城市',
    `company`      varchar(100) DEFAULT NULL comment '发件人公司',
    `country_code` varchar(100) DEFAULT NULL comment '发件人国家',
    `email`        varchar(100) DEFAULT NULL comment '发件人邮件',
    `fid`          bigint(20)   DEFAULT NULL comment '发件人fid',
    `name`         varchar(200) DEFAULT NULL comment '发件人姓名',
    `phone`        varchar(100) DEFAULT NULL comment '发件人手机号码',
    `postal_code`  varchar(100) DEFAULT NULL comment '发件人邮政编码',
    `create_user`  varchar(50)  DEFAULT NULL comment '发件人创建人',
    `update_user`  varchar(50)  DEFAULT NULL comment '发件人更新人',
    `created_time` datetime     DEFAULT NULL comment '发件人创建时间',
    `updated_time` datetime     DEFAULT NULL comment '发件人更新时间',
    `is_delete`    tinyint(1)   DEFAULT '0'  comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '发件人信息';

INSERT INTO business.address_sender (id, address, city, company, country_code, email, fid, name, phone, postal_code, create_user, update_user, created_time, updated_time, is_delete) VALUES (1, 'admin', '北京', '公司', 'CN', 'ry@163.com', 123456, '管理员', '15888888888', '100000', '1', '1', '2022-01-24 17:43:27', '2022-01-24 17:43:33', 0);

drop table if exists address_receiver;
CREATE TABLE `address_receiver`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '收件人主键',
    `address`      varchar(500)   DEFAULT NULL comment '收件人地址',
    `city`         varchar(100)   DEFAULT NULL comment '收件人城市',
    `company`      varchar(100)   DEFAULT NULL comment '收件人公司',
    `country_code` varchar(100)   DEFAULT NULL comment '收件人国家编码',
    `email`        varchar(100)   DEFAULT NULL comment '收件人邮件',
    `name`         varchar(200)   DEFAULT NULL comment '收件人姓名',
    `phone`        varchar(100)   DEFAULT NULL comment '收件人手机号码',
    `postal_code`  varchar(100)   DEFAULT NULL comment '收件人邮政编码',
    `pln`          decimal(12, 4) DEFAULT NULL comment '货物金额',
    `create_user`  varchar(50)    DEFAULT NULL comment '收件人创建人',
    `update_user`  varchar(50)    DEFAULT NULL comment '收件人更新人',
    `created_time` datetime       DEFAULT NULL comment '收件人创建时间',
    `updated_time` datetime       DEFAULT NULL comment '收件人更新时间',
    `is_delete`    tinyint(1)   DEFAULT '0'  comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '收货人信息';

drop table if exists services;
CREATE TABLE `services`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `cod_amount`   varchar(200) DEFAULT NULL,
    `cod_currency` varchar(200) DEFAULT NULL,
    `create_user`  varchar(50)  DEFAULT NULL,
    `update_user`  varchar(50)  DEFAULT NULL,
    `created_time` datetime     DEFAULT NULL,
    `updated_time` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200;

drop table if exists parcel;
CREATE TABLE `parcel`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `size_x`         int(10)      DEFAULT NULL comment '长',
    `size_y`         int(10)      DEFAULT NULL comment '宽',
    `size_z`         int(10)      DEFAULT NULL comment '高',
    `content`        varchar(500) DEFAULT NULL comment '备注',
    `customer_data1` varchar(500) DEFAULT NULL comment '客户数据1',
    `reference`      varchar(500) DEFAULT NULL comment '内部引用号',
    `weight`         decimal(10, 4)      DEFAULT NULL comment '重量',
    `pack_id`        bigint(20)   DEFAULT NULL comment 'pack_id',
    `waybill`        varchar(100) DEFAULT NULL comment 'waybill 运货单',
    `status`         varchar(100) DEFAULT NULL comment '包裹状态',
    `package_id`     bigint(20)   DEFAULT NULL comment 'package_id',
    `create_user`    varchar(50)  DEFAULT NULL comment '创建人',
    `update_user`    varchar(50)  DEFAULT NULL comment '更新人',
    `created_time`   datetime     DEFAULT NULL comment '创建时间',
    `updated_time`   datetime     DEFAULT NULL comment '更新时间',
    `is_delete`    tinyint(1)   DEFAULT '0'  comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '包裹';

# sessionId 对应多个packageId  一个packageId对应多个parcel
drop table if exists packages_generation_response;
CREATE TABLE `packages_generation_response`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `session_id`    bigint(20)   DEFAULT NULL,
    `package_id`    bigint(20)   DEFAULT NULL,
    `status`        varchar(100) DEFAULT NULL,
    `pkg_reference` varchar(500) DEFAULT NULL,
    `pkg_status`    varchar(100) DEFAULT NULL,
    `pack_id`       bigint(20)   DEFAULT NULL,
    `create_user`   varchar(50)  DEFAULT NULL,
    `update_user`   varchar(50)  DEFAULT NULL,
    `created_time`  datetime     DEFAULT NULL,
    `updated_time`  datetime     DEFAULT NULL,
    `is_delete`    tinyint(1)   DEFAULT '0'  comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '面单返回值';

drop table if exists batch_task_history;
CREATE TABLE `batch_task_history`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `type`          varchar(100) DEFAULT NULL comment '类型',
    `status`        varchar(100) DEFAULT NULL comment '状态',
    `success_num`   int(10)      DEFAULT NULL comment '成功面单数',
    `fail_num`      int(10)      DEFAULT NULL comment '失败面单数',
    `download_num`  int(10)      DEFAULT NULL comment '下载次数',
    `excel_url`     varchar(50)  DEFAULT NULL comment '原始excel路径',
    `excel_content` text         DEFAULT NULL comment 'excel内容',
    `create_user`   varchar(50)  DEFAULT NULL comment '创建人',
    `update_user`   varchar(50)  DEFAULT NULL comment '更新人',
    `created_time`  datetime     DEFAULT NULL comment '创建时间',
    `updated_time`  datetime     DEFAULT NULL comment '更新时间',
    `is_delete`    tinyint(1)   DEFAULT '0'  comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '批量任务历史';

drop table if exists sequence;
CREATE TABLE `sequence` (
  `seq_name` varchar(50) NOT NULL comment '序列名称',
  `current_val` int(11) NOT NULL comment '当前值',
  `increment_val` int(11) NOT NULL comment '跨度',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop FUNCTION if exists currval;
CREATE FUNCTION currval (v_seq_name VARCHAR(50)) RETURNS INTEGER
BEGIN
DECLARE current INTEGER;
SET current = 0;
SELECT
    current_val INTO current
FROM
    sequence
WHERE
    seq_name = v_seq_name;
RETURN current;
END;

drop FUNCTION if exists nextval;
CREATE FUNCTION nextval (v_seq_name VARCHAR(50)) RETURNS INTEGER
BEGIN
DECLARE current INTEGER;
SET current = 0;
SELECT
    current_val + increment_val INTO current
FROM
    sequence
WHERE
    seq_name = v_seq_name FOR UPDATE;
UPDATE sequence
SET current_val = current
WHERE
    seq_name = v_seq_name;
RETURN current;
END;

INSERT INTO sequence (`seq_name`, `current_val`, `increment_val`) VALUES ('send_seq', '1', '1');
INSERT INTO sequence (`seq_name`, `current_val`, `increment_val`) VALUES ('receiver_seq', '1', '1');
INSERT INTO sequence (`seq_name`, `current_val`, `increment_val`) VALUES ('package_seq', '1', '1');
INSERT INTO sequence (`seq_name`, `current_val`, `increment_val`) VALUES ('services_seq', '1', '1');
INSERT INTO sequence (`seq_name`, `current_val`, `increment_val`) VALUES ('pack_gen_seq', '1', '1');

drop table if exists documents;
CREATE TABLE `documents`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `session_id`    bigint(20)   DEFAULT NULL,
    `package_id`    bigint(20)   DEFAULT NULL,
    `document_id`  varchar(100)  DEFAULT NULL,
    `file_name`    varchar(100) DEFAULT NULL comment '文件名称',
    `display_name` varchar(100) DEFAULT NULL comment '文件显示的名称',
    `extension`    varchar(50)  DEFAULT NULL comment '文件的扩展名',
    `file_data`    longblob     DEFAULT NULL comment '文件二进制格式',
    `file_size`    bigint(20)   DEFAULT NULL comment '文件大小',
    `content_type` varchar(500) DEFAULT NULL comment '文件种类',
    `create_user`  varchar(50)  DEFAULT NULL comment '创建人',
    `update_user`  varchar(50)  DEFAULT NULL comment '更新人',
    `created_time` datetime     DEFAULT NULL comment '创建时间',
    `updated_time` datetime     DEFAULT NULL comment '更新时间',
    `is_delete`    tinyint(1)   DEFAULT '0' comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '文件';

drop table if exists redirect_package;
CREATE TABLE `redirect_package`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '转寄面单主键',
    `original_id`  bigint(20)  DEFAULT NULL comment '原面单主键',
    `create_user`  varchar(50) DEFAULT NULL comment '创建人',
    `update_user`  varchar(50) DEFAULT NULL comment '更新人',
    `created_time` datetime    DEFAULT NULL comment '创建时间',
    `updated_time` datetime    DEFAULT NULL comment '更新时间',
    `is_delete`    tinyint(1)  DEFAULT '0' comment '是否删除',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '转寄面单原面单关联关系表';
