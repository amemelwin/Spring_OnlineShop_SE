--db name = 'order_system'
Drop Table If Exists roles;
Drop Table If Exists users;
Drop Table If Exists divisions;
Drop Table If Exists catagories;
Drop Table If Exists items;
Drop Table If Exists orders;
Drop Table If Exists order_details;
CREATE TABLE roles (
	id serial PRIMARY KEY,
	name VARCHAR ( 50 ) UNIQUE NOT NULL,
	created_at TIMESTAMP Default now(),
    updated_at TIMESTAMP Default now()
);
Create Table users (
	id SERIAL NOT NULL,
	username varchar(50) NOT NULL,
	email varchar(199) unique NOT NULL,
	password text NOT NULL,
	address text NOT NULL,
	role_id int,
	created_at timestamp Default now(),
	updated_at timestamp Default now(), 	
	PRIMARY KEY(id)
);
Create Table divisions (
	id SERIAL NOT NULL,
	name varchar(50) NOT NULL,
	created_at timestamp Default now(),
	updated_at timestamp Default now(),
	PRIMARY KEY(id)
);
Create Table orders (
	id SERIAL NOT NULL,
	user_id int,
 	division_id int,
	created_at timestamp Default now(),
	updated_at timestamp Default now(), 	
	PRIMARY KEY(id)
);
Create Table order_details (
	id SERIAL NOT NULL,
	quantity int NOT NULL,
	order_id int,
 	item_id int,
	created_at timestamp Default now(),
	updated_at timestamp Default now(), 	
	PRIMARY KEY(id)
);
Create Table catagories (
	id SERIAL NOT NULL,
	name varchar(50) NOT NULL,
	created_at timestamp Default now(),
	updated_at timestamp Default now(),
	PRIMARY KEY(id)
);
	Create Table items (
	id SERIAL NOT NULL,
	name varchar(50) NOT NULL,
	 price int NOT NULL,
	photo_url text NOT NULL,
	category_id int,
	created_at timestamp Default now(),
	updated_at timestamp Default now(), 	
	PRIMARY KEY(id)
);

-- simple data
INSERT INTO roles(name)
VALUES
('admin'),
('customer');

INSERT INTO users(username,email,password,address)
VALUES
('Ma Ma','mama@gmail.com','1234','Hlaing Thar Yar'),
('Mg Mg','mgmg@gmail.com','1234','Inn Sein');

INSERT INTO divisions(name)
VALUES
('Hlaing Thar Yar'),
('Inn Sein'),
('Hlaing'),
('San Chaung');

INSERT INTO catagories(name)
VALUES
('cosmetic'),
('mobile');

INSERT INTO orders(user_id,division_id)
VALUES
(1,2),
(2,3);

INSERT INTO items(name,price,photo_url,category_id)
VALUES
('Catr. Demi Matt Lipstick 030', 19500,'cosmetic3.jpg',1),
('makeup',20000,'cosmetic2.jpg',1),
('lipstick',8900,'cosmetic3.jpg',1),
('Samsung A23',900000,'photo4.jpg',2);

INSERT INTO order_details(quantity,order_id,item_id)
VALUES 
(2,1,2),
(5,1,1),
(1,1,3),
(10,2,1),
(1,2,3);


-- select  o.created_at::timestamp::date date,i.name,sum(i.price*d.quantity) total
-- from orders o ,order_details d,items i
-- where o.id = d.order_id and i.id = d.item_id and o.id =1
-- group by i.name, o.created_at
select  o.created_at::timestamp::date date,i.name,sum(i.price*d.quantity) total
from orders o ,order_details d,items i
where o.id = d.order_id and i.id = d.item_id and o.id =2
group by i.name, o.created_at

SELECT  o.created_at::timestamp::date date,i.name,d.quantity,i.price,i.price*d.quantity cost
FROM orders o ,order_details d,items i
WHERE o.id = d.order_id AND i.id = d.item_id AND o.id =1

SELECT od.order_id,od.date, SUM(od.qty* i.price) "total" 
FROM
	(SELECT o.id "order_id",o.created_at::timestamp::date "date",d.quantity "qty",d.item_id FROM orders o
	LEFT JOIN order_details d
	ON o.id = d.order_id
	WHERE o.user_id = 1) od
LEFT JOIN items i
ON i.id=od.item_id
GROUP BY od.order_id,od.date
