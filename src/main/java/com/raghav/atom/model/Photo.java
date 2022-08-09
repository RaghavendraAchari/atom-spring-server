package com.raghav.atom.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Photo")
public class Photo {

    @Id
    @Getter
    private String id;
    @Getter
    @Setter
    private Date date;
    @Getter
    @Setter
    private String thumbNailUrl;
    @Getter
    @Setter
    private String originalFileUrl;

    public Photo(Date date, String thumbNailUrl, String originalFileUrl) {
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
