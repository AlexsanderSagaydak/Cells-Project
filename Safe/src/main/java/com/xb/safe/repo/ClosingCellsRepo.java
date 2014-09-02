package com.xb.safe.repo;

import com.xb.safe.domain.ClosingCellsEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClosingCellsRepo extends JpaRepository<ClosingCellsEntity, Integer>  {
    
    @Query(value = "SELECT cl.surname, cl.name, cl.patronymic, cl.phone_m1, c.safe_num, c.date_start, c.date_end, c.price, c.contract_id FROM contract AS c INNER JOIN clients AS cl ON(cl.client_id = c.client_id1) WHERE to_timestamp(date_end, 'DD.MM.YYYY') >= ?1 AND to_timestamp(date_end, 'DD.MM.YYYY') <= ?2 AND c.contract_status =1 ORDER BY contract_id", nativeQuery = true)
    public List<ClosingCellsEntity> getContractByEndDate(Date startDate, Date endDate);
    
    @Query(value = "SELECT cl.surname, cl.name, cl.patronymic, cl.phone_m1, c.safe_num, c.date_start, c.date_end, c.price, c.contract_id FROM contract AS c INNER JOIN clients AS cl ON(cl.client_id = c.client_id1) WHERE to_timestamp(date_end, 'DD.MM.YYYY') >= ?1 AND to_timestamp(date_end, 'DD.MM.YYYY') <= ?2 AND c.contract_status =1 AND c.dept_id =?3 ORDER BY contract_id", nativeQuery = true)
    public List<ClosingCellsEntity> getContractByEndDateAndDeptId(Date startDate, Date endDate, Integer deptId);
    
}
