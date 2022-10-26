package com.fuel.ead.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordResetDto {

    private String id;
    private String currentPassword;
    private String newPassword;
}
