-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('user deliver infomation table', '3', '1', 'userdeliverinfo', 'system/userdeliverinfo/index', 1, 0, 'C', '0', '0', 'system:userdeliverinfo:list', '#', 'admin', sysdate(), '', null, 'user deliver infomation table菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('user deliver infomation table查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:userdeliverinfo:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('user deliver infomation table新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:userdeliverinfo:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('user deliver infomation table修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:userdeliverinfo:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('user deliver infomation table删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:userdeliverinfo:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('user deliver infomation table导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:userdeliverinfo:export',       '#', 'admin', sysdate(), '', null, '');