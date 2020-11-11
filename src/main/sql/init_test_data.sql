INSERT INTO product_category (name, description, department) VALUES ('category a', 'dep a', 'test category a');
INSERT INTO product_category (name, description, department) VALUES ('category b', 'dep b', 'test category b');

INSERT INTO supplier (name, description) VALUES ('supplier a', 'test supplier a');
INSERT INTO supplier (name, description) VALUES ('supplier b', 'test supplier b');

INSERT INTO product (name, description, price, currency, category_id, supplier_id) VALUES ('Product a', 'test1', 49.9, 'USD', 1, 1);
INSERT INTO product (name, description, price, currency, category_id, supplier_id) VALUES ('Product b', 'test2', 49.9, 'USD', 2, 2);
