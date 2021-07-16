DELETE FROM service.general_singlevalued_configuration WHERE (attribute_name = 'AnnLanguage');
DELETE FROM service.general_singlevalued_configuration WHERE (attribute_name = 'CC');
DELETE FROM service.general_singlevalued_configuration WHERE (attribute_name = 'CRF');
DELETE FROM service.general_singlevalued_configuration WHERE (attribute_name = 'RoutingPrefix ');
DELETE FROM service.general_singlevalued_configuration WHERE (attribute_name = 'Timeout');
DELETE FROM service.general_singlevalued_configuration WHERE (attribute_name = 'SupportedLanguages');

INSERT INTO service.general_singlevalued_configuration (attribute_name, attribute_value, attribute_desc)
VALUES ("CC", "375", "Country code prefix"),
       ("Timeout", "20", "Maximum waiting time for CRF feature"),
       ("RoutingPrefix ", "*311#", "Prefix to add for diverted calls while CRF feature"),
       ("SupportedLanguages", "Russian, English", "Service supported languages"),
       ("AnnLanguage", "Russian", "Language to play announcement"),
       ("CRF", "Active", "CRF feature activation flag");