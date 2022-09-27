package com.raghav.atom.controller;

import com.raghav.atom.ReqResModel.PhotoResponseModel;
import com.raghav.atom.exception.PhotoServiceException;
import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.Photo;
import com.raghav.atom.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public ResponseEntity getAllPhotos() throws ResourceNotFoundException, PhotoServiceException {
        return ResponseEntity.ok(photoService.getAllPhotos());
    }
    @GetMapping("/page/{pageNumber}")
    public ResponseEntity getAllPhotos(@PathVariable("pageNumber") Integer pageNumber ) throws ResourceNotFoundException, PhotoServiceException {
        PhotoResponseModel model = new PhotoResponseModel(photoService.getAllPhotos(pageNumber));
        return ResponseEntity.ok(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPhotoById(@PathVariable("id") String id) throws PhotoServiceException, ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok(photoService.getPhotoById(id));

    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity addPhoto(@RequestBody Photo photo) throws PhotoServiceException {
        return ResponseEntity.ok()
                .body(photoService.addNewDocument(photo));
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity updatePhoto(@RequestBody Photo photo) throws  ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok()
        .body(photoService.updateDocument(photo));
    }

    @RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deletePhoto(@PathVariable("id") String photoId) throws ServiceException, ResourceNotFoundException {
        return ResponseEntity.ok(photoService.deletePhoto(photoId));
    }

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }
}
