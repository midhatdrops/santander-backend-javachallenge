package br.com.midhatdrops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CicloDePagmentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CicloDePagmentosApplication.class, args);
	}

}
