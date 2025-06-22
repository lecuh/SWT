// SecurityConfig.java
package com.myapp.schoolhealth.security;

import com.myapp.schoolhealth.service.NurseUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final NurseUserDetailsService nurseUserDetailsService;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(NurseUserDetailsService nurseUserDetailsService, JwtTokenFilter jwtTokenFilter) {
        this.nurseUserDetailsService = nurseUserDetailsService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(nurseUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {
                }) // ✅ THÊM DÒNG NÀY: bật CORS
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/prescriptions/*/confirm").permitAll() // ✅ SỬA LẠI Ở ĐÂY
                        .requestMatchers("/api/students/**").hasAnyRole("NURSE", "ADMIN")
                        .requestMatchers("/api/medicines/**").hasAnyRole("NURSE", "ADMIN")
                        .requestMatchers("/api/medical-history/**").hasAnyRole("NURSE", "ADMIN")
                        .requestMatchers("/api/prescriptions/**").hasAnyRole("NURSE", "ADMIN")
                        .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}