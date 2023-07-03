package com.challenge.conexa.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDTO {
    private String currentPassword;
    private String newPassword;
}
