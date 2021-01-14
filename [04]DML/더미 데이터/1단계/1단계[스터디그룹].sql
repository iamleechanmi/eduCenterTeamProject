-- 1단계[스터디그룹].sql

-- 스터디그룹.tblstudygroup(seq, goal)
-- 10개 그룹 목표
select * from tblstudygroup;

-- 개설과정 6
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '프로젝트', '2020-01-06', '2020-03-06');
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '코딩공부', '2020-03-09', '2020-05-08');
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '코딩공부', '2020-05-11', '2020-07-10');

-- 개설과정 9
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '오라클공부', '2020-10-30', '2020-12-29');

-- 개설과정 12
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, 'FRONT-END공부', '2020-11-09', '2020-01-08');
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '애플리케이션구현', '2020-11-19', '2020-01-08');
