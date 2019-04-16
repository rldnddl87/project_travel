CREATE TABLE SCHEDULE(
	sch_no number, 	--���� ��ȣ(pk)
	user_no number not null, --ȸ����ȣ(����)
	start_date date not null, --���� ���� ����
	end_date date not null, --���� ���� ����
	sch_title varchar2(45) not null, --���� ����
	sch_content varchar2(3000) not null, --������ ���� ����,�޸�
	sch_review varchar2(3000) , --���������� �ı�
	constraint schedule_sch_no_pk primary key(sch_no), --sch_no�� pk�� ����
	constraint schedule_user_no_fk foreign key(user_no) references client(user_no)
	--user_no�� fk�� ���� user ���̺��� user_no �����ϵ���
) --���� �Ϸ�

create table sch_detail(
	detail_no number, --���� ���� ��ȣ(pk)
	user_no number not null,-- ȸ����ȣ(����)
	sch_no number ,	--���� ��ȣ (����)
	travel_no number not null,--���� ��ȣ(����)
	festival_no number not null, --���� ��ȣ(����)
	det_content varchar2(3000), --���� ���� ����
	det_start date, --���� ���� ���� �ð�(�Ⱦ����� ����)
	det_end date, --���� ���� ���� �ð�(�Ⱦ����� ����)
	det_date date, --���� ���� ��¥
	constraint detail_user_no_fk foreign key(user_no) references client(user_no), 
	constraint detail_no_pk primary key(detail_no), --���� ���� ��ȣ pk�� ����
	constraint detail_sch_no_fk foreign key(sch_no) references schedule(sch_no), --sch_no ����Ű�� ����
	constraint detail_travel_no_fk foreign key(travel_no) references travel_information(travel_no), --travel_no ����Ű�� ����
	constraint detail_festival_no_fk foreign key(festival_no) references festival_information(festival_no) --festival_no ����Ű�� ����	
)

create table pocket(
	user_no number not null, --fk
	pocket_no number, --pk
	travel_no number, --fk
	festival_no, --fk
	constraint pocket_user_no_fk foreign key(user_no) references client(user_no),
	constraint pocket_no_pk primary key(pocket_no),
	constraint pocket_travel_no_fk foreign key(travel_no) references travel_information(travel_no),
	constraint pocket_festival_no_fk foreign key(festival_no) references festival_information(festival_no)
	)


create table client(user_no number primary key) --���� �Ϸ�
alter table client add(user_id varchar2(50) not null);

drop table travel_information
drop table festival_information
drop table client
drop table sch_detail
drop table schedule
drop table pocket


create table travel_information(
    travel_no number,
    travel_name varchar2(40) not null,
    travel_content varchar2(400) not null,
    travel_address varchar2(300) not null,
    travel_location varchar2(50) not null,
    travel_tema number not null,
    primary key(travel_no)
 );
alter table sch_detail modify festival_no null;
drop table pocket
drop table festival_information
create table festival_information(
    festival_no number,
    festival_name varchar2(30) not null,
    festival_startday date,
    festival_endday date,
    festival_location varchar2(50) not null,
    festival_address varchar2(50) not null,
    festival_content varchar2(4000),
    primary key (festival_no)
 );
 
 create sequence schedule_seq 
 start with 1000
 increment by 1
 create sequence sch_detail_seq
 start with 2000
 increment by 2
 
 create sequence pocket_seq
 start with 1000
 increment by 1
 
 select schedule_seq.nextval from dual
 select schedule_seq.currval from dual
 insert into schedule (sch_no, user_no, start_date, end_date, sch_title, sch_content)
 values(schedule_seq.nextval ,  1 , '2018-06-01' ,'2018-06-02','���ֵ�', '�����' )
 
 alter table sch_detail add(det_title varchar2(400))
 
 select max(sch_no) from schedule
 
select travel_no , travel_name, travel_content, travel_Address, travel_location, travel_tema
from travel_information natural join sch_Detail where sch_no = 1166
 


select sch_no,user_no,start_Date,end_date,sch_Title,sch_content,sch_review from schedule where user_no = 1  order by sch_no desc
select count(*) from travel_information natural join pocket where user_no = 1

select * from  (select rownum rnum, sch_no, user_no, start_Date,  end_date, sch_title, sch_content, sch_review from 
(select sch_no,user_no,start_Date,end_date,sch_Title,sch_content,sch_review from schedule where user_no = 1 order by start_date desc)) where rnum between 2 and 3 

select * from (select rownum rnum, festival_no, Festival_name, Festival_startday, Festival_endday, Festival_location, 
Festival_address, Festival_content from  
(select festival_no, Festival_name, Festival_startday, Festival_endday, Festival_location, Festival_address, 
Festival_content  from festival_information natural join pocket where user_no = 1 order by pocket_no))
where rnum between 1 and 4 



select count(*) from sch_detail where user_no = 1

select * from 
(select rownum rnum, det_no, user_no, sch_no, travel_no, festival_no,  det_content, det_start, det_end, det_Date, det_title from 
(select det_no, user_no, sch_no, travel_no, festival_no, det_content, det_start, det_end, det_date, det_title from sch_detail order by det_no desc)) 
where rnum between ? and ?

select * from (select rownum rnum, detail_no, user_no, sch_no, travel_no, festival_no,  det_content, det_start, det_end, det_Date, det_title from (select detail_no, user_no, sch_no, travel_no, festival_no, det_content, det_start, det_end, det_date, det_title from sch_detail order by detail_no desc)) where rnum between ? and ?
select * from pocket

insert into pocket (user_no, pocket_no ,travel_no) values ( ? , pocket_no.nextval ,? );
insert into pocket (useR_no, pocket_no, festival_no) values (? , pocket_no.nextval ,? );


create table client(
	user_no			number primary key,
	user_id			varchar2(20) unique,
	user_passwd		varchar2(40) not null,
	user_name		varchar2(20) not null,
	user_email		varchar2(50) unique,
	user_type		number not null
)



drop table client