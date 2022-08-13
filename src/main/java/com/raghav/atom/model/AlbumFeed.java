package com.raghav.atom.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "AlbumFeed")
@Getter
@Setter
@NoArgsConstructor
public class AlbumFeed {
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    private String title;

    private String description;

    private List<String> photos;

    private String details;

    public AlbumFeed(ObjectId id, LocalDateTime date, String title, String description, List<String> photos, String details) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.photos = photos;
        this.details = details;
    }
}
