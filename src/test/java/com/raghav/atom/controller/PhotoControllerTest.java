package com.raghav.atom.controller;

import com.raghav.atom.exception.PhotoServiceException;
import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.model.Photo;
import com.raghav.atom.repo.PhotoRepo;
import com.raghav.atom.service.PhotoService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PhotoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private PhotoService photoService;
    @InjectMocks
    private PhotoController photoController;

    List<Photo> list = List.of(new Photo(new ObjectId(), LocalDateTime.now(), "ThumbnailUrl", "OriginalFileUrl")
            , new Photo(new ObjectId(), LocalDateTime.now(), "ThumbnailUrl", "OriginalFileUrl")
            );

    @Test
    void getAllPhotosOnSuccess() throws Exception {
        Mockito.when(photoService.getAllPhotos()).thenReturn(list);
        ResponseEntity response = this.photoController.getAllPhotos();
        Assertions.assertEquals(response.getStatusCodeValue(), 200);
        Assertions.assertEquals(response.getBody(), list);
        System.out.println(response.getBody());

    }

    @Test
    void addPhoto() throws Exception{
//        Mockito.when(photoService.addNewDocument(
//                new Photo(new ObjectId(), LocalDateTime.now(), "ThumbnailUrl", "OriginalFileUrl"))).thenReturn(list);
//        ResponseEntity response = this.photoController.getAllPhotos();
//        Assertions.assertEquals(response.getStatusCodeValue(), 200);
//        Assertions.assertEquals(response.getBody(), list);
//        System.out.println(response.getBody());
    }

    @Test
    void updatePhoto() {
    }

    @Test
    void deletePhoto() {
    }
}