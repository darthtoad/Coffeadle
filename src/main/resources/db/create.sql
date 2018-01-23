SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS cafes (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    address VARCHAR,
    zip VARCHAR,
    phone VARCHAR,
    website VARCHAR,
    email VARCHAR,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS foodtypes (
    id int PRIMARY KEY auto_increment,
    name VARCHAR
);

CREATE TABLE IF NOT EXISTS cafes_foodtypes (
    id int PRIMARY KEY auto_increment,
    foodtypeid INTEGER,
    cafeid INTEGER
);

CREATE TABLE IF NOT EXISTS reviews (
    id int PRIMARY KEY auto_increment,
    writtenby VARCHAR,
    rating VARCHAR,
    cafeid INTEGER,
    kanttent VARCHAR
);