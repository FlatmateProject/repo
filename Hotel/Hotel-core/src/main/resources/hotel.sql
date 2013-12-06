/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES = utf8 */;

--
-- Baza danych: `hotel`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `firmy`
--

DROP TABLE IF EXISTS `firmy`;
CREATE TABLE IF NOT EXISTS `firmy` (
  `IDF_KRS`        BIGINT(10) NOT NULL AUTO_INCREMENT,
  `NAZWA`          VARCHAR(20) DEFAULT NULL,
  `WOJEWODZTWO`    VARCHAR(20) DEFAULT NULL,
  `MIASTO`         VARCHAR(20) DEFAULT NULL,
  `ULICA`          VARCHAR(20) DEFAULT NULL,
  `BLOK`           VARCHAR(4) DEFAULT NULL,
  `NR_LOKALU`      BIGINT(4) DEFAULT NULL,
  `STATUS`         VARCHAR(20) DEFAULT NULL,
  `UWAGI`          VARCHAR(100) DEFAULT NULL,
  `REGON`          BIGINT(9) DEFAULT NULL,
  `NIP`            BIGINT(11) DEFAULT NULL,
  `TELEFON`        BIGINT(9) DEFAULT NULL,
  `DATA_ZALOZENIA` DATE DEFAULT NULL,
  PRIMARY KEY (`IDF_KRS`),
  UNIQUE KEY `IDF_KRS` (`IDF_KRS`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `firmy`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `grafik`
--

DROP TABLE IF EXISTS `grafik`;
CREATE TABLE IF NOT EXISTS `grafik` (
  `ID_GRAF`    BIGINT(2) NOT NULL AUTO_INCREMENT,
  `IDK_PESEL`  BIGINT(11) DEFAULT NULL,
  `ZMIANA`     BIGINT(1) DEFAULT NULL,
  `DATA`       DATE DEFAULT NULL,
  `NADGODZINY` BIGINT(4) DEFAULT NULL,
  `ID_POKOJU`  BIGINT(4) DEFAULT NULL,
  PRIMARY KEY (`ID_GRAF`),
  UNIQUE KEY `ID_GRAF` (`ID_GRAF`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `grafik`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `grafik_sal`
--

DROP TABLE IF EXISTS `grafik_sal`;
CREATE TABLE IF NOT EXISTS `grafik_sal` (
  `ID_GRAF_S` BIGINT(2) NOT NULL AUTO_INCREMENT,
  `IDF_KRS`   BIGINT(10) DEFAULT NULL,
  `IDK_PESEL` BIGINT(11) DEFAULT NULL,
  `DATA`      DATE DEFAULT NULL,
  `ID_POKOJU` BIGINT(4) DEFAULT NULL,
  PRIMARY KEY (`ID_GRAF_S`),
  UNIQUE KEY `ID_GRAF_S` (`ID_GRAF_S`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `grafik_sal`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `kantor`
--

DROP TABLE IF EXISTS `kantor`;
CREATE TABLE IF NOT EXISTS `kantor` (
  `ID_TRANS`  BIGINT(10) NOT NULL AUTO_INCREMENT,
  `IDK_PESEL` BIGINT(11) DEFAULT NULL,
  `IDF_KRS`   BIGINT(10) DEFAULT NULL,
  `DATA`      DATE DEFAULT NULL,
  `W_KU`      BIGINT(20) DEFAULT NULL,
  `W_SP`      BIGINT(20) DEFAULT NULL,
  `ILOSC`     BIGINT(20) DEFAULT NULL,
  `WARTOSC`   BIGINT(20) DEFAULT NULL,
  `ZYSK`      BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`ID_TRANS`),
  UNIQUE KEY `ID_TRANS` (`ID_TRANS`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `kantor`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `klasy`
--

DROP TABLE IF EXISTS `klasy`;
CREATE TABLE IF NOT EXISTS `klasy` (
  `ID_KLASY` BIGINT(2) NOT NULL AUTO_INCREMENT,
  `IL_OSOB`  BIGINT(1) DEFAULT NULL,
  `CENA`     BIGINT(4) DEFAULT NULL,
  `OPIS`     VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`ID_KLASY`),
  UNIQUE KEY `ID_KLASY` (`ID_KLASY`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `klasy`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `klienci`
--

DROP TABLE IF EXISTS `klienci`;
CREATE TABLE IF NOT EXISTS `klienci` (
  `IDK_PESEL`   BIGINT(11) NOT NULL AUTO_INCREMENT,
  `IMIE`        VARCHAR(20) DEFAULT NULL,
  `NAZWISKO`    VARCHAR(20) DEFAULT NULL,
  `WOJEWODZTWO` VARCHAR(20) DEFAULT NULL,
  `MIASTO`      VARCHAR(20) DEFAULT NULL,
  `ULICA`       VARCHAR(20) DEFAULT NULL,
  `BLOK`        VARCHAR(4) DEFAULT NULL,
  `NR_LOKALU`   BIGINT(4) DEFAULT NULL,
  `STATUS`      VARCHAR(5) DEFAULT NULL,
  `UWAGI`       VARCHAR(20) DEFAULT NULL,
  `TELEFON`     BIGINT(9) DEFAULT NULL,
  `NIP`         BIGINT(10) DEFAULT NULL,
  PRIMARY KEY (`IDK_PESEL`),
  UNIQUE KEY `IDK_PESEL` (`IDK_PESEL`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `klienci`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `pokoje`
--

DROP TABLE IF EXISTS `pokoje`;
CREATE TABLE IF NOT EXISTS `pokoje` (
  `ID_POKOJU` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `ID_KLASY`  BIGINT(2) DEFAULT NULL,
  `IDK_PESEL` BIGINT(11) DEFAULT NULL,
  `IDF_KRS`   BIGINT(10) DEFAULT NULL,
  PRIMARY KEY (`ID_POKOJU`),
  UNIQUE KEY `ID_POKOJU` (`ID_POKOJU`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `pokoje`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `pracownicy`
--

DROP TABLE IF EXISTS `pracownicy`;
CREATE TABLE IF NOT EXISTS `pracownicy` (
  `IDP_PESEL`     BIGINT(11) NOT NULL AUTO_INCREMENT,
  `IMIE`          VARCHAR(20) DEFAULT NULL,
  `NAZWISKO`      VARCHAR(20) DEFAULT NULL,
  `HASLO`         VARCHAR(40) DEFAULT NULL,
  `WOJEWODZTWO`   VARCHAR(20) DEFAULT NULL,
  `MIASTO`        VARCHAR(20) DEFAULT NULL,
  `ULICA`         VARCHAR(20) DEFAULT NULL,
  `BLOK`          VARCHAR(4) DEFAULT NULL,
  `NR_LOKALU`     BIGINT(4) DEFAULT NULL,
  `TELEFON`       BIGINT(9) DEFAULT NULL,
  `NIP`           BIGINT(10) DEFAULT NULL,
  `ID_STANOWISKA` BIGINT(2) DEFAULT NULL,
  PRIMARY KEY (`IDP_PESEL`),
  UNIQUE KEY `IDP_PESEL` (`IDP_PESEL`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `pracownicy`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `rachunki`
--

DROP TABLE IF EXISTS `rachunki`;
CREATE TABLE IF NOT EXISTS `rachunki` (
  `ID_RACHUNKU` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `ID_REZ`      BIGINT(10) DEFAULT NULL,
  `DATA`        DATE DEFAULT NULL,
  `IDR`         VARCHAR(20) DEFAULT NULL,
  `PODATEK`     BIGINT(20) DEFAULT NULL,
  `WARTOSC`     BIGINT(20) DEFAULT NULL,
  `NAZWA`       VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`ID_RACHUNKU`),
  UNIQUE KEY `ID_RACHUNKU` (`ID_RACHUNKU`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `rachunki`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `rekreacja`
--

DROP TABLE IF EXISTS `rekreacja`;
CREATE TABLE IF NOT EXISTS `rekreacja` (
  `IDK_REKREACJI` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `ID_USLUGI`     BIGINT(2) DEFAULT NULL,
  `ID_REZ`        BIGINT(10) DEFAULT NULL,
  `CZAS`          BIGINT(2) DEFAULT NULL,
  PRIMARY KEY (`IDK_REKREACJI`),
  UNIQUE KEY `IDK_REKREACJI` (`IDK_REKREACJI`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `rekreacja`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `rezerwacje`
--

DROP TABLE IF EXISTS `rezerwacje`;
CREATE TABLE IF NOT EXISTS `rezerwacje` (
  `ID_REZ`    BIGINT(10) NOT NULL AUTO_INCREMENT,
  `IDK_PESEL` BIGINT(11) DEFAULT NULL,
  `IDF_KRS`   BIGINT(10) DEFAULT NULL,
  `ID_USLUGI` BIGINT(2) DEFAULT NULL,
  `ID_POKOJU` BIGINT(4) DEFAULT NULL,
  `TYP`       INT(1) DEFAULT NULL,
  `DATA_Z`    DATE       NOT NULL,
  `DATA_W`    DATE,
  PRIMARY KEY (`ID_REZ`),
  UNIQUE KEY `ID_REZ` (`ID_REZ`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `rezerwacje`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `stanowiska`
--

DROP TABLE IF EXISTS `stanowiska`;
CREATE TABLE IF NOT EXISTS `stanowiska` (
  `ID_STANOWISKA` BIGINT(4) NOT NULL AUTO_INCREMENT,
  `NAZWA`         VARCHAR(20) DEFAULT NULL,
  `PODSTAWA`      BIGINT(6) DEFAULT NULL,
  `PREMIA`        VARCHAR(6) DEFAULT NULL,
  PRIMARY KEY (`ID_STANOWISKA`),
  UNIQUE KEY `ID_STANOWISKA` (`ID_STANOWISKA`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `stanowiska`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `uslugi`
--

DROP TABLE IF EXISTS `uslugi`;
CREATE TABLE IF NOT EXISTS `uslugi` (
  `ID_USLUGI` BIGINT(2) NOT NULL AUTO_INCREMENT,
  `NAZWA`     VARCHAR(20) DEFAULT NULL,
  `CENA`      BIGINT(4) DEFAULT NULL,
  `TYP`       VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (`ID_USLUGI`),
  UNIQUE KEY `ID_USLUGI` (`ID_USLUGI`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `uslugi`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla  `waluty`
--

DROP TABLE IF EXISTS `waluty`;
CREATE TABLE IF NOT EXISTS `waluty` (
  `ID_WALUTY` BIGINT(2) NOT NULL AUTO_INCREMENT,
  `NAZWA`     VARCHAR(5) DEFAULT NULL,
  `CENA_SP`   NUMERIC(8, 2) DEFAULT NULL,
  `CENA_KU`   NUMERIC(8, 2) DEFAULT NULL,
  `ILOSC`     BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`ID_WALUTY`),
  UNIQUE KEY `ID_WALUTY` (`ID_WALUTY`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;

--
-- Zrzut danych tabeli `waluty`
--

--
-- Struktura tabeli dla  `firmy`
--

DROP TABLE IF EXISTS `archiwum`;
CREATE TABLE IF NOT EXISTS `archiwum` (
  `ID_ARCHIVE` BIGINT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1
  AUTO_INCREMENT =1;
