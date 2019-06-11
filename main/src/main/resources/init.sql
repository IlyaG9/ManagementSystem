CREATE DATABASE `managment` CHARACTER SET utf8 COLLATE utf8_general_ci;
GRANT ALL ON `managment`.* TO `root`@localhost IDENTIFIED BY 'root';

 insert into sensor (id,name,url,CREATE_DATE) value(1,'hall','http://192.168.0.196/',SYSDATE());
  INSERT INTO sensor_param (ID,SENSOR_ID,NAME) values(1,1,'hall');