package com.example.store.model;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
public class Product {
    @Id
    private Long id;

    private String name;

    private String description;

    private String image_url;

    @ManyToOne
    private ProductCategory category;

    @ManyToOne
    private Manufacturer manufacturer;

    private String color;
    @Enumerated(EnumType.STRING)
    private ProductSize size;

    @Enumerated(EnumType.STRING)
    private ProductSex sex;

    private Double price;

    private int stock;

    private LocalDate dateAdded;


    public Product(Long id,
                   String name,
                   String description,
                   String image_url,
                   ProductCategory category,
                   Manufacturer manufacturer,
                   String color, ProductSize size,
                   ProductSex sex,
                   Double price,
                   int stock,
                   LocalDate dateAdded) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.image_url=image_url;
        this.category = category;
        this.manufacturer=manufacturer;
        this.color = color;
        this.size = size;
        this.sex = sex;
        this.price = price;
        this.stock = stock;
        this.dateAdded = dateAdded;
    }

    public Product() {

    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ProductSize getSize() {
        return size;
    }

    public void setSize(ProductSize size) {
        this.size = size;
    }

    public ProductSex getSex() {
        return sex;
    }

    public void setSex(ProductSex sex) {
        this.sex = sex;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}