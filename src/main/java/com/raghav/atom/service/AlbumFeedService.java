package com.raghav.atom.service;

import com.raghav.atom.ReqResModel.AlbumFeedRequest;
import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.model.Photo;
import com.raghav.atom.model.ResourceType;
import com.raghav.atom.repo.AlbumFeedRepo;
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

    public List<AlbumFeed> getAllFeed() throws ServiceException {
        try{
            List<AlbumFeed> list = albumFeedRepo.findAll();
            return list;
        }catch (Exception e){
            throw new ServiceException(ResourceType.ALBUM_FEED);
        }

    }

    public Page<AlbumFeed> getAllFeed(int currentPage) throws ServiceException {
        try{
            Pageable pageable = PageRequest.of(currentPage - 1, 10,Sort.by("date").descending());
            Page<AlbumFeed> page = albumFeedRepo.findAll(pageable);
            return page;
        }catch (Exception e){
            throw new ServiceException(ResourceType.ALBUM_FEED);
        }

    }

    public AlbumFeed addAlbum(AlbumFeedRequest feed) throws ServiceException {
        try{
            List<Photo> addedPhotos = photoService.addNewDocuments(feed.getPhotos());
            AlbumFeed albumFeed = AlbumFeedRequest.fromAlbumFeedRequest(feed, addedPhotos);

            log.info(ADD_TAG, albumFeed);

            return albumFeedRepo.save(albumFeed);
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw new ServiceException(ResourceType.ALBUM_FEED);
        }

    }

    public AlbumFeed updateDocument(AlbumFeed feed) throws ResourceNotFoundException, ServiceException {
        try{
            Optional<AlbumFeed> old = albumFeedRepo.findById(feed.getId());
            if(old.isPresent()){
                log.info(UPDATE_TAG, old.get());
                return albumFeedRepo.save(feed);

            }else {
                throw new ResourceNotFoundException(ResourceType.ALBUM_FEED);
            }
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new ServiceException(ResourceType.ALBUM_FEED);
        }

    }

    public boolean deleteAlbum(String albumId) throws ResourceNotFoundException, ServiceException {
        try{
            Optional<AlbumFeed> old = albumFeedRepo.findById(new ObjectId(albumId));
            old.ifPresent(albumFeed -> albumFeedRepo.deleteById(albumFeed.getId()));
            log.info(DELETE_TAG, old.get());

            if(albumFeedRepo.findById(old.get().getId()).isEmpty())
                return true;
            else {
                throw new ResourceNotFoundException(ResourceType.ALBUM_FEED);
            }
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new ServiceException(ResourceType.ALBUM_FEED);
        }

    }

    public AlbumFeed getAlbumFeedById(String id) throws ServiceException, ResourceNotFoundException {
        try{
            Optional<AlbumFeed> album = albumFeedRepo.findById(new ObjectId(id));
            if(album.isPresent())
                return album.get();
            throw new ResourceNotFoundException(ResourceType.ALBUM_FEED);
        } catch (ResourceNotFoundException e) {
            throw e;
        }catch (Exception e){
            throw new ServiceException(ResourceType.ALBUM_FEED);
        }

    }
}
