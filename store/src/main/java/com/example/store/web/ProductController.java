package com.example.store.web;

import com.example.store.model.*;
import com.example.store.service.ManufacturerService;
import com.example.store.service.ProductCategoryService;
import com.example.store.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping
public class ProductController {
    private final ManufacturerService manufacturerService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    public ProductController(ManufacturerService manufacturerService, ProductService productService, ProductCategoryService productCategoryService) {
        this.manufacturerService = manufacturerService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }
    @GetMapping(path = {"/shop"})
    public String showShop(){
        return "shop.html";
    }
    @GetMapping(path = {"/shop/men"})
    public String showShopMen(){
        return "shop_men.html";
    }
    @GetMapping(path = {"/shop/women"})
    public String showShopWomen(){
        return "shop_women.html";
    }
    @GetMapping(path = {"/products/{sex}/{category}"})
    public String showProducts(@PathVariable String category, @PathVariable ProductSex sex, Model model) {
        List<Product> products=null;
        if (category != null && sex != null){
            products=this.productService.filter(category,sex);
        }
        model.addAttribute("products",products);
        return "product.html";
    }
    @GetMapping(path={"/product/{id}"})
    public String showProduct(@PathVariable Long id, Model model){
        Product product = this.productService.findById(id);
        model.addAttribute("product",product);
        return "singleProduct.html";
    }
    @GetMapping("/add-product-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        List<ProductCategory> categories = this.productCategoryService.listAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "add-product.html";
    }
    @GetMapping("/edit-product-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editProductPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        List<ProductCategory> categories = this.productCategoryService.listAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "edit-product.html";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String image_url,
            @RequestParam Long categoryId,
            @RequestParam Long manufacturerId,
            @RequestParam String color,
            @RequestParam ProductSize size,
            @RequestParam ProductSex sex,
            @RequestParam Double price,
            @RequestParam int stock,
            @RequestParam String dateAdded) {
            this.productService.create(id,name,description,image_url,categoryId,manufacturerId,color,size,sex,price,stock,LocalDate.parse(dateAdded));
        return "redirect:/home";
    }
    @PostMapping("/edit")
    public String editProduct(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String image_url,
            @RequestParam Long categoryId,
            @RequestParam Long manufacturerId,
            @RequestParam String color,
            @RequestParam ProductSize size,
            @RequestParam ProductSex sex,
            @RequestParam Double price,
            @RequestParam int stock,
            @RequestParam String dateAdded) {
        this.productService.update(id,name,description,image_url,categoryId,manufacturerId,color,size,sex,price,stock,LocalDate.parse(dateAdded));
        return "redirect:/home";
    }

}
