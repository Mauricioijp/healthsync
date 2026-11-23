DROP SCHEMA IF EXISTS `healthsync`; 
CREATE SCHEMA `healthsync`; 
USE healthsync;

DROP TABLE IF EXISTS `healthsync`.`users`; 
CREATE TABLE `healthsync`.`users` ( 
  `userid` int NOT NULL AUTO_INCREMENT, 
  `user` varchar(50) NOT NULL, 
  `password` varchar(100) NOT NULL, 
  `fullname` varchar(50) NOT NULL, 
  `photo` mediumblob,
  `role` varchar(25),
  `state` INT NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `healthsync`.`users` (`userid`, `user`, `password`, `fullname`,`role`, `state`) VALUES ('1', 'mjuarezp', '123456', 'Mauricio Juarez Proaño', 'ADMIN','1');
INSERT INTO `healthsync`.`users` (`userid`, `user`, `password`, `fullname`,`role`, `state`) VALUES ('2', 'jperezgil', '123456', 'Juan Carlos Perez Gil', 'admin','1');
INSERT INTO `healthsync`.`users` (`userid`, `user`, `password`, `fullname`,`role`, `state`) VALUES ('3', 'mjuarezp', '$2a$10$7s1n9m8u5v4w3x2y1z0a9b8c7d6e5f4g3h2i1j0k9l8m7n6o5p4q3r2s1t0', 'Mauricio Juarez Proaño', 'admin','1');
INSERT INTO `healthsync`.`users` ( `user`, `password`, `fullname`,`role`, `state`) VALUES ('mauriciojp', '123456', 'Mauricio Juarez', 'doctor', '1');
INSERT INTO `healthsync`.`users` ( `user`, `password`, `fullname`,`role`, `state`) VALUES ('patient', '123456', 'Margarita Suarez', 'patient', '1');


DROP TABLE IF EXISTS `healthsync`.`patients`; 
CREATE TABLE `healthsync`.`patients` ( 
  `id` int NOT NULL AUTO_INCREMENT,
  `medicalrecordnumber` varchar(20) NOT NULL UNIQUE,
  `nationalid` varchar(20) NOT NULL UNIQUE,
  `patientidentifier` varchar(20) NOT NULL,
  `familyname` varchar(50) NOT NULL, 
  `givenname` varchar(50) NOT NULL,
  `sex` char(1) NOT NULL,
  `birthdate` date,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `healthsync`.`patients` (`id`, `medicalrecordnumber`, `nationalid`, `patientidentifier`, `familyname`, `givenname`, `sex`, `birthdate`, `phone`) VALUES ('1', '123456^^^Hospital^MR', '07458541', '987654','Perez', 'Javier', 'M', '1960-05-01', '985562214');
INSERT INTO `healthsync`.`patients` (`id`, `medicalrecordnumber`, `nationalid`, `patientidentifier`, `familyname`, `givenname`, `sex`, `birthdate`, `phone`) VALUES ('2', '123457^^^Hospital^MR', '07442563', '741258','Ramirez', 'Gonzalo', 'M', '1953-05-06', '945623517');

select * from patients;

ALTER TABLE `healthsync`.`patients`
DROP COLUMN state;

DROP TABLE IF EXISTS `healthsync`.`appointments`; 
CREATE TABLE `healthsync`.`appointments` ( 
  `idapp` int NOT NULL AUTO_INCREMENT,
  `patientid` INT NOT NULL,
  `medicalrecordnumber` varchar(20) NOT NULL,
  `nationalid` varchar(20) NOT NULL,
  `doctor` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `state` int NOT NULL, 
  PRIMARY KEY (`idapp`),
  CONSTRAINT FK_patient
        FOREIGN KEY (patientid)
        REFERENCES patients(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


USE healthsync;
select * from users;

INSERT INTO `healthsync`.`appointments` (`idapp`, `patientid`, `medicalrecordnumber`, `nationalid`, `doctor`, `date`, `state`) VALUES ('1', '2', '07442563', '123457^^^Hospital^MR', 'Roberto Martinez', '2026-02-15', '1');
select * from appointments;