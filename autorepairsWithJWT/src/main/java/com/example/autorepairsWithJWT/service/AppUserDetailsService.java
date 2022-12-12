package com.example.autorepairsWithJWT.service;

import com.example.autorepairsWithJWT.model.entity.UserEntity;
import com.example.autorepairsWithJWT.model.entity.UserRoleEntity;
import com.example.autorepairsWithJWT.repository.UserRepository;
import com.example.autorepairsWithJWT.user.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// NOTE: This is not annotated as @Service, because we will return it as a bean. //TODO: we will see that
@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.
                findByUsername(username).
                map(this::mapUserEntityToUserDetails).
                orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    private UserDetails mapUserEntityToUserDetails(UserEntity userEntity) {
        return new AppUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.
                        getUserRoles().stream().map(this::mapRole).toList()
        );
    }

    private GrantedAuthority mapRole(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.
                        getUserRole().name());
    }
}
