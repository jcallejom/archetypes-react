CREATE OR REPLACE FUNCTION GENERATE_UUID_V4 RETURN VARCHAR2 IS
  raw_guid RAW(16);
BEGIN
  -- Genera un GUID en formato RAW (16 bytes)
  raw_guid := SYS_GUID();

  -- Convierte el RAW a HEX y luego le añade los guiones en el formato UUID estándar (V4)
  RETURN
    LOWER(SUBSTR(RAWTOHEX(raw_guid), 1, 8) || '-' ||
          SUBSTR(RAWTOHEX(raw_guid), 9, 4) || '-' ||
          SUBSTR(RAWTOHEX(raw_guid), 13, 4) || '-' ||
          SUBSTR(RAWTOHEX(raw_guid), 17, 4) || '-' ||
          SUBSTR(RAWTOHEX(raw_guid), 21, 12));
END;



CREATE TABLE purchase_order (
    id VARCHAR2(36) DEFAULT GENERATE_UUID_V4() PRIMARY KEY,
    user_id INT,
    product_id INT,
    price NUMBER(10, 2),
    status VARCHAR(50)
);
CREATE TABLE client_order (
    id VARCHAR2(36) DEFAULT GENERATE_UUID_V4() PRIMARY KEY,
    client_id VARCHAR(12),
    user_id VARCHAR(12),
    card_id VARCHAR(12),
    status VARCHAR(50)
);
