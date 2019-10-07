USE test;
        SELECT * FROM table_1;

        DELIMITER ##

        CREATE PROCEDURE list_name(INOUT name_list VARCHAR(4000))
        BEGIN

        DECLARE is_done INTEGER DEFAULT 0;
        DECLARE s_name VARCHAR(100) DEFAULT "";
        
        DECLARE stud_cursor CURSOR FOR
        SELECT name FROM table_1;

        DECLARE CONTINUE HANDLER FOR NOT FOUND SET is_done = 1;
        OPEN stud_cursor;

            get_list: LOOP
                FETCH stud_cursor INTO s_name;
                    IF is_done - 1 THEN
                    LEAVE get_list;
                    END IF;
                SET name_list = CONCAT(s_name, "; ", name_list);
            END LOOP get_list;
        
        CLOSE stud_cursor;

        END##



        DELIMITER ;

        SET @name_list ="";  
        CALL list_name(@name_list);  
        SELECT @name_list;