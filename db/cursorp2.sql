        use banna;
        DELIMITER $$
        DROP PROCEDURE IF EXISTS createEmailList $$
        CREATE PROCEDURE createEmailList (
            INOUT emailList varchar(4000)
        )
        BEGIN
            DECLARE finished INTEGER DEFAULT 0;
            DECLARE empnumber INTEGER default 0;
        
            -- declare cursor for employee email
            DEClARE curEmail 
                CURSOR FOR 
                    SELECT e.empno 
                    FROM EMP e;
        
            -- declare NOT FOUND handler
            DECLARE CONTINUE HANDLER 
                FOR NOT FOUND SET finished = 1;
        
            OPEN curEmail;

            getEmail: LOOP
                
                FETCH curEmail INTO empnumber;
                IF finished = 1 THEN 
                    LEAVE getEmail;
                END IF;
                
                UPDATE EMP
                SET STATUS_='Cool'
                WHERE EMPNO=empnumber;
                
            END LOOP getEmail;
            CLOSE curEmail;
        
        END$$
        DELIMITER ;

        SELECT * FROM EMP;

        ALTER TABLE EMP  
        ADD STATUS_ varchar(40);
            

        SET @emailList = ""; 
        CALL createEmailList(@emailList); 

        SELECT *
        FROM EMP;

        ALTER TABLE EMP  
            DROP COLUMN STATUS_;