-- 5단계[스터디룸예약].sql

-- tblStudyReservation(seq, registrationseq, studyroomseq, reservationdate)
-- 스터디룸예약(tblStudyReservation) 더미

-- select * from tblstudyreservation;
--  1, '프로젝트', '2020-01-06', '2020-03-06' 팀장 seq = 1
insert into tblStudyReservation values (seqStudyReservation.nextVal, 1, 5, to_date('2020-01-06', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 1, 4, to_date('2020-01-21', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 1, 2, to_date('2020-03-03', 'yyyy-mm-dd'));

-- 2, '코딩공부', '2020-03-09', '2020-05-08' 팀장 seq = 6
insert into tblStudyReservation values (seqStudyReservation.nextVal, 6, 5, to_date('2020-03-17', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 6, 1, to_date('2020-03-20', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 6, 2, to_date('2020-04-22', 'yyyy-mm-dd'));

-- 3,'코딩공부', '2020-05-11', '2020-07-10' 팀장 seq = 11
insert into tblStudyReservation values (seqStudyReservation.nextVal, 11, 4, to_date('2020-05-20', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 11, 1, to_date('2020-06-05', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 11, 4, to_date('2020-06-29', 'yyyy-mm-dd'));

-- 4, '오라클공부', '2020-10-30', '2020-12-29' 팀장 seq = 16
insert into tblStudyReservation values (seqStudyReservation.nextVal, 16, 5, to_date('2020-11-11', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 16, 4, to_date('2020-11-17', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 16, 2, to_date('2020-12-08', 'yyyy-mm-dd'));

--  5, 'FRONT-END공부', '2020-11-09', '2020-01-08' 팀장 seq = 22
insert into tblStudyReservation values (seqStudyReservation.nextVal, 22, 4, to_date('2020-11-19', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 22, 3, to_date('2020-12-01', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 22, 5, to_date('2020-12-14', 'yyyy-mm-dd'));

-- 6, '애플리케이션구현', '2020-11-19', '2020-01-08' 팀장 seq = 27
insert into tblStudyReservation values (seqStudyReservation.nextVal, 27, 2, to_date('2020-11-27', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 27, 3, to_date('2020-11-30', 'yyyy-mm-dd'));
insert into tblStudyReservation values (seqStudyReservation.nextVal, 27, 5, to_date('2020-12-18', 'yyyy-mm-dd'));