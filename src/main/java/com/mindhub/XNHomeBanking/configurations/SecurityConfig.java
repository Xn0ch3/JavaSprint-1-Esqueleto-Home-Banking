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

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Modificando las reglas de la seguridad de la app. Como se hace el Login, quienes son ADMIN & CLIENTS.
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/index.html", "/style.css", "/index.js" , "tailwind.config.js", "/images/**").permitAll()
                        .requestMatchers("/pages/**" , "/JavaScript/**" ,"/pages/*/cards.html","/api/clients/*", "/pages/accounts.html" ,"/api/accounts/*/transactions", "/api/clients/current").hasAuthority("CLIENT")
                        .requestMatchers("/admin/**","/pages/**", "/JavaScript/**", "/h2-console").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/clients" , "/api/login").permitAll()
                        .anyRequest().denyAll());

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
                        .failureHandler(((request, response, exception) -> response.sendError(403))));

        http.exceptionHandling( exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> response.sendError(403)));


        //Logout para cerrar sesion
        http.logout(httpSecurityLogoutConfigurer ->
                httpSecurityLogoutConfigurer
                        .logoutUrl("/api/logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()));


        http.rememberMe(Customizer.withDefaults());


        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }

}
