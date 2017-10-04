--Integration test also uses MySql database
--To clear the test data created by schema-mysql-test.sql
delete from store where store_name in ("S1", "S2", "S3", "S4", "S5", "S6");