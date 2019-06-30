INSERT INTO usr (user_id, first_name, last_name, role_name, password)
VALUES ('aaaa', 'Aaa', 'Aaa', 'USER', '{bcrypt}$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
INSERT INTO usr (user_id, first_name, last_name, role_name, password)
VALUES ('bbbb', 'Bbb', 'Bbb', 'USER', '{bcrypt}$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
INSERT INTO usr (user_id, first_name, last_name, role_name, password)
VALUES ('cccc', 'Ccc', 'Ccc', 'ADMIN', '{bcrypt}$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
--
INSERT INTO meeting_room (room_name) VALUES ('中目黒');
INSERT INTO meeting_room (room_name) VALUES ('恵比寿');
INSERT INTO meeting_room (room_name) VALUES ('広尾');
INSERT INTO meeting_room (room_name) VALUES ('六本木');
INSERT INTO meeting_room (room_name) VALUES ('神谷町');
INSERT INTO meeting_room (room_name) VALUES ('霞ケ関');
INSERT INTO meeting_room (room_name) VALUES ('日比谷');
--

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '中目黒'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '中目黒'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '中目黒'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '中目黒'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '中目黒'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '恵比寿'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '恵比寿'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '恵比寿'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '恵比寿'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '恵比寿'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '広尾'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '広尾'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '広尾'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '広尾'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '広尾'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '神谷町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '神谷町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '神谷町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '神谷町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '神谷町'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '霞ケ関'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '霞ケ関'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '霞ケ関'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '霞ケ関'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '霞ケ関'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '日比谷'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '日比谷'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '日比谷'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '日比谷'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '日比谷'));
