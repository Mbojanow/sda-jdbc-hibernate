DELIMITER //

CREATE PROCEDURE GetUserByName(
    IN name_param VARCHAR(255)
)
BEGIN
SELECT *
FROM users
WHERE uname = name_param;
END //

DELIMITER ;