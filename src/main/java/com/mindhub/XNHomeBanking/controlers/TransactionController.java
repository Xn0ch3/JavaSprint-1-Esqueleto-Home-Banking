package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.Service.AccountService;
import com.mindhub.XNHomeBanking.Service.TransactionService;
import com.mindhub.XNHomeBanking.dto.AccountDTO;
import com.mindhub.XNHomeBanking.dto.ClientDTO;
import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.models.Transaction;
import com.mindhub.XNHomeBanking.models.TransactionType;
import com.mindhub.XNHomeBanking.repositories.AccountRepositories;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import com.mindhub.XNHomeBanking.repositories.TransactionRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientsRepositories clientsRepositories;

    @Autowired
    private AccountService accountService;

    @Transactional
    @PostMapping("/transactions")
    //Debe recibir el monto, la descripción, número de cuenta de origen y número de cuenta de destino como parámetros de solicitud
    public ResponseEntity<String> createTransactions(@RequestParam double amount,
                                                     @RequestParam String description ,
                                                    @RequestParam String accountOrigen,
                                                    @RequestParam String accountDestiny,
                                                    Authentication authentication){

        //Verificar que exista la cuenta de origen
       Account originAccount = accountService.findByNumber(accountOrigen);
        //Verificar que exista la cuenta de destino
       Account destinyAccount = accountService.findByNumber((accountDestiny));

        //Verificar que los parámetros no estén vacíos
        if (accountOrigen.isBlank()){
           return new ResponseEntity<>("the Origin account cannot be empty.", HttpStatus.FORBIDDEN);
       }
        if (accountOrigen == null){
            return new ResponseEntity<>("the Origin account cannot be empty.", HttpStatus.FORBIDDEN);
        }

        if (accountDestiny.isBlank()){
            return new ResponseEntity<>("the Destiny account cannot be empty.", HttpStatus.FORBIDDEN);
        }
        if (accountDestiny == null){
            return new ResponseEntity<>("the Destiny account cannot be empty.", HttpStatus.FORBIDDEN);
        }

        if (amount <= 0 ){
            return new ResponseEntity<>("the amount cannot be less than 0.", HttpStatus.FORBIDDEN);
        }

        if (description.isBlank()){
            return new ResponseEntity<>("the Description cannot be empty.", HttpStatus.FORBIDDEN);
        }
        if (description == null){
            return new ResponseEntity<>("the Description cannot be empty.", HttpStatus.FORBIDDEN);
        }

        //Verificar que los números de cuenta no sean iguales
       if (accountOrigen.equals(accountDestiny)){
           return new ResponseEntity<>("the accounts cannot be equals",HttpStatus.FORBIDDEN);
       }

        //Verificar que la cuenta de origen pertenezca al cliente autenticado
       if (!originAccount.getClient().getEmail().equals(authentication.getName())){
           return new ResponseEntity<>("the Origin account does not exist.",HttpStatus.FORBIDDEN);
       }

       //Verificar que la cuenta de origen tenga el monto disponible.
        if (originAccount.getBalance()< amount){
            return new ResponseEntity<>("the transfer amount exceeds your account balance.",HttpStatus.FORBIDDEN);
        }

        //Se deben crear dos transacciones, una con el tipo de transacción “DEBIT” asociada a la cuenta
        // de origen y la otra con el tipo de transacción “CREDIT” asociada a la cuenta de destino.
        Transaction transactionDebit = new Transaction(TransactionType.DEBIT, amount, description, LocalDateTime.now());
        Transaction transactionCredit = new Transaction(TransactionType.CREDIT, amount, description, LocalDateTime.now());

        //A la cuenta de origen se le restará el monto indicado en la petición y a la cuenta de destino se le sumará el mismo monto.
        originAccount.setBalance(originAccount.getBalance()-amount);
        destinyAccount.setBalance(destinyAccount.getBalance()+amount);

        //Se agregan las peticiones a las cuentas.
        originAccount.addTransaction(transactionDebit);
        destinyAccount.addTransaction(transactionCredit);

        //Se guardan las transaction en el Respositories Transaction.
        transactionService.saveTransaction(transactionDebit);
        transactionService.saveTransaction(transactionCredit);

        //Se guardan las transaction en el Respositories Account.
        accountService.saveAccount(originAccount);
        accountService.saveAccount(destinyAccount);


        return new ResponseEntity<>("Transfer successfully completed",HttpStatus.CREATED);

    }

}//Class TransactionController Ending...
