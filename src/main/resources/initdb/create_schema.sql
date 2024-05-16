CREATE TABLE IF NOT EXISTS `users` (
    `user_id`       CHAR(36) PRIMARY KEY,
    `email`         VARCHAR(255) NOT NULL,
    `password`      VARCHAR(255),
    `login_method`  VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS `todos` (
    `todo_id` INT AUTO_INCREMENT PRIMARY KEY,
    `owner`   INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `users_todos` (
    `user_id`       CHAR(36) NOT NULL,
    `todo_id`       INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES `users` (user_id),
    FOREIGN KEY (todo_id) REFERENCES `todos` (todo_id),
    PRIMARY KEY (user_id, todo_id)
);

CREATE TABLE IF NOT EXISTS `todo_items` (
    `item_id`       INT AUTO_INCREMENT PRIMARY KEY,
    `todo_id`       INT NOT NULL,
    `exp`           DATETIME,
    `priority`      INT NOT NULL,
    `content`       TEXT,
    FOREIGN KEY (todo_id) REFERENCES `todos` (todo_id)
);