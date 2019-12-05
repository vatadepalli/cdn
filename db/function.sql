delimiter ;
DROP FUNCTION IF EXISTS costToCompany;
SET GLOBAL log_bin_trust_function_creators = 1;


delimiter //
CREATE FUNCTION costToCompany(eid INT(10)) RETURNS DECIMAL(9,2)
begin
	DECLARE cost DECIMAL(9,2);
    DECLARE fsal DECIMAL(9,2);
    DECLARE years INT;
    
	SELECT sal, FLOOR(DATEDIFF(CURDATE(), hiredate)/365)
    INTO fsal, years
    FROM EMP
    WHERE empno = eid;
    
    IF years < 32 THEN 
		SET cost = fsal + (fsal * 0.15) + (fsal*0.2) + (fsal*0.08) + (fsal*0);
	elseif years < 35 then
		SET cost =  fsal + (fsal * 0.15) + (fsal*0.2) + (fsal*0.08) + (fsal*0.1);
    elseif years < 37 then
		SET cost =  fsal + (fsal * 0.15) + (fsal*0.2) + (fsal*0.08) + (fsal*0.2);
    else
		SET cost =  fsal + (fsal * 0.15) + (fsal*0.2) + (fsal*0.08) + (fsal*0.3);
    end if;
    
    return cost;

end //
delimiter ;

select ename, costToCompany(empno)
from EMP;
