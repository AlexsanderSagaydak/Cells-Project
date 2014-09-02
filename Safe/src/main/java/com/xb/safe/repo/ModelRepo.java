package com.xb.safe.repo;

import com.xb.safe.domain.ModelEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModelRepo extends JpaRepository<ModelEntity, Integer> {

    @Query(value = "SELECT * FROM model WHERE model_id IN(SELECT model_id FROM safe WHERE dept_id = ?1)", nativeQuery = true)
    public List<ModelEntity> findModelByDeptId(Integer deptId);
}
