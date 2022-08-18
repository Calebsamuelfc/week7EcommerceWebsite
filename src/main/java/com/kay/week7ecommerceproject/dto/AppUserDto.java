package com.kay.week7ecommerceproject.dto;

import lombok.*;

import javax.persistence.Column;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private String fullName;
    private String email;
    private String password;
}
