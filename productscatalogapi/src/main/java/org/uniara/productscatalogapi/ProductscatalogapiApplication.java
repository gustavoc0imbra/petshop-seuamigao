package org.uniara.productscatalogapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Products catalog API - PetShop", version = "1.0", description = "API to manage/read products catalog"))
public class ProductscatalogapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductscatalogapiApplication.class, args);
	}

}
