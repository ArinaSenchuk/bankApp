package com.senchuk.project.repository;

import com.senchuk.project.model.CreditsAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditsAccountsRepository extends JpaRepository<CreditsAccounts, Long> {

    @Query("select account.masterAccountBalance from CreditsAccounts as account where account.profileId=:id")
    String getBalanceOfClientAccount(@Param("id") long profileId);

}
