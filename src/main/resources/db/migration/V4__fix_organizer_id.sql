ALTER TABLE events
    DROP CONSTRAINT fk_events_on_event;

ALTER TABLE events
    ADD organizer_id UUID;

ALTER TABLE events
    ALTER COLUMN organizer_id SET NOT NULL;

ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_ORGANIZER FOREIGN KEY (organizer_id) REFERENCES companies (id);

ALTER TABLE events
    DROP COLUMN event_id;