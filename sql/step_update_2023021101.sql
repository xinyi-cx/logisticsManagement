drop table if exists user_fid_rel;
CREATE TABLE user_fid_rel
(
    id            bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    user_id       bigint(20) not null auto_increment comment '用户ID',
    user_name     varchar(30)  default null comment '用户账号',
    customer_name varchar(30)  default null comment '用户账号',
    nick_name     varchar(30)  default null comment '用户昵称',
    country       varchar(100) default NULL comment '国家',
    fid           bigint(20) DEFAULT NULL comment 'fid',
    fid_common    varchar(200) DEFAULT NULL comment 'fid备注',
    remark        varchar(200) DEFAULT NULL comment '备注1',
    remark2       varchar(200) DEFAULT NULL comment '备注2',
    is_active     tinyint(1) DEFAULT '0' comment '是否激活',
    create_by     varchar(64)  default '' comment '创建者',
    create_time   datetime comment '创建时间',
    update_by     varchar(64)  default '' comment '更新者',
    update_time   datetime comment '更新时间',
    PRIMARY KEY (id)
) engine = innodb
  CHARSET = utf8
  auto_increment = 1 comment = '用户fid对应关系';



alter table sys_user
    add column customer_name varchar(30)  default null comment '用户账号';


CREATE INDEX index_parcel_waybill ON parcel (waybill);

CREATE INDEX index_parcel_package_id ON parcel (package_id);


CREATE INDEX index_import_logic_content_new_waybill ON import_logic_content (new_waybill);
CREATE INDEX index_import_logic_content_document_file_id ON import_logic_content (document_file_id);
CREATE INDEX index_import_logic_content_document_pack_id ON import_logic_content (pack_id);
CREATE INDEX index_import_logic_content_document_batch_id ON import_logic_content (batch_id);
CREATE INDEX index_import_logic_content_document_document_file_id ON import_logic_content (document_file_id);


CREATE INDEX index_package_receiver_id ON package (receiver_id);
CREATE INDEX index_package_batch_id ON package (batch_id);
CREATE INDEX index_package_services_id ON package (services_id);

CREATE INDEX index_logistics_info_receiver_id ON logistics_info (waybill);
CREATE INDEX index_logistics_info_package_id ON logistics_info (package_id);
CREATE INDEX index_logistics_info_parcel_id ON logistics_info (parcel_id);


CREATE INDEX index_batch_task_history_excel_url ON batch_task_history (excel_url);
