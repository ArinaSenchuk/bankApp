package com.senchuk.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String lastname;
    private String firstname;
    private String patronic;
    private LocalDate dateOfBd;

    @ManyToOne
    private ReferenceData sex;
    private String passportSeries;
    private String passportNumber;
    private String issuedBy;
    private String identificationNumber;
    private String placeOfBirth;
    @ManyToOne
    private ReferenceData city;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private ReferenceData familyStatus;
    @ManyToOne
    private ReferenceData nationality;
    @ManyToOne
    private ReferenceData disability;
    private String revenue;
    private String isRetiree;

}
