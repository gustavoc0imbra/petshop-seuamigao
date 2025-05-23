package org.uniara.paymentsapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Orders API - PetShop", version = "1.0", description = "API to manage/read orders"))
public class PaymentsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApiApplication.class, args);
	}

}
