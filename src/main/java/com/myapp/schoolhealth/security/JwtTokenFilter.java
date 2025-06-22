// JwtTokenFilter.java (Kiểm tra kỹ các import này)
package com.myapp.schoolhealth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.myapp.schoolhealth.service.NurseUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // THÊM IMPORT NÀY
import org.springframework.security.core.context.SecurityContextHolder; // THÊM IMPORT NÀY
import org.springframework.security.core.userdetails.UserDetails; // THÊM IMPORT NÀY
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; // THÊM IMPORT NÀY
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// ... phần còn lại của class, sẽ OK nếu các import trên đúng

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final NurseUserDetailsService userDetailsService;

    public JwtTokenFilter(JwtUtil jwtUtil, NurseUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}