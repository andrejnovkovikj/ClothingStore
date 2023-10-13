package com.example.store.web;

import com.example.store.service.ManufacturerService;
import com.example.store.service.ProductCategoryService;
import com.example.store.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    private final ManufacturerService manufacturerService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    public HomeController(ManufacturerService manufacturerService, ProductService productService, ProductCategoryService productCategoryService) {
        this.manufacturerService = manufacturerService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping(path = {"/","/home"})
    public String home(){
        return "home.html";
    }
}
