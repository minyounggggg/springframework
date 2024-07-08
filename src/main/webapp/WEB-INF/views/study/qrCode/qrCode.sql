/* qrCode */

show tables;

create table qrCode (
	idx int not null auto_increment primary key,	
	mid varchar(20) not null,						/* 아이디 */
	name varchar(20) not null,						/* 이름 */
	email varchar(50) not null,						/* 이메일 */
	movieName varchar(50) not null,					/* 영화이름 */
	movieDate varchar(50) not null,					/* 상영일자 */
	movieTime varchar(50) not null,					/* 상영시간 */
	movieAdult int not null,						/* 성인티켓수 */
	movieChild int not null,						/* 소인티켓수 */
	publishDate varchar(30) not null,				/* QR코드 발행일자 */
	qrCodeName varchar(80) not null,				/* QR 코드 이미지 파일명 */
	foreign key(mid) references member2(mid)
);

desc qrCode;
drop table qrCode;