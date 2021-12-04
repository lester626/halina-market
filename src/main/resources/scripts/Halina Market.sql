USE inventory;

CREATE TABLE users(
user_id INT NOT NULL AUTO_INCREMENT,
email VARCHAR(55) NOT NULL,
user_password VARCHAR(150) NOT NULL,
user_first_name VARCHAR(20) NOT NULL,
user_last_name VARCHAR(20) NOT NULL,
user_image BLOB,
accept_email TINYINT NOT NULL,
user_role VARCHAR(15) NOT NULL,
country VARCHAR(55) NOT NULL, 
city VARCHAR(55) NOT NULL, 
street VARCHAR(100) NOT NULL, 
zip_code INT NOT NULL,
PRIMARY KEY (user_id)
);
CREATE TABLE delivery_address(
del_address_id INT NOT NULL AUTO_INCREMENT,
country VARCHAR(20) NOT NULL,
city VARCHAR(20) NOT NULL,
street VARCHAR(55) NOT NULL,
zipcode INT NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY (del_address_id)
);
CREATE TABLE products(
product_id INT NOT NULL AUTO_INCREMENT,
product_name VARCHAR(500) NOT NULL,
product_desc VARCHAR(5000) NOT NULL,
image_url VARCHAR(10000) NOT NULL,
availability TINYINT NOT NULL,
price FLOAT NOT NULL,
category_id INT NOT NULL,
subcategory_id INT NOT NULL,
PRIMARY KEY (product_id)
);
CREATE TABLE category(
category_id INT NOT NULL AUTO_INCREMENT,
category_name VARCHAR(50) NOT NULL,
PRIMARY KEY (category_id)
);
CREATE TABLE subcategory(
subcategory_id INT NOT NULL AUTO_INCREMENT,
subcategory_name VARCHAR(50) NOT NULL,
category_id INT NOT NULL,
PRIMARY KEY (subcategory_id)
);
CREATE TABLE order_lines(
order_line_id INT NOT NULL AUTO_INCREMENT,
total_products INT NOT NULL,
product_price FLOAT,
product_id INT NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY (order_line_id)
);
CREATE TABLE orders(
order_id INT NOT NULL AUTO_INCREMENT,
total_cost FLOAT NOT NULL,
order_date DATE NOT NULL,
order_status VARCHAR(15) NOT NULL,
order_line_id INT NOT NULL,
user_id INT NOT NULL,
del_address_id INT NOT NULL,
product_id INT NOT NULL,
PRIMARY KEY (order_id)
);
ALTER TABLE orders
ADD CONSTRAINT orders_order_line_id_fk
FOREIGN KEY (order_line_id) REFERENCES order_lines(order_line_id);
ALTER TABLE orders
ADD CONSTRAINT orders_user_id_fk
FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE orders
ADD CONSTRAINT orders_product_id_fk
FOREIGN KEY (product_id) REFERENCES products(product_id);
ALTER TABLE orders
ADD CONSTRAINT orders_del_address_id_fk
FOREIGN KEY (del_address_id) REFERENCES delivery_address(del_address_id);
ALTER TABLE delivery_address
ADD CONSTRAINT delivery_address_user_id_fk
FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE products
ADD CONSTRAINT products_category_id_fk
FOREIGN KEY (category_id) REFERENCES category(category_id);
ALTER TABLE products
ADD CONSTRAINT products_subcategory_id_fk
FOREIGN KEY (subcategory_id) REFERENCES subcategory(subcategory_id);
ALTER TABLE subcategory
ADD CONSTRAINT subcategory_category_id_fk
FOREIGN KEY (category_id) REFERENCES category(category_id);
ALTER TABLE order_lines
ADD CONSTRAINT order_lines_product_id_fk
FOREIGN KEY (product_id) REFERENCES products(product_id);
ALTER TABLE order_lines
ADD CONSTRAINT order_lines_user_id_fk
FOREIGN KEY (user_id) REFERENCES users(user_id);

SELECT * FROM category;
SELECT * FROM delivery_address;
SELECT * FROM order_lines;
SELECT * FROM orders;
SELECT * FROM products;
SELECT * FROM subcategory;
SELECT * FROM users;

ALTER TABLE orders
DROP FOREIGN KEY orders_order_line_id_fk;
ALTER TABLE orders
DROP COLUMN order_line_id;
ALTER TABLE orders
DROP FOREIGN KEY orders_product_id_fk;
ALTER TABLE orders
DROP COLUMN product_id;

CREATE TABLE order_line_holder(
order_holder_id INT NOT NULL AUTO_INCREMENT,
total_products INT NOT NULL,
product_price FLOAT NOT NULL,
product_id INT NOT NULL,
order_id INT,
PRIMARY KEY (order_holder_id));

ALTER TABLE order_line_holder
ADD CONSTRAINT holder_order_fk FOREIGN KEY (order_id) REFERENCES orders(order_id);
ALTER TABLE order_line_holder
ADD CONSTRAINT holder_product_fk FOREIGN KEY (product_id) REFERENCES products(product_id);
