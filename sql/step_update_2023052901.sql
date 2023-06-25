create table mb_address_receiver like address_receiver;
-- create table dpd_address_sender like address_sender;
create table mb_batch_task_history like batch_task_history;
create table mb_documents like documents;
create table mb_package like package;
create table mb_parcel like parcel;
-- create table dpd_sequence like sequence;
create table mb_services like services;
create table mb_import_logic_content like import_logic_content;


-- address_receiver
-- address_sender
-- batch_task_history
-- documents
-- package
-- parcel
-- sequence
-- services


alter table sys_user add column master_id varchar(100) default null;
alter table sys_user add column master_pwd varchar(100) default null;


alter table import_logic_content add column box_remark_three varchar(200) default null;
alter table import_logic_content add column box_remark_one varchar(200) default null;
alter table import_logic_content add column box_remark_two varchar(200) default null;


CREATE TABLE `userdeliverinfo` (
	  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
	  `waybill` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户运单号',
	  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户邮箱',
	  `issuedesc` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户问题描述',
	  `inserttime` datetime NOT NULL COMMENT '信息提交时间',
	  `country` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '运单国别',
	  `logistics` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '运单物流商',
	  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='user deliver infomation table'
