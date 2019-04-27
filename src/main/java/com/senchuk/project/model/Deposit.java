package com.senchuk.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "deposits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Deposit{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private long profileId;

    @ManyToOne
    private ReferenceData depositType;
    private String numberOfContract;

    @ManyToOne
    private ReferenceData currencyType;
    private LocalDate dateOfDepositStart;
    private LocalDate dateOfDepositEnd;

    @ManyToOne
    private ReferenceData depositTerm;
    private String depositAmount;

}
