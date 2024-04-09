##### JDK18
##### Nhập Username, Password trong mysql trong resources/application.properties
```agsl
CREATE DATABASE bhxh;

CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    citizen VARCHAR(255),
    fullname VARCHAR(255),
    phoneNumber VARCHAR(255),
    birthday VARCHAR(255),
    domestic VARCHAR(255),
    email VARCHAR(255),
    sex INT
);

INSERT INTO user (username, password, citizen, fullname, phoneNumber, birthday, domestic, email, sex)
VALUES ('ducanh235', 'ducanh235', '0012020398765', 'Nguyễn Đức Anh', '0987888802', '30-10-2002', 'HaNoi', 'qlda@gmail.com', 0),
       ('quang', 'quang', '001202037840', 'Nguyễn Viết Quang', '0916542333', '15-10-2002', 'HaNoi', 'qldav2@gmail.com', 1);

```
