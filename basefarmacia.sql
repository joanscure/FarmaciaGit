-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-09-2018 a las 22:57:31
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.1.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `basefarmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boletacabecera`
--

CREATE TABLE `boletacabecera` (
  `idboletacabecera` int(11) NOT NULL,
  `correlativoboleta` int(4) NOT NULL,
  `numeroboleta` int(8) NOT NULL,
  `fechaemisionboleta` date NOT NULL,
  `idpersonacliente` int(11) NOT NULL,
  `idempleado` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boletadetalle`
--

CREATE TABLE `boletadetalle` (
  `idboletadetalle` int(11) NOT NULL,
  `idboletacabecera` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` double(5,2) NOT NULL,
  `subtotal` double(5,2) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuento`
--

CREATE TABLE `descuento` (
  `iddescuento` int(11) NOT NULL,
  `nombredescuento` varchar(32) NOT NULL,
  `condicion` varchar(32) NOT NULL,
  `porcentaje` double(2,2) NOT NULL,
  `descripciondescuento` varchar(64) DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `idempleado` int(11) NOT NULL,
  `idpersona` int(11) NOT NULL,
  `login` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `fechaalta` date DEFAULT NULL,
  `idtipotrabajador` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `idempresa` int(11) NOT NULL,
  `rucempresa` char(11) NOT NULL,
  `razonsocial` varchar(64) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `direccion` varchar(32) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresacliente`
--

CREATE TABLE `empresacliente` (
  `idempresacliente` int(11) NOT NULL,
  `idempresa` int(11) NOT NULL,
  `fecharegistro` date DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturacabecera`
--

CREATE TABLE `facturacabecera` (
  `idfacturacabecera` int(11) NOT NULL,
  `correlativofactura` int(4) NOT NULL,
  `numerofactura` int(8) NOT NULL,
  `fechaemisionfactura` date NOT NULL,
  `idempresacliente` int(11) NOT NULL,
  `idempleado` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturadetalle`
--

CREATE TABLE `facturadetalle` (
  `idfacturadetalle` int(11) NOT NULL,
  `idfacturacabecera` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` double(5,2) NOT NULL,
  `subtotal` double(5,2) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `idpersona` int(11) NOT NULL,
  `nombre` varchar(32) NOT NULL,
  `appaterno` varchar(32) NOT NULL,
  `apmaterno` varchar(32) NOT NULL,
  `numerodni` char(8) NOT NULL,
  `personaedad` int(11) DEFAULT NULL,
  `direccion` varchar(32) DEFAULT NULL,
  `telefono` varchar(16) DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personacliente`
--

CREATE TABLE `personacliente` (
  `idpersonacliente` int(11) NOT NULL,
  `idpersona` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL,
  `nombreproducto` varchar(32) NOT NULL,
  `descripcionproducto` varchar(128) NOT NULL,
  `dosisproducto` varchar(32) NOT NULL,
  `precioventa` double(5,2) NOT NULL,
  `igv` double(2,2) NOT NULL,
  `preciofinal` double(5,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productodescuento`
--

CREATE TABLE `productodescuento` (
  `idproductodescuent` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `iddescuento` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipotrabajador`
--

CREATE TABLE `tipotrabajador` (
  `idtipotrabajador` int(11) NOT NULL,
  `nombretipotrabajador` varchar(32) DEFAULT NULL,
  `accederventas` tinyint(1) NOT NULL,
  `accederproductos` tinyint(1) NOT NULL,
  `accederclientes` tinyint(1) NOT NULL,
  `accederconsultas` tinyint(1) NOT NULL,
  `accederempleados` tinyint(1) NOT NULL,
  `accedertipousuario` tinyint(1) NOT NULL,
  `accedercambioclave` tinyint(1) NOT NULL,
  `accederanulaciones` tinyint(1) NOT NULL,
  `accedereliminarproducto` tinyint(1) NOT NULL,
  `accedereliminarcliente` tinyint(1) NOT NULL,
  `accedereliminarempleado` tinyint(1) NOT NULL,
  `accedereliminartipoempleado` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `boletacabecera`
--
ALTER TABLE `boletacabecera`
  ADD PRIMARY KEY (`idboletacabecera`),
  ADD KEY `idpersonacliente` (`idpersonacliente`),
  ADD KEY `idempleado` (`idempleado`);

--
-- Indices de la tabla `boletadetalle`
--
ALTER TABLE `boletadetalle`
  ADD PRIMARY KEY (`idboletadetalle`),
  ADD KEY `idboletacabecera` (`idboletacabecera`),
  ADD KEY `idproducto` (`idproducto`);

--
-- Indices de la tabla `descuento`
--
ALTER TABLE `descuento`
  ADD PRIMARY KEY (`iddescuento`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`idempleado`),
  ADD KEY `persona` (`idpersona`),
  ADD KEY `tipotrabajador` (`idtipotrabajador`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`idempresa`);

--
-- Indices de la tabla `empresacliente`
--
ALTER TABLE `empresacliente`
  ADD PRIMARY KEY (`idempresacliente`),
  ADD KEY `EMPRESA` (`idempresa`);

--
-- Indices de la tabla `facturacabecera`
--
ALTER TABLE `facturacabecera`
  ADD PRIMARY KEY (`idfacturacabecera`),
  ADD KEY `idempresacliente` (`idempresacliente`),
  ADD KEY `idempleado` (`idempleado`);

--
-- Indices de la tabla `facturadetalle`
--
ALTER TABLE `facturadetalle`
  ADD PRIMARY KEY (`idfacturadetalle`),
  ADD KEY `idproducto` (`idproducto`),
  ADD KEY `idfacturacabecera` (`idfacturacabecera`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`idpersona`);

--
-- Indices de la tabla `personacliente`
--
ALTER TABLE `personacliente`
  ADD PRIMARY KEY (`idpersonacliente`),
  ADD KEY `idpersona` (`idpersona`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idproducto`);

--
-- Indices de la tabla `productodescuento`
--
ALTER TABLE `productodescuento`
  ADD PRIMARY KEY (`idproductodescuent`),
  ADD KEY `PRODUCTO` (`idproducto`),
  ADD KEY `DESCUENTO` (`iddescuento`);

--
-- Indices de la tabla `tipotrabajador`
--
ALTER TABLE `tipotrabajador`
  ADD PRIMARY KEY (`idtipotrabajador`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `boletacabecera`
--
ALTER TABLE `boletacabecera`
  MODIFY `idboletacabecera` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `boletadetalle`
--
ALTER TABLE `boletadetalle`
  MODIFY `idboletadetalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `descuento`
--
ALTER TABLE `descuento`
  MODIFY `iddescuento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `idempleado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idempresa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empresacliente`
--
ALTER TABLE `empresacliente`
  MODIFY `idempresacliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `facturacabecera`
--
ALTER TABLE `facturacabecera`
  MODIFY `idfacturacabecera` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `facturadetalle`
--
ALTER TABLE `facturadetalle`
  MODIFY `idfacturadetalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `idpersona` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `personacliente`
--
ALTER TABLE `personacliente`
  MODIFY `idpersonacliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idproducto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productodescuento`
--
ALTER TABLE `productodescuento`
  MODIFY `idproductodescuent` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipotrabajador`
--
ALTER TABLE `tipotrabajador`
  MODIFY `idtipotrabajador` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `boletacabecera`
--
ALTER TABLE `boletacabecera`
  ADD CONSTRAINT `empleado` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idempleado`),
  ADD CONSTRAINT `personacliente` FOREIGN KEY (`idpersonacliente`) REFERENCES `personacliente` (`idpersonacliente`);

--
-- Filtros para la tabla `boletadetalle`
--
ALTER TABLE `boletadetalle`
  ADD CONSTRAINT `elrpoductorelacionado` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`),
  ADD CONSTRAINT `idboleta` FOREIGN KEY (`idboletacabecera`) REFERENCES `boletacabecera` (`idboletacabecera`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `persona` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tipotrabajador` FOREIGN KEY (`idtipotrabajador`) REFERENCES `tipotrabajador` (`idtipotrabajador`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `empresacliente`
--
ALTER TABLE `empresacliente`
  ADD CONSTRAINT `EMPRESA` FOREIGN KEY (`idempresa`) REFERENCES `empresa` (`idempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `facturacabecera`
--
ALTER TABLE `facturacabecera`
  ADD CONSTRAINT `empleado-factura` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idempleado`),
  ADD CONSTRAINT `empresa-cliente` FOREIGN KEY (`idempresacliente`) REFERENCES `empresacliente` (`idempresacliente`);

--
-- Filtros para la tabla `facturadetalle`
--
ALTER TABLE `facturadetalle`
  ADD CONSTRAINT `cabecera-factura` FOREIGN KEY (`idfacturacabecera`) REFERENCES `facturacabecera` (`idfacturacabecera`),
  ADD CONSTRAINT `producto-factura` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`);

--
-- Filtros para la tabla `personacliente`
--
ALTER TABLE `personacliente`
  ADD CONSTRAINT `personalcliente` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`);

--
-- Filtros para la tabla `productodescuento`
--
ALTER TABLE `productodescuento`
  ADD CONSTRAINT `DESCUENTO` FOREIGN KEY (`iddescuento`) REFERENCES `descuento` (`iddescuento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `PRODUCTO` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
