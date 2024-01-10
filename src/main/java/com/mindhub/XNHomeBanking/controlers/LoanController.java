package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.Service.*;
import com.mindhub.XNHomeBanking.dto.LoanAplicationDTO;
import com.mindhub.XNHomeBanking.dto.LoanDTO;
import com.mindhub.XNHomeBanking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
        ClientLoan clientLoan = new ClientLoan(loanAplicationDTO.getAmount()*1.2,loanAplicationDTO.getPayments());
        Account accountDestinationNumber = accountService.findByNumber(loanAplicationDTO.getDestinyAccount());

        if (loanAplicationDTO.getAmount() <= 0 )
        //||
                //loanAplicationDTO.getPayments() == null ||
                //loanAplicationDTO.getDestinyAccount().isBlank())
        {
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
        loan.addClientLoan(clientLoan);

        accountDestinationNumber.setBalance(accountDestinationNumber.getBalance()+loanAplicationDTO.getAmount());
        accountService.saveAccount(accountDestinationNumber);
        clientLoanService.saveClientLoan(clientLoan);

        Transaction loanCreditTransaction = new Transaction(TransactionType.CREDIT, loanAplicationDTO.getAmount(),loan.getName()+"Loan Aprovated.", LocalDateTime.now());
        accountDestinationNumber.addTransaction(loanCreditTransaction);
        transactionService.saveTransaction(loanCreditTransaction);


        accountDestinationNumber.setBalance(accountDestinationNumber.getBalance()+ loanAplicationDTO.getAmount());
        accountService.saveAccount(accountDestinationNumber);



        return new ResponseEntity<>("Loans successfully completed",HttpStatus.CREATED);
    };


}//Aca finaliza la Class LoanController
