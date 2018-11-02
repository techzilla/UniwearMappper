CREATE TABLE IF NOT EXISTS products(
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
  price decimal(6, 2) NULL,
  map decimal(6, 2) NULL,
  weight decimal(6, 2) NULL,
  manufacturer varchar(256) NULL,
  brand varchar(256) NULL,
  collection varchar(256) NULL,
  callouts varchar(256) NULL,
  title text NULL,
  description text NULL,
  image text NULL,
  PRIMARY KEY (id)
) ENGINE=Aria;

CREATE TABLE IF NOT EXISTS supplierinventory(
  id int NOT NULL AUTO_INCREMENT,
  gtin varchar(14) NULL,
  supplier varchar(256) NULL,
  price decimal(6, 2) NULL,
  stock int NULL,
  PRIMARY KEY (id)
) ENGINE=Aria;

