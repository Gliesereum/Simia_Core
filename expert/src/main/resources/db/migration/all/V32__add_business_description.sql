CREATE TABLE IF NOT EXISTS karma.business_description
(
    id            uuid              NOT NULL DEFAULT uuid_generate_v4(),
    object_id     uuid              NOT NULL,
    language_code character varying not null,
    name          character varying,
    description   character varying,
    address       character varying,
    CONSTRAINT business_description_pk PRIMARY KEY (id)
);
