drop table if exists sync_history_dpd;
create table sync_history_dpd
(
    id          bigint(20) not null auto_increment comment '主键',
    bat_id      bigint(20) default null comment '批量任务历史Id',
    message_id varchar(500) default null comment '信息id',
    file_name   varchar(500) not null comment '文件名称',
    sync_status char(1)      default '0' comment '同步状态 1-同步成功 0-同步失败 2-部分成功',
    file_delete_status   char(1)      default '0' comment '文件是否删除 0-未删除 1-已经删除',
    error_msg   varchar(500) default null comment '错误信息',
    sync_time   datetime     default now() comment '同步时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '同步历史记录表';


insert into sys_config values(11, '同步DPD信息路径',         'sys.sync.dpd.path',         'C:/ruoyi/uploadPath/packagePdf/',        'Y', 'admin', sysdate(), '', null, '同步DPD信息路径' );

