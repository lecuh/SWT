// src/main/java/com/myapp/schoolhealth/service/NurseUserDetailsService.java
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.Nurse;
import com.myapp.schoolhealth.repository.NurseRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors; // Thêm import này

@Service
public class NurseUserDetailsService implements UserDetailsService {

    private final NurseRepository nurseRepository;

    public NurseUserDetailsService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Nurse nurse = nurseRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nurse not found with username: " + username));

        List<String> roles = Arrays.stream(nurse.getRoles().split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        return new User(nurse.getUsername(), nurse.getPassword(), roles.stream().map(s -> (org.springframework.security.core.GrantedAuthority) () -> s).collect(Collectors.toList()));
    }
}