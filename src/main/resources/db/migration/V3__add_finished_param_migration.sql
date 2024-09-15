ALTER TABLE events
    ADD finished BOOLEAN;

ALTER TABLE events
    ALTER COLUMN finished SET NOT NULL;