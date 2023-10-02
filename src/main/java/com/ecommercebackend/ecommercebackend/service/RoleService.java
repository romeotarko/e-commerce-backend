package com.ecommercebackend.ecommercebackend.service;

import com.ecommercebackend.ecommercebackend.Exceptions.EcommerceApplicationException;
import com.ecommercebackend.ecommercebackend.models.Role;
import com.ecommercebackend.ecommercebackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public Role create(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.saveAndFlush(role);
    }

    public void delete(Long id) {
        checkIfExists(id);
        roleRepository.deleteById(id);
    }

    private void checkIfExists(Long id) {
        if (!roleRepository.existsById(id)) {
            log.debug("Role with id {} does not exist!", id);
            throw new EcommerceApplicationException("Role with id: " + id + " does not exist!", HttpStatus.BAD_REQUEST);
        }
    }
}
