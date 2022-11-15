package com.raghav.atom.controller;

import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.Category;
import com.raghav.atom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getCategories() throws ServiceException {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
