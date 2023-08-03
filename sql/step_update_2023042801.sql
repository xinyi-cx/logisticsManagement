drop table if exists cod_report;
CREATE TABLE `cod_report`
(
    `id`                            bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `document_file_id`              bigint(20)   DEFAULT NULL comment 'document表id',
    `pack_id`                       bigint(20)   DEFAULT NULL comment 'package表id',
    `batch_id`                      bigint(20)   DEFAULT NULL comment 'batch_id',
    `logic_id`                      bigint(20)   DEFAULT NULL comment 'logic_id',
    `client`                        varchar(200) DEFAULT NULL comment 'CLIENT',
    `country`                       varchar(200) DEFAULT NULL comment 'Country',
    `import_type`                   varchar(200) DEFAULT NULL comment 'Type',
    `loginId`                       varchar(200) DEFAULT NULL comment 'LoginID',

    `nr_klienta`                    varchar(200) DEFAULT NULL comment 'nr_klienta',
    `download_recipient`            varchar(200) DEFAULT NULL comment '下载收件人',
    `waybill`                       varchar(200) DEFAULT NULL comment '运单号码（货件）',
    `waybill_posting_date`          varchar(200) DEFAULT NULL comment '运单（过账）日期',
    `cash_on_delivery`              varchar(200) DEFAULT NULL comment '货到付款金额 （货到付款）',
    `goods_effective_delivery_date` varchar(200) DEFAULT NULL comment '货件有效交付日期',
    `addressee`                     varchar(200) DEFAULT NULL comment '货件收件人',
    `postal_code`                   varchar(200) DEFAULT NULL comment '邮政编码',
    `area`                          varchar(200) DEFAULT NULL comment '地区',
    `street`                        varchar(200) DEFAULT NULL comment '街',
    `unisoft_Client_content_field`  varchar(500) DEFAULT NULL comment 'Unisoft-Client中的内容字段',
    `batch_transmission`            varchar(200) DEFAULT NULL comment '批量传输',
    `collective_transfer_date`      varchar(200) DEFAULT NULL comment '集体移送日期',
    `settlement_number`             varchar(200) DEFAULT NULL comment '结算编号 （怀俄明州）',

    `sys_actived_date`              varchar(200) DEFAULT NULL comment '激活时间',
    `sys_last_status_date`          varchar(200) DEFAULT NULL comment '最近物流时间',
    `sys_sign_date`                 varchar(200) DEFAULT NULL comment '签收时间',

    `remark`                        varchar(200) DEFAULT NULL comment '备注1',
    `remark2`                       varchar(200) DEFAULT NULL comment '备注2',
    `remark3`                       varchar(200) DEFAULT NULL comment '备注3',
    `remark4`                       varchar(200) DEFAULT NULL comment '备注4',
    `remark5`                       varchar(200) DEFAULT NULL comment '备注5',
    `is_delete`                     tinyint(1)   DEFAULT '0' comment '是否删除',
    `create_by`                     varchar(64)  default '' comment '创建者',
    `create_time`                   datetime comment '创建时间',
    `update_by`                     varchar(64)  default '' comment '更新者',
    `update_time`                   datetime comment '更新时间',
    PRIMARY KEY (`id`)
) engine = innodb
  CHARSET = utf8
  auto_increment = 200 comment = 'COD报告表';

INSERT INTO business.sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES (13, '报告管理', 0, 4, 'report', null, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2023-05-02 17:58:27', '', null, '报告管理目录');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('COD报告', '13', '1', 'report', 'system/report/index', 1, 0, 'C', '0', '0', 'system:report:list', '#', 'admin', sysdate(), '', null, 'COD报告菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('COD报告查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:report:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('COD报告新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:report:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('COD报告修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:report:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('COD报告删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:report:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('COD报告导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:report:export',       '#', 'admin', sysdate(), '', null, '');
