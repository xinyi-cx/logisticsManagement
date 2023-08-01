drop table if exists sync_dpd_history;
create table sync_dpd_history
(
    id          bigint(20) not null auto_increment comment '用户ID',
    bat_id      bigint(20) default null comment '批量任务历史Id',
    message_id varchar(500) default null comment '信息id',
    file_name   varchar(500) not null comment '文件名称',
    sync_status char(1)      default '0' comment '同步状态 1-同步成功 0-同步失败 2-部分成功',
    file_delete_status   char(1)      default '0' comment '文件是否删除 0-未删除 1-已经删除',
    error_msg   varchar(500) default null comment '错误信息',
    sync_time   datetime     default sysdate() comment '同步时间',
    remark      varchar(500) default null comment '备注',
    primary key (user_id)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '同步历史记录表';


insert into sys_config values(11, '同步DPD信息路径-local-pl',         'sys.sync.dpd.local.pl.path',         'C:/ruoyi/uploadPath/local/pl',        'Y', 'admin', sysdate(), '', null, '同步DPD信息路径 local-pl' );
insert into sys_config values(12, '同步DPD信息路径-local-cz',         'sys.sync.dpd.local.cz.path',        'C:/ruoyi/uploadPath/local/cz',       'Y', 'admin', sysdate(), '', null, '同步DPD信息路径 local-cz' );
insert into sys_config values(13, '同步DPD信息路径-转寄-pl',         'sys.sync.dpd.ref.pl.path',         'C:/ruoyi/uploadPath/ref/pl',      'Y', 'admin', sysdate(), '', null, '同步DPD信息路径 转寄-pl' );
insert into sys_config values(14, '同步DPD信息路径-转寄-cz',         'sys.sync.dpd.ref.cz.path',         'C:/ruoyi/uploadPath/ref/cz',       'Y', 'admin', sysdate(), '', null, '同步DPD信息路径 转寄-cz' );
insert into sys_config values(15, '同步DPD信息路径-面单-pl',         'sys.sync.dpd.original.pl.path',       'C:/ruoyi/uploadPath/original/pl',         'Y', 'admin', sysdate(), '', null, '同步DPD信息路径 面单-pl' );
insert into sys_config values(16, '同步DPD信息路径-面单-cz',         'sys.sync.dpd.original.cz.path',         'C:/ruoyi/uploadPath/original/cz',         'Y', 'admin', sysdate(), '', null, '同步DPD信息路径 面单-cz' );

