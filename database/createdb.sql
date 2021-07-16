CREATE DATABASE IF NOT EXISTS service
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS service.general_singlevalued_configuration (
    attribute_name  VARCHAR(255) NOT NULL,
    attribute_value VARCHAR(1024) NOT NULL,
    attribute_desc  VARCHAR(500) NOT NULL DEFAULT '-',
    PRIMARY KEY (attribute_name)
    )
    DEFAULT CHARACTER SET = latin1;