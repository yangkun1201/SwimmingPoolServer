create table user(
    id int primary key auto_increment,
    username varchar(100),
    phone varchar(20),
    password varchar(100),
    gender int,
    type int
)charset utf8;

create table checkin(
    id int primary key auto_increment,
    phone varchar(20),
    check_time timestamp comment '出入馆时间',
    check_flag int comment '0:入馆,1:出馆',
    vercode varchar(10) comment '验证码'
)charset utf8;
