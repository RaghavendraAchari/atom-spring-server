package com.raghav.atom.controller;

import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.model.Photo;
import com.raghav.atom.service.AlbumFeedService;
import com.raghav.atom.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/albumfeed")
public class AlbumFeedController {
    @Autowired
    private AlbumFeedService albumFeedService;


    @GetMapping
    public ResponseEntity getAllAlbum(){
        return ResponseEntity.ok(albumFeedService.getAllFeed());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAlbumById(@PathVariable("id") String id){
        return albumFeedService.getAlbumFeedById(id);
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity addAlbum(@RequestBody AlbumFeedRequest feed){
        System.out.println(feed);
        return ResponseEntity.ok(albumFeedService.addAlbum(feed));
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity updateAlbum(@RequestBody AlbumFeed feed){
        System.out.println(feed);
        return albumFeedService.updateDocument(feed);

    }

    @DeleteMapping( value = "/{albumId}",headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity deletePhoto(@PathVariable("albumId") String albumId){
        return albumFeedService.deleteAlbum(albumId);
    }
}
