package com.xb.safe.repo;

import com.xb.safe.domain.FilingCellsEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FillingCellsRepo extends JpaRepository<FilingCellsEntity, Integer> {

    @Query(value = "SELECT c.dept_id, d.name,\n"
            + "(SELECT COUNT(*) FROM contract as c1 WHERE  ?1 between to_timestamp(date_start, 'DD.MM.YYYY') and to_timestamp(coalesce(date_close, '01.01.2030'), 'DD.MM.YYYY') and c1.dept_id = c.dept_id ) as open_on_start,\n"
            + "(SELECT COUNT(*) FROM contract as c1 WHERE  ?2 between to_timestamp(date_start, 'DD.MM.YYYY') and to_timestamp(coalesce(date_close, '01.01.2030'), 'DD.MM.YYYY') and c1.dept_id = c.dept_id ) as open_on_end,\n"
            + "(SELECT COUNT(*) FROM contract as c1 WHERE  to_timestamp(date_close, 'DD.MM.YYYY') between ?1 and ?2 and c1.dept_id = c.dept_id)  as close_contract_by_period,\n"
            + "(SELECT COUNT(*) FROM contract as c1 WHERE  to_timestamp(date_start, 'DD.MM.YYYY') between ?1 and ?2 and c1.dept_id = c.dept_id)  as open_contract_by_period,\n"
            + "(SELECT COUNT(*) FROM price WHERE id_dep = c.dept_id) AS count_safe\n"
            + "FROM contract AS c \n"
            + "LEFT JOIN department AS d ON d.dept_id = c.dept_id WHERE d.dept_id = ?3\n"
            + " GROUP BY c.dept_id, d.name", nativeQuery = true)
    public List<FilingCellsEntity> filingCellsInfoByPeriodAndDeptId(Date startDate, Date endDate, Integer deptId);

    @Query(value = "SELECT d.dept_id, d.name,\n"
            + "(SELECT COUNT(*) FROM contract as c1 WHERE  ?1 > to_timestamp(date_start, 'DD.MM.YYYY') and ?1 <= to_timestamp(coalesce(date_close, '01.01.2030'), 'DD.MM.YYYY') and c1.dept_id = d.dept_id ) as open_on_start,\n"
            + "(SELECT COUNT(*) FROM contract as c1 WHERE  ?2 >= to_timestamp(date_start, 'DD.MM.YYYY') and ?2 < to_timestamp(coalesce(date_close, '01.01.2030'), 'DD.MM.YYYY') and c1.dept_id = d.dept_id ) as open_on_end,\n"
            + "(SELECT COUNT(*) FROM contract as c1 WHERE  to_timestamp(date_close, 'DD.MM.YYYY') between ?1 and ?2 and c1.dept_id = d.dept_id) as close_contract_by_period,\n"
            + "(SELECT COUNT(*) FROM contract as c1 WHERE  to_timestamp(date_start, 'DD.MM.YYYY') between ?1 and ?2 and c1.dept_id = d.dept_id) as open_contract_by_period,\n"
            + "(SELECT COUNT(*) FROM safe WHERE dept_id = d.dept_id) AS count_safe\n"
            + "FROM contract AS c \n"
            + "RIGHT JOIN department AS d ON d.dept_id = c.dept_id\n"
            + " GROUP BY d.dept_id, d.name", nativeQuery = true)
    public List<FilingCellsEntity> filingCellsInfoByPeriod(Date startDate, Date endDate);
}
