@echo off
echo 设置MySql数据库的连接信息
set host=127.0.0.1
set port=3306
set user=root
set pass=1234
echo 设置要备份MySql数据库名称
set dbname=business
echo 获取当天的日期格式，例如：2022-12-13-14-30-40

set backup_date=%date:~0,4%-%date:~5,2%-%date:~8,2%-%time:~0,2%-%time:~3,2%-%time:~6,2%
echo 设置备份文件的路径
set backupfile=C:\sqlBactup\%dbname%-%backup_date%.sql
echo 使用mysqldump对指定的MySql进行备份
echo 注意路径中有空格的要加上双引号
"D:\work\mysql-5.7.36-winx64\bin\mysqldump" -h%host% -P%port% -u%user% -p%pass% -c --add-drop-table %dbname% > %backupfile%
echo 删除过期文件，这里是超过30天就删除
forfiles /p D:\sqlBactup\ /s /m *.sql /d -30 /c "cmd /c del @file /f"
exist
