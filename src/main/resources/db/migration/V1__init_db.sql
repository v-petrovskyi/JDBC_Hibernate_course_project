CREATE DATABASE IF NOT EXISTS hibernate_course_project;

USE hibernate_course_project;

CREATE TABLE user_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255) UNIQUE,
    role_description TEXT
);

CREATE TABLE profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    postal_code VARCHAR(50)
);

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY ,
  user_name VARCHAR(255) UNIQUE ,
  password VARCHAR(255),
  user_role_id BIGINT,
  profile_id BIGINT,
  FOREIGN KEY (user_role_id) REFERENCES user_role(id),
  FOREIGN KEY (profile_id) REFERENCES profile(id)
);

CREATE TABLE incident(
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    service_name VARCHAR(255),
    is_active BIT NOT NULL ,
    problem_description TEXT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);
CREATE TABLE service (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    service_name VARCHAR(255),
    is_active BIT NOT NULL ,
    service_month_price DOUBLE,
    customer_id BIGINT
);

CREATE TABLE user_services(
    service_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (service_id) REFERENCES service(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);
