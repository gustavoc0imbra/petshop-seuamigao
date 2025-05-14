package org.uniara.usersapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Users API - PetShop", version = "1.0", description = "API to manage/read users and provide the JWT"))
public class UsersapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersapiApplication.class, args);
	}

}
