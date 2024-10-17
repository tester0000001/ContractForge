package com.contractforge.service;

import com.contractforge.dto.ContractDTO;
import com.contractforge.dto.Status;
import com.contractforge.exception.InvalidStatusTransitionException;
import com.contractforge.exception.ResourceNotFoundException;
import com.contractforge.model.Contract;
import com.contractforge.repository.ContractRepository;
import com.contractforge.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing contracts.
 */
@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ItemService itemService;

    /**
     * Retrieves all contracts with optional filters.
     *
     * @param buyer    Filter by buyer name.
     * @param statuses Filter by statuses.
     * @return A list of ContractDTOs.
     */
    @Transactional(readOnly = true)
    public List<ContractDTO> getAllContracts(String buyer, List<Status> statuses) {
        List<Contract> contracts = contractRepository.findByBuyerContainingIgnoreCaseAndStatusIn(
                buyer != null ? buyer : "", statuses);
        return contracts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a contract by ID.
     *
     * @param id The ID of the contract.
     * @return The ContractDTO.
     * @throws ResourceNotFoundException if not found.
     */
    @Transactional(readOnly = true)
    public ContractDTO getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with ID: " + id));
        return convertToDTO(contract);
    }

    /**
     * Creates a new contract.
     *
     * @param contractDTO The data of the contract to create.
     * @return The created ContractDTO.
     */
    @Transactional
    public ContractDTO createContract(ContractDTO contractDTO) {
        Contract contract = convertToEntity(contractDTO);
        contract.setStatus(Status.KREIRANO);
        contract.setContractNumber(generateContractNumber());
        Contract savedContract = contractRepository.save(contract);
        return convertToDTO(savedContract);
    }

    /**
     * Updates an existing contract.
     *
     * @param id          The ID of the contract to update.
     * @param contractDTO The updated data.
     * @return The updated ContractDTO.
     * @throws InvalidStatusTransitionException if invalid status transition.
     * @throws ResourceNotFoundException        if not found.
     */
    @Transactional
    public ContractDTO updateContract(Long id, ContractDTO contractDTO) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with ID: " + id));

        validateStatusTransition(existingContract.getStatus(), contractDTO.getStatus());

        existingContract.setBuyer(contractDTO.getBuyer());
        existingContract.setDeliveryDeadline(contractDTO.getDeliveryDeadline());
        existingContract.setDepositDate(contractDTO.getDepositDate());
        existingContract.setStatus(contractDTO.getStatus());

        Contract updatedContract = contractRepository.save(existingContract);
        return convertToDTO(updatedContract);
    }

    /**
     * Soft deletes a contract.
     *
     * @param id The ID of the contract to delete.
     * @throws InvalidStatusTransitionException if contract is not in KREIRANO status.
     * @throws ResourceNotFoundException        if not found.
     */
    @Transactional
    public void softDeleteContract(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with ID: " + id));

        if (contract.getStatus() != Status.KREIRANO) {
            throw new InvalidStatusTransitionException("Only contracts in KREIRANO status can be deleted.");
        }

        contractRepository.delete(contract);
    }
}