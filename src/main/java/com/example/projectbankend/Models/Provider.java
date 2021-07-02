package com.example.projectbankend.Models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smartdev.backend.smartdev_backend.Models.Validator.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "providers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @NotNull(message = "Tên cửa hàng không được bỏ trống")
    private String store_name;

    private String owner;

    @Status
    @NotNull(message = "trang thai khong duoc de trong")
    private String status;

    private String bank_account_number;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;
}
