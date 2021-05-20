create table rooms
(
    id   integer primary key auto_increment,
    room_name varchar(255) not null unique
);

create table users
(
    user_id   integer primary key auto_increment,
    user_name varchar(255) not null,
    isInside boolean not null,
    room_id integer not null,
    foreign key(room_id) references rooms(id)
);