package com.xb.safe.repo;

import com.xb.safe.domain.SafeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SafeRepo extends JpaRepository<SafeEntity, Integer> {

    public List<SafeEntity> findAllByDepartmentId(Integer deptId);
    public List<SafeEntity> findAllByStatusAndDepartmentId(String status, Integer deptId);
    public List<SafeEntity> findAllByDepartmentIdAndModelIdAndStatus(Integer deptId, Integer modelId, String status);
       
    @Modifying
    @Transactional
    @Query(value = "UPDATE safe SET status = ?1 WHERE safe_id = ?2", nativeQuery = true)
    public Integer setSafeStatus(String safeStatus, int safeId);
}
