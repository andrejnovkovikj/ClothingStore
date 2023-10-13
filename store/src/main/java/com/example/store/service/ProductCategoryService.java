package com.example.store.service;

import com.example.store.model.Product;
import com.example.store.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findById(Long id);
    ProductCategory findByName(String name);
    List<ProductCategory> listAll();
    ProductCategory create(String name);
    ProductCategory update(Long id,String name);
    ProductCategory delete(Long id);

}
