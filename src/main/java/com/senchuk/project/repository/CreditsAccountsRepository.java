package com.senchuk.project.repository;

import com.senchuk.project.model.CreditsAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditsAccountsRepository extends JpaRepository<CreditsAccounts, Long> {

    @Query("select account.masterAccountBalance from CreditsAccounts as account where account.profileId=:id")
    String getBalanceOfClientAccount(@Param("id") long profileId);

    CreditsAccounts findByCreditId(long creditId);

    void deleteByCreditId(long creditId);

    @Query("select account.masterAccountBalance from CreditsAccounts as account where account.creditId=:creditId")
    String getMasterAccountBalanceByCreditId(@Param("creditId") long creditId);

    @Query("select account.interestAccountBalance from CreditsAccounts as account where account.creditId=:creditId")
    String getInterestAccountBalanceByCreditId(@Param("creditId") long creditId);
}
