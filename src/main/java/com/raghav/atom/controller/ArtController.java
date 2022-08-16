package com.raghav.atom.controller;

import com.raghav.atom.model.Art;
import com.raghav.atom.repo.ArtRepo;
import com.raghav.atom.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/art")
public class ArtController {
    @Autowired
    private ArtService artService;

    @GetMapping
    public ResponseEntity getAllArt(){
        return ResponseEntity.ok()
                .body(artService.getAllArt());
    }

    @GetMapping("/{id}")
    public ResponseEntity getArtById(@PathVariable("id") String id){
        return ResponseEntity.ok()
                .body(artService.getArtById(id));
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity addArt(@RequestBody Art art){
        return ResponseEntity.ok()
                .body(artService.addArt(art));
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity updateArt(@RequestBody Art art){
        return ResponseEntity.ok()
                .body(artService.updateArt(art));
    }

    @DeleteMapping( value = "/{id}",headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity deleteArt(@PathVariable("id") String artId){
        return ResponseEntity.ok()
                .body(artService.deleteArt(artId));
    }
}
