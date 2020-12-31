--create database pokepipeline

create table users (
	userid serial primary key,
	username varchar(255) unique not null,
	password varchar(255),
	email varchar(255) not null,
	description varchar(255),
	profilePicture bytea
);

create table item (
	itemid serial primary key,
	itemapi int4,
	userid int4 references users(userid)
);

create table pokemon (
	pokemonid serial primary key,
	currenthp int4,
	experience int4,
	move1api int4,
	move2api int4,
	move3api int4,
	move4api int4,
	pokemonapi int4,
	userid int4 references users(userid)
);

insert into users values (900, 'username', '4dUltQjwj0H2Hi8apvifJg==:sehF8cLvhpMRZAAoPNj4Eg==', 'user@email.com', '', null);