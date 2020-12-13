package com.lti.coding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CodingApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/lti");
		SpringApplication.run(CodingApplication.class, args);
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis((Predicate<RequestHandler>) RequestHandlerSelectors.any())              
          .paths((Predicate<String>) PathSelectors.any())                          
          .build()
          .pathMapping("/")
          .enableUrlTemplating(false);                                           
    }

}
