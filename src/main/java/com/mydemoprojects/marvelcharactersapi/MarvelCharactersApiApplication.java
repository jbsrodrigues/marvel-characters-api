package com.mydemoprojects.marvelcharactersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MarvelCharactersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelCharactersApiApplication.class, args);
	}

}
