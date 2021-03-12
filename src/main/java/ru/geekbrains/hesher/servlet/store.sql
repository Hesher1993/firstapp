create table students (id bigserial primary key, name varchar(255), score int);
insert into students (name, score) values
('Ben', 90),
('Lock', 80),
('Soer', 100);
create table products (id bigserial primary key , title varchar(255), cost double);
insert into products (title, cost) values
('Milk', 32.90),
('Bread', 48.20),
('Apple', 129.50),
('Juice', 77.60),
('Sugar', 19.90),
('Salt', 74.10),
('Chocolate', 33.30),
('Cakes', 29.60)