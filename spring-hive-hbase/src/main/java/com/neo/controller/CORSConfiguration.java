package com.neo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class CORSConfiguration extends WebMvcConfigurerAdapter{
   public void addCorsMappings(CorsRegistry  registry) {
       registry
       .addMapping("/cors/**")
       .allowedMethods("*")
       .allowedOrigins("*")
       .allowedHeaders("*");

   }
}
