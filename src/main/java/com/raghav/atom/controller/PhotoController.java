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

    @GetMapping("/{id}")
    public ResponseEntity getPhotoById(@PathVariable("id") String id){

        return photoService.getPhotoById(id);
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

    @RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deletePhoto(@PathVariable("id") String photoId){
        System.out.println(photoId);
        return photoService.deletePhoto(photoId);
    }

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }
}
