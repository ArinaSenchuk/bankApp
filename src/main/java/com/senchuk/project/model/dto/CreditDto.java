package com.senchuk.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditDto {

    private long creditId;

    private String numberOfContract;
    private String creditType;
    private String currencyType;
    private LocalDate dateOfCreditStart;
    private LocalDate dateOfCreditEnd;

    private String creditAmount;
    private Double commonDebt;

}
