package com.raghav.atom.service;

import com.raghav.atom.ReqResModel.AlbumFeedRequest;
import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.model.Photo;
import com.raghav.atom.repo.AlbumFeedRepo;
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
public class AlbumFeedService {

    private final String ADD_TAG = "Adding New Art";
    private final String UPDATE_TAG = "Updating Existing Art";
    private final String DELETE_TAG = "Deleting old Art";


    @Autowired
    private AlbumFeedRepo albumFeedRepo;

    @Autowired
    private PhotoService photoService;

    public List<AlbumFeed> getAllFeed() {
        List<AlbumFeed> list = albumFeedRepo.findAll();
        return list;
    }

    public AlbumFeed addAlbum(AlbumFeedRequest feed) {
        List<Photo> addedPhotos = photoService.addNewDocuments(feed.getPhotos());
        AlbumFeed albumFeed = AlbumFeedRequest.fromAlbumFeedRequest(feed, addedPhotos);

        log.info(ADD_TAG, albumFeed);

        return albumFeedRepo.save(albumFeed);
    }

    public AlbumFeed updateDocument(AlbumFeed feed) {
        Optional<AlbumFeed> old = albumFeedRepo.findById(feed.getId());
        if(old.isPresent()){
            log.info(UPDATE_TAG, old.get());
            return albumFeedRepo.save(feed);

        }else {
            return null;
        }
    }

    public boolean deleteAlbum(String albumId) {
        Optional<AlbumFeed> old = albumFeedRepo.findById(new ObjectId(albumId));
        old.ifPresent(albumFeed -> albumFeedRepo.deleteById(albumFeed.getId()));
        log.info(DELETE_TAG, old.get());

        if(albumFeedRepo.findById(old.get().getId()).isEmpty())
            return true;
        else {
            return false;
        }
    }

    public AlbumFeed getAlbumFeedById(String id) {
        Optional<AlbumFeed> album = albumFeedRepo.findById(new ObjectId(id));
        if(album.isPresent())
            return album.get();
        return null;
    }
}
