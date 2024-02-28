CREATE INDEX index_parcel_waybill ON parcel (waybill);
CREATE INDEX index_parcel_pack_id ON parcel (pack_id);
CREATE INDEX index_parcel_package_id ON parcel (package_id);

CREATE INDEX index_import_logic_content_new_waybill ON import_logic_content (new_waybill);
CREATE INDEX index_import_logic_content_document_file_id ON import_logic_content (document_file_id);
CREATE INDEX index_import_logic_content_document_pack_id ON import_logic_content (pack_id);
CREATE INDEX index_import_logic_content_document_batch_id ON import_logic_content (batch_id);
CREATE INDEX index_import_logic_content_document_document_file_id ON import_logic_content (document_file_id);

CREATE INDEX index_logistics_info_receiver_id ON logistics_info (waybill);
CREATE INDEX index_logistics_info_package_id ON logistics_info (package_id);
CREATE INDEX index_logistics_info_parcel_id ON logistics_info (parcel_id);

CREATE INDEX index_batch_task_history_excel_url ON batch_task_history (excel_url);


CREATE INDEX index_redirect_rel_new_package_id ON redirect_rel (new_package_id);
CREATE INDEX index_pack_rel_local_old_package_id ON pack_rel_local (old_package_id);

CREATE INDEX index_cod_report_waybill ON cod_report (waybill);

CREATE INDEX index_package_receiver_id ON package (receiver_id);
CREATE INDEX index_package_batch_id ON package (batch_id);
CREATE INDEX index_package_services_id ON package (services_id);
CREATE INDEX index_package_parcel_reference ON package (parcel_reference);

CREATE INDEX index_mb_return_dto_platform_trade_code ON mb_return_dto (platform_trade_code);


-- 20230803

CREATE INDEX index_packages_generation_response_pack_id ON packages_generation_response (pack_id);
CREATE INDEX index_package_sender_id ON package (sender_id);

