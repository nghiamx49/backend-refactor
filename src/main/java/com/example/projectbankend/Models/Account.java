package com.example.projectbankend.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(unique = true)
    @NotNull(message = "Vui lòng nhập tên tài khoản!")
    private String username;

    @NotNull(message = "Vui lòng nhập mật khẩu.")
    private String password;

    @Column(length = 1000)
    private String avatar_source;

    @NotNull(message = "Địa chỉ không được để trống.")
    private String address;

    @NotNull(message = "Số điện thoại không được để trống.")
    private String phone_number;

    @NotNull(message = "Email không được để trống.")
    @Email(message = "Vui lòng nhập email đúng định dạng")
    private String email;

    private Date create_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "account", cascade = CascadeType.PERSIST)
    private Admin admin;

    @OneToOne(mappedBy = "account", cascade = CascadeType.PERSIST)
    private User user;

    @OneToOne(mappedBy = "account", cascade = CascadeType.PERSIST)
    private Provider provider;
}
