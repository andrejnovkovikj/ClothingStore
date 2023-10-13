package com.example.store.service;

import com.example.store.model.*;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    Product findById(Long id);
    Product findByName(String name);
    Product findByDateAdded(LocalDate dateAdded);
    List<Product> listAll();
    List<Product> filter(String category, ProductSex sex);
    Product create(Long id,String name, String description,String image_url, Long categoryId, Long manufacturerId, String color, ProductSize size, ProductSex sex, Double price, int stock, LocalDate dateAdded);
    Product update(Long id,String name, String description,String image_url, Long categoryId, Long manufacturerId, String color, ProductSize size, ProductSex sex, Double price, int stock, LocalDate dateAdded);
    Product delete(Long id);

}
