CREATE TABLE cliente (
	id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
	nombre VARCHAR(255)
);
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO cliente (nombre) VALUES ('sebastian');
INSERT INTO cliente (nombre) VALUES ('joel');

CREATE TABLE producto (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    nombre VARCHAR(255),
    precio DECIMAL(10, 2)
);



