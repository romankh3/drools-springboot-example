package com.github.romankh3.droolsspringboot;

import lombok.Data;

@Data
public class Order {

    private String name;
    private String cardType;
    private int discount;
    private Integer price;
}
