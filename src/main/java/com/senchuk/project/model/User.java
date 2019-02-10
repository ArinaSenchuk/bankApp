package com.senchuk.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String lastname;
    private String firstname;
    private String patronic;
    private LocalDate dateOfBd;
    private String sex;
    private String passportSeries;
    private String passportNumber;
    private String issuedBy;
    private String identificationNumber;
    private String placeOfBirth;
    private String city;
    private String address;
    private String phoneNumber;
    private String familyStatus;
    private String nationality;
    private String disability;
    private String revenue;
    private String isRetiree;

    private String email;
    private String password;




}
