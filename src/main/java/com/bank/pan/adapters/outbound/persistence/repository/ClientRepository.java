package com.bank.pan.adapters.outbound.persistence.repository;

import com.bank.pan.adapters.outbound.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
}
