package com.senchuk.project.repository;

import com.senchuk.project.model.AccountingEntries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingEntriesRepository extends JpaRepository<AccountingEntries, Long> {

    void deleteAllByProfileId(long profileId);
}
