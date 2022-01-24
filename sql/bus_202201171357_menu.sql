-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转寄面单原面单关联关系', '3', '1', 'redirect', 'system/redirect/index', 1, 0, 'C', '0', '0', 'system:package:list', '#', 'admin', sysdate(), '', null, '转寄面单原面单关联关系菜单');
