package com.example.projectbankend.Models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    private String full_name;

    @NotNull(message = "Zipcode không được bỏ trống.")
    private String zipcode;

    private Boolean ban;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<Rate> rates = new HashSet<>();
}
