INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (1,'16422555E','1996-05-20','Tom','Hank','Hill');
INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (2,'84765555Q','1994-01-05','Sam','Mann','Stone');
INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (3,'76422555C','2001-11-16','Mirco','Pretzel','Burg');
INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (4,'47562555E','1987-08-17','John','Doe','Doe');
INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (5,'06429685B','1990-04-20','Ivan','Virtov','Svan');
INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (6,'42277665A','1980-03-10','Abraham','Shekelstein','Gold');
INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (7,'87422555J','1993-02-02','Sarah','Halen','Royson');
INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (8,'47622555T','1974-06-06','Marisa','Weinen','Thrill');
INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (9,'99422555O','1994-02-28','Rachel','Duran','Tres');
INSERT INTO Persona(personaID, DNI, dataNaixement, nom, cognom1, cognom2) VALUES (10,'24542255E','1953-02-11','Mary','Jane','Johnson');

INSERT INTO Edifici(edificiID, nom) VALUES (1, "Edifici Principal");
INSERT INTO Edifici(edificiID, nom) VALUES (2, "Ala Pacients intensius");
INSERT INTO Edifici(edificiID, nom) VALUES (3, "Annex Psiquiatria");

INSERT INTO Departament(departamentID, nom, edificiID) VALUES (1,'Pediatria',1);
INSERT INTO Departament(departamentID, nom, edificiID) VALUES (2,'Emergencies',1);
INSERT INTO Departament(departamentID, nom, edificiID) VALUES (3,'Tractament intesiu',2);
INSERT INTO Departament(departamentID, nom, edificiID) VALUES (4,'Psiquiatria',3);

INSERT INTO Doctor(doctorID, posicio, dataInici, departamentID) VALUES (2,'Cirurgia','2007-05-10',3);
INSERT INTO Doctor(doctorID, posicio, dataInici, departamentID) VALUES (4,'Doctor de guardia','2005-10-05',2);
INSERT INTO Doctor(doctorID, posicio, dataInici, departamentID) VALUES (6,'Director','2000-02-01',1);
INSERT INTO Doctor(doctorID, posicio, dataInici, departamentID) VALUES (8,'Psiquiatra','2010-12-12',4);

INSERT INTO Pacient(pacientID, dataAlta, dataBaixa) VALUES (1,'2012-05-20',NULL);
INSERT INTO Pacient(pacientID, dataAlta, dataBaixa) VALUES (3,'2011-01-04',NULL);
INSERT INTO Pacient(pacientID, dataAlta, dataBaixa) VALUES (5,'2016-03-19',NULL);
INSERT INTO Pacient(pacientID, dataAlta, dataBaixa) VALUES (7,'2010-11-05','2011-05-02');
INSERT INTO Pacient(pacientID, dataAlta, dataBaixa) VALUES (9,NULL,NULL);
INSERT INTO Pacient(pacientID, dataAlta, dataBaixa) VALUES (10,NULL,NULL);

INSERT INTO Habitacio(habitacioID, lliure, pacientID, edificiID) VALUES (1,FALSE,1,1);
INSERT INTO Habitacio(habitacioID, lliure, pacientID, edificiID) VALUES (2,FALSE,3,2);
INSERT INTO Habitacio(habitacioID, lliure, pacientID, edificiID) VALUES (3,FALSE,5,3);
INSERT INTO Habitacio(habitacioID, lliure, pacientID, edificiID) VALUES (4,TRUE,NULL,1);
INSERT INTO Habitacio(habitacioID, lliure, pacientID, edificiID) VALUES (5,TRUE,NULL,3);
INSERT INTO Habitacio(habitacioID, lliure, pacientID, edificiID) VALUES (6,TRUE,NULL,3);
INSERT INTO Habitacio(habitacioID, lliure, pacientID, edificiID) VALUES (7,TRUE,NULL,2);

INSERT INTO Visita(visitaID, dataVisita, motiu, doctorID, pacientID) VALUES (1,'2018-01-05',"Visita rutinaria",2,5);
INSERT INTO Visita(visitaID, dataVisita, motiu, doctorID, pacientID) VALUES (2,'2018-01-15',"Seguiment cremades",6,1);