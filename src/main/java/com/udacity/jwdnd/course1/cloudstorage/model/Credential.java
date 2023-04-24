package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Credential {

    private Integer credentialId;
    private String url;
    private String username;
    private String password;
    private Integer userId;
    private String credentialKey;
    private String decryptedPassword;

}
