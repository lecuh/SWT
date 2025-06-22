// src/main/java/com/myapp/schoolhealth/payload/request/LoginRequest.java
package com.myapp.schoolhealth.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}