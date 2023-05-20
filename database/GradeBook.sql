BEGIN TRANSACTION;

DROP TABLE IF EXISTS student, test, student_test;

DROP SEQUENCE IF EXISTS seq_student_id, seq_test_id;

-- Sequence to start student_id values at 1001 instead of 1
CREATE SEQUENCE seq_student_id
  INCREMENT BY 1
  NO MAXVALUE;

CREATE TABLE student (
	student_id int NOT NULL DEFAULT nextval('seq_student_id'),
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	CONSTRAINT PK_student PRIMARY KEY (student_id)
);

-- Sequence to start test_id values at 2001 instead of 1
CREATE SEQUENCE seq_test_id
  INCREMENT BY 1
  NO MAXVALUE;

CREATE TABLE test (
	test_id int NOT NULL DEFAULT nextval('seq_test_id'),
	test_name varchar(50) NOT NULL,
	test_subject varchar(50) NOT NULL,
	number_of_questions int NOT NULL,
	CONSTRAINT PK_test PRIMARY KEY (test_id)
);

CREATE TABLE student_test (
	student_id int NOT NULL,
	test_id int NOT NULL,
	right_answers int NULL,
	date_taken date NULL,
	CONSTRAINT PK_student_test PRIMARY KEY(student_id, test_id),
	CONSTRAINT FK_student_id FOREIGN KEY(student_id) REFERENCES student(student_id), 
	CONSTRAINT FK_test_id FOREIGN KEY(test_id) REFERENCES test(test_id)
);

--Adding Students--

INSERT INTO student (student_id, first_name, last_name)
	VALUES (1001, 'Bobba', 'Fett');

INSERT INTO student (student_id, first_name, last_name)
	VALUES (1002, 'Din', 'Djarin');

INSERT INTO student (student_id, first_name, last_name)
	VALUES (1003, 'Leia', 'Organa');

INSERT INTO student (student_id, first_name, last_name)
	VALUES (1004, 'Ahsoka', 'Tano');

INSERT INTO student (student_id, first_name, last_name)
	VALUES (1005, 'Jyn', 'Erso');


--Adding Tests--

INSERT INTO test (test_id, test_name, test_subject, number_of_questions) 
	VALUES (2001, 'Midichlorians Quiz', 'Biology', 15);

INSERT INTO test (test_id, test_name, test_subject, number_of_questions) 
	VALUES (2002, 'Huttese Grammar Test', 'Vocabulary', 20);

INSERT INTO test (test_id, test_name, test_subject, number_of_questions) 
	VALUES (2003, 'Mandalorian Unit Test', 'History', 40);

INSERT INTO test (test_id, test_name, test_subject, number_of_questions) 
	VALUES (2004, 'Calculating Parsecs Quiz', 'Math', 11);


--Adding Student_Tests--

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1001, 2001, 10, '3/13/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1002, 2001, 12, '3/13/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1003, 2001, 13, '3/13/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1004, 2001, 15, '3/13/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1005, 2001, 14, '3/13/2023');


INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1001, 2002, 9, '3/15/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1002, 2002, 15, '3/15/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1003, 2002, 20, '3/15/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1004, 2002, 18, '3/15/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1005, 2002, 19, '3/15/2023');


INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1001, 2003, 31, '3/16/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1002, 2003, 30, '3/16/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1003, 2003, 37, '3/16/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1004, 2003, 35, '3/16/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1005, 2003, 40, '3/16/2023');


INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1001, 2004, 9, '3/22/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1002, 2004, 4, '3/22/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1003, 2004, 11, '3/22/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1004, 2004, 11, '3/22/2023');

INSERT INTO student_test (student_id, test_id, right_answers, date_taken) 
	VALUES (1005, 2004, 9, '3/22/2023');


COMMIT;