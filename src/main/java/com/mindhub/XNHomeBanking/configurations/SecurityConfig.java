package com.mindhub.XNHomeBanking.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;


@Configuration//Configuration Realiza una configuracion antes de inciar la aplicacion, y nos anticipa que vamos a tener un @Bean por cada metodo


@EnableWebSecurity //Se utiliza para personalizar la configuración de seguridad de una aplicación Spring basada en web. Al utilizar esta anotación, puedes extender la clase WebSecurityConfigurerAdapter
// o implementar la interfaz SecurityConfigurer para configurar aspectos específicos de la seguridad web, como autorización, autenticación, manejo de sesiones,
public class SecurityConfig {
    //Ayuda a Sprint a entender cuales son los componentes que va a ocupar al inicar la app
    //y permite oganizar y configurar los objetos de una clase designada como PasswordEncoder o SegurityFilterChain
 @Bean
    //SecurityFilterChain es un Filtro en cadena para la seguridad.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Modificando las reglas de la seguridad de la app. Como se hace el Login, quienes son ADMIN & CLIENTS.
        http.authorizeHttpRequests(auth ->
                //requestMatchers son las autorizaciones para los accesos.
                auth.requestMatchers("/index.html", "/style.css", "/index.js" , "tailwind.config.js", "/images/**").permitAll()
                        .requestMatchers("/pages/**" , "/JavaScript/**" ,"/api/clients/current","/api/accounts/*/transactions").hasAuthority("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/api/clients" ).permitAll()
                        .requestMatchers( HttpMethod.POST, "/api/clients/current/accounts","/api/clients/current/cards","/api/transactions" ).hasAuthority("CLIENT")
                        .requestMatchers("/admin/**", "/h2-console").hasAuthority("ADMIN")
                        .anyRequest().denyAll());
        //Desabilitamos el CSRF que desactiva el TOKEN xq si no deberiamos solicitarlo y enviarlo
        // cada vez que realizamos una peticion, el filtro para poder acceder a los Headers,
        // Para headers como H2 DataBase,
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        //Para poder accerder a H2DataBase.
        http.headers(header -> header.frameOptions(options -> options.disable()));

        //Para acceder al login que nosotros Creamos.
        http.formLogin( formLogin ->
                formLogin.loginPage("/index.html")
                        .usernameParameter("email")   //Key primer parámetro
                        .passwordParameter("password") //Key Segundo parámetro
                        .loginProcessingUrl("/api/login") //Endpoint para la petición.
                        .successHandler((request, response, authentication) -> clearAuthenticationAttributes(request))
                        .failureHandler(((request, response, exception) -> response.sendError(401, "Client not Fount"))));

        //Es una Exception que da por respuesta a todos los por fuera de FormLogin.
        http.exceptionHandling( exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> response.sendError(403)));


        //Método Logout para cerrar sesion y borrar la Cookies del navegador.
        http.logout(httpSecurityLogoutConfigurer ->
                httpSecurityLogoutConfigurer
                        .logoutUrl("/api/logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())) ;

        http.rememberMe(Customizer.withDefaults());


        return http.build();
    }

    //Método para limpiar banderas (Flags u errores del lado del Serv) los atributos de autenticacion de una Session del Login
    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }

}
