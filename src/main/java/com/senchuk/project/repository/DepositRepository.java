package com.senchuk.project.repository;

import com.senchuk.project.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    List<Deposit> findByProfileId(long profileId);
}
