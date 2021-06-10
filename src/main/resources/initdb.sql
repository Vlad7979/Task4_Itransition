CREATE TABLE `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `email` varchar(255) NOT NULL,
    `regDate` DATE NOT NULL,
    `lastLogIn` DATE NOT NULL,
    `status` BOOLEAN NOT NULL,
    `password` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
)