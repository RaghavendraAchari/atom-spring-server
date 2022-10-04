package com.raghav.atom.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("Art")
public class Art {
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;

    private String title;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using= ToStringSerializer.class)
    private LocalDateTime date;

    private String thumbnailLink;

    private String originalFileLink;

    @Override
    public String toString() {
        return "Art{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", thumbnailLink='" + thumbnailLink + '\'' +
                ", originalFileLink='" + originalFileLink + '\'' +
                '}';
    }
}
