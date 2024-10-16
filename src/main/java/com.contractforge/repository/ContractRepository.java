package com.contractforge.repository;

import com.contractforge.model.Contract;
import com.contractforge.dto.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Contract entities.
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    /**
     * Finds contracts by buyer name containing the given string and statuses.
     *
     * @param buyer    The buyer name to search for.
     * @param statuses The list of statuses to filter by.
     * @return A list of matching contracts.
     */
    List<Contract> findByBuyerContainingIgnoreCaseAndStatusIn(String buyer, List<Status> statuses);
}