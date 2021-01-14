-- 관리자 쿼리2.sql
-- * select문에 한글로 입력된 것은 매개변수로 어떤 값을 넣었는지 알려주기 위해서 작성했습니다.

--------------------------------------------------------------------------------
-- 과정의 이름을 반환하는 함수 
--------------------------------------------------------------------------------
create or replace function fnCourseName(
    pcourseSeq number       -- 과정 번호를 받는다
) return varchar2
is
    vcourseName tblCourse.name%type;
begin
    select name into vcourseName from tblCourse where seq = pcourseSeq;
    return vcourseName;
end;

--------------------------------------------------------------------------------
-- 강의실의 이름을 반환하는 함수
--------------------------------------------------------------------------------
create or replace function fnClassroomName(
    pclassroomSeq number       -- 강의실 번호를 받는다
) return varchar2
is
    vclassroomName tblClassroom.name%type;
begin
    select name into vclassroomName from tblClassroom where seq = pclassroomSeq;
    return vclassroomName;
end;

--------------------------------------------------------------------------------
-- 과목의 이름을 반환하는 함수 
--------------------------------------------------------------------------------
create or replace function fnSubjectName(
    psubjectSeq number       -- 과목 번호를 받는다
) return varchar2
is
    vsubjectName tblSubject.name%type;
begin
    select name into vsubjectName from tblSubject where seq = psubjectSeq;
    return vsubjectName;
end;

--------------------------------------------------------------------------------
-- 교재의 이름을 반환하는 함수 
--------------------------------------------------------------------------------
create or replace function fnTextbookName(
    ptextbookSeq number       -- 교재 번호를 받는다
) return varchar2
is
    vtextbookName tblTextbook.name%type;
begin
    select name into vtextbookName from tblTextbook where seq = ptextbookSeq;
    return vtextbookName;
end;

--------------------------------------------------------------------------------
-- 교사의 이름을 반환하는 함수 
--------------------------------------------------------------------------------
create or replace function fnTeacherName(
    pteacherSeq number       -- 교사 번호를 받는다
) return varchar2
is
    vteacherName tblTeacher.name%type;
begin
    select name into vteacherName from tblTeacher where seq = pteacherSeq;
    return vteacherName;
end;

--------------------------------------------------------------------------------
-- 휴일을 제외하고 평일 종료일을 세는 함수
--------------------------------------------------------------------------------
create or replace function fnEndDay(
    pday date, 
    pmax number
) return date
is
    vnum number := 1;
    vday date;
    vcheck number;
begin
    vday := pday;
    loop
        vday := vday + interval '1' day;
        vnum := vnum + 1;
        select count(*) into vcheck from tblHoliday where to_char(vday, 'yyyy-mm-dd') = to_char(regdate, 'yyyy-mm-dd');
        if vcheck > 0 or to_char(vday, 'd') in ('1', '7') then
            vnum := vnum - 1;
        end if;
        exit when vnum >= pmax;
    end loop;
    return vday;
end;

--------------------------------------------------------------------------------
/*
[로그인] > [관리자 메인] > [개설 과정 관리]
- 전체 개설 과정 목록을 출력한다.
- 과정 번호를 출력한다.
- 개설 과정명을 출력한다.
- 과정 기간(시작/끝[년/월/일])을 출력한다.
- 강의실을 출력한다.
- 개설 과목 등록 여부를 출력한다.
- 교육생 등록 인원을 출력한다.
- 개설 과정 상태를 출력한다.
*/
--------------------------------------------------------------------------------
-- 전체 개설 과정 목록 조회
--------------------------------------------------------------------------------
create or replace view vwFindOpenCourse
as
select
    distinct oc.seq as seq,
    rpad(c.name, 60, ' ') as name,
    to_char(oc.startDate, 'yy/mm/dd') as startDate,
    to_char(oc.endDate, 'yy/mm/dd') as endDate,
    (select r.name from tblClassroom r inner join tblOpenCourse toc on r.seq = toc.classroomSeq where toc.seq = oc.seq and r.name <> '없음') as classroomName,
    case
        when oj.seq is not null then 'O'
        when oj.seq is null then 'X'
    end as whetherRegister,
    (select count(*) from tblSignUp where openCourseSeq = oc.seq) as personnel,
    oc.status as status
from tblOpenCourse oc
    left outer join tblOpenSubject oj
        on oc.seq = oj.openCourseSeq
            left outer join tblCourse c
                on c.seq = oc.courseSeq
                    where oc.status <> '삭제' and c.name <> '없음' -- 삭제된 개설 과정은 출력되지 않는다
                        order by oc.seq asc;
                                 
select * from vwFindOpenCourse;                                 

-- 페이지수에 따라 전체 개설 과정 목록을 출력한다
select * from (select rownum as rnum, voc.* from vwFindOpenCourse voc) where rnum > (페이지수-1)*10 and rnum <= 페이지수*10;

--------------------------------------------------------------------------------
-- 페이지 수 계산하는 함수
--------------------------------------------------------------------------------
create or replace function fnFindPage(
    page number --페이지 수를 받는다
) return number
is
    vpage number;
    vmaxPage number;
begin
    select ceil((select count(seq) from vwFindOpenCourse) / 10) into vmaxPage from dual;
    select vmaxPage - page into vpage from dual;
    return case
                when vpage <= 0 then -1
                when vpage = vmaxPage then 1
                else 0
            end;
end;

-- 매개변수에 페이지 수를 넣었을 때
-- 1) 1을 반환하면 첫 페이지
-- 2) -1을 반환하면 마지막 페이지
-- 3) 0은 그 외 출력 가능한 페이지임을 알려준다
select fnFindPage(페이지수) as nowPage from dual;

--------------------------------------------------------------------------------
/*
> [개설 과정 관리] > [개설 과정 조회]
- 개설 과정 번호를 입력 받아 선택한 개설 과정의 과목 정보를 출력한다.
- 과목명을 출력한다.
- 과목 기간(시작/끝[년/월/일])을 출력한다.
- 교사명을 출력한다.
- 교재명을 출력한다.
*/
--------------------------------------------------------------------------------
-- 특정 개설 과정 조회
-- (아래에 전체 개설 과목 조회 뷰 vwFindOpenSubject 생성 후 프로시저 생성해야 한다)
--------------------------------------------------------------------------------
create or replace procedure procFindOneOpenCourse(
    pseq number,        -- 선택한 개설 과정 번호
    pcursor out sys_refcursor 
)
is
begin
    open pcursor for select subjectSeq
                            ,rpad(subjectName, 40, ' ') as subjectName
                            ,to_char(subjectStartDate, 'yy/mm/dd') as subjectStartDate
                            ,to_char(subjectEndDate, 'yy/mm/dd') as subjectEndDate
                            ,teacherName
                            ,textbookName
                    from vwFindOpenSubject
                        where courseSeq = pseq;
end procFindOneOpenCourse;      

--------------------------------------------------------------------------------
/*
> [개설 과정 조회] > [개설 과목 조회]
- 개설 과목 번호를 입력 받아 선택한 개설 과목의 정보를 출력한다.
- 개설 과정 정보를 출력한다.
  - 과정명을 출력한다.
  - 과정 기간(시작/끝[년/월/일])을 출력한다.
  - 강의실명을 출력한다.
- 과목명을 출력한다.
- 과목 기간(시작/끝[년/월/일])을 출력한다.
- 시험 문제 등록 여부를 출력한다.
- 성적 등록 여부를 출력한다.
- 교사명을 출력한다.
- 교재명을 출력한다.
*/
--------------------------------------------------------------------------------
-- 전체 개설 과목 조회
--------------------------------------------------------------------------------
create or replace view vwFindOpenSubject
as
select
    voc.seq as courseSeq,
    oj.seq as subjectSeq,
    voc.name as courseName,
    voc.startDate as courseStartDate,
    voc.endDate as courseEndDate,
    voc.classroomName as classroomName,
    j.name as subjectName,
    oj.startDate as subjectStartDate,
    oj.endDate as subjectEndDate,
    oj.whetherExam as whetherExam,
    oj.whetherGrade as whetherGrade,
    te.name as teacherName,
    b.name as textbookName
from vwFindOpenCourse voc
    left outer join tblOpenSubject oj
        on voc.seq = oj.openCourseSeq
            left outer join tblSubject j
                on oj.subjectSeq = j.seq
                    left outer join tblTeacherRecord tr
                        on oj.seq = tr.openSubjectSeq
                            left outer join tblTeacher te
                                on tr.teacherSeq = te.seq
                                    left outer join tblTextbook b
                                        on oj.textbookSeq = b.seq
                                            where voc.status <> '삭제' and te.name <> '없음' and j.name <> '없음' and b.name <> '없음'
                                                order by subjectSeq;

--------------------------------------------------------------------------------
-- 특정 개설 과목 조회
--------------------------------------------------------------------------------
create or replace procedure procFindOneOpenSubject(
    pseq number,        -- 개설 과목 번호
    pcursor out sys_refcursor
)
is
begin
    open pcursor for select rtrim(courseName) as courseName
                            ,courseStartDate
                            ,courseEndDate
                            ,classroomName
                            ,rpad(subjectName, 30, ' ') as subjectName
                            ,to_char(subjectStartDate, 'yy/mm/dd') as subjectStartDate
                            ,to_char(subjectEndDate, 'yy/mm/dd') as subjectEndDate
                            ,whetherExam
                            ,whetherGrade
                            ,teacherName
                            ,textbookName
                    from vwFindOpenSubject
                        where subjectSeq = pseq and subjectName <> '없음' and classroomName <> '없음' and teacherName <> '없음' and textbookName <> '없음';
end procFindOneOpenSubject;

--------------------------------------------------------------------------------
/*
> [개설 과정 조회] > [개설 과목 등록]
- 개설 과정 번호를 입력한다.
- 과목 번호를 입력한다.
- 과목 시작일(년/월/일)을 입력한다.
- 교재 번호를 입력한다.
- 교사 번호를 입력한다.

* 교사 번호는 현재 과목과 강의 가능 과목이 일치하는 교사 명단 중에서 선택한다.
*/
--------------------------------------------------------------------------------
-- 개설 과정과 개설 과목 종료일 비교를 위한 뷰
--------------------------------------------------------------------------------
create or replace view vwFindEndDate
as
select
    oc.seq as courseSeq,
    oc.endDate as courseEndDate,
    case
        when oj.endDate is null then to_date('2000101', 'yyyymmdd')
        when oj.endDate is not null then oj.endDate
    end as subjectEndDate
from tblOpenCourse oc
    left outer join tblOpenSubject oj
        on oc.seq = oj.openCourseSeq;

--------------------------------------------------------------------------------
-- 선택한 개설 과정에 새로운 개설 과목 등록이 가능한지 확인 (0 이상일 경우 가능하다)
--------------------------------------------------------------------------------
select
    (select distinct courseEndDate from vwFindEndDate where courseSeq = 개설과정번호)
    -
    (select max(subjectEndDate) from vwFindEndDate where courseSeq = 개설과정번호)
    -
    (select min(period) from tblSubject where period <> 0 and name <> '없음') as difference
from dual;

--------------------------------------------------------------------------------
-- 선택한 개설 과목 시작일과 과정 종료일을 비교해 등록 가능 여부를 반환해주는 함수
--------------------------------------------------------------------------------
create or replace function fnAvailableOpenSubjectPeriod(
    pcourseSeq number, -- 개설 과정 번호
    psubjectStartDate varchar2 -- 개설 과목 시작일
) return number
is
    vcourseStartDate tblOpenCourse.startDate%type;      -- 개설 과정 시작일 저장할 변수
    vcourseEndDate vwFindEndDate.courseEndDate%type;    -- 개설 과정 종료일 저장할 변수
    vmaxEndDate vwFindEndDate.subjectEndDate%type;      -- 개설 과정 내 가장 최근 과목 종료일 저장할 변수
    vminPeriod number;                                  -- 과목 최소 기간을 저장할 변수
    vrealEndDate date;                                  -- 함수에 넣어 선택한 시작일을 기준으로 최소 종료일을 저장할 변수
begin
    select startDate into vcourseStartDate from tblOpenCourse where seq = pcourseSeq;
    select distinct courseEndDate into vcourseEndDate from vwFindEndDate where courseSeq = pcourseSeq;
    select max(subjectEndDate) into vmaxEndDate from vwFindEndDate where courseSeq = pcourseSeq;
    select min(period) into vminPeriod from tblSubject where period <> 0 and name <> '없음';

    select fnEndDay(psubjectStartDate, vminPeriod) into vrealEndDate from dual;
    
    -- 조건 만족하면 1 반환
    if psubjectStartDate >= vmaxEndDate and psubjectStartDate >= vcourseStartDate and vrealEndDate <= vcourseEndDate then
        return 1;
    else
        return 0; -- 만족하지 않으면 0 반환
    end if;
end;

select fnAvailableOpenSubjectPeriod(개설과정번호, 개설과목시작일) as result from dual;

--------------------------------------------------------------------------------
-- 등록 가능한 과목 목록 출력
--------------------------------------------------------------------------------
-- 1) 선택한 개설 과정 내의 과목은 제외하고 출력
select
    distinct j.seq as seq,
    rpad(j.name, 40, ' ') as name,
    j.period as period
from tblSubject j
    inner join tblOpenSubject oj
        on j.seq = oj.subjectSeq
            inner join tblOpenCourse oc
                on oc.seq = oj.openCourseSeq
                    where oj.subjectSeq not in (select distinct subjectSeq 
                                                from tblOpenSubject 
                                                    where openCourseSeq = 개설과정번호)
                    and j.name <> '없음' and oc.status <> '삭제'
                        order by j.seq;

--------------------------------------------------------------------------------
-- 등록 가능한 교사 목록 출력
--------------------------------------------------------------------------------
-- 1) 교사 가능 과목에 있는 교사이고
-- 2) 다른 과목 강의 중이면 기간이 겹치지 않는 교사 목록 출력
select
    distinct te.seq as seq,
    te.name as name,
    te.ssn as ssn,
    substr(te.tel, 1, 3) || '-' || substr(te.tel, 4, 4) || '-' || substr(te.tel, 8, 4) as tel
from tblPossibleSubject pj
    inner join tblTeacher te
        on te.seq = pj.teacherSeq
            where pj.subjectSeq = 과목번호 and te.name <> '없음'
            and te.seq not in (select
    tr.teacherSeq as teacherSeq
from tblTeacherRecord tr
    inner join tblOpenSubject oj
        on tr.openSubjectSeq = oj.seq
            where oj.endDate >= to_date('20201123', 'yyyymmdd') 
            and oj.startDate <= to_date('20201123', 'yyyymmdd')) 
                order by te.seq;

--------------------------------------------------------------------------------
-- 개설 과목 등록
--------------------------------------------------------------------------------
create or replace procedure procAddOpenSubject(
    popenCourseSeq in number,       -- 개설 과정 번호
    pstartDate in varchar2,         -- 시작일
    psubjectSeq in number,          -- 과목 번호
    pteacherSeq in number,          -- 교사 번호
    ptextbookSeq in number          -- 교재 번호
)
is
    vseq tblOpenSubject.seq%type;
    vstatus tblOpenCourse.status%type;
    vperiod number;
    vstartDate tblOpenSubject.startDate%type;
    vendDate tblOpenSubject.endDate%type;
    vcourseEndDate tblOpenCourse.endDate%type;
begin
    select period into vperiod from tblSubject where seq = psubjectSeq;
    select to_date(pstartDate, 'yyyymmdd') into vstartDate from dual;
    select fnEndDay(vstartDate, vperiod) into vendDate from dual;
    select status into vstatus from tblOpenCourse where seq = popenCourseSeq;
    select endDate into vcourseEndDate from tblOpenCourse where seq = popenCourseSeq;
 
    select max(seq) + 1 into vseq from tblOpenSubject;
    
    if vstatus <> '삭제' and fnSubjectName(psubjectSeq) <> '없음' and fnTeacherName(pteacherSeq) <> '없음' and fnTextbookName(ptextbookSeq) <> '없음' then
        if vendDate <= vcourseEndDate then
            insert into tblOpenSubject (seq, openCourseSeq, subjectSeq, startDate, endDate, textbookSeq) values (vseq, popenCourseSeq, psubjectSeq, vstartDate, vendDate, ptextbookSeq);
            -- 교사 과목 기록 테이블에 추가
            insert into tblTeacherRecord (seq, teacherSeq, openSubjectSeq) values ((select max(seq) + 1 from tblTeacherRecord), pteacherSeq, vseq);

        elsif vendDate > vcourseEndDate then
            insert into tblOpenSubject (seq, openCourseSeq, subjectSeq, startDate, endDate, textbookSeq) values (vseq, popenCourseSeq, psubjectSeq, vstartDate, vcourseEndDate, ptextbookSeq);
            -- 교사 과목 기록 테이블에 추가
            insert into tblTeacherRecord (seq, teacherSeq, openSubjectSeq) values ((select max(seq) + 1 from tblTeacherRecord), pteacherSeq, vseq);

        end if;
    end if;
    commit;
exception
    when others then
        rollback;
end procAddOpenSubject;

--------------------------------------------------------------------------------
/*
> [개설 과정 조회] > [개설 과목 수정]
- 개설 과목 번호를 입력 받아 개설 과목 정보를 수정한다.
- 교사명을 수정한다.
- 교재명을 수정한다.
*/
--------------------------------------------------------------------------------
-- 수정 가능한 교사 목록 출력
--------------------------------------------------------------------------------
-- 1) 해당 개설 과목의 과목이 강의 가능 과목에 있는 교사이고
-- 2) 강의 중인 다른 과목의 기간과 겹치지 않는 교사
select
    distinct te.seq as seq,
    te.name as name,
    te.ssn as ssn,
    te.tel as tel
from tblPossibleSubject pj
    inner join tblTeacher te
        on te.seq = pj.teacherSeq
            where pj.subjectSeq = (select subjectSeq from tblOpenSubject where seq = 개설과목번호)
            and te.name <> '없음'
            and te.seq not in (select
    tr.teacherSeq as teacherSeq
from tblTeacherRecord tr
    inner join tblOpenSubject oj
        on tr.openSubjectSeq = oj.seq
            where oj.endDate >= (select startDate from tblOpenSubject where seq = 개설과목번호)
            and oj.startDate <= (select startDate from tblOpenSubject where seq = 개설과목번호)) 
                order by te.seq;

--------------------------------------------------------------------------------
-- 개설 과목 교사 수정
--------------------------------------------------------------------------------
create or replace procedure procReplaceOpenSubjectTeacher(
    pseq in number,             -- 개설 과목 번호
    pteacherSeq in number       -- 교사 번호    
)
is
begin
    if fnTeacherName(pteacherSeq) <> '없음' then
        update tblTeacherRecord set seq = (select max(seq) + 1 from tblTeacherRecord), teacherSeq = pteacherSeq, openSubjectSeq = pseq where openSubjectSeq = pseq;
    end if;    
    commit;    
exception
    when others then
        rollback;
end procReplaceOpenSubjectTeacher;   

--------------------------------------------------------------------------------
-- 개설 과목 교재 수정
--------------------------------------------------------------------------------
-- 1) 교재 목록 출력
select 
    lpad(seq, 2, ' ') as seq, 
    rpad(name, 35, ' ') as name, 
    rpad(publisher, 14, ' ') as publisher 
from tblTextbook where name <> '없음';

-- 2) 교재 수정
update tblOpenSubject set textbookSeq = 교재번호 where seq = 개설과목번호;

--------------------------------------------------------------------------------
/*
> [개설 과목 삭제]
- 개설 과목 번호를 입력 받아 개설 과목을 삭제한다.
*/
--------------------------------------------------------------------------------
-- 개설 과목 삭제
--------------------------------------------------------------------------------
create or replace procedure procDeleteOpenSubject(
    pseq in number              -- 개설 과목 번호
)
is
begin
    delete from tblTeacherRecord where opensubjectseq = pseq;
    delete from tblExamScore where opensubjectseq = pseq;
    delete from tblPoint where opensubjectseq = pseq;    
    delete from tblOpenSubject where seq = pseq;
    commit;
exception
    when others then
        rollback;
end procDeleteOpenSubject;

--------------------------------------------------------------------------------
/*
> [개설 과정 등록]
- 과정 번호를 입력한다.
- 강의실 번호를 입력한다.
- 과정 시작일을 입력한다.
*/
--------------------------------------------------------------------------------
-- 개설 과정 등록 시 선택 가능한 과정 조회
--------------------------------------------------------------------------------
select 
    distinct c.seq as seq,
    c.name as name,
    c.goal as goal,
    c.period as period
from tblOpenCourse oc inner join tblCourse c on oc.courseSeq = c.seq 
    where c.seq not in (select distinct courseSeq from tblOpenCourse where status <> '삭제' and ('시작일' < endDate and startDate < to_date('시작일', 'yyyymmdd') + (select max(period) from tblCourse)))
        and oc.status <> '삭제' and c.name <> '없음' order by c.seq;
        
--------------------------------------------------------------------------------
-- 개설 과정 등록 시 사용 가능한 강의실 조회
--------------------------------------------------------------------------------
create or replace procedure procAvailableClassroom(
    pcourseSeq number,              -- 과정 번호
    pstartDate varchar2,            -- 과정 시작일
    presult out sys_refcursor
)
is
    vstartDate Date;
    vendDate Date;
    vperiod number;
begin
    select to_date(pstartDate, 'yyyymmdd') into vstartDate from dual;
    select period into vperiod from tblCourse where seq = pcourseSeq;
    select fnEndDay(vstartDate, vperiod) into vendDate from dual;
    
    open presult for select 
                            seq, 
                            name, 
                            personnel 
                    from tblClassroom 
                        where name <> '없음' and seq not in (select 
                                                                distinct r.seq 
                                                            from tblClassroom r inner join tblOpenCourse oc 
                                                                on r.seq = oc.classroomSeq 
                                                                    where oc.status <> '삭제' 
                                                                    and vstartDate < endDate 
                                                                    and startDate < vendDate and r.name <> '없음');
end procAvailableClassroom;

--------------------------------------------------------------------------------
-- 개설 과정 등록
--------------------------------------------------------------------------------
create or replace procedure procAddOpenCourse(
    pstartDate varchar2,                            -- 과정 시작일
    pcourseSeq number,                              -- 과정 번호
    pclassroomSeq number                            -- 강의실 번호
)
is
    vperiod number;                                 -- 과정 기간 저장할 변수
    vstartDate tblOpenCourse.startDate%type;        -- 과정 시작일 저장할 변수
    vendDate tblOpenCourse.endDate%type;            -- 과정 종료일 저장할 변수
begin
    -- 시작일, 종료일 변수에 저장
    select period into vperiod from tblCourse where seq = pcourseSeq;
    select to_date(pstartDate, 'yyyymmdd') into vstartDate from dual;
    select fnEndDay(vstartDate, vperiod) into vendDate from dual;
    
    if fnClassroomName(pclassroomSeq) <> '없음' and fnCourseName(pcourseSeq) <> '없음' then
        insert into tblOpenCourse (seq, courseSeq, classroomSeq, startDate, endDate) values ((select max(seq) from tblOpenCourse) + 1 , pcourseSeq, pclassroomSeq, vstartDate, vendDate);
    end if;
    commit;
exception
    when others then
        rollback;
end procAddOpenCourse;

--------------------------------------------------------------------------------
/*
> [개설 과정 수정]
- 개설 과정 번호를 입력 받아 선택한 개설 과정의 정보를 수정한다.
- 강의실 번호를 수정한다.
- 수료일을 수정한다.
*/
--------------------------------------------------------------------------------
-- 개설 과정 강의실 수정
--------------------------------------------------------------------------------
create or replace procedure procReplaceOpenCourseClassroom(
    pseq in number,                -- 개설 과정 번호
    pclassroomSeq in number        -- 수정하려는 강의실 번호
)
is
begin
    update tblOpenCourse set classRoomSeq = pclassroomSeq where seq = pseq;
    commit;
exception
    when others then
        rollback;
end procReplaceOpenCourseClassroom;

--------------------------------------------------------------------------------
-- 개설 과정 강의실 수정 시 사용 가능한 강의실 조회
--------------------------------------------------------------------------------
create or replace procedure procAvailableReplaceClassroom(
    pseq number,                    -- 개설 과정 번호
    presult out sys_refcursor
)
is
    vstartDate Date;
    vendDate Date;
begin
    select (select startDate from tblOpenCourse where seq = pseq) into vstartDate from dual;
    select (select endDate from tblOpenCourse where seq = pseq) into vendDate from dual;
    
    open presult for select 
                            seq, 
                            name, 
                            personnel 
                    from tblClassroom 
                        where name <> '없음' and seq not in (select 
                                                                distinct r.seq 
                                                            from tblClassroom r inner join tblOpenCourse oc 
                                                                on r.seq = oc.classroomSeq
                                                                    where oc.status <> '삭제' 
                                                                        and oc.seq <> pseq 
                                                                        and vstartDate < oc.endDate 
                                                                        and oc.startDate < vendDate 
                                                                        or r.personnel <(select count(seq) from tblSignUp where openCourseSeq = pseq));
end procAvailableReplaceClassroom;

--------------------------------------------------------------------------------
-- 수료일 수정 시 개설 과정 종료일보다 수료일이 같거나 큰지 확인하는 함수
--------------------------------------------------------------------------------
create or replace function fnAvailableEndDate(
    pseq in number,             -- 개설 과정 번호
    pendDate in varchar2        -- 수정하려는 수료일
) return number
is
    vendDate tblOpenCourse.endDate%type;          -- 개설 과정 종료일 저장할 변수
begin
    select endDate into vendDate from tblOpenCourse where seq = pseq;
    
    
    if vendDate <= pendDate then    -- 올바른 수료일일 경우
        return 1;
    elsif vendDate > pendDate then  -- 수료일이 선택한 개설 과정 종료일보다 전일 경우
        return 0;
    end if;
end;

select fnAvailableEndDate(개설과정번호, 수료일) as result from dual; 

--------------------------------------------------------------------------------
-- 수료일 수정
--------------------------------------------------------------------------------
create or replace procedure procReplaceCompletionDate(
    pseq in number,             -- 개설 과정 번호
    pendDate in varchar2        -- 수정하려는 수료일
)
is
begin
    update (select
                su.endDate as endDate,
                su.openCourseSeq as seq
            from tblStudent st
                inner join tblSignUp su
                    on st.seq = su.studentSeq
                        inner join tblOpenCourse oc
                            on su.openCourseSeq = oc.seq
                                where (su.status = '종료' and oc.status = '종료') 
                                and oc.status <> '삭제' 
                                and su.endDate - oc.endDate>= 0 or su.endDate is null) 
    set endDate = to_date(pendDate, 'yyyymmdd') where seq = pseq;
    commit;
exception
    when others then
        rollback;
end procReplaceCompletionDate;

--------------------------------------------------------------------------------
-- 개설 과정 삭제
--------------------------------------------------------------------------------
create or replace procedure procDeleteOpenCourse(
    pseq in number               -- 삭제하려는 개설 과정 번호
)
is
    cursor vcursor
    is
    select seq from tblOpenSubject where openCourseSeq = pseq;
    vrow tblOpenSubject.seq%type;
begin
    update tblOpenCourse set status = '삭제' where seq = pseq;
    
    open vcursor;
        loop
            fetch vcursor into vrow;
            exit when vcursor%notfound;
            procDeleteOpenSubject(vrow); 
        end loop;
    close vcursor;
   
    commit;
exception
    when others then
        rollback;
end procDeleteOpenCourse;

--------------------------------------------------------------------------------
/*
> [교육생 정보]
- 개설 과정 번호를 입력 받아 선택한 개설 과정의 교육생의 정보를 출력한다.
- 교육생 이름을 출력한다.
- 주민번호 뒷자리를 출력한다.
- 전화번호를 출력한다.
- 등록일을 출력한다.
- 수료 상태를 출력한다.
- 수료 및 중단일을 출력한다.
*/
--------------------------------------------------------------------------------
-- 교육생 정보 출력
--------------------------------------------------------------------------------
create or replace procedure procFindOpenCourseStudent(
    pseq in number,             -- 개설 과정 번호
    pcursor out sys_refcursor
)
is
begin
    open pcursor for select
                        st.seq as seq,
                        st.name as name,
                        st.ssn as ssn,
                        substr(st.tel, 1, 3) || '-' || substr(st.tel, 4, 4) || '-' || substr(st.tel, 8, 4) as tel,
                        st.registrationDate as registrationDate,
                        rpad(case
                            when su.status = '종료' and oc.status = '종료' and su.endDate - oc.endDate >= 0 then '수료'
                            when su.status = '진행' and su.endDate is null then '미수료'
                            when su.endDate - oc.endDate < 0 then '중도탈락'
                            else '미수료'
                        end, 8, ' ') as status,
                        rpad(case
                            when to_date(su.endDate, 'yy/mm/dd') is not null then to_char(to_date(su.endDate, 'yy/mm/dd'), 'yy/mm/dd') 
                            when to_date(su.endDate, 'yy/mm/dd') is null then decode(substr(to_date('01/01/01', 'yy/mm/dd'), 1, 8), '01/01/01', '    ') 
                        end, 8, ' ') as endDate
                    from tblOpenCourse oc
                        inner join tblSignUp su
                            on oc.seq = su.openCourseSeq
                                inner join tblStudent st
                                    on su.studentSeq = st.seq
                                        where su.openCourseSeq = pseq and oc.status <> '삭제'
                                            order by seq;
end procFindOpenCourseStudent;