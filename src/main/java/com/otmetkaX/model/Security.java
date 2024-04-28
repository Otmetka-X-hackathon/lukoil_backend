package com.otmetkaX.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lukoil_user")
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "role", nullable = false)
    private String role;

    public Security() {}

    public Security(String phone, String token, String role) {
        this.phone = phone;
        this.token = token;
        this.role = role;
    }

}

