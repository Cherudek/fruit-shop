package com.example.myapplication.pojo;

public final class FruitBuilder {
    private String type;
    private Integer price;
    private Integer weight;

    private FruitBuilder() {
    }

    public static FruitBuilder aFruit() {
        return new FruitBuilder();
    }

    public FruitBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public FruitBuilder withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public FruitBuilder withWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Fruit build() {
        Fruit fruit = new Fruit();
        fruit.setType(type);
        fruit.setPrice(price);
        fruit.setWeight(weight);
        return fruit;
    }
}
