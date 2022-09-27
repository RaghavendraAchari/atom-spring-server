package com.raghav.atom.service;

import com.raghav.atom.exception.PhotoServiceException;
import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.Photo;
import com.raghav.atom.model.ResourceType;
import com.raghav.atom.repo.PhotoRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Slf4j
public class PhotoService {
    private final String ADD_TAG = "Adding New Photo";
    private final String UPDATE_TAG = "Updating Existing Photo";
    private final String DELETE_TAG = "Deleting old Photo";

    @Autowired
    private PhotoRepo photoRepo;

    public List<Photo> getAllPhotos() throws ResourceNotFoundException, PhotoServiceException{
        try{
            List<Photo> all = photoRepo.findAll();
            if(all == null)
                throw new ResourceNotFoundException(ResourceType.PHOTO);
            return all;
        }catch (Exception e){
            throw new PhotoServiceException();
        }

    }
    public Page<Photo> getAllPhotos(int currentPage) throws ResourceNotFoundException, PhotoServiceException{
        try{
            Pageable pageable = PageRequest.of(currentPage - 1, 10, Sort.by("date").descending());
            Page<Photo> page = photoRepo.findAll(pageable);
            return page;
        }catch (Exception e){
            throw new PhotoServiceException();
        }

    }

    public Photo addNewDocument(Photo body) throws PhotoServiceException {
        try {
            log.info(ADD_TAG, body);
            Photo photo = photoRepo.save(body);
            return photo;
        }catch (Exception e){
            throw new PhotoServiceException();
        }

    }

    public Photo updateDocument(Photo photo) throws ServiceException, ResourceNotFoundException {
        try{
            Optional<Photo> old = photoRepo.findById(photo.getId());
            if(old.isPresent()){
                log.info(UPDATE_TAG, old.get());
                photoRepo.save(photo);
                return photoRepo.findById(photo.getId()).get();
            }
        }catch (Exception e){
            throw new ServiceException(ResourceType.PHOTO);
        }
        throw new ResourceNotFoundException(ResourceType.PHOTO);
    }

    public boolean deletePhoto(String photoId) throws ServiceException, ResourceNotFoundException {
        try{
            Optional<Photo> old = photoRepo.findById(new ObjectId(photoId));
            if(old.isPresent()){
                log.info(DELETE_TAG, old.get());
                photoRepo.deleteById(old.get().getId());
            }else {
                throw new ResourceNotFoundException(ResourceType.PHOTO);
            }

            if(photoRepo.findById(old.get().getId()).isEmpty())
                return true;
            else
                throw new ServiceException(ResourceType.PHOTO);
        }catch (Exception e){
            throw e;
        }

    }

    public List<Photo> addNewDocuments(List<Photo> photos) throws ServiceException {
        try {
            List<Photo> photoList = photoRepo.saveAll(photos);
            if(photoList == null)
                throw new ServiceException(ResourceType.PHOTO);
            return photoList;

        }catch (Exception e){
            throw new ServiceException(ResourceType.PHOTO);
        }
    }

    public Photo getPhotoById(String id) throws ServiceException, ResourceNotFoundException {
        try{
            Optional<Photo> photo = photoRepo.findById(new ObjectId(id));
            if(photo.isPresent())
                return photo.get();
            throw new ResourceNotFoundException(ResourceType.PHOTO);
        }catch (ResourceNotFoundException e){
            throw e;
        }
        catch (Exception e){
            throw new ServiceException(ResourceType.PHOTO);
        }
    }
}
