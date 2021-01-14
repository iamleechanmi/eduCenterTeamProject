package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.ClassroomDTO;
import com.test.dto.CourseDTO;
import com.test.dto.OpenCourseDTO;
import com.test.dto.OpenCourseStudentDTO;
import com.test.dto.OpenSubjectDTO;

import oracle.jdbc.OracleTypes;

/**
 * OpenCourseDAO. 개설 과정 관리에 필요한 데이터를 조회 및 반환하는 클래스입니다.
 * @author 이찬미
 *
 */
public class OpenCourseDAO {

   private Connection conn;
   private Statement stat;
   private PreparedStatement pstat;
   private CallableStatement cstat;
   private ResultSet rs;

   
   /**
    * DB 연결을 위한 기본 생성자입니다.
    */
   public OpenCourseDAO() {
      
      try {

         this.conn = DBUtil.open();
         this.stat = conn.createStatement();
         
      } catch (Exception e) {
         System.out.println("ManagerDAO.생성자");
         e.printStackTrace();
      }
   }
   
   
   /**
    * 전체 개설 과정 목록으르 조회하는 메소드입니다.
    * @param page 페이지 수
    * @return 개설 과정 목록을 반환합니다.
    */
   public ArrayList<OpenCourseDTO> findOpenCourse(int page) {
      
      try {
         
         String sql = "select * from (select rownum as rnum, voc.* from vwFindOpenCourse voc) where rnum > (?-1)*10 and rnum <= ?*10";
         
         pstat = conn.prepareStatement(sql);

         pstat.setInt(1, page);
         pstat.setInt(2, page);
         
         rs = pstat.executeQuery();
         
         ArrayList<OpenCourseDTO> list = new ArrayList<OpenCourseDTO>();
         
         while (rs.next()) {
            
            OpenCourseDTO dto = new OpenCourseDTO();

            dto.setSeq(rs.getInt("seq"));
            dto.setName(rs.getString("name"));
            dto.setStartDate(rs.getString("startDate"));
            dto.setEndDate(rs.getString("endDate"));
            dto.setClassroomName(rs.getString("classroomName"));
            dto.setWhetherRegister(rs.getString("whetherRegister"));
            dto.setPersonnel(rs.getInt("personnel"));
            dto.setStatus(rs.getString("status"));
            
            list.add(dto);
         }
         
         return list;

      } catch (Exception e) {
         System.out.println("ManagerDAO.openCourseList()");
         e.printStackTrace();
      }
      
      return null;
   }

   
   /**
    * 특정한 개설 과정 조회 시 과정 정보를 저장하여 반환하는 메소드입니다. 
    * @param openCoursSeq 개설 과정 번호
    * @return 개설 과정 정보를 반환합니다.
    */
   public ArrayList<OpenSubjectDTO> findOneOpenCourse(String openCoursSeq) {
      
      try {

         String sql = "{ call procFindOneOpenCourse(?, ?) }";
         
         cstat = conn.prepareCall(sql);
         
         cstat.setString(1, openCoursSeq);
         cstat.registerOutParameter(2, OracleTypes.CURSOR);
         
         cstat.executeQuery();
         
         rs = (ResultSet)cstat.getObject(2);
         
         ArrayList<OpenSubjectDTO> list = new ArrayList<OpenSubjectDTO>();
         
         while (rs.next()) {
            
            OpenSubjectDTO dto = new OpenSubjectDTO();
            
            dto.setSubjectSeq(rs.getInt("subjectSeq"));
            dto.setSubjectName(rs.getString("subjectName"));
            dto.setSubjectStartDate(rs.getString("subjectStartDate"));
            dto.setSubjectEndDate(rs.getString("subjectEndDate"));
            dto.setTeacherName(rs.getString("teacherName"));
            dto.setTextbookName(rs.getString("textbookName"));
            
            list.add(dto);
         }
         
         return list;

      } catch (Exception e) {
         System.out.println("ManagerDAO.findOpenCourse()");
         e.printStackTrace();
      }
      
      return null;
   }

   
   /**
    * 개설 과정 등록 시 등록 가능한 과정 목록을 저장하여 반환하는 메소드입니다.
    * @param courseStartDate 선택한 개설 과정 시작일
    * @return 과정 기간 내에 등록 가능한 과정 목록을 반환합니다.
    */
   public ArrayList<CourseDTO> availableCourse(String courseStartDate) {
      
      try {

         String sql = String.format("select"
               + "    distinct c.seq as seq,"
               + "    c.name as name,"
               + "    c.goal as goal,"
               + "    c.period as period "
               + "from tblOpenCourse oc inner join tblCourse c on oc.courseSeq = c.seq"
               + "    where c.seq not in (select distinct courseSeq from tblOpenCourse where status <> '삭제' and ('%s' < endDate and startDate < to_date('%s', 'yyyymmdd') + (select max(period) from tblCourse)))"
               + "        and oc.status <> '삭제' and c.name <> '없음' order by c.seq", courseStartDate, courseStartDate);

         rs = stat.executeQuery(sql);
         
         ArrayList<CourseDTO> list = new ArrayList<CourseDTO>();
         
         while (rs.next()) {
            
            CourseDTO dto = new CourseDTO();
            
            dto.setSeq(rs.getString("seq"));
            dto.setName(rs.getString("name"));
            dto.setGoal(rs.getString("goal"));
            dto.setPeriod(rs.getString("period"));
            
            list.add(dto);
         }
         
         return list;

      } catch (Exception e) {
         System.out.println("ManagerDAO.availableCourse()");
         e.printStackTrace();
      }
      
      return null;
   }
   
   
   /**
    * 개설 과정 등록 시 사용 가능한 강의실 목록을 저장하여 반환하는 메소드입니다.
    * @param courseSeq 과정 번호
    * @param courseStartDate 선택한 개설 과정 시작일
    * @return 사용 가능한 강의실 목록을 반환합니다.
    */
   public ArrayList<ClassroomDTO> availableClassroom(String courseSeq, String courseStartDate) {
      
      
      try {

         String sql = "{ call procAvailableClassroom(?, ?, ?) }";
         
         cstat = conn.prepareCall(sql);
         
         cstat.setString(1, courseSeq);
         cstat.setString(2, courseStartDate);
         cstat.registerOutParameter(3, OracleTypes.CURSOR);
         
         cstat.executeQuery();
         
         rs = (ResultSet)cstat.getObject(3);
         
         ArrayList<ClassroomDTO> list = new ArrayList<ClassroomDTO>();
         
         while (rs.next()) {
            
            ClassroomDTO dto = new ClassroomDTO();
            
            dto.setSeq(rs.getString("seq"));
            dto.setName(rs.getString("name"));
            dto.setPersonnel(rs.getString("personnel"));
            
            list.add(dto);
         }
         
         return list;

      } catch (Exception e) {
         System.out.println("ManagerDAO.availableClassroom()");
         e.printStackTrace();
      }
      
      return null;
   }


   /**
    * 새로운 개설 과정 등록을 위한 메소드입니다.
    * @param courseStartDate 선택한 개설 과정 시작일
    * @param courseSeq 과정 번호
    * @param classroomSeq 강의실 번호
    * @return 개설 과정 등록의 성공 유무(성공 시 1)를 반환합니다.
    */
   public int addOpenCourse(String courseStartDate, String courseSeq, String classroomSeq) {
      
      try {

         String sql = "{ call procAddOpenCourse(?, ?, ?) }";
         
         cstat = conn.prepareCall(sql);
         
         cstat.setString(1, courseStartDate);
         cstat.setString(2, courseSeq);
         cstat.setString(3, classroomSeq);
         
         return cstat.executeUpdate();

      } catch (Exception e) {
         System.out.println("ManagerDAO.addOpenCourse()");
         e.printStackTrace();
      }
      
      return 0;
      
   }

   
   /**
    * 개설 과정 수정 시 수정 가능한 강의실 목록을 저장하여 반환하는 메소드입니다.
    * @param courseSeq 개설 과정 번호
    * @return 수정 가능한 강의실 목록을 반환합니다.
    */
   public ArrayList<ClassroomDTO> availableReplaceClassroom(String courseSeq) {
      
      try {

         String sql = "{ call procAvailableReplaceClassroom(?, ?) }";
         
         cstat = conn.prepareCall(sql);
         
         cstat.setString(1, courseSeq);
         cstat.registerOutParameter(2, OracleTypes.CURSOR);
         
         cstat.executeQuery();
         
         rs = (ResultSet)cstat.getObject(2);
         
         ArrayList<ClassroomDTO> list = new ArrayList<ClassroomDTO>();
         
         while (rs.next()) {
            
            ClassroomDTO dto = new ClassroomDTO();
            
            dto.setSeq(rs.getString("seq"));
            dto.setName(rs.getString("name"));
            dto.setPersonnel(rs.getString("personnel"));
            
            list.add(dto);
         }
         
         return list;

      } catch (Exception e) {
         System.out.println("OpenCourseDAO.availableReplaceClassroom()");
         e.printStackTrace();
      }
      
      return null;
   }


   /**
    * 선택한 개설 과정의 강의실을 수정하는 메소드입니다. 
    * @param courseSeq 개설 과정 번호
    * @param classroomSeq 강의실 번호
    * @return 강의실 수정의 성공 유무(성공 시 1)를 반환합니다.
    */
   public int replaceClassroom(String courseSeq, String classroomSeq) {

      try {

         String sql = "{call procReplaceOpenCourseClassroom(?, ?) }";
         
         cstat = conn.prepareCall(sql);
         
         cstat.setString(1, courseSeq);
         cstat.setString(2, classroomSeq);
         
         return cstat.executeUpdate();
         
      } catch (Exception e) {
         System.out.println("OpenCourseDAO.replaceOpenCourseClassroom()");
         e.printStackTrace();
      }
      
      return 0;
   }
   

   /**
    * 선택한 개설 과정의 교육생 정보를 저장하여 반환하는 메소드입니다.
    * @param courseSeq 개설 과정 번호
    * @return 개설 과정의 교육생 정보를 반환합니다.
    */
   public ArrayList<OpenCourseStudentDTO> findStudent(String courseSeq) {
      
      try {

         String sql = "{ call procFindOpenCourseStudent(?, ?) }";
         
         cstat = conn.prepareCall(sql);
         
         cstat.setString(1, courseSeq);
         cstat.registerOutParameter(2, OracleTypes.CURSOR);

         cstat.executeQuery();
         
         rs = (ResultSet)cstat.getObject(2);
         
         ArrayList<OpenCourseStudentDTO> list = new ArrayList<OpenCourseStudentDTO>();
         
         while (rs.next()) {
            
            OpenCourseStudentDTO dto = new OpenCourseStudentDTO();
            
            dto.setSeq(rs.getString("seq"));
            dto.setName(rs.getString("name"));
            dto.setSsn(rs.getString("ssn"));
            dto.setTel(rs.getString("tel"));
            dto.setStatus(rs.getString("status"));
            dto.setEndDate(rs.getString("endDate"));
            
            list.add(dto);
         }
         
         return list;

      } catch (Exception e) {
         System.out.println("OpenCourseDAO.findOpenCourseStudent()");
         e.printStackTrace();
      }
      
      return null;
   }


   /**
    * 수료일이 개설 과정 종료일보다 같거나 큰지 확인하는 메소드입니다.
    * @param courseSeq 개설 과정 번호
    * @param endDate 수정하려는 수료일
    * @return 수정 가능하면 1, 수정 불가능하면 0을 반환합니다.
    */
   public int availableReplaceEndDate(String courseSeq, String endDate) {
      
      try {

         String sql = "select fnAvailableEndDate(?, ?) as result from dual";
         
         pstat = conn.prepareStatement(sql);
         
         pstat.setString(1, courseSeq);
         pstat.setString(2, endDate);
         
         rs = pstat.executeQuery();
         
         if (rs.next()) {
            
            return rs.getInt("result");
         }

      } catch (Exception e) {
         System.out.println("OpenCourseDAO.availableReplaceEndDate()");
         e.printStackTrace();
      }
      
      return 0;
   }

   
   /**
    * 선택한 개설 과정의 교육생의 수료일을 수정하는 메소드입니다.
    * @param courseSeq 개설 과정 번호
    * @param endDate 수료일
    * @return 수료일 수정의 성공 유무(성공 시 1)를 반환합니다.
    */
   public int replaceCompletionDate(String courseSeq, String endDate) {
      
      try {

         String sql = "{ call procReplaceCompletionDate(?, ?) }";
         
         cstat = conn.prepareCall(sql);
         
         cstat.setString(1, courseSeq);
         cstat.setString(2, endDate);
         
         return cstat.executeUpdate();

      } catch (Exception e) {
         System.out.println("OpenCourseDAO.replaceCompletionDate()");
         e.printStackTrace();
      }
      
      return 0;
   }

   
   /**
    * 개설 과정 삭제 시 선택한 개설 과정 정보를 저장하여 반환하는 메소드입니다.
    * @param openCourseSeq 개설 과정 번호
    * @return 개설 과정 정보를 반환합니다.
    */
   public OpenCourseDTO findOneLineOpenCourse(String openCourseSeq) {
      
      try {
         
         String sql = "select * from vwFindOpenCourse where seq = ?";
         
         pstat = conn.prepareStatement(sql);
         
         pstat.setString(1, openCourseSeq);
         
         rs = pstat.executeQuery();
         
         if (rs.next()) {
            
            OpenCourseDTO dto = new OpenCourseDTO();
            
            dto.setSeq(rs.getInt("seq"));
            dto.setName(rs.getString("name"));
            dto.setStartDate(rs.getString("startDate"));
            dto.setEndDate(rs.getString("endDate"));
            dto.setClassroomName(rs.getString("classroomName"));
            dto.setWhetherRegister(rs.getString("whetherRegister"));
            dto.setPersonnel(rs.getInt("personnel"));
            dto.setStatus(rs.getString("status"));
            
            return dto;
         }

      } catch (Exception e) {
         System.out.println("ManagerDAO.findOneOpenCourse()");
         e.printStackTrace();
      }
      
      return null;
   }
      
   
   /**
    * 개설 과정 삭제하는 메소드입니다.
    * @param openCourseSeq 개설 과정 번호
    * @return 개설 과정 삭제의 성공 유무(성공 시 1)를 반환합니다.
    */
   public int deleteOpenCourse(String openCourseSeq) {
   
      try {

         String sql = "{ call procDeleteOpenCourse(?) }";
         
         cstat = conn.prepareCall(sql);
         
         cstat.setString(1, openCourseSeq);
         
         return cstat.executeUpdate();

      } catch (Exception e) {
         System.out.println("ManagerDAO.deleteOpenCourse()");
         e.printStackTrace();
      }
      
      return 0;
   }

   
   /**
    * 현재 페이지의 위치를 조회하는 메소드입니다.
    * @param page 페이지 수
    * @return 페이지 위치 값(첫 페이지 : 1, 마지막 페이지 : -1, 그 외 : 0)을 반환합니다.
    */
   public int findPage(int page) {
      
      try {

         String sql = "select fnFindPage(?) as nowPage from dual";
         
         pstat = conn.prepareStatement(sql);
         pstat.setInt(1, page);
         
         rs = pstat.executeQuery();
         
         if (rs.next()) {
            
            return rs.getInt("nowPage");
         }

      } catch (Exception e) {
         System.out.println("OpenCourseDAO.findPage()");
         e.printStackTrace();
      }
      
      return 0;
   }

}