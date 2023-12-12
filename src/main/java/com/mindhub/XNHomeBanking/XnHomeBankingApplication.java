package com.mindhub.XNHomeBanking;

import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.models.Transaction;
import com.mindhub.XNHomeBanking.models.TransactionType;
import com.mindhub.XNHomeBanking.repositories.AccountRepositories;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import com.mindhub.XNHomeBanking.repositories.TransactionRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class  XnHomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(XnHomeBankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientsRepositories clientsRepositories,
									  AccountRepositories accountRepositories,
									  TransactionRepositories transactionRepositories){

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

			//Transacciones, se generan para el cliente1.

			Transaction transaction1 = new Transaction(TransactionType.DEBIT, 550.000, "Haberes Mensuales", LocalDateTime.now() );
			Transaction transaction2 = new Transaction(TransactionType.CREDIT, 125.000, "Pago Tarjeta Credito",LocalDateTime.now() );
			Transaction transaction3 = new Transaction(TransactionType.DEBIT, 85.000, "Compra",LocalDateTime.now());
			Transaction transaction4 = new Transaction(TransactionType.DEBIT, 550.000, "Haberes Mensuales", LocalDateTime.now());
			//Se guardan las Transacciones en las cuentas correspondientes.
			cuenta1.addTransaction(transaction1);
			cuenta1.addTransaction(transaction2);
			cuenta2.addTransaction(transaction3);
			cuenta2.addTransaction(transaction4);
			//Se guardan las transacciones en el Repositorio de Transacciones.
			transactionRepositories.save(transaction1);
			transactionRepositories.save(transaction2);
			transactionRepositories.save(transaction3);
			transactionRepositories.save(transaction4);

			//Se generan las Transacciones para el Cliente2
			Transaction transaction5 = new Transaction(TransactionType.DEBIT, 9750.00, "Compra Online", LocalDateTime.now());
			Transaction transaction6 = new Transaction(TransactionType.CREDIT, 5200.00, "Reembolso", LocalDateTime.now());
			Transaction transaction7 = new Transaction(TransactionType.DEBIT, 11200.00, "Pago de Factura", LocalDateTime.now());
			Transaction transaction8 = new Transaction(TransactionType.CREDIT, 1000.00, "Bonificación", LocalDateTime.now());
			//Se guardan las Transacciones en las cuentas correspondientes.
			cuenta3.addTransaction(transaction5);
			cuenta3.addTransaction(transaction6);
			cuenta3.addTransaction(transaction7);
			cuenta3.addTransaction(transaction8);
			//Se guardan las Transacciones en el Repositorio de Transacciones.
			transactionRepositories.save(transaction5);
			transactionRepositories.save(transaction6);
			transactionRepositories.save(transaction7);
			transactionRepositories.save(transaction8);
		};
	}

}
