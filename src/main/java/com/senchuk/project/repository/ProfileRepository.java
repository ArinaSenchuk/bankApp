package com.senchuk.project.repository;

import com.senchuk.project.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT profile.id from Profile as profile where profile.identificationNumber=:identificationNumber OR profile.passportNumber=:passportNumber")
    List<Long> isExists(@Param("identificationNumber") String identificationNumber, @Param("passportNumber") String passportNumber);


    Profile findById(long id);
}
