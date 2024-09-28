package com.example.store.web;

import com.example.store.model.Manufacturer;
import com.example.store.model.ProductCategory;
import com.example.store.model.ProductSex;
import com.example.store.model.ProductSize;
import com.example.store.service.ProductCategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping
public class CategoryController {
    private final ProductCategoryService productCategoryService;

    public CategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/add-category-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        model.addAttribute("bodyContent", "add-category");
        return "add-category.html";
    }
    @PostMapping("/addCategory")
    public String saveCategory(
            @RequestParam String name) {
        this.productCategoryService.create(name);
        return "redirect:/home";
    }
    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", productCategoryService.listAll());
        model.addAttribute("bodyContent", "categorylist");
        return "categorylist.html";
    }
    @PostMapping("/deleteCategory")
    public String deleteCategory(@RequestParam Long id) {
        this.productCategoryService.delete(id);
        return "redirect:/home";
    }

}
