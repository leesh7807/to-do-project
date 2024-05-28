CREATE TABLE IF NOT EXISTS `users` (
    `user_id`       BINARY(16) PRIMARY KEY COMMENT 'user id, UUID',
    `email`         VARCHAR(255) NOT NULL COMMENT 'email address',
    `password`      VARCHAR(255) COMMENT 'crypted password',
    `login_method`  VARCHAR(20) NOT NULL COMMENT 'email or oauth'
);

CREATE TABLE IF NOT EXISTS `todos` (
    `todo_id`       BINARY(16) PRIMARY KEY COMMENT 'todo id, UUID',
    `user_id`       BINARY(16) NOT NULL COMMENT 'user_id foreign key',
    `title`         VARCHAR(255) NOT NULL COMMENT 'title of todo',
    `owner`         VARCHAR(20) NOT NULL COMMENT 'todo owner',
    `priority`      INT NOT NULL COMMENT 'set position in todos',
    FOREIGN KEY (user_id) REFERENCES `users` (user_id)
);

CREATE TABLE IF NOT EXISTS `todo_items` (
    `item_id`       BINARY(16) PRIMARY KEY COMMENT 'item id in todo, UUID',
    `todo_id`       BINARY(16) NOT NULL COMMENT 'todo id foreign key',
    `status`        VARCHAR(20) NOT NULL COMMENT 'is completed or pending?',
    `exp`           DATETIME COMMENT 'expiry datetime',
    `priority`      INT NOT NULL COMMENT 'set position in list',
    `content`       TEXT COMMENT 'write in todo item',
    FOREIGN KEY (todo_id) REFERENCES `todos` (todo_id)
);