package com.raghav.atom.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private ObjectId _id;
    private String userName;
    private String password;
    private Boolean isActive;
    private String roles;
}
