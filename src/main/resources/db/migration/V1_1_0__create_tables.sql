create table flat_seq (next_val bigint) engine=InnoDB;
insert into flat_seq values ( 1 );

create table floor_seq (next_val bigint) engine=InnoDB;
insert into floor_seq values ( 1 );

create table house_seq (next_val bigint) engine=InnoDB;
insert into house_seq values ( 1 );

create table street_seq (next_val bigint) engine=InnoDB;
insert into street_seq values ( 1 );

create table flat (id bigint not null, floor_id integer not null, number_of_flat integer not null, number_of_lodger integer not null, number_of_room integer not null, square_of_flat float(53) not null, primary key (id)) engine=InnoDB;

create table floor (id integer not null, house_id integer not null, number_of_flats integer not null, number_of_floor integer not null, primary key (id)) engine=InnoDB;

create table house (id integer not null, number_of_house integer not null, street_id integer not null, value_of_floors integer not null, primary key (id)) engine=InnoDB;

create table street (id integer not null, name varchar(255), primary key (id)) engine=InnoDB;
