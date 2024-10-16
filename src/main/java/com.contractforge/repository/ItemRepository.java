package com.contractforge.repository;

import com.contractforge.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Item entities.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * Finds items by contract ID.
     *
     * @param contractId The ID of the contract.
     * @return A list of items belonging to the contract.
     */
    List<Item> findByContractId(Long contractId);
}