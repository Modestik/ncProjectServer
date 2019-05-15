--тестовые данные
INSERT INTO users
VALUES ('admin', '$2a$10$iVX.9qlpk0m8OwuXiOcBW.h/YonJeBwG6To6of1sGd/DlymimW34q', 'ADMIN');
INSERT INTO users
VALUES ('customer', '$2a$10$R5hRT15dh9h/qv32MG75QuZh1TDeUkidlfvmaPHsNYD9JJoA1RlXW', 'CUSTOMER');
INSERT INTO users
VALUES ('nikita', '$2a$10$R5hRT15dh9h/qv32MG75QuZh1TDeUkidlfvmaPHsNYD9JJoA1RlXW', 'CUSTOMER');
INSERT INTO users
VALUES ('operator', '$2a$12$5ZvE6t13B4zJFQowhoJbl.YOJAV/wyOOH.OTFRaaIn9QhFdJJ6qc6', 'OPERATOR');
INSERT INTO users
VALUES ('driver', '$2a$10$urnyDT8dd9YR9.or2RY9VuOmEoxxd5Um.DmNNBWqk7Qwxk3p4pyaC', 'DRIVER');
INSERT INTO customers (username, first_name, last_name, phone_number)
VALUES ('customer', 'customer', 'customer', '+7(920)440-02-32');
INSERT INTO customers (username, first_name, last_name, phone_number)
VALUES ('nikita', 'nikita', 'nikita', '+7(920)440-02-32');

INSERT INTO operators (username, first_name, last_name, phone_number)
VALUES ('operator', 'operator', 'operator', '+7(920)440-02-32');
INSERT INTO orders (customer, point_from, point_to, cost, start_time, end_time, status, driver, description)
VALUES ('customer', 'Russia, Voronezh, Krasnoarmeysky, 50', 'Russia, Voronezh, Moskovsky prospect, 150', 11.01, '2019-3-19 15:13', null, 'CLOSED', null,
        'my number 555-111-111');
INSERT INTO orders (customer, point_from, point_to, cost, start_time, end_time, status, driver, description)
VALUES ('customer', 'Russia, Voronezh, Dimitrova, 10', 'Russia, Voronezh, Perevertkina, 42', 11.01, '2019-3-21 15:13', null, 'CLOSED', null, null);

INSERT INTO orders (customer, point_from, point_to, cost, start_time, end_time, status, driver, description)
VALUES ('nikita', 'Russia, Voronezh, Dimitrova, 10', 'Russia, Voronezh, Krasnoarmeysky, 50', 11.01, '2019-3-21 15:13', null, 'CLOSED', null, null);


INSERT INTO cars
VALUES ('d777ri36ru', 'lada', 'red');

INSERT INTO drivers
VALUES ('driver', 'dri', 'ver', '777777', 'd777ri36ru', 'test');
