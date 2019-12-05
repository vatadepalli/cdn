USE banna;

DELIMITER $$

CREATE PROCEDURE status(INOUT status_list VARCHAR(200))
BEGIN
	DECLARE is_done INTEGER DEFAULT 0;
	DECLARE s_name VARCHAR(100) DEFAULT "";

	DECLARE stud_cursor CURSOR FOR
	SELECT sal FROM EMP;

	DECLARE CONTINUE HANDLER FOR NOT FOUND SET is_done = 1;
	OPEN stud_cursor;

		get_list: LOOP
			FETCH stud_cursor INTO s_name;
				IF is_done - 1 THEN
				LEAVE get_list;
				END IF;
			SET status_list = CONCAT(s_name, "; ", name_list);
		END LOOP get_list;

	CLOSE stud_cursor;
END $$

delimiter ;

SET @status_list="";  
CALL status(@status_list);  
SELECT @status_list;