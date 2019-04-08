package com.senchuk.project.repository;

import com.senchuk.project.model.ProgramInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramInterestRepository extends JpaRepository<ProgramInterest, Long> {

    @Query("select program.interest from ProgramInterest as program where program.program_id=:depositType_id AND program.term_id=:depositTerm_id")
    String getValueOfInterest(@Param("depositTerm_id") long depositTerm_id, @Param("depositType_id") long depositType_id);
}
