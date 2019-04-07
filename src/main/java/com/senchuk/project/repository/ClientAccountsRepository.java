package com.senchuk.project.repository;

import com.senchuk.project.model.ClientAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountsRepository extends JpaRepository<ClientAccounts,Long> {

    @Query("select account from ClientAccounts as account where account.profile_id=:profile_id")
    ClientAccounts findByProfileId(@Param("profile_id") Long profile_id);


}
