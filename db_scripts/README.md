# 数据库更新流程
1. 在终端运行如下命令（就是运行sql脚本）：
`mysql -u root -p < ./schema.sql`
`mysql -u root -p < ./testdata.sql`
2. 按需求修改数据库
3. 更新完成后在终端运行如下命令(注意：导出脚本时，不要在powershell中运行，否则会出现乱码)：
* 导出结构脚本
` mysqldump -u root -p --no-data --default-character-set=utf8mb4 shec_psims > schema.sql`
* 导出数据脚本
`mysqldump -u root -p --no-create-info --default-character-set=utf8mb4 shec_psims > testdata.sql`