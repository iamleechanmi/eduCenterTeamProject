--학생 번호 조회 함수
create or replace function fnFindManagerSeq(
    pname tblStudent.name%type,
    pssn tblStudent.ssn%type
) return number
is
    pseq number;
begin
    
    select seq into pseq from tblStudent where name = pname and ssn = pssn;
    
    return pseq;
    
end;

-- 1. 교육생이 현재 수강하고 있는(진행중인) 수강 과정 정보와 개인정보를 출력
-- 교육생의 수강 목록과 개인정보 조회 뷰
CREATE OR REPLACE VIEW vwMySignUp
AS
SELECT
        su.seq AS suSeq,                -- 수강 번호
        oc.seq AS ocSeq,                -- 개설 과정 번호
        rpad(C.NAME,50,' ') AS cName,                -- 과정명
        to_char(oc.startdate,'yy/mm/dd') AS ocStartDate,    -- 과정 시작일
        to_char(oc.endDate,'yy/mm/dd') AS ocEndDate,        -- 과정 종료일
        CASE
            WHEN su.endDate is null THEN '미수료'
            WHEN su.endDate < oc.endDate THEN oc.endDate || '(중도탈락)'
            WHEN su.endDate >= oc.endDate THEN oc.endDate ||'(수료)'
        END AS suEndDate,        -- 수료 및 중단 일자
        cr.NAME AS crName,              -- 강의실명
        su.status AS suStatus,          -- 과정 상태
        s.seq AS studentSeq,            --교육생번호
        S.NAME AS sName, -- 교육생 이름
        S.tel AS sTel, -- 교육생 전화번호
        to_char(S.registrationdate,'yy/mm/dd') AS sRegistrationDate -- 교육생 등록 일자
FROM tblOpenCourse oc
    INNER JOIN tblCourse C
    ON oc.courseSeq = C.seq
        INNER JOIN tblClassRoom cr
        ON oc.classRoomSeq = cr.seq
            INNER JOIN tblSignUp su
            ON su.openCourseSeq = oc.seq
                INNER JOIN tblStudent S
                ON su.studentseq = S.seq;

-- 교육생이 현재 수강하고 있는 정보 목록과 개인정보 조회 프로시저
CREATE OR REPLACE PROCEDURE procFindMySignUpNow(
    psseq NUMBER, -- 학생 번호
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN pcursor
        FOR select * 
        from vwMySignUp
            WHERE studentSeq = psseq AND suStatus = '진행';
END;

select * 
        from vwMySignUp
            WHERE studentSeq = psseq AND suStatus = '진행';

-- 2. 성적 조회
-- 전체 수강 목록 조회
-- 수강 번호, 과정명, 시작일, 종료일, 강의실명, 진행상태, 수료 및 중단 일자
CREATE OR REPLACE PROCEDURE procMySignUpFindAll(
    psseq NUMBER, -- 학생 번호
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN pcursor
        FOR select * 
        from vwMySignUp
            WHERE studentSeq = psseq
            order by ocseq;
END;


-- 수료/미수료 출력 함수
CREATE OR REPLACE FUNCTION fnSignUpEndStatus(
    psuEndDate DATE, -- 수료일
    pocEndDate DATE -- 과정 종료일
)RETURN VARCHAR2
IS
BEGIN
     RETURN CASE
        WHEN psuEndDate is null THEN '미수료'
        WHEN psuEndDate < pocEndDate THEN '중도탈락'
        WHEN psuEndDate >= pocEndDate THEN '수료'
    END;
END;

-- 2.1 전체 과목 성적 조회
-- 교육생의 수강 과정 전체 과목 정보와 성적 조회 뷰
create or replace view vwStudentScore
as
select
    su.studentSeq as studentSeq,                --교육생번호
    su.seq as signUpSeq,                             -- 수강번호
    rpad(s.name,40,' ') as subjectName,                         --과목이름
    os.seq as opensubjectSeq,               --개설 과목 번호
    p.attendancepoint as attendancepoint,   --출석배점
    p.writtenpoint as writtenpoint,                 --필기배점
    p.practicalpoint as practicalpoint,           --실기배점
    es.attendancescore as attendancescore, --출석점수
    es.writtenscore as writtenscore,               --필기점수
    es.practicalscore as practicalscore,         --실기점수
    os.whethergrade as whetherGrade,            --시험점수등록여부
    os.whetherexam as whetherExam               --시험문제등록여부
from tblSignUp su
    inner join tblExamScore es
    on su.seq = es.signupseq
        inner join tblOpenSubject os
        on os.seq = es.openSubjectSeq
            inner join tblPoint p
            on os.seq = p.opensubjectseq
                inner join tblSubject s
                on s.seq = os.subjectSeq
                        inner join tblTextbook tb
                        on tb.seq = os.textbookSeq;


-- 과목성적 전체조회
-- 선택 과정 전체 과목 정보와 성적 조회 프로시저
CREATE OR REPLACE PROCEDURE procFindMyScore(
    psSeq number, --학생번호
    psuSeq NUMBER, -- 수강번호
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN pcursor
        FOR SELECT * from vwStudentScore where studentSeq = psSeq and signUpSeq = psuSeq
        order by subjectname;
                
END;



-- 3. 출결 등록 및 조회
-- 3.1 출결 등록
-- 3.1.1 출근 상태 등록
CREATE OR REPLACE PROCEDURE procAddAttendanceOn
(
    psSeq NUMBER -- 교육생 번호
)
IS
vsignUpSeq number; -- 수강 번호
vstartDate date;
vendDate date;
BEGIN

    select 
        su.Seq, oc.startDate, oc.endDate
        into vsignUpSeq, vstartdate, venddate
    from tblStudent s 
        inner join tblSignUp su 
        on su.studentseq = s.seq 
            inner join tblopenCourse oc 
            on su.opencourseseq = oc.seq 
    where s.seq = psSeq;
    
    -- 현재 날짜가 과정 종료일보다 크면 출석 체크 안됨
    if TO_DATE(sysdate,'yyyy-mm-dd') > to_date(vendDate, 'yyyy-mm-dd') then
        dbms_output.put_line('종료된 과정은 출석체크를 할 수 없습니다.');
    
    -- 현재 날짜가 과정 시작일보다 작으면 출석 체크 안됨
    elsif TO_DATE(sysdate,'yyyy-mm-dd') < to_date(vstartDate, 'yyyy-mm-dd') then
        dbms_output.put_line('진행 예정인 과정은 출석체크를 할 수 없습니다.');
    
    --현재 시간이 06:00:00 ~ 09:05:59이면 status(출결 상태)가 찍히지 않고, enterTime은 현재 시각으로 찍힌다.
    elsif TO_DATE(sysdate,'hh24:mi:ss') < TO_DATE('06:00:00', 'hh24:mi:ss') then
        INSERT INTO tblAttendance (seq, signUpSeq, attendanceDate, enterTime) VALUES (SEQATTENDANCE.NEXTVAL, vsignUpSeq, sysdate, sysdate);
        dbms_output.put_line(to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') || ' 입실되었습니다.(정상)');
        COMMIT;  

            
    --현재 시간이 09:06:00 ~ 14:00:00이면 status(출결 상태)가 '지각'으로 찍히고, enterTime은 현재 시각으로 찍힌다.
    elsif TO_DATE(sysdate,'hh24:mi:ss') >= TO_DATE('09:06:00', 'hh24:mi:ss') then
        INSERT INTO tblAttendance (seq, signUpSeq, attendanceDate, enterTime, status) VALUES (SEQATTENDANCE.NEXTVAL, vsignUpSeq, sysdate, sysdate,'지각');
        dbms_output.put_line(to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') || ' 입실되었습니다. (지각)');
        COMMIT;
        
    --현재 시간이 14:00:00 ~ 18:00:00이면 status(출결 상태)가 '결석'으로 찍히고, enterTime은 현재 시각으로 찍힌다.
    elsif to_date(sysdate,'hh24:mi:ss') > to_date('14:00:00','hh24:mi:ss') then --and TO_DATE(sysdate,'hh24:mi:ss') <= TO_DATE('18:00:00', 'hh24:mi:ss') then
        INSERT INTO tblAttendance (seq, signUpSeq, attendanceDate, enterTime, status) VALUES (SEQATTENDANCE.NEXTVAL, vsignUpSeq, sysdate, sysdate,'결석');
        dbms_output.put_line(to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') || ' 입실되었습니다. (결석)');
        COMMIT;
      
    elsif to_date(sysdate,'hh24:mi:ss') >  to_date('21:00:00','hh24:mi:ss') then
        dbms_output.put_line('**21시 부터 6시까지는 출결 등록을 할 수 없습니다.');
    
    else
        dbms_output.put_line('실패');
        
    END IF;

    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;



-- 3.1.2 퇴근 상태 등록
CREATE OR REPLACE PROCEDURE procAddAttendanceOff
(
    psSeq NUMBER -- 교육생 번호
)
IS
    vsignUpSeq number; -- 수강 번호
    venterTime DATE; -- 입실 시간
BEGIN

    select s.seq, a.entertime into vsignUpSeq, venterTime from tblattendance a inner join tblsignup s on a.signupseq = s.seq inner join tblstudent st on s.studentseq = st.seq
        where st.seq = psSeq and to_char(a.attendancedate,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd');
        
        
    -- 입실 정보 없으면(null) 퇴근 등록 불가    
    if venterTime is null then
        dbms_output.put_line('입실 정보가 없습니다. 확인 후 재등록 해 주세요.');
        
    
    -- enterTime(입실 시간)이 14:00:00 이후면 퇴실 시간과 상관 없이 무조건 결석    
    elsif venterTime > to_date(to_char(venterTime, 'yyyy-mm-dd') || ' 14:00:00','yyyy-mm-dd hh24:mi:ss') then
        UPDATE tblattendance SET ExitTime = sysdate, status = '결석' WHERE signUpseq = vsignUpSeq AND to_char(attendancedate,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd');
        dbms_output.put_line(to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') || ' 퇴실되었습니다.(결1)');
        COMMIT;
        
    
    -- 퇴실 시간과 입실 시간의 차이가 9시간 넘으면 출석    
    elsIF (sysdate - venterTime)*24 >= 8.90027777777777777777777777777777777778 THEN    
        UPDATE tblattendance SET ExitTime = sysdate, status = '출석' WHERE signUpseq = vsignUpSeq AND to_char(attendancedate,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd');
        dbms_output.put_line(to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') || ' 퇴실되었습니다.(출)');
        COMMIT;
    
    -- 퇴실 시간과 입실 시간의 차이가 9시간 이내면 조퇴
    ELSIF (sysdate - venterTime)*24 < 8.90027777777777777777777777777777777778 AND (sysdate - venterTime) >= 4  THEN
        UPDATE tblattendance SET ExitTime = sysdate, status = '조퇴' WHERE signUpseq = vsignUpSeq AND to_char(attendancedate,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd');
        dbms_output.put_line(to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') || ' 퇴실되었습니다.(조)');
        COMMIT;
        
    -- 퇴실 시간과 입실 시간의 차이가 4시간 이내면 결석
    ELSIF (sysdate - venterTime)*24 < 4 THEN
        UPDATE tblattendance SET ExitTime = sysdate, status = '결석' WHERE signUpseq = vsignUpSeq AND to_char(attendancedate,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd');
        dbms_output.put_line(to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') || ' 퇴실되었습니다.(결2)');
        COMMIT;

    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;



-- 외출 처리 프로시저
CREATE OR REPLACE PROCEDURE procAddAttendanceOuting
(
    psSeq NUMBER -- 교육생 번호
)
IS
    vsignUpSeq number(3); -- 수강 번호
    vattendancedate DATE; -- 출결 일자
    venterTime DATE; -- 입실 시간
BEGIN
    
    
    select s.seq, a.attendancedate, a.entertime into vsignUpSeq, vattendanceDate, venterTime from tblattendance a inner join tblsignup s on a.signupseq = s.seq inner join tblstudent st on s.studentseq = st.seq
        where st.seq = psSeq and to_char(a.attendancedate,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd');
    
    -- 입실 정보 없으면(null) 등록 불가    
    if venterTime is null then
        dbms_output.put_line('입실 정보가 없습니다. 확인 후 재등록 해 주세요.');
        
    -- 퇴실 처리는 되지 않고 출결 상태가 외출로 변경
    else 
        UPDATE tblattendance SET status = '외출' WHERE signUpseq = vsignUpSeq AND to_char(attendancedate,'yyyy-mm-dd') = to_char(vattendancedate,'yyyy-mm-dd');
        COMMIT;

    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;


-- 3.2 출결 조회
-- 전체 수강 목록 조회
-- 3.2.1 특정 과정 전체 출결 조회

-- JDBC에서는 날짜에 과정 기간을 매개변수로 넣어줄건데 뷰 작성을 위해 임의로 기간을 정해놨습니다.
--2020/12/01~2020/12/31 날짜
create or replace view vwattendance
as
select to_date('2019-11-01','yyyy-mm-dd') + level -1 as regdate from dual
    connect by level <=(to_date('2020-12-31','yyyy-mm-dd') - to_date('2019-11-01','yyyy-mm-dd') + 1);

-- 출결 조회 뷰
create or replace view vwFindAttendance
as
select
    su.seq as suSeq, -- 수강 번호
    s.seq as sSeq, -- 학생 번호
    to_char(va.regdate,'yy/mm/dd') as attendanceDate, -- 출결일
    case
        when ta.enterTime is null then '        '
        else to_char(ta.enterTime,'hh24:mi:ss')
    end as enterTime,--입실 시간
    case
        when ta.exitTime is null then '        '
        else to_char(ta.exitTime,'hh24:mi:ss')
    end exitTime,--퇴실 시간
    case
        when to_char(va.regdate,'d') in (1,7) then '주말'
        when h.name is not null then h.name
        when ta.exittime is null then '  '
        when ta.attendanceDate is null then '결석'
        else ta.status                              --상태
    end as status,
    oc.startdate as ocStartDate,    --과정시작일
    oc.endDate as ocEndDate         --과정종료일
from tblattendance ta -- 출결테이블
    INNER join tblsignup su -- 수강테이블
    on ta.signupseq = su.seq
        inner join tblstudent s --교육생테이블
        on su.studentseq = s.seq
            inner join tblopencourse oc --개설과정테이블
            on su.opencourseSeq = oc.seq
                right outer join vwattendance va -- 임의기간테이블
                on to_char(ta.attendancedate, 'yy/mm/dd') = va.regdate
                    left outer join tblholiday h    --휴일테이블
                    on va.regdate = h.regdate
order by va.regdate;

-- 출결 상태 조회 뷰
create or replace view vwFindAttendance2
as
select
    suSeq, -- 수강 번호
    sSeq, -- 학생 번호
    to_date(attendanceDate,'yy/mm/dd') as attendanceDate, -- 출결일
    enterTime,--입실 시간
    exitTime,--퇴실 시간
    status,
    ocStartDate,    --과정시작일
    ocEndDate         --과정종료일
from vwFindAttendance;

                        
-- 특정 과정 전체 출결 조회 프로시저
create or replace procedure procFindAttendanceAll
(
    psSeq number,
    psuSeq number,
    pcursor out SYS_REFCURSOR
)
is
begin
    open pcursor
        for select
            *
            from vwFindAttendance
            where sSeq = psSeq or sSeq is null
            and suSeq = psuSeq or suSeq is null
            order by attendancedate;
end;


-- 3.2.2 특정 과정 연도별 출결 조회
CREATE OR REPLACE PROCEDURE procFindAttendanceYear
(
    psSeq number, -- 학생 번호
    psuSeq NUMBER, -- 조회할 수강 번호
    pperiod varchar2, -- 조회할 연도
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN pcursor
        FOR SELECT
                *
            FROM vwFindAttendance
                WHERE (sSeq = psSeq or sSeq is null)
                and (suSeq = psuSeq or suSeq is null)
                and (substr(attendanceDate,1,2) = pperiod)
                order by attendancedate;
END;


-- 3.2.3 특정 과정 월별 출결 조회
CREATE OR REPLACE PROCEDURE procFindAttendanceMonth
(
    psSeq number,-- 학생번호
    psuSeq NUMBER, -- 조회할 수강 번호
    pperiod VARCHAR2, -- 조회할 연도,월
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN pcursor
        FOR SELECT
                *
            FROM vwfindattendance
                WHERE (sSeq = psSeq or sSeq is null)
                and (suSeq = psuSeq or suSeq is null)
                and (substr(attendanceDate,1,5) = pperiod)
                order by attendancedate;
END;


-- 3.2.4 특정 과정 일별 출결 조회
CREATE OR REPLACE PROCEDURE procFindAttendanceDate
(
    psSeq number, -- 학생 번호
    psuSeq NUMBER, -- 조회할 수강 번호
    pperiod varchar2, -- 조회할 일
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN pcursor
        FOR SELECT
                *
            FROM vwFindAttendance
                WHERE (sSeq = psSeq or sSeq is null)
                and (suSeq = psuSeq or suSeq is null)
                and attendanceDate = pperiod;
END;


-- 4. 스터디 그룹
-- 4.1 스터디 그룹 정보 조회

-- 스터디 그룹 정보 조회 뷰
CREATE OR REPLACE VIEW vwFindMyStudyGroup
AS
SELECT
    sg.seq AS sgSeq, -- 스터디 번호
    sg.goal AS sgGoal, -- 스터디 목표
    to_char(sg.registrationdate,'yy/mm/dd') AS sgRegDate, -- 스터디 등록일
    to_char(sg.endDate,'yy/mm/dd') AS sgEndDate, -- 스터디 종료일
    sgr.grouprank AS sgrGroupRank, -- 스터디 직위
    S.NAME AS sName, -- 학생 이름
    S.seq as sSeq, -- 학생 번호
    sgr.seq as sgrSeq -- 스터디 그룹 등록 번호
    FROM tblSignUp su
        INNER JOIN tblStudent S
        ON su.studentSeq = S.seq
            INNER JOIN tblstudygroupregistration sgr
            ON sgr.signUpSeq = su.seq
                INNER JOIN tblStudyGroup sg
                ON sgr.studygroupseq = sg.seq;

-- 스터디 그룹 정보 조회 프로시저
CREATE OR REPLACE PROCEDURE procFindMyStudyGroup
(
    psSeq NUMBER, -- 학생 번호
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN

    OPEN pcursor
        FOR select * from vwFindMyStudyGroup WHERE sSeq = psSeq order by sgSeq;

END;



-- 4.2 스터디 룸 예약
-- 같은 날짜에 예약 가능한 강의실 목록                
select seq, name from tblstudyroom where not name in (select sm.name from tblstudyroom sm 
        inner join tblStudyReservation sn 
            on sn.studyroomSeq = sm.seq 
                where to_char(reservationDate,'yymmdd') = '201201') order by seq;



-- 스터디룸 예약 프로시저
create or replace procedure procAddStudyRoomReservation(
    pregistrationSeq number,--스터디그룹등록번호
    pstudyroomSeq number,--스터디룸번호
    preservationDate tblStudyReservation.reservationDate%type--예약일
)
is
begin

        insert into tblStudyReservation(seq, registrationSeq, studyroomSeq, reservationDate)
            values (seqStudyReservation.nextVal, pregistrationSeq,pstudyroomSeq, preservationDate);

end;



-- 4.3 스터디 룸 예약 조회
--스터디 룸 예약 조회 뷰
CREATE OR REPLACE VIEW vwFindStudyReservation
AS
SELECT
    to_char(sr.reservationdate,'yy/mm/dd') AS srRsvpDate, -- 스터디룸 예약 날짜
    sm.NAME AS smName,--스터디룸명
    sgr.studygroupseq as sgseq,         --스터디그룹 번호
    s.seq as sSeq --학생번호
FROM tblSignUp su
    INNER JOIN tblStudent S
    ON su.studentSeq = S.seq
        INNER JOIN tblstudygroupregistration sgr
        ON sgr.signUpSeq = su.seq
            INNER JOIN tblstudyreservation sr
            ON sr.registrationseq = sgr.seq
                INNER JOIN tblStudyroom sm
                ON sr.studyroomseq = sm.seq;


-- 스터디 룸 예약 조회 프로시저
CREATE OR REPLACE PROCEDURE procFindStudyReservation
(
    psgSeq NUMBER, -- 스터디 그룹 번호
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN
            
    OPEN pcursor
    FOR SELECT * from vwFindStudyReservation
            WHERE sgSeq = psgSeq
                order by srRsvpDate;
exception
    when others then
        --rollback
        dbms_output.put_line('예약 실패');
        
END;




-- 4.4 스터디 일지 작성
create or replace procedure procAddStudyRecord(
    pcontent varchar2,
    pregistrationSeq VARCHAR2 
)
is
begin

    insert into tblStudyRecord(seq, registrationSeq, content, recordDate)
        values (seqStudyRecord.nextVal, pregistrationSeq, pcontent, sysdate);

exception
    when others then
        --rollback
        dbms_output.put_line('입력 실패');

end;


-- 4.5 스터디 일지 조회

CREATE OR REPLACE VIEW vwFindStudyRecord
AS
SELECT
    sr.CONTENT AS srContent, -- 내용
    to_char(sr.recordDate,'yy/mm/dd') AS srRecordDate, -- 작성일
    sg.seq as sgseq         --스터디그룹번호
FROM tblSignUp su
    INNER JOIN tblStudent S
    ON su.studentSeq = S.seq
        INNER JOIN tblstudygroupregistration sgr
        ON sgr.signUpSeq = su.seq
            INNER JOIN tblStudyGroup sg
            ON sgr.studygroupseq = sg.seq
                INNER JOIN tblStudyRecord sr
                ON sr.registrationseq = sgr.seq;

--일지 조회 프로시저
CREATE OR REPLACE PROCEDURE procFindStudyRecord
(
    psgSeq NUMBER,-- 스터디 그룹 번호
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN pcursor
    FOR SELECT * from vwFindStudyRecord WHERE sgseq = psgseq order by srRecordDate;

END;

