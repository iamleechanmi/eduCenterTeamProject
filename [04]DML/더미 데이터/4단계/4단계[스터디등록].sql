-- 4단계[스터디등록].sql

-- 스터디등록. tblstudyreservation(seq, registrationSeq, studyroomSeq. reservationDate)


-- 끝난 스터디
-- 1, '프로젝트', '2020-01-06', '2020-03-06'
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 1, 16,'팀장');--1
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 1, 17,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 1, 18,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 1, 19,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 1, 20,'팀원');--5

-- 2, '코딩공부', '2020-03-09', '2020-05-08'
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 2, 16,'팀장');--6
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 2, 17,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 2, 18,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 2, 19,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 2, 20,'팀원');--10

-- 3,'코딩공부', '2020-05-11', '2020-07-10'
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 3, 16,'팀장');--11
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 3, 17,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 3, 18,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 3, 19,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 3, 20,'팀원');--15


-- 진행 중인 스터디
--4, '오라클공부', '2020-10-30', '2020-12-29'
--select * from tblSignUp where opencourseSeq = 8;
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 4, 51,'팀장');--16
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 4, 52,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 4, 53,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 4, 54,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 4, 55,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 4, 56,'팀원');--21

-- 5, 'FRONT-END공부', '2020-11-09', '2020-01-08'
--select * from tblSignUp where opencourseSeq = 9;
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 5, 79,'팀장');--22
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 5, 80,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 5, 81,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 5, 82,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 5, 83,'팀원');--26

-- 6, '애플리케이션구현', '2020-11-19', '2020-01-08'
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 6, 84,'팀장');--27
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 6, 85,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 6, 86,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 6, 87,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 6, 88,'팀원');
insert into tblstudygroupregistration (seq,studyGroupSeq,signUpSeq,groupRank) values (SEQSTUDYGROUPREGISTRATION.nextval, 6, 89,'팀원');--32
