package com.senchuk.project.repository;

import com.senchuk.project.model.ReferenceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceData, Long> {

    List<ReferenceData> getReferenceDataByDefinition(String definition);
}
