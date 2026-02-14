INSERT INTO countries (name, code) VALUES
('Ukraine', 'UA'),
('Russia', 'RU'),
('United States', 'US'),
('Israel', 'IL'),
('Palestine', 'PS');

INSERT INTO conflicts (name, description, start_date, status) VALUES
('Ukraine War', 'Conflict between Russia and Ukraine', '2022-02-24 00:00:00', 'ACTIVE'),
('Israel-Palestine Conflict', 'Long-running conflict in the Middle East', '1948-05-14 00:00:00', 'ACTIVE');

INSERT INTO conflict_country (conflict_id, country_id) VALUES
(1, 1), -- Ukraine War -> Ukraine
(1, 2), -- Ukraine War -> Russia
(1, 3), -- Ukraine War -> USA
(2, 4), -- Israel-Palestine -> Israel
(2, 5); -- Israel-Palestine -> Palestine

INSERT INTO factions (name, conflict_id) VALUES
('Ukrainian Armed Forces', 1),
('Russian Armed Forces', 1),
('Israeli Defense Forces', 2),
('Hamas', 2);

INSERT INTO faction_country (faction_id, country_id) VALUES
(1, 1), -- Ukrainian Armed Forces -> Ukraine
(2, 2), -- Russian Armed Forces -> Russia
(3, 4), -- IDF -> Israel
(4, 5); -- Hamas -> Palestine

INSERT INTO events (event_date, location, description, conflict_id) VALUES
('2022-02-24 05:00:00', 'Kyiv', 'Beginning of large scale invasion', 1),
('2022-03-01 12:00:00', 'Kharkiv', 'Heavy fighting reported', 1),
('2023-10-07 06:30:00', 'Gaza Strip', 'Major escalation of violence', 2);
