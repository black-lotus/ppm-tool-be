package id.dondon.ppmt.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/docs/v2/api-docs", "/v2/api-docs");
    registry.addRedirectViewController("/docs/swagger-resources/configuration/ui",
        "/swagger-resources/configuration/ui");
    registry.addRedirectViewController("/docs/configuration/security", "/configuration/security");
    registry.addRedirectViewController("/docs/swagger-resources", "/swagger-resources");
    registry.addRedirectViewController("/docs", "/docs/swagger-ui.html");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/docs/**").addResourceLocations("classpath:/META-INF/resources/");
  }

}
