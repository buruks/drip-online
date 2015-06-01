package org.drip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:data-access.properties")
public class ApplicationContext {
	public static void main(String[] args) {
	    SpringApplication.run(ApplicationContext.class, args);
	}
}
