-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER GradeBook_owner
WITH PASSWORD 'gradebook';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO GradeBook_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO GradeBook_owner;

CREATE USER final_capstone_appuser
WITH PASSWORD 'gradebook';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO GradeBook_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO GradeBook_appuser;
