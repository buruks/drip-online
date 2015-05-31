package org.drip;

import org.drip.config.DataConfig;
import org.drip.config.ThymeleafConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:data-access.properties")
public class Application {
	public static void main(String[] args) {
	    SpringApplication app = new SpringApplication(ThymeleafConfig.class, DataConfig.class);
	    app.setShowBanner(false);
	    app.run(args);
	}
}
