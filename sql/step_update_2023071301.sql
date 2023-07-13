
alter table parcel
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';

alter table import_logic_content
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';


alter table mb_parcel
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';

alter table mb_import_logic_content
    add column update_last_flag char(1)  default null comment '更新标志 1-最终更新 0或者空 不是最终更新';
