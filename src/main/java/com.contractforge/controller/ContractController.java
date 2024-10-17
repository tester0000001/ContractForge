package com.contractforge.controller;

import com.contractforge.dto.ContractDTO;
import com.contractforge.dto.Status;
import com.contractforge.service.ContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * REST controller for contracts.
 */
@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    /**
     * Retrieves all contracts with optional filtering.
     *
     * @param buyer  The buyer name to filter by.
     * @param status The status to filter by.
     * @return A list of ContractDTOs.
     */
    @GetMapping
    public ResponseEntity<List<ContractDTO>> getAllContracts(
            @RequestParam(required = false) String buyer,
            @RequestParam(required = false) Status status) {
        List<Status> statuses = status != null ? Arrays.asList(status) : Arrays.asList(Status.values());
        List<ContractDTO> contracts = contractService.getAllContracts(buyer, statuses);
        return ResponseEntity.ok(contracts);
    }

    /**
     * Retrieves a contract by ID.
     *
     * @param id The ID of the contract.
     * @return The ContractDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> getContractById(@PathVariable Long id) {
        ContractDTO contractDTO = contractService.getContractById(id);
        return ResponseEntity.ok(contractDTO);
    }

    /**
     * Creates a new contract.
     *
     * @param contractDTO The contract data.
     * @return The created ContractDTO.
     */
    @PostMapping
    public ResponseEntity<ContractDTO> createContract(@RequestBody ContractDTO contractDTO) {
        ContractDTO createdContract = contractService.createContract(contractDTO);
        return ResponseEntity.status(201).body(createdContract);
    }

    /**
     * Updates an existing contract.
     *
     * @param id          The ID of the contract.
     * @param contractDTO The updated data.
     * @return The updated ContractDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractDTO> updateContract(@PathVariable Long id, @RequestBody ContractDTO contractDTO) {
        ContractDTO updatedContract = contractService.updateContract(id, contractDTO);
        return ResponseEntity.ok(updatedContract);
    }

    /**
     * Soft deletes a contract.
     *
     * @param id The ID of the contract.
     * @return A ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteContract(@PathVariable Long id) {
        contractService.softDeleteContract(id);
        return ResponseEntity.noContent().build();
    }
}