--数据库初始化脚本
CREATE DATABASE IF NOT EXISTS panicbuy  DEFAULT CHARACTER SET utf8;
--使用数据库
USE panicbuy;
--创建商品库存表
CREATE TABLE IF NOT EXISTS stock(
	stock_id  BIGINT NOT NULL AUTO_INCREMENT COMMENT'商品id',
	name  VARCHAR(120) NOT NULL COMMENT'商品名称',
	number int NOT NULL COMMENT'商品数量',
	start_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT'抢购开始时间',
	end_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT'抢购结束时间',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT'记录创建时间',
	PRIMARY KEY(stock_id),
	KEY idx_start_time(start_time),
	KEY idx_end_time(end_time),
	KEY idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHAR SET =utf8 COMMENT'库存明细表';
insert into stock (name,number,start_time,end_time) 
values("1000元抢购iphone6s",100,"2016-11-11 00:00:00","2016-11-12 00:00:00"),
("800元抢购ipad",50,"2016-11-13 00:00:00","2016-11-14 00:00:00"),
("600元抢购小米MIX",200,"2016-11-13 21:00:00","2016-11-12 22:00:00"),
("400元抢购红米4",400,"2016-11-11 12:00:00","2016-11-12 13:00:00");
CREATE TABLE IF NOT EXISTS kill_success(
	kill_stock_id  BIGINT  NOT NULL  COMMENT'秒杀成功的商品id',
	user_phone BIGINT NOT NULL COMMENT'联系方式',
	state TINYINT NOT NULL DEFAULT -1 COMMENT'-1表示没抢购成功，0表示已成功抢购，1表示已经成功付款',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT'记录创建时间', 
	PRIMARY KEY(kill_stock_id,user_phone),
	KEY idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHAR SET =utf8 COMMENT'抢购成功明细';
