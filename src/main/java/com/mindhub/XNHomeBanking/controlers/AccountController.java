package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.models.AccountType;
import com.mindhub.XNHomeBanking.service.AccountService;
import com.mindhub.XNHomeBanking.service.ClientService;
import com.mindhub.XNHomeBanking.dto.AccountDTO;
import com.mindhub.XNHomeBanking.dto.TransactionDTO;
import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//RestController marca la clase como un controlador donde cada método devuelve un objeto de dominio en lugar de una vista.
@RestController
//RequestMapping se utiliza para asignar solicitudes web a clases de controlador específicas y/o métodos de controlador.
//Request = Petición, Mapping = asociar. //Asocia una petición.
@RequestMapping("/api")
public class AccountController {
    //Autowired es una anotación utilizada en Spring Boot para habilitar la inyección automática de dependencias.
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;

    @RequestMapping("/all/accounts")
    public List<AccountDTO> getAllAccount() {
        return accountService.getAllAccountsDTO();
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<TransactionDTO> getOneAccount(@PathVariable Long id) {
        return accountService.findById(id);

    }
    //Sprint7
    //Creamos un metodo para Crear cuentas
    @PostMapping("/clients/current/accounts")
    public ResponseEntity<String> CreateAccount(Authentication authentication,
                                                @RequestParam AccountType accountType) {

        Client client = clientService.findByEmail(authentication.getName());
        //Creamos el una condicion para saber si tiene Cuentas, y si tiene más 3.
        if (client.getListAccount().size() > 2 ){
            return new ResponseEntity<>("You already have 3 accounts, it is the maximum per customer.", HttpStatus.FORBIDDEN);
        }
        //Creamos un String para posteriormente asignarle un número aleatorio.
        String numberAccount;
        do{
            numberAccount = "VIN-" + getRandomNumber(00000000 , 99999999);
        }while (accountService.existsByNumber(numberAccount));

        Account account = new Account(numberAccount , LocalDate.now(), (double) 0, true, accountType);
        clientService.saveClient(client);
        client.addAccount(account);
        accountService.saveAccount(account);


        return new ResponseEntity<>("Account Created", HttpStatus.CREATED);
    }

    //Creamos el método gerRandomNumber para generar numeros aleatorios para las cuentas.
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @PatchMapping("/clients/current/accounts/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam Long id , Authentication authentication){
        Client client = clientService.findByEmail(authentication.getName());
        Account account = accountService.findByIdAccount(id);

        if (account.isActive() && account.getClient().getEmail().equals(authentication.getName())){
            if (account.getBalance() == 0 ) {
                accountService.deleteAccount(account);
                return new ResponseEntity<>("Your Account is Delete", HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>("Balance must be $0", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>("client is not authenticated or account is already desactive", HttpStatus.FORBIDDEN);

    }
}//Aca termina la class AccountController.