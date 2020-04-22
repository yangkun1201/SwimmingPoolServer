create table user(
    id int primary key auto_increment,
    username varchar(100) comment '用户名',
    phone varchar(20) unique comment '手机号',
    password varchar(100) comment '密码',
    gender int comment '性别 0:男, 1:女',
    type int comment '用户类别 0:普通用户, 1:管理员',
    card_type int default 0 comment '票卡类型',
    integral int default 0 comment '积分'
)charset utf8;

create table checkin(
    id int primary key auto_increment,
    phone varchar(20),
    check_time timestamp comment '出入馆时间',
    check_flag int comment '0:入馆,1:出馆',
    vercode varchar(10) comment '验证码'
)charset utf8;

create table card_type(
    id int primary key auto_increment,
    type int unique comment '票卡类型',
    name varchar(50) comment '票卡名称',
    description varchar(200) comment '票卡描述',
    price int comment '票卡价格',
    pic_url varchar(200) comment '票卡图片地址'
)charset utf8;

create table commodity(
    id int primary key auto_increment,
    name varchar(200) comment '商品名称',
    price int comment '价格',
    pic_url varchar(200) comment '商品图片地址'
)charset utf8;

create table exchange_records(
    id int primary key auto_increment,
    user_id int comment '用户id',
    commodity_id int comment '商品id',
    time timestamp comment '兑换时间',
    address varchar(300) comment '地址'
)charset utf8;