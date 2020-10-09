package com.soaint.exam.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserFile {

    private Long idUser;

    private String username;

    private String password;

    private String firstname;

    private Boolean enabled;
}