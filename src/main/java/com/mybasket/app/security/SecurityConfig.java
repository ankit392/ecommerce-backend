package com.mybasket.app.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

@EnableMethodSecurity
public class SecurityConfig {


//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user1 = User.builder().username("ankit").password("{noop}abc").build();
//
//        return new InMemoryUserDetailsManager(user1);
//    }

    @Bean// HTTP ROUTES CUSTUMIZATION
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){


        httpSecurity.csrf(e->e.disable())
                .authorizeHttpRequests(

   requests->
//                        requests.requestMatchers(HttpMethod.GET).permitAll()
//                                .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
           requests.anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());



        return  httpSecurity.build();

  }

@Bean
public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

}

@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){

        return configuration.getAuthenticationManager();

}

}
