package com.onebox.shopping.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		Contact contact = new Contact("OneBox", "info@onebox.com", "http://omneboxtds.com");
		ApiInfo apiInfo = new ApiInfo("OneBox Shopping cart", "OneBox app", "1.0.0",
				"http://termsOfServiceUrl.oneboxtds.com", contact, "OneBox(c)", "http://oneboxtds.com",
				new ArrayList<>());

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.select().apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build();
	}

}
