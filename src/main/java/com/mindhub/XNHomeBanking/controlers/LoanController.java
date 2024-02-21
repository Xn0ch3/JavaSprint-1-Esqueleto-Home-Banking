package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.dto.LoanAdminDTO;
import com.mindhub.XNHomeBanking.dto.LoanAplicationDTO;
import com.mindhub.XNHomeBanking.dto.LoanDTO;
import com.mindhub.XNHomeBanking.models.*;
import com.mindhub.XNHomeBanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.mindhub.XNHomeBanking.models.RoleType.ADMIN;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientLoanService clientLoanService;


    @GetMapping("/loans")
    public List<LoanDTO> getLoansDTO(){
        return loanService.getAllLoanDTO();
    }
    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createLoans(
            Authentication authentication,
            @RequestBody LoanAplicationDTO loanAplicationDTO) {

        Client client = clientService.getAutenticatedClient(authentication.getName());
        Loan loan = loanService.findById(loanAplicationDTO.getId());
        ClientLoan clientLoan = new ClientLoan(loanAplicationDTO.getAmount() * loan.getInterestRate(), loanAplicationDTO.getPayments());
        Account accountDestinationNumber = accountService.findByNumber(loanAplicationDTO.getDestinyAccount());

        if (loanAplicationDTO.getAmount() <= 0 ) {
            return new ResponseEntity<>("You must select a available options.", HttpStatus.FORBIDDEN);
        }
        if (loanAplicationDTO.getAmount()>= loanService.findById(loanAplicationDTO.getId()).getMaxAmount()){
            return new ResponseEntity<>("This amount is not correct.",HttpStatus.FORBIDDEN);
        }
        if (!loan.getPayments().contains(loanAplicationDTO.getPayments())){
            return new ResponseEntity<>("The Payment is not correct.", HttpStatus.FORBIDDEN);
        }
        if (accountDestinationNumber == null){
            return new ResponseEntity<>("The Account Destiny not exist.",HttpStatus.FORBIDDEN);
        }
        if (loan == null){
            return new ResponseEntity<>("The loan not exist." , HttpStatus.FORBIDDEN);
        }


        client.addClientLoan(clientLoan);
        // Agregar el préstamo al objeto de préstamo
        loan.addClientLoan(clientLoan);
        // Guardar el préstamo en el servicio de préstamos del cliente
        clientLoanService.saveClientLoan(clientLoan);
        // Crear una transacción de crédito para el préstamo
        Transaction loanCreditTransaction = new Transaction(TransactionType.CREDIT, loanAplicationDTO.getAmount(),loan.getName()+"Loan Aprovated.", LocalDateTime.now(), 0.0);

        // Establecer el saldo después de la transacción directamente
        loanCreditTransaction.setAccountBalance(accountDestinationNumber.getBalance() + loanAplicationDTO.getAmount());

        // Agregar la transacción al número de cuenta de destino
        accountDestinationNumber.addTransaction(loanCreditTransaction);

        // Guardar la transacción en el servicio de transacciones
        transactionService.saveTransaction(loanCreditTransaction);

        // Actualizar el saldo de la cuenta de destino
        accountDestinationNumber.setBalance(accountDestinationNumber.getBalance()+loanAplicationDTO.getAmount());

        // Guardar la cuenta de destino en el servicio de cuentas
        accountService.saveAccount(accountDestinationNumber);

        return new ResponseEntity<>("Loans successfully completed",HttpStatus.CREATED);
    };

    @Transactional
    @PostMapping("/loans/admin")
    public ResponseEntity<Object> loansAdmin (Authentication authentication,
                                              @RequestBody LoanAdminDTO loanAdminDTO){

        Client client = clientService.findByEmail(authentication.getName());
        Set<Integer> payments = loanAdminDTO.getPayments();
        if(loanAdminDTO.getName().isBlank()){
            return ResponseEntity.badRequest().body("The Name cannot blank.");
        }
        if (loanAdminDTO.getInterestRate() == 0 ){
            return ResponseEntity.badRequest().body("The Interest cannot be 0.");
        }
        if(loanAdminDTO.getMaxAmount() <= 0 ){
            return ResponseEntity.badRequest().body("The Max amount cannot be 0.");
        }
        if (payments.size() == 0 ){
            return ResponseEntity.badRequest().body("The payments is empty.");
        }
        if (client.getRole() != ADMIN){
            return new ResponseEntity<>("No eres administrador", HttpStatus.FORBIDDEN);
        }


        Loan loanAdmin = new Loan(loanAdminDTO.getName(), loanAdminDTO.getMaxAmount(), loanAdminDTO.getPayments(),loanAdminDTO.getInterestRate());
        loanService.saveLoanAd(loanAdmin);

        return new ResponseEntity<>("Loans Admin successfully completed",HttpStatus.CREATED);

    };

}//Aca finaliza la Class LoanController
