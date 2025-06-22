// AuthServiceImpl.java (Chỉnh sửa)
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.Nurse;
import com.myapp.schoolhealth.payload.request.LoginRequest;
import com.myapp.schoolhealth.payload.response.AuthResponse;
import com.myapp.schoolhealth.repository.NurseRepository;
import com.myapp.schoolhealth.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final NurseRepository nurseRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil, NurseRepository nurseRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.nurseRepository = nurseRepository;
    }

    @Override
    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        Nurse nurse = nurseRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: Nurse not found."));

        List<String> roles = Arrays.stream(nurse.getRoles().split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        return new AuthResponse(jwt, true, roles);
    }
}