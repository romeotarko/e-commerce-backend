package com.ecomercebackend.ecomercebackend.service;

import com.ecomercebackend.ecomercebackend.models.Role;
import com.ecomercebackend.ecomercebackend.models.User;
import com.ecomercebackend.ecomercebackend.repository.RoleRepository;
import com.ecomercebackend.ecomercebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding to user {} role {} ", username, roleName   );
       User user=userRepository.findByUsername(username);
       Role role=roleRepository.findByName(roleName);
       user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Get user {} from database", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Get Users {} from database", userRepository.findAll());
        return userRepository.findAll();
    }
}
