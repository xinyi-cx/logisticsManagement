-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转寄关联关系', '3', '1', 'redirectrel', 'system/redirectrel/index', 1, 0, 'C', '0', '0', 'system:redirectrel:list', '#', 'admin', sysdate(), '', null, '转寄关联关系菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转寄关联关系查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:redirectrel:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转寄关联关系新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:redirectrel:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转寄关联关系修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:redirectrel:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转寄关联关系删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:redirectrel:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转寄关联关系导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:redirectrel:export',       '#', 'admin', sysdate(), '', null, '');