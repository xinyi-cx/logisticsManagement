drop table if exists user_fid_rel;
CREATE TABLE user_fid_rel
(
    id            bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    user_id       bigint(20) not null auto_increment comment '用户ID',
    user_name     varchar(30)  default null comment '用户账号',
    customer_name varchar(30)  default null comment '用户账号',
    nick_name     varchar(30)  default null comment '用户昵称',
    country       varchar(100) default NULL comment '国家',
    fid           bigint(20) DEFAULT NULL comment 'fid',
    fid_common    varchar(200) DEFAULT NULL comment 'fid备注',
    remark        varchar(200) DEFAULT NULL comment '备注1',
    remark2       varchar(200) DEFAULT NULL comment '备注2',
    is_active     tinyint(1) DEFAULT '0' comment '是否激活',
    create_by     varchar(64)  default '' comment '创建者',
    create_time   datetime comment '创建时间',
    update_by     varchar(64)  default '' comment '更新者',
    update_time   datetime comment '更新时间',
    PRIMARY KEY (id)
) engine = innodb
  CHARSET = utf8
  auto_increment = 1 comment = '用户fid对应关系';



alter table sys_user
    add column customer_name varchar(30)  default null comment '用户账号';

