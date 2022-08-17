package com.raghav.atom.controller;

import com.raghav.atom.repo.PhotoRepo;
import com.raghav.atom.service.PhotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PhotoControllerTest {

    private PhotoController photoController;

    @Mock
    private PhotoService photoService;

    @BeforeEach
    void setUp(){
        photoController = new PhotoController(photoService);
    }

    @Test
    void getAllPhotos() {
       // ResponseEntity response = this.photoController.getAllPhotos();
       // verify(photoService).getAllPhotos();
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