-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单', '3', '1', 'package', 'system/package/index', 1, 0, 'C', '0', '0', 'system:package:list', '#', 'admin', sysdate(), '', null, '面单菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:package:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:package:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:package:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:package:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:package:export',       '#', 'admin', sysdate(), '', null, '');