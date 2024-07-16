DROP DATABASE IF EXISTS sandwich_shop;

-- Create new database
CREATE DATABASE sandwich_shop;

-- Select the new database
USE sandwich_shop;

-- Create tables
CREATE TABLE breads 
(
    bread_id INT NOT NULL AUTO_INCREMENT,
    bread_type VARCHAR(30) NOT NULL, 
    bread_price DOUBLE,
    PRIMARY KEY (bread_id)
);

CREATE TABLE meats
(
    meat_id INT NOT NULL AUTO_INCREMENT,
    meat_type VARCHAR(30) NOT NULL,
    extra_meat BOOLEAN DEFAULT NULL,
    PRIMARY KEY (meat_id)
);

CREATE TABLE cheese 
(
    cheese_id INT NOT NULL AUTO_INCREMENT,
    cheese_type VARCHAR(30) NOT NULL,
    extra_cheese BOOLEAN DEFAULT NULL,
    PRIMARY KEY (cheese_id)
);

CREATE TABLE regular_toppings
(
    topping_id INT NOT NULL AUTO_INCREMENT,
    topping_type VARCHAR(30) NOT NULL,
    PRIMARY KEY (topping_id)
);

CREATE TABLE sauces 
(
    sauce_id INT NOT NULL AUTO_INCREMENT,
    sauce_type VARCHAR(30) NOT NULL,
    PRIMARY KEY (sauce_id)
);

CREATE TABLE sides
(
    side_id INT NOT NULL AUTO_INCREMENT,
    side_type VARCHAR(30) NOT NULL,
    PRIMARY KEY (side_id)
);

CREATE TABLE drinks
(
    drink_id INT NOT NULL AUTO_INCREMENT,
    drink_type VARCHAR(30),
    PRIMARY KEY (drink_id)
);

CREATE TABLE chips
(
    chip_id INT NOT NULL AUTO_INCREMENT,
    chip_type VARCHAR(30),
    PRIMARY KEY (chip_id)
);

-- Insert data into tables
INSERT INTO breads (bread_type, bread_price) 
VALUES 
    ('White 4"', 5.50), 
    ('White 8"', 7.00), 
    ('White 12"', 8.50), 
    ('Wheat 4"', 5.50), 
    ('Wheat 8"', 7.00), 
    ('Wheat 12"', 8.50), 
    ('Rye 4"', 5.50), 
    ('Rye 8"', 7.00), 
    ('Rye 12"', 8.50), 
    ('Wrap 4"', 5.50), 
    ('Wrap 8"', 7.00), 
    ('Wrap 12"', 8.50);

INSERT INTO meats (meat_type)
VALUES 
    ('steak'), 
    ('ham'), 
    ('salami'), 
    ('roast beef'), 
    ('chicken'), 
    ('bacon');

INSERT INTO cheese (cheese_type) 
VALUES 
    ('american'), 
    ('provolone'), 
    ('cheddar'), 
    ('swiss');

INSERT INTO regular_toppings (topping_type)
VALUES 
    ('lettuce'), 
    ('peppers'), 
    ('onions'), 
    ('tomatoes'), 
    ('jalapenos'), 
    ('cucumbers'), 
    ('pickles'), 
    ('guacamole'), 
    ('mushrooms');

INSERT INTO sauces (sauce_type)
VALUES 
    ('mayo'), 
    ('mustard'), 
    ('ketchup'), 
    ('ranch'), 
    ('thousand islands'), 
    ('vinaigrette');
INSERT INTO sides (side_type) 
VALUES 
    ('au jus'), 
    ('sauce');

INSERT INTO drinks (drink_type)
VALUES 
    ('Coke'), 
    ('Sprite');

INSERT INTO chips (chip_type)
VALUES 
    ('Lays'), 
    ('Hot Fries');
    
CREATE TABLE sandwiches
(
    sandwich_id INT NOT NULL AUTO_INCREMENT,
    bread_id INT NOT NULL,
    meat_id INT NOT NULL,
    cheese_id INT,
    topping_id INT,
    sauce_id INT,
    side_id INT,
    PRIMARY KEY (sandwich_id),
    FOREIGN KEY (bread_id) REFERENCES breads(bread_id),
    FOREIGN KEY (meat_id) REFERENCES meats(meat_id),
    FOREIGN KEY (cheese_id) REFERENCES cheese(cheese_id),
    FOREIGN KEY (topping_id) REFERENCES regular_toppings(topping_id),
    FOREIGN KEY (sauce_id) REFERENCES sauces(sauce_id),
    FOREIGN KEY (side_id) REFERENCES sides(side_id)
);

CREATE TABLE orders
(
    order_id INT NOT NULL AUTO_INCREMENT,
    sandwich_id INT NOT NULL,
    chip_id INT,
    drink_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (order_id),
    FOREIGN KEY (sandwich_id) REFERENCES sandwiches(sandwich_id),
    FOREIGN KEY (chip_id) REFERENCES chips(chip_id),
    FOREIGN KEY (drink_id) REFERENCES drinks(drink_id)
);