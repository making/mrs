INSERT INTO usr (user_id, first_name, last_name, role_name, password)
VALUES ('aaaa', 'Aaa', 'Aaa', 'USER', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
INSERT INTO usr (user_id, first_name, last_name, role_name, password)
VALUES ('bbbb', 'Bbb', 'Bbb', 'USER', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
INSERT INTO usr (user_id, first_name, last_name, role_name, password)
VALUES ('cccc', 'Ccc', 'Ccc', 'ADMIN', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
--
INSERT INTO meeting_room (room_name) VALUES ('新木場');
INSERT INTO meeting_room (room_name) VALUES ('辰巳');
INSERT INTO meeting_room (room_name) VALUES ('豊洲');
INSERT INTO meeting_room (room_name) VALUES ('月島');
INSERT INTO meeting_room (room_name) VALUES ('新富町');
INSERT INTO meeting_room (room_name) VALUES ('銀座一丁目');
INSERT INTO meeting_room (room_name) VALUES ('有楽町');
--

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '新木場'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '新木場'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '新木場'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '新木場'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '新木場'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '辰巳'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '辰巳'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '辰巳'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '辰巳'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '辰巳'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '豊洲'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '豊洲'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '豊洲'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '豊洲'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '豊洲'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '新富町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '新富町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '新富町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '新富町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '新富町'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '銀座一丁目'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '銀座一丁目'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '銀座一丁目'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '銀座一丁目'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '銀座一丁目'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = '有楽町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '有楽町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '有楽町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '有楽町'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = '有楽町'));
