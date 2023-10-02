package br.com.tech4me.musica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MusicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicaApplication.class, args);
	}

}
