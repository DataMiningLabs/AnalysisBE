package com.knhu.shtefan.analysis.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrossOriginConfiguration {

  /**
   * Spring Framework also provides a CorsFilter.
   * In that case, instead of using @CrossOrigin or WebMvcConfigurer#addCorsMappings(CorsRegistry),
   * you can for example declare the filter as following in your Spring Boot application
   *
   * For more details > https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
   *
   * @return FilterRegistrationBean
   */
  @Bean
  public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:3000");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
  }

}
