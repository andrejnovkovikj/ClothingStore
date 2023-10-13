package com.example.store.service.impl;

import com.example.store.model.Manufacturer;
import com.example.store.model.Product;
import com.example.store.model.ProductCategory;
import com.example.store.model.exceptions.InvalidProductCategoryIdException;
import com.example.store.model.exceptions.InvalidProductIdException;
import com.example.store.repository.ProductCategoryRepository;
import com.example.store.repository.ProductRepository;
import com.example.store.service.ManufacturerService;
import com.example.store.service.ProductCategoryService;
import com.example.store.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductCategory findById(Long id) {
       return this.productCategoryRepository.findById(id).orElseThrow(InvalidProductCategoryIdException::new);
    }

    @Override
    public ProductCategory findByName(String name) {
        return this.productCategoryRepository.findByName(name);
    }

    @Override
    public List<ProductCategory> listAll() {
        return this.productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory create(String name) {
        ProductCategory productCategory=new ProductCategory(name);
        return this.productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory update(Long id, String name) {
        ProductCategory productCategory=findById(id);
        productCategory.setName(name);
        return this.productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory delete(Long id) {
        ProductCategory productCategory=findById(id);
        this.productCategoryRepository.delete(productCategory);
        return productCategory;
    }
}
