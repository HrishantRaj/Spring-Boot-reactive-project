package com.reactive.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootApplication
public class SpringProjectApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringProjectApplication.class, args);
		FluxAndMono fluxAndMono = new FluxAndMono();
		fluxAndMono.getFlux()
				.subscribe(name -> {
					System.out.println(name);
				});

		fluxAndMono.getMono()
				.subscribe( name -> {
					System.out.println("Mono " + name);
				});
	}

}
