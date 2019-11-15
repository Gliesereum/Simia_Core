CREATE TABLE account.user
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    first_name character varying,
    last_name character varying,
    gender character varying,
    ban_status character varying,
    address character varying,
    avatar_url character varying,
    cover_url character varying,
    last_sign_in TIMESTAMP without time zone,
    last_activity TIMESTAMP without time zone,
    create_date TIMESTAMP without time zone,
    update_date TIMESTAMP without time zone,
    delete_date TIMESTAMP without time zone,
    object_state character varying,

    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE account.user_email
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    email character varying,
    user_id uuid,

    CONSTRAINT user_email_pk PRIMARY KEY (id),
    CONSTRAINT user_email_user_fk FOREIGN KEY (user_id) REFERENCES account.user (id)
);

CREATE TABLE account.user_phone
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    phone character varying,
    user_id uuid,

    CONSTRAINT user_phone_pk PRIMARY KEY (id),
    CONSTRAINT user_phone_user_fk FOREIGN KEY (user_id) REFERENCES account.user (id)
);

CREATE TABLE account.referral_code
(
    id          uuid NOT NULL DEFAULT uuid_generate_v4(),
    user_id     uuid NOT NULL,
    code        character varying,
    create_date TIMESTAMP without time zone,

    CONSTRAINT referral_code_pk PRIMARY KEY (id)
);

CREATE TABLE account.referral_code_user
(
    id               uuid NOT NULL DEFAULT uuid_generate_v4(),
    user_id          uuid NOT NULL,
    referrer_id      uuid,
    referral_code_id uuid,

    CONSTRAINT referral_code_user_pk PRIMARY KEY (id)
);
