package com.boot.webscraping;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.boot.webscraping.service.ScrapperServiceImpl;

@SpringBootApplication
@ComponentScan("com.boot.webscraping")
public class SpringBootWebScrapingApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringBootWebScrapingApplication.class, args);
	}

}
