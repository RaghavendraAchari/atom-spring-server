package com.raghav.atom.service;

import com.raghav.atom.controller.AlbumFeedRequest;
import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.model.Photo;
import com.raghav.atom.repo.AlbumFeedRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AlbumFeedService {

    @Autowired
    private AlbumFeedRepo albumFeedRepo;

    @Autowired
    private PhotoService photoService;

    public List<AlbumFeed> getAllFeed() {
        List<AlbumFeed> list = albumFeedRepo.findAll();
        return list;
    }

    public AlbumFeed addAlbum(AlbumFeedRequest feed) {
        System.out.println(feed);
        List<Photo> addedPhotos = photoService.addNewDocuments(feed.getPhotos());
        AlbumFeed albumFeed = AlbumFeedRequest.fromAlbumFeedRequest(feed, addedPhotos);
        return albumFeedRepo.save(albumFeed);
    }

    public ResponseEntity updateDocument(AlbumFeed feed) {
        Optional<AlbumFeed> old = albumFeedRepo.findById(feed.getId());
        if(old.isPresent()){
            return ResponseEntity.ok(albumFeedRepo.save(feed));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity deleteAlbum(String albumId) {
        Optional<AlbumFeed> old = albumFeedRepo.findById(new ObjectId(albumId));
        old.ifPresentOrElse(albumFeed -> albumFeedRepo.deleteById(albumFeed.getId()),
                ()->{ ResponseEntity.notFound().build();});
        if(albumFeedRepo.findById(old.get().getId()).isEmpty())
            return ResponseEntity.ok().build();
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity getAlbumFeedById(String id) {
        Optional<AlbumFeed> album = albumFeedRepo.findById(new ObjectId(id));
        if(album.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(album.get());
    }
}
