package net.enjoy.springboot.registrationlogin.dto;

public class ProductDto {
    private int id;
    private String name;
    private String image;
    private String description;
    private double price;
    private int quantity;
    private int category;
    private boolean status;

    public ProductDto(int id, String name, String image, String description, double price, int quantity, int category, boolean status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.status = status;
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}