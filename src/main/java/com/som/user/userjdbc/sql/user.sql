drop table userinfo;

create table userinfo(
username varchar(40) not null primary key,
passwd varchar(200) not null,
sex int(2),
mobile varchar(20) not null,
age int (3) ,
hight int(3),
address varchar(200)
);

insert into userinfo(username,passwd,sex,mobile,age,hight,address)
values('test2','test2',1,'13912345678',23,180,'上海');


insert into userinfo(username,passwd,sex,mobile,age,hight,address)
values(?,?,?,?,?,?,?);