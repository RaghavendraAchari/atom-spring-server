package com.raghav.atom.service;

import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.Category;
import com.raghav.atom.repo.CategoryRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    List<Category> list = new ArrayList<Category>(List.of(new Category(new ObjectId(), "Cat 1")));

    @Mock
    private CategoryRepo repo;

    @InjectMocks
    private CategoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories() throws ServiceException {
        when(repo.findAll()).thenReturn(list);

        assertEquals(1, service.getAllCategories().size());

    }
}