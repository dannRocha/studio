create table if not exists artista (
  id int auto_increment primary key,
  nacionalidade varchar(255),
  nome varchar(255)
);

create table if not exists album (
  id int auto_increment primary key,
  ano integer,
  duracao integer default 0,
  nome varchar(255)
);

create table if not exists musica (
  id int auto_increment primary key,
  duracao integer,
  nome varchar(255)
);

create table if not exists album_musica (
  album_id int not null,
  musica_id int not null,
  foreign key (album_id) references album(id),
  foreign key (musica_id) references musica(id)
);

create table if not exists artista_album (
  artista_id int not null,
  album_id int not null,
  foreign key (album_id) references album(id),
  foreign key (artista_id) references artista(id)
);

create table if not exists artista_musica (
  artista_id int not null,
  musica_id int not null,
  foreign key (artista_id) references artista(id),
  foreign key (musica_id) references musica(id)
);

create table if not exists musica_interprete (
  musica_id int not null,
  interprete_id int not null,
  foreign key (interprete_id) references artista(id),
  foreign key (musica_id) references musica(id)
);
