DROP SCHEMA IF EXISTS quotes;
CREATE SCHEMA quotes;
CREATE USER if not exists 'adm'@'localhost' IDENTIFIED BY 'adm';
GRANT ALL PRIVILEGES ON quotes.* TO 'adm'@'localhost';
USE quotes
CREATE TABLE quotes (
    id integer auto_increment,
    author VARCHAR(20) ,
    citation VARCHAR(120),
    submitter VARCHAR(20) ,
    PRIMARY KEY(id)
);
insert into quotes values (1,'Dr. Seuss', 'Don''t cry because it''s over, smile because it happened.','Pierre');
insert into quotes values (2,'Oscar Wilde', 'Be yourself; everyone else is already taken.','Sophie');
insert into quotes values (3,'Albert Einstein', 'Two things are infinite: the universe and human stupidity; and I''m not sure about the universe.','Pierre');
select * from quotes;

