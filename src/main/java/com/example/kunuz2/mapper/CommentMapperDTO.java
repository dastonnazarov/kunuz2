package com.example.kunuz2.mapper;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



public interface CommentMapperDTO {
    Integer getId();
    Integer getProfileId();
    String getProfileName();
    String getProfileSurname();
    LocalDateTime getCreatedDate();
    LocalDateTime getUpdateDate();
}
