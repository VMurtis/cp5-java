package br.com.fiap.cp5.configurations;

import br.com.fiap.cp5.services.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("123456"))
                .roles("ADMIN","TRANQUILO")
                //.roles("TRANQUILO")
                .build();

        UserDetails user = User.withUsername("user")
                .password(encoder.encode("123456"))
                .roles("USUARIO")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }*/

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
            AuthorizationService authorizationService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authorizationService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }



}
