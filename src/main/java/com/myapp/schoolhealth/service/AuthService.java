// AuthService.java
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.payload.request.LoginRequest;
import com.myapp.schoolhealth.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse authenticateUser(LoginRequest loginRequest); // Đảm bảo chữ ký này
}