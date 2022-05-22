package com.ecomercebackend.ecomercebackend.security;


import com.ecomercebackend.ecomercebackend.Exceptions.EcommerceApplicationException;
import com.ecomercebackend.ecomercebackend.models.User;
import com.ecomercebackend.ecomercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EcommerceApplicationException("User Not Found with username: " + username, HttpStatus.NOT_FOUND));
        return UserDetailsImpl.build(user);
    }
}
