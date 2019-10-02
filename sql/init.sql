#项目使用flywaydb进行数据库迁移，配置好application.properties里面关于flyway的字段后启动即可自动完成建库操作。
#所有建库的sql在resource/migration目录下
#在resource/migration下建立 V[版本]__XX.sql 后将修改数据库的语句写入，启动后自动迁移。