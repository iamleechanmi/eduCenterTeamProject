-- 1단계[과목].sql

--과목.tblsubject(seq, name, period)
select * from tblsubject;

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

