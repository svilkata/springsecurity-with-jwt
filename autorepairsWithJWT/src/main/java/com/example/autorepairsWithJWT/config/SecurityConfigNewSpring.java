/*package com.example.autorepairsWithJWT.config;

import com.example.autorepairsWithJWT.model.enums.UserRoleEnum;
import com.example.autorepairsWithJWT.utils.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfigNewSpring {
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfigNewSpring(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // Enabling CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        // Set permissions on endpoints
        http
                // I) *Public endpoints*
                .authorizeRequests()
                // everyone can login and register
                .antMatchers("/", "/users/login/**", "/users/register/**").permitAll()
                //other specific public endpoints
                .antMatchers(HttpMethod.GET, "/spareparts/rims/all", "/spareparts/rims/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/spareparts/tyres/all", "/spareparts/tyres/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/spareparts/filters/all", "/spareparts/filters/{id}").permitAll()

                // II) *Private endpoints*
                //specific authorized endpoints only by user with admin role
                .antMatchers(HttpMethod.DELETE, "/spareparts/rims/{id}").hasRole(UserRoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/spareparts/tyres/{id}").hasRole(UserRoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/spareparts/filters/{id}").hasRole(UserRoleEnum.ADMIN.name())

                // Private endpoints for all logged users (the POST and PUT http requests for creating and updating auto-parts)
                .anyRequest()
                .authenticated()
                .and();

        // Add JWT token filter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //Access to the authentication manager - by default it is not publicly accessible
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    // Used by Spring Security if CORS is enabled.
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

 */