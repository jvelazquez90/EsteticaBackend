-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema estetica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema estetica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `estetica` DEFAULT CHARACTER SET utf8 ;
USE `estetica` ;

-- -----------------------------------------------------
-- Table `estetica`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`Persona` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Apellido` VARCHAR(45) NULL,
  `DNI` VARCHAR(45) NULL,
  `Sexo` INT(10) NULL,
  PRIMARY KEY (`idPersona`),
  UNIQUE INDEX `DNI_UNIQUE` (`DNI` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`Paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`Paciente` (
  `idPaciente` INT NOT NULL AUTO_INCREMENT,
  `Telefono` VARCHAR(45) NULL,
  `FechaDeAlta` DATE NOT NULL,
  `FechaReal` VARCHAR(45) NULL,
  `TotalGastos` DOUBLE NOT NULL,
  `Saldo` DOUBLE NULL,
  `Activo` TINYINT NULL,
  `Persona_id` INT NOT NULL,
  PRIMARY KEY (`idPaciente`),
  INDEX `fk_Paciente_Persona1_idx` (`Persona_id` ASC),
  CONSTRAINT `fk_Paciente_Persona1`
    FOREIGN KEY (`Persona_id`)
    REFERENCES `estetica`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`Calendario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`Calendario` (
  `idCalendario` INT NOT NULL AUTO_INCREMENT,
  `Dia` INT NOT NULL,
  `Mes` INT NOT NULL,
  `Anio` INT NOT NULL,
  `Hora` INT NOT NULL,
  PRIMARY KEY (`idCalendario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`Turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`Turno` (
  `idTurno` INT NOT NULL AUTO_INCREMENT,
  `Activo` TINYINT NULL,
  `Fecha` DATETIME NULL,
  `Hora` DATETIME NULL,
  `Gasto` INT NULL,
  `MetodoDePago` VARCHAR(45) NULL,
  `EstaPago` TINYINT NULL,
  `Calendario_id` INT NOT NULL,
  `Paciente_id` INT NOT NULL,
  PRIMARY KEY (`idTurno`),
  INDEX `fk_Turno_Calendario1_idx` (`Calendario_id` ASC),
  INDEX `fk_Turno_Paciente1_idx` (`Paciente_id` ASC),
  CONSTRAINT `fk_Turno_Calendario`
    FOREIGN KEY (`Calendario_id`)
    REFERENCES `estetica`.`Calendario` (`idCalendario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Turno_Paciente1`
    FOREIGN KEY (`Paciente_id`)
    REFERENCES `estetica`.`Paciente` (`idPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`FichaPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`FichaPaciente` (
  `idFichaPaciente` INT NOT NULL AUTO_INCREMENT,
  `MedidasAntesYDespues` VARCHAR(80) NULL,
  `Descripcion` VARCHAR(80) NULL,
  `Patologia` VARCHAR(255) NULL,
  `Medicacion` VARCHAR(80) NULL,
  `TratamientoEnOtroLugar` VARCHAR(255) NULL,
  `EsParaEstetica` TINYINT NULL,
  `Observaciones` VARCHAR(255) NULL,
  `Comentario` VARCHAR(255) NULL,
  `FichaPacientecol` VARCHAR(45) NULL,
  `Saldo` INT NULL,
  `FormaDePago` VARCHAR(45) NULL,
  `Paciente_id` INT NOT NULL,
  PRIMARY KEY (`idFichaPaciente`),
  INDEX `fk_FichaPaciente_Paciente1_idx` (`Paciente_id` ASC),
  CONSTRAINT `fk_FichaPaciente_Paciente1`
    FOREIGN KEY (`Paciente_id`)
    REFERENCES `estetica`.`Paciente` (`idPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`FichaPaciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`FichaPaciente` (
  `idFichaPaciente` INT NOT NULL AUTO_INCREMENT,
  `MedidasAntesYDespues` VARCHAR(80) NULL,
  `Descripcion` VARCHAR(80) NULL,
  `Patologia` VARCHAR(255) NULL,
  `Medicacion` VARCHAR(80) NULL,
  `TratamientoEnOtroLugar` VARCHAR(255) NULL,
  `EsParaEstetica` TINYINT NULL,
  `Observaciones` VARCHAR(255) NULL,
  `Comentario` VARCHAR(255) NULL,
  `FichaPacientecol` VARCHAR(45) NULL,
  `Saldo` INT NULL,
  `FormaDePago` VARCHAR(45) NULL,
  `Paciente_id` INT NOT NULL,
  PRIMARY KEY (`idFichaPaciente`),
  INDEX `fk_FichaPaciente_Paciente1_idx` (`Paciente_id` ASC),
  CONSTRAINT `fk_FichaPaciente_Paciente1`
    FOREIGN KEY (`Paciente_id`)
    REFERENCES `estetica`.`Paciente` (`idPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `User` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `UltimoAcceso` DATETIME NULL DEFAULT NULL,
  `Activo` TINYINT NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`Tratamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`Tratamiento` (
  `idTratamiento` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `CantidadSesiones` INT NULL,
  `Tiempo` VARCHAR(45) NULL,
  `TiempoSuperpuesto` VARCHAR(45) NULL,
  `Precio` INT NULL,
  PRIMARY KEY (`idTratamiento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`TurnoTratamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`TurnoTratamiento` (
  `TurnoId` INT NOT NULL AUTO_INCREMENT,
  `TratamientoIdTratamiento` INT NOT NULL,
  PRIMARY KEY (`TurnoId`, `TratamientoIdTratamiento`),
  INDEX `fk_Turno_has_Tratamiento_Tratamiento1_idx` (`TratamientoIdTratamiento` ASC),
  INDEX `fk_Turno_has_Tratamiento_Turno1_idx` (`TurnoId` ASC),
  CONSTRAINT `fk_Turno_has_Tratamiento_Turno1`
    FOREIGN KEY (`TurnoId`)
    REFERENCES `estetica`.`Turno` (`idTurno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Turno_has_Tratamiento_Tratamiento1`
    FOREIGN KEY (`TratamientoIdTratamiento`)
    REFERENCES `estetica`.`Tratamiento` (`idTratamiento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`FichaPacienteTieneTratamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`FichaPacienteTieneTratamiento` (
  `FichaPacienteIdFichaPaciente` INT NOT NULL AUTO_INCREMENT,
  `TratamientoIdTratamiento` INT NOT NULL,
  `FechaTratamiento` DATETIME NULL,
  `Peso` DOUBLE NULL,
  `Medida` DOUBLE NULL,
  PRIMARY KEY (`FichaPacienteIdFichaPaciente`, `TratamientoIdTratamiento`),
  INDEX `fk_FichaPaciente_has_Tratamiento_Tratamiento1_idx` (`TratamientoIdTratamiento` ASC),
  INDEX `fk_FichaPaciente_has_Tratamiento_FichaPaciente1_idx` (`FichaPacienteIdFichaPaciente` ASC),
  CONSTRAINT `fk_FichaPaciente_has_Tratamiento_FichaPaciente1`
    FOREIGN KEY (`FichaPacienteIdFichaPaciente`)
    REFERENCES `estetica`.`FichaPaciente` (`idFichaPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FichaPaciente_has_Tratamiento_Tratamiento1`
    FOREIGN KEY (`TratamientoIdTratamiento`)
    REFERENCES `estetica`.`Tratamiento` (`idTratamiento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estetica`.`Log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estetica`.`Log` (
  `idLog` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATETIME NULL,
  `Metodo` VARCHAR(80) NULL,
  PRIMARY KEY (`idLog`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-----------------------------------------------------------------------------------------------------------------------
INSERT INTO usuario (User, Password, Email, Activo) VALUES ('sf', 'sf24', 'silviafernandez_forty@hotmail.com', 1);
INSERT INTO usuario (User, Password, Email, Activo) VALUES ('invitado', 'invitado', 'silviafernandez_forty@hotmail.com', 1);

INSERT INTO persona(Nombre, Apellido, DNI, Sexo) VALUES ("Maria Laura","",35184756,0);
INSERT INTO persona(Nombre, Apellido, DNI, Sexo) VALUES ("Juan Carlos","",37456875,1);
INSERT INTO persona(Nombre, Apellido, DNI, Sexo) VALUES ("Nadia Soledad","",40567856,0);
INSERT INTO persona(Nombre, Apellido, DNI, Sexo) VALUES ("Nicolas Alejandro","",21456321,1);
INSERT INTO persona(Nombre, Apellido, DNI, Sexo) VALUES ("Matias Emanuel","",25432854,1);
INSERT INTO persona(Nombre, Apellido, DNI, Sexo) VALUES ("Daiana Belen","",45002532,0);
INSERT INTO persona(Nombre, Apellido, DNI, Sexo) VALUES ("Juan Carlos Perez","",45002533,1);

INSERT INTO paciente(Telefono,Activo,Persona_id) VALUES ("1156475010",1,1);
INSERT INTO paciente(Telefono,Activo,Persona_id) VALUES ("1132786523",1,2);
INSERT INTO paciente(Telefono,Activo,Persona_id) VALUES ("1156478532",1,3);
INSERT INTO paciente(Telefono,Activo,Persona_id) VALUES ("1145652574",1,4);
INSERT INTO paciente(Telefono,Activo,Persona_id) VALUES ("1145256895",1,5);
INSERT INTO paciente(Telefono,Activo,Persona_id) VALUES ("1145756214",1,6);
INSERT INTO paciente(Telefono,Activo,Persona_id) VALUES ("1145756214",1,7);


INSERT INTO tratamiento (Nombre, CantidadSesiones, Tiempo, TiempoSuperpuesto, Precio) VALUES ("Peelin",4,"30m","30m",12000);
INSERT INTO tratamiento (Nombre, CantidadSesiones, Tiempo, TiempoSuperpuesto, Precio) VALUES ("Mascara facial",1,"30m","14m",7500);
INSERT INTO tratamiento (Nombre, CantidadSesiones, Tiempo, TiempoSuperpuesto, Precio) VALUES ("Puntas de diamante",6,"45m","10m",25000);
INSERT INTO tratamiento (Nombre, CantidadSesiones, Tiempo, TiempoSuperpuesto, Precio) VALUES ("Peelin",4,"30m","30m",12000);
INSERT INTO tratamiento (Nombre, CantidadSesiones, Tiempo, TiempoSuperpuesto, Precio) VALUES ("Mascara facial",1,"30m","14m",7500);
INSERT INTO tratamiento (Nombre, CantidadSesiones, Tiempo, TiempoSuperpuesto, Precio) VALUES ("Puntas de diamante",6,"45m","10m",25000);