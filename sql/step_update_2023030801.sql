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


CREATE INDEX index_redirect_rel_new_package_id ON redirect_rel (new_package_id);

CREATE INDEX index_pack_rel_local_old_package_id ON pack_rel_local (old_package_id);

