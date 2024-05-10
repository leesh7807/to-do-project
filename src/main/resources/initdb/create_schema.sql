CREATE TABLE IF NOT EXISTS `users` (
    `user_id`       INT AUTO_INCREMENT PRIMARY KEY,
    `email`         VARCHAR(255) NOT NULL,
    `password`      VARCHAR(255),
    `login_method`  VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS `todos` (
    `todo_id`         INT AUTO_INCREMENT PRIMARY KEY,
    `task`            TEXT NOT NULL,
    `due_date`        DATE,
    `status`          VARCHAR(20) DEFAULT 'Pending'
);

CREATE TABLE IF NOT EXISTS `user_todo_mapping` (
    `user_id`         INT,
    `todo_id`         INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (todo_id) REFERENCES todos(todo_id),
    PRIMARY KEY (user_id, todo_id)
);