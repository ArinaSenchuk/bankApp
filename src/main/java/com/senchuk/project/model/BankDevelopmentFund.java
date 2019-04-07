package com.senchuk.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bank_development_fund")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankDevelopmentFund {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String balance;
}
