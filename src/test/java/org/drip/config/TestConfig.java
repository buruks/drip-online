package org.drip.config;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "org.drip")
public class TestConfig {
	@Bean
	public JavaMailSender javaMailSender() {
		return Mockito.mock(JavaMailSender.class);
	}
}
