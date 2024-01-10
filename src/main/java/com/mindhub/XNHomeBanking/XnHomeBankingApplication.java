package com.mindhub.XNHomeBanking;

import com.mindhub.XNHomeBanking.models.*;
import com.mindhub.XNHomeBanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.print.attribute.SetOfIntegerSyntax;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class  XnHomeBankingApplication {

	//@Autowired
	//private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(XnHomeBankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientsRepositories clientsRepositories,
									  AccountRepositories accountRepositories,
									  TransactionRepositories transactionRepositories,
									  LoanRepositories loanRepositories,
									  ClientLoanRepositories clientLoanRepositories,
									  CardRepositories cardRepositories){

		return args -> {
			/*Client cliente1 = new Client("Melba","Morel","Melba@mindhub.com", passwordEncoder.encode("melba2023"));
			Client cliente2 = new Client("Xavier", "Nochelli", "Xavier@mindhub.com",passwordEncoder.encode("xavier2023"));;
			Client admin = new Client("Alvaro", "Orquera","Alvaro@mindhub.com", passwordEncoder.encode("alvaro2023"));
			admin.setRole(RoleType.ADMIN);

			System.out.println(cliente1); //sout sin guardar id = null
			clientsRepositories.save(cliente1);
			clientsRepositories.save(cliente2);
			clientsRepositories.save(admin);


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

			Transaction transaction1 = new Transaction(TransactionType.DEBIT, -550000.00, "Tarjeta Credito", LocalDateTime.now() );
			Transaction transaction2 = new Transaction(TransactionType.CREDIT, 125000.00, "Haberes Mensuales",LocalDateTime.now() );
			Transaction transaction3 = new Transaction(TransactionType.DEBIT, -85.000, "Compra",LocalDateTime.now());
			Transaction transaction4 = new Transaction(TransactionType.DEBIT, -550.000, "Haberes Mensuales", LocalDateTime.now());
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
			Transaction transaction5 = new Transaction(TransactionType.DEBIT, -9750.00, "Compra Online", LocalDateTime.now());
			Transaction transaction6 = new Transaction(TransactionType.CREDIT, 5200.00, "Reembolso", LocalDateTime.now());
			Transaction transaction7 = new Transaction(TransactionType.DEBIT, -11200.00, "Pago de Factura", LocalDateTime.now());
			Transaction transaction8 = new Transaction(TransactionType.CREDIT, 1000.00, "Bonificación", LocalDateTime.now());
			//Se guardan las Transacciones en las cuentas correspondientes.
			cuenta3.addTransaction(transaction5);
			cuenta3.addTransaction(transaction6);
			cuenta4.addTransaction(transaction7);
			cuenta4.addTransaction(transaction8);
			//Se guardan las Transacciones en el Repositorio de Transacciones.
			transactionRepositories.save(transaction5);
			transactionRepositories.save(transaction6);
			transactionRepositories.save(transaction7);
			transactionRepositories.save(transaction8);

			//Se generan los 3 Loan(Prestamos).
			Loan hipotecario = new Loan("Mortgage", 500000.00, Set.of(12,24,48,60));
			Loan personal = new Loan("Personal", 100000.0,Set.of(6,12,24));
			Loan automotriz = new Loan("Automotive", 300000.00,Set.of(6,12,24,36));

			//Se guardan los Loan(Prestamos).
			loanRepositories.save(hipotecario);
			loanRepositories.save(personal);
			loanRepositories.save(automotriz);

			//Se generan los Loan (préstamos)
			ClientLoan prestamo1 = new ClientLoan(400000.0,60);
			ClientLoan prestamo2 = new ClientLoan(50000.0, 12);
			//Se añaden los Loans al Client.
			cliente1.addClientLoan(prestamo1);
			cliente1.addClientLoan(prestamo2);
			//se guardan Loan en intermediario ClientLoan.
			hipotecario.addClientLoan(prestamo1);
			personal.addClientLoan(prestamo2);
			//Se agregan Loans en Repositorio.
			clientLoanRepositories.save(prestamo1);
			clientLoanRepositories.save(prestamo2);

			//Cliente 2 se generan Loans.
			ClientLoan prestamo3 = new ClientLoan(100000.0,24);
			ClientLoan prestamo4 = new ClientLoan(200000.0,36);
			//Se guardan los Loans en los Clientes.
			cliente2.addClientLoan(prestamo3);
			cliente2.addClientLoan(prestamo4);
			//Se guardan los Loans en ClientLoans para generar las Relaciones.
			personal.addClientLoan(prestamo3);
			automotriz.addClientLoan(prestamo4);
			//Se agregan Loans a Repositories.
			clientLoanRepositories.save(prestamo3);
			clientLoanRepositories.save(prestamo4);

			//Creamos Card para cliente1.
			Card melvaDebito = new Card("3325-6745-7876-4445", "Melba Morel", 990, LocalDate.of(2021,04,26), LocalDate.now().plusYears(5), CardColor.GOLD, CardType.DEBIT);
			Card melvaCredito = new Card("2234-6745-5528-7888", "Melba Morel", 750, LocalDate.of(2021,04,26), LocalDate.now().plusYears(5), CardColor.TITANIUM,CardType.CREDIT);
			//Se agregan las Cards al Cliente.
			cliente1.addCard(melvaDebito);
			cliente1.addCard(melvaCredito);
			//Se agregan las Cards a CardRepositories.
			cardRepositories.save(melvaDebito);
			cardRepositories.save(melvaCredito);
			System.out.println(melvaCredito);

			//Creamos Card para Cliente2.
			Card xavierCredito = new Card("4522-6526-8745-6324","Xavier Nochelli", 654, LocalDate.of(2022,05, 04), LocalDate.now().plusYears(5),CardColor.TITANIUM, CardType.CREDIT);
			//Se Agregan Card al Cliente2.
			cliente2.addCard(xavierCredito);
			//Se agrega la Card a CardRepositories.
			cardRepositories.save(xavierCredito);*/
		};
	}

}
