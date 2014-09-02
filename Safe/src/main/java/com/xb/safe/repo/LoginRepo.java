package com.xb.safe.repo;

import com.xb.safe.domain.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LoginRepo extends JpaRepository<LoginEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE logins SET dept_id = ?1 WHERE login = ?2", nativeQuery = true)
    public void setNewDeptId(Integer departmentId, String clientLogin);
}
