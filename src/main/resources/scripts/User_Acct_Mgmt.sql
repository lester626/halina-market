CREATE USER 'halinamarket'@'localhost' IDENTIFIED BY 'halinamarket';

create database inventory;

GRANT ALL ON inventory.* to 'halinamarket'@'localhost'