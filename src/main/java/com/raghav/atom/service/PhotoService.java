package com.raghav.atom.service;

import com.raghav.atom.controller.PhotoAddRequest;
import com.raghav.atom.model.Photo;
import com.raghav.atom.repo.PhotoRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class PhotoService {
    @Autowired
    private PhotoRepo photoRepo;

    public ResponseEntity getAllPhotos(){
        List<Photo> all = photoRepo.findAll();

        return ResponseEntity.ok(all);
    }

    public Photo addNewDocument(Photo body){
        System.out.println(body);
        return photoRepo.save(body);
    }

    public ResponseEntity updateDocument(Photo photo) {
        Optional<Photo> old = photoRepo.findById(photo.getId().toString());
        if(old.isPresent()){
            photoRepo.save(photo);
            return ResponseEntity.ok(photoRepo.findById(photo.getId().toString()).get());
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity deletePhoto(Photo photo) {
        Optional<Photo> old = photoRepo.findById(photo.getId().toString());
        if(old.isPresent()){
            photoRepo.delete(photo);
            return ResponseEntity.ok(ResponseEntity.ok());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
