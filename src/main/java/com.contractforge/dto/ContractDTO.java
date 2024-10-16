package com.contractforge.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for the Contract entity.
 */
public class ContractDTO {

    private Long id;
    private String buyer;
    private String contractNumber;
    private LocalDate depositDate;
    private LocalDate deliveryDeadline;
    private Status status;
    private List<ItemDTO> items;
}