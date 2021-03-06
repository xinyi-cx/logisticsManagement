-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id           bigint(20)      not null auto_increment    comment '部门id',
  parent_id         bigint(20)      default 0                  comment '父部门id',
  ancestors         varchar(50)     default ''                 comment '祖级列表',
  dept_name         varchar(30)     default ''                 comment '部门名称',
  order_num         int(4)          default 0                  comment '显示顺序',
  leader            varchar(20)     default null               comment '负责人',
  phone             varchar(11)     default null               comment '联系电话',
  email             varchar(50)     default null               comment '邮箱',
  status            char(1)         default '0'                comment '部门状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (dept_id)
) engine=innodb CHARSET=utf8  auto_increment=200 comment = '部门表';

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept values(100,  0,   '0',          '公司姓名',   0, '管理员', '15888888888', '123@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(101,  100, '0,100',      '深圳总公司', 1, '管理员', '15888888888', '123@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(103,  101, '0,100,101',  '研发部门',   1, '管理员', '15888888888', '123@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(104,  101, '0,100,101',  '市场部门',   2, '管理员', '15888888888', '123@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(105,  101, '0,100,101',  '测试部门',   3, '管理员', '15888888888', '123@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(106,  101, '0,100,101',  '财务部门',   4, '管理员', '15888888888', '123@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(107,  101, '0,100,101',  '运维部门',   5, '管理员', '15888888888', '123@qq.com', '0', '0', 'admin', sysdate(), '', null);


-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  user_id           bigint(20)      not null auto_increment    comment '用户ID',
  dept_id           bigint(20)      default null               comment '部门ID',
  user_name         varchar(30)     not null                   comment '用户账号',
  nick_name         varchar(30)     not null                   comment '用户昵称',
  user_type         varchar(2)      default '00'               comment '用户类型（00系统用户）',
  email             varchar(50)     default ''                 comment '用户邮箱',
  phonenumber       varchar(11)     default ''                 comment '手机号码',
  country           varchar(100)    default ''                 comment '国家',
  sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
  avatar            varchar(100)    default ''                 comment '头像地址',
  password          varchar(100)    default ''                 comment '密码',
  status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  login_ip          varchar(128)    default ''                 comment '最后登录IP',
  login_date        datetime                                   comment '最后登录时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (user_id)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user values(1,  103, 'admin', '管理员', '00', '123@163.com', '15888888888', 'CN', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin', sysdate(), '', null, '管理员');
insert into sys_user values(2,  105, 't01',    '管理员', '00', '123@qq.com',  '15666666666', 'CN', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin', sysdate(), '', null, '测试员');


-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
  post_id       bigint(20)      not null auto_increment    comment '岗位ID',
  post_code     varchar(64)     not null                   comment '岗位编码',
  post_name     varchar(50)     not null                   comment '岗位名称',
  post_sort     int(4)          not null                   comment '显示顺序',
  status        char(1)         not null                   comment '状态（0正常 1停用）',
  create_by     varchar(64)     default ''                 comment '创建者',
  create_time   datetime                                   comment '创建时间',
  update_by     varchar(64)     default ''			       comment '更新者',
  update_time   datetime                                   comment '更新时间',
  remark        varchar(500)    default null               comment '备注',
  primary key (post_id)
) engine=innodb CHARSET=utf8  comment = '岗位信息表';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_post values(1, 'ceo',  '董事长',    1, '0', 'admin', sysdate(), '', null, '');
insert into sys_post values(2, 'se',   '项目经理',  2, '0', 'admin', sysdate(), '', null, '');
insert into sys_post values(3, 'hr',   '人力资源',  3, '0', 'admin', sysdate(), '', null, '');
insert into sys_post values(4, 'user', '普通员工',  4, '0', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 4、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id              bigint(20)      not null auto_increment    comment '角色ID',
  role_name            varchar(30)     not null                   comment '角色名称',
  role_key             varchar(100)    not null                   comment '角色权限字符串',
  role_sort            int(4)          not null                   comment '显示顺序',
  data_scope           char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  menu_check_strictly  tinyint(1)      default 1                  comment '菜单树选择项是否关联显示',
  dept_check_strictly  tinyint(1)      default 1                  comment '部门树选择项是否关联显示',
  status               char(1)         not null                   comment '角色状态（0正常 1停用）',
  del_flag             char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by            varchar(64)     default ''                 comment '创建者',
  create_time          datetime                                   comment '创建时间',
  update_by            varchar(64)     default ''                 comment '更新者',
  update_time          datetime                                   comment '更新时间',
  remark               varchar(500)    default null               comment '备注',
  primary key (role_id)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '角色信息表';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role values('1', '超级管理员',  'admin',  1, 1, 1, 1, '0', '0', 'admin', sysdate(), '', null, '超级管理员');
insert into sys_role values('2', '普通角色',    'common', 2, 2, 1, 1, '0', '0', 'admin', sysdate(), '', null, '普通角色');


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id           bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  parent_id         bigint(20)      default 0                  comment '父菜单ID',
  order_num         int(4)          default 0                  comment '显示顺序',
  path              varchar(200)    default ''                 comment '路由地址',
  component         varchar(255)    default null               comment '组件路径',
  query             varchar(255)    default null               comment '路由参数',
  is_frame          int(1)          default 1                  comment '是否为外链（0是 1否）',
  is_cache          int(1)          default 0                  comment '是否缓存（0缓存 1不缓存）',
  menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  visible           char(1)         default 0                  comment '菜单状态（0显示 1隐藏）',
  status            char(1)         default 0                  comment '菜单状态（0正常 1停用）',
  perms             varchar(100)    default null               comment '权限标识',
  icon              varchar(100)    default '#'                comment '菜单图标',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',
  primary key (menu_id)
) engine=innodb CHARSET=utf8  auto_increment=2000 comment = '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1, '系统管理', 0, 4, 'system', null, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', sysdate(), '', null, '系统管理目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2, '系统监控', 0, 5, 'monitor', null, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', sysdate(), '', null, '系统监控目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (11, '面单管理', 0, 2, 'pac', null, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', sysdate(), '', null, '面单管理目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (12, '跟踪管理', 0, 3, 'logic', null, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', sysdate(), '', null, '跟踪管理目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', sysdate(), '', null, '用户管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', sysdate(), '', null, '角色管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', sysdate(), '', null, '菜单管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', sysdate(), '', null, '字典管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', sysdate(), '', null, '参数设置菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', sysdate(), '', null, '通知公告菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', sysdate(), '', null, '日志管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', sysdate(), '', null, '在线用户菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', sysdate(), '', null, '定时任务菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', sysdate(), '', null, '服务监控菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', sysdate(), '', null, '操作日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', sysdate(), '', null, '登录日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1001, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1002, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1003, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1004, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1005, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1006, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1007, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1008, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1009, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1010, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1011, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1012, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1013, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1014, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1015, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1016, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1017, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1018, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1019, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1020, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1026, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1027, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1028, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1029, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1030, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1031, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1032, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1033, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1034, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1035, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1036, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1037, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1038, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1039, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1040, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1041, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1042, '日志导出', 500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1043, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1044, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1045, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1049, '任务查询', 110, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1050, '任务新增', 110, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1051, '任务修改', 110, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1052, '任务删除', 110, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1053, '状态修改', 110, 5, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1054, '任务导出', 110, 7, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2000, '转寄面单管理', 11, 3, 'redirect', 'system/redirect/index', null, 1, 0, 'C', '0', '0', 'system:package:list', 'message', 'admin', sysdate(), '', null, '转寄面单管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2001, '物流信息', 12, 1, 'info', 'system/info/index', null, 1, 0, 'C', '0', '0', 'system:info:list', 'message', 'admin', sysdate(), '', null, '物流信息菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2002, '物流信息查询', 2001, 1, '#', '', null, 1, 0, 'F', '0', '0', 'system:info:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2003, '物流信息新增', 2001, 2, '#', '', null, 1, 0, 'F', '0', '0', 'system:info:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2004, '物流信息修改', 2001, 3, '#', '', null, 1, 0, 'F', '0', '0', 'system:info:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2005, '物流信息删除', 2001, 4, '#', '', null, 1, 0, 'F', '0', '0', 'system:info:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2006, '物流信息导出', 2001, 5, '#', '', null, 1, 0, 'F', '0', '0', 'system:info:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2007, '批量任务历史', 11, 2, 'history', 'system/history/index', null, 1, 0, 'C', '0', '0', 'system:history:list', 'message', 'admin', sysdate(), '', null, '批量任务历史菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2008, '批量任务历史查询', 2007, 1, '#', '', null, 1, 0, 'F', '0', '0', 'system:history:query', 'message', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2009, '批量任务历史新增', 2007, 2, '#', '', null, 1, 0, 'F', '0', '0', 'system:history:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2010, '批量任务历史修改', 2007, 3, '#', '', null, 1, 0, 'F', '0', '0', 'system:history:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2011, '批量任务历史删除', 2007, 4, '#', '', null, 1, 0, 'F', '0', '0', 'system:history:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2012, '批量任务历史导出', 2007, 5, '#', '', null, 1, 0, 'F', '0', '0', 'system:history:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2013, '面单列表', 11, 1, 'package', 'system/package/index', null, 1, 0, 'C', '0', '0', 'system:package:list', 'message', 'admin', sysdate(), '', null, '面单菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2014, '面单查询', 2013, 1, '#', '', null, 1, 0, 'F', '0', '0', 'system:package:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2015, '面单新增', 2013, 2, '#', '', null, 1, 0, 'F', '0', '0', 'system:package:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2016, '面单修改', 2013, 3, '#', '', null, 1, 0, 'F', '0', '0', 'system:package:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2017, '面单删除', 2013, 4, '#', '', null, 1, 0, 'F', '0', '0', 'system:package:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2018, '面单导出', 2013, 5, '#', '', null, 1, 0, 'F', '0', '0', 'system:package:export', '#', 'admin', sysdate(), '', null, '');

-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id   bigint(20) not null comment '用户ID',
  role_id   bigint(20) not null comment '角色ID',
  primary key(user_id, role_id)
) engine=innodb CHARSET=utf8  comment = '用户和角色关联表';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');
insert into sys_user_role values ('2', '2');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb CHARSET=utf8  comment = '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu values ('2', '1');
insert into sys_role_menu values ('2', '2');
insert into sys_role_menu values ('2', '3');
insert into sys_role_menu values ('2', '4');
insert into sys_role_menu values ('2', '100');
insert into sys_role_menu values ('2', '101');
insert into sys_role_menu values ('2', '102');
insert into sys_role_menu values ('2', '103');
insert into sys_role_menu values ('2', '104');
insert into sys_role_menu values ('2', '105');
insert into sys_role_menu values ('2', '106');
insert into sys_role_menu values ('2', '107');
insert into sys_role_menu values ('2', '108');
insert into sys_role_menu values ('2', '109');
insert into sys_role_menu values ('2', '110');
insert into sys_role_menu values ('2', '111');
insert into sys_role_menu values ('2', '112');
insert into sys_role_menu values ('2', '113');
insert into sys_role_menu values ('2', '114');
insert into sys_role_menu values ('2', '115');
insert into sys_role_menu values ('2', '116');
insert into sys_role_menu values ('2', '500');
insert into sys_role_menu values ('2', '501');
insert into sys_role_menu values ('2', '1000');
insert into sys_role_menu values ('2', '1001');
insert into sys_role_menu values ('2', '1002');
insert into sys_role_menu values ('2', '1003');
insert into sys_role_menu values ('2', '1004');
insert into sys_role_menu values ('2', '1005');
insert into sys_role_menu values ('2', '1006');
insert into sys_role_menu values ('2', '1007');
insert into sys_role_menu values ('2', '1008');
insert into sys_role_menu values ('2', '1009');
insert into sys_role_menu values ('2', '1010');
insert into sys_role_menu values ('2', '1011');
insert into sys_role_menu values ('2', '1012');
insert into sys_role_menu values ('2', '1013');
insert into sys_role_menu values ('2', '1014');
insert into sys_role_menu values ('2', '1015');
insert into sys_role_menu values ('2', '1016');
insert into sys_role_menu values ('2', '1017');
insert into sys_role_menu values ('2', '1018');
insert into sys_role_menu values ('2', '1019');
insert into sys_role_menu values ('2', '1020');
insert into sys_role_menu values ('2', '1021');
insert into sys_role_menu values ('2', '1022');
insert into sys_role_menu values ('2', '1023');
insert into sys_role_menu values ('2', '1024');
insert into sys_role_menu values ('2', '1025');
insert into sys_role_menu values ('2', '1026');
insert into sys_role_menu values ('2', '1027');
insert into sys_role_menu values ('2', '1028');
insert into sys_role_menu values ('2', '1029');
insert into sys_role_menu values ('2', '1030');
insert into sys_role_menu values ('2', '1031');
insert into sys_role_menu values ('2', '1032');
insert into sys_role_menu values ('2', '1033');
insert into sys_role_menu values ('2', '1034');
insert into sys_role_menu values ('2', '1035');
insert into sys_role_menu values ('2', '1036');
insert into sys_role_menu values ('2', '1037');
insert into sys_role_menu values ('2', '1038');
insert into sys_role_menu values ('2', '1039');
insert into sys_role_menu values ('2', '1040');
insert into sys_role_menu values ('2', '1041');
insert into sys_role_menu values ('2', '1042');
insert into sys_role_menu values ('2', '1043');
insert into sys_role_menu values ('2', '1044');
insert into sys_role_menu values ('2', '1045');
insert into sys_role_menu values ('2', '1046');
insert into sys_role_menu values ('2', '1047');
insert into sys_role_menu values ('2', '1048');
insert into sys_role_menu values ('2', '1049');
insert into sys_role_menu values ('2', '1050');
insert into sys_role_menu values ('2', '1051');
insert into sys_role_menu values ('2', '1052');
insert into sys_role_menu values ('2', '1053');
insert into sys_role_menu values ('2', '1054');
insert into sys_role_menu values ('2', '1055');
insert into sys_role_menu values ('2', '1056');
insert into sys_role_menu values ('2', '1057');
insert into sys_role_menu values ('2', '1058');
insert into sys_role_menu values ('2', '1059');
insert into sys_role_menu values ('2', '1060');

-- ----------------------------
-- 8、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sys_role_dept;
create table sys_role_dept (
  role_id   bigint(20) not null comment '角色ID',
  dept_id   bigint(20) not null comment '部门ID',
  primary key(role_id, dept_id)
) engine=innodb CHARSET=utf8  comment = '角色和部门关联表';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------
insert into sys_role_dept values ('2', '100');
insert into sys_role_dept values ('2', '101');
insert into sys_role_dept values ('2', '105');


-- ----------------------------
-- 9、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
  user_id   bigint(20) not null comment '用户ID',
  post_id   bigint(20) not null comment '岗位ID',
  primary key (user_id, post_id)
) engine=innodb CHARSET=utf8  comment = '用户与岗位关联表';

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
insert into sys_user_post values ('1', '1');
insert into sys_user_post values ('2', '2');


-- ----------------------------
-- 10、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
  oper_id           bigint(20)      not null auto_increment    comment '日志主键',
  title             varchar(50)     default ''                 comment '模块标题',
  business_type     int(2)          default 0                  comment '业务类型（0其它 1新增 2修改 3删除）',
  method            varchar(100)    default ''                 comment '方法名称',
  request_method    varchar(10)     default ''                 comment '请求方式',
  operator_type     int(1)          default 0                  comment '操作类别（0其它 1后台用户 2手机端用户）',
  oper_name         varchar(50)     default ''                 comment '操作人员',
  dept_name         varchar(50)     default ''                 comment '部门名称',
  oper_url          varchar(255)    default ''                 comment '请求URL',
  oper_ip           varchar(128)    default ''                 comment '主机地址',
  oper_location     varchar(255)    default ''                 comment '操作地点',
  oper_param        varchar(2000)   default ''                 comment '请求参数',
  json_result       varchar(2000)   default ''                 comment '返回参数',
  status            int(1)          default 0                  comment '操作状态（0正常 1异常）',
  error_msg         varchar(2000)   default ''                 comment '错误消息',
  oper_time         datetime                                   comment '操作时间',
  primary key (oper_id)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '操作日志记录';


-- ----------------------------
-- 11、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
  dict_id          bigint(20)      not null auto_increment    comment '字典主键',
  dict_name        varchar(100)    default ''                 comment '字典名称',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_id),
  unique (dict_type)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '字典类型表';

insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', 'admin', sysdate(), '', null, '用户性别列表');
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin', sysdate(), '', null, '菜单状态列表');
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin', sysdate(), '', null, '系统开关列表');
insert into sys_dict_type values(4,  '任务状态', 'sys_job_status',      '0', 'admin', sysdate(), '', null, '任务状态列表');
insert into sys_dict_type values(5,  '任务分组', 'sys_job_group',       '0', 'admin', sysdate(), '', null, '任务分组列表');
insert into sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', 'admin', sysdate(), '', null, '系统是否列表');
insert into sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', 'admin', sysdate(), '', null, '通知类型列表');
insert into sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', 'admin', sysdate(), '', null, '通知状态列表');
insert into sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', 'admin', sysdate(), '', null, '操作类型列表');
insert into sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', 'admin', sysdate(), '', null, '登录状态列表');

insert into sys_dict_type values(11, '国家', 'sys_country',   '0', 'admin', sysdate(), '', null, '国家列表');

-- ----------------------------
-- 12、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
  dict_code        bigint(20)      not null auto_increment    comment '字典编码',
  dict_sort        int(4)          default 0                  comment '字典排序',
  dict_label       varchar(100)    default ''                 comment '字典标签',
  dict_value       varchar(100)    default ''                 comment '字典键值',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  css_class        varchar(100)    default null               comment '样式属性（其他样式扩展）',
  list_class       varchar(100)    default null               comment '表格回显样式',
  is_default       char(1)         default 'N'                comment '是否默认（Y是 N否）',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_code)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '字典数据表';

insert into sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',        '',   '',        'Y', '0', 'admin', sysdate(), '', null, '性别男');
insert into sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, '性别女');
insert into sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, '性别未知');
insert into sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',       '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '显示菜单');
insert into sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '隐藏菜单');
insert into sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',  '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data values(8,  1,  '正常',     '0',       'sys_job_status',      '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(9,  2,  '暂停',     '1',       'sys_job_status',      '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data values(10, 1,  '默认',     'DEFAULT', 'sys_job_group',       '',   '',        'Y', '0', 'admin', sysdate(), '', null, '默认分组');
insert into sys_dict_data values(11, 2,  '系统',     'SYSTEM',  'sys_job_group',       '',   '',        'N', '0', 'admin', sysdate(), '', null, '系统分组');
insert into sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '系统默认是');
insert into sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',          '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '系统默认否');
insert into sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', 'admin', sysdate(), '', null, '通知');
insert into sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',     '',   'success', 'N', '0', 'admin', sysdate(), '', null, '公告');
insert into sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '关闭状态');
insert into sys_dict_data values(18, 1,  '新增',     '1',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, '新增操作');
insert into sys_dict_data values(19, 2,  '修改',     '2',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, '修改操作');
insert into sys_dict_data values(20, 3,  '删除',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '删除操作');
insert into sys_dict_data values(21, 4,  '授权',     '4',       'sys_oper_type',       '',   'primary', 'N', '0', 'admin', sysdate(), '', null, '授权操作');
insert into sys_dict_data values(22, 5,  '导出',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, '导出操作');
insert into sys_dict_data values(23, 6,  '导入',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, '导入操作');
insert into sys_dict_data values(24, 7,  '强退',     '7',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '强退操作');
insert into sys_dict_data values(25, 8,  '生成代码', '8',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, '生成操作');
insert into sys_dict_data values(26, 9,  '清空数据', '9',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '清空操作');
insert into sys_dict_data values(27, 1,  '成功',     '0',       'sys_common_status',   '',   'primary', 'N', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(28, 2,  '失败',     '1',       'sys_common_status',   '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '停用状态');

insert into sys_dict_data values(29, 1,  '安道尔',     'AD',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '安道尔');
insert into sys_dict_data values(30, 2,  '阿拉伯联合酋长国',     'AE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '阿拉伯联合酋长国');
insert into sys_dict_data values(31, 3,  '阿富汗',     'AF',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '阿富汗');
insert into sys_dict_data values(32, 4,  '安提瓜和巴布达',     'AG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '安提瓜和巴布达');
insert into sys_dict_data values(33, 5,  '安圭拉',     'AI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '安圭拉');
insert into sys_dict_data values(34, 6,  '阿尔巴尼亚',     'AL',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '阿尔巴尼亚');
insert into sys_dict_data values(35, 7,  '亚美尼亚',     'AM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '亚美尼亚');
insert into sys_dict_data values(36, 8,  '安哥拉',     'AO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '安哥拉');
insert into sys_dict_data values(37, 9,  '南极洲',     'AQ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '南极洲');
insert into sys_dict_data values(38, 10,  '阿根廷',     'AR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '阿根廷');
insert into sys_dict_data values(39, 11,  '美属萨摩亚',     'AS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '美属萨摩亚');
insert into sys_dict_data values(40, 12,  '奥地利',     'AT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '奥地利');
insert into sys_dict_data values(41, 13,  '澳大利亚',     'AU',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '澳大利亚');
insert into sys_dict_data values(42, 14,  '阿鲁巴',     'AW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '阿鲁巴');
insert into sys_dict_data values(43, 15,  '奥兰群岛',     'AX',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '奥兰群岛');
insert into sys_dict_data values(44, 16,  '阿塞拜疆',     'AZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '阿塞拜疆');
insert into sys_dict_data values(45, 17,  '波斯尼亚和黑塞哥维那',     'BA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '波斯尼亚和黑塞哥维那');
insert into sys_dict_data values(46, 18,  '巴巴多斯',     'BB',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '巴巴多斯');
insert into sys_dict_data values(47, 19,  '孟加拉国',     'BD',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '孟加拉国');
insert into sys_dict_data values(48, 20,  '比利时',     'BE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '比利时');
insert into sys_dict_data values(49, 21,  '布基纳法索',     'BF',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '布基纳法索');
insert into sys_dict_data values(50, 22,  '保加利亚',     'BG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '保加利亚');
insert into sys_dict_data values(51, 23,  '巴林',     'BH',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '巴林');
insert into sys_dict_data values(52, 24,  '布隆迪',     'BI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '布隆迪');
insert into sys_dict_data values(53, 25,  '贝宁',     'BJ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '贝宁');
insert into sys_dict_data values(54, 26,  '圣巴泰勒米',     'BL',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圣巴泰勒米');
insert into sys_dict_data values(55, 27,  '百慕大',     'BM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '百慕大');
insert into sys_dict_data values(56, 28,  '文莱',     'BN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '文莱');
insert into sys_dict_data values(57, 29,  '玻利维亚',     'BO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '玻利维亚');
insert into sys_dict_data values(58, 30,  '荷属加勒比区',     'BQ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '荷属加勒比区');
insert into sys_dict_data values(59, 31,  '巴西',     'BR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '巴西');
insert into sys_dict_data values(60, 32,  '巴哈马',     'BS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '巴哈马');
insert into sys_dict_data values(61, 33,  '不丹',     'BT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '不丹');
insert into sys_dict_data values(62, 34,  '布韦岛',     'BV',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '布韦岛');
insert into sys_dict_data values(63, 35,  '博茨瓦纳',     'BW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '博茨瓦纳');
insert into sys_dict_data values(64, 36,  '白俄罗斯',     'BY',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '白俄罗斯');
insert into sys_dict_data values(65, 37,  '伯利兹',     'BZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '伯利兹');
insert into sys_dict_data values(66, 38,  '加拿大',     'CA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '加拿大');
insert into sys_dict_data values(67, 39,  '科科斯（基林）群岛',     'CC',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '科科斯（基林）群岛');
insert into sys_dict_data values(68, 40,  '刚果（金）',     'CD',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '刚果（金）');
insert into sys_dict_data values(69, 41,  '中非共和国',     'CF',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '中非共和国');
insert into sys_dict_data values(70, 42,  '刚果（布）',     'CG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '刚果（布）');
insert into sys_dict_data values(71, 43,  '瑞士',     'CH',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '瑞士');
insert into sys_dict_data values(72, 44,  '科特迪瓦',     'CI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '科特迪瓦');
insert into sys_dict_data values(73, 45,  '库克群岛',     'CK',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '库克群岛');
insert into sys_dict_data values(74, 46,  '智利',     'CL',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '智利');
insert into sys_dict_data values(75, 47,  '喀麦隆',     'CM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '喀麦隆');
insert into sys_dict_data values(76, 48,  '中国',     'CN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '中国');
insert into sys_dict_data values(77, 49,  '哥伦比亚',     'CO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '哥伦比亚');
insert into sys_dict_data values(78, 50,  '哥斯达黎加',     'CR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '哥斯达黎加');
insert into sys_dict_data values(79, 51,  '古巴',     'CU',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '古巴');
insert into sys_dict_data values(80, 52,  '佛得角',     'CV',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '佛得角');
insert into sys_dict_data values(81, 53,  '库拉索',     'CW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '库拉索');
insert into sys_dict_data values(82, 54,  '圣诞岛',     'CX',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圣诞岛');
insert into sys_dict_data values(83, 55,  '塞浦路斯',     'CY',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '塞浦路斯');
insert into sys_dict_data values(84, 56,  '捷克',     'CZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '捷克');
insert into sys_dict_data values(85, 57,  '德国',     'DE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '德国');
insert into sys_dict_data values(86, 58,  '吉布提',     'DJ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '吉布提');
insert into sys_dict_data values(87, 59,  '丹麦',     'DK',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '丹麦');
insert into sys_dict_data values(88, 60,  '多米尼克',     'DM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '多米尼克');
insert into sys_dict_data values(89, 61,  '多米尼加共和国',     'DO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '多米尼加共和国');
insert into sys_dict_data values(90, 62,  '阿尔及利亚',     'DZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '阿尔及利亚');
insert into sys_dict_data values(91, 63,  '厄瓜多尔',     'EC',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '厄瓜多尔');
insert into sys_dict_data values(92, 64,  '爱沙尼亚',     'EE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '爱沙尼亚');
insert into sys_dict_data values(93, 65,  '埃及',     'EG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '埃及');
insert into sys_dict_data values(94, 66,  '西撒哈拉',     'EH',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '西撒哈拉');
insert into sys_dict_data values(95, 67,  '厄立特里亚',     'ER',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '厄立特里亚');
insert into sys_dict_data values(96, 68,  '西班牙',     'ES',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '西班牙');
insert into sys_dict_data values(97, 69,  '埃塞俄比亚',     'ET',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '埃塞俄比亚');
insert into sys_dict_data values(98, 70,  '芬兰',     'FI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '芬兰');
insert into sys_dict_data values(99, 71,  '斐济',     'FJ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '斐济');
insert into sys_dict_data values(100, 72,  '福克兰群岛',     'FK',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '福克兰群岛');
insert into sys_dict_data values(101, 73,  '密克罗尼西亚',     'FM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '密克罗尼西亚');
insert into sys_dict_data values(102, 74,  '法罗群岛',     'FO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '法罗群岛');
insert into sys_dict_data values(103, 75,  '法国',     'FR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '法国');
insert into sys_dict_data values(104, 76,  '加蓬',     'GA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '加蓬');
insert into sys_dict_data values(105, 77,  '英国',     'GB',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '英国');
insert into sys_dict_data values(106, 78,  '格林纳达',     'GD',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '格林纳达');
insert into sys_dict_data values(107, 79,  '格鲁吉亚',     'GE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '格鲁吉亚');
insert into sys_dict_data values(108, 80,  '法属圭亚那',     'GF',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '法属圭亚那');
insert into sys_dict_data values(109, 81,  '根西岛',     'GG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '根西岛');
insert into sys_dict_data values(110, 82,  '加纳',     'GH',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '加纳');
insert into sys_dict_data values(111, 83,  '直布罗陀',     'GI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '直布罗陀');
insert into sys_dict_data values(112, 84,  '格陵兰',     'GL',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '格陵兰');
insert into sys_dict_data values(113, 85,  '冈比亚',     'GM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '冈比亚');
insert into sys_dict_data values(114, 86,  '几内亚',     'GN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '几内亚');
insert into sys_dict_data values(115, 87,  '瓜德罗普',     'GP',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '瓜德罗普');
insert into sys_dict_data values(116, 88,  '赤道几内亚',     'GQ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '赤道几内亚');
insert into sys_dict_data values(117, 89,  '希腊',     'GR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '希腊');
insert into sys_dict_data values(118, 90,  '南乔治亚和南桑威奇群岛',     'GS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '南乔治亚和南桑威奇群岛');
insert into sys_dict_data values(119, 91,  '危地马拉',     'GT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '危地马拉');
insert into sys_dict_data values(120, 92,  '关岛',     'GU',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '关岛');
insert into sys_dict_data values(121, 93,  '几内亚比绍',     'GW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '几内亚比绍');
insert into sys_dict_data values(122, 94,  '圭亚那',     'GY',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圭亚那');
insert into sys_dict_data values(123, 95,  '中国香港特别行政区',     'HK',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '中国香港特别行政区');
insert into sys_dict_data values(124, 96,  '赫德岛和麦克唐纳群岛',     'HM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '赫德岛和麦克唐纳群岛');
insert into sys_dict_data values(125, 97,  '洪都拉斯',     'HN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '洪都拉斯');
insert into sys_dict_data values(126, 98,  '克罗地亚',     'HR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '克罗地亚');
insert into sys_dict_data values(127, 99,  '海地',     'HT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '海地');
insert into sys_dict_data values(128, 100,  '匈牙利',     'HU',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '匈牙利');
insert into sys_dict_data values(129, 101,  '印度尼西亚',     'ID',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '印度尼西亚');
insert into sys_dict_data values(130, 102,  '爱尔兰',     'IE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '爱尔兰');
insert into sys_dict_data values(131, 103,  '以色列',     'IL',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '以色列');
insert into sys_dict_data values(132, 104,  '马恩岛',     'IM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马恩岛');
insert into sys_dict_data values(133, 105,  '印度',     'IN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '印度');
insert into sys_dict_data values(134, 106,  '英属印度洋领地',     'IO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '英属印度洋领地');
insert into sys_dict_data values(135, 107,  '伊拉克',     'IQ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '伊拉克');
insert into sys_dict_data values(136, 108,  '伊朗',     'IR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '伊朗');
insert into sys_dict_data values(137, 109,  '冰岛',     'IS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '冰岛');
insert into sys_dict_data values(138, 110,  '意大利',     'IT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '意大利');
insert into sys_dict_data values(139, 111,  '泽西岛',     'JE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '泽西岛');
insert into sys_dict_data values(140, 112,  '牙买加',     'JM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '牙买加');
insert into sys_dict_data values(141, 113,  '约旦',     'JO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '约旦');
insert into sys_dict_data values(142, 114,  '日本',     'JP',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '日本');
insert into sys_dict_data values(143, 115,  '肯尼亚',     'KE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '肯尼亚');
insert into sys_dict_data values(144, 116,  '吉尔吉斯斯坦',     'KG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '吉尔吉斯斯坦');
insert into sys_dict_data values(145, 117,  '柬埔寨',     'KH',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '柬埔寨');
insert into sys_dict_data values(146, 118,  '基里巴斯',     'KI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '基里巴斯');
insert into sys_dict_data values(147, 119,  '科摩罗',     'KM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '科摩罗');
insert into sys_dict_data values(148, 120,  '圣基茨和尼维斯',     'KN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圣基茨和尼维斯');
insert into sys_dict_data values(149, 121,  '朝鲜',     'KP',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '朝鲜');
insert into sys_dict_data values(150, 122,  '韩国',     'KR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '韩国');
insert into sys_dict_data values(151, 123,  '科威特',     'KW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '科威特');
insert into sys_dict_data values(152, 124,  '开曼群岛',     'KY',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '开曼群岛');
insert into sys_dict_data values(153, 125,  '哈萨克斯坦',     'KZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '哈萨克斯坦');
insert into sys_dict_data values(154, 126,  '老挝',     'LA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '老挝');
insert into sys_dict_data values(155, 127,  '黎巴嫩',     'LB',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '黎巴嫩');
insert into sys_dict_data values(156, 128,  '圣卢西亚',     'LC',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圣卢西亚');
insert into sys_dict_data values(157, 129,  '列支敦士登',     'LI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '列支敦士登');
insert into sys_dict_data values(158, 130,  '斯里兰卡',     'LK',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '斯里兰卡');
insert into sys_dict_data values(159, 131,  '利比里亚',     'LR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '利比里亚');
insert into sys_dict_data values(160, 132,  '莱索托',     'LS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '莱索托');
insert into sys_dict_data values(161, 133,  '立陶宛',     'LT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '立陶宛');
insert into sys_dict_data values(162, 134,  '卢森堡',     'LU',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '卢森堡');
insert into sys_dict_data values(163, 135,  '拉脱维亚',     'LV',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '拉脱维亚');
insert into sys_dict_data values(164, 136,  '利比亚',     'LY',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '利比亚');
insert into sys_dict_data values(165, 137,  '摩洛哥',     'MA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '摩洛哥');
insert into sys_dict_data values(166, 138,  '摩纳哥',     'MC',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '摩纳哥');
insert into sys_dict_data values(167, 139,  '摩尔多瓦',     'MD',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '摩尔多瓦');
insert into sys_dict_data values(168, 140,  '黑山',     'ME',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '黑山');
insert into sys_dict_data values(169, 141,  '法属圣马丁',     'MF',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '法属圣马丁');
insert into sys_dict_data values(170, 142,  '马达加斯加',     'MG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马达加斯加');
insert into sys_dict_data values(171, 143,  '马绍尔群岛',     'MH',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马绍尔群岛');
insert into sys_dict_data values(172, 144,  '马其顿',     'MK',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马其顿');
insert into sys_dict_data values(173, 145,  '马里',     'ML',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马里');
insert into sys_dict_data values(174, 146,  '缅甸',     'MM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '缅甸');
insert into sys_dict_data values(175, 147,  '蒙古',     'MN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '蒙古');
insert into sys_dict_data values(176, 148,  '中国澳门特别行政区',     'MO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '中国澳门特别行政区');
insert into sys_dict_data values(177, 149,  '北马里亚纳群岛',     'MP',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '北马里亚纳群岛');
insert into sys_dict_data values(178, 150,  '马提尼克',     'MQ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马提尼克');
insert into sys_dict_data values(179, 151,  '毛里塔尼亚',     'MR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '毛里塔尼亚');
insert into sys_dict_data values(180, 152,  '蒙特塞拉特',     'MS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '蒙特塞拉特');
insert into sys_dict_data values(181, 153,  '马耳他',     'MT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马耳他');
insert into sys_dict_data values(182, 154,  '毛里求斯',     'MU',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '毛里求斯');
insert into sys_dict_data values(183, 155,  '马尔代夫',     'MV',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马尔代夫');
insert into sys_dict_data values(184, 156,  '马拉维',     'MW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马拉维');
insert into sys_dict_data values(185, 157,  '墨西哥',     'MX',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '墨西哥');
insert into sys_dict_data values(186, 158,  '马来西亚',     'MY',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马来西亚');
insert into sys_dict_data values(187, 159,  '莫桑比克',     'MZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '莫桑比克');
insert into sys_dict_data values(188, 160,  '纳米比亚',     'NA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '纳米比亚');
insert into sys_dict_data values(189, 161,  '新喀里多尼亚',     'NC',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '新喀里多尼亚');
insert into sys_dict_data values(190, 162,  '尼日尔',     'NE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '尼日尔');
insert into sys_dict_data values(191, 163,  '诺福克岛',     'NF',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '诺福克岛');
insert into sys_dict_data values(192, 164,  '尼日利亚',     'NG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '尼日利亚');
insert into sys_dict_data values(193, 165,  '尼加拉瓜',     'NI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '尼加拉瓜');
insert into sys_dict_data values(194, 166,  '荷兰',     'NL',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '荷兰');
insert into sys_dict_data values(195, 167,  '挪威',     'NO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '挪威');
insert into sys_dict_data values(196, 168,  '尼泊尔',     'NP',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '尼泊尔');
insert into sys_dict_data values(197, 169,  '瑙鲁',     'NR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '瑙鲁');
insert into sys_dict_data values(198, 170,  '纽埃',     'NU',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '纽埃');
insert into sys_dict_data values(199, 171,  '新西兰',     'NZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '新西兰');
insert into sys_dict_data values(200, 172,  '阿曼',     'OM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '阿曼');
insert into sys_dict_data values(201, 173,  '巴拿马',     'PA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '巴拿马');
insert into sys_dict_data values(202, 174,  '秘鲁',     'PE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '秘鲁');
insert into sys_dict_data values(203, 175,  '法属波利尼西亚',     'PF',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '法属波利尼西亚');
insert into sys_dict_data values(204, 176,  '巴布亚新几内亚',     'PG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '巴布亚新几内亚');
insert into sys_dict_data values(205, 177,  '菲律宾',     'PH',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '菲律宾');
insert into sys_dict_data values(206, 178,  '巴基斯坦',     'PK',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '巴基斯坦');
insert into sys_dict_data values(207, 179,  '波兰',     'PL',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '波兰');
insert into sys_dict_data values(208, 180,  '圣皮埃尔和密克隆群岛',     'PM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圣皮埃尔和密克隆群岛');
insert into sys_dict_data values(209, 181,  '皮特凯恩群岛',     'PN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '皮特凯恩群岛');
insert into sys_dict_data values(210, 182,  '波多黎各',     'PR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '波多黎各');
insert into sys_dict_data values(211, 183,  '巴勒斯坦领土',     'PS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '巴勒斯坦领土');
insert into sys_dict_data values(212, 184,  '葡萄牙',     'PT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '葡萄牙');
insert into sys_dict_data values(213, 185,  '帕劳',     'PW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '帕劳');
insert into sys_dict_data values(214, 186,  '巴拉圭',     'PY',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '巴拉圭');
insert into sys_dict_data values(215, 187,  '卡塔尔',     'QA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '卡塔尔');
insert into sys_dict_data values(216, 188,  '留尼汪',     'RE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '留尼汪');
insert into sys_dict_data values(217, 189,  '罗马尼亚',     'RO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '罗马尼亚');
insert into sys_dict_data values(218, 190,  '塞尔维亚',     'RS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '塞尔维亚');
insert into sys_dict_data values(219, 191,  '俄罗斯',     'RU',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '俄罗斯');
insert into sys_dict_data values(220, 192,  '卢旺达',     'RW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '卢旺达');
insert into sys_dict_data values(221, 193,  '沙特阿拉伯',     'SA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '沙特阿拉伯');
insert into sys_dict_data values(222, 194,  '所罗门群岛',     'SB',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '所罗门群岛');
insert into sys_dict_data values(223, 195,  '塞舌尔',     'SC',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '塞舌尔');
insert into sys_dict_data values(224, 196,  '苏丹',     'SD',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '苏丹');
insert into sys_dict_data values(225, 197,  '瑞典',     'SE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '瑞典');
insert into sys_dict_data values(226, 198,  '新加坡',     'SG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '新加坡');
insert into sys_dict_data values(227, 199,  '圣赫勒拿',     'SH',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圣赫勒拿');
insert into sys_dict_data values(228, 200,  '斯洛文尼亚',     'SI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '斯洛文尼亚');
insert into sys_dict_data values(229, 201,  '斯瓦尔巴和扬马延',     'SJ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '斯瓦尔巴和扬马延');
insert into sys_dict_data values(230, 202,  '斯洛伐克',     'SK',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '斯洛伐克');
insert into sys_dict_data values(231, 203,  '塞拉利昂',     'SL',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '塞拉利昂');
insert into sys_dict_data values(232, 204,  '圣马力诺',     'SM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圣马力诺');
insert into sys_dict_data values(233, 205,  '塞内加尔',     'SN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '塞内加尔');
insert into sys_dict_data values(234, 206,  '索马里',     'SO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '索马里');
insert into sys_dict_data values(235, 207,  '苏里南',     'SR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '苏里南');
insert into sys_dict_data values(236, 208,  '南苏丹',     'SS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '南苏丹');
insert into sys_dict_data values(237, 209,  '圣多美和普林西比',     'ST',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圣多美和普林西比');
insert into sys_dict_data values(238, 210,  '萨尔瓦多',     'SV',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '萨尔瓦多');
insert into sys_dict_data values(239, 211,  '荷属圣马丁',     'SX',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '荷属圣马丁');
insert into sys_dict_data values(240, 212,  '叙利亚',     'SY',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '叙利亚');
insert into sys_dict_data values(241, 213,  '斯威士兰',     'SZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '斯威士兰');
insert into sys_dict_data values(242, 214,  '特克斯和凯科斯群岛',     'TC',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '特克斯和凯科斯群岛');
insert into sys_dict_data values(243, 215,  '乍得',     'TD',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '乍得');
insert into sys_dict_data values(244, 216,  '法属南部领地',     'TF',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '法属南部领地');
insert into sys_dict_data values(245, 217,  '多哥',     'TG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '多哥');
insert into sys_dict_data values(246, 218,  '泰国',     'TH',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '泰国');
insert into sys_dict_data values(247, 219,  '塔吉克斯坦',     'TJ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '塔吉克斯坦');
insert into sys_dict_data values(248, 220,  '托克劳',     'TK',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '托克劳');
insert into sys_dict_data values(249, 221,  '东帝汶',     'TL',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '东帝汶');
insert into sys_dict_data values(250, 222,  '土库曼斯坦',     'TM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '土库曼斯坦');
insert into sys_dict_data values(251, 223,  '突尼斯',     'TN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '突尼斯');
insert into sys_dict_data values(252, 224,  '汤加',     'TO',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '汤加');
insert into sys_dict_data values(253, 225,  '土耳其',     'TR',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '土耳其');
insert into sys_dict_data values(254, 226,  '特立尼达和多巴哥',     'TT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '特立尼达和多巴哥');
insert into sys_dict_data values(255, 227,  '图瓦卢',     'TV',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '图瓦卢');
insert into sys_dict_data values(256, 228,  '台湾',     'TW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '台湾');
insert into sys_dict_data values(257, 229,  '坦桑尼亚',     'TZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '坦桑尼亚');
insert into sys_dict_data values(258, 230,  '乌克兰',     'UA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '乌克兰');
insert into sys_dict_data values(259, 231,  '乌干达',     'UG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '乌干达');
insert into sys_dict_data values(260, 232,  '美国本土外小岛屿',     'UM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '美国本土外小岛屿');
insert into sys_dict_data values(261, 233,  '美国',     'US',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '美国');
insert into sys_dict_data values(262, 234,  '乌拉圭',     'UY',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '乌拉圭');
insert into sys_dict_data values(263, 235,  '乌兹别克斯坦',     'UZ',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '乌兹别克斯坦');
insert into sys_dict_data values(264, 236,  '梵蒂冈',     'VA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '梵蒂冈');
insert into sys_dict_data values(265, 237,  '圣文森特和格林纳丁斯',     'VC',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '圣文森特和格林纳丁斯');
insert into sys_dict_data values(266, 238,  '委内瑞拉',     'VE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '委内瑞拉');
insert into sys_dict_data values(267, 239,  '英属维尔京群岛',     'VG',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '英属维尔京群岛');
insert into sys_dict_data values(268, 240,  '美属维尔京群岛',     'VI',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '美属维尔京群岛');
insert into sys_dict_data values(269, 241,  '越南',     'VN',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '越南');
insert into sys_dict_data values(270, 242,  '瓦努阿图',     'VU',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '瓦努阿图');
insert into sys_dict_data values(271, 243,  '瓦利斯和富图纳',     'WF',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '瓦利斯和富图纳');
insert into sys_dict_data values(272, 244,  '萨摩亚',     'WS',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '萨摩亚');
insert into sys_dict_data values(273, 245,  '也门',     'YE',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '也门');
insert into sys_dict_data values(274, 246,  '马约特',     'YT',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '马约特');
insert into sys_dict_data values(275, 247,  '南非',     'ZA',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '南非');
insert into sys_dict_data values(276, 248,  '赞比亚',     'ZM',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '赞比亚');
insert into sys_dict_data values(277, 249,  '津巴布韦',     'ZW',       'sys_country',   '',   '',  'N', '0', 'admin', sysdate(), '', null, '津巴布韦');

-- ----------------------------
-- 13、参数配置表
-- ----------------------------
drop table if exists sys_config;
create table sys_config (
  config_id         int(5)          not null auto_increment    comment '参数主键',
  config_name       varchar(100)    default ''                 comment '参数名称',
  config_key        varchar(100)    default ''                 comment '参数键名',
  config_value      varchar(500)    default ''                 comment '参数键值',
  config_type       char(1)         default 'N'                comment '系统内置（Y是 N否）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (config_id)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '参数配置表';

insert into sys_config values(1, '主框架页-默认皮肤样式名称',     'sys.index.skinName',            'skin-blue',     'Y', 'admin', sysdate(), '', null, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow' );
insert into sys_config values(2, '用户管理-账号初始密码',         'sys.user.initPassword',         '123456',        'Y', 'admin', sysdate(), '', null, '初始化密码 123456' );
insert into sys_config values(3, '主框架页-侧边栏主题',           'sys.index.sideTheme',           'theme-dark',    'Y', 'admin', sysdate(), '', null, '深色主题theme-dark，浅色主题theme-light' );
insert into sys_config values(4, '账号自助-验证码开关',           'sys.account.captchaOnOff',      'true',          'Y', 'admin', sysdate(), '', null, '是否开启验证码功能（true开启，false关闭）');
insert into sys_config values(5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser',      'false',         'Y', 'admin', sysdate(), '', null, '是否开启注册用户功能（true开启，false关闭）');


-- ----------------------------
-- 14、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id        bigint(20)     not null auto_increment   comment '访问ID',
  user_name      varchar(50)    default ''                comment '用户账号',
  ipaddr         varchar(128)   default ''                comment '登录IP地址',
  login_location varchar(255)   default ''                comment '登录地点',
  browser        varchar(50)    default ''                comment '浏览器类型',
  os             varchar(50)    default ''                comment '操作系统',
  status         char(1)        default '0'               comment '登录状态（0成功 1失败）',
  msg            varchar(255)   default ''                comment '提示消息',
  login_time     datetime                                 comment '访问时间',
  primary key (info_id)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '系统访问记录';


-- ----------------------------
-- 15、定时任务调度表
-- ----------------------------
drop table if exists sys_job;
create table sys_job (
  job_id              bigint(20)    not null auto_increment    comment '任务ID',
  job_name            varchar(64)   default ''                 comment '任务名称',
  job_group           varchar(64)   default 'DEFAULT'          comment '任务组名',
  invoke_target       varchar(500)  not null                   comment '调用目标字符串',
  cron_expression     varchar(255)  default ''                 comment 'cron执行表达式',
  misfire_policy      varchar(20)   default '3'                comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  concurrent          char(1)       default '1'                comment '是否并发执行（0允许 1禁止）',
  status              char(1)       default '0'                comment '状态（0正常 1暂停）',
  create_by           varchar(64)   default ''                 comment '创建者',
  create_time         datetime                                 comment '创建时间',
  update_by           varchar(64)   default ''                 comment '更新者',
  update_time         datetime                                 comment '更新时间',
  remark              varchar(500)  default ''                 comment '备注信息',
  primary key (job_id, job_name, job_group)
) engine=innodb CHARSET=utf8  auto_increment=100 comment = '定时任务调度表';

insert into sys_job values(1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams',        '0/10 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');
insert into sys_job values(2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')',  '0/15 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');
insert into sys_job values(3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)',  '0/20 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');
INSERT INTO sys_job VALUES(4, '根据获取单个物流接口获取物流信息', 'DEFAULT', 'dpdTask.dpdTaskGetParcelMsg()', '1 0/1 * * * ?', '1', '1', '1', 'admin', sysdate(), '', null, '');
INSERT INTO sys_job VALUES(5, '根据获取所有物流接口获取物流信息', 'DEFAULT', 'dpdTask.dpdTaskGetAllParcelMsg()', '1 0/1 * * * ?', '1', '1', '1', 'admin', sysdate(), '', null, '此方法无法获取到英文物流信息');
-- ----------------------------
-- 16、定时任务调度日志表
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log (
  job_log_id          bigint(20)     not null auto_increment    comment '任务日志ID',
  job_name            varchar(64)    not null                   comment '任务名称',
  job_group           varchar(64)    not null                   comment '任务组名',
  invoke_target       varchar(500)   not null                   comment '调用目标字符串',
  job_message         varchar(500)                              comment '日志信息',
  status              char(1)        default '0'                comment '执行状态（0正常 1失败）',
  exception_info      varchar(2000)  default ''                 comment '异常信息',
  create_time         datetime                                  comment '创建时间',
  primary key (job_log_id)
) engine=innodb CHARSET=utf8  comment = '定时任务调度日志表';


-- ----------------------------
-- 17、通知公告表
-- ----------------------------
drop table if exists sys_notice;
create table sys_notice (
  notice_id         int(4)          not null auto_increment    comment '公告ID',
  notice_title      varchar(50)     not null                   comment '公告标题',
  notice_type       char(1)         not null                   comment '公告类型（1通知 2公告）',
  notice_content    longblob        default null               comment '公告内容',
  status            char(1)         default '0'                comment '公告状态（0正常 1关闭）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(255)    default null               comment '备注',
  primary key (notice_id)
) engine=innodb CHARSET=utf8  auto_increment=10 comment = '通知公告表';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into sys_notice values('1', '温馨提醒：2018-07-01 新版本发布啦', '2', '新版本内容', '0', 'admin', sysdate(), '', null, '管理员');
insert into sys_notice values('2', '维护通知：2018-07-01 系统凌晨维护', '1', '维护内容',   '0', 'admin', sysdate(), '', null, '管理员');


-- ----------------------------
-- 18、代码生成业务表
-- ----------------------------
drop table if exists gen_table;
create table gen_table (
  table_id          bigint(20)      not null auto_increment    comment '编号',
  table_name        varchar(200)    default ''                 comment '表名称',
  table_comment     varchar(500)    default ''                 comment '表描述',
  sub_table_name    varchar(64)     default null               comment '关联子表的表名',
  sub_table_fk_name varchar(64)     default null               comment '子表关联的外键名',
  class_name        varchar(100)    default ''                 comment '实体类名称',
  tpl_category      varchar(200)    default 'crud'             comment '使用的模板（crud单表操作 tree树表操作）',
  package_name      varchar(100)                               comment '生成包路径',
  module_name       varchar(30)                                comment '生成模块名',
  business_name     varchar(30)                                comment '生成业务名',
  function_name     varchar(50)                                comment '生成功能名',
  function_author   varchar(50)                                comment '生成功能作者',
  gen_type          char(1)         default '0'                comment '生成代码方式（0zip压缩包 1自定义路径）',
  gen_path          varchar(200)    default '/'                comment '生成路径（不填默认项目路径）',
  options           varchar(1000)                              comment '其它生成选项',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (table_id)
) engine=innodb CHARSET=utf8  auto_increment=1 comment = '代码生成业务表';


-- ----------------------------
-- 19、代码生成业务表字段
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column (
  column_id         bigint(20)      not null auto_increment    comment '编号',
  table_id          varchar(64)                                comment '归属表编号',
  column_name       varchar(200)                               comment '列名称',
  column_comment    varchar(500)                               comment '列描述',
  column_type       varchar(100)                               comment '列类型',
  java_type         varchar(500)                               comment 'JAVA类型',
  java_field        varchar(200)                               comment 'JAVA字段名',
  is_pk             char(1)                                    comment '是否主键（1是）',
  is_increment      char(1)                                    comment '是否自增（1是）',
  is_required       char(1)                                    comment '是否必填（1是）',
  is_insert         char(1)                                    comment '是否为插入字段（1是）',
  is_edit           char(1)                                    comment '是否编辑字段（1是）',
  is_list           char(1)                                    comment '是否列表字段（1是）',
  is_query          char(1)                                    comment '是否查询字段（1是）',
  query_type        varchar(200)    default 'EQ'               comment '查询方式（等于、不等于、大于、小于、范围）',
  html_type         varchar(200)                               comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  dict_type         varchar(200)    default ''                 comment '字典类型',
  sort              int                                        comment '排序',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (column_id)
) engine=innodb CHARSET=utf8  auto_increment=1 comment = '代码生成业务表字段';