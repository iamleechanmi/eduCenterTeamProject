-- 1단계[관리자].sql


-- 관리자.tblmanager(seq, ssn, name)
-- 3명
select * from tblmanager;

insert into tblmanager(seq, ssn, name) values (seqmanager.nextVal, '1234567', '홍길동');
insert into tblmanager(seq, ssn, name) values (seqmanager.nextVal, '2123456', '이민아');
insert into tblmanager(seq, ssn, name) values (seqmanager.nextVal, '2452101', '박민정');

