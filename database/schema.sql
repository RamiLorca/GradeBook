BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, student, test;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE student (
	student_id SERIAL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	CONSTRAINT PK_student PRIMARY KEY (student_id)
);

CREATE TABLE test (
	test_id SERIAL,
	test_name VARCHAR(50) NOT NULL,
	test_subject VARCHAR(50) NOT NULL,
	number_of_questions int NOT NULL,
	CONSTRAINT PK_test PRIMARY KEY (test_id)
);

COMMIT TRANSACTION;
