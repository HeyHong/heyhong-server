package com.heyhong.HeyHong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class HeyHongApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeyHongApplication.class, args);
	}

}
