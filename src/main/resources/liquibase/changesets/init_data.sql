-- Создаем таблицу в случае её отсутствия
CREATE TABLE IF NOT EXISTS user_table (
    id bigserial primary key,
    name varchar(50) not null,
    age int not null,
    email varchar(50) not null
)