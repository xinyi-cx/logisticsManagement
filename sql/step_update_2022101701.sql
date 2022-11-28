alter table batch_task_history add column file_name varchar(250) default null;

insert into sys_dict_type values(10001, '物流状态', 'sys_waybill',   '0', 'admin', sysdate(), '', null, '物流状态列表');

insert into sys_dict_data values(10001, 1,  '未激活',     '0',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '未激活');
insert into sys_dict_data values(10002, 2,  '运输中',     '1',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '运输中');
insert into sys_dict_data values(10003, 3,  '改派',     '2',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '改派');
insert into sys_dict_data values(10004, 4,  '已签收',     '3',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '已签收');
insert into sys_dict_data values(10005, 5,  '已退件',     '4',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '已退件');
insert into sys_dict_data values(10006, 6,  '转寄',     '5',       'sys_waybill',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '转寄');


INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (12001, '已签收', 12, 2, 'info', 'system/info/index/status/3', null, 1, 0, 'C', '0', '0', 'system:info:list', 'message', 'admin', sysdate(), '', null, '物流信息菜单');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (12002, '已退件', 12, 3, 'info', 'system/info/index/status/4', null, 1, 0, 'C', '0', '0', 'system:info:list', 'message', 'admin', sysdate(), '', null, '物流信息菜单');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (12003, '重派件', 12, 4, 'info', 'system/info/index/status/2', null, 1, 0, 'C', '0', '0', 'system:info:list', 'message', 'admin', sysdate(), '', null, '物流信息菜单');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (12004, '进行中', 12, 5, 'info', 'system/info/index/status/1', null, 1, 0, 'C', '0', '0', 'system:info:list', 'message', 'admin', sysdate(), '', null, '物流信息菜单');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (12005, '未激活', 12, 6, 'info', 'system/info/index/status/0', null, 1, 0, 'C', '0', '0', 'system:info:list', 'message', 'admin', sysdate(), '', null, '物流信息菜单');
