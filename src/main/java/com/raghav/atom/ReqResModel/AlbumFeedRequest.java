package com.raghav.atom.ReqResModel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.model.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumFeedRequest {
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using= ToStringSerializer.class)
    private LocalDateTime date;

    private String title;

    private String description;

    private List<Photo> photos;

    private String details;
    private List<String> category;

    public static AlbumFeed fromAlbumFeedRequest(AlbumFeedRequest request,
                                                 List<Photo> newPhotos){
        AlbumFeed newOj = new AlbumFeed();
        newOj.setDate(request.date);
        newOj.setDescription(request.description);
        newOj.setDetails(request.details);
        newOj.setTitle(request.title);
        newOj.setCategory(request.category);

        List<String> newPhotoIds = new ArrayList<>();
        newPhotos.forEach((item) ->
            newPhotoIds.add(item.getId().toString()) );

        newOj.setPhotos(newPhotoIds);

        return newOj;
    }
}
