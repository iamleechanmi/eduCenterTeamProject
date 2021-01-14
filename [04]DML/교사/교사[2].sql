/*

2) 성적 정보 관리
   * 성적 입력
   * 성적 수정
   * 교육생별 성적 조회

3) 배점 및 시험 정보 관리
   * 배점 입력
   * 배점 수정
   * 배점 조회
   * 시험 문제 등록 여부 입력
   * 시험 문제 등록 여부 수정
   * 시험 문제 등록 여부 삭제

*/

-- 2) 성적 정보관리 * 성적 입력
-- 교사 이름 입력 전체 정보 조회(학생이름포함) -> 개설과목 번호 확인 -> 번호 입력 이름 입력 성적 입력. 

--[0] 교사번호 반환 함수 

create or replace function fnTeacherSeq(
    pTeacherName tblTeacher.name%type,  -- 교사이름
    pTeacherSsn number                          -- 교사번호
    
) return number 
is
    pseq number;
begin

    Select 
        seq into pSeq 
    from  tblTeacher
        where name = pTeacherName and ssn = pTeacherSsn;
        
    return pSeq;
end;


-- [1] 조회 create view

-- 전체 조회 
-- 전체 과정과 포함 과목명 .
create or replace view vwFindCourseSubject
as
SELECT
    s.seq as subjectSeq,
    os.osSeq as openSubjectSeq,
    oc.ocseq as openCourseSeq,
    tr.teacherseq as teacherSeq,
    tr.status as status,
    s.name as subjectName,
    to_char(os.startdate, 'yy/mm/dd') as startdate,
    to_char(os.endDate, 'yy/mm/dd') as endDate,
    lpad(oc.name, 45, ' ')  as courseName,
    p.attendancePoint,
    p.writtenPoint,
    p.practicalPoint,
    os.whetherExam,
    os.whetherGrade
FROM tblSubject s 
inner join (
                select 
                        openCourseSeq ,
                        subjectSeq,
                        textBookSeq,
                        seq as osSeq,
                        startDate,
                        endDate,
                        whetherExam,
                        whetherGrade
                  from tblopensubject 
                        where seq in (
                                             select 
                                                openSubjectSeq 
                                             from tblTeacherRecord 
                                              )
                ) os                                              
on s.seq =os.subjectSeq
inner join (
                 select 
                    oc.seq as ocseq,
                    c.name as name,
                    c.period as period,
                    oc.startdate as startDate,
                    oc.enddate as endDate
                from tblOpenCourse oc 
                    inner join tblCourse c on oc.courseSeq =c.seq 
                   where oc.status <> '삭제'  ) oc                                                    ---삭제 제외 
on os.openCourseSeq = oc.ocseq
inner join tblTeacherRecord tr on tr.openSubjectSeq = os.osSeq
left outer join tblPoint p on os.osSeq = p.seq
    order by openCourseSeq;
-- vwFindCourseSubject


-- 전체 학생 성적 조회 테이블 
create or replace view vwFindStudentGrade
as
Select 
    su.seq as signUpSeq,
    st.seq as studentSeq,
    es.seq as examScoreSeq,
    es.opensubjectseq as openSubjectSeq,
    case
    when su.openCourseSeq  in (select 
                                             seq 
                                            from tblOpenCourse) 
    then (select 
                lpad(name, 45, ' ')
            from tblcourse 
                where seq = ( Select 
                                    courseSeq 
                                    from tblOpenCourse 
                                        where seq = su.openCourseSeq) )
                
    end as courseName,
    case
        when  es.opensubjectseq in (select 
                                                    seq 
                                                from tblOpenSubject) 
        then (select 
                    name 
                from tblSubject 
                    where seq = (select 
                                            subjectSeq 
                                        from tblOpenSubject 
                                            where seq = es.openSubjectSeq)  
                )
    end as subjectName,
    su.opencourseseq as openCourseSeq,
    st.name as StudentName,
    st.ssn,
    es.attendanceScore,
    es.writtenscore,
    es.practicalScore,
    p.attendancePoint,
    p.writtenPoint,
    p.practicalPoint

from  tblSignUp su  
left outer join tblExamScore es on su.seq = es.signUpSeq
 inner join tblStudent st on su.studentSeq = st.seq
 left outer join tblpoint p on es.openSubjectSeq = p.seq
    where su.openCourseSeq in ( select 
                                                seq 
                                            from tblOpenCourse 
                                                where status <> '삭제')
    
  order by studentName;
-- vwStudentGrade

-- ExamSeq가 null이 아닌것 
create or replace view vwFindStudentGradeNotnull
as
Select 
     su.seq as signUpSeq,
    st.seq as studentSeq,
    es.seq as examScoreSeq,
    es.opensubjectseq as openSubjectSeq,
    
    case
    when su.openCourseSeq  in (select 
                                             seq 
                                            from tblOpenCourse) 
    then (select 
                lpad(name, 45, ' ') 
            from tblcourse 
                where seq = ( Select 
                                    courseSeq 
                                    from tblOpenCourse 
                                        where seq = su.openCourseSeq) )
                
    end as courseName,
    
    case
        when  es.opensubjectseq in (select 
                                                    seq 
                                                from tblopensubject) 
        then (select 
                    name 
                from tblsubject 
                    where seq = (select 
                                            subjectSeq 
                                        from tblOpenSubject 
                                            where seq = es.openSubjectSeq)  
                )
    end as subjectName,
    su.opencourseseq as openCourseSeq,
    st.name as StudentName,
    st.ssn,
    es.attendanceScore,
    es.writtenscore,
    es.practicalScore,
    p.attendancePoint,
    p.writtenPoint,
    p.practicalPoint
    
    from  tblSignUp su  
    left outer join tblExamScore es on su.seq = es.signUpSeq
     inner join tblStudent st on su.studentSeq = st.seq
     left outer join tblPoint p on es.openSubjectSeq = p.seq
        where es.writtenscore is not null or es.attendanceScore is not null or es.practicalScore is not null
            and su.openCourseSeq in ( select 
                                                    seq 
                                                from tblOpenCourse 
                                                    where status <> '삭제')
                
            order by studentName;
-- vwFindStudentGradeNotnull


-- 학생성적인 단 한개라도 누락된 것. 
create or replace view vwFindStudentGradeNull
as
select  
    os.seq as openSubjectSeq,
    os.openCourseSeq as openCourseSeq,
    sNull.수강번호 as signUpseq,
    
    case
    when os.openCourseSeq  in (select 
                                             seq 
                                            from tblOpenCourse) 
    then (select 
             lpad(name, 45, ' ')
            from tblcourse 
                where seq = ( Select 
                                    courseSeq 
                                    from tblOpenCourse 
                                        where seq = os.openCourseSeq) )
                
    end as courseName,
    
    case
    when os.subjectSeq in (select 
                                    seq 
                                    from tblSubject) 
    then (select 
                name 
            from tblSubject 
                where seq = os.subjectseq)
    end as subjectName,
    sNull.학생이름 as studentName,
    Snull.뒷자리 as ssn,
    Snull.출석 as attendanceScore,
    Snull.필기 as writtenScore,
    Snull.실기 as practicalScore,
    os.whetherexam as whetherexam,
    os.whethergrade as whethergrade,
    case
        when os.seq in (select seq from tblpoint) 
        then (select attendancePoint from tblpoint where openSubjectSeq = os.seq)
    end as attendancePoint,
    case
        when os.seq in (select seq from tblpoint) 
        then (select writtenPoint from tblpoint where openSubjectSeq = os.seq)
    end as writtenPoint,
    case
        when os.seq in (select seq from tblpoint) 
        then (select practicalPoint from tblpoint where openSubjectSeq = os.seq)
    end as practicalPoint,
    to_char(os.startdate,'yy/mm/dd') as startdate,
    to_char(os.enddate,'yy/mm/dd') as enddate
        
from tblOpenSubject os 
inner join(
                Select 
                    su.seq as "수강번호",
                    st.seq as "학생번호",
                    es.seq as "성적번호",
                    es.opensubjectseq as "개설과목번호",
                    case
                        when  es.opensubjectseq in (select 
                                                                    seq 
                                                                from tblopensubject) 
                        then (select 
                                    name 
                                from tblsubject 
                                    where seq = (select 
                                                            subjectSeq 
                                                        from tblopensubject 
                                                            where seq = es.opensubjectseq)  
                                )
                    end as "과목명",
                    su.opencourseseq as "개설과정번호",
                    st.name as "학생이름",
                    st.ssn as "뒷자리",
                    es.writtenscore as "필기",
                    es.attendanceScore as "출석",
                    es.practicalScore as "실기",
                    p.attendancePoint,
                    p.writtenPoint,
                    p.practicalPoint
                
                from  tblSignUp su  
                left outer join tblExamScore es on su.seq = es.signUpSeq
                 inner join tblStudent st on su.studentSeq = st.seq
                 left outer join tblpoint p on es.opensubjectseq = p.seq
                        where es.seq is null
                -- where es.writtenscore is null or es.attendanceScore is null or es.practicalScore is null
            ) sNull 
on sNull.개설과정번호 = os.opencourseseq 
    where os.openCourseSeq in ( select 
                                               seq 
                                            from tblOpenCourse 
                                               where status <> '삭제')
                                               
        order by sNull.학생이름, os.openCourseSeq, os.seq;
-- vwFindStudentGradeNull

 
--[2] 조회 호출 
 set SERVEROUTPUT ON;

--- 교사 별 전체 조회
create or replace PROCEDURE procFindTeacher(
    --procName in varchar2,                               --교사이름
    pTeacherSeq in number,                               --교사번호
    pStatus in varchar2,                                 -- 상태 
    vcursor out SYS_REFCURSOR
)
is
begin
    open vcursor
    for
          Select 
                * 
           from vwFindCourseSubject
                where teacherSeq = pTeacherSeq -- 교사 번호
                  and status = pStatus
                        order by openCourseSeq;
           
EXCEPTION
        when others then
        dbms_output.put_line('잘못입력하였습니다.');             
end procFindTeacher;        


 -- 교사의 진행진행 상태별 학생 성적 조회 
create or replace procedure procFindByTeacherGrade (
     pTeacherSeq in number,                              --교사번호
    pStatus in varchar2,                                     -- 진행상태 
    vcursor out SYS_REFCURSOR
 )
is
BEGIN
    open vcursor
    for
        select 
            *
        from vwFindStudentGrade
             where openCourseSeq in (
                                        SELECT
                                        --    s.seq as "과목번호",
                                        --    os.osSeq as "개설과목번호",
                                            oc.ocseq as "개설과정번호"
                                        --    s.name as "과목명",
                                        --    os.startdate as "과목시작",
                                        --    os.endDate as "과목종료",
                                        --    oc.name as "과정명"
                                            
                                        FROM tblSubject s 
                                            inner join (
                                                            select 
                                                                openCourseSeq ,
                                                                subjectSeq,
                                                                textBookSeq,
                                                                seq as osSeq,
                                                                startDate,
                                                                endDate 
                                                            from tblopensubject 
                                                                where seq in (
                                                                                        select 
                                                                                            opensubjectseq 
                                                                                        from tblteacherrecord 
                                                                                             where teacherseq =  pTeacherSeq  -- 교사 번호 .  
                                                                                           and status = pStatus
                                                                                    )) os                                              
                                            on s.seq =os.subjectseq
                                            inner join (
                                                            select 
                                                            oc.seq as ocseq,
                                                            c.name as name,
                                                            c.period as period,
                                                            oc.startdate as startDate,
                                                            oc.enddate as endDate
                                                            from tblOpenCourse oc inner join tblcourse c on oc.courseseq =c.seq) oc
                                            on os.openCourseSeq = oc.ocseq
                                            )
                        order by StudentName;
                                            
 exception 
    when others then 
    dbms_output.put_line('다시 확인하세요');
 end  procFindByTeacherGrade;
  
  
  
-- 성적 입력 프로지서 교사조회 
create or replace procedure procFindByTeacherGradeNull (
    pteacherseq in number,
     vcursor out SYS_REFCURSOR
)
is
begin
    open vcursor
    for
    select 
        * 
    from VWFINDSTUDENTGRADENULL 
    where openSubjectseq in (select 
                                            openSubjectseq 
                                        from tblteacherrecord 
                                            where teacherseq = pteacherSeq and status= '진행') ;
    
end procFindByTeacherGradeNull;
commit;

/*

2) 성적 정보 관리
   ㄱ. 성적 입력(최초성적입력) 트리거 배점 성적 확인. 
*/
--성적입력상태 
create or  replace trigger trgReplacewhetherGrade
    after 
    insert on tblExamScore
    for each row
declare
begin
    update tblOpenSubject set whetherGrade = '등록' where seq = :new.openSubjectSeq;
    dbms_output.put_line('whetherGrade가 등록으로 변경되었습니다.');
end trgReplacewhetherGrade;

-- procLookTeacher에다 examScore null 확인 _> null전용 조회화면에서 입력 조회 
--담당 수강생 출력 - > 번호 확인 -> 성적 입력. 
create or replace procedure procAddScore(
    pSignUpSeq number,                              -- 수강번호
    pOpenSubjectSeq number,                     -- 개설과목번호
    pAttend number,                                     -- 출석점수
    pWrite number,                                      --필기점수
    pPractical number                                   --실기점수
)
is
    vSeq number;
begin 
        select 
            count(*) into vSeq 
        from tblExamScore 
            where signupseq = psignUpSeq and opensubjectseq =pOpenSubjectSeq;
            
    if vSeq = 0 then 
        insert into tblExamScore(seq, signupseq,openSubjectSeq,writtenScore,attendanceScore,practicalScore) 
        values ( (select max(seq)+1 from tblExamScore), pSignUpseq, pOpenSubjectSeq, pWrite, pAttend,pPractical); -- 입력되는 순서 주의!
        dbms_output.put_line('성적입력이되었습니다.');
    elsif vSeq <> 0 then
        dbms_output.put_line('기존에 있는 성적이 입력되어 있습니다. 수정해주세요');
         raise_application_error(-20020,'성적등록오류');
    end if; 
    commit;
exception
    when others then
    dbms_output.put_line('잘못입력하였습니다.');
    raise_application_error(-20020,'성적등록오류');
end procAddScore;

/*

2) 성적 정보 관리
   ㄴ. 성적 수정 
        (1)  전체 성적수정
*/
--  procFindByTeacherGrade 교사번호, 상태 입력-> 학생의 번호 확인 -> 성적 수정. 
create or replace PROCEDURE procReplaceScore(
    pseq number,                                                    -- 성적번호 확인입력
    attend number,                                                  -- 출석성적   
    write number,                                                    -- 필기성적
    practice number                                                 -- 실기성적
)
is
begin

    update tblexamScore 

    set 
                writtenScore = write,
                attendanceScore = attend,
                practicalScore = practice
    where
                seq =pseq; 
     Commit;
exception 
    when others then
        dbms_output.put_line('다시확인하세요');
end procReplaceScore;


/*
2) 성적 정보 관리
   ㄷ.  교육생별 성적조회  
        
*/

create or replace procedure procFindByStudentScore(
    pStudentSeq in number,                                   -- 학생번호
    vcursor out sys_refcursor
)
is
begin
    open vcursor
    for
    select 
        * 
    from 
        vwFindStudentGrade
     where 
        studentSeq = pStudentSeq; 

end procFindByStudentScore;



/*

3) 배점 및 시험 정보 관리
   ㄱ. 배점 입력
        
*/
--  vwFindCourseSubject 전체조회 배점 입력 할 번호 확인 (openSubjeectSeq =seq 번호 ) 

create or replace procedure procAddPoint(
    pOpenSubjectSeq in number,                 -- 개설과목번호
    pAttend in number,                               -- 출석배점
    pWrite in number,                                  -- 필기배점
    pPractical in number                              -- 실기배점                     
)
is
begin

    if  (pAttend +pWrite +  pPractical) =100 then
        insert into tblPoint(seq, openSubjectSeq, attendancePoint, writtenPoint, practicalPoint)
        values((select max(seq)+1 from tblPoint),  pOpenSubjectSeq, pAttend,  pWrite,  pPractical );
    else
        raise_application_error ( -20003, '배점합이 100이 아닙니다.' ) ;
    end if;
    commit;
end  procAddPoint;

declare 
    vOpenSubjectSeq number,                 -- 개설과목번호
    vAttend number,                               -- 출석점수
    vWrite number,                                  -- 필기점수
    vPractical number                              -- 실기점수

begin
    procAddPoint(  vOpenSubjectSeq, vAttend,vWrite,vPractical);
end;


/*

3) 배점 및 시험 정보 관리
   ㄴ. 배점 수정 ( 100점이라는 조건으로 전체 수정만 허용) 
        
*/
-- vwFindCourseSubject 전체조회 배점 입력 할 번호 확인 (openSubjeectSeq =seq 번호 ) 
create or replace procedure procReplacePoint (
    pOpenSubjectSeq in number,                 -- 개설과목번호
    pAttend in number,                               -- 출석점수
    pWrite in number,                                  -- 필기점수
    pPractical in number                              -- 실기점수           
)
is
begin

    if ( pAttend <20) then
     raise_application_error ( -20013, '최소 출석점수 20점.' ) ;
    elsif  (pAttend + pWrite + pPractical) = 100 then
        update 
            tblPoint 
        set 
            attendancePoint = pAttend,
            writtenPoint = pWrite,
             practicalPoint = pPractical 
        where 
            openSubjectSeq = pOpenSubjectSeq;
    else  
       -- 100점이 아니면 할 것이 없다. 
        raise_application_error ( -20003, '배점합이 100이 아닙니다.' ) ;
    end if;
    commit;
end procReplacePoint;



/*

3) 배점 및 시험 정보 관리
   ㄷ. 배점 조회 
        
*/
-- vwFindCourseSubject 전체조회 배점 입력 할 번호 확인 (openSubjeectSeq =seq 번호 ) ( slet  문 수정.  )
create or replace procedure procFindPoint(
    pTeacherSeq varchar2,                      -- 교사번호 입력
    pcursor out sys_refcursor
)
is
begin
    open pcursor
    for
    select
        *
    from 
        vwFindCourseSubject
    where 
        teacherSeq =  pTeacherSeq;
    
end procFindPoint;


/*

3) 배점 및 시험 정보 관리
   ㄹ. 시험 문제 등록 여부 입력 
        
*/
-- vwFindCourseSubject 전체조회 등록 여부 확인. -> 여부 입력. 

create or replace procedure procAddWhetherExam(
    pOpenSubjectSeq number,                                 -- 개설과목번호
    pWhetherExam varchar2                                       -- 등록 여부 입력
)
is
   ptext tblopensubject.whetherexam%type; 
begin
    ptext :=  pWhetherExam;
    if pWhetherExam ='등록' or pWhetherExam ='미등록' then
        update 
            tblopenSubject 
        set
            WhetherExam = pWhetherExam
        where
            seq = pOpenSubjectSeq;
     elsif pWhetherExam <>'등록' and pWhetherExam <> '미등록'  then
        raise_application_error ( -20023, '등록여부를 다시확인해주세요' ) ;
    end if;
    
end procAddWhetherExam;

set serveroutput  on;


/*

3) 배점 및 시험 정보 관리
   ㅁ. 시험 문제 등록 여부 수정 (삭제와 같이 ) 
        
*/
-- vwFindCourseSubject 전체조회 등록 여부 확인. -> 여부 입력. 
-- 개설과목번호 선택 입력 여부 -> 자동 변환 

create or replace procedure procReplaceWhetherExam(
    pOpenSubjectSeq in number,                                    --  개설과목번호 입력.
    pNewStatus out varchar2                                         -- 수정 값 반환. 
)
is
    vStatus tblOpenSubject.whetherExam%type;              -- 기존 상태를 입력받는 곳
begin 
    select 
        WhetherExam into vStatus
    from 
         vwFindCourseSubject
        where openSubjectSeq = pOpenSubjectSeq ;
        
      if vStatus= '등록' then
            update 
                tblOpenSubject 
            set 
                WhetherExam = '미등록' 
            where 
                Seq = pOpenSubjectSeq;
                
             pNewStatus := '미등록' ;
             commit;
       elsif vStatus= '미등록' then
            update 
                tblOpenSubject 
            set 
                WhetherExam = '등록' 
            where 
                Seq = pOpenSubjectSeq; 
             pNewStatus := '등록'; 
             commit;
      end if;
      commit;
end procReplaceWhetherExam;



declare 
    vOpenSubjectSeq number;
    vNewStatus tblOpenSubject.WhetherExam%type;
begin
    procReplaceWhetherExam(124,vNewStatus);
    dbms_output.put_line( 'WhetherExam = ' || vNewStatus);
end;
