package com.otmetkaX.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lukoil_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "count", nullable = false)
    private long count;

    @Column(name = "count_to_day", nullable = false)
    private long countToDay;

    @Column(name = "region")
    private String region;

    @Column(name = "price")
    private double price;

    public Product() {
    }

    public Product(String name, long count, long countToDay, String region, double price) {
        this.name = name;
        this.count = count;
        this.countToDay = countToDay;
        this.region = region;
        this.price = price;
    }
}
