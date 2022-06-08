/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.5-10.4.16-MariaDB : Database - lookies
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lookies` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `lookies`;

/*Table structure for table `bahan` */

DROP TABLE IF EXISTS `bahan`;

CREATE TABLE `bahan` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `nama_bahan` varchar(100) DEFAULT NULL,
  `takaran` varchar(50) DEFAULT NULL,
  `ID_Kue` int(10) DEFAULT NULL,
  `hasil_ML` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_idkue` (`ID_Kue`),
  CONSTRAINT `fk_idkue` FOREIGN KEY (`ID_Kue`) REFERENCES `kue` (`ID_Kue`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bahan` */

insert  into `bahan`(`ID`,`nama_bahan`,`takaran`,`ID_Kue`,`hasil_ML`) values (1,'tepung beras','200 gram',1,'kue_ape'),(2,'tepung terigu protein','100 gram',1,'kue ape'),(3,'baking powder','1 sendok teh',1,'kue_ape'),(4,'garam','1/2 sendok teh',1,'kue_ape'),(5,'gula pasir','175 gram',1,'kue_ape'),(6,'air','400 ml',1,'kue_ape'),(7,'pasta pandan','8 tetes',1,'kue_ape'),(8,'air daun suji','25 ml',1,'kue_ape'),(9,'santan kental','300 ml',2,'kue_bika_ambon'),(10,'santan cair','100 ml',2,'kue_bika_ambon'),(11,'daun jeruk','4 lembar',2,'kue_bika_ambon'),(12,'serai yang sudah dimemarkan','2 batang',2,'kue_bika_ambon'),(13,'kunyit bubuk','1 sendok makan',2,'kue_bika_ambon'),(14,'air','120 ml',2,'kue_bika_ambon'),(15,'tepung protein sedang','50 gram',2,'kue_bika_ambon'),(16,'ragi instan','8 gram',2,'kue_bika_ambon'),(17,'gula pasir','200 gram',2,'kue_bika_ambon'),(18,'tepung sagu','100 gram',2,'kue_bika_ambon'),(19,'tepung ketan','50 gram',2,'kue_bika_ambon'),(20,'kuning telur','8',2,'kue_bika_ambon'),(21,'telur ayam utuh','2 butir',2,'kue_bika_ambon'),(22,'bubuk vanili','1 sendok teh',2,'kue_bika_ambon'),(23,'garam','1/2 sendok teh',2,'kue_bika_ambon'),(24,'Tepung kanji','500 gram',3,'kue_cenil'),(25,'Gula pasir','2 sendok makan',3,'kue_cenil'),(26,'Vanili','Seperempat sendok teh',3,'kue_cenil'),(27,'Air','300 cc',3,'kue_cenil'),(28,'Daun Pandan','2 lembar ',3,'kue_cenil'),(29,'Garam','Setengah sendok teh',3,'kue_cenil'),(30,'Pewarna makan','1/4 sendok teh',3,'kue_cenil'),(31,'daging Kelapa tua kemudian parut memanjang','1/2',3,'kue_cenil'),(32,'Gula pasir','4 sendok makan',3,'kue_cenil'),(33,'Garam','1/2 sendok teh',3,'kue_cenil'),(34,'kelapa parut\r\n\r\n','1/2 buah',4,'kue_dadar_gulung'),(35,'gula merah','125 gram',4,'kue_dadar_gulung'),(36,'Air','secukupnya',4,'kue_dadar_gulung'),(37,'Garam','secukupnya',4,'kue_dadar_gulung'),(38,'Pewarna makanan','3 tetes',4,'kue_dadar_gulung'),(39,'Singkong kupas','1 kg',5,'kue_gethuk_lindri'),(40,'gula pasir','200 gram',5,'kue_gethuk_lindri'),(41,'vanili','1/2 sendok teh',5,'kue_gethuk_lindri'),(42,'air','100 ml',5,'kue_gethuk_lindri'),(43,'kelapa yang diparut kasar','1/3 butir',5,'kue_gethuk_lindri'),(44,'garam','1/4 sendok teh',5,'kue_gethuk_lindri'),(45,'terigu kunci/segitiga ','700 gr ',6,'kue_kastengel'),(46,'mentega mixed margarine','500 gr',6,'kue_kastengel'),(47,'keju edam parut halus','150 gr',6,'kue_kastengel'),(48,'kuning telur ','5 butir',6,'kue_kastengel'),(49,'putih telur ','1 butir',6,'kue_kastengel'),(50,'susu bubuk full cream ','50 gr',6,'kue_kastengel'),(51,'gula halus ','1 sdt',6,'kue_kastengel'),(52,'Bola Ketan',NULL,7,'kue_klepon'),(53,'Tepung ketan','250 gr ',7,'kue_klepon'),(54,'Tepung beras ','50 gr',7,'kue_klepon'),(55,'Air','secukupnya',7,'kue_klepon'),(56,'Isian',NULL,7,'kue_klepon'),(57,'Gula merah','sesuai selera',7,'kue_klepon'),(58,'Balutan',NULL,7,'kue_klepon'),(59,NULL,NULL,7,'kue_klepon');

/*Table structure for table `kue` */

DROP TABLE IF EXISTS `kue`;

CREATE TABLE `kue` (
  `ID_Kue` int(10) NOT NULL AUTO_INCREMENT,
  `hasil_ML` varchar(100) NOT NULL,
  `nama_kue` varchar(100) NOT NULL,
  `paragaf_1` text DEFAULT NULL,
  `paragaf_2` text DEFAULT NULL,
  `gambar` varchar(200) NOT NULL,
  `bahan` text DEFAULT NULL,
  PRIMARY KEY (`ID_Kue`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kue` */

insert  into `kue`(`ID_Kue`,`hasil_ML`,`nama_kue`,`paragaf_1`,`paragaf_2`,`gambar`,`bahan`) values (1,'kue_ape','Kue Ape','Salah satu jajanan paling ikonik di kota Jakarta adalah kue ape yang berpenampilan hijau bulat pipih dengan sedikit \'benjolan\' di tengah-tengahnya ini. Masyarakat Jakarta, khususnya masyarakat Betawi, pasti pernah mencicipi kue unik satu ini. Kue ape tergolong jajanan dengan harga yang terjangkau, digemari oleh anak-anak maupun orang tua. Kue ape paling nikmat disajikan saat masih hangat, ditemani dengan secangkir teh hangat.','Kue ini berbentuk bulat dengan pinggiran tipis renyah seperti renda dan lembut ditengah. Perpaduan rasa manis dan aroma daun pandan akan menambah rasa kue ape semakin terasa lezat.','https://storage.googleapis.com/lookies2/gambarkue/kue_ape.jpg','- 200 gram tepung beras\r\n- 100 gram tepung terigu protein sedang\r\n- 1 sendok teh baking powder\r\n- 1/2 sendok teh garam\r\n- 175 gram gula pasir\r\n- 400 ml air\r\n- 2-4 tetes pasta pandan atau sesuai selera '),(2,'kue_bika_ambon','Kue Bika Ambon','Bika Ambon adalah salah satu makanan khas jenis kue basah. Kue satu ini memiliki ciri khas dengan warnanya yang kuning dan memiliki rongga-rongga di bagian dalamnya.','Kue dengan rasa yang lezat ini sudah ada sejak puluhan tahun lalu dan sangat populer di Kota Medan. Bika Ambon bahkan telah menjadi salah satu ikon kuliner yang tak bisa dilepaskan dari ibu kota Sumut ini.','https://storage.googleapis.com/lookies2/gambarkue/kue_bika_ambon.jpg','Bahan Santan:\r\n- 300 ml santan kental\r\n- 100 ml santan cair\r\n- 4 lembar daun jeruk, buang batangnya\r\n- 2 batang serai, memarkan\r\n- 1 sendok makan kunyit bubuk\r\nBahan Biang:\r\n- 120 ml air\r\n- 50 gram tepung protein sedang\r\n- 8 gram ragi instan\r\n- 2 sendok makan gula pasir\r\nBahan Utama:\r\n- 200 gram gula pasir\r\n- 100 gram tepung sagu\r\n- 50 gram tepung ketan\r\n- 8 kuning telur\r\n- 2 butir telur ayam utuh\r\n- 1 sendok teh bubuk vanili\r\n- 1/2 sendok teh garam\r\n\r\n\r\n'),(3,'kue_cenil','Kue Cenil','Cenil yang berasal dari Pacitan, Jawa Timur memiliki bentuk bulan-bulat sebesar kelerang dengan rasa manis atau pun gurih. Hampir sama dengan cenil di daerah lain, cenil ala Pacitan ini juga memiliki warna yang beraneka ragam, disajikan bersama saus gula merah dan taburan kelapa parut.','Cenil juga disebut juga sebagai cethil di beberapa daerah di Jawa Tengah. Cenil khas Jawa Tengah ini biasanya disajikan dalam daun pisang yang dipincuk. Baik di Jawa Tengah maupun Jawa Timur, pincuk merujuk pada makna pinten-pinten cukup.','https://storage.googleapis.com/lookies2/gambarkue/kue_cenil.jpeg','- 100 gr tepung tapioka\r\n- 100 gr tepung terigu pro rendah\r\n- 1/2 sdt garam\r\n- 1/4 sdt vanilli bubuk\r\n- 130-150 ml air panas\r\n- Daun pandan, untuk merebus\r\n- Pewarna makanan\r\n- Kelapa parut dan sedikit garam (kukus sebentar)'),(4,'kue_dadar_gulung','Kue Dadar Gulung','Dadar Gulung adalah salah satu makanan tradisional Indonesia yang berasal dari pulau jawa. Asal mula Dadar Gulung berawal dari Orang Romawi yang mengenalinya dengan sebutan Pancake. Dadar Gulung  dapat digolongkan sebagai pancake yang diisi dengan parutan kelapa yang dicampur dengan gula jawa cair. Isi ini disebut unti.','Tidak hanya dadar gulung pandan saja yang dapat disajikan atau dijual, namun ada beberapa variasi kulit dadar gulung yang mulai bermunculan, seperti: Dadar Gulung Polkadot, Dadar Gulung Cokelat, dan Dadar Gulung Vanila','https://storage.googleapis.com/lookies2/gambarkue/kue_dadar_gulung.jpg','- 200 gram terigu\r\n- 1 butir telur\r\n- 500 ml air\r\n- 1/2 sdm gula\r\n- 1/4 sdt garam\r\n- Sedikit vanili\r\n'),(5,'kue_gethuk_lindri','Kue Gethuk Lindri','Getuk lindri adalah salah satu jajanan jadul khas Indonesia yang terbuat dari singkong. Getuk lindri banyak dijumpai di daerah Jawa, terutama Jawa Tengah. Getuk sangat digemari oleh semua kalangan karena rasanya yang legit.','Sejarah getuk bermula ketika zaman penjajahan Jepang ketika itu beras yang menjadi makanan pokok namun sulit untuk ditemukan. Sehingga, masyarakat lokal mengganti beras dengan ketela karena mudah ditemukan. Pada awalnya penduduk membuat getuk gondok, yaitu bentuk getuk yang paling sederhana dengan cara mengkukus singkong lalu diambil serat tengahnya. Kemudian ditumbuk dengan halus dan diberi rasa manis atau asin.','https://storage.googleapis.com/lookies2/gambarkue/kue_gethuk_lindri.jpg','- 500 gr singkong, lalu kupas\r\n- 100 gram gula pasir\r\n- 1/2 sendok teh vanili\r\n- 4 tetes pasta coklat\r\n- 50 gr Palmia Margarin Serbaguna\r\n- 1/2 butir kelapa yang diparut kasar dan dibuang kulit ari nya\r\n- 1/4 sendok teh garam\r\n'),(6,'kue_kastengel','Kue Kastengel','Kaasstengels (bahasa Belanda kaas, keju; stengel, batang) adalah kue kering yang dibuat dari adonan tepung terigu, telur, margarin, dan parutan keju. Kue ini berbentuk persegi panjang, panjangnya sekitar 3-4 cm dan lebarnya 1 cm, dan dipanggang di dalam oven hingga kuning keemasan.','Kaasstengels biasanya dihidangkan pada berbagai macam perayaan, mulai dari Natal, Idulfitri, hingga tahun baru Imlek dan hari besar lainnua di Indonesoa. Kue ini dijual di toku kue, toko roti, dan swalayan dalam kemasan stoples plastik.','https://storage.googleapis.com/lookies2/gambarkue/kue_kastengel.jpg','- 200 gr Mentega\r\n- 30 gr Gula Halus\r\n- 2 Butir Telur\r\n- 250 gr Tepung Terigu\r\n- 50 gr Maizena\r\n- 30 gr susu bubuk Frisian Flag Full Cream\r\n- 150 gr Keju Gouda\r\n- 100 gr Keju Cheddar\r\n'),(7,'kue_klepon','Kue Klepon','Klepon merupakan jajanan tradisional Indonesia yang umumnya terbuat dari\r\ntepung ketan putih yang dibentuk seperti bola-bola kecil dengan isi gula merah\r\ndan ditaburi parutan kelapa.','Kue Klepon memiliki tekstur yang kenyal, padat, manis dan tidak memiliki\r\ndaya simpan yang lama.','https://storage.googleapis.com/lookies2/gambarkue/kue_klepon.jpeg','- 500 gram tepung ketan\r\n- 100 gram tepung beras\r\n- 400 ml air\r\n- 20 lembar daun pandan\r\n- 100 gram gula merah disisir untuk isian\r\n- 200 gram kelapa parut untuk taburan\r\n- 1 sendok teh garam\r\n- Air secukupnya untuk merebus\r\n'),(8,'kue_lapis','Kue Lapis','Kue lapis adalah makanan khas Indonesia. Kue ini biasanya terdiri dari dua warna yang berlapis-lapis, ini yang memberi nama kue ini.','Terdapat dua jenis kue lapis dilihat dari cara pembuatannya, yaitu pembuatan lapisannya langsung pada saat proses memasak dalam satu loyang contohnya kue pepe dan kue lapis legit. Kedua, lapisannya disatukan di luar loyang, misalnya kue lapis surabaya dan kue lapis moka vla.','https://storage.googleapis.com/lookies2/gambarkue/kue_lapis.jpg','- 125 gr tepung beras\r\n- 75 gr tepung tapioka\r\n- 125 gr gula pasir\r\n- 1/2 sdt garam\r\n- 200 ml santan kental\r\n- 500 ml air\r\n- 4 helai pandan\r\n- Vanili secukupnya\r\n- Pewarna makanan secukupnya\r\n- Minyak makan'),(9,'kue_lemper','Kue Lemper','Lemper adalah kudapan yang terbuat dari ketan, biasanya berisi abon atau cincangan daging ayam dan dibungkus dengan daun pisang. Lemper terkenal di seluruh Indonesia dan dimakan sebagai pengganjal lapar sebelum memakan makanan lainnya. Lemper sering dijadikan menu favorit dalam snack box di antara kue-kue tradisional lainnya.','Lemper memiliki tekstur yang lengket dan cenderung sticky juga memiliki makna persaudaraan. Bersaudara berarti bersatu. Selain makna tersebut, nenek moyang masyarakat Jawa juga percaya bahwa tekstur lemper yang sangat lengket itu juga akan mendatangkan rezeki bagi siapapun yang memakannya.','https://storage.googleapis.com/lookies2/gambarkue/kue_lemper.jpg','- 1 kg beras ketan, cuci bersih\r\n- 2 gelas santan\r\n- 2 lembar daun pandan, ikat simpul\r\n- 2 sdt garam\r\n- daun pisang dan tusuk lidi secukupnya\r\n- 500 gram daging ayam, \r\n- 1 gelas santan kental\r\n- 2 sdm gula pasir\r\n- 1 sdt garam\r\n- 1 batang serai, geprek\r\n- 2 lembar daun salam\r\n- 2 lembar daun jeruk\r\n- air secukupnya\r\n\r\n'),(10,'kue_lumpur','Kue Lumpur','Kue lumpur adalah jajanan tradisional yang banyak ditemui di Indonesia.Konon kue lumpur merupakan makanan khas Betawi yang memiliki tekstur lembut dan halus.','Ada yang mengatakan bahwa kue ini disebut kue lumpur karena teksturnya yang licin dan lembut seperti lumpur. Kalau dipikir-pikir, benar juga. Ada juga yang bilang, nama “lumpur” dipakai karena adonan kue ini yang lembek dan lembut dengan sedikit serutan kelapa, sehingga mirip dengan lumpur.','https://storage.googleapis.com/lookies2/gambarkue/kue_lumpur.jpg','- 250 gram kentang, yang sudah dikukus\r\n- 250 gram tepung terigu\r\n- 2 butir telur ayam\r\n- 550 ml santan kelapa\r\n- 3 sdm mentega\r\n- 100 gram gula pasir\r\n- 1/2 sdt garam\r\n- Kismis atau keju,'),(11,'kue_nagasari','Kue Nagasari','Kue Nagasari atau Kue Nogosari (sebutan dalam bahasa Jawa) merupakan jenis kue basah tradisional yang sangat populer dan diwariskan dari generasi ke generasi dalam masyarakat Jawa. Umumnya, kue nagasari ini disajikan saat hari besar menurut tanggalan orang Jawa. Nagasari dipercaya berasal dari daerah Indramayu, Jawa Barat, karena Indramayu dikenal sebagai daerah penghasil beras.','Kue basah ini terbuat dari tepung beras, tepung sagu, santan, dan gula, dengan isian buah pisang raja yang dibalut dengan daun pisang lalu dikukus sampai matang.\r\nSelain menggunakan daun pisang, nagasari juga biasa dibungkus dengan balutan daun pandan supaya aromanya semakin harum.','https://storage.googleapis.com/lookies2/gambarkue/kue_nagasari.jpeg','- 5 buah pisang (potong jadi dua bagian sekitar 7 cm)\r\n- 250 gr tepung beras\r\n- 100 gr tepung tapioka\r\n- 250 gr gula pasir\r\n- sdt garam\r\n- 500 ml santan\r\n- 200 ml air\r\n- 2 lembar daun pandan, simpulkan\r\n- Daun pisang (potong 20 cm x 20 cm) '),(12,'kue_pastel','Kue Pastel','Pastel adalah semacam pastri yang dibuat dengan meletakkan isian di atas adonan, lalu dilipat dan ditutup rapat. Pastel dapat terasa manis atau gurih tergantung dari isian. Pastel dapat menjadi makanan yang mudah dibawa-bawa, sama seperti sandwich.','Pastel biasanya dijadikan jajanan atau panganan dalam berbagai jamuan atau acara dan dinikmati dengan cabai rawit hijau. Pastel dibuat dengan cara digoreng sampai kecokelatan dan bisa menjadi snack gurih yang cukup mengenyangkan.','https://storage.googleapis.com/lookies2/gambarkue/kue_pastel.jpeg','- 500 gram terigu protein sedang\r\n- 1 sdt garam\r\n- 100 gram margarin, lelehkan\r\n- 150-200 ml air hangat (secukupnya)\r\n- 1 kentang, potong dadu kecil1 \r\n- 3 sdm ayam giling\r\n- 4 butir bawang merah, cincang halus\r\n- 2 siung bawang putih, cincang halus\r\n- 1 batang daun bawang, iris halus\r\n- 1 batang seledri, iris halus\r\n- Garam secukupnya\r\n- Lada bubuk secukupnya\r\n- Kaldu bubuk secukupnya\r\n- 50 ml air\r\n- 6 butir telur rebus'),(13,'kue_putri_salju','Kue Putri Salju','Kue putri salju adalah sejenis kue kering yang berbentuk bulan sabit dan di atasnya diselimuti dengan gula halus seperti salju. Kue putri salju dibuat dari adonan tepung terigu, tepung maizena, mentega dan kuning telur yang dipanggang di dalam oven sampai matang dan di atasnya diselimuti dengan gula halus. Kue ini banyak digemari oleh orang-orang karena rasanya yang enak, gurih dan dingin ketika menggigitnya.\r\n','Kue putri salju biasanya menjadi sajian khas pada hari raya, seperti Idulfitri, Natal, dan tahun baru Imlek. Kue putri salju biasanya dijual dalam kemasan stoples plastik di toko roti, toko kue, dan pasar swalayan.','https://storage.googleapis.com/lookies2/gambarkue/kue_putri_salju.jpg','- 400 gram tepung terigu protein sedang\r\n- 50 gram tepung maizena\r\n- 300 gram mentega\r\n- 100 gram gula halus\r\n- 2 butir kuning telur\r\n- 100 gram keju parut\r\n- Gula halus secukupnya\r\n- Gula donat secukupnya\r\n\r\n'),(14,'kue_putu_ayu','Kue Putu Ayu','Putu ayu adalah salah satu jenis kue basah yang empuk dan lembut, kue ini berasal dari jawa tengah. putu ayu merupakan jajanan tradisional yang hingga kini mesih popular di kalangan masyarakat. Sesuai Namanya, ayu dalam Bahasa jawa berarti “cantik”, karena dari sisi visual kue ini terlihat cantik dan menarik.','Kue putu ini memiliki rasa yang sangat enak dan lembut Ketika disantap. Kue putu ayupun memiliki aroma yang sangat harum, karena terdapat campuran daun pandan dan kelapa parut di dalamnya.Kue putu ayu ini biasanya di hidangkan pada saat acara keluarga seperti selametan, hajatan, arisan dan lainnya. Kue putu ayu ini memiliki bentuk dan warna yang sangat indah, biasanya kue ini berwarna hijau dan merah muda.','https://storage.googleapis.com/lookies2/gambarkue/kue_putu_ayu.jpg','- 200 gram tepung terigu\r\n- 200 gram gula pasir\r\n- 150 ml santan kelapa\r\n- 2 butir telur ayam\r\n- 1 sdt SP\r\n- 150 gram kelapa parut, kukus dengan sejumput garam\r\n- 1 lembar daun pandan, ikat simpul\r\n- 3 tetes pasta pandan\r\n'),(15,'kue_risoles','Kue Risoles','Risoles, atau hanya risol saja (bahasa Inggris: rissole), adalah pastri berisi daging, biasanya daging cincang, dan sayuran yang dibungkus dadar, dan digoreng setelah dilapisi tepung panir dan kocokan telur ayam.','Yang terkenal di Indonesia adalah risoles dengan bagian kulit luar yang berlapis tepung panir atau tepung roti. Ada pula risoles yang langsung digoreng tanpa dilapisi tepung. Perbedaan paling mencolok adalah isian dari risoles yang beragam. Umumnya risoles diisi daging cincang, ada pula yang berisi rogut hingga sayuran dan telur. ','https://storage.googleapis.com/lookies2/gambarkue/kue_risoles.jpg','- 1 bungkus bihun kering ukuran kecil\r\n- 3 siung bawang merah, iris-iris\r\n- 2 siung bawang putih, cincang\r\n- 5 buah cabai rawit, iris-iris\r\n- Sayuran sesuai selera kol, wortel\r\n- 10 kulit lumpia\r\n- 1 sdm kaldu jamur '),(16,'kue_serabi','Kue Serabi','Serabi merupakan jajanan pasar tradisional yang berasal dari Indonesia. Serabi berasal dari bahasa Jawa yang berinduk dasar dari kata \"rabi\" yang dalam bahasa Jawa berarti \"kawin\". Mungkin karena proses pembuatannya yang cukup sebentar dan tidak terlalu lama maka orang Jawa menyebutnya dengan kata \"Serabi\"','Di Jawa, serabi umumnya disajikan dengan isian gula atau manisan lainnya, tetapi di Tatar Sunda serabi disajikan dengan isian oncom dan asinan lainnya. Serabi biasanya dijajakan di pagi hari dan dimasak menggunakan tungku sehingga menghasilkan rasa yang khas. Serabi ini banyak ditemukan terutama di Bandung, Jakarta dan Bogor.','https://storage.googleapis.com/lookies2/gambarkue/kue_serabi.jpg','- 150 gr terigu protein sedang\r\n- 50 gr tepung beras\r\n- 1/2 sdt ragi\r\n- 1 sdt baking powder\r\n- 1/2 sdt garam\r\n- 2 sdm gula\r\n- 1 telur\r\n- 65 ml santan\r\n- 200 ml air pandan \r\n- 200 gr gula merah\r\n- 3 sdm gula (sesuai selera)\r\n- 300 ml air\r\n- 200 ml santan\r\n- 1/2 sdt garam\r\n- 1 lembar pandan ');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Nama` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email` (`Email`),
  CONSTRAINT `CONSTRAINT_1` CHECK (char_length(`Password`) >= 8)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `users` */

insert  into `users`(`ID`,`Nama`,`Username`,`Email`,`Password`) values (1,'aaa','bbb','aaa@gmail.com','aaaabbbb'),(2,'sdsf','poiu','qq@gmail.com','aaaabbbb'),(3,'sdsf','poiu','aaqq@gmail.com','aaasabbbb'),(5,'sdsf','poiu','oapsd@gmail.com','$2b$12$RhYeLK4WIJ9zbtNDypqEE.mRskSKA0/APgc5hE/lb/b'),(6,'sdasd','sdasd','oapsdasd@gmail.com','$2b$12$iYcY6p0XDrJgGyCm2wC.wOS43eUfu.VBLQSnAIJ3osD'),(7,'sdasd','sdasd','ytyasg@gmail.com','$2b$12$CyIxa6hCPiNsqeCbLy99ReKvZjPWIcrX.Cjby7Hw7vw'),(8,'aaa','aaa','polang@gmail.com','aaaabbbb'),(9,'ayam','ayam','ayan@gmail.com','$2b$12$ZjAT4K5F1LvAbAl1/LozLuK1u87uDnei1.jdmSCjzrB'),(10,'seru','seru','seru@gmail.com','$2b$12$.e/iipu1ewjp7/vSgop67Ozpe/.vt2.ChNLoTCl.EFB'),(11,'ikan','ikan','ikan@gmail.com','aaaabbbb');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
