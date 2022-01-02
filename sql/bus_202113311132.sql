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
    `create_user`  varchar(50)  DEFAULT NULL comment '创建人',
    `update_user`  varchar(50)  DEFAULT NULL comment '更新人',
    `created_time` datetime     DEFAULT NULL comment '创建时间',
    `updated_time` datetime     DEFAULT NULL comment '更新时间',
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
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '发件人信息';

drop table if exists address_receiver;
CREATE TABLE `address_receiver`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '收件人主键',
    `address`      varchar(500) DEFAULT NULL comment '收件人地址',
    `city`         varchar(100) DEFAULT NULL comment '收件人城市',
    `company`      varchar(100) DEFAULT NULL comment '收件人公司',
    `country_code` varchar(100) DEFAULT NULL comment '收件人国家编码',
    `email`        varchar(100) DEFAULT NULL comment '收件人邮件',
    `name`         varchar(200) DEFAULT NULL comment '收件人姓名',
    `phone`        varchar(100) DEFAULT NULL comment '收件人手机号码',
    `postal_code`  varchar(100) DEFAULT NULL comment '收件人邮政编码',
    `create_user`  varchar(50)  DEFAULT NULL comment '收件人创建人',
    `update_user`  varchar(50)  DEFAULT NULL comment '收件人更新人',
    `created_time` datetime     DEFAULT NULL comment '收件人创建时间',
    `updated_time` datetime     DEFAULT NULL comment '收件人更新时间',
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
    `weight`         int(10)      DEFAULT NULL comment '重量',
    `pack_id`        bigint(20)   DEFAULT NULL comment 'pack_id',
    `waybill`        varchar(100) DEFAULT NULL comment 'waybill 运货单',
    `status`         varchar(100) DEFAULT NULL comment '包裹状态',
    `package_id`     bigint(20)   DEFAULT NULL comment 'package_id',
    `create_user`    varchar(50)  DEFAULT NULL comment '创建人',
    `update_user`    varchar(50)  DEFAULT NULL comment '更新人',
    `created_time`   datetime     DEFAULT NULL comment '创建时间',
    `updated_time`   datetime     DEFAULT NULL comment '更新时间',
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
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '面单返回值';