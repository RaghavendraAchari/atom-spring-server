package com.raghav.atom.controller;

import com.raghav.atom.ReqResModel.ArtResponseModel;
import com.raghav.atom.exception.ResourceNotFoundException;
import com.raghav.atom.exception.ServiceException;
import com.raghav.atom.model.Art;
import com.raghav.atom.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/art")
public class ArtController {
    @Autowired
    private ArtService artService;

    @GetMapping
    public ResponseEntity getAllArt() throws ServiceException {
        return ResponseEntity.ok()
                .body(artService.getAllArt());
    }

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity getAllArt(@PathVariable("pageNumber") Integer pageNumber) throws ServiceException {
        ArtResponseModel model = new ArtResponseModel(artService.getAllArt(pageNumber));
        return ResponseEntity.ok(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity getArtById(@PathVariable("id") String id) throws ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok()
                .body(artService.getArtById(id));
    }

    @PostMapping(headers = "Accept=application/json")
    public ResponseEntity addArt(@RequestBody Art art) throws ServiceException {
        return ResponseEntity.ok()
                .body(artService.addArt(art));
    }

    @PutMapping(headers = "Accept=application/json")
    public ResponseEntity updateArt(@RequestBody Art art) throws ResourceNotFoundException, ServiceException {
        return ResponseEntity.ok()
                .body(artService.updateArt(art));
    }

    @DeleteMapping( value = "/{id}",headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity deleteArt(@PathVariable("id") String artId) throws ServiceException {
        return ResponseEntity.ok()
                .body(artService.deleteArt(artId));
    }
}
