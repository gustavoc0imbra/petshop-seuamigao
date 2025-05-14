package org.uniara.usersapi.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginUserDTO {
    private String email;
    private String password;
}
