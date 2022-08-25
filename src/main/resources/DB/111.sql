
CREATE DATABASE `mini_project` ;


DROP TABLE IF EXISTS `articles`;

CREATE TABLE `articles` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `content` varchar(256) NOT NULL,
  `user_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `KEY` (`user_id`),
  CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comments` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(256) NOT NULL,
  `author_id` int unsigned NOT NULL,
  `article_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `KEY` (`author_id`),
  KEY `KEY1` (`article_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `surname` varchar(256) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(256) NOT NULL,
  `age` int NOT NULL DEFAULT '0',
  `avatar` varchar(256) DEFAULT 'NULL',
  `gender` enum('FEMALE','MALE','OTHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'OTHER',
  `phone_number` varchar(256) DEFAULT 'NULL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



insert  into `users`(`id`,`name`,`surname`,`email`,`password`,`age`,`avatar`,`gender`,`phone_number`) values (1,'Ofelya','Nazaryan','ofelya.nazaryan7777@gmail.com','19967777',81,'avatar.png','FEMALE','+37491168870'),(2,'Karine','Badoyan','karine.badoyan@hotmail.ru','99887766',54,'avatar.png','FEMALE','+374555444'),(3,'Vahe','Pochukyan','vahe.pochukyan@gmail.com','4*4*4*4*4',18,'avatar.png','MALE','+3745551444'),(5,'Anna','Petrosyan','ann.petrosyan@gmail.com','123456',18,'avatar.png','FEMALE','+374549474');


