CREATE TABLE products(
  id int NOT NULL AUTO_INCREMENT,
  sku varchar(64) NULL,
  gtin varchar(14) NULL,
  type varchar(256) NULL,
  color varchar(64) NULL,
  color_base varchar(64) NULL,
  color_type varchar(64) NULL,
  size varchar(64) NULL,
  gender varchar(64) NULL,
  fit varchar(64) NULL,
  price decimal(4, 2) NULL,
  map decimal(4, 2) NULL,
  weight decimal(4, 2) NULL,
  brand varchar(256) NULL,
  collection varchar(256) NULL,
  callouts varchar(256) NULL,
  title text NULL,
  description text NULL,
  image text NULL,
  PRIMARY KEY (id)
);

CREATE TABLE supplierinventory(
  id int NOT NULL AUTO_INCREMENT,
  gtin varchar(14) NULL,
  supplier varchar(256) NULL,
  price decimal(4, 2) NULL,
  stock int NULL,
  PRIMARY KEY (id)
);