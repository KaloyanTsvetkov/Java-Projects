DROP TABLE PLANES;


CREATE TABLE PLANES (
   Plane_ID NVARCHAR(20) NOT NULL PRIMARY KEY,
   Model NVARCHAR(50) NOT NULL,
   Produce_Year NVARCHAR(10) NOT NULL,
   Traveled_KM LONG NOT NULL,
   Places INT NOT NULL
);


INSERT INTO PLANES (Plane_ID, Model, Produce_Year, Traveled_KM, Places)
VALUES
   ('B737001P189','Boeing 737', '2015', 510258, 189),
   ('B767001P260','Boeing 767', '2013', 723288, 260),
   ('BBJ901P20','Boeing Business Jet', '2014', 134258, 20),
   ('B787103P219','Boeing 787', '2015', 607653, 219),
   ('AI101P144','Airbus Industrie', '2010', 270380, 144),
   ('BA201P84','British Aerospace', '2014', 109564, 84);
