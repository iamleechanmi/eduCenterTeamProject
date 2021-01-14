-- 1단계[강의실].sql

-- 강의실.tblclassroom(seq, name, personnel)
-- 6개 강의실(1,2,3 강의실 정원 30명/4,5,6 강의실 정원 26명)
select * from tblclassroom;

insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '1강의실', 30);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '2강의실', 30);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '3강의실', 30);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '4강의실', 26);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '5강의실', 26);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '6강의실', 26);
