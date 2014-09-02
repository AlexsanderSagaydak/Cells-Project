package com.xb.safe.repo;

import com.xb.safe.domain.ContractEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ContractRepo extends JpaRepository<ContractEntity, Integer>, JpaSpecificationExecutor {

    public List<ContractEntity> findClientEntityByContractStatusAndDepartmentId(Integer status, Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE contract SET contract_status = '0', date_close= ?2 WHERE contract_id = ?1", nativeQuery = true)
    public Integer setContractStatus(Integer contractId, String dateClose);
    
    @Query(value = "SELECT * FROM contract WHERE safe_id = ?1 AND contract_status = 1", nativeQuery = true)
    public ContractEntity getContractBySafeId(Integer safeId);

    @Query(value = "SELECT * FROM clients INNER JOIN contract  ON(client_id = client_id1) WHERE LOWER(surname) = LOWER(?1)", nativeQuery = true)
    public List<ContractEntity> findContractEntityBySurname(String surname);

    @Query(value = "SELECT * FROM clients INNER JOIN contract  ON(client_id = client_id1) WHERE inn = ?1", nativeQuery = true)
    public List<ContractEntity> findClientEntityByInn(String inn);

    @Query(value = "SELECT * FROM clients INNER JOIN contract  ON(client_id = client_id1)", nativeQuery = true)
    public List<ContractEntity> getAllClientsByAllDept();
    
    @Query(value = "SELECT * FROM contract WHERE to_timestamp(date_start, 'DD.MM.YYYY') >= ?1 AND to_timestamp(date_start, 'DD.MM.YYYY') <= ?2 AND dept_id = ?3 ORDER BY contract_id", nativeQuery = true)
    public List<ContractEntity> getContractsByPeriod(Date dateStart, Date dateEnd, Integer deptId);
    
    

}
