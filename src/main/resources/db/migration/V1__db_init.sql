
CREATE TABLE IF NOT EXISTS person
(
    id uuid NOT NULL DEFAULT uuidv7(),
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS account
(
    balance double precision,
    id uuid NOT NULL DEFAULT uuidv7(),
    person_id uuid NOT NULL,
    bank_provider character varying(255) COLLATE pg_catalog."default",
    card_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
    iban character varying(255) COLLATE pg_catalog."default" NOT NULL,
    pin character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id),
    CONSTRAINT account_iban_key UNIQUE (iban),
    CONSTRAINT account_person_fkey FOREIGN KEY (person_id)
        REFERENCES person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT account_bank_provider_check CHECK (bank_provider::text = ANY (ARRAY['PKO'::character varying, 'MILLENNIUM'::character varying, 'REVOLUT'::character varying]::text[]))
);
CREATE TABLE IF NOT EXISTS legacy_payment
(
    id UUID NOT NULL DEFAULT uuidv7(),
    person_from_name VARCHAR(255)                NOT NULL,
    person_to_name   VARCHAR(255)                NOT NULL,
    amount           DOUBLE PRECISION            NOT NULL,
    payment_date     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_legacy_payment PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS person_accounts
(
    accounts_id uuid NOT NULL,
    person_id uuid NOT NULL,
    CONSTRAINT person_accounts_accounts_id_key UNIQUE (accounts_id),
    CONSTRAINT fkh2swcedbloy7oshglbs58wmxx FOREIGN KEY (person_id)
        REFERENCES person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkig022iida5w33h1xoala4a933 FOREIGN KEY (accounts_id)
        REFERENCES account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS payment
(
    amount double precision NOT NULL,
    payment_date timestamp(6) without time zone NOT NULL,
    id uuid NOT NULL,
    source_account uuid NOT NULL,
    target_account uuid NOT NULL,
    status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT payment_pkey PRIMARY KEY (id),
    CONSTRAINT payment_status_check CHECK (status::text = ANY (ARRAY['ACCEPTED'::character varying, 'IN_PROGRESS'::character varying, 'REJECTED'::character varying]::text[]))
);

INSERT INTO person(id, first_name, last_name)
VALUES
    ('01a095f3-b9d4-7542-bb01-32071a7e031f', 'John', 'Doe'),
    ('01a095f3-b9d6-7742-90b4-1c3eb0060f12', 'Emily', 'Blunt');
INSERT INTO account(id, person_id, bank_provider, card_number, pin, iban, balance)
VALUES
    ('01a095fe-d545-7ad0-a15c-0ec8546cf1f0','01a095f3-b9d4-7542-bb01-32071a7e031f', 'MILLENNIUM', '12345678909876', encode('1234'::bytea, 'base64') ,'PL64109024028851119733719366', 500.0),
    ('01a095fe-d551-7fee-9b3e-1c70c13a2d95', '01a095f3-b9d6-7742-90b4-1c3eb0060f12', 'MILLENNIUM', '09876543212345', encode('0987'::bytea, 'base64'), 'PL49109024027131869268519927', 900.0)

