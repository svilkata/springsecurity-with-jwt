package com.example.autorepairsWithJWT.config;

import com.example.autorepairsWithJWT.repository.UserRepository;
import com.example.autorepairsWithJWT.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    private final AppUserDetailsService appUserDetailsService;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, AppUserDetailsService appUserDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // Get authorization header and validate
        if (!hasAuthorizationBearer(request)) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = getAccessToken(request);
        if (!jwtTokenUtil.validateAccessToken(token)) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        setAuthenticationContext(token, request);
        chain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            return false;
        }

        return true;
    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities());

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getUserDetails(String token) {
        String[] jwtSubject = jwtTokenUtil.getSubject(token).split(",");
//        userDetails.setId(Integer.parseInt(jwtSubject[0]));
//        userDetails.setUsername(jwtSubject[1]);

        UserDetails userDetails = appUserDetailsService.loadUserByUsername(jwtSubject[1]);

        return userDetails;
    }
}
