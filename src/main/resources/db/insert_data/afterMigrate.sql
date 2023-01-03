set foreign_key_checks = 0;

delete from escola;

set foreign_key_checks = 1;

alter table escola auto_increment = 1;

insert into escola (nome) values ("Escola U.A.");
insert into escola (nome) values ("Escola Ketsubutsu");
insert into escola (nome) values ("Escola Shiketsu");
insert into escola (nome) values ("Escola Isamu");
insert into escola (nome) values ("Pro Hero");

