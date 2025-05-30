package org.uniara.usersapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.uniara.usersapi.model.User;
import org.uniara.usersapi.repository.UserRepository;
import org.uniara.usersapi.service.UserService;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Users API - PetShop", version = "1.0", description = "API to manage/read users and provide the JWT"))
public class UsersapiApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UsersapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (userRepository.count() == 0) {
			User user = new User();
			user.setName("Admin");
			user.setEmail("admin@email.com");
			user.setPassword("admin123");
			user.setStatus(true);

			userRepository.save(user);
		}


	}
}
