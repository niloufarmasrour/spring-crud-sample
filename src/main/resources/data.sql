-- create table CUSTOMER
-- (
--     "ID" INTEGER primary key auto_increment,
--     USERNAME nvarchar default 50 not null,
--     LASTNAME nvarchar default 50 not null,
--     EMAIL    nvarchar default 50 not null,
--     AGE      int                 not null,
--     PASSWORD nvarchar default 100,
--     ROLE     nvarchar default 20,
--     constraint CUSTOMER_PK
--         primary key (ID)
-- );



INSERT INTO CUSTOMER (ID, USERNAME, LASTNAME, EMAIL, AGE, PASSWORD, ROLE) VALUES (0, 'admin', 'adminian', 'admin@gmail.com', 26, '$2a$12$jYJYfiKpP7QRK9nCg9E9ueoHB0vg546SyvgHAeLpGIESaNXg734JO', 'ADMIN');