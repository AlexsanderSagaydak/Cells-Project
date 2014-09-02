package com.xb.safe.repo;

import com.xb.safe.domain.DelayReportEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DelayReportRepo extends JpaRepository<DelayReportEntity, Integer> {

    @Query(value = "SELECT CAST((EXTRACT(DAY FROM (to_timestamp(date_end, 'DD-MM-YYYY') - to_timestamp(?1, 'DD-MM-YYYY'))))*(-1) AS int8) AS delay_days, CAST((EXTRACT(DAY FROM (to_timestamp(date_end, 'DD-MM-YYYY') - to_timestamp(?1, 'DD-MM-YYYY')))) * c.price_rent_24 * (-1)AS int8) AS sum_delay, c.date_end, c.safe_num, c.contract_id, cl.name AS client_name, cl.surname, cl.patronymic, cl.phone_m1, dpt.name, m.height, m.width, m.depth FROM contract c\n"
            + "LEFT JOIN clients AS cl ON cl.client_id = c.client_id1\n"
            + "LEFT JOIN department AS dpt ON c.dept_id = dpt.dept_id\n"
            + "LEFT JOIN model AS m ON c.safe_size = m.model_id\n"
            + "WHERE c.dept_id = ?2 AND c.contract_status = 1", nativeQuery = true)

    public List<DelayReportEntity> getDataForDelayReport(String currentDate, Integer deptId);
    
    @Query(value = "SELECT CAST((EXTRACT(DAY FROM (to_timestamp(date_end, 'DD-MM-YYYY') - to_timestamp(?1, 'DD-MM-YYYY'))))*(-1) AS int8) AS delay_days, CAST((EXTRACT(DAY FROM (to_timestamp(date_end, 'DD-MM-YYYY') - to_timestamp(?1, 'DD-MM-YYYY')))) * c.price_rent_24 * (-1)AS int8) AS sum_delay, c.date_end, c.safe_num, c.contract_id, cl.name AS client_name, cl.surname, cl.patronymic, cl.phone_m1, dpt.name, m.height, m.width, m.depth FROM contract c\n"
            + "LEFT JOIN clients AS cl ON cl.client_id = c.client_id1\n"
            + "LEFT JOIN department AS dpt ON c.dept_id = dpt.dept_id\n"
            + "LEFT JOIN model AS m ON c.safe_size = m.model_id\n"
            + "WHERE c.contract_status = 1", nativeQuery = true)

    public List<DelayReportEntity> getDataForAllDelayReport(String currentDate);
}
