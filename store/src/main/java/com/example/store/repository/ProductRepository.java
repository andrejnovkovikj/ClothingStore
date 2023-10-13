package com.example.store.repository;

import com.example.store.model.Product;
import com.example.store.model.ProductCategory;
import com.example.store.model.ProductSex;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
    Product findByName(String name);
    Product findByDateAdded(LocalDate dateAdded);
    List<Product> findAllByCategoryAndSex(ProductCategory category, ProductSex sex);
}
