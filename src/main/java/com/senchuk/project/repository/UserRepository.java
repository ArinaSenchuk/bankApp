package com.senchuk.project.repository;

import com.senchuk.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   @Query("SELECT user.id from User as user where user.login=:login OR user.email=:email")
   Long isExists(@Param("login") String login, @Param("email") String email);

    User findByLogin(String login);

    @Query("SELECT user.profileId from User as user where user.login=:login")
    Long getProfileIdByLogin(@Param ("login") String login);

    void deleteByProfileId(long profileId);
}
