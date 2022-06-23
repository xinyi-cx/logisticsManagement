drop table if exists dpd_msg;
create table dpd_msg
(
    id          bigint(20) not null auto_increment,
    status      varchar(64)  default '',
    msg         TEXT         default null comment '信息',
    is_delete   tinyint(1)   DEFAULT '0' comment '是否删除',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine = innodb
  CHARSET = utf8
  auto_increment = 1 comment = 'DPD返回值';