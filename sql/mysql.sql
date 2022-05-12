create table if not exists songshu.user
(
	id bigint auto_increment
		primary key,
	username varchar(256) null,
	userAccount varchar(256) null,
	avatarUrl varchar(1024) null,
	gender tinyint null,
	userPassword varchar(512) not null,
	phone varchar(128) null,
	email varchar(512) null,
	userStatus int default 0 not null,
	createTime datetime default CURRENT_TIMESTAMP null,
	updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
	isDelete tinyint default 0 not null,
	userPermission int default 1 not null comment '0-admin 1-user'
)
comment '用户';