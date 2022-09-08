package com.raghav.atom.controller;

import com.raghav.atom.exception.PhotoServiceException;
import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.repo.PhotoRepo;
import com.raghav.atom.service.PhotoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PhotoControllerTest {

    private PhotoController photoController;

    private PhotoService photoService;

    @Mock
    private PhotoRepo photoRepo;

    @BeforeAll
    void setUp(){
        photoService = new PhotoService(photoRepo);
        photoController = new PhotoController(photoService);
    }

    @Test
    void getAllPhotosOnSuccess() throws PhotoServiceException, ResourceNotFoundException {
       ResponseEntity response = this.photoController.getAllPhotos();
        Assertions.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    void addPhoto() {
    }

    @Test
    void updatePhoto() {
    }

    @Test
    void deletePhoto() {
    }
}