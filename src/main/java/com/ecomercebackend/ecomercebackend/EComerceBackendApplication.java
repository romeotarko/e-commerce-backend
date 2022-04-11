package com.ecomercebackend.ecomercebackend;

import com.ecomercebackend.ecomercebackend.models.Role;
import com.ecomercebackend.ecomercebackend.models.User;
import com.ecomercebackend.ecomercebackend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class EComerceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EComerceBackendApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.saveUser(new User(null, "Romeo", "romeo@tarko.com", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Alket", "alket@tarko.com", "1234", new ArrayList<>()));

			userService.addRoleToUser("romeo@tarko.com", "ROLE_ADMIN");
			userService.addRoleToUser("alket@tarko.com", "ROLE_USER");

		};
}
}
