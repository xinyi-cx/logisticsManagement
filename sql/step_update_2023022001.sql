-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户fid对应关系', '1', '9', 'fidrel', 'system/fidrel/index', 1, 0, 'C', '0', '0', 'system:fidrel:list', '#', 'admin', sysdate(), '', null, '用户fid对应关系菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户fid对应关系查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:fidrel:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户fid对应关系新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:fidrel:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户fid对应关系修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:fidrel:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户fid对应关系删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:fidrel:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户fid对应关系导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:fidrel:export',       '#', 'admin', sysdate(), '', null, '');




-- 初始化数据
insert into user_fid_rel
(user_id,
 user_name,
 customer_name,
 nick_name,
 country,
 fid,
 status)
select user_id,
       user_name,
       nick_name,
       nick_name,
       country,
       as.fid,
       '0'
from sys_user tb1
         left join address_sender `as` on tb1.user_id = `as`.create_user





