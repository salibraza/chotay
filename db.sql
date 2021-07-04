#create schema temp;
#use temp;
drop table if exists customers;
create table customers (
	id int primary key,
    name varchar(50) not null,
    age int not null,
    location varchar(50) not null
);

insert into customers (id,name,age,location) values
(1,"Moosa", 21, "G15 Islamabad"),(2,"Khilat", 22, "F11 Islamabad"),
(3,"Shehroz", 22, "F11 Islamabad"),(4,"Asil", 22, "F11 Islamabad"),
(5,"Numan", 21, "PWD Rawalpindi"),(6,"Jalil", 21, "PWD Rawalpindi");
