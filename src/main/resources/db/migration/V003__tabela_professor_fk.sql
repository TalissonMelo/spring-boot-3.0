alter table professor add constraint fk_escola
foreign key (id_escola) references escola (id);