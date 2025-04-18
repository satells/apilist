SELECT 'CREATE DATABASE apilist'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'apilist')\gexec