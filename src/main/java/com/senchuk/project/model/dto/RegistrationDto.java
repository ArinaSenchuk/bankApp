package com.senchuk.project.model.dto;

import com.senchuk.project.model.Profile;
import com.senchuk.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationDto {

    private Profile profile;
    private User user;
}
