package com.example.autorepairsWithJWT.service;

import com.example.autorepairsWithJWT.config.mapstruct.StructMapper;
import com.example.autorepairsWithJWT.exception.NotFoundUserException;
import com.example.autorepairsWithJWT.exception.ConflictUsernameEmailException;
import com.example.autorepairsWithJWT.init.InitializableService;
import com.example.autorepairsWithJWT.model.dto.userregister.UserDtoResponse;
import com.example.autorepairsWithJWT.model.dto.userregister.UserRegisterRequest;
import com.example.autorepairsWithJWT.model.dto.userregister.UserRoleObject;
import com.example.autorepairsWithJWT.model.entity.UserEntity;
import com.example.autorepairsWithJWT.model.entity.UserRoleEntity;
import com.example.autorepairsWithJWT.model.enums.UserRoleEnum;
import com.example.autorepairsWithJWT.repository.UserRepository;
import com.example.autorepairsWithJWT.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements InitializableService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final StructMapper structMapper;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder, StructMapper structMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.structMapper = structMapper;
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

    public UserEntity registerNewUser(UserRegisterRequest userRegisterRequest) {
        if (userRepository.existsUserEntityByUsername(userRegisterRequest.getUsername())) {
            throw new ConflictUsernameEmailException("Username %s is already occupied!".formatted(userRegisterRequest.getUsername()));
        }

        if (userRepository.existsUserEntityByEmail(userRegisterRequest.getEmail())) {
            throw new ConflictUsernameEmailException("Email %s is already occupied!".formatted(userRegisterRequest.getEmail()));
        }

        List<UserRoleEntity> userRoleEntities = new ArrayList<>();

        for (UserRoleObject userRoleObject : userRegisterRequest.getUserRoles()) {
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
                        setEmail(userRegisterRequest.getEmail()).
                        setUsername(userRegisterRequest.getUsername()).
                        setFirstName(userRegisterRequest.getFirstName()).
                        setLastName(userRegisterRequest.getLastName()).
                        setPassword(passwordEncoder.encode(userRegisterRequest.getPassword())).
                        setUserRoles(userRoleEntities);

        return userRepository.save(newUser);
    }

    public UserDtoResponse findUserById(Long userId) {
        Optional<UserEntity> userEntityOpt = this.userRepository.findById(userId);

        return
                userEntityOpt
                        .map(usr -> structMapper.userEntityToUserDtoResponse(usr))
                        .orElseThrow(() -> new NotFoundUserException("User with id " + userId + " not found"));
    }

    public List<UserDtoResponse> findAllUsers() {
        List<UserEntity> all = this.userRepository.findAll();

        return all.stream()
                .map(usr -> structMapper.userEntityToUserDtoResponse(usr))
                .collect(Collectors.toList());
    }

    public Optional<UserEntity> findUserByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    public void deleteUserById(Long userId) {
        try {
            this.userRepository.deleteById(userId);
        } catch (RuntimeException e){
            throw new NotFoundUserException("Cannot delete user with id %d as such user does not exists".formatted(userId));
        }
    }

//    public Optional<UserEntity> findUserByUsername(String username) {
//        return this.userRepository.findByUsername(username);
//    }
}
