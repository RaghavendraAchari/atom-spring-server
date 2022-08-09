package com.raghav.atom.controller;

import com.raghav.atom.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.raghav.atom.repo.PhotoRepo;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoRepo photoRepo;

    @GetMapping
    public ResponseEntity<?> getAllPhotos(){
        System.out.println("In get method");
        return ResponseEntity.ok(photoRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addPhoto(@RequestBody Photo photo ){
        System.out.println(photo);
        return ResponseEntity.ok("200");
    }

}
