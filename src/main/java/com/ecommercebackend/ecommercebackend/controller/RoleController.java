package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.models.Role;
import com.ecommercebackend.ecommercebackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return ResponseEntity.ok((roleService.create(role)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}