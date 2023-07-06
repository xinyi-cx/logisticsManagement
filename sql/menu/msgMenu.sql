-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('马帮信息', '3', '1', 'msg', 'system/msg/index', 1, 0, 'C', '0', '0', 'system:msg:list', '#', 'admin', sysdate(), '', null, '马帮信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('马帮信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:msg:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('马帮信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:msg:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('马帮信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:msg:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('马帮信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:msg:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('马帮信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:msg:export',       '#', 'admin', sysdate(), '', null, '');