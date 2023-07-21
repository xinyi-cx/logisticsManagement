
alter table parcel
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';

alter table import_logic_content
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';


alter table mb_parcel
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';

alter table mb_import_logic_content
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';



insert into sys_config values(6, '物流权限-local用户id',         'sys.user.local.userId',         '123',        'Y', 'admin', sysdate(), '', null, '物流权限-本地用户id' );
insert into sys_config values(7, '物流权限-zf用户id',         'sys.user.zf.userId',         '123',        'Y', 'admin', sysdate(), '', null, '物流权限-直发用户id' );
insert into sys_config values(8, '物流权限-zj用户id',         'sys.user.zj.userId',         '123',        'Y', 'admin', sysdate(), '', null, '物流权限-转寄用户id' );

insert into sys_config values(9, '邮箱-接收邮箱号',         'sys.mail.to',         '1097700731@qq.com,caixin0830@163.com',        'Y', 'admin', sysdate(), '', null, '邮箱-接收邮箱号 多个邮箱号英文逗号隔开' );
insert into sys_config values(10, '邮箱-抄送邮箱号',         'sys.mail.cc',         '1097700731@qq.com,caixin0830@163.com',        'Y', 'admin', sysdate(), '', null, '邮箱-抄送邮箱号 多个邮箱号英文逗号隔开' );
