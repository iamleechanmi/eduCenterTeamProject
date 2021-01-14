-- 1단계[스터디룸].sql

-- 스터디룸.tblstudyroom(seq, name)
select * from tblstudyroom;

insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '1번방');
insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '2번방');
insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '3번방');
insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '4번방');
insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '5번방');

