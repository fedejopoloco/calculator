package com.tenpo.calculator.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Calculator")
				.description("Calculate the percentage of the sum between 2 numbers")
				.version("1.0").build();
	}

	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		return new InternalResourceViewResolver();
	}


}
