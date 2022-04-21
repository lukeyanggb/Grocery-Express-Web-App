package com.CS6310.Team045;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableBatchProcessing
@SpringBootApplication
@EnableCaching
public class Team045Application {

	public static void main(String[] args) {
		SpringApplication.run(Team045Application.class, args);
	}

}
