-- 2단계[개설과정].sql


-- 종료
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 1, 1,'2020-01-06', '2020-06-14', '종료');--160
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 2, 2,'2020-01-06', '2020-07-04', '종료');--180
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 3, 3,'2020-01-06', '2020-08-13', '종료');--220
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 4, 4,'2020-01-06', '2020-06-14', '종료');--160
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 5, 5,'2020-01-06', '2020-07-04', '종료');--180
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 6, 6,'2020-01-06', '2020-08-13', '종료');--220

-- 진행
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 9, 1, '2020-06-15', '2021-01-21', '진행');-- 220
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 6, 4, '2020-06-15', '2021-01-21', '진행');-- 220
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 8, 2, '2020-07-06', '2021-01-02', '진행');-- 180
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 2, 5, '2020-07-06', '2021-01-02', '진행');-- 180
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 10, 3, '2020-08-17', '2021-01-24', '진행');-- 160
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate, status) values (seqOpenCourse.nextval, 1, 6, '2020-08-17', '2021-01-24', '진행');-- 160

-- 예정
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate) values (seqOpenCourse.nextval, 1, 2, '2021-01-04', '2021-06-13');--160 
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate) values (seqOpenCourse.nextval, 2, 5, '2021-01-04', '2021-07-03');--180
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate) values (seqOpenCourse.nextval, 3, 1, '2021-01-25', '2021-09-02');--220
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate) values (seqOpenCourse.nextval, 4, 3, '2021-01-25', '2021-07-04');--160
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate) values (seqOpenCourse.nextval, 5, 4, '2021-01-25', '2021-07-24');--180 
insert into tblopencourse (seq,courseSeq, classroomSeq, startDate, endDate) values (seqOpenCourse.nextval, 6, 6, '2021-01-25', '2021-09-02');--220

