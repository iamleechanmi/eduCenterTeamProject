-- 프로젝트 create문 4조

-- 1. 관리자
create table tblManager
(
    seq number primary key,
    ssn number not null,
    name varchar2(15) not null

); 

-- 2. 교사
create table tblTeacher
(
    seq number primary key,
    name varchar2(15) not null,
    ssn varchar2(7) not null,
    tel varchar2(11) not null
);


-- 3. 과목
create table tblSubject
(
    seq number primary key,
    name varchar2(100) not null,
    period number not null
);



-- 4. 교사가능과목(tblPossibleSubject)
create table tblPossibleSubject
(
    seq number primary key,
    subjectSeq number not null references tblSubject(seq),
    teacherSeq number not null references tblTeacher(seq)
);


-- 5. 과정(tblCourse)
create table tblCourse
(
    seq number primary key,
    name varchar2(100) not null,
    goal varchar2(200) not null,
    period number not null
);


-- 6.과정포함과목(tblCourseSubject)
create table tblCourseSubject
(
    seq number primary key,
    subjectSeq number not null references tblSubject(seq),
    courseSeq number not null references tblCourse(seq)
);


-- 7.강의실(tblClassroom)
create table tblClassroom
(
    seq number primary key,
    name varchar2(15) not null,
    personnel number not null,
    
    constraint tblClaseeroom_personnel_ck check(personnel in (26, 30))
);


-- 8.개설과정(tblOpenCourse)
create table tblOpenCourse
(
    seq number primary key,
    courseSeq number not null references tblCourse(seq),
    classroomSeq number not null references tblClassroom(seq),
    startDate date not null,
    endDate date not null,
    status varchar2(6) default '예정',
    
    constraint tblOpenCourse_status_ck check(status in ('예정','진행','종료','삭제'))
);

-- 9.교재(tblTextbook)
create table tblTextbook
(
    seq number primary key,
    name varchar2(50) not null,
    publisher varchar2(30) not null

);


-- 10.개설과목(tblOpenSubject)
create table tblOpenSubject
(
    seq number primary key,
    openCourseSeq number not null references tblOpenCourse(seq),
    subjectSeq number not null references tblSubject(seq),
    startDate date not null,
    endDate date not null,
    textbookSeq number not null references tblTextbook(seq),
    whetherExam varchar2(10) default '미등록',
    whetherGrade varchar2(10) default '미등록',
    
    constraint tblOpenSubject_whetherExam_ck check(whetherExam in ('등록','미등록')),
    constraint tblOpenSubject_whetherGrade_ck check(whetherGrade in ('등록','미등록'))
);

-- 11. 교사과목기록(tblTeacherRecord)
create table tblTeacherRecord
(
    seq number primary key,
    status varchar2(6) default '예정',
    teacherSeq number not null references tblTeacher(seq),
    openSubjectSeq number not null references tblOpenSubject(seq),
    
    constraint tblTeacherRecord_status_ck check(status in ('예정','진행','종료'))
);


-- 12. 배점(tblPoint)
create table tblPoint
(
    seq number primary key,
    openSubjectSeq number not null references tblOpenSubject(seq),
    attendancePoint number not null check(attendancePoint >= 20),
    writtenPoint number not null,
    practicalPoint number not null
    
);


-- 13. 교육생(tblStudent)
create table tblStudent
(
    seq number primary key,
    name varchar2(15) not null,
    ssn varchar2(7) not null,
    tel varchar2(11) not null,
    registrationDate date default sysdate not null
    
);

select * from tblStudent where seq = 176;


-- 14. 수강(tblSignUp)
create table tblSignUp
(
    seq number primary key,
    openCourseSeq number not null references tblOpenCourse(seq),
    studentSeq number not null references tblStudent(seq),
    status varchar2(15) default '예정',
    endDate date,
    
    constraint tblSignUp_status_ch check(status in ('예정','진행','종료'))
);

-- 15. 성적(tblScore)
create table tblExamScore
(
    seq number primary key,
    signUpSeq number not null references tblSignUp(seq),
    openSubjectSeq number not null references tblOpenSubject(seq),
    writtenScore number,
    attendanceScore number,
    practicalScore number
);


-- 16. 출결(tblAttendance)
create table tblAttendance
(
    seq number primary key,
    signUpSeq number not null references tblSignUp(seq),
    attendanceDate date not null,
    enterTime date,
    exitTime date,
    status varchar2(15),
    
    constraint tblAttendance_status_ck check(status in ('출석','지각','조퇴','외출','병가','결석'))
);

-- 17. 스터디그룹(tblStudyGroup)
create table tblStudyGroup
(
    seq number primary key,
    goal varchar2(100) not null,
    registrationDate date not null,
    endDate date not null
);



-- 18. 스터디등록(tblStudyGroupRegistration)
create table tblStudyGroupRegistration
(
    seq number primary key,
    studyGroupSeq number not null references tblStudyGroup(seq),
    signUpSeq number not null references tblSignUp(seq),
    groupRank varchar2(100) default '팀장' not null,
    
    constraint tblSGR_groupRank_ck check(groupRank in ('팀원','팀장'))
);

-- 19. 스터디일지(tblStudyRecord)
create table tblStudyRecord
(
    seq number primary key,
    registrationSeq number not null references tblStudyGroupRegistration(seq),
    content varchar2(150) not null,
    recordDate date not null
);


-- 20. 스터디룸(tblStudyRoom)
create table tblStudyRoom
(
    seq number primary key,
    name varchar2(15) not null
);

-- 21. 스터디룸예약(tblStudyReservation)
create table tblStudyReservation
(
    seq number primary key,
    registrationSeq number not null references tblStudyGroupRegistration(seq),
    studyroomSeq number not null references tblStudyroom(seq),
    reservationDate date not null
);

-- 22. 휴일(tblHoliday)
create table tblHoliday (
    seq number primary key,
    regdate date not null,
    name varchar2(100) not null
);

