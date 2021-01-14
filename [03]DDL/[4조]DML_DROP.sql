-- 관리자 1
drop view vwTeacher;
drop view vwStudyRoomReservation;
drop view vwInTheWorkCourse;
drop view vwStudentInCourse;
drop view vwstudyGroupMember;
drop view vwStudyGroup;

drop procedure procDeletePossibleSubject;
drop procedure procAddPossibleSubject;
drop procedure procAddStudyGroup;
drop procedure procteachercoursefind;
drop procedure procteacheropensubjectfind;
drop procedure procteastuallfind;
drop procedure procteastuattendanceyearfind;
drop procedure procteastuattendancemonthfind;
drop procedure procteastuattendancedayfind;
drop procedure procteastulistfind;
drop procedure procteastsequattendyearfind;
drop procedure procteastsequattendmonthfind;
drop procedure procteastsequattenddayfind;

-- 관리자 2
drop function fnCourseName;
drop function fnClassroomName;
drop function fnSubjectName;
drop function fnTextbookName;
drop function fnTeacherName;
drop function fnEndDay;
drop function fnFindPage;
drop function fnAvailableOpenSubjectPeriod;
drop function fnAvailableEndDate;

drop view vwFindOpenCourse;
drop view vwFindOpenSubject;
drop view vwFindEndDate;

drop procedure procFindOneOpenCourse;
drop procedure procFindOneOpenSubject;
drop procedure procAddOpenSubject;
drop procedure procReplaceOpenSubjectTeacher;
drop procedure procDeleteOpenSubject;
drop procedure procAvailableClassroom;
drop procedure procAddOpenCourse;
drop procedure procReplaceOpenCourseClassroom;
drop procedure procAvailableReplaceClassroom;
drop procedure procReplaceCompletionDate;
drop procedure procDeleteOpenCourse;
drop procedure procFindOpenCourseStudent;

-- 관리자 3
drop function fnSignUpCount;

drop view vwFindYetOpenCourse;
drop view vwFindAllStudent;
drop view vwOneStudent;
drop view vwFindExamOpenCourse;
drop view VwOpenCourseWithSubject;
drop view vwFindGradeBySubject;
drop view vwFindGradeByStudent;
drop view vwCourseByStudent;
drop view vwFindOpenCourseBrief;
drop view vwFindAttByCourse;
drop view vwTemp;
drop view vwFindAbsent;
drop view vwFindAbsent2;
drop view vwFindAbsent3;
drop view vwFindAbsent4 ;
drop view vwFindAttByStudent;

drop procedure procAddStudent;
drop procedure procAddSignUp;
drop procedure procFindStudent;
drop procedure procReplaceStudentName;
drop procedure procReplaceStudentTel;
drop procedure procDeleteStudent;
drop procedure procFindOpenCourse;
drop procedure procFindGradeBySubject;
drop procedure procFindGradeByStudent;
drop procedure procFindCourseByStudent;
drop procedure procFindGradeByStdOpcs;
drop procedure procFindOpenCourseBrief;

-- 교사(성적, 배점)
drop function fnTeacherSeq;

drop view vwFindCourseSubject;
drop view vwFindStudentGrade;
drop view vwFindStudentGradeNull;
drop view vwFindStudentGradeNotnull;

drop PROCEDURE procFindTeacher;
drop procedure procFindByTeacherGrade;
drop procedure procFindByTeacherGradeNull;
drop procedure procAddScore;
drop PROCEDURE procReplaceScore;
drop trigger trgReplacewhetherGrade;
drop procedure procReplaceWrittenScore;
drop procedure procReplaceAttendanceScore;
drop procedure procReplacePracticalScore;
drop procedure procFindByStudentScore;
drop procedure procAddPoint;
drop procedure procReplacePoint;
drop procedure procFindPoint;
drop procedure procAddWhetherExam;
drop procedure procReplaceWhetherExam;

drop trigger trgReplacewhetherGrade;

-- 교사(스케줄, 출석)
drop procedure procteachercoursefind;
drop procedure procteacheropensubjectfind;
drop procedure procteastuallfind;
drop procedure procteastuattendanceyearfind;
drop procedure procteastuattendancemonthfind;
drop procedure procteastuattendancedayfind;
drop procedure procteastulistfind;
drop procedure procteastsequattendyearfind;
drop procedure procteastsequattendmonthfind;
drop procedure procteastsequattenddayfind;

-- 교육생
drop function fnFindManagerSeq;
drop FUNCTION fnSignUpEndStatus;

drop VIEW vwMySignUp;
drop view vwStudentScore;
drop view vwattendance;
drop view vwFindAttendance;
drop VIEW vwFindMyStudyGroup;
drop VIEW vwFindStudyReservation;
drop VIEW vwFindStudyRecord;

drop PROCEDURE procFindMySignUpNow;
drop PROCEDURE procMySignUpFindAll;
drop PROCEDURE procFindMyScore;
drop PROCEDURE procAddAttendanceOn;
drop PROCEDURE procAddAttendanceOff;
drop PROCEDURE procAddAttendanceOuting;
drop procedure procFindAttendanceAll;
drop PROCEDURE procFindAttendanceYear;
drop PROCEDURE procFindAttendanceMonth;
drop PROCEDURE procFindMyStudyGroup;
drop PROCEDURE procFindStudyReservation;
drop procedure procAddStudyRecord;
drop PROCEDURE procFindStudyRecord;
drop procedure procAddStudyRoomReservation;
drop PROCEDURE procFindAttendanceDate;

drop TRIGGER trgAddAttendance;