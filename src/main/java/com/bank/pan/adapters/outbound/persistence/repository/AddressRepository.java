package com.bank.pan.adapters.outbound.persistence.repository;

import com.bank.pan.adapters.outbound.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
