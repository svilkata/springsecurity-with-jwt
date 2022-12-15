/*
package com.example.autorepairsWithJWT.config;

import com.example.autorepairsWithJWT.model.enums.UserRoleEnum;
import com.example.autorepairsWithJWT.service.AppUserDetailsService;
import com.example.autorepairsWithJWT.utils.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AppUserDetailsService appUserDetailsService;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(AppUserDetailsService appUserDetailsService, JwtTokenFilter jwtTokenFilter) {
        this.appUserDetailsService = appUserDetailsService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                //other specific public endpoints
                .antMatchers(HttpMethod.GET, "/spareparts/rims/all", "/spareparts/rims/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/spareparts/tyres/all", "/spareparts/tyres/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/spareparts/filters/all", "/spareparts/filters/{id}").permitAll()

                // II) *Private endpoints*
                //specific authorized endpoints only by user with admin role (/users/login and /users/register precede the below endpoints /users/**)
                .antMatchers("/users/**").hasRole(UserRoleEnum.ADMIN.name()) //any http method (GET, DELETE in our case)
                .antMatchers(HttpMethod.DELETE, "/spareparts/rims/{id}").hasRole(UserRoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/spareparts/tyres/{id}").hasRole(UserRoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/spareparts/filters/{id}").hasRole(UserRoleEnum.ADMIN.name())

                // Private endpoints for all logged users (the POST and PUT http requests for creating and updating auto-parts)
                .anyRequest()
                .authenticated()
                .and();

        // Add JWT token filter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //Access to the authentication manager - by default it is not publicly accessible
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

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










