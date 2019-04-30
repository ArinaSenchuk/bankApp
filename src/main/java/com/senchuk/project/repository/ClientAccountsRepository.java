package com.senchuk.project.repository;

import com.senchuk.project.model.ClientAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountsRepository extends JpaRepository<ClientAccounts,Long> {

    ClientAccounts findByProfileId(@Param("profileId") Long profileId);

    void deleteByProfileId(long profileId);
}
