-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-10-2018 a las 05:16:46
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.7

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
  `correlativoboleta` varchar(4) NOT NULL,
  `numeroboleta` varchar(8) NOT NULL,
  `fechaemisionboleta` date NOT NULL,
  `idpersonacliente` int(11) NOT NULL,
  `idempleado` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `boletacabecera`
--

INSERT INTO `boletacabecera` (`idboletacabecera`, `correlativoboleta`, `numeroboleta`, `fechaemisionboleta`, `idpersonacliente`, `idempleado`, `status`) VALUES
(1, 'B001', '00000001', '2018-09-28', 1, 1, 1),
(2, 'B001', '00000002', '2018-09-28', 1, 1, 1),
(3, 'B001', '00000003', '2018-09-28', 1, 1, 1),
(4, 'B001', '00000004', '2018-09-28', 1, 1, 1),
(5, 'B001', '00000005', '2018-09-28', 1, 1, 1),
(6, 'B001', '00000006', '2018-09-28', 1, 3, 1);

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

--
-- Volcado de datos para la tabla `boletadetalle`
--

INSERT INTO `boletadetalle` (`idboletadetalle`, `idboletacabecera`, `idproducto`, `cantidad`, `subtotal`, `status`) VALUES
(1, 1, 1, 6.00, 8.34, 1),
(2, 1, 2, 8.00, 18.88, 1),
(3, 2, 1, 12.00, 16.68, 1),
(4, 2, 2, 5.00, 11.80, 1),
(5, 3, 1, 4.00, 5.56, 1),
(6, 3, 2, 5.00, 11.80, 1),
(7, 4, 1, 5.00, 6.95, 1),
(8, 5, 1, 3.00, 4.17, 1),
(9, 6, 1, 15.00, 20.85, 1);

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

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`idempleado`, `idpersona`, `login`, `password`, `fechaalta`, `idtipotrabajador`, `status`) VALUES
(1, 1, 'admin', '81dc9bdb52d04dc20036dbd8313ed055', '2018-09-27', 1, 1),
(3, 5, 'vender', '1a19b39699d9a03071c1478b3f425ea0', '2018-09-28', 2, 1),
(4, 6, 'practica', 'afc0a762ff45348639901afc1ac3701c', '2018-09-28', 7, 1),
(5, 7, 'PROBANDO', '81dc9bdb52d04dc20036dbd8313ed055', '2018-10-01', 2, 1);

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

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`idempresa`, `rucempresa`, `razonsocial`, `telefono`, `direccion`, `status`) VALUES
(9, '12332323212', 'EMPRESASS', '121231231', '121231231', 1),
(10, '12312835467', 'EJEMPLO', '123128381', 'ASJDAJSDJAJSD', 0),
(11, '23498239848', 'EMPRESASA', '231912932', 'SJAJSDJASDASDAS', 1);

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

--
-- Volcado de datos para la tabla `empresacliente`
--

INSERT INTO `empresacliente` (`idempresacliente`, `idempresa`, `fecharegistro`, `status`) VALUES
(1, 9, '2018-09-28', 1),
(2, 10, '2018-09-28', 1),
(3, 11, '2018-09-28', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturacabecera`
--

CREATE TABLE `facturacabecera` (
  `idfacturacabecera` int(11) NOT NULL,
  `correlativofactura` varchar(4) NOT NULL,
  `numerofactura` varchar(8) NOT NULL,
  `fechaemisionfactura` date NOT NULL,
  `idempresacliente` int(11) NOT NULL,
  `idempleado` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `facturacabecera`
--

INSERT INTO `facturacabecera` (`idfacturacabecera`, `correlativofactura`, `numerofactura`, `fechaemisionfactura`, `idempresacliente`, `idempleado`, `status`) VALUES
(5, 'F001', '00000001', '2018-09-28', 1, 1, 1);

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

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`idpersona`, `nombre`, `appaterno`, `apmaterno`, `numerodni`, `personaedad`, `direccion`, `telefono`, `status`) VALUES
(1, 'JOAN', 'LEYTON', 'CARRILLO', '76354201', 19, 'PIURA', '943542521', 1),
(2, 'JUNIOR', 'HOLGUIN', 'RUIZ', '12312131', 21, 'PIURA', '458948484', 0),
(3, 'CLIENTE', 'GENERICO', 'GENERICO', '00000000', 0, '', '', 1),
(5, 'POL', 'LE', 'AC', '12313123', 12, 'TALARA', '1231321', 1),
(6, 'PRAC', 'APE', 'APE', '12812831', 18, 'UNP', '182182812', 1),
(7, 'PRUEBA', 'APEPRIE', 'APPRUEBB', '12132313', 14, 'SDASDASDASD', '123213123', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personacliente`
--

CREATE TABLE `personacliente` (
  `idpersonacliente` int(11) NOT NULL,
  `idpersona` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `personacliente`
--

INSERT INTO `personacliente` (`idpersonacliente`, `idpersona`, `status`) VALUES
(1, 2, 0),
(2, 3, 1);

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
  `igv` double(5,2) NOT NULL,
  `preciofinal` double(5,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idproducto`, `nombreproducto`, `descripcionproducto`, `dosisproducto`, `precioventa`, `igv`, `preciofinal`, `stock`, `status`) VALUES
(1, 'PRUEBA', 'EJEMPLO', '500 MG', 1.39, 18.00, 1.39, 100, 1),
(2, 'IBUPROFENO', 'CALMAR DOLOR', '200 MG', 2.36, 18.00, 2.36, 100, 1),
(3, 'NUEVO', 'PRODUCTO', '100MG', 4.72, 18.00, 4.72, 100, 0),
(4, 'PRUEBA', 'DESCR', '200 MG', 2.36, 18.00, 2.36, 150, 0);

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
-- Volcado de datos para la tabla `tipotrabajador`
--

INSERT INTO `tipotrabajador` (`idtipotrabajador`, `nombretipotrabajador`, `accederventas`, `accederproductos`, `accederclientes`, `accederconsultas`, `accederempleados`, `accedertipousuario`, `accedercambioclave`, `accederanulaciones`, `accedereliminarproducto`, `accedereliminarcliente`, `accedereliminarempleado`, `accedereliminartipoempleado`, `status`) VALUES
(1, 'ADMINISTRADOR', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(2, 'VENDEDOR', 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
(5, 'REGISTRADOR', 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
(6, 'ELIMINACION', 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1),
(7, 'PRACTICANTE', 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);

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
  MODIFY `idboletacabecera` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `boletadetalle`
--
ALTER TABLE `boletadetalle`
  MODIFY `idboletadetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `descuento`
--
ALTER TABLE `descuento`
  MODIFY `iddescuento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `idempleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idempresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `empresacliente`
--
ALTER TABLE `empresacliente`
  MODIFY `idempresacliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `facturacabecera`
--
ALTER TABLE `facturacabecera`
  MODIFY `idfacturacabecera` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `facturadetalle`
--
ALTER TABLE `facturadetalle`
  MODIFY `idfacturadetalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `idpersona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `personacliente`
--
ALTER TABLE `personacliente`
  MODIFY `idpersonacliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idproducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `productodescuento`
--
ALTER TABLE `productodescuento`
  MODIFY `idproductodescuent` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipotrabajador`
--
ALTER TABLE `tipotrabajador`
  MODIFY `idtipotrabajador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
