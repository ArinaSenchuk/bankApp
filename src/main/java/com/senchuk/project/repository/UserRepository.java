package com.senchuk.project.repository;

import com.senchuk.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user.id from User as user where user.passportNumber=:passport OR user.identificationNumber=:identification")
    Long isExists(@Param("passport") String passportNumber, @Param("identification") String identificationNumber);
}
