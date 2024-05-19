CREATE TABLE IF NOT EXISTS `users` (
    `user_id`       BINARY(16) PRIMARY KEY COMMENT 'user id, UUID',
    `email`         VARCHAR(255) NOT NULL COMMENT 'email address',
    `password`      VARCHAR(255) COMMENT 'crypted password',
    `login_method`  VARCHAR(20) NOT NULL COMMENT 'email or oauth'
);

CREATE TABLE IF NOT EXISTS `todos` (
    `todo_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT 'todo id',
    `owner`   VARCHAR(20) NOT NULL COMMENT 'todo owner'
);

CREATE TABLE IF NOT EXISTS `users_todos` (
    `user_id`       BINARY(16) NOT NULL COMMENT 'user id foreign key',
    `todo_id`       INT NOT NULL COMMENT 'todo id foreign key',
    FOREIGN KEY (user_id) REFERENCES `users` (user_id),
    FOREIGN KEY (todo_id) REFERENCES `todos` (todo_id),
    PRIMARY KEY (user_id, todo_id)
);

CREATE TABLE IF NOT EXISTS `todo_items` (
    `item_id`       INT AUTO_INCREMENT PRIMARY KEY COMMENT 'item id in todo',
    `todo_id`       INT NOT NULL COMMENT 'todo id foreign key',
    `exp`           DATETIME COMMENT 'expiry datetime',
    `priority`      INT NOT NULL COMMENT 'set position in list by priority',
    `content`       TEXT COMMENT 'write in todo item',
    FOREIGN KEY (todo_id) REFERENCES `todos` (todo_id)
);