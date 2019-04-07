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
@Table(name = "deposits_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositsAccounts {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private long profile_id;
    private long deposit_id;

    private String master_account_number;
    private String master_account_balance;
    private String interest_account_number;
    private String interest_account_balance;
}
