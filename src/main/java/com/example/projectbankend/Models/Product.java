package com.example.projectbankend.Models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.projectbankend.Models.Validator.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;

    @NotNull(message = "Số lượng sản  không được để trống.")
    private int quantity;

    @NotNull(message = "Giá sản phẩm không được để trống.")
    private String unit_price;

    @NotNull(message = "Số lượng đã bán không được bỏ trống")
    private int number_of_sold;

    private String product_description;

    @Status
    @NotNull(message = "trang thai khong duoc de trong")
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<ProductImage> images = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "product")
    private Set<Rate> rates = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Order> orders = new HashSet<>();
}
