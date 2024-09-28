package com.example.store.service.impl;

import com.example.store.model.*;
import com.example.store.model.exceptions.InvalidProductIdException;
import com.example.store.repository.OrderItemRepository;
import com.example.store.repository.ProductCategoryRepository;
import com.example.store.repository.ProductRepository;
import com.example.store.service.ManufacturerService;
import com.example.store.service.ProductCategoryService;
import com.example.store.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryService productCategoryService;
    private final ManufacturerService manufacturerService;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductCategoryService productCategoryService, ManufacturerService manufacturerService) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryService = productCategoryService;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(InvalidProductIdException::new);
    }

    @Override
    public Product findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Product findByDateAdded(LocalDate dateAdded) {
        return this.productRepository.findByDateAdded(dateAdded);
    }
    @Override
    public List<Product> filter(String category, ProductSex sex){

        List <Product> results=null;
        if (category == null && sex == null) {
            results = productRepository.findAll();
        }else if(category != null && sex != null){
                results = productRepository.findAllByCategoryAndSex(productCategoryRepository.findByName(category), sex);
            }
        return results;
        }



    @Override
    public List<Product> listAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product create(Long id,String name, String description,String image_url, Long categoryId, Long manufacturerId, String color, ProductSize size, ProductSex sex, Double price, int stock, LocalDate dateAdded) {
        ProductCategory category=this.productCategoryService.findById(categoryId);
        Manufacturer manufacturer=this.manufacturerService.findById(manufacturerId);
        Product product=new Product(id,name,description,image_url,category,manufacturer,color,size,sex,price,stock,dateAdded);
        return this.productRepository.save(product);
    }

    @Override
    public Product update(Long id, String name, String description,String image_url, Long categoryId, Long manufacturerId, String color, ProductSize size, ProductSex sex, Double price, int stock, LocalDate dateAdded) {
        Product product=findById(id);
        ProductCategory category=this.productCategoryService.findById(categoryId);
        Manufacturer manufacturer=this.manufacturerService.findById(manufacturerId);
        product.setName(name);
        product.setDescription(description);
        product.setImage_url(image_url);
        product.setCategory(category);
        product.setManufacturer(manufacturer);
        product.setColor(color);
        product.setSize(size);
        product.setSex(sex);
        product.setPrice(price);
        product.setStock(stock);
        product.setDateAdded(dateAdded);
        return this.productRepository.save(product);
    }

    @Override
    public Product delete(Long id) {
        Product product=findById(id);
        this.productRepository.delete(product);
        return product;
    }

    @Override
    public Long getNextId() {
        Long maxId = productRepository.findMaxId();
        return maxId != null ? maxId + 1 : 1;
    }
}
