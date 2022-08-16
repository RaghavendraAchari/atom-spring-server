package com.raghav.atom.controller;

import com.raghav.atom.ReqResModel.AlbumFeedRequest;
import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.service.AlbumFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/albumfeed")
public class AlbumFeedController {
    @Autowired
    private AlbumFeedService albumFeedService;


    @GetMapping
    public ResponseEntity getAllAlbum(){
        return ResponseEntity.ok()
                .body(albumFeedService.getAllFeed());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAlbumById(@PathVariable("id") String id){
        return ResponseEntity.ok()
                .body(albumFeedService.getAlbumFeedById(id));
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity addAlbum(@RequestBody AlbumFeedRequest feed){
        return ResponseEntity.ok()
                .body(albumFeedService.addAlbum(feed));
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity updateAlbum(@RequestBody AlbumFeed feed){
        return ResponseEntity.ok()
                .body(albumFeedService.updateDocument(feed));

    }

    @DeleteMapping( value = "/{albumId}",headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity deletePhoto(@PathVariable("albumId") String albumId){
        return ResponseEntity.ok()
                .body(albumFeedService.deleteAlbum(albumId));
    }
}
