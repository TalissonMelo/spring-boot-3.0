create table professor (
	id bigint not null auto_increment, 
	nome varchar(80) not null,
	numero bigint not null,
	individualidade varchar(80) not null,
	nome_heroi varchar(80) not null,
	id_escola bigint not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;