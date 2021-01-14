
--   * 담당 과정 전체조회
create or replace procedure procteachercoursefind 
(
    pstate varchar2,
    pseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor for
        select
        rpad(c.name, 40, ' ') as coursename,
        lpad(oc.seq,2,' ') as courseseq,
        to_char(oc.startDate,'yy/mm/dd') as startDate,
        to_char(oc.endDate,'yy/mm/dd') as endDate
        from tblteacher t
            inner join tblteacherrecord tr
                on t.seq = tr.teacherseq
            inner join tblopensubject os
                on os.seq = tr.opensubjectseq
            inner join tblopencourse oc
                on oc.seq = os.opencourseseq
            inner join tblcourse c
                on c.seq = oc.courseseq
            inner join tblcoursesubject cs
                on cs.courseseq = c.seq
            inner join tblsubject s
                on s.seq = cs.subjectseq
                     where oc.status = pstate 
                        and t.seq = pseq
                        and oc.status = pstate
                        and oc.status <> '삭제'
                        group by c.name, oc.seq, oc.startDate, oc.endDate, rpad(c.name, 40, ' '), 
lpad(oc.seq,2,' '), to_char(oc.startDate,'yy/mm/dd'), to_char(oc.endDate,'yy/mm/dd')
                            order by oc.seq;
end;                                                
      
      
-- 특정 과정의 과목 목록 조회
create or replace procedure procteacheropensubjectfind
(
    pseq varchar2,
    psubjectseq varchar2,
    pcursor out sys_refcursor
)
is
begin
    open pcursor for
        select 
            lpad(os.seq,3,' ') as seq,
            rpad(s.name,35, ' ') as subjectname,
            to_char(os.startDate,'yy/mm/dd') as startDate,
            to_char(os.endDate,'yy/mm/dd') as endDate,
            rpad(b.name,35,' ') as textbook,
            (select count(*) from tblsignup where openCourseSeq = oc.seq) as studentcount
            
        from tblteacher t
            inner join tblteacherrecord tr
                on t.seq = tr.teacherseq
            inner join tblopensubject os
                on os.seq = tr.opensubjectseq
            inner join tblopencourse oc
                on oc.seq = os.opencourseseq
            inner join tblcourse c
                on c.seq = oc.courseseq
            inner join tblcoursesubject cs
                on cs.courseseq = c.seq 
            inner join tblsubject s
                on s.seq = cs.subjectseq
            inner join tbltextbook b
                on b.seq = os.textbookSeq
                   where cs.subjectseq = os.subjectseq 
                        and t.seq = pseq   -- -> 교사번호
                        and oc.seq = psubjectseq  -- -> 과목번호
                        and oc.status <> '실패';
end;

-- 해당 과목을 듣는 교육생 명단을 출력
create or replace procedure procteastuallfind
(
 pseq number,
 pcursor out sys_refcursor
)
is
begin
    open pcursor for
        select
            lpad(st.seq,3,' ') as studentseq, 
            st.name as name,
            st.tel as tel,
            to_char(st.registrationdate,'yy/mm/dd') as registrationdate,
            su.status as status
        from tblstudent st
            inner join tblsignup su
                on st.seq = su.studentseq
            inner join tblopencourse oc
                on su.opencourseseq = oc.seq
            inner join tblopensubject os
                on oc.seq = os.opencourseseq
                    where os.seq = pseq -- 개설과목번호
                     and oc.status <> '실패';
end;


-- 년도별 출석 확인하기  
create or replace procedure procteastuattendanceyearfind
(
    pseq number, -- 과목번호
    pyear number, -- 해당 년도
    pcursor out sys_refcursor
)
is 
begin
    open pcursor for
        select
        
            s.seq as seq,
            s.name as name,
            a.status as status,
            count(*) as count
                from tblattendance a 
                    inner join tblsignup su 
                        on a.signupseq = su.seq
                    inner join tblstudent s
                        on su.studentseq = s.seq
                    inner join tblopencourse oc
                        on oc.seq = su.opencourseseq
                    inner join tblopensubject os
                        on oc.seq = os.opencourseseq
                        where to_char(a.attendanceDate,'yy') = pyear
                            and os.seq = pseq--교사의 과목번호(tr.opensubjectseq)
                            group by s.seq, s.name, a.status
                                order by seq;
end;

-- 월별 출석 확인하기  
create or replace procedure procteastuattendancemonthfind
(
    pseq number,
    pyear number,
    pmonth number,
    pcursor out sys_refcursor
)
is  
begin
    open pcursor for
        select
            s.seq as seq,
            s.name as name,
            a.status as status,
            count(*) as count
                from tblattendance a 
                    inner join tblsignup su 
                        on a.signupseq = su.seq
                    inner join tblstudent s
                        on su.studentseq = s.seq
                    inner join tblopencourse oc
                        on oc.seq = su.opencourseseq
                    inner join tblopensubject os
                        on oc.seq = os.opencourseseq
                        where to_char(a.attendanceDate,'yy') = pyear
                            and to_char(a.attendanceDate,'mm') = pmonth
                            and os.seq = pseq--교사의 과목번호(tr.opensubjectseq)
                            group by s.seq, s.name, a.status
                                order by seq;
end;




-- 일별 출석 확인하기
create or replace procedure procteastuattendancedayfind
(
    pseq number,
    pyear number,
    pmonth number,
    pday number,
    pcursor out sys_refcursor
)
is  
begin
    open pcursor for
        select
            s.seq as seq,
            s.name as name,
            a.status as status,
            count(*) as count
                from tblattendance a 
                    inner join tblsignup su 
                        on a.signupseq = su.seq
                    inner join tblstudent s
                        on su.studentseq = s.seq
                    inner join tblopencourse oc
                        on oc.seq = su.opencourseseq
                    inner join tblopensubject os
                        on oc.seq = os.opencourseseq
                        where to_char(a.attendanceDate,'yy') = pyear
                            and to_char(a.attendanceDate,'mm') = pmonth
                            and to_char(a.attendanceDate,'dd') = pday
                            and os.seq = pseq--교사의 과목번호(tr.opensubjectseq)
                            group by s.seq, s.name, a.status
                                order by seq;  
end;



-- 교사 번호와 강의 상태로 해당 교육생 목록 가져오기
create or replace procedure procteastulistfind(
    psubjectseq number,
    pseq number,
    pstatus varchar2,
    pcursor out sys_refcursor
)
is 
begin
    open pcursor for
    select 
        st.seq as seq,
        st.name as name, 
        st.registrationDate as regist
    from tblstudent st 
        inner join tblsignup su 
            on st.seq = su.studentseq
        inner join tblopencourse oc 
            on oc.seq = su.opencourseseq 
        inner join tblopensubject os 
            on os.opencourseseq = oc.seq
        inner join tblteacherrecord tr 
            on tr.opensubjectseq = os.seq
             where os.seq = psubjectseq  --과목번호
                and tr.teacherseq = pseq --교사번호
                and oc.status = pstatus; --진행상태
end;



--년도별 조회하기
create or replace procedure procteastsequattendyearfind
(
    pyear number,
    stseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor for
        select
            s.seq as seq,
            s.name as name,
            a.status as status,
            count(*) as count
                from tblattendance a 
                    inner join tblsignup su 
                        on a.signupseq = su.seq
                    inner join tblstudent s
                        on su.studentseq = s.seq
                        where to_char(a.attendanceDate,'yy') = pyear --년도
                            and s.seq = stseq --  번호
                                group by s.seq, s.name, a.status
                                    order by s.seq;
end;

-- 월별 출석 확인하기                
create or replace procedure procteastsequattendmonthfind
(
    pyear number,
    pmonth number,
    stseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor for
        select
            s.seq as seq,
            s.name as name,
            a.status as status,
            count(*) as count
                from tblattendance a 
                    inner join tblsignup su 
                        on a.signupseq = su.seq
                    inner join tblstudent s
                        on su.studentseq = s.seq
                        where to_char(a.attendanceDate,'yy') = pyear --년도
                            and to_char(a.attendanceDate,'mm') = pmonth
                            and s.seq = stseq --  번호
                                group by s.seq, s.name, a.status
                                    order by s.seq;   
end;


-- 일별 출석 확인하기
create or replace procedure procteastsequattenddayfind
(
    pyear number,
    pmonth number,
    pday number,
    stseq number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor for
        select
            s.seq as seq,
            s.name as name,
            a.status as status,
            count(*) as count
                from tblattendance a 
                    inner join tblsignup su 
                        on a.signupseq = su.seq
                    inner join tblstudent s
                        on su.studentseq = s.seq
                        where to_char(a.attendanceDate,'yy') = pyear --년도
                            and to_char(a.attendanceDate,'mm') = pmonth
                            and to_char(a.attendanceDate,'dd') = pday
                            and s.seq = stseq --  번호
                                group by s.seq, s.name, a.status
                                    order by s.seq;   
end;



