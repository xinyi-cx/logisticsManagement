create table accounting.redirect_rel like business.redirect_rel;
INSERT INTO accounting.redirect_rel SELECT * FROM business.redirect_rel;

update redirect_rel
set new_package_id = 999999999
where new_package_id is null;

alter table redirect_rel DROP PRIMARY KEY, ADD PRIMARY key(id,new_package_id);

ALTER TABLE redirect_rel
    PARTITION BY RANGE (new_package_id) (
    PARTITION p0 VALUES LESS THAN (50000),
    PARTITION p1 VALUES LESS THAN (100000),
    PARTITION p2 VALUES LESS THAN (150000),
    PARTITION p3 VALUES LESS THAN (200000),
    PARTITION p4 VALUES LESS THAN (250000),
    PARTITION p5 VALUES LESS THAN (300000),
    PARTITION p6 VALUES LESS THAN (350000),
    PARTITION p7 VALUES LESS THAN (450000),
    PARTITION p8 VALUES LESS THAN (500000),
    PARTITION p9 VALUES LESS THAN (550000),
    PARTITION p10 VALUES LESS THAN (600000),
    PARTITION p11 VALUES LESS THAN (650000),
    partition p999 values less than MAXVALUE
    );
