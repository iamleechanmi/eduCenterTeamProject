package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.AddOpenSubjectDTO;
import com.test.dto.OpenSubjectDTO;
import com.test.dto.SubjectDTO;
import com.test.dto.TeacherDTO;
import com.test.dto.TextbookDTO;

import oracle.jdbc.OracleTypes;

/**
 * OpenSubjectDAO. 개설 과목 관리에 필요한 데이터를 조회 및 반환하는 클래스입니다.
 * @author 이찬미
 *
 */
public class OpenSubjectDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;

	
	/**
	 * DB 연결을 위한 기본 생성자입니다.
	 */
	public OpenSubjectDAO() {

		try {

			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.생성자");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 특정한 개설 과목 조회 시 과목 정보를 저장하여 반환하는 메소드입니다.
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 개설 과목 정보를 반환합니다.
	 */
	public OpenSubjectDTO findOpenSubject(String openSubjectSeq) {
		
		try {

			String sql = "{ call procFindOneOpenSubject(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, openSubjectSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			if (rs.next()) {
				
				OpenSubjectDTO dto = new OpenSubjectDTO();
				
				dto.setCourseName(rs.getString("courseName"));
				dto.setCourseStartDate(rs.getString("courseStartDate"));
				dto.setCourseEndDate(rs.getString("courseEndDate"));
				dto.setClassroomName(rs.getString("classroomName"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setSubjectStartDate(rs.getString("subjectStartDate"));
				dto.setSubjectEndDate(rs.getString("subjectEndDate"));
				dto.setWhetherExam(rs.getString("whetherExam"));
				dto.setWhetherGrade(rs.getString("whetherGrade"));
				dto.setTeacherName(rs.getString("teacherName"));
				dto.setTextbookName(rs.getString("textbookName"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.findOpenSubject()");
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * 개설 과목 등록 시 개설 과정과 개설 과목의 날짜 차이를 계산하는 메소드입니다.
	 * @param openCourseSeq 개설 과정 번호
	 * @return 개설 과목 등록의 성공 유무(성공 시 0 이상의 양수)를 반환합니다.
	 */
	public int availableOpenSubject(String openCourseSeq) {
		
		try {

			String sql = "select"
					+ "    (select distinct courseEndDate from vwFindEndDate where courseSeq = ?) "
					+ "    -"
					+ "    (select max(subjectEndDate) from vwFindEndDate where courseSeq = ?) "
					+ "    -"
					+ "    (select min(period) from tblSubject where period <> 0 and name <> '없음') as difference "
					+ "from dual";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, openCourseSeq);
			pstat.setString(2, openCourseSeq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("difference");
			}	
			
		} catch (Exception e) {
			System.out.println("ManagerDAO.availableOpenSubjectDate()");
			e.printStackTrace();
		}
		
		return -1;
	}


	/**
	 * 개설 과목 등록 시 시작일이 개설 과정 내에 있고 가장 최근 과목 종료일보다 큰지 계산하는 메소드입니다.
	 * @param openCourseSeq 개설 과정 번호
	 * @param subjectStartDate 개설 과목 시작일
	 * @return 등록 가능한 시작일이면 1, 등록 불가능한 시작일이면 0을 반환합니다.
	 */
	public int availablePeriod(String openCourseSeq, String subjectStartDate) {
		
		try {
			
			String sql = "select fnAvailableOpenSubjectPeriod(?, ?) as result from dual";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, openCourseSeq);
			pstat.setString(2, subjectStartDate);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt("result");
			}

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.availableOpenSubjectPeriod()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	/**
	 * 개설 과목 등록 시 등록 가능한 과목 목록을 저장하여 반환하는 메소드입니다.
	 * @param openCourseSeq 개설 과정 번호
	 * @return 등록 가능한 과목 목록을 반환합니다.
	 */
	public ArrayList<SubjectDTO> availableSubject(String openCourseSeq) {
		
		try {
			
			String sql = "select "
					+ "    distinct j.seq as seq, "
					+ "    rpad(j.name, 40, ' ') as name, "
					+ "    j.period as period "
					+ "from tblSubject j "
					+ "    inner join tblOpenSubject oj "
					+ "        on j.seq = oj.subjectSeq "
					+ "            inner join tblOpenCourse oc "
					+ "                on oc.seq = oj.openCourseSeq "
					+ "                    where oj.subjectSeq not in (select distinct subjectSeq "
					+ "                                                from tblOpenSubject "
					+ "                                                    where openCourseSeq = ?) "
					+ "                    and j.name <> '없음' and oc.status <> '삭제' "
					+ "                        order by j.seq";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, openCourseSeq);
			
			rs = pstat.executeQuery();
			
			ArrayList<SubjectDTO> list = new ArrayList<SubjectDTO>();
			
			while (rs.next()) {
				
				SubjectDTO dto = new SubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setPeriod(rs.getString("period"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.availableOpenSubject()");
			e.printStackTrace();
		}
		
		return null;
	}
	

	/**
	 * 개설 과목 등록 시 해당 과목 강의가 가능한 교사 목록을 저장하여 반환하는 메소드입니다.
	 * @param subjectSeq 과목 번호
	 * @param subjectStartDate 개설 과목 시작일
	 * @return 강의 가능한 교사 목록을 반환합니다.
	 */
	public ArrayList<TeacherDTO> availableTeacher(String subjectSeq, String subjectStartDate) {
		
		try {

			String sql = "select "
					+ "    distinct te.seq as seq, "
					+ "    te.name as name, "
					+ "    te.ssn as ssn, "
					+ "    substr(te.tel, 1, 3) || '-' || substr(te.tel, 4, 4) || '-' || substr(te.tel, 8, 4) as tel "
					+ "from tblPossibleSubject pj "
					+ "    inner join tblTeacher te "
					+ "        on te.seq = pj.teacherSeq "
					+ "            where pj.subjectSeq = ? and te.name <> '없음' "
					+ "            and te.seq not in (select "
					+ "    tr.teacherSeq as teacherSeq "
					+ "from tblTeacherRecord tr "
					+ "    inner join tblOpenSubject oj "
					+ "        on tr.openSubjectSeq = oj.seq "
					+ "            where oj.endDate >= to_date(?, 'yyyymmdd') "
					+ "            and oj.startDate <= to_date(?, 'yyyymmdd')) "
					+ "                order by te.seq";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, subjectSeq);
			pstat.setString(2, subjectStartDate);
			pstat.setString(3, subjectStartDate);
			
			rs = pstat.executeQuery();
			
			ArrayList<TeacherDTO> list = new ArrayList<TeacherDTO>();
			
			while (rs.next()) {
				
				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				
				list.add(dto);
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.availableTeacher()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 개설 과목 등록하는 메소드입니다.
	 * @param dto 개설 과정 등록에 필요한 정보
	 * @return 개설 과목 등록의 성공 유무(성공 시 1)를 반환합니다.
	 */
	public int addOpenSubject(AddOpenSubjectDTO dto) {
	
		try {

			String sql = " {call procAddOpenSubject(?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getOpenCourseSeq());
			cstat.setString(2, dto.getStartDate());
			cstat.setString(3, dto.getSubjectSeq());
			cstat.setString(4, dto.getTeacherSeq());
			cstat.setString(5, dto.getTextbookSeq());
			
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.addOpenSubject()");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * 교재 목록을 저장하여 반환하는 메소드입니다. 
	 * @return 교재 목록을 반환합니다.
	 */
	public ArrayList<TextbookDTO> textbook() {
		
		try {

			String sql = "select "
					+ "    lpad(seq, 2, ' ') as seq, "
					+ "    rpad(name, 35, ' ') as name, "
					+ "    rpad(publisher, 14, ' ') as publisher "
					+ "from tblTextbook where name <> '없음'";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<TextbookDTO> list = new ArrayList<TextbookDTO>();
			
			while (rs.next()) {
				
				TextbookDTO dto = new TextbookDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setPublisher(rs.getString("publisher"));
				
				list.add(dto);
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.textbook()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 개설 과목 수정 시 수정 가능한 교사 목록을 저장하여 반환하는 메소드입니다.
	 * @param replaceSubjectSeq 개설 과목 번호
	 * @return 수정 가능한 교사 목록을 반환합니다.
	 */
	public ArrayList<TeacherDTO> availableReplaceTeacher(String replaceSubjectSeq) {
	
		try {

			String sql = "select "
					+ "    distinct te.seq as seq, "
					+ "    te.name as name, "
					+ "    te.ssn as ssn, "
					+ "    te.tel as tel "
					+ "from tblPossibleSubject pj "
					+ "    inner join tblTeacher te "
					+ "        on te.seq = pj.teacherSeq "
					+ "            where pj.subjectSeq = (select subjectSeq from tblOpenSubject where seq = ?) "
					+ "            and te.name <> '없음' "
					+ "            and te.seq not in (select "
					+ "    tr.teacherSeq as teacherSeq "
					+ "from tblTeacherRecord tr "
					+ "    inner join tblOpenSubject oj "
					+ "        on tr.openSubjectSeq = oj.seq "
					+ "            where oj.endDate >= (select startDate from tblOpenSubject where seq = ?) "
					+ "            and oj.startDate <= (select startDate from tblOpenSubject where seq = ?)) "
					+ "                order by te.seq";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, replaceSubjectSeq);
			pstat.setString(2, replaceSubjectSeq);
			pstat.setString(3, replaceSubjectSeq);
			
			rs = pstat.executeQuery();
			
			ArrayList<TeacherDTO> list = new ArrayList<TeacherDTO>();
			
			while (rs.next()) {
				
				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.availableReplaceTeacher()");
			e.printStackTrace();
		}
		
		return null;
	}
	

	/**
	 * 선택한 개설 과목의 교사를 수정하는 메소드입니다. 
	 * @param openSubjectSeq 개설 과목 번호
	 * @param teacherSeq 교사 번호
	 * @return 교사 수정의 성공 유무(성공 시 1)를 반환합니다.
	 */
	public int replaceTeacher(String openSubjectSeq, String teacherSeq) {
		
		try {

			String sql = "{ call procReplaceOpenSubjectTeacher(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, teacherSeq);
			
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.replaceTeacher()");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * 선택한 개설 과목의 교재를 수정하는 메소드입니다.
	 * @param openSubjectSeq 개설 과목 번호
	 * @param textbookSeq 교재 번호
	 * @return 교재 수정의 성공 유무(성공 시 1)를 반환합니다.
	 */
	public int replaceTextbook(String openSubjectSeq, String textbookSeq) {
		
		try {

			String sql = "update tblOpenSubject set textbookSeq = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, textbookSeq);
			pstat.setString(2, openSubjectSeq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.replaceTextbook()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	/**
	 * 개설 과목 삭제하는 메소드입니다.
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 개설 과목 삭제의 성공 유무(성공 시 1)를 반환합니다.
	 */
	public int deleteOpenSubject(String openSubjectSeq) {
		
		try {

			String sql = "{ call procDeleteOpenSubject(?) }";

			cstat = conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("ManagerDAO.deleteOpenSubject()");
			e.printStackTrace();
		}
		
		return 0;
	}











	








	
}
