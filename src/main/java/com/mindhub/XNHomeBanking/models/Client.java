package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//JPA = Java Persistance API
//Interfaz = Contrato || Una serie de lineamientios que debemos respetar.

@Entity //Crea una tabla en la base de datos, con los datos de la clase, Hace columnas de las de base de datos sean las propiedades
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Atributos
    private String firstname, lastname, email, password;

    @Enumerated(EnumType.STRING)
   private RoleType role = RoleType.CLIENT;

    //Relación de Uno a Muchos, un cliente puede tener muchas cuentas
    @OneToMany(mappedBy = "client" , fetch = FetchType.EAGER)
    private List<Account> listAccount = new ArrayList<>();

    //Relación de Uno a Muchos, una ClientLoan(ListaDePrestamos) a Client(Clientes)
    @OneToMany(mappedBy = "client" , fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    //Relacion de OneToMany un cliente puede tener varias tarjetas.
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();

    public Client() {
    }

    public Client(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<Account> listAccount) {
        this.listAccount = listAccount;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    //Método para agregar Account(cuentas) a Cliente(clientes)
    public void addAccount(Account account){
        account.setClient(this);
        this.listAccount.add(account);
    }

    //Método para Agregar ClientLoan(ListaDePrestamos) a Client(clientes).
    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setClient(this);
        this.clientLoans.add(clientLoan);
    }

    //Método para Agregar Cards a Clients.
    public void addCard(Card card){
        card.setClient(this);
        this.cards.add(card);
    }

    //ToString


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}// Aca Termina la clase Client
