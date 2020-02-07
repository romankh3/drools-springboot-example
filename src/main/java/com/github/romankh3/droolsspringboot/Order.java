package com.github.romankh3.droolsspringboot;


public class Order {

    private String name;
    private String cardType;
    private int discount;
    private Integer price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                '}';
    }
}
