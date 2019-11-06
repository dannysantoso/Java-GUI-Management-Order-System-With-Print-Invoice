-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 06 Nov 2019 pada 09.44
-- Versi server: 10.1.34-MariaDB
-- Versi PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `internal_management_order`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `adminID` char(5) NOT NULL,
  `adminEmail` text NOT NULL,
  `adminPassword` text NOT NULL,
  `adminName` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`adminID`, `adminEmail`, `adminPassword`, `adminName`) VALUES
('AD001', 'danny.santoso@yahoo.co.id', 'admin', 'Danny Santoso');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detailtransaction`
--

CREATE TABLE `detailtransaction` (
  `TransactionID` char(5) NOT NULL,
  `ProductID` char(5) NOT NULL,
  `Qty` int(11) NOT NULL,
  `status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detailtransaction`
--

INSERT INTO `detailtransaction` (`TransactionID`, `ProductID`, `Qty`, `status`) VALUES
('TR001', 'PR001', 5, 'ordered'),
('TR001', 'PR005', 15, 'ordered'),
('TR001', 'PR003', 2, 'ordered'),
('TR002', 'PR004', 20, 'ordered'),
('TR002', 'PR002', 15, 'ordered'),
('TR002', 'PR002', 15, 'ordered');

-- --------------------------------------------------------

--
-- Struktur dari tabel `headertransaction`
--

CREATE TABLE `headertransaction` (
  `TransactionID` char(5) NOT NULL,
  `UserID` char(5) DEFAULT NULL,
  `TransactionDate` date NOT NULL,
  `eventDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `headertransaction`
--

INSERT INTO `headertransaction` (`TransactionID`, `UserID`, `TransactionDate`, `eventDate`) VALUES
('TR001', 'CU001', '2019-11-06', '2020-01-12'),
('TR002', 'CU002', '2019-11-06', '2019-12-07');

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE `product` (
  `ProductID` char(5) NOT NULL,
  `ProductName` text NOT NULL,
  `ProductPrice` int(11) NOT NULL,
  `ProductImage` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`ProductID`, `ProductName`, `ProductPrice`, `ProductImage`) VALUES
('PR001', 'Cinnamon Roll', 20000, '/img/cinnamon.png'),
('PR002', 'Croissant', 20000, '/img/croissant.png'),
('PR003', 'Baguette', 25000, '/img/baguette.png'),
('PR004', 'Red Bean Bread', 20000, '/img/redbean.png'),
('PR005', 'Mellon Bread', 18000, '/img/mellon.png');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `UserID` char(5) NOT NULL,
  `UserEmail` text NOT NULL,
  `UserFname` text NOT NULL,
  `UserLname` text NOT NULL,
  `UserPhone` text NOT NULL,
  `UserAddress` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`UserID`, `UserEmail`, `UserFname`, `UserLname`, `UserPhone`, `UserAddress`) VALUES
('CU000', 'default', 'default', 'default', 'default', 'default'),
('CU001', 'billgates@microsoft.com', 'bill', 'gates', '622125518100', 'Sudirman Kav. 52-53. Jakarta 12190. Indonesia'),
('CU002', 'timcook@apple.com', 'tim', 'cook', '628001027753', 'World Trade Center II, Jl. Jend. Sudirman');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminID`);

--
-- Indeks untuk tabel `detailtransaction`
--
ALTER TABLE `detailtransaction`
  ADD KEY `ProductID` (`ProductID`),
  ADD KEY `TransactionID` (`TransactionID`,`ProductID`);

--
-- Indeks untuk tabel `headertransaction`
--
ALTER TABLE `headertransaction`
  ADD PRIMARY KEY (`TransactionID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indeks untuk tabel `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detailtransaction`
--
ALTER TABLE `detailtransaction`
  ADD CONSTRAINT `detailtransaction_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`),
  ADD CONSTRAINT `detailtransaction_ibfk_2` FOREIGN KEY (`TransactionID`) REFERENCES `headertransaction` (`TransactionID`);

--
-- Ketidakleluasaan untuk tabel `headertransaction`
--
ALTER TABLE `headertransaction`
  ADD CONSTRAINT `headertransaction_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
