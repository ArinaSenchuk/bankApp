package com.senchuk.project.repository;

import com.senchuk.project.model.DepositsAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositsAccountsRepository extends JpaRepository<DepositsAccounts, Long> {

    @Query("select account.master_account_balance from DepositsAccounts as account where account.profile_id=:profileId")
    String getBalanceOfClientAccount(@Param("profileId") long profile_id);

    //@Query("select account from DepositsAccounts as account where account.depositId =: depositId")
    //DepositsAccounts findByDepositId(@Param("depositId") long depositId);

    DepositsAccounts findByDepositId(long depositId);
}
