# :bulb: EduCenter
관계형 데이터베이스 기반의 교육센터 운영 및 관리 프로그램입니다.
<table>
  <tr>
    <td width="50%"><img src="https://user-images.githubusercontent.com/77385338/104592223-3434ae80-56b1-11eb-8056-df3b5529b18e.PNG"></td>
    <td width="50%"><img src="https://user-images.githubusercontent.com/77385338/104592254-3e56ad00-56b1-11eb-84eb-39f81cdb7f3f.PNG"></td>
  </tr>
</table>

***

# 👋</g-emoji> Introduction</h3>
<table align="center">
    <tr>
        <th>프로젝트 명</th>
        <th>교육센터 CRUD</th>
        <th>개발 기간</th>
        <th>2020/12/02 ~ 2020/12/24</th>
    </tr>
    <tr>
        <th>프로젝트 성격</th>
        <th>Oracle DBMS와 JDBC API를 이용한 콘솔 프로젝트</th>
        <th>개발 인원</th>
        <th>6명<br>
          (<a href="https://github.com/iamleechanmi">이찬미</a>/</a>오수경</a>/</a>윤지봉</a>/</a>이대홍</a>/</a>이진혁</a>/</a>이청강</a>)
      </th>
    </tr>
      <tr>
        <th>프로젝트 개요</th>
        <th>교육센터 운영 및 관리 프로그램</th>
        <th>개발 환경</th>
        <th>Windows10</th>
    </tr>
    <tr>
        <th colspan="6">사용 도구</th>
    </tr>  
    <tr>
        <th>개발 언어</th>
        <th colspan="5">Java(JDK 1.8), Oracle SQL</th>
    </tr>
    <tr>
        <th>개발 도구</th>
        <th colspan="5">Eclipse JEE, Oracle SQL Developer, eEXERD</th>
    </tr>
</table>

***
# :bar_chart: ERD
<table>
  <tr>
    <th width="50%">논리</th>
    <th width="50%">물리</th>
  </tr>
  <tr>
    <th><img src="https://user-images.githubusercontent.com/77385338/104591949-cdaf9080-56b0-11eb-81e2-525cef4e2137.png"></th>
    <th><img src="https://user-images.githubusercontent.com/77385338/104591982-dd2ed980-56b0-11eb-947f-d2f3ec42af21.png"></th>
  </tr>
</table>

***
# 📑 Role & Member
<table align="center" width="1000px">
  <tr>
    <th colspan="3">관리자</th>
    <th colspan="2">교사</th>
    <th>교육생</th>
  </tr>
  <tr>
    <th width="166px">이진혁</th>
    <th width="166px">이찬미</th>
    <th width="166px">이청강</th>
    <th width="166px">윤지봉</th>
    <th width="166px">이대홍</th>
    <th width="166px">오수경</th>
  </tr>
  <tr>
    <th>기초정보관리<br>교사계정관리<br>스터디그룹관리</th>
    <th>개설과정관리<br>개설과목관리</th>
    <th>교육생관리<br>시험관리 및 성적조회<br>출결관리 및 조회<br></th>
    <th>담당과정조회<br>출결조회</th>
    <th>성적정보관리<br>배점정보관리</th>
    <th>성적조회<br>출석체크/현황조회<br>스터디그룹관리</th>
  </tr>
  <tr>
    <th colspan="6">요구분석서작성, ERD설계, 순서도 작성<br>화면 설계, 더미데이터 생성<br>SQL 쿼리 및 PL/SQL 작성, 데이터베이스 관리, 기능 구현</th>
  </tr>
</table>

***
# :computer: Implementation
**아래는 프로젝트에서 구현을 맡은 부분입니다.**
<table align="center" >
  <tr>
    <th>개설과정관리</th>
  </tr>
  <tr>
    <th align="center"><img src="https://user-images.githubusercontent.com/77385338/104592011-e5871480-56b0-11eb-86cc-769b666e79f9.gif">
    <a href="https://github.com/iamleechanmi/eduCenterTeamProject/blob/master/%5B08%5D%EC%86%8C%EC%8A%A4%EC%BD%94%EB%93%9C/ProjectTest/src/com/test/dao/OpenCourseDAO.java">OpenCourseDAO.java</a> , <a href="https://github.com/iamleechanmi/eduCenterTeamProject/blob/master/%5B08%5D%EC%86%8C%EC%8A%A4%EC%BD%94%EB%93%9C/ProjectTest/src/com/test/manager/ManagerOpenCourse.java">ManagerOpenCourse.java</a>
    </th>
  </tr>
  <tr>
    <th>개설과목관리</th>
  </tr>
  <tr>
    <th align="center"><img src="https://user-images.githubusercontent.com/77385338/104592039-f0da4000-56b0-11eb-836f-11371a308b40.gif">
    <a href="https://github.com/iamleechanmi/eduCenterTeamProject/blob/master/%5B08%5D%EC%86%8C%EC%8A%A4%EC%BD%94%EB%93%9C/ProjectTest/src/com/test/dao/OpenSubjectDAO.java">OpenSubjectDAO.java</a> , <a href="https://github.com/iamleechanmi/eduCenterTeamProject/blob/master/%5B08%5D%EC%86%8C%EC%8A%A4%EC%BD%94%EB%93%9C/ProjectTest/src/com/test/manager/ManagerOpenSubject.java">ManagerOpenSubject.java</a></th>
  </tr>
  <tr>
    <th><a href="https://github.com/iamleechanmi/eduCenterTeamProject/blob/master/%5B04%5DDML/%EA%B4%80%EB%A6%AC%EC%9E%90/%EA%B4%80%EB%A6%AC%EC%9E%90%5B2%5D.sql">DML 보기</a></th>
  </tr>
</table>

<br><br>
**아래는 담당업무 별 대표화면입니다.**
<table align="center">
  <tr>
    <th width="50%">1. 메인화면</th>
    <th width="50%">2. 관리자 - 과정조회</th>
  </tr>
  <tr>
    <td align="center"><img src="https://user-images.githubusercontent.com/77385338/104599582-54696b00-56bb-11eb-8707-44da3bf5a5cc.png" height="200px"></td>
    <td align="center"><img src="https://user-images.githubusercontent.com/77385338/104599838-9c888d80-56bb-11eb-9dcc-9106ba07b9c6.png" height="200px"></td>
  </tr>
  <tr>
    <th colspan="2">3. 관리자 - 개설과정등록</th>
  </tr>
  <tr>
    <td colspan="2" align="center"><img src="https://user-images.githubusercontent.com/77385338/104600029-d194e000-56bb-11eb-8e1a-3f0c773e5ca0.png" height="600px"></td>
  </tr>
  <tr>
    <th>4. 관리자 - 출결조회</th>
    <th>5. 교사 - 배점조회</th>
  </tr>
  <tr>
    <td align="center"><img src="https://user-images.githubusercontent.com/77385338/104600055-d9548480-56bb-11eb-8078-413993344aff.png" height="400px"></td>
    <td align="center"><img src="https://user-images.githubusercontent.com/77385338/104600104-ecffeb00-56bb-11eb-97cf-7815a060664d.gif" height="300px"></td>
  </tr>
  <tr>
    <th>6. 교사 - 담당과목조회</th>
    <th>7. 교육생 - 출결조회</th>
  </tr>
  <tr>
    <td align="center"><img src="https://user-images.githubusercontent.com/77385338/104600128-f38e6280-56bb-11eb-998a-f193d51fca06.png" height="200px"></td>
    <td align="center"><img src="https://user-images.githubusercontent.com/77385338/104600539-79121280-56bc-11eb-9fb0-3e9f0c144c0a.png" height="400px"></td>
  </tr>
</table>

***
# :speech_balloon: Review
<img src="https://user-images.githubusercontent.com/77385338/104592191-2717bf80-56b1-11eb-9c13-a1ad01500301.jpg">
