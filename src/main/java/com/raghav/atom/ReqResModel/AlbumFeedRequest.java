package com.raghav.atom.ReqResModel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.model.Photo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AlbumFeedRequest {
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    private String title;

    private String description;

    private List<Photo> photos;

    private String details;

    public AlbumFeedRequest(ObjectId id, LocalDateTime date, String title, String description, List<Photo> photos, String details) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.photos = photos;
        this.details = details;
    }

    public static AlbumFeed fromAlbumFeedRequest(AlbumFeedRequest request, List<Photo> newPhotos){
        AlbumFeed newOj = new AlbumFeed();
        newOj.setDate(request.date);
        newOj.setDescription(request.description);
        newOj.setDetails(request.details);
        newOj.setTitle(request.title);

        List<String> newPhotoIds = new ArrayList<>();
        newPhotos.forEach((item) -> {
            newPhotoIds.add(item.getId().toString());
        });

        newOj.setPhotos(newPhotoIds);

        return newOj;
    }
}
