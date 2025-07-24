DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'cpf') THEN
        CREATE DOMAIN cpf character(11);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'ano') THEN
        CREATE DOMAIN ano smallint CHECK (VALUE > 0 AND VALUE <= 9999);
    END IF;
END $$;
