package com.senchuk.project.repository;

import com.senchuk.project.model.BankDevelopmentFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDevelopmentFundRepository extends JpaRepository<BankDevelopmentFund, Long> {

    @Query("select account.balance from BankDevelopmentFund as account where account.id = 1")
    String getBalanceOfBankDevelopmentFund();
}
