package com.zzb.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicates;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/**
	 * 可以定义多个组，比如本类中定义把test和demo区分开了 （访问页面就可以看到效果了）
	 * http://localhost:8080/swagger-ui.html
	 */
	@Bean
	public Docket testApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("test")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.apiInfo(testApiInfo())
				.pathMapping("/")// base，最终调用接口后会和paths拼接在一起
				.select()
				.paths(PathSelectors.regex("/api/.*"))
				.build();
		return docket;
	}

	private ApiInfo testApiInfo() {
		return new ApiInfoBuilder()
				.title("Test相关接口")
				.description("Test相关接口，主要用于测试.")
                .version("1.0")
				.build();
	}

}
