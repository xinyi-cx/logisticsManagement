create table test_flyway
(
    id          bigint(20) not null auto_increment comment '主键',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '测试表';
