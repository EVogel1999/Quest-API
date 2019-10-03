package com.example.Quest.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class QuestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestDemoApplication.class, args);
	}

	/**
	 * CORS configuration for when connecting this API to a client
	 */
	@EnableWebMvc
	@Configuration
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**");
		}
	}
}
