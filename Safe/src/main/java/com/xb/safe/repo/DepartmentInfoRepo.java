package com.xb.safe.repo;

import com.xb.safe.domain.DepartmentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentInfoRepo extends JpaRepository<DepartmentInfoEntity, Integer> {
     public DepartmentInfoEntity findDepartmentEntityByDeptartmentId(Integer deptId);
}
