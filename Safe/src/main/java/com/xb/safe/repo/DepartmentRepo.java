package com.xb.safe.repo;

import com.xb.safe.domain.DepartmentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DepartmentRepo extends JpaRepository<DepartmentEntity, Integer> {
    
    public DepartmentEntity findDepartmentEntityById(Integer deptId);
    @Query(value = "SELECT * FROM safe AS a INNER JOIN department AS d ON(a.dept_id = d.dept_id) WHERE status = 'f';", nativeQuery = true)
    
    public List<DepartmentEntity> findFreeSafes();
}
