package com.example.autorepairsWithJWT.config;

import com.example.autorepairsWithJWT.repository.UserRepository;
import com.example.autorepairsWithJWT.service.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        return new AppUserDetailsService(userRepository);
//    }

    public SecurityConfig(AppUserDetailsService appUserDetailsService, JwtTokenFilter jwtTokenFilter) {
        this.appUserDetailsService = appUserDetailsService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> appUserDetailsService.loadUserByUsername(username));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
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
                // define which requests are allowed and which not
                .authorizeRequests()
                // *Our public endpoints*
                // everyone can download static resources (css, js, images)
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // everyone can login and register
                .antMatchers("/", "/users/login/**", "/users/register/**").permitAll()
                //specific endpoints
                .antMatchers(HttpMethod.GET, "/spareparts/rims/all", "/spareparts/rims/{id}").permitAll()
//                antMatchers(HttpMethod.POST, "/spareparts/rims").authenticated().
//                antMatchers(HttpMethod.PUT, "/spareparts/edit/rims/{id}").authenticated().
//                antMatchers(HttpMethod.DELETE, "/spareparts/rims/{id}").authenticated().

                .antMatchers(HttpMethod.GET, "/spareparts/tyres/all", "/spareparts/tyres/{id}").permitAll()
//                antMatchers(HttpMethod.POST, "/spareparts/tyres").authenticated().
//                antMatchers(HttpMethod.PUT, "/spareparts/edit/tyres/{id}").authenticated().
//                antMatchers(HttpMethod.DELETE, "/spareparts/tyres/{id}").authenticated().

                .antMatchers(HttpMethod.GET, "/spareparts/filters/all", "/spareparts/filters/{id}").permitAll()
//                antMatchers(HttpMethod.POST, "/spareparts/filters").authenticated().
//                antMatchers(HttpMethod.PUT, "/spareparts/edit/filters/{id}").authenticated().
//                antMatchers(HttpMethod.DELETE, "/spareparts/filters/{id}").authenticated().

                // pages available only for admins
//                antMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name()).

//         Our private endpoints for logged users
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


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new Pbkdf2PasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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










