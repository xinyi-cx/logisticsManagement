alter table sys_user add column api_key varchar(100) default null;
alter table sys_user add column api_account_id bigint(20) default null;
update sys_user
set
api_account_id = 29689,
api_key = '2675166dc4b2242bf88c3ea25a452b3f'
where user_id = 1;


drop table if exists mb_return_dto;
create table mb_return_dto
(
    code                  varchar(500) default null comment '内部订单号',
    customize_code        varchar(500) default null comment '_e_r_p自定义单号',
    platform_trade_code   varchar(500) default null comment '平台订单编号：例如ebay,wish 等订单编号',
    status                varchar(500) default null comment '订单状态',
    has_exception         varchar(500) default null comment '是否有异常',
    process_message       varchar(500) default null comment '异常信息，物流供应商处理信息',
    package_id            varchar(500) default null comment 'package _id',
    price_forcast         varchar(500) default null comment '预计订单运费',
    price_real            varchar(500) default null comment '实际订单运费',
    shipping_country_code varchar(500) default null comment '包裹地址国家代码',
    time_created          varchar(500) default null comment '订单生成时间',
    weight_forcast        varchar(500) default null comment '客户预报订单重量（单位：g 克）',
    weight_real           varchar(500) default null comment '供应商实际称重订单重量（单位：g 克）',
    length                varchar(500) default null comment '包裹长度（单位：mm 毫米）',
    width                 varchar(500) default null comment '包裹宽度（单位：mm 毫米）',
    height                varchar(500) default null comment '包裹高度（单位：mm 毫米）',
    product_name_cn       varchar(500) default null comment '订单申报物品中文名',
    product_name_en       varchar(500) default null comment '订单申报物品英文名',
    product_value         varchar(500) default null comment '订单申报价值(US_d)',
    remark                varchar(500) default null comment '备注',
    item_list_quantity    varchar(500) default null comment ' 货物数量',
    item_list_count       varchar(500) default null comment ' 订单项数量',
    currency_code         varchar(500) default null comment ' 申报币种',
    platform_pay_time     varchar(500) default null comment '交易平台付款时间',
    AUS_details           TEXT         default null comment '{\ _vendor_i_d\ :\ 澳洲GS_t供应商_i_d\\ abnnumber\ :\ 税号\ }',
    cod_flag              varchar(500) default null comment 'cod 订单标识  1:是;2:否',
    cod_value             varchar(500) default null comment 'cod 订单金额（币种为买家当地币种）',
    vat_no                varchar(500) default null comment '商家税号',
    vat_type              varchar(500) default null comment '商家税号类型 1.欧盟国_iOSS 编号；2.非_iOSS或其他国家税号 编号；3.空表示other',
    has_battery           varchar(500) default null comment '订单是否带电 1.是 2.否',
    has_magnetic          varchar(500) default null comment '订单是否含磁 1.是 2.否',
    has_powder            varchar(500) default null comment '订单是否含粉末 1.是 2.否',
    customer              varchar(500) default null comment '',
    express_channel_code  varchar(500) default null comment '物流产品运单号',
    supplier_inner_code   varchar(500) default null comment '物流商内部单',
    express_channel       varchar(500) default null comment '',
    my_express_channel    varchar(500) default null comment '',
    label_HTML_url        varchar(500) default null comment '',
    label_PDF_url         varchar(500) default null comment '',
    extend_fields_str     TEXT         default null comment '',
    item_list_str         TEXT         default null comment '商品SKU',
    address_pickup_str    TEXT         default null comment '发件人',
    address_receive_str   TEXT         default null comment '收件人',
    address_back_str      TEXT         default null comment '退件人',
    create_by             varchar(64)  default '' comment '创建者',
    create_time           datetime comment '创建时间',
    update_by             varchar(64)  default '' comment '更新者',
    update_time           datetime comment '更新时间'
) engine = innodb
  CHARSET = utf8
  auto_increment = 100 comment = '马帮订单信息';

drop table if exists user_authorization;
create table user_authorization
(
    user_id     bigint(20)   not null auto_increment comment '用户ID',
    user_name   varchar(30)  not null comment '用户授权账号',
    user_token  varchar(100) not null comment '用户授权token',
    is_delete   tinyint(1)   DEFAULT '0' comment '是否删除',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (user_id)
) engine = innodb
  CHARSET = utf8
  auto_increment = 100 comment = '用户授权信息表';