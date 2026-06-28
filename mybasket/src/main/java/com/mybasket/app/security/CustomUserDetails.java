package com.mybasket.app.security;

import com.mybasket.app.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {

    @Autowired
    private PasswordEncoder passwordEncoder;


    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    //roles based authentication

    //post->  /products ROLE_ADMIN
    // get /products ROLE_USER
    // delete /products/{productId}  ROLE_ADMIN


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        ROLE role = user.getRole();

        Collection roleCollection = new ArrayList();
        roleCollection.add(new SimpleGrantedAuthority(role.toString()));

        return roleCollection;
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnable();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
