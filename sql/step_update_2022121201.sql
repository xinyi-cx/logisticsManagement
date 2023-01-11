
insert into sys_dict_type values(10002, '物流导入状态', 'sys_waybill_import',   '0', 'admin', sysdate(), '', null, '物流导入状态列表');

insert into sys_dict_data values(10007, 1,  '本地',     '本地',       'sys_waybill_import',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '本地');
insert into sys_dict_data values(10008, 2,  '转寄',     '转寄',       'sys_waybill_import',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '转寄');
insert into sys_dict_data values(10009, 3,  '直发',     '直发',       'sys_waybill_import',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '直发');

insert into sys_dict_data values(10010, 7,  '未查询到物流信息',     '7',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '未查询到物流信息');

alter table mb_msg modify column msg LONGTEXT;


alter table redirect_rel
    add column new_package_id bigint(20) default null;

alter table redirect_rel
    add column old_package_id bigint(20) default null;
