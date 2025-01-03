-- Create Sequence
CREATE SEQUENCE CAR_SEQ
    INCREMENT BY 1
	START WITH 1
	MAXVALUE 999999999999999
	MINVALUE 1
	NOCYCLE
	NOCACHE;


-- Create Car Table
CREATE TABLE CAR (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  make VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL,
  year_built VARCHAR(255) NOT NULL,
  vin VARCHAR(255) NOT NULL UNIQUE,
  is_active BOOLEAN NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO CAR (make, model, year_built, vin, is_active) VALUES
 ('Toyota', 'Camry', 2020, '1HGCM82633A123456', true),
 ('Honda', 'Civic', 2019, '2HGFB2F50DH123456', true),
 ('Ford', 'Mustang', 2021, '1FAFP404X1F123456', true),
 ('Chevrolet', 'Malibu', 2018, '1G1ZC5ST2JF123456', true),
 ('Tesla', 'Model 3', 2022, '5YJ3E1EA2LF123456', true),
 ('BMW', '3 Series', 2020, 'WBA8A9C50GK123456', true),
 ('Audi', 'A4', 2019, 'WAUFFAFL3GN123456', true),
 ('Mercedes-Benz', 'C-Class', 2021, 'WDDGF8BB3DR123456', true),
 ('Volkswagen', 'Jetta', 2018, '3VWD17AJ5FM123456', true),
 ('Nissan', 'Altima', 2022, '1N4AL3AP4JC123456', true);