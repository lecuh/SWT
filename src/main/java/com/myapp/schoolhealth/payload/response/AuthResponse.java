// src/main/java/com/myapp/schoolhealth/payload/response/AuthResponse.java
package com.myapp.schoolhealth.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Boolean authented;
    private List<String> roles;
}