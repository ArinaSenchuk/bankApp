package com.senchuk.project.repository;

import com.senchuk.project.model.DepositsAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositsAccountsRepository extends JpaRepository<DepositsAccounts, Long> {

    @Query("select account.masterAccountBalance from DepositsAccounts as account where account.profileId=:profileId")
    String getBalanceOfClientAccount(@Param("profileId") long profileId);

    DepositsAccounts findByDepositId(long depositId);

    void deleteByDepositId(long depositId);
}
