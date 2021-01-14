-- 더미데이터.sql

-- 1단계

-- 관리자.tblmanager(seq, ssn, name)
-- 3명

insert into tblmanager(seq, ssn, name) values (seqmanager.nextVal, '1234567', '홍길동');
insert into tblmanager(seq, ssn, name) values (seqmanager.nextVal, '2123456', '이민아');
insert into tblmanager(seq, ssn, name) values (seqmanager.nextVal, '2452101', '박민정');


-- 교사.tblteacher(seq, name, ssn, tel)
-- 10명 / 6명 기존 교사 + 4명 예비 교사

insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '이강인', '1546237', '01054625555');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '윤석영', '1022451', '01013284565');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '박주호', '1512385', '01028546235');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '이동국', '1782153', '01054215325');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '김진수', '1578623', '01051233697');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '장슬기', '2546231', '01051233554');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '고예림', '2002153', '01046218325');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '박인비', '2546213', '01051254752');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '김연경', '2035654', '01051756352');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '지소연', '2847562', '01084516520');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '주지훈', '1057562', '01077776520');
insert into tblteacher(seq, name, ssn, tel) values (seqteacher.nextVal, '전지현', '2057082', '01088886520');

-- 강의실.tblclassroom(seq, name, personnel)
-- 6개 강의실(1,2,3 강의실 정원 30명/4,5,6 강의실 정원 26명)

insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '1강의실', 30);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '2강의실', 30);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '3강의실', 30);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '4강의실', 26);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '5강의실', 26);
insert into tblclassroom(seq, name, personnel) values (seqclassroom.nextVal, '6강의실', 26);


-- 스터디그룹.tblstudygroup(seq, goal)
-- 10개 그룹 목표
-- 개설과정 6
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '프로젝트', '2020-01-06', '2020-03-06');
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '코딩공부', '2020-03-09', '2020-05-08');
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '코딩공부', '2020-05-11', '2020-07-10');

-- 개설과정 9
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '오라클공부', '2020-10-30', '2020-12-29');

-- 개설과정 12
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, 'FRONT-END공부', '2020-11-09', '2020-01-08');
insert into tblstudygroup(seq, goal, registrationDate, endDate) values (seqstudygroup.nextVal, '애플리케이션구현', '2020-11-19', '2020-01-08');


-- 스터디룸.tblstudyroom(seq, name)

insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '1번방');
insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '2번방');
insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '3번방');
insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '4번방');
insert into tblstudyroom(seq, name) values (seqstudyroom.nextVal, '5번방');


-- 교재.tbltextbook(seq, name, publisher)

--1
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, '데이터베이스 개론', '한빛아카데미');
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, '이것이 MySQL이다', '한빛미디어');
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, '원리부터 관계형 데이터베이스', '위키북스');
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, 'Do it! 오라클로 배우는 데이터베이스', '이지스퍼블리싱');
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, '실전 DB 모델링과 SQL for Oracle', '혜지원');

--2
insert into tbltextbook(seq, name, publisher) values (seqtextbook.nextVal, '생활코딩!자바프로그래밍', '위키북스');
insert into tbltextbook(seq, name, publisher) values (seqtextbook.nextVal, '프론트엔드개발첫걸음', '아이콕스');
insert into tbltextbook(seq, name, publisher) values (seqtextbook.nextVal, '백엔드개발첫걸음', '아이콕스');
insert into tbltextbook(seq, name, publisher) values (seqtextbook.nextVal, '깔끔한 파이썬 백엔드', '비제이퍼블릭');
insert into tbltextbook(seq, name, publisher) values (seqtextbook.nextVal, '프레임워크 정석', '이지스퍼블리싱');

--3
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, 'Do it! 웹 사이트 따라 만들기','이지스퍼블리싱');
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, '웹 클라이언트 프로그래밍','정익사');
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, 'Do it! 점프 투 파이썬','이지스퍼블리싱');
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, '자바 객체지향 프로그래밍 입문','에이콘출판사');
insert into tblTextbook (seq, name, publisher) values (seqTextbook.nextVal, 'Go 풀스택 웹 개발','에이콘출판사');

--4
insert into tblTextbook (seq, name, publisher) values (seqTextBook.nextval, 'Boost C++ 애플리케이션 개발 ','에이콘');
insert into tblTextbook (seq, name, publisher) values (seqTextBook.nextval, '데이터 중심 애플리케이션 설계','위키북스');
insert into tblTextbook (seq, name, publisher) values (seqTextBook.nextval, '이것이 UX/UI 디자인이다','위키북스');
insert into tblTextbook (seq, name, publisher) values (seqTextBook.nextval, '애플리케이션 UI 디자인,프로그래밍','정보문화사');
insert into tblTextbook (seq, name, publisher) values (seqTextBook.nextval, '임베디드 시스템 제작 및 구현','홍릉과학출판사');

--5
insert into tblTextbook values (seqTextbook.nextVal, '데이터 중심 애플리케이션 설계', '위키북스');
insert into tblTextbook values (seqTextbook.nextVal, '스프링 5 레시피', '한빛미디어');
insert into tblTextbook values (seqTextbook.nextVal, 'IT 엔지니어를 위한 네트워크 입문', '길벗');
insert into tblTextbook values (seqTextbook.nextVal, '소프트웨어 요구사항 3', '위키북스');
insert into tblTextbook values (seqTextbook.nextVal, '자바 ORM 표준 JPA 프로그래밍', '에이콘출판');

--6
insert into tblTextbook values ( seqtextbook.nextVal, 'all in one java 애플리케이션 개발' , '와우북스' );
insert into tblTextbook values ( seqtextbook.nextVal,'차세대 애플리케이션 개발 데스트','에이콘출판');
insert into tblTextbook values ( seqtextbook.nextVal,'자바 엔터프라이즈 애플리케이션개발','에이콘출판사');
insert into tblTextbook values ( seqtextbook.nextVal,'자바 spring 레시피','한빛미디어');
insert into tblTextbook values ( seqtextbook.nextVal,'전문 Java SE 8 프로그래머과정','기전출판사');


--과정.tblcourse(seq, name, goal, period)

insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'Java기반 멀티플랫폼 융합 SW개발자 양성 과정','자바 응용 소프트웨어 구현 능력과 서버 구축 능력을 바탕으로 자바 기반의 Serlvet/JSP/스프링 서버개발자 등에 종사할 수 있다.',160); --1
insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'Java,Python 융합형 웹 프로그래머 양성과정','자바와 파이썬을 활용하여 웹과 앱을 기반으로 하는 플랫폼에 적용 가능한 개발 기법을 학습하고 프로젝트를 통해 실무 능력을 습득한다.',180); --2
insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'자바(JAVA)를 활용한 풀스택 융합 SW개발자 양성과정','디지털 기술을 기반으로 기기, 네트워크, 콘텐츠의 기획, 설계, 제작 운용 및 시험을 수행하는 기능 및 기술을 함양할 수 있다.',220); --3
insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'자바기반 Full-stack 웹 개발자 양성과정','하드웨어 시스템 및 플랫폼에 대한 이해를 바탕으로 임베디드 소프트웨어를 개발하고, 하드웨어 플랫폼에 최적화 할 수 있다.',160); --4
insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'Java언어 기반의 스마트 웹 Full-Stack개발자 양성과정','자바응용 프로그램 개발자, 자바 웹 개발자, Full-stack 개발자 등 다양한 웹 개발 분야로 진출 가능한 개발자 양성을 목표로 한다.',180); --5
insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'Java,JavaScript library을 활용한 반응형 웹 개발자 양성과정','소규모 프로젝트에 적합하며 중복코드를 줄이는 구조인 JSP와 DAO를 사용한 Model1방식의 개발을 할 수 있다.',220); --6
insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'Python 프레임워크를 활용한 웹개발 입문 교육 및 취업 지원 과정','백엔드와 웹 개발의 프로세스를 이해하고 활용할 수 있는 역량 배양,웹개발자,AI개발자로의 취업연계를 지원한다. ',160); --7
insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'자바(JAVA)기반 빅데이터 개발자 양성과정','자바 웹 개발자로 취업을 준비하실 수 있습니다',180); --8
insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'빅데이터를 활용한 지능형 웹 콘텐츠 개발자 양성과정','데이터를 활용한 지능형 웹 콘텐츠 개발자로 취업연계를 지원한다.',220); --9
insert into tblCourse (seq,name,goal,period) values (seqCourse.nextval,'Framework 기반의 빅데이터 활용 응용프로그래머 양성과정','Framework를 기반으로 한 빅데이터 활용 응용프로그래머에 종사할 수 있다.',160); --10


--과목.tblsubject(seq, name, period)

-- 공통 과목 5개
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'SQL활용', 11);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'SQL응용', 19);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '관계형 데이터베이스', 18);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'Oracle DBMS', 26);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '개념데이터 모델링', 26);

-- 개별 과목 15개
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'JAVA 프로그래밍', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'Front-end 개발', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'Back-end 개발', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'Back-end 웹 애플리케이션 구현', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '자바 프레임워크', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '반응형 웹 사이트 만들기', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '웹 클라이언트 프로그래밍(HTML5, CSS)', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '파이썬을 활용한 머신러닝 분석기술', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '객체지향과 Java SE', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'Full-stack Framework 구현', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '애플리케이션 테스트 및 배포', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '애플리케이션 설계', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'UI 디자인', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'UI 설계 및 구현', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '임베디드 애플리케이션 구현', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '통합구현', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '요구사항 분석 및 설계', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '네트워크 프로그래밍 구현', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'Spring 개발', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '웹 퍼블리싱', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'JAVA 애플리케이션 구현', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, '구현 애플리케이션 테스트', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'Spring 서버 기반의 지능형 웹과 앱 통합 개발 및 구현', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'Spring framework 통합 구현', 20);
insert into tblSubject (seq, name, period) values (seqSubject.nextVal, 'Fundamental Java SE', 20);


--교육생.tblstudent(seq, name, ssn, tel, registrationdate)
insert into tblStudent values (seqStudent.nextVal, '박민경', '2516100', '01014333270', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최주주', '2773273', '01057191021', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김우진', '1857446', '01022145197', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이경희', '1874040', '01055546790', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김주민', '1853343', '01051582583', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '이희재', '2492241', '01033903645', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정경재', '1014617', '01021045203', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최재희', '1019313', '01030827753', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김우희', '2664868', '01065201791', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이영경', '1806863', '01012761548', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '정재영', '1046759', '01038522286', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이우재', '1463821', '01001436753', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김민은', '2704876', '01037716221', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이은재', '2102163', '01036937237', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정은현', '1928464', '01017578637', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '최연경', '1042828', '01031125295', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권민주', '2061390', '01077744690', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김현진', '1554822', '01035637170', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권주영', '2683632', '01023575249', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이경현', '2117298', '01055405400', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '박영현', '1557466', '01027336401', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이민은', '1498941', '01055329089', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이현연', '2224570', '01042328240', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정현은', '1793938', '01069578465', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김재우', '2139928', '01005179325', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '박희희', '2770422', '01005373952', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박진진', '1445118', '01041185005', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김희현', '2918791', '01036442595', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정우경', '1561164', '01012153295', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박연재', '1521816', '01091523729', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '박진영', '1559523', '01036757666', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권주민', '2469236', '01038049081', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이주진', '2454388', '01047445971', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이현연', '2741048', '01060427715', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김영연', '2172599', '01039564387', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '권현영', '1849982', '01081344386', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박희현', '2629109', '01019800063', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박연현', '1080152', '01071782635', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이희현', '1624553', '01099609074', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권영민', '1411985', '01080868130', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '권현주', '1293426', '01012340771', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최희경', '2292136', '01088987742', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이영영', '1290121', '01069911984', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이우민', '1410441', '01026652579', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박진진', '2974590', '01021805412', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '정은연', '1748390', '01051961605', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박영영', '2307720', '01030353939', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권경우', '1727782', '01086760228', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정은희', '1147007', '01031271503', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김연희', '2018822', '01001039995', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '이재희', '2754740', '01037401513', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김연주', '1123714', '01078681205', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이재진', '2381165', '01001518110', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최영영', '1893159', '01096755622', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박현현', '1121729', '01055786902', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '김재우', '1188927', '01038546976', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정민은', '2555161', '01099249038', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이우현', '2989280', '01072772324', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최재우', '1236619', '01078575581', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이연재', '1110297', '01060685951', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '권우우', '1064603', '01072381340', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정현민', '1292802', '01075451583', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권영진', '2375511', '01067965223', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이현은', '2931623', '01066940546', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최영영', '1003989', '01026909001', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '정희진', '1327065', '01090844855', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권연재', '2475733', '01040661629', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정연현', '1131354', '01076799336', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박영재', '1495798', '01071970903', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김경진', '2521258', '01080156156', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '정현경', '2725727', '01087201033', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정연우', '2167739', '01098410370', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이은현', '1482548', '01088425738', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박영민', '1057910', '01015247409', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정우희', '2483129', '01025697754', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '박우진', '1439471', '01025879284', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최은연', '2250767', '01014267624', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최진현', '2644980', '01016567082', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권영우', '1169425', '01009607069', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김현영', '1726325', '01027746164', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '김영민', '1594131', '01070055802', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박현주', '1898840', '01012168880', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김재현', '2095488', '01055228461', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김재재', '2271178', '01034341082', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정진연', '1023306', '01045901487', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '정연희', '2366713', '01082050994', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권현현', '1614052', '01048892309', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정재현', '2622607', '01072815463', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김연경', '2764132', '01027354428', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최현현', '1504689', '01045467927', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '이연희', '2333809', '01075012611', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최현희', '1966510', '01061199588', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정희현', '1513877', '01075178549', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이은민', '1638388', '01073730671', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박희재', '1421121', '01001897624', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '정진진', '1574054', '01034771959', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정현희', '2177903', '01049008030', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최민연', '1920722', '01060017177', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권경경', '2154862', '01056734766', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김현현', '2268486', '01050449197', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '최현주', '2425603', '01066702613', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정우현', '1816138', '01080470594', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정경희', '2477172', '01031127063', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최희영', '2238320', '01066634942', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최진영', '1663847', '01009096340', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '이주은', '1975632', '01085639069', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박진재', '1416047', '01072886497', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이현연', '2259032', '01082201164', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최현희', '1523822', '01094841132', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최진연', '1144648', '01064961577', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '권현영', '1176990', '01026476563', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박현주', '2024579', '01075772322', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권재희', '1581235', '01001292490', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김경희', '2543497', '01057938416', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박주희', '2563748', '01004240367', '2019-12-01');

insert into tblStudent values (seqStudent.nextVal, '박경경', '2108681', '01040198776', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최연우', '2140943', '01065581559', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김연현', '2189253', '01035041248', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이주경', '2333808', '01057413658', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최진재', '2129452', '01091038554', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박경현', '2512588', '01020797200', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김재주', '1151748', '01083405802', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이민영', '2561869', '01021472983', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정경희', '2259017', '01065037537', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이은현', '2092298', '01058786987', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이현주', '1399829', '01075765619', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권영경', '1253236', '01054652559', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박주연', '1947330', '01036453503', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최영영', '2299908', '01018930141', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이경희', '2751912', '01051324787', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김주민', '1154126', '01003287554', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김진연', '2392630', '01006908243', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정연은', '2745832', '01086897228', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권연현', '1678200', '01059396320', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김경재', '2525311', '01013836006', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정경재', '2375853', '01082638046', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최연연', '2026431', '01034071534', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정재희', '1166090', '01062091382', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최재희', '2829093', '01038465446', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최희주', '1026392', '01026937794', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최현주', '2197227', '01007898574', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최현민', '1869422', '01087057520', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이주경', '2640023', '01077968666', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권진연', '2158035', '01087013510', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정희희', '2451240', '01017013959', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박영경', '2770235', '01026728269', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최진현', '2816422', '01091735121', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김경현', '1000933', '01003053905', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정은희', '1494413', '01006732872', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박현경', '1125334', '01086257036', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이우현', '2842462', '01099732040', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최민영', '1948412', '01059987627', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이민현', '2569482', '01010521615', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박재경', '1429674', '01095152496', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권현진', '1716612', '01067807408', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이재은', '1470132', '01040468543', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김은주', '1659114', '01082883054', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정우연', '2778096', '01037287358', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이재현', '2653080', '01052598957', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박진주', '1634321', '01080472476', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정영현', '2065937', '01019000202', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최재희', '1556135', '01076231291', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박연우', '2136979', '01011736978', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정영현', '1607807', '01024144368', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박경재', '1216254', '01021332247', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최주현', '2237211', '01033025455', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이연재', '1710568', '01047490470', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박진경', '2965079', '01058542028', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권민민', '1336137', '01088831774', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최재경', '1529249', '01096756911', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최진경', '2030243', '01041198332', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이희연', '2475044', '01091881875', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김재영', '2209848', '01018903865', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권은경', '2250729', '01097269255', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최재영', '1826418', '01094452950', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정현민', '1464781', '01002123223', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최영현', '1607057', '01038160612', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정은현', '2631241', '01098267070', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권주현', '1015345', '01058701045', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정영주', '1497597', '01090888181', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최현은', '2315814', '01026494521', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최현은', '1275157', '01052532314', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김민우', '1829550', '01019486012', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박영민', '2610383', '01089827911', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이주주', '2169352', '01067414597', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김현경', '2634848', '01045004281', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최연현', '2122410', '01071096148', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박연우', '1793149', '01065033633', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권영재', '2636887', '01064723391', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '최영은', '1066941', '01079661330', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박희재', '1545495', '01057202546', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '정주진', '2723940', '01071285008', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박희우', '2477064', '01032986059', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권희진', '1789188', '01059649081', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권진진', '1586266', '01045228823', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '권우우', '1848580', '01004756379', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '박은연', '1533089', '01095228595', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김경현', '2985291', '01039148762', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '김재희', '2827467', '01094445002', '2019-12-01');
insert into tblStudent values (seqStudent.nextVal, '이진희', '1808702', '01025073668', '2019-12-01');


-- 휴일 데이터

insert into tblHoliday values (seqHoliday.nextVal, '2020-12-25', '크리스마스');
insert into tblHoliday values (seqHoliday.nextVal, '2021-01-01', '신정');
insert into tblHoliday values (seqHoliday.nextVal, '2021-02-11', '설날');
insert into tblHoliday values (seqHoliday.nextVal, '2021-02-12', '설날');
insert into tblHoliday values (seqHoliday.nextVal, '2021-03-01', '삼일절');
insert into tblHoliday values (seqHoliday.nextVal, '2021-05-05', '어린이날');
insert into tblHoliday values (seqHoliday.nextVal, '2021-05-19', '부처님오신날');
insert into tblHoliday values (seqHoliday.nextVal, '2021-09-20', '추석');
insert into tblHoliday values (seqHoliday.nextVal, '2021-09-21', '추석');
insert into tblHoliday values (seqHoliday.nextVal, '2021-09-22', '추석');
