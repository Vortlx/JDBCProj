CREATE TABLE student_in_group(
	id_group INT UNSIGNED,
	id_student INT UNSIGNED UNIQUE,
	
	PRIMARY KEY (id_group, id_student),
	FOREIGN KEY (id_group) REFERENCES groups(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_student) REFERENCES students(id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET = utf8 COLLATE utf8_unicode_ci;