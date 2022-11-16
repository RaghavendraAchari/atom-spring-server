package com.raghav.atom.controller;

import com.raghav.atom.ReqResModel.AlbumFeedRequest;
import com.raghav.atom.ReqResModel.AlbumFeedResponseModel;
import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.model.Photo;
import com.raghav.atom.service.AlbumFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("/api/albumfeed")
public class AlbumFeedController {
    @Autowired
    private AlbumFeedService albumFeedService;


    @GetMapping
    @Deprecated
    public ResponseEntity<List<AlbumFeed>> getAllAlbum() throws ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.getAllFeed());
    }
    @GetMapping("/page/{pageNumber}")
    public ResponseEntity getAllAlbum(@PathVariable("pageNumber") Integer pageNumber,
                                      @RequestParam(name = "category", defaultValue = "All", required = false) String category) throws ServiceException {
        AlbumFeedResponseModel albumFeedResponseModel = new AlbumFeedResponseModel(albumFeedService.getAllFeed(pageNumber,category));
        return ResponseEntity.ok(albumFeedResponseModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumFeed> getAlbumById(@PathVariable("id") String id) throws ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.getAlbumFeedById(id));
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity<AlbumFeed> addAlbum(@RequestBody AlbumFeedRequest feed) throws ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.addAlbum(feed));
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity<AlbumFeed> updateAlbum(@RequestBody AlbumFeed feed) throws ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.updateDocument(feed));

    }

    @DeleteMapping( value = "/{albumId}",headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Boolean> deletePhoto(@PathVariable("albumId") String albumId) throws ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.deleteAlbum(albumId));
    }
}
