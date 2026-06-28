package com.mybasket.app.security;


import com.mybasket.app.entity.User;
import com.mybasket.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {



    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user= userRepository.findByEmail(username).orElseThrow(()->new BadCredentialsException("user name/pwd invalid"));


        System.out.println("Username = " + username);
        System.out.println("Password = " + user.getPassword());
        System.out.println("Enabled = " + user.isEnable());
        System.out.println("Role = " + user.getRole());
     return new CustomUserDetails(user);





    }
}
