create table contacts (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	gender VARCHAR(10),
	email VARCHAR(250) UNIQUE,
	phone VARCHAR(50) UNIQUE,
	city VARCHAR(50),
	country VARCHAR(50)
)