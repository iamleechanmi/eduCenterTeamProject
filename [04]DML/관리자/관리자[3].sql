-- 교육생등록
create or replace procedure procAddStudent(
    pname varchar2,
    pssn varchar2,
    ptel varchar2
)
is
begin
    insert into tblStudent (seq, name, ssn, tel, registrationDate) values (seqStudent.nextVal, pname, pssn, ptel, default);
    commit;
end;


-- 진행 예정인 개설과정 출력
create or replace view vwFindYetOpenCourse
as
select 
    oc.seq,
    cs.name,
    to_char(oc.startdate, 'yy/mm/dd') as startDate
from tblOpenCourse oc
    inner join tblcourse cs
        on oc.courseseq = cs.seq
where oc.status = '예정'
order by seq;


-- 교육생개설과정등록
create or replace procedure procAddSignUp(
    pStudentSeq number,
    pCourseSeq number -- 개설과정번호입니다.

)
is
    vstatus tblopencourse.status%type;
    vcount number;
    statusErr EXCEPTION;
    countErr Exception;
begin
    select 
        status into vstatus 
    from tblOpenCourse 
    where seq = pCourseSeq;
    select
        count(status) into vcount
    from tblSignUp
    where studentseq = pstudentseq and status in ('진행', '예정');    
    if vstatus = '예정' and vcount = 0 then
        insert into tblSignUp (seq, openCourseSeq, studentSeq, status, endDate) values (seqSignUp.nextVal, pCourseSeq, pStudentSeq, vstatus, null);
        commit;
    elsif vstatus in ('진행', '종료', '삭제') then
        raise statusErr;
    elsif vcount >= 1 then
        raise countErr;
    else
        raise_application_error(-20303, '에러');
    end if;
exception
    when statusErr then
        -- 등록하려는 과정의 상태가 '예정'이 아닐 때 발생.
        raise_application_error(-20301, '이미 진행중이거나 종료된 과정에 교육생을 등록시킬 수 없습니다.');
    when countErr then
        raise_application_error(-20302, '교육생은 한 번에 여러 과정을 등록할 수 없습니다!');
    when others then
        dbms_output.put_line('error');
        rollback;
end;


-- (전체)교육생출력
-- 1. 수강 신청 횟수 찾기
create or replace function fnSignUpCount(
    pseq number
) return number
is
    vcount number;
    vdeletedCourseSeq number;
begin
    select 
        count(*) into vcount 
    from tblSignUp su
        inner join tblOpenCourse oc
            on su.opencourseseq = oc. seq
    where studentseq = pseq and oc.status <> '삭제';
    return vcount;
end;
-- 2. 출력하기
create or replace view vwFindAllStudent
as
select 
    seq as studentSeq, 
    name as studentName, 
    ssn as studentSsn, 
    tel as studentTel, 
    to_char(registrationDate, 'yy/mm/dd') as StudentRegdate, 
    fnSignUpCount(seq) as signUpCount 
from tblStudent
order by seq asc;


-- 특정교육생출력
create or replace procedure procFindStudent(
    pseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor 
        for select
                su.seq as signUpSeq,
                cs.name as courseName,
                to_char(oc.startdate, 'yy/mm/dd') as openCourseStartDate,
                to_char(oc.enddate, 'yy/mm/dd') as openCourseEndDate,
                cr.name as classroomName,
                case
                    when su.endDate - oc.endDate >= 0 then '수료'
                    when su.endDate - oc.endDate < 0 then '중도탈락'
                    when su.endDate is null then '미수료'
                end as signUpStatus,
                to_char(su.enddate, 'yy/mm/dd') as signUpEnddate
            from tblSignUp su
                inner join tblopencourse oc
                    on su.opencourseseq = oc.seq
                        inner join tblcourse cs
                            on oc.courseseq = cs.seq
                                inner join tblClassroom cr
                                    on oc.classroomseq = cr.seq
            where su.studentseq = pseq and oc.status <> '삭제'
            order by su.seq asc;
exception
    when others then
        dbms_output.put_line('error');
        rollback;
end;

create or replace view vwOneStudent
as
select
        su.seq as signUpSeq,
        cs.name as courseName,
        oc.startdate as openCourseStartDate,
        oc.enddate as openCourseEndDate,
        cr.name as classroomName,
        case
            when su.endDate - oc.endDate >= 0 then '수료'
            when su.endDate - oc.endDate < 0 then '중도탈락'
            when su.endDate is null then '미수료'
        end as signUpStatus,
        su.enddate as signUpEnddate
from tblSignUp su
    inner join tblopencourse oc
        on su.opencourseseq = oc.seq
            inner join tblcourse cs
                 on oc.courseseq = cs.seq
                    inner join tblClassroom cr
                        on oc.classroomseq = cr.seq;
select * from vwOneStudent;


-- 특정교육생정보수정
-- 이름 수정
create or replace procedure procReplaceStudentName(
    pseq number,
    preplacement varchar2
)
is
begin
    update 
        tblStudent 
    set name = preplacement 
    where seq = pseq;
    commit;
end;
-- 전화번호 수정
create or replace procedure procReplaceStudentTel(
    pseq number,
    preplacement varchar2
)
is
begin
    update 
        tblStudent 
    set tel = preplacement
    where seq = pseq;
    commit; 
end;


-- 교육생 정보 삭제
create or replace procedure procDeleteStudent(
    pseq number
)
is
begin
    update tblStudent set name = '없음', tel = '없음', ssn = '없음', registrationdate = to_date('0099-01-01', 'yyyy-mm-dd')
    where seq = pseq;
    commit;
exception
    when others then
        dbms_output.put_line('error');
        rollback;
end;


-- 개설과정 출력
create or replace view vwFindExamOpenCourse
as
select 
    oc.seq,
    cs.name,
    cr.name as classroomname,
    to_char(oc.startdate, 'yy/mm/dd') as startdate
from tblOpenCourse oc
    inner join tblcourse cs
        on oc.courseseq = cs.seq
            inner join tblclassroom cr
                on oc.classroomseq = cr.seq
where oc.status <> '삭제' and oc.status <> '예정'
order by seq;


-- 특정개설과정출력
create or replace procedure procFindOpenCourse(
    pseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor
    for select
        oc.seq as openCourseSeq, 
        cs.name as courseName, 
        cr.name as classroomName,
        os.seq as openSubjectSeq,
        sj.name as subjectName,
        os.startdate as openSubjectStartDate,
        os.enddate as openSubjectEndDate,
        tb.name as textbookName,
        tc.name as teacherName,
        os.whethergrade as whetherGrade,
        os.whetherexam as whetherExam
    from tblopencourse oc
        inner join tblcourse cs
            on oc.courseSeq = cs.seq
                inner join tblopensubject os
                    on os.opencourseseq = oc.seq
                        inner join tbltextbook tb
                            on os.textbookseq = tb.seq
                                inner join tblsubject sj
                                    on os.subjectseq = sj.seq
                                        inner join tblteacherrecord tr
                                            on tr.opensubjectseq = os.seq
                                                inner join tblTeacher tc
                                                    on tr.teacherseq = tc.seq
                                                        inner join tblClassroom cr
                                                            on oc.classroomseq = cr.seq
    where oc.seq = pseq and oc.status <> '삭제'
    order by os.startdate asc;
end;

create or replace view VwOpenCourseWithSubject
as
select
        oc.seq as openCourseSeq, 
        cs.name as courseName, 
        cr.name as classroomName,
        os.seq as openSubjectSeq,
        sj.name as subjectName,
        to_char(os.startdate, 'yy/mm/dd') as openSubjectStartDate,
        to_char(os.enddate, 'yy/mm/dd') as openSubjectEndDate,
        tb.name as textbookName,
        tc.name as teacherName,
        os.whethergrade as whetherGrade,
        os.whetherexam as whetherExam
    from tblopencourse oc
        inner join tblcourse cs
            on oc.courseSeq = cs.seq
                inner join tblopensubject os
                    on os.opencourseseq = oc.seq
                        inner join tbltextbook tb
                            on os.textbookseq = tb.seq
                                inner join tblsubject sj
                                    on os.subjectseq = sj.seq
                                        inner join tblteacherrecord tr
                                            on tr.opensubjectseq = os.seq
                                                inner join tblTeacher tc
                                                    on tr.teacherseq = tc.seq
                                                        inner join tblClassroom cr
                                                            on oc.classroomseq = cr.seq;


-- 특정과목 성적출력
create or replace procedure procFindGradeBySubject(
    pseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor 
    for select
        os.seq as openSubjectSeq,
        sj.name as subjectName,
        tc.name as teacherName,
        tb.name as textbookName,
        sd.name as studentName,
        sd.seq as studentSeq,
        sd.ssn as studentSsn,
        es.attendancescore as attendanceScore,
        es.writtenscore as writtenScore,
        es.practicalscore as practicalScore,
        cs.name as courseName
    from tblOpenSubject os
        inner join tblsubject sj
            on os.subjectseq = sj.seq
                inner join tblteacherrecord tr
                    on tr.opensubjectseq = os.seq
                        inner join tblTeacher tc
                             on tr.teacherseq = tc.seq
                                inner join tblTextbook tb
                                    on os.textbookseq = tb.seq
                                        inner join tblexamScore es
                                            on es.openSubjectSeq = os.seq
                                                inner join tblSignup su
                                                    on es.signUpseq = su.seq
                                                        inner join tblStudent sd
                                                            on su.studentseq = sd.seq
                                                                inner join tblopenCourse oc
                                                                    on su.openCourseSeq = oc.seq
                                                                        inner join tblcourse cs
                                                                            on oc.courseSeq = cs.seq
    where os.seq = pseq and oc.status <> '삭제'
    order by studentName asc; 
end;

create or replace view vwFindGradeBySubject
as
select
        os.seq as openSubjectSeq,
        sj.name as subjectName,
        tc.name as teacherName,
        tb.name as textbookName,
        sd.name as studentName,
        sd.ssn as studentSsn,
        sd.seq as studentseq,
        es.attendancescore as attendanceScore,
        es.writtenscore as writtenScore,
        es.practicalscore as practicalScore,
        cs.name as courseName
    from tblOpenSubject os
        inner join tblsubject sj
            on os.subjectseq = sj.seq
                inner join tblteacherrecord tr
                    on tr.opensubjectseq = os.seq
                        inner join tblTeacher tc
                             on tr.teacherseq = tc.seq
                                inner join tblTextbook tb
                                    on os.textbookseq = tb.seq
                                        inner join tblexamScore es
                                            on es.openSubjectSeq = os.seq
                                                inner join tblSignup su
                                                    on es.signUpseq = su.seq
                                                        inner join tblStudent sd
                                                            on su.studentseq = sd.seq
                                                                inner join tblOpencourse oc
                                                                    on su.opencourseSeq = oc.seq
                                                                        inner join tblcourse cs
                                                                            on oc.courseSeq = cs.seq;


-- 교육생별 성적조회
create or replace procedure procFindGradeByStudent(
    pseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor 
    for select
        sd.seq as studentSeq,
        sd.name as studentName,
        sd.ssn as studentSsn,
        su.seq as signUpSeq,
        cs.name as courseName,
        sj.name as subjectName,
        to_char(os.startdate, 'yy/mm/dd') as openSubjectStartDate,
        to_char(os.enddate, 'yy/mm/dd') as openSubjectEndDate,
        tc.name as teacherName,
        es.attendancescore as attendanceScore,
        es.writtenscore as writtenScore,
        es.practicalscore as practicalScore,
        to_char(oc.startdate, 'yy/mm/dd') as openCourseStartDate,
        to_char(oc.enddate, 'yy/mm/dd') as openCourseEndDate
    from tblOpenSubject os
        inner join tblsubject sj
            on os.subjectseq = sj.seq
                inner join tblteacherrecord tr
                    on tr.opensubjectseq = os.seq
                        inner join tblTeacher tc
                             on tr.teacherseq = tc.seq
                                inner join tblTextbook tb
                                    on os.textbookseq = tb.seq
                                        inner join tblexamScore es
                                            on es.openSubjectSeq = os.seq
                                                inner join tblSignup su
                                                    on es.signUpseq = su.seq
                                                        inner join tblStudent sd
                                                            on su.studentseq = sd.seq
                                                                inner join tblOpenCourse oc
                                                                    on su.opencourseseq = oc.seq
                                                                        inner join tblCourse cs
                                                                            on oc.courseseq = cs.seq
    where sd.seq = pseq and oc.status <> '삭제'
    order by os.startdate asc;
end;

create or replace view vwFindGradeByStudent
as
select
        sd.seq as studentSeq,
        sd.name as studentName,
        sd.ssn as studentSsn,
        su.seq as signUpSeq,
        cs.name as courseName,
        sj.name as subjectName,
        to_char(os.startdate, 'yy/mm/dd') as openSubjectStartDate,
        to_char(os.enddate, 'yy/mm/dd') as openSubjectEndDate,
        tc.name as teacherName,
        es.attendancescore as attendancescore,
        es.writtenscore as writtenscore,
        es.practicalscore as practicalscore,
        to_char(oc.startdate, 'yy/mm/dd') as openCourseStartDate,
        to_char(oc.enddate, 'yy/mm/dd') as openCourseEndDate
from tblOpenSubject os
        inner join tblsubject sj
            on os.subjectseq = sj.seq
                inner join tblteacherrecord tr
                    on tr.opensubjectseq = os.seq
                        inner join tblTeacher tc
                             on tr.teacherseq = tc.seq
                                inner join tblTextbook tb
                                    on os.textbookseq = tb.seq
                                        inner join tblexamScore es
                                            on es.openSubjectSeq = os.seq
                                                inner join tblSignup su
                                                    on es.signUpseq = su.seq
                                                        inner join tblStudent sd
                                                            on su.studentseq = sd.seq
                                                                inner join tblOpenCourse oc
                                                                    on su.opencourseseq = oc.seq
                                                                        inner join tblCourse cs
                                                                            on oc.courseseq = cs.seq
order by os.startdate asc;


-- 특정 교육생이 수강하거나 한 적이 있는 과정 조회
create or replace procedure procFindCourseByStudent(
    pseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor
    for select
        sd.seq as studentSeq,
        sd.name as studentName,
        oc.seq as openCourseSeq,
        cs.name as courseName,
        to_char(oc.startdate, 'yy/mm/dd') as openCourseStartDate,
        to_char(oc.enddate, 'yy/mm/dd') as openCourseEndDate
    from tblSignUp su
        inner join tblOpenCourse oc
            on su.opencourseseq = oc.seq
                inner join tblStudent sd
                    on su.studentSeq = sd.seq
                        inner join tblCourse cs
                            on oc.courseSeq = cs.seq
    where sd.seq = pseq and oc.status <> '삭제'
    order by oc.startDate asc;
end;


-- 교육생별 과정 조회
create or replace view vwCourseByStudent
as
select
    sd.seq as studentSeq,
    sd.name as studentName,
    oc.seq as openCourseSeq,
    cs.name as courseName,
    to_char(oc.startdate, 'yy/mm/dd') as openCourseStartDate,
    to_char(oc.enddate, 'yy/mm/dd') as endCourseEndDate
from tblSignUp su
    inner join tblOpenCourse oc
        on su.opencourseseq = oc.seq
             inner join tblStudent sd
                on su.studentSeq = sd.seq
                    inner join tblCourse cs
                        on oc.courseSeq = cs.seq
order by oc.startDate asc;


-- 교육생이 수강한 특정 개설 과정의 성적 조회
create or replace procedure procFindGradeByStdOpcs(
    pseq number,
    popenCourseSeq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor 
    for select
        sd.seq as studentSeq,
        sd.name as studentName,
        sd.ssn as studentSsn,
        su.seq as signUpSeq,
        cs.name as courseName,
        sj.name as subjectName,
        to_char(os.startdate, 'yy/mm/dd') as openSubjectStartDate,
        to_char(os.enddate, 'yy/mm/dd') as openSubjectEndDate,
        tc.name as teacherName,
        es.attendancescore as attendanceScore,
        es.writtenscore as writtenScore,
        es.practicalscore as practicalScore,
        to_char(oc.startdate, 'yy/mm/dd') as openCourseStartDate,
        to_char(oc.enddate, 'yy/mm/dd') as openCourseEndDate
    from tblOpenSubject os
        inner join tblsubject sj
            on os.subjectseq = sj.seq
                inner join tblteacherrecord tr
                    on tr.opensubjectseq = os.seq
                        inner join tblTeacher tc
                             on tr.teacherseq = tc.seq
                                inner join tblTextbook tb
                                    on os.textbookseq = tb.seq
                                        inner join tblexamScore es
                                            on es.openSubjectSeq = os.seq
                                                inner join tblSignup su
                                                    on es.signUpseq = su.seq
                                                        inner join tblStudent sd
                                                            on su.studentseq = sd.seq
                                                                inner join tblOpenCourse oc
                                                                    on su.opencourseseq = oc.seq
                                                                        inner join tblCourse cs
                                                                            on oc.courseseq = cs.seq
    where sd.seq = pseq and oc.status <> '삭제' and oc.seq = popenCourseSeq
    order by os.startdate asc;
end;


-- 1. 과정 정보 출력
create or replace procedure procFindOpenCourseBrief(
    pseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor
    for select
        cs.name as courseName,
        cr.name as classroomName
    from tblOpenCourse oc
        inner join tblCourse cs
            on oc.courseseq = cs.seq
                inner join tblClassroom cr
                    on oc.classroomseq = cr.seq
    where oc.seq = pseq and oc.status <> '삭제';
end;

create or replace view vwFindOpenCourseBrief
as
select
    cs.name as courseName,
    cr.name as classroomName,
    oc.seq as courseSeq,
    to_char(oc.startDate, 'yy/mm/dd') as openCourseStartDate
from tblOpenCourse oc
    inner join tblCourse cs
        on oc.courseseq = cs.seq
            inner join tblClassroom cr
                on oc.classroomseq = cr.seq
where oc.status <> '삭제'
order by oc.seq asc;


-- 과정별 출결조회 뷰
create or replace view vwFindAttByCourse
as
select 
        sd.seq as studentSeq,
        sd.name as studentName, 
        --at.status as attendanceStatus,
        count(case
            when at.status = '출석' then 1
        end) as attendanceCount,
        count(case
            when at.status = '지각' then 1
        end) as tardyCount,
        count(case
            when at.status = '조퇴' then 1
        end) as earlyLeaveCount,
        count(case
            when at.status = '외출' then 1
        end) as leavingCount,
        count(case
            when at.status = '병가' then 1
        end) as sickLeaveCount,
        count(case
            when at.status = '결석' then 1
        end) as absentCount
    from tblAttendance at 
        inner join tblSignUp su 
            on at.signupseq = su.seq
                inner join tblOpenCourse oc
                    on su.opencourseseq = oc.seq
                        inner join tblstudent sd
                            on su.studentseq = sd.seq
group by sd.seq, sd.name
order by sd.seq desc;


-- 출결 비교를 위한 특정기간(2020-07-06 ~ 2020-12-23)의 날짜 뷰(~sysdate로 해도 괜찮을듯!)
create or replace view vwTemp
as
select
    level as seq,
    to_date('2020-07-06', 'yy/mm/dd') + level - 1 as tempDate
from dual
connect by level <= to_date('2020-12-23', 'yy/mm/dd') - to_date('2020-07-06', 'yy/mm/dd') + 1;


-- 무단결석한 날짜를 찾는 뷰
create or replace view vwFindAbsent
as
select
    oc.seq as openCourseSeq,
    oc.startDate as openCourseStartDate,
    oc.endDate as openCourseEndDate,
    sd.seq as studentSeq,
    sd.name as studentName,
    at.status as attendanceStatus,
    at.attendancedate as attendanceDate
    from tblAttendance at
        inner join tblSignUp su 
            on at.signupseq = su.seq
                inner join tblOpenCourse oc
                    on su.opencourseseq = oc.seq
                        inner join tblstudent sd
                            on su.studentseq = sd.seq  
    where oc.status <> '삭제';
select * from tblOpenCourse oc inner join tblCourse cs on oc.courseSeq = cs.seq;
select * from tblsignUp;

-- JDBC에서 만들어야하는 뷰, studentSeq를 조회하고자하는 교육생의 번호로 입력받는다.
create or replace view vwFindAbsent2
as
select
    *
from vwFindAbsent where studentseq = 103;

create or replace view vwFindAbsent3
as
select
    case
        when to_char(vt.tempDate, 'd') in (1,7) then '주말'
        when hd.name is not null then '공휴일'
        when fa.attendanceDate is null then '결석'
        else fa.attendanceStatus
    end as attendanceStatus,
    vt.tempDate as tempDate
from vwTemp vt
    left outer join vwFindAbsent2 fa
        on vt.tempDate = fa.attendanceDate
            left outer join tblholiday hd
                on vt.tempDate = hd.regdate
order by vt.tempDate asc;

create or replace view vwFindAbsent4 
as 
select 
count(case when attendanceStatus = '출석' then 1 end) as attendanceCount, count(case when attendanceStatus = '지각' then 1 end) as tardyCount, count(case when attendanceStatus = '조퇴' then 1 end) as earlyLeaveCount, count(case when attendanceStatus = '외출' then 1 end) as leavingCount, count(case when attendanceStatus = '병가' then 1 end) as sickLeaveCount, count(case when attendanceStatus = '결석' then 1 end) as absentCount 
from vwFindAbsent3;


-- 교육생별 출결 조회 뷰
create or replace view vwFindAttByStudent
as
select
    at.attendancedate,
    at.status
from tblattendance at
    inner join tblSignUp su
        on at.signUpseq = su.seq
order by at.attendancedate asc;


commit;