package com.xb.safe.repo;

import com.xb.safe.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<ClientEntity, Integer> {
    public List<ClientEntity> findAllBySurnameIgnoringCaseOrInn(String surname, String inn);
    public ClientEntity findClientEntityByInn(String check);
    public ClientEntity findClientEntityByNumber(String check);
}
