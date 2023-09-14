
create table medicos(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    rfc varchar(10) not null unique,
    especialidad varchar(100) not null,
    calle varchar(100) not null,
    colonia varchar(100) not null,
    sudnumero varchar(100),
    numero varchar(20),
    ciudad varchar(100) not null,

    primary key(id)

);
