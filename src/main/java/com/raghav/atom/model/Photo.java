package com.raghav.atom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Document(collection = "Photo")
@Getter
@Setter
@NoArgsConstructor
public class Photo {

    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    private String thumbNailUrl;

    private String originalFileUrl;

    public Photo(ObjectId id, LocalDateTime date, String thumbNailUrl, String originalFileUrl) {
        this.id = id;
        this.date = date;
        this.thumbNailUrl = thumbNailUrl;
        this.originalFileUrl = originalFileUrl;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", thumbNailUrl='" + thumbNailUrl + '\'' +
                ", originalFileUrl='" + originalFileUrl + '\'' +
                '}';
    }
}
