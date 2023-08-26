package com.example.todomanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfiguration {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorize) -> {
            //this method is used to configure the authorization rules
//            authorize.requestMatchers(HttpMethod.POST, "/api/v1/todos/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.PUT, "/api/v1/todos/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.DELETE, "/api/v1/todos/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.GET, "/api/v1/todos/**").hasAnyRole("USER","ADMIN");
//            authorize.requestMatchers(HttpMethod.PATCH, "/api/v1/todos/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.GET, "/api/v1/todos/all").permitAll();
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails alvaro = User.builder()
                .username("alvaro")
                .password(passwordEncoder().encode("linares"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin1"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(alvaro, admin);
    }


}
