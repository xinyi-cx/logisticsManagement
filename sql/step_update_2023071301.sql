
alter table parcel
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';

alter table import_logic_content
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';


alter table mb_parcel
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';

alter table mb_import_logic_content
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';



insert into sys_config values(6, '物流权限-local用户id',         'sys.user.local.userId',         '123',        'Y', 'admin', sysdate(), '', null, '物流权限-直发用户id' );
