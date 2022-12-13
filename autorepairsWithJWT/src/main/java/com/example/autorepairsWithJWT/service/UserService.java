package com.example.autorepairsWithJWT.service;

import com.example.autorepairsWithJWT.init.InitializableService;
import com.example.autorepairsWithJWT.model.dto.userauth.UserRegisterRequestResponse;
import com.example.autorepairsWithJWT.model.dto.userauth.UserRoleObject;
import com.example.autorepairsWithJWT.model.entity.UserEntity;
import com.example.autorepairsWithJWT.model.entity.UserRoleEntity;
import com.example.autorepairsWithJWT.model.enums.UserRoleEnum;
import com.example.autorepairsWithJWT.repository.UserRepository;
import com.example.autorepairsWithJWT.repository.UserRoleRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements InitializableService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void init() {
        if (userRepository.count() == 0 && userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setUserRole(UserRoleEnum.MODERATOR);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            adminRole = userRoleRepository.save(adminRole);
            moderatorRole = userRoleRepository.save(moderatorRole);
            userRole = userRoleRepository.save(userRole);

            initAdmin(List.of(adminRole, moderatorRole, userRole));
            initModerator(List.of(moderatorRole, userRole));
            initUser(List.of(userRole));
        }
    }

    private void initAdmin(List<UserRoleEntity> roles) {
        UserEntity admin = new UserEntity().
                setUserRoles(roles).
                setFirstName("Admin").
                setLastName("Adminov").
                setEmail("admin@example.com").
                setUsername("admin").
                setPassword(passwordEncoder.encode("topsecrect"));

        userRepository.save(admin);
    }

    private void initModerator(List<UserRoleEntity> roles) {
        UserEntity moderator = new UserEntity().
                setUserRoles(roles).
                setFirstName("Moderator").
                setLastName("Moderatorov").
                setEmail("moderator@example.com").
                setUsername("moderator").
                setPassword(passwordEncoder.encode("topsecrect"));

        userRepository.save(moderator);
    }

    private void initUser(List<UserRoleEntity> roles) {
        UserEntity user = new UserEntity().
                setUserRoles(roles).
                setFirstName("User").
                setLastName("Userov").
                setEmail("user@example.com").
                setUsername("Svilen").
                setPassword(passwordEncoder.encode("topsecrect"));

        userRepository.save(user);
    }

    public Long registerNewUser(UserRegisterRequestResponse userRegisterRequestResponse) {
        // add check for username exists in a DB
        //TODO: exception user already exists
        if (userRepository.existsUserEntityByUsername(userRegisterRequestResponse.getUsername())) {
            return -1L;
        }

        // add check for еmail exists in a DB
        //TODO: exception user email already exists
        if (userRepository.existsUserEntityByEmail(userRegisterRequestResponse.getEmail())) {
            return -2L;
        }

        List<UserRoleEntity> userRoleEntities = new ArrayList<>();

        for (UserRoleObject userRoleObject : userRegisterRequestResponse.getUserRoles()) {
            UserRoleEntity userRoleEntityCurr = null;
            String userRole = userRoleObject.getUserRole();
            switch (userRole) {
                case "ADMIN" -> userRoleEntityCurr = this.userRoleRepository.findById(1L).get();
                case "MODERATOR" -> userRoleEntityCurr = this.userRoleRepository.findById(2L).get();
                case "USER" -> userRoleEntityCurr = this.userRoleRepository.findById(3L).get();
            }

            userRoleEntities.add(userRoleEntityCurr);
        }

        UserEntity newUser =
                new UserEntity().
                        setEmail(userRegisterRequestResponse.getEmail()).
                        setUsername(userRegisterRequestResponse.getUsername()).
                        setFirstName(userRegisterRequestResponse.getFirstName()).
                        setLastName(userRegisterRequestResponse.getLastName()).
                        setPassword(passwordEncoder.encode(userRegisterRequestResponse.getPassword())).
                        setUserRoles(userRoleEntities);

        UserEntity savedUserEntity = userRepository.save(newUser);

        return savedUserEntity.getId();
    }

    public UserEntity findUserById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + userId + " not found"));

    }

    public List<UserEntity> findAllUsers() {
        List<UserEntity> all = this.userRepository.findAll();
        return all;
    }

    public Optional<UserEntity> findUserByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

//    public Optional<UserEntity> findUserByUsername(String username) {
//        return this.userRepository.findByUsername(username);
//    }
}
