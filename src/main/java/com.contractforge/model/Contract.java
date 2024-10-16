package com.contractforge.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity representing a sales contract.
 */
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buyer;

    @Column(unique = true)
    private String contractNumber;

    private LocalDate depositDate;

    private LocalDate deliveryDeadline;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;
}