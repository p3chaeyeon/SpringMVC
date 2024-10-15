## SpringProject/src/main/resources/sql/userUpload.sql
## Oracle
create table userUpload (
	seq NUMBER PRIMARY KEY,
	imageName VARCHAR2(50),
	imageContent VARCHAR2(4000),
	imageFileName VARCHAR2(100) NOT NULL,
	imageOriginalFileName2 VARCHAR2(100) NOT NULL
);

## MySQL
create table userUpload (
	seq int(10) PRIMARY KEY AUTO_INCREMENT,
	imageName VARCHAR(50),
	imageContent VARCHAR(4000),
	imageFileName VARCHAR(100),
	imageOriginalFileName VARCHAR(100) NOT NULL
);