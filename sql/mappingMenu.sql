-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单dpd对应关系', '3', '1', 'mapping', 'system/mapping/index', 1, 0, 'C', '0', '0', 'system:mapping:list', '#', 'admin', sysdate(), '', null, '面单dpd对应关系菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单dpd对应关系查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:mapping:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单dpd对应关系新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:mapping:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单dpd对应关系修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:mapping:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单dpd对应关系删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:mapping:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面单dpd对应关系导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:mapping:export',       '#', 'admin', sysdate(), '', null, '');