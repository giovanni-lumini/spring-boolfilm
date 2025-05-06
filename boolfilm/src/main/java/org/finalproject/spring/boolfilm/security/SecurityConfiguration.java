package org.finalproject.spring.boolfilm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // METHODS
    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/film/**", "/categories/**").hasAuthority("ADMIN")
                .requestMatchers("/film/create", "/film/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/categories/create", "/categories/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/", "/books", "/books/**", "/categories", "/categories/**")
                .hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**").permitAll()
                // automatic login form from Spring
                .and().formLogin()
                // automatic login form from Spring
                .and().logout()
                // handles access denied/403
                .and().exceptionHandling()
                // to make POST calls work, via API, but not always safe, it should be managed
                .and().csrf().disable();

        return httpSecurity.build();
    }

    // delegate password encoder responsibility to db
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // used by Spring Security to load user data from database during login
    @Bean
    DatabaseUserDetailsService userDetailService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        // restituisce il mio DatabaseUserDetailService, per cercare l’utente nel db
        authenticationProvider.setUserDetailsService(userDetailService());

        // verificare la password criptata
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // metodo sotto

        return authenticationProvider;
    }

}
