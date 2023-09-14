-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-09-2023 a las 06:05:16
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `vollmed_api`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flyway_schema_history`
--

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `flyway_schema_history`
--

INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, '1', 'create-table-medicos', 'SQL', 'V1__create-table-medicos.sql', -2064417698, 'root', '2023-09-14 01:26:52', 20, 1),
(2, '2', 'alter-table-medicos-add-telefono', 'SQL', 'V2__alter-table-medicos-add-telefono.sql', 2096914250, 'root', '2023-09-14 01:26:52', 9, 1),
(3, '3', 'alter-table-medicos-add-activo', 'SQL', 'V3__alter-table-medicos-add-activo.sql', -1881248741, 'root', '2023-09-14 01:26:52', 10, 1),
(4, '4', 'create-table-usuarios', 'SQL', 'V4__create-table-usuarios.sql', -1008767348, 'root', '2023-09-14 01:26:52', 7, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicos`
--

CREATE TABLE `medicos` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `rfc` varchar(10) NOT NULL,
  `especialidad` varchar(100) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `colonia` varchar(100) NOT NULL,
  `sudnumero` varchar(100) DEFAULT NULL,
  `numero` varchar(20) DEFAULT NULL,
  `ciudad` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `activo` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicos`
--

INSERT INTO `medicos` (`id`, `nombre`, `email`, `rfc`, `especialidad`, `calle`, `colonia`, `sudnumero`, `numero`, `ciudad`, `telefono`, `activo`) VALUES
(1, 'Ana Gonzales Torres', 'ana_gonzales@voll.com', 'GOTA900820', 'PEDIATRIA', 'calle 13', 'colonia 4', 'a', '345', 'ciudad X', '6533451234', 1),
(2, 'Uriel Flores Ruiz', 'uriel.flores@voll.com', 'FORU900820', 'CARDIOLOGIA', 'calle 13', 'colonia 4', 'a', '345', 'ciudad X', '6531100696', 1),
(3, 'David Mendez Ordega', 'david_mendez@voll.com', 'MEOD801209', 'ORTOPEDIA', 'calle 23', 'colonia 4', 'a', '678', 'ciudad X', '6531100696', 1),
(4, 'Santos Flores Saucedo', 'santos.flores@voll.com', 'FOSS711228', 'ORTOPEDIA', 'calle 45', 'colonia 7', 'a', '888', 'ciudad X', '6531474750', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `login` varchar(100) NOT NULL,
  `clave` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `login`, `clave`) VALUES
(1, 'UrielAdmin', '$2a$12$UNtt9SV957rPqD.bVufyVOkKwl8GIubNlBItWxx7mf5QPSecdxua2');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `flyway_schema_history`
--
ALTER TABLE `flyway_schema_history`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `flyway_schema_history_s_idx` (`success`);

--
-- Indices de la tabla `medicos`
--
ALTER TABLE `medicos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `rfc` (`rfc`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medicos`
--
ALTER TABLE `medicos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
