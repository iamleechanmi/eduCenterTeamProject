package com.test.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.dto.TeacherPointDTO;
import com.test.dto.vwFindCourseSubjectDTO;

import oracle.jdbc.OracleTypes;

public class TeacherPointDAO {

	public ArrayList<vwFindCourseSubjectDTO> vwFindCourseSubjectList(int teacherNumber, String info) {
		// TODO Auto-generated method stub
		
		Connection conn =null;
		Statement stat = null;
		ResultSet rs = null;
		
		String where = "";
		if (info.equals("")) {																	//배점 수정조회
			where = String.format(" where openSubjectSeq in "
					+ "(select openSubjectSeq from tblteacherrecord "
					+ " where teacherseq = %d and status in ('예정', '진행') )",teacherNumber);
		} else if (info.equals("조회")) { 														// 단순조회 
			where = String.format(" where openSubjectSeq in "
					+ "(select openSubjectSeq from tblteacherrecord "
					+ " where teacherseq = %d )",teacherNumber);
		} else {
			where = String.format(" where openSubjectSeq in "
					+ "(select openSubjectSeq from tblteacherrecord "
					+ " where teacherseq = %d ) and openSubjectSeq = %s ",teacherNumber,info);
		}
		
		try {
			conn =DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "Select * from vwFindCourseSubject " + where;
			
			rs = stat.executeQuery(sql);
			
			ArrayList<vwFindCourseSubjectDTO> list = new ArrayList<vwFindCourseSubjectDTO>();
			/*
			String manu = String.format("[개설과목번호] [과정명] [과목명] [시작일]"
					+ " [종료일] [출석배점] [필기배점] [실기배점] [시험문제등록]");
			System.out.println(manu);
			
			int count =0; 
			*/
			
			while(rs.next()) {
				vwFindCourseSubjectDTO dto = new vwFindCourseSubjectDTO();
				dto.setOpenSubjectSeq(rs.getString("openSubjectSeq"));
				dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				dto.setTeacherSeq(rs.getString("teacherSeq"));
				dto.setStatus(rs.getString("status"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setStartdate(rs.getString("startdate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setAttendancePoint(rs.getString("attendancePoint"));
				dto.setWrittenPoint(rs.getString("writtenPoint"));
				dto.setPracticalPoint(rs.getString("practicalPoint"));
				dto.setWhetherExam(rs.getString("whetherExam"));
				dto.setWhetherGrade(rs.getString("WhetherGrade"));
				list.add(dto);
			}
			
			
			rs.close();
			stat.close();
			conn.close();
			
			return list;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TeacherPointDAO_vwFindCourseSubjectList()");
			e.printStackTrace();
			System.out.println("자세한 사항은 관리자에게 문의해주세요");
			
		}
		
		return null;
	}//vwFindCourseSubjectList

	//배점입력
	public int pointAdd(TeacherPointDTO pointAdd) {
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
		
			conn = DBUtil.open();
			
			//개설과목번호, 출석, 필기, 실기
			String sql = "{ call procAddPoint(?,?,?,?) }";
			
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1,pointAdd.getOpenSubjectSeq());
			cstat.setString(2,pointAdd.getAttendancePoint());
			cstat.setString(3,pointAdd.getWrittenPoint());
			cstat.setString(4,pointAdd.getPracticalPoint());
			
			int result = cstat.executeUpdate();
			
			cstat.close();
			conn.close();
			
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TeacherPointDAO_PointAdd()");
			e.printStackTrace();
			System.out.println("자세한사항은 관리자에게 문의해주세요");
		} 
		
		return 0;
	}// pointAdd

	public int pointReplace(TeacherPointDTO pointReplace) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		CallableStatement cstat;
		
		try {
			conn = DBUtil.open();
			
			String sql = "{call procReplacePoint(?,?,?,?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, pointReplace.getSeq());
			cstat.setString(2, pointReplace.getAttendancePoint());
			cstat.setString(3, pointReplace.getWrittenPoint());
			cstat.setString(4, pointReplace.getPracticalPoint());
			
			int result = cstat.executeUpdate();
			
			cstat.close();
			conn.close();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("TeacherPointDAO_pointReplace");
			e.printStackTrace();
			System.out.println("자세한사항은 관리자에게 문의해주세요");
		}
		
		return 0;
	} // pointReplace

	public int whetherExamAdd(vwFindCourseSubjectDTO dto) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		CallableStatement cstat = null;
		
		try {
			
			conn =DBUtil.open();
			
			String sql = "{ call procAddWhetherExam(?,?) }";

			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getOpenSubjectSeq());
			cstat.setString(2, dto.getWhetherExam());
			
			int result = cstat.executeUpdate();
						
			cstat.close();
			conn.close();
			
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TeacherPointDAO_whetherExamAdd()");
			e.printStackTrace();
			System.out.println("자세한 사항은 관리자에게 문의해주세요");
		}

		return 0;
	} // TeacherPointDAO_whetherExamAdd
	
	
	//등록 수정
	public int whetherExamReplace(vwFindCourseSubjectDTO dto) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement cstat = null;
	
		try {
			
			conn =DBUtil.open();
			
			String sql = "{ call procReplaceWhetherExam(?,?) }";
			
			cstat = conn.prepareCall(sql);
			String info = dto.getOpenSubjectSeq();
			cstat.setString(1, info);
			cstat.registerOutParameter(2, OracleTypes.VARCHAR);
			cstat.executeQuery();
			String name = cstat.getString(2);
			System.out.println(name + "으로 변경");
			
			
			sql = "{ call procAddWhetherExam(?,?) }";

			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, info);
			cstat.setString(2, dto.getWhetherExam());
			
			int result = cstat.executeUpdate();
			
			cstat.close();
			conn.close();
			
			return result;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TeacherPointDAO_whetherExamReplace()");
			e.printStackTrace();
			System.out.println("자세한 사항은 관리자에게 문의해주세요");
		}

		return 0;

	}

} //class
