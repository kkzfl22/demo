create table TEST_PATTERN(
text varchar2(2000) not null,
info varchar2(200)
);

--�����������
insert into TEST_PATTERN(text,Info)
values('��ã���������ԭ','��');
insert into TEST_PATTERN(text,Info)
values('��ã��������Ϻ�����ѧУ','��');
insert into TEST_PATTERN(text,Info)
values('������ĳĳ��˾ �������ʽ���Ҫ��','��');

select * from TEST_PATTERN where  regexp_like(text,'(��|��).{0,10}((��ԭ)|(ѧУ))'); 
