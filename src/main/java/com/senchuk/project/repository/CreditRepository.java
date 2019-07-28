package com.senchuk.project.repository;

import com.senchuk.project.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    List<Credit> findAllByProfileId(long profileId);
}
