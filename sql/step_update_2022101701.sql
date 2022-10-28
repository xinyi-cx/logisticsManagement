alter table batch_task_history add column file_name varchar(250) default null;

insert into sys_dict_type values(10001, '物流状态', 'sys_waybill',   '0', 'admin', sysdate(), '', null, '物流状态列表');

insert into sys_dict_data values(10001, 1,  '未激活',     '0',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '未激活');
insert into sys_dict_data values(10002, 2,  '运输中',     '1',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '运输中');
insert into sys_dict_data values(10003, 3,  '改派',     '2',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '改派');
insert into sys_dict_data values(10004, 4,  '已签收',     '3',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '已签收');
insert into sys_dict_data values(10005, 5,  '已退件',     '4',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '已退件');
insert into sys_dict_data values(10006, 6,  '转寄',     '5',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '转寄');
