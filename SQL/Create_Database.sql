--create database pokepipeline

create table users (
	userid serial primary key,
	username varchar(255) unique
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