package com.raghav.atom.service;

import com.raghav.atom.model.Art;
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


    public List<Art> getAllArt() {
        return artRepo.findAll();
    }

    public Art getArtById(String id) {
        Optional<Art> art = artRepo.findById(new ObjectId(id));
        if(art.isPresent())
            return art.get();
        return null;
    }

    public Art addArt(Art art) {
        Art saved = artRepo.save(art);
        log.info(ADD_TAG, saved);
        return artRepo.save(saved);
    }

    public Art updateArt(Art art) {
        Optional<Art> old = artRepo.findById(art.getId());
        if(old.isPresent()){
            log.info(UPDATE_TAG, old.get());
            artRepo.save(art);
            return artRepo.findById(art.getId()).get();
        }

        return null;
    }

    public boolean deleteArt(String artId) {
        Optional<Art> old = artRepo.findById(new ObjectId(artId));
        if(old.isPresent()){
            log.info(DELETE_TAG, old.get());
            artRepo.delete(old.get());
        }

        //confirm deletion
        if(artRepo.findById(old.get().getId()).isEmpty())
            return true;
        return false;
    }
}
