package com.senchuk.project.repository;

import com.senchuk.project.model.InterestProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestProgramRepository extends JpaRepository<InterestProgram, Long> {

    @Query("select program.interest from InterestProgram as program where program.programId=:depositTypeId AND program.termId=:depositTermId")
    String getValueOfInterest(@Param("depositTermId") long depositTermId, @Param("depositTypeId") long depositTypeId);
}
