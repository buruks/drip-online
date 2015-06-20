package org.drip.config;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@Configuration
@EnableTransactionManagement
@EntityScan("org.drip.model")
@EnableJpaRepositories(basePackages = "org.drip.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@ComponentScan(basePackages = { "org.drip.services" })
public class TestConfig {
	@Bean
	public JavaMailSender javaMailSender() {
		return Mockito.mock(JavaMailSender.class);
	}
}
