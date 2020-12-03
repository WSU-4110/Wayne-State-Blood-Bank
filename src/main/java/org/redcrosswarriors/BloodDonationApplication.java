package org.redcrosswarriors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableJpaRepositories("org.redcrosswarriors.repository")
@EnableSwagger2
public class 	BloodDonationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodDonationApplication.class, args);
	}

   @Bean
   public Docket productApi() {
      return new Docket(DocumentationType.SWAGGER_2).select()
		 .apis(
			 RequestHandlerSelectors.basePackage("org.redcrosswarriors")	 
		 )
		 .build();
   }

 
}
