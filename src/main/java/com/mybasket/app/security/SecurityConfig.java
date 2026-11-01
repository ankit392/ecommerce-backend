package com.mybasket.app.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


          private final JwtAuthenticationFilter jwtAuthenticationFilter;
          private final AuthenticationEntryPoint authenticationEntryPoint;
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

          // requests.requestMatchers(HttpMethod.POST,"/users").hasRole("NORMAL")
           requests.anyRequest().permitAll()
                )
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
                .exceptionHandling(ex->ex.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(e->e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        return  httpSecurity.build();

  }

@Bean
public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

}


//
@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){

        return configuration.getAuthenticationManager();

}

}
