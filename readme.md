##### JDK18
##### Nhập Username, Password trong mysql trong resources/application.properties
##### URL: localhost:8080

```mysql
INSERT INTO bhxh.bhxh_agency_entity (id, address, display_name) VALUES (1, 'Ha Dong, Ha Noi', 'BHXH Quận Hà Đông');
INSERT INTO bhxh.user_entity (id, base_salary, bhxh_id, fullname, password, role, type, bhxh_agency_id) VALUES (1, 1000000, '1', 'Nguyen Viet Quang', '1', 1, 2, 1);
INSERT INTO bhxh.user_entity (id, base_salary, bhxh_id, fullname, password, role, type, bhxh_agency_id) VALUES (2, null, 'admin', null, 'admin', 0, null, null);
INSERT INTO bhxh.bhxh_invoice_entity (id, base_salary, deducted_amount, end_date, interest_amount, start_date, status, total_amount, user_id) VALUES (1, 1000000, 33000, '2024-05-09', 100000, '2024-02-15', 0, 1, 1);

```
