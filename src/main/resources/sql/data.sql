
DELETE FROM ticket_entity;

-- ENUM validity
    -- INVALID = 0
    -- VALID = 1
    -- CHECKED = 2

-- ENUM ticketState
    -- UNRESERVED = 0
    -- RESERVED = 1
    -- PAID = 2

-- section_id, row_id, seat_id = schematic_object_id, przynajmniej ja tak to rozumiem i inaczej pierdole numeracje na tickecie
--INSERT INTO ticket_entity (id, seat_id, order_id, event_id, section_id, validity, ticket_state, price, qr_code) VALUES
--(1,  1, 1,    1, 1, 1, 0, 21.37, 'abcdef'),
--(2,  2, 1,    1, 1, 1, 0, 21.37, 'bcdefa'),
--(3,  3, 1,    1, 1, 1, 0, 21.37, 'cdefab'),
--(4,  4, 1,    1, 1, 1, 0, 21.37, 'defabc'),
-- bez przydziału do jakiegos order
--(5,  5, NULL, 1, 8, 1, 0, 21.37, 'efabcd'),
--(6,  6, NULL, 1, 8, 1, 0, 21.37, 'fabcde'),
-- inny event
--(7,  1, 2,    2, 1, 2, 2, 21.37, 'xxxxxx'),
--(8,  2, 2,    2, 1, 2, 2, 21.37, 'yyyyyy'),
--(9,  3, 2,    2, 1, 2, 2, 21.37, 'zzzzzz'),
--(10, 4, 2,    2, 1, 2, 2, 21.37, 'wwwwww')
--;

-- section_id, row_id, seat_id = schematic_object_id, przynajmniej ja tak to rozumiem i inaczej pierdole numeracje na tickecie
-- jednak nie schematic id
INSERT INTO ticket_entity (id, order_id, event_id, seat_id, section_id, validity, ticket_state, price, qr_code) VALUES
(1,  1, 1,    1, 1, 1, 0, 21.37, 'abcdef'),
(2,  1, 1,    2, 1, 1, 0, 21.37, 'bcdefa'),
(3,  1, 1,    3, 1, 1, 0, 21.37, 'cdefab'),
 (4, 1, 1,    4, 1, 1, 0, 21.37, 'defabc'),
-- bez przydziału do jakiegos order
(5,  NULL, 1, 5, 2, 1, 0, 21.37, 'efabcd'),
(6,  NULL, 1, 6, 2, 1, 0, 21.37, 'fabcde'),
-- inny event i zakupione
(7,  2, 2,    1, 1, 2, 2, 21.37, 'xxxxxx'),
(8,  2, 2,    2, 1, 2, 2, 21.37, 'yyyyyy'),
(9,  2, 2,    3, 1, 2, 2, 21.37, 'zzzzzz'),
(10, 2, 2,    4, 1, 2, 2, 21.37, 'wwwwww')
;
