package com.mindhub.XNHomeBanking;

import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class  XnHomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(XnHomeBankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientsRepositories clientsRepositories){
		return args -> {
			Client cliente1 = new Client("Melba","Morel","Melba@minhub.com");
			System.out.println(cliente1);
			clientsRepositories.save(cliente1);
			System.out.println(cliente1);
		};
	}

}
