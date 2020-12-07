package br.com.padaria.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket detalheApi() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.select().apis(RequestHandlerSelectors.basePackage("br.com.padaria")).paths(PathSelectors.any())
				.build().apiInfo(this.informacoesApi().build());

		return docket;
	}
 
	private ApiInfoBuilder informacoesApi() {
		 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("Api-Padaria");
		apiInfoBuilder.description("Documento do projeto de Márcio Ricardo.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Termo de uso: Estudo da disciplina Desenvolvimento web com o professor Carlos Barbosa.");
		apiInfoBuilder.license("Professor Carlos Barbosa");
		apiInfoBuilder.licenseUrl("http://www.ciceroednilson.com.br");
		apiInfoBuilder.build();
 
		return apiInfoBuilder;
}
	
}
