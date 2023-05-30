BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('eric','$2a$10$sIwITAUfESJNpC2ssjCpq.Ks42hje6bHCYFJKjYmc5ursBqers9kq','ROLE_ADMIN');

INSERT INTO student (student_id, first_name, last_name) VALUES (1001, 'Bobba', 'Fett');
INSERT INTO student (student_id, first_name, last_name) VALUES (1002, 'Din', 'Djarin');
INSERT INTO student (student_id, first_name, last_name) VALUES (1003, 'Leia', 'Organa');
INSERT INTO student (student_id, first_name, last_name) VALUES (1004, 'Ahsoka', 'Tano');
INSERT INTO student (student_id, first_name, last_name) VALUES (1005, 'Jyn', 'Erso');

INSERT INTO test (test_id, test_name, test_subject, number_of_questions) VALUES (2001, 'Midichlorians Quiz', 'Biology', 15);
INSERT INTO test (test_id, test_name, test_subject, number_of_questions) VALUES (2002, 'Huttese Grammar Test', 'Vocabulary', 20);
INSERT INTO test (test_id, test_name, test_subject, number_of_questions) VALUES (2003, 'Mandalorian Unit Test', 'History', 40);
INSERT INTO test (test_id, test_name, test_subject, number_of_questions) VALUES (2004, 'Calculating Parsecs Quiz', 'Math', 11);

COMMIT TRANSACTION;
