package com.raghav.atom.controller;

import com.raghav.atom.ReqResModel.AlbumFeedRequest;
import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.service.AlbumFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/albumfeed")
public class AlbumFeedController {
    @Autowired
    private AlbumFeedService albumFeedService;


    @GetMapping
    public ResponseEntity getAllAlbum() throws ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.getAllFeed());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAlbumById(@PathVariable("id") String id) throws ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.getAlbumFeedById(id));
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity addAlbum(@RequestBody AlbumFeedRequest feed) throws ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.addAlbum(feed));
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity updateAlbum(@RequestBody AlbumFeed feed) throws ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.updateDocument(feed));

    }

    @DeleteMapping( value = "/{albumId}",headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity deletePhoto(@PathVariable("albumId") String albumId) throws ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok()
                .body(albumFeedService.deleteAlbum(albumId));
    }
}
