CREATE TABLE curator(
	id_group INT UNSIGNED,
	id_teacher INT UNSIGNED,
	
	PRIMARY KEY (id_group, id_teacher),
	FOREIGN KEY (id_group) REFERENCES groups (id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_teacher) REFERENCES teachers (id) ON DELETE CASCADE ON UPDATE CASCADE
) CHARACTER SET utf8 = COLLATE utf8_unicode_ci;