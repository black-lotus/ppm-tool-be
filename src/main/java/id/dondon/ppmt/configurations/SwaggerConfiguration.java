package id.dondon.ppmt.configurations;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  private static final String HEADER = "header";
  private static final String STRING = "string";
  private static final String AUTH = "Authorization";

  @Bean
  public Docket init() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("id.dondon.ppmt.rest"))
        .paths(regex("/.*"))
        .build()
        .globalOperationParameters(Collections.singletonList(
            new ParameterBuilder()
                .name(AUTH)
                .parameterType(HEADER)
                .modelRef(new ModelRef(STRING))
                .required(true)
                .defaultValue("Bearer eyJhbGciOiJIUzUxMiJ9.eyJmdWxsX25hbWUiOiJkb25kb24iLCJpZCI6IjEiLCJleHAiOjE1ODgzNDEzNjgsImlhdCI6MTU4ODM0MTMzOCwidXNlcm5hbWUiOiJkb25kb25AaWQifQ.HSUpcdqptY5gZ7pf7yzfqhkNLX0-FO9LKwG-P1CQhF4ibhda_oHFywEUfnY7iBxm_SbiNX2NrJ9kwzcCJ3GH2A")
                .description("Json Web Token Authentication")
                .build()
        ))
        .genericModelSubstitutes(DeferredResult.class, ResponseEntity.class);
  }

}
