package com.raghav.atom.service;

import com.raghav.atom.model.Photo;
import com.raghav.atom.repo.PhotoRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Slf4j
public class PhotoService {
    private final String ADD_TAG = "Adding New Art";
    private final String UPDATE_TAG = "Updating Existing Art";
    private final String DELETE_TAG = "Deleting old Art";

    @Autowired
    private PhotoRepo photoRepo;

    public List<Photo> getAllPhotos(){
        List<Photo> all = photoRepo.findAll();
        return all;
    }

    public Photo addNewDocument(Photo body){
        log.info(ADD_TAG, body);
        return photoRepo.save(body);
    }

    public Photo updateDocument(Photo photo) {
        Optional<Photo> old = photoRepo.findById(photo.getId());
        if(old.isPresent()){
            log.info(UPDATE_TAG, old.get());
            photoRepo.save(photo);
            return photoRepo.findById(photo.getId()).get();
        }else {
            return null;
        }

    }

    public boolean deletePhoto(String photoId) {
        Optional<Photo> old = photoRepo.findById(new ObjectId(photoId));
        if(old.isPresent()){
            log.info(DELETE_TAG, old.get());
            photoRepo.deleteById(old.get().getId());
        }
        if(photoRepo.findById(old.get().getId()).isEmpty())
            return true;
        else
            return false;
    }

    public List<Photo> addNewDocuments(List<Photo> photos) {
        return photoRepo.saveAll(photos);
    }

    public Photo getPhotoById(String id) {
        Optional<Photo> photo = photoRepo.findById(new ObjectId(id));
        if(photo.isPresent())
            return photo.get();
        else
            return null;
    }
}
