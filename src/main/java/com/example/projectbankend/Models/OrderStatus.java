package com.example.projectbankend.Models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "order_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String type;

    @OneToOne(mappedBy = "orderStatus")
    private Order order;
}
