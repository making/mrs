
DROP TABLE IF EXISTS reservation CASCADE;
DROP TABLE IF EXISTS usr CASCADE;
DROP TABLE IF EXISTS reservable_room CASCADE;
DROP TABLE IF EXISTS meeting_room CASCADE;

CREATE TABLE IF NOT EXISTS meeting_room (
  room_id   INTEGER      NOT NULL AUTO_INCREMENT,
  room_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (room_id)
);
CREATE TABLE IF NOT EXISTS reservable_room (
  reserved_date DATE    NOT NULL,
  room_id       INTEGER NOT NULL,
  PRIMARY KEY (reserved_date, room_id),
  FOREIGN KEY (room_id) REFERENCES meeting_room (room_id)
);
CREATE TABLE IF NOT EXISTS usr (
  user_id    VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  role_name  VARCHAR(255) NOT NULL,
  PRIMARY KEY (user_id)
);
CREATE TABLE IF NOT EXISTS reservation (
  reservation_id INTEGER      NOT NULL AUTO_INCREMENT,
  end_time       TIME         NOT NULL,
  start_time     TIME         NOT NULL,
  reserved_date  DATE         NOT NULL,
  room_id        INTEGER      NOT NULL,
  user_id        VARCHAR(255) NOT NULL,
  PRIMARY KEY (reservation_id),
  FOREIGN KEY (reserved_date, room_id) REFERENCES reservable_room (reserved_date, room_id),
  FOREIGN KEY (user_id) REFERENCES usr (user_id)
);
