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
@Table(name = "client_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientAccounts {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String accountNumber;
    private String accountBalance;

    private long profile_id;
}
