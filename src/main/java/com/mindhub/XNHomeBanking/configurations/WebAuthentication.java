package com.mindhub.XNHomeBanking.configurations;

import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
    //Se inyecta la Interfaz que se comunica con la DB,
    @Autowired
    ClientsRepositories clientsRepositories;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {
            //Es una variable de Cliente, que devuelve un cliente mediante la búsqueda de Email, en clienteRepositories.
            Client client = clientsRepositories.findByEmail(inputName);

            if (client != null) {
                //Iniciamos Session con Email y password.
                return new User(client.getEmail(), client.getPassword(),
                        AuthorityUtils.createAuthorityList(client.getRole().toString()));
                //AuthorityUtils: en Sprint Security proporciona metodos de utilidad para trabajar
                //con roles y autoridades en contexto de seguridad,

            } else {

                throw new UsernameNotFoundException("Unknown user: " + inputName);

            }

        });

    }

    //PasswordEncoder se encarga de Encriptar las contraseñas de los Usuarios
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
;
}



}