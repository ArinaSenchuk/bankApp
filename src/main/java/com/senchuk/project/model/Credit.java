package com.senchuk.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "credits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Credit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private long profileId;

    @ManyToOne
    private ReferenceData creditType;
    private String numberOfContract;

    @ManyToOne
    private ReferenceData currencyType;
    private LocalDate dateOfCreditStart;
    private LocalDate dateOfCreditEnd;

    @ManyToOne
    private ReferenceData creditTerm;
    private String creditAmount;

}
