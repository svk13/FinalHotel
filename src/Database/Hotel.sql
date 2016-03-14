PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE Hotel(
    id int primary key,
	name varchar(20),
	address varchar(20),
	postcode int,
	city varchar(20),
	URL varchar(40)
    );
INSERT INTO "Hotel" VALUES(1,'Hotel Hilton','Suðurlandsbraut 14',105,'Reykjavik','www.hilton.is');
INSERT INTO "Hotel" VALUES(2,'Black Pearl','Laugavegur 100',101,'Reykjavik','www.blackpearl.is');
INSERT INTO "Hotel" VALUES(3,'Reykjavik Residence Hotel','Laugavegur 10',101,'Reykjavik','www.reykjavikresidencehotel.is');
INSERT INTO "Hotel" VALUES(4,'Reykjavik4you','Hverfisgata 20',101,'Reykjavik','www.reykjavik4you.is');
INSERT INTO "Hotel" VALUES(5,'Alda Hotel','Gata 14',105,'Reykjavik','www.alda.is');
INSERT INTO "Hotel" VALUES(6,'Hotel Berg','Gata 6',111,'Reykjavik','www.hotelberg.is');
INSERT INTO "Hotel" VALUES(7,'Fosshótel Austfjörðum','Gata 7',700,'Fáskrúðsfirði','www..is');
INSERT INTO "Hotel" VALUES(8,'Kvosin Downtown Hotel','Gata 8',101,'Reykjavik','www.kvosin.is');
INSERT INTO "Hotel" VALUES(9,'Castle House Luxury Apartmens','Gata 9',105,'Reykjavik','www.castlehouse.is');
INSERT INTO "Hotel" VALUES(10,'Hotel Rangá','Gata 10',630,'Hella','www.ranga.is');
INSERT INTO "Hotel" VALUES(11,'Reykjavik Residence Suites','Gata 11',101,'Reykjavik','www.reykjavikresidencesuites.is');
INSERT INTO "Hotel" VALUES(12,'101 Hotel','Hverfisgata 10',101,'Reykjavik','www.101Hotel.is');
INSERT INTO "Hotel" VALUES(13,'4th Floor Hotel','Laugavegur 101',101,'Reykjavik','www.4thfloorhotel.is');
INSERT INTO "Hotel" VALUES(14,'Arctic Comfort Hotel','Síðumúli 19',108,'Reykjavik','www.arcticcomforthotel.is');
INSERT INTO "Hotel" VALUES(15,'Best Western Hotel Reykjavik','Rauðararstíg 37',105,'Reykjavík','www.westernhotel.is');
INSERT INTO "Hotel" VALUES(16,'City Center Hotel','Austurstræti 6',101,'Reykjavik','www.citycenterhotel.is');
INSERT INTO "Hotel" VALUES(17,'Fosshotel Baron','Baronsstígur 2',101,'Reykjavik','www.baronhotel.is');
INSERT INTO "Hotel" VALUES(18,'Fosshotel Lind','Rauðarárstígur 28',105,'Reykjavik','www.fosshotel.is');
INSERT INTO "Hotel" VALUES(19,'Grand Hotel Reykjavik','Sigtún 38',105,'Reykjavik','www.grandhotel.is');
INSERT INTO "Hotel" VALUES(20,'Hilton Reykjavik Nordica','Suðurlandsbraut 2',108,'Reykjavik','www.hiltonhotel.is');
INSERT INTO "Hotel" VALUES(21,'Hotel Leifur Eiriksson','Skólavörðustígur 45',101,'Reykjavik','www.hotelleifureiriksson.is');
INSERT INTO "Hotel" VALUES(22,'Hotel Odinsve Óðinstorg','Óðinsgata 20',101,'Reykjavik','www.odinsvehotel.is');
INSERT INTO "Hotel" VALUES(23,'Hotel Reykjavik Centrum','Aðalstræti 16',101,'Reykjavik','www.reykjavikcentrum.is');
INSERT INTO "Hotel" VALUES(24,'Hotel Smári','Hlíðasmári 13',201,'Kópavogur','www.hotelsmari.is');
INSERT INTO "Hotel" VALUES(25,'Hotel Viking Fjorukrain/Viking Village','Strandgata 55',220,'Hafnarfjörður','www.fjorukrain.is');
INSERT INTO "Hotel" VALUES(26,'Hotel Örkin','Brautarholt 29',105,'Reykjavik','www.hotelork.is');
INSERT INTO "Hotel" VALUES(27,'Icelandair Hotel Reykjavik Natura','Reykjavík airport',105,'Reykjavik','www.icelandairhotels.is');
INSERT INTO "Hotel" VALUES(28,'Metropolitan Hotel','Ránargata 4a',101,'Reykjavik','www.metropolitanhotel.is');
INSERT INTO "Hotel" VALUES(29,'Park Inn Island','Ármúli 9',108,'Reykjavik','www.parkinniceland.is');
INSERT INTO "Hotel" VALUES(30,'Radisson Blu 1919 Hotel','Pósthússtræti 2',101,'Reykjavik','www.radissonblu.is');
INSERT INTO "Hotel" VALUES(31,'Radisson Blu Saga Hotel','v/Hagatorg',104,'Reykjavik','www.radissonblu.is');
CREATE TABLE Reservation(
    id INT Primary key,
    date_in date,
	date_out date,
	made_by varchar(20),
	guest_id int,
	foreign key (guest_id) references guest(id) 
    );
CREATE TABLE Room_Bookings(
	hotelID int references Hotel(id),
	reservationID int NOT NULL UNIQUE,
	date_in varchar(30),
	date_out varchar(30),
	room_count int, /* fjöldi herbergja pr. kúnna*/
	client_id String NOT NULL UNIQUE,
	client_passw varchar(20)

);
CREATE TABLE HotelFacilities(
	hotelID int references Hotel(id),
	Wifi boolean,
	FreeWifi boolean,
	SmokingArea boolean,
	Swimmingpool boolean,
	Gym boolean,
	TV boolean
);
INSERT INTO "HotelFacilities" VALUES(1,1,0,1,0,0,0);
INSERT INTO "HotelFacilities" VALUES(2,0,1,1,1,0,0);
INSERT INTO "HotelFacilities" VALUES(3,1,0,0,1,1,0);
INSERT INTO "HotelFacilities" VALUES(4,1,0,1,0,0,0);
INSERT INTO "HotelFacilities" VALUES(5,0,0,0,1,1,1);
INSERT INTO "HotelFacilities" VALUES(6,0,0,1,1,1,1);
INSERT INTO "HotelFacilities" VALUES(7,1,1,1,0,0,1);
INSERT INTO "HotelFacilities" VALUES(8,1,0,0,1,1,0);
INSERT INTO "HotelFacilities" VALUES(9,0,1,0,0,0,0);
INSERT INTO "HotelFacilities" VALUES(10,1,0,0,1,1,0);
INSERT INTO "HotelFacilities" VALUES(11,0,0,0,0,1,0);
INSERT INTO "HotelFacilities" VALUES(12,0,1,0,0,0,0);
INSERT INTO "HotelFacilities" VALUES(13,0,1,1,1,0,1);
INSERT INTO "HotelFacilities" VALUES(14,0,1,0,1,0,0);
INSERT INTO "HotelFacilities" VALUES(15,0,1,1,0,1,0);
INSERT INTO "HotelFacilities" VALUES(16,1,0,0,1,0,1);
INSERT INTO "HotelFacilities" VALUES(17,0,0,1,0,0,0);
INSERT INTO "HotelFacilities" VALUES(18,0,0,0,0,1,0);
INSERT INTO "HotelFacilities" VALUES(19,1,1,1,1,0,1);
INSERT INTO "HotelFacilities" VALUES(20,1,1,1,0,0,1);
INSERT INTO "HotelFacilities" VALUES(21,1,0,1,1,1,0);
INSERT INTO "HotelFacilities" VALUES(22,0,0,1,0,0,1);
INSERT INTO "HotelFacilities" VALUES(23,1,1,1,1,1,1);
INSERT INTO "HotelFacilities" VALUES(24,1,0,0,0,0,1);
INSERT INTO "HotelFacilities" VALUES(25,0,1,0,0,1,1);
INSERT INTO "HotelFacilities" VALUES(26,1,1,1,0,1,1);
INSERT INTO "HotelFacilities" VALUES(27,0,1,1,0,1,0);
INSERT INTO "HotelFacilities" VALUES(28,1,0,0,1,0,1);
INSERT INTO "HotelFacilities" VALUES(29,0,0,0,0,1,0);
INSERT INTO "HotelFacilities" VALUES(30,0,0,0,1,0,1);
INSERT INTO "HotelFacilities" VALUES(31,1,0,1,1,0,0);
CREATE TABLE Room_price(
	hotelID int references Hotel(id),
	type1 int'Kr.',
	type2 int'Kr.',
	type3 int'Kr.',
	counttype1 int,
	counttype2 int,
	counttype3 int,
	check (counttype1>=0),
	check (counttype2>=0),
	check (counttype3>=0)
);
INSERT INTO "Room_price" VALUES(1,46374,19403,5440,6,20,51);
INSERT INTO "Room_price" VALUES(2,35307,18827,6813,8,15,52);
INSERT INTO "Room_price" VALUES(3,49017,31514,6570,6,23,18);
INSERT INTO "Room_price" VALUES(4,48042,33003,11790,2,19,20);
INSERT INTO "Room_price" VALUES(5,41892,23966,17018,6,32,57);
INSERT INTO "Room_price" VALUES(6,53087,24061,11045,6,25,52);
INSERT INTO "Room_price" VALUES(7,53906,19903,17408,8,30,28);
INSERT INTO "Room_price" VALUES(8,39585,18562,14695,6,32,35);
INSERT INTO "Room_price" VALUES(9,53928,21647,9591,3,35,43);
INSERT INTO "Room_price" VALUES(10,39778,25854,17017,8,20,32);
INSERT INTO "Room_price" VALUES(11,38196,28460,15824,3,17,50);
INSERT INTO "Room_price" VALUES(12,42814,34731,11569,8,19,35);
INSERT INTO "Room_price" VALUES(13,39461,31054,9599,4,15,38);
INSERT INTO "Room_price" VALUES(14,54060,24284,17320,3,29,33);
INSERT INTO "Room_price" VALUES(15,43534,32861,16569,7,32,20);
INSERT INTO "Room_price" VALUES(16,38125,23547,14932,7,21,52);
INSERT INTO "Room_price" VALUES(17,52828,32364,4510,3,34,19);
INSERT INTO "Room_price" VALUES(18,36210,32141,4466,4,33,17);
INSERT INTO "Room_price" VALUES(19,52644,22447,13971,4,35,45);
INSERT INTO "Room_price" VALUES(20,42976,28732,12186,4,21,60);
INSERT INTO "Room_price" VALUES(21,52215,31942,9521,5,24,45);
INSERT INTO "Room_price" VALUES(22,42636,27757,6370,8,22,21);
INSERT INTO "Room_price" VALUES(23,54189,24637,6712,5,30,34);
INSERT INTO "Room_price" VALUES(24,50926,21177,8589,7,16,36);
INSERT INTO "Room_price" VALUES(25,54666,18400,12470,7,29,42);
INSERT INTO "Room_price" VALUES(26,54655,34922,15621,7,15,23);
INSERT INTO "Room_price" VALUES(27,44461,28849,13980,7,15,21);
INSERT INTO "Room_price" VALUES(28,54679,34589,7357,4,22,44);
INSERT INTO "Room_price" VALUES(29,44580,23564,14490,4,22,41);
INSERT INTO "Room_price" VALUES(30,54805,25420,9949,5,19,50);
INSERT INTO "Room_price" VALUES(31,51653,20414,9175,8,19,21);
COMMIT;
