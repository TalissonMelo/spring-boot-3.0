set foreign_key_checks = 0;

delete from escola;
delete from professor;

set foreign_key_checks = 1;

alter table escola auto_increment = 1;
alter table professor auto_increment = 1;

insert into escola (nome) values ("Escola U.A.");
insert into escola (nome) values ("Escola Ketsubutsu");
insert into escola (nome) values ("Escola Shiketsu");
insert into escola (nome) values ("Escola Isamu");
insert into escola (nome) values ("Pro Hero");

insert into professor (nome, numero, individualidade, nome_heroi, id_escola) values ("Toshinori Yagi", 1 ,"Individualidade One For All", "All Might", 1);
insert into professor (nome, numero, individualidade, nome_heroi, id_escola) values ("Shota Aizawa", 2, "Anula a individualidade de qualquer pessoa ao olhar para ela", "Eraser Head", 1);
insert into professor (nome, numero, individualidade, nome_heroi, id_escola) values ("All For One", 1 ,"Individualidade All For One", "All For One", 5);


