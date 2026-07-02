package com.chizweet.tokoonline.model;

public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String emoji;

    public Product(Long id, String name, String description, double price, String category, String emoji) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.emoji = emoji;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public String getEmoji() { return emoji; }
}
