CREATE TABLE companies
(
    id   UUID         NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_companies PRIMARY KEY (id)
);

CREATE TABLE event_participant
(
    event_id       UUID NOT NULL,
    participant_id UUID NOT NULL,
    CONSTRAINT pk_event_participant PRIMARY KEY (event_id, participant_id)
);

CREATE TABLE events
(
    id                    UUID                        NOT NULL,
    name                  VARCHAR(255)                NOT NULL,
    description           VARCHAR(255),
    start_date            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    place                 VARCHAR(255)                NOT NULL,
    event_id              UUID                        NOT NULL,
    registration_deadline TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_events PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         UUID         NOT NULL,
    role       VARCHAR(31),
    full_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    company_id UUID,
    confirmed  BOOLEAN,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_EVENT FOREIGN KEY (event_id) REFERENCES companies (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id);

ALTER TABLE event_participant
    ADD CONSTRAINT fk_evepar_on_event FOREIGN KEY (event_id) REFERENCES events (id);

ALTER TABLE event_participant
    ADD CONSTRAINT fk_evepar_on_student FOREIGN KEY (participant_id) REFERENCES users (id);