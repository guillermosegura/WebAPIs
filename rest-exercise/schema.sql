CREATE TABLE order_table (
  id INT IDENTITY(1,1) PRIMARY KEY,
  clientId INT NOT NULL,
  timestamp DATETIME NOT NULL,
  total DECIMAL(10, 2) NOT NULL
);

CREATE TABLE order_detail (
  id INT IDENTITY(1,1) PRIMARY KEY,
  orderId INT NOT NULL,
  sku VARCHAR(50) NOT NULL,
  description VARCHAR(100) NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL(10, 2) NOT NULL
);

ALTER TABLE order_detail ADD FOREIGN KEY (orderId) REFERENCES order_table(id);
