CREATE TABLE Persona (
	personaID INT AUTO_INCREMENT,
	DNI VARCHAR(9),
	dataNaixement DATETIME,
	nom VARCHAR(16),
	cognom1 VARCHAR(16),
	cognom2 VARCHAR(16),
	PRIMARY KEY (personaID)
);

CREATE TABLE Edifici (
	edificiID INT AUTO_INCREMENT,
	nom VARCHAR(32),
	PRIMARY KEY(edificiID)
);

CREATE TABLE Departament (
	departamentID INT AUTO_INCREMENT,
	nom VARCHAR(32),
	edificiID INT,
	PRIMARY KEY(departamentID),
	FOREIGN KEY(edificiID) REFERENCES Edifici(edificiID)
);

CREATE TABLE Doctor (
	doctorID INT NOT NULL,
	posicio VARCHAR(32),
	dataInici DATETIME,
	departamentID INT,
	PRIMARY KEY(doctorID),
	FOREIGN KEY(doctorID) REFERENCES Persona(personaID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(departamentID) REFERENCES Departament(departamentID)
);

CREATE TABLE Pacient (
	pacientID INT NOT NULL,
	dataAlta DATETIME,
	dataBaixa DATETIME,
	habitacioID INT,
	PRIMARY KEY(pacientID),
	FOREIGN KEY(pacientID) REFERENCES Persona(personaID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Visita (
	visitaID INT AUTO_INCREMENT,
	dataVisita DATETIME,
	motiu VARCHAR(128),
	doctorID INT,
	pacientID INT,
	PRIMARY KEY(visitaID),
	FOREIGN KEY(pacientID) REFERENCES Pacient(pacientID),
	FOREIGN KEY(doctorID) REFERENCES Doctor(doctorID)
);

CREATE TABLE Habitacio (
	habitacioID INT AUTO_INCREMENT,
	lliure BOOLEAN,
	pacientID INT,
	edificiID INT,
	PRIMARY KEY(habitacioID),
	FOREIGN KEY(pacientID) REFERENCES Pacient(pacientID),
	FOREIGN KEY(edificiID) REFERENCES Edifici(edificiID)
);