CREATE TABLE usuarios(

    id BIGINT NOT NULL auto_increment,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL,

    primary key(id)
);