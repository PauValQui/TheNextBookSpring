DROP TABLE IF EXISTS `Categoria`;
CREATE TABLE `Categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tipo_UNIQUE` (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `Autor`;
CREATE TABLE `Autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `foto` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `Libro`;
CREATE TABLE `Libro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `sinopsis` text,
  `precio` decimal(8,2),
  `foto` varchar(100) NOT NULL,
  `idCategoria` int not null,
  `idAutor` int not null,
  PRIMARY KEY (`id`),
  KEY `fk_libro_categoria1_idx` (`idCategoria`),
  KEY `fk_libro_autor1_idx` (`idAutor`),
  CONSTRAINT `fk_libro_categoria1` FOREIGN KEY (`idCategoria`) REFERENCES `Categoria` (`id`),
  CONSTRAINT `fk_libro_autor1` FOREIGN KEY (`idAutor`) REFERENCES `Autor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `Usuario`;
CREATE TABLE `Usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `idRole` int not null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
  CONSTRAINT fk_usuario_role FOREIGN KEY (idRole)
      REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `Role`;
CREATE TABLE `Role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) not null,
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `Valoracion`;
CREATE TABLE `Valoracion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `puntuacion` int,
  `comentario` text,
  `idLibro` int not null,
  `idUsuario` int not null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Libro_Usuario_UNIQUE` (`idLibro`, `idUsuario`),
  KEY `fk_valoracion_libro1_idx` (`idLibro`),
  KEY `fk_valoracion_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_valoracion_libro1` FOREIGN KEY (`idLibro`) REFERENCES `Libro` (`id`),
  CONSTRAINT `fk_valoracion_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `Factura`;
CREATE TABLE `Factura` (
  `id` int NOT NULL AUTO_INCREMENT,
  `precioTotal` decimal(8,2) NOT NULL,
  `fechaCompra` Date NOT NULL,
  `idUsuario` int not null,
  PRIMARY KEY (`id`),
  KEY `fk_factura_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_factura_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `FacturaLibro`;
CREATE TABLE `FacturaLibro` (
  `idFactura` int not null,
  `idLibro` int not null,
  UNIQUE KEY `Factura_Libro_UNIQUE` (`idFactura`, `idLibro`),
  KEY `fk_factura1_idx` (`idFactura`),
  KEY `fk_libro1_idx` (`idLibro`),
  CONSTRAINT `fk_factura1` FOREIGN KEY (`idFactura`) REFERENCES `Factura` (`id`),
  CONSTRAINT `fk_libro1` FOREIGN KEY (`idLibro`) REFERENCES `Libro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE usuario
   ADD CONSTRAINT fk_usuario_role FOREIGN KEY (idRole)
      REFERENCES role (id)
;