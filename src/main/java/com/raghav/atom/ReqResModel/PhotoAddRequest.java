package com.raghav.atom.ReqResModel;

import com.raghav.atom.model.Photo;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PhotoAddRequest {
    private String id;
    private String date;
    private String thumbNailUrl;
    private String originalFileUrl;

    public static Photo fromPhotoRequest( PhotoAddRequest req){
        return new Photo(new ObjectId(),
                LocalDateTime.parse(req.date, DateTimeFormatter.ISO_DATE_TIME),
                req.thumbNailUrl,
                req.originalFileUrl);
    }

    @Override
    public String toString() {
        return "PhotoAddRequest{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", thumbNailUrl='" + thumbNailUrl + '\'' +
                ", originalFileUrl='" + originalFileUrl + '\'' +
                '}';
    }
}
