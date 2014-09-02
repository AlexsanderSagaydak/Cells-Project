package com.xb.safe.service.specification;

import com.xb.safe.domain.ContractEntity;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ContractSpecification {
    public static Specification<ContractEntity> getFilterById(final Integer id) {
        return new Specification<ContractEntity>() {

            @Override
            public Predicate toPredicate(Root<ContractEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.<Integer>get("safeId"), id);
            }
        };
    }
}
