-- 1단계[교사].sql


-- 교사.tblteacher(seq, name, ssn, tel)
-- 10명 / 6명 기존 교사 + 4명 예비 교사
select * from tblteacher;

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
