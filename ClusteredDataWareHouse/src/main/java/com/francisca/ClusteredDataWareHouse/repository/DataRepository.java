package com.francisca.ClusteredDataWareHouse.repository;

import com.francisca.ClusteredDataWareHouse.model.DealDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<DealDetails, Long> {
    Optional<DealDetails> findById(Long Id);
    Optional<DealDetails> findByDealId(String Id);
}
