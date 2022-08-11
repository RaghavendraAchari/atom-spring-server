package com.raghav.atom.controller;

import com.raghav.atom.model.Photo;
import com.raghav.atom.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public ResponseEntity getAllPhotos(){
        return photoService.getAllPhotos();
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity addPhoto(@RequestBody Photo photo){
        System.out.println(photo);
        return ResponseEntity.ok(photoService.addNewDocument(photo));
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity updatePhoto(@RequestBody Photo photo){
        System.out.println(photo);
        return photoService.updateDocument(photo);
    }

    @DeleteMapping(headers = "Accept=application/json")
    public ResponseEntity deletePhoto(@RequestBody Photo photo){
        return photoService.deletePhoto(photo);
    }
}
