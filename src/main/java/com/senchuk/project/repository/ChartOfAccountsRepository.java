package com.senchuk.project.repository;

import com.senchuk.project.model.ChartOfAcconts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartOfAccountsRepository extends JpaRepository<ChartOfAcconts,Long> {

    @Query("select chart.accountNumber from ChartOfAcconts as chart where chart.accountCode=:accountCode")
    String getAccountNumberByAccountCode(@Param("accountCode") String accountCode);
}
