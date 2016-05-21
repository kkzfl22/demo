create table TEST_PATTERN(
text varchar2(2000) not null,
info varchar2(200)
);

--插入基础数据
insert into TEST_PATTERN(text,Info)
values('你好，这里是瑞原','无');
insert into TEST_PATTERN(text,Info)
values('你好，这里是上海交大学校','无');
insert into TEST_PATTERN(text,Info)
values('这里是某某公司 请问有资金需要吗？','无');

select * from TEST_PATTERN where  regexp_like(text,'(你|干).{0,10}((瑞原)|(学校))'); 
