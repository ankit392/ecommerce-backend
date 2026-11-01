package com.mybasket.app.entity;


import com.mybasket.app.security.ROLE;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="my-basket-users")
@SQLDelete(sql="update `my-basket-users` set deleted=true where jpa_user_id=? ")
@SQLRestriction("deleted=false")  // ONLY FALSE FETCH

public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jpa_user_id")
    private int userid;

    // create enable and getter setter for userDetails
    private boolean isEnable=true;


    //getter
    public ROLE getRole() {
        return role;
    }

    //setter
    public void setRole(ROLE role) {
        this.role = role;
    }


    @Enumerated(EnumType.STRING)
    private ROLE role=ROLE.ROLE_NORMAL;

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String userImageUrl;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private  Set<PaymentMethodInfo> paymentMethodInfos=new LinkedHashSet<>();


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,orphanRemoval = true
    )
    private Set<Address> address=new HashSet<>();


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
}
