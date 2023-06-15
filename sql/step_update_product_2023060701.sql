drop table if exists product_info;
CREATE TABLE `product_info`
(
    `id`                  bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `product_name`        varchar(200)   DEFAULT NULL comment '产品名称',
    `product_cn_name`     varchar(200)   DEFAULT NULL comment '产品名称',
    `product_type`        varchar(200)   DEFAULT NULL comment '产品类型',
    `product_description` varchar(500)   DEFAULT NULL comment '产品描述',
    `product_key_word`    varchar(500)   DEFAULT NULL comment '产品搜索关键字',
    `product_code`        varchar(200)   DEFAULT NULL comment '产品代码',
    `need_box`            varchar(10)    DEFAULT NULL comment 'need_box',
    price DECIMAL(10,2) DEFAULT NULL comment '价格',
    `size_x`              int(10)        DEFAULT NULL comment '长',
    `size_y`              int(10)        DEFAULT NULL comment '宽',
    `size_z`              int(10)        DEFAULT NULL comment '高',
    `content`             varchar(500)   DEFAULT NULL comment '备注',
    `weight`              decimal(10, 4) DEFAULT NULL comment '重量',
    `country`             varchar(20)    DEFAULT NULL comment '所属国家',
    `remark`              varchar(200)   DEFAULT NULL comment '备注1',
    `remark2`             varchar(200)   DEFAULT NULL comment '备注2',
    `remark3`             varchar(200)   DEFAULT NULL comment '备注3',
    `remark4`             varchar(200)   DEFAULT NULL comment '备注4',
    `remark5`             varchar(200)   DEFAULT NULL comment '备注5',
    `status`              char(1)        default '0' comment '帐号状态（0正常 1停用）',
    `del_flag`            char(1)        default '0' comment '删除标志（0代表存在 2代表删除）',
    `create_by`           varchar(64)    default '' comment '创建者',
    `create_time`         datetime comment '创建时间',
    `update_by`           varchar(64)    default '' comment '更新者',
    `update_time`         datetime comment '更新时间',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '产品信息表';

CREATE INDEX index_product_info_product_code ON product_info (product_code);

-- 产品库存表
drop table if exists product_repertory;
CREATE TABLE `product_repertory`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `product_id`    bigint(20) DEFAULT NULL comment '产品id',
    `product_code`  varchar(200) DEFAULT NULL comment '产品代码',
    `remaining_num` int(10)      DEFAULT NULL comment '剩余数量',
    `country`       varchar(20)  DEFAULT NULL comment '所属国家',
    `remark`        varchar(200) DEFAULT NULL comment '备注1',
    `remark2`       varchar(200) DEFAULT NULL comment '备注2',
    `remark3`       varchar(200) DEFAULT NULL comment '备注3',
    `remark4`       varchar(200) DEFAULT NULL comment '备注4',
    `remark5`       varchar(200) DEFAULT NULL comment '备注5',
    `create_by`     varchar(64)  default '' comment '创建者',
    `create_time`   datetime comment '创建时间',
    `update_by`     varchar(64)  default '' comment '更新者',
    `update_time`   datetime comment '更新时间',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '产品库存表';

CREATE INDEX index_product_repertory_product_code ON product_repertory (product_code);
CREATE INDEX index_product_repertory_product_id ON product_repertory (product_id);

-- 产品库存变更历史表
drop table if exists product_repertory_history;
CREATE TABLE `product_repertory_history`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `product_id`    bigint(20) DEFAULT NULL comment '产品id',
    `product_code`  varchar(200) DEFAULT NULL comment '产品代码',
    `remaining_num` int(10)      DEFAULT NULL comment '剩余数量',
    `before_num`    int(10)      DEFAULT NULL comment '剩余数量',
    `change_type`   varchar(20)  DEFAULT NULL comment '变更类型：加减库存/下单/退货',
    `country`       varchar(20)  DEFAULT NULL comment '所属国家',
    `remark`        varchar(200) DEFAULT NULL comment '备注1',
    `remark2`       varchar(200) DEFAULT NULL comment '备注2',
    `remark3`       varchar(200) DEFAULT NULL comment '备注3',
    `remark4`       varchar(200) DEFAULT NULL comment '备注4',
    `remark5`       varchar(200) DEFAULT NULL comment '备注5',
    `create_by`     varchar(64)  default '' comment '创建者',
    `create_time`   datetime comment '创建时间',
    `update_by`     varchar(64)  default '' comment '更新者',
    `update_time`   datetime comment '更新时间',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = '产品库存表';

CREATE INDEX index_product_repertory_history_product_code ON product_repertory_history (product_code);
CREATE INDEX index_product_repertory_history_product_id ON product_repertory_history (product_id);



