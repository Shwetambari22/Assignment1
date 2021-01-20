CREATE USER 'inventoryapp'@'localhost' IDENTIFIED BY 'inventoryapp';

GRANT ALL PRIVILEGES ON * . * TO 'inventoryapp'@'localhost';

ALTER USER 'inventoryapp'@'localhost' IDENTIFIED WITH mysql_native_password BY 'inventoryapp';