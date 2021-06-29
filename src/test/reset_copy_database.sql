-- DELETE SCRIPTS FOR DB [WARNING!]
set foreign_key_checks=0;
truncate table tour_order;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table user;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table user;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table tour;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table tour_category;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table tour_status;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table user_status;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table role;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table hotel;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table discount;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table order_status;
set foreign_key_checks=1;

set foreign_key_checks=0;
truncate table discount;
set foreign_key_checks=1;



-- INSERT TEST DATA

insert into order_status (name) values ('opened');
insert into order_status (name) values ('paid');
insert into order_status (name) values ('canceled');

insert into tour_status (name) values ('burning');
insert into tour_status (name) values ('non-burning');


insert into tour_category (name) values ('excursion');
insert into tour_category (name) values ('shopping');
insert into tour_category (name) values ('rest');

insert into user_status (name) values ('non-blocked');
insert into user_status (name) values ('blocked');

insert into role (name) values ('client');
insert into role (name) values ('admin');
insert into role (name) values ('manager');
insert into role (name) values ('guide');


insert into hotel (name, city, address, stars, admin_phone) values ('Aria Hotel', 'Budapest', 'Hercegprímás u. 5, 1051 ', 5, '380938909574');
insert into hotel (name, city, address, stars, admin_phone) values ('Ritz-Carlton Reserve', 'Ubud', 'Kabupaten Gianyar, Bali 80571', 5, '623614792777');
insert into hotel (name, city, address, stars, admin_phone) values ('Turin Palace Hotel', 'Turin', 'Via Sacchi, 8, 10128 Torino TO', 4, '39011825321');
insert into hotel (name, city, address, stars, admin_phone) values ('Bukville', 'Lviv', 'uchastok Vyshni 309', 3, '380697895357');
insert into hotel (name, city, address, stars, admin_phone) values ('Ancient Castle Park Hotel', 'Turin', '7th km of the Kyiv highway', 3, '3809357341539');


insert into user (login, password, firstname, lastname, email, phone, role_id, status_id)
values ('Nick123', 12345678, 'Nick', 'Jackson', 'Nick@gmail.com', 3809356978, 1, 1);

insert into user (login, password, firstname, lastname, email, phone, role_id, status_id)
values ('Ivan5', 'qwerty123', 'Ivan', 'Ivanov', 'ivanov@gmail.com', 3809356531,  1, 1);

insert into user (login, password, firstname, lastname, email, phone, role_id, status_id)
values ('Alex5', 132465798, 'Alexey', 'Andreev', 'alex@gmail.com', 38093569111, 1, 1);

insert into user (login, password, firstname, lastname, email, phone, role_id, status_id)
values ('Anton', 555999333, 'Anton', 'Shvachko', 'admin@gmail.com', 3809355555, 2, 1);

insert into user (login, password, firstname, lastname, email, phone, role_id, status_id)
values ('Anatoliy', 111222333, 'Anatoliy', 'Gunko', 'tolik.gunko@gmail.com', 380938909084, 3, 1);

insert into user (login, password, firstname, lastname, email, phone, role_id, status_id)
values ('Sergiy', 111222333, 'Sergiy', 'Nickolskiy', 'nickol@gmail.com', 380938909085, 4, 1);




insert into tour (name, country,  price, max_tickets, taken_tickets, start_date, end_date, category_id,
                  status_id, hotel_id, city) values (
                                                        'private full day tour', 'Ukraine', 5000, 15, 8, '2021-04-05', '2021-04-07', 3, 1,
                                                        1, 'Lviv');



insert into tour (name, country, price, max_tickets, taken_tickets, start_date, end_date, category_id,
                  status_id, hotel_id, city) values (
                                                        'hot summer tour', 'Indonesia', 10000, 20, 5, '2021-07-10', '2021-07-20', 3, 2,
                                                        4, 'Ubud');



insert into tour (name, country, price, max_tickets, taken_tickets, start_date, end_date, category_id,
                  status_id, hotel_id, city) values (
                                                        'Slavyanskyy Tur', 'Ukraine', 3000, 20, 5, '2021-03-10', '2021-03-20', 1, 1,
                                                        4, 'Lviv');




insert into tour (name, country, price, max_tickets, taken_tickets, start_date, end_date, category_id,
                  status_id, hotel_id, city) values (
                                                        'Amazing new year celebrating', 'Ukraine', 7500, 50, 45, '2021-12-30', '2022-01-5', 3, 2,
                                                        4, 'Lviv');




insert into tour (name, country, price, max_tickets, taken_tickets, start_date, end_date, category_id,
                  status_id, hotel_id, city) values (
                                                        'Exciting weekends with all family', 'Italy', 1000, 15, 3, '2021-11-30', '2022-12-3', 3, 2,
                                                        3, 'Turin');




insert into tour_order (date, tour_id, status_id, client_id, price) values ('2021-03-15', 1, 1, 3, 4700);
insert into tour_order (date, tour_id, status_id, client_id, price) values ('2021-06-10', 3, 2, 1, 3520);
insert into tour_order (date, tour_id, status_id, client_id, price) values ('2021-01-27', 4, 3, 3, 3520);
insert into tour_order (date, tour_id, status_id, client_id, price) values ('2021-09-10', 1, 2, 4, 7500);
insert into tour_order (date, tour_id, status_id, client_id, price) values ('2021-11-17', 2, 1, 2, 1000);

insert into discount (percent, max_percent) values (3, 10);
