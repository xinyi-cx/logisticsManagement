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
