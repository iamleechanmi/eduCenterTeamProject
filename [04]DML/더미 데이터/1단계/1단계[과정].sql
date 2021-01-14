-- 1단계[과정].sql

--과정.tblcourse(seq, name, goal, period)
select * from tblcourse;

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
