package com.boot.webscraping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.boot.webscraping.service.ScrapperService;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	@Autowired
	ScrapperService service;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		service.getArticles();
	}

}
