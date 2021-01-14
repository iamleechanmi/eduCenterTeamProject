-- 3단계[수강].sql

-- 수강 tblsignup(seq, openCourseSeq, studentseq, status, enddate)
-- 30명 : 오픈과정 1, 2, 3, 7, 9, 11, 13, 15, 16
-- 26명 : 오픈과정 4, 5, 6, 8, 10, 12, 14, 17, 18

/*

 진행 과정(168명)
 7 -> 1강의실 : 30명
 8 -> 4강의실 : 26명
 9 -> 2강의실 : 30명
 10 -> 5강의실 : 26명
 11 -> 3강의실 : 30명
 12 -> 6강의실 : 26명


 종료 과정  => 과정 종료된 학생 20명
 진행 중인 과정 => 과정 진행 중인 학생 168명
 예정 과정 -> 과전 예전인 학생 12명
=============================> 총 200명



*/


-- 완료
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 1, 1, '종료','2020-06-15');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 1, 2, '종료','2020-06-15');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 1, 3, '종료','2020-03-10'); -- 중도탈락

insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 2, 4, '종료','2020-07-06');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 2, 5, '종료','2020-07-06');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 2, 6, '종료','2020-07-06');

insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 3, 7, '종료','2020-08-17');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 3, 8, '종료','2020-08-17');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 3, 9, '종료','2020-08-17');

insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 4, 10, '종료','2020-06-15');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 4, 11, '종료','2020-06-15');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 4, 12, '종료','2020-04-20');-- 중도탈락

insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 5, 13, '종료','2020-07-06');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 5, 14, '종료','2020-07-06');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 5, 15, '종료','2020-07-06');

insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 6, 16, '종료','2020-08-17');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 6, 17, '종료','2020-08-17');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 6, 18, '종료','2020-08-17');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 6, 19, '종료','2020-08-17');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 6, 20, '종료','2020-08-17');




-- 진행
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 21, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 22, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 23, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 24, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 25, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 26, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 27, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 28, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 29, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 30, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 31, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 32, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 33, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 34, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 35, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 36, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 37, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 38, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 39, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 40, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 41, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 42, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 43, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 44, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 45, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 46, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 47, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 48, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 7, 49, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 7, 50, '종료', '2020-10-12'); -- 중도탈락

insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 51, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 52, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 53, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 54, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 55, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 56, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 57, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 58, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 59, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 60, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 61, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 62, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 63, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 64, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 65, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 66, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 67, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 68, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 69, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 70, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 71, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 72, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 73, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 74, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 75, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 8, 76, '진행');

insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 77, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 78, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 79, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 80, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 81, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 82, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 83, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 84, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 85, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 86, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 87, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 88, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 89, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 90, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 91, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 92, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 93, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 94, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 95, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 96, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 97, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 98, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 99, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 100, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 101, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 102, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 103, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 104, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 9, 105, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 9, 106, '종료', '2020-10-05'); -- 중도 탈락

insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 107, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 108, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 109, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 110, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 111, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 112, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 113, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 114, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 115, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 116, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 117, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 118, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 119, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 120, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 121, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 122, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 123, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 124, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 125, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 126, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 127, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 128, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 129, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 130, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 131, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 10, 132, '진행');

insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 133, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 134, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 135, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 136, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 137, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 138, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 139, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 140, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 141, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 142, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 143, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 144, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 145, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 146, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 147, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 148, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 149, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 150, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 151, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 152, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 153, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 154, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 155, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 156, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 157, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 158, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 159, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 160, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 11, 161, '진행');
insert into tblSignUp(seq ,openCourseSeq, studentSeq, status, endDate) values (SEQSIGNUP.nextval, 11, 162, '종료', '2020-11-02');

insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 163, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 164, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 165, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 166, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 167, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 168, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 169, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 170, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 171, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 172, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 173, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 174, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 175, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 176, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 177, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 178, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 179, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 180, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 181, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 182, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 183, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 184, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 185, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 186, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 187, '진행');
insert into tblSignUp(seq, openCourseSeq, studentSeq, status) values (SEQSIGNUP.nextval, 12, 188, '진행');



-- 예정
insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 13, 189);
insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 13, 190);

insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 14, 191);
insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 14, 192);

insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 15, 193);
insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 15, 194);

insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 16, 195);
insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 16, 196);

insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 17, 197);
insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 17, 198);

insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 18, 199);
insert into tblSignUp(seq, openCourseSeq, studentSeq) values (SEQSIGNUP.nextval, 18, 200);

