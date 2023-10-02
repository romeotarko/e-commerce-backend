package com.ecommercebackend.ecommercebackend;

import com.ecommercebackend.ecommercebackend.models.Role;
import com.ecommercebackend.ecommercebackend.repository.RoleRepository;
import com.ecommercebackend.ecommercebackend.models.ERoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class EcommerceBackendApplication implements CommandLineRunner  {


	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackendApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (roleRepository.findByName(ERoleType.ROLE_USER).isEmpty()) {
			roleRepository.saveAndFlush(new Role(ERoleType.ROLE_USER));
		}
		if (roleRepository.findByName(ERoleType.ROLE_ADMIN).isEmpty()) {
			roleRepository.saveAndFlush(new Role(ERoleType.ROLE_ADMIN));
		}
	}
}
