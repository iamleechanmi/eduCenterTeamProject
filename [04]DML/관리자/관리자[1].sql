-- 관리자[1].sql
-- select 쿼리는 JDBC 안에 작성했습니다.
-- 프로시저와 뷰만 실행해주시면 됩니다.

-- 0. 메인화면 로그인

-- 로그인 시 해당 이름, 주민번호의 레코드의 개수
select count(*) as cnt from tblManager where name = ? and ssn = ?;

--------------------------------------------------------------

-- 관리자의 번호를 출력
select seq from tblManager where name = ? and ssn = ?;

--------------------------------------------------------------

--A001. 기초정보관리

--------------------------------------------------------------
--A001.a 과정
--------------------------------------------------------------
--1) 조회

select 
    seq, 
    rpad(name, 70, ' ') as name, 
    rpad(goal, 150, ' ') as goal, 
    rpad(period, 5, ' ') as period 
from tblCourse 
    where name != '없음' or goal != '없음' 
        order by seq;
---------------------------------------------------------------
--2) 입력

insert 
    into tblCourse(seq, name, goal, period) 
        values (seqCourse.nextVal, ?, ?, ?)
---------------------------------------------------------------
--3) 삭제

update tblCourse 
    set name = '없음', goal = '없음' 
        where seq = ?;
        
----------------------------------------------------------------
--4) 수정

-- 수정 전 데이터
select * from tblCourse where seq = ?;

-- 수정
update tblCourse set name = ?, goal = ?, period = ? where seq = ?;

--------------------------------------------------------------
--A001.b 과목
--------------------------------------------------------------
--1) 조회

select seq, rpad(name, 62, ' ') as name, rpad(period, 5, ' ') as period 
from tblSubject 
    where name != '없음';
---------------------------------------------------------------
--2) 입력

insert 
    into tblSubject(seq, name, period) 
        values (seqSubject.nextVal, ?, ?);
---------------------------------------------------------------
--3) 삭제

update tblSubject 
    set name = '없음' 
        where seq = ?;
        
----------------------------------------------------------------
--4) 수정

-- 수정 전 데이터
select * from tblSubject where seq = ?;

-- 수정
update tblSubject set name = ?, period = ? where seq = ?

--------------------------------------------------------------
--A001.c 강의실
--------------------------------------------------------------
--1) 조회

select * 
    from tblClassroom 
        where name != '없음'
            order by seq;
---------------------------------------------------------------
--2) 입력

insert 
    into tblClassroom(seq, name, personnel) 
        values (seqClassroom.nextVal, ?, ?);
---------------------------------------------------------------
--3) 삭제

update tblClassroom 
    set name = '없음' 
        where seq = ?;
        
----------------------------------------------------------------
--4) 수정

-- 수정 전 데이터
select * from tblClassroom where seq = ?;

-- 수정
update tblClassroom set name = ?, personnel = ? where seq = ?;


--------------------------------------------------------------
--A001.d 교재
--------------------------------------------------------------
--1) 조회

select seq, rpad(name, 40, ' ') as name, rpad(publisher, 25, ' ') as publisher 
from tblTextbook 
    where name != '없음' order by seq;
---------------------------------------------------------------
--2) 입력

insert 
    into tblTextbook(seq, name, publisher) 
        values (seqTextbook.nextVal, ?, ?);
---------------------------------------------------------------
--3) 삭제

update tblTextbook 
    set name = '없음', publisher = '없음' 
        where seq = ?;
        
----------------------------------------------------------------
--4) 수정

-- 수정 전 데이터
select * from tblTextbook where seq = ?;

-- 수정
Update tblTextbook 
    set name = ?, publisher = ? 
        where seq = ?;


--------------------------------------------------------------

--A002. 교사계정관리

--------------------------------------------------------------
--A001.a 교사조회
--------------------------------------------------------------
--교사 개인 정보 조회
select * from tblTeacher where name != '없음' order by seq;

--------------------------------------------------------------
--교사 강의 가능 과목 조회
--------------------------------------------------------------
--교사 강의 가능 과목 조회를 위한 뷰
create view vwTeacher
as
select
    ps.seq as seq,
    t.seq as teacherSeq,
    s.seq as subjectSeq,
    t.name as name,
    t.ssn as ssn,
    t.tel as tel,
    s.name as subject
from tblPossibleSubject ps
    inner join tblTeacher t
        on ps.teacherseq = t.seq
            inner join tblSubject s
                on s.seq = ps.subjectSeq;
--------------------------------------------------------------
--교사 강의 가능 과목 조회
select seq, subject from vwTeacher where teacherSeq = ? order by seq;

--------------------------------------------------------------
--A001.b 교사입력
--------------------------------------------------------------
--교사 추가(개인정보)
insert into tblTeacher(seq, name, ssn, tel) values (seqTeacher.nextVal, ?, ?, ?);

--------------------------------------------------------------
--A001.c 교사삭제
--------------------------------------------------------------
--교사 삭제(개인정보)
update tblTeacher set name = '없음', ssn = '없음', tel = '없음' where seq = ?;

--------------------------------------------------------------
--A001.d 교사수정
--------------------------------------------------------------
-- 수정전 데이터
select * from tblTeacher where seq = ?;

-- 수정
update tblTeacher set name = ?, ssn = ?, tel =? where seq = ?;

--------------------------------------------------------------
-- 강의 가능 과목 수정
--------------------------------------------------------------
-- 강의 가능 과목 삭제
create or replace procedure procDeletePossibleSubject(
    pseq tblPossibleSubject.seq%type
)
is
begin
    delete from tblPossibleSubject where seq = pseq;
    commit;
exception
    when others then
        rollback;
        dbms_output.put_line('삭제 실패');
end;

-- test
begin
    procPossibleSubject(241);
end;
----------------------------------------------------------------
-- 강의 가능 과목 등록
create or replace procedure procAddPossibleSubject(
    psubjectseq tblPossibleSubject.subjectseq%type,
    pteacherseq tblPossibleSubject.teacherseq%type
)
is
    pcount number;
    presult varchar2(50);
begin

    select count(*) into pcount from tblpossiblesubject where subjectseq = psubjectseq and teacherseq = pteacherseq;

    if pcount =0 then
        insert into tblPossibleSubject(seq, subjectseq, teacherseq) values (seqPossibleSubject.nextVal, psubjectseq, pteacherseq);
        commit;
        presult := '등록완료';
    else
        presult := '이미 등록된 과목입니다.';
    end if;
    dbms_output.put_line(presult);
    
exception
    when others then
        rollback;
        dbms_output.put_line('등록실패');

end;




--------------------------------------------------------------

--A008. 스터디그룹관리

--------------------------------------------------------------
--A008.a 스터디그룹조회
--------------------------------------------------------------
-- studygroup에 대한 정보, 스터디그룹의 인원에 대한 정보 조인한 뷰
create view vwStudyGroup
as
select 
    sgr.seq as seq,
    sgr.studygroupseq as studyGroupSeq,
    sgr.signupseq as signupSeq,
    sgr.grouprank as groupRank,
    sg.goal as goal,
    sg.registrationdate as registrationdate,
    sg.enddate as enddate
from tblStudyGroupRegistration sgr
    inner join tblStudyGroup sg
        on sgr.studygroupseq = sg.seq
            order by sgr.seq;
---------------------------------------------------------------
-- 전체 조회
select 
    seq, 
    rpad(goal, 20, ' ') as goal, 
    to_char(registrationDate, 'yyyy/mm/dd') as regDate, 
    to_char(endDate, 'yyyy/mm/dd') as endDate 
from tblstudygroup 
    where goal != '없음' 
        order by seq;

-- 진행중인 스터디그룹조회
select 
    seq, 
    rpad(goal, 20, ' ') as goal, 
    to_char(registrationDate, 'yyyy/mm/dd') as regDate, 
    to_char(endDate, 'yyyy/mm/dd') as endDate 
from tblstudygroup 
    where endDate > sysdate and goal != '없음' 
        order by seq;


--------------------------------------------------------------
--A008.b 스터디그룹삭제
--------------------------------------------------------------
-- 스터디그룹의 멤버 정보 출력
select * from tblStudyGroupRegistration where studygroupSeq = ? order by seq;

--------------------------------------------------------------
-- 스터디 그룹의 멤버의 자세한 정보 뷰
create view vwstudyGroupMember
as
select
    sg.seq as seq,
    sg.studygroupseq as studyGroupSeq,
    su.studentseq as studentSeq,
    s.name as studentName,
    s.tel as studentTel,
    sg.signupseq as signUpSeq,
    sg.grouprank as GroupRank,
    sg.goal as goal,
    sg.enddate as enddate,
    (select count(*) from tblStudyRecord where registrationSeq = sg.seq group by registrationseq) as recordCount
from vwStudyGroup sg
    inner join tblSignUp su
        on sg.signupseq = su.seq
            inner join tblStudent s
                on su.studentSeq = s.seq
                    order by sg.seq;

--------------------------------------------------------------
-- 더 자세한 멤버의 정보 출력
select * from vwstudyGroupMember where studyGroupSeq = ? order by seq;

--------------------------------------------------------------
-- 멤버 삭제
delete from tblstudygroupRegistration where seq = ?;

--------------------------------------------------------------
-- 스터디그룹 삭제
Update tblStudygroup set goal = '없음' where seq = ?;

--------------------------------------------------------------
--A008.c 스터디그룹등록
--------------------------------------------------------------
-- 진행중인 과정 정보 뷰
create view vwInTheWorkCourse
as
select 
    oc.seq as seq, 
    c.name as courseName,
    cr.name as classroomName
from tblopencourse oc
inner join tblcourse c
on oc.courseSeq = c.seq
inner join tblClassroom cr
on oc.classroomSeq = cr.seq
where oc.status = '진행'
order by oc.seq;
--------------------------------------------------------------
-- 진행중인 과정의 목록
select * from vwInTheWorkCourse;

--------------------------------------------------------------
-- 진행중인 과정의 학생 정보 뷰
create or replace view vwStudentInCourse
as
select 
    su.seq as seq,
    su.opencourseseq as opencourseseq,
    s.name as studentname,
    s.tel as studenttel,
    s.ssn as studentssn,
    oc.status as opencoursestatus
from tblsignup su
inner join tblstudent s
on su.studentSeq = s.seq
inner join tblopencourse oc
on oc.seq = su.opencourseseq;

--------------------------------------------------------------
-- 진행중인 과정의 학생들 정보 출력
select * from vwStudentInCourse where openCourseStatus = '진행' and openCourseSeq = ?;

--------------------------------------------------------------
-- 스터디그룹 등록
create or replace procedure procAddStudyGroup(
    pgoal tblStudyGroup.goal%type
)
is
begin

    insert into tblStudyGroup(seq, goal, registrationdate, enddate) values (seqStudyGroup.nextVal, pgoal, sysdate, sysdate + 60);
    commit;
exception
    when others then
        rollback;
        dbms_output.put_line('등록 실패');
end;

begin
    procAddStudyGroup('java 프로젝트');
end;

--------------------------------------------------------------
-- 스터디그룹 인원 등록
insert 
    into tblStudyGroupRegistration(seq, studyGroupSeq, signUpSeq, groupRank)
        values(seqStudyGroupRegistration.nextVal, ?, ?, ?);
        
--------------------------------------------------------------
-- 팀장의 유무를 판단하기 위한 쿼리
select count(*) as cnt from tblStudyGroupRegistration where groupRank = '팀장' and studyGroupSeq = ?

--------------------------------------------------------------
-- 스터디 그룹의 멤버 수를 출력
select count(*) as cnt from tblStudyGroupRegistration where studyGroupSeq = ?

--------------------------------------------------------------
--A008.d 스터디룸예약상태조회
--------------------------------------------------------------
-- 스터디룸 예약 조회를 위한 뷰
create view vwStudyRoomReservation
as
select 
    sr.seq as seq,
    sg.seq as studyGroupSeq,
    sroom.name as studyroomName,
    sr.reservationdate as reservationDate
from tblStudyReservation sr
    inner join tblStudyGroupRegistration sgr
        on sr.registrationseq = sgr.seq
            inner join tblStudyGroup sg
                on sg.seq = sgr.studyGroupSeq
                    inner join tblStudyRoom sroom
                        on sroom.seq = sr.studyroomSeq;
-----------------------------------------------------------------

select 
    seq, 
    studygroupSeq, 
    studyroomName, 
    to_char(reservationDate, 'yyyy/mm/dd') as rvDate 
from vwStudyRoomReservation 
    order by seq desc;


