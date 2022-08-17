package com.raghav.atom.service;

import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.Art;
import com.raghav.atom.model.ResourceType;
import com.raghav.atom.repo.ArtRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
//@Slf4j auto injects log object
public class ArtService {

    @Autowired
    private ArtRepo artRepo;

    private final String ADD_TAG = "Adding New Art";
    private final String UPDATE_TAG = "Updating Existing Art";
    private final String DELETE_TAG = "Deleting old Art";


    public List<Art> getAllArt() throws ServiceException {
        try{
            return artRepo.findAll();
        }catch (Exception e){
            throw new ServiceException(ResourceType.ART);
        }

    }

    public Art getArtById(String id) throws ResourceNotFoundException, ServiceException {
        try {
            Optional<Art> art = artRepo.findById(new ObjectId(id));
            if(art.isPresent())
                return art.get();
            throw new ResourceNotFoundException(ResourceType.ART);
        } catch (ResourceNotFoundException e) {
            throw e;
        }catch (Exception e){
            throw new ServiceException(ResourceType.ART);
        }

    }

    public Art addArt(Art art) throws ServiceException {
        try{
            Art saved = artRepo.save(art);
            log.info(ADD_TAG, saved);
            return artRepo.save(saved);
        }catch (Exception e){
            throw new ServiceException(ResourceType.ART);
        }

    }

    public Art updateArt(Art art) throws ResourceNotFoundException, ServiceException {
        try{
            Optional<Art> old = artRepo.findById(art.getId());
            if(old.isPresent()){
                log.info(UPDATE_TAG, old.get());
                artRepo.save(art);
                return artRepo.findById(art.getId()).get();
            }
            throw new ResourceNotFoundException(ResourceType.ART);

        }catch (ResourceNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new ServiceException(ResourceType.ART);
        }
    }

    public boolean deleteArt(String artId) throws ServiceException {
        try{
            Optional<Art> old = artRepo.findById(new ObjectId(artId));
            if(old.isPresent()){
                log.info(DELETE_TAG, old.get());
                artRepo.delete(old.get());
            }

            //confirm deletion
            if(artRepo.findById(old.get().getId()).isEmpty())
                return true;
            throw new ServiceException(ResourceType.ART);
        }catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw new ServiceException(ResourceType.ART);
        }

    }
}
