-- 1단계[교재].sql

-- 교재.tbltextbook(seq, name, publisher)
select * from tbltextbook;

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

