CREATE TRIGGER 'student_update' 
        AFTER UPDATE 
        ON 'student' FOR EACH ROW 
            UPDATE student2 
            SET name=new.name 
            WHERE id=id;