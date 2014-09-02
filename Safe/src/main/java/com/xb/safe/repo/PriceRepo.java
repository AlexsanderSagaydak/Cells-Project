package com.xb.safe.repo;

import com.xb.safe.domain.PriceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PriceRepo extends JpaRepository<PriceEntity, Integer> {
    public PriceEntity findByDepartmentIdAndModelIdAndRentPeriod(Integer deptId, Integer modelId, Integer rentPeriod);
    public List<PriceEntity> findByDepartmentIdAndModelId(Integer deptId, Integer modelId);
    
    @Query(value = "SELECT price_24h FROM price WHERE id_dep = ?1 AND id_model = ?2 AND price_24h is not null", nativeQuery = true)
     public Integer findPrce24ByDepartmentIdAndModelId(Integer deptId, Integer modelId);
     
    @Query(value = "SELECT * FROM price INNER JOIN model ON(model_id = id_model) WHERE id_dep = ?1 ORDER BY price_id", nativeQuery = true)
     public List<PriceEntity> findPriceListByDeptId(Integer deptId);
     
    @Modifying
    @Transactional
    @Query(value = "UPDATE price SET price = ?1 WHERE price_id = ?2", nativeQuery = true)
    public void setNewModelPrice(Integer newPrice, Integer priceId);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE price SET price_24h = ?1 WHERE price_id = ?2", nativeQuery = true)
    public void setNewModelPrice24(Integer newPrice, Integer priceId);
}
