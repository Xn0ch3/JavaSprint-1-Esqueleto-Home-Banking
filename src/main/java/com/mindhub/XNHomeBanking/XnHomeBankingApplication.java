package com.mindhub.XNHomeBanking;

import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.repositories.AccountRepositories;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class  XnHomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(XnHomeBankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientsRepositories clientsRepositories,
									  AccountRepositories accountRepositories){

		return args -> {
			Client cliente1 = new Client("Melba","Morel","Melba@minhub.com");
			Client cliente2 = new Client("Xavier", "Nochelli", "Xavier@minhub.com");
			System.out.println(cliente1); //sout sin guardar id = null
			clientsRepositories.save(cliente1);
			clientsRepositories.save(cliente2);

			Account cuenta1 = new Account("VIN-001", LocalDate.now(), 5000.00);
			Account cuenta2 = new Account("VIN-002", LocalDate.now().plusDays(1), 7500.00);
			cliente1.addAccount(cuenta1);
			cliente1.addAccount(cuenta2);
			System.out.println(cuenta1);
			accountRepositories.save(cuenta1);
			accountRepositories.save(cuenta2);
			System.out.println(cuenta1);
			System.out.println(cuenta2);
			System.out.println(cliente1); //Cliente guardado con las cuentas.

			//Cliente N°2


			Account cuenta3 = new Account("VIN-003", LocalDate.now(), 80000.00);
			Account cuenta4 = new Account("VIN-004", LocalDate.now(), 150000.00);


			cliente2.addAccount(cuenta3);
			cliente2.addAccount(cuenta4);

			accountRepositories.save(cuenta3);
			accountRepositories.save(cuenta4);

			System.out.println(cliente2);
		};
	}

}
