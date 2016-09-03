﻿CREATE TABLE curator(
	id_group INT UNSIGNED UNIQUE,
	id_teacher INT UNSIGNED UNIQUE,
	
	PRIMARY KEY (id_group, id_teacher),
	FOREIGN KEY (id_group) REFERENCES groups (id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_teacher) REFERENCES teachers (id) ON DELETE CASCADE ON UPDATE CASCADE
);