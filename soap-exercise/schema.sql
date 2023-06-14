-- Crear la tabla Player
CREATE TABLE Player (
  id INT PRIMARY KEY,
  name NVARCHAR(255),
  race NVARCHAR(255),
  playerClass NVARCHAR(255),
  level INT,
  hitPoints INT,
  strength INT,
  dexterity INT,
  constitution INT,
  intelligence INT,
  wisdom INT,
  charisma INT
);