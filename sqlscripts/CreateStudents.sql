CREATE TABLE students(
	id INT UNSIGNED AUTO_INCREMENT,
	name VARCHAR(255),
	family_name VARCHAR(255),
	
	PRIMARY KEY (id),
	CONSTRAINT unique_student UNIQUE(name, family_name)
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;