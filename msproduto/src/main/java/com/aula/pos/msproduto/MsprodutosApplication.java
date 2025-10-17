package com.aula.pos.msproduto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsprodutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsprodutosApplication.class, args);
	}

}
