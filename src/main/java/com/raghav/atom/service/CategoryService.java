package com.raghav.atom.service;

import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.Category;
import com.raghav.atom.model.ResourceType;
import com.raghav.atom.repo.CategoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {
    private final String INFO = "Sending categories";
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategories() throws ServiceException {
        try {
            List<Category> categories = categoryRepo.findAll();
            log.info(INFO, categories);
            return categories;
        }catch (Exception e){
            throw new ServiceException(e.getMessage(), ResourceType.CATEGORY);
        }
    }

}
