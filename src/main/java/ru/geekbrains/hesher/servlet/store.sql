create table category (
                         id bigserial primary key,
                         title varchar(255),
                         created_at timestamp default current_timestamp,
                         updated_at timestamp default current_timestamp,
                         deleted_at timestamp);

insert into category (id, title) values
(1, 'Drinks'),
(2, 'Food');

create table product (
    id bigserial primary key,
    title varchar(255),
    cost int,
    category_id bigserial,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    deleted_at timestamp);

insert into product (category_id, title, cost) values
(1,'Milk', 32.90),
(2,'Bread', 48.20),
(2,'Apple', 129.50),
(1,'Juice', 77.60),
(2,'Sugar', 19.90),
(2,'Salt', 74.10),
(2,'Chocolate', 33.30),
(2,'Cakes', 29.60)