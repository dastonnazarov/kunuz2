package com.example.kunuz2.mapper;

import java.time.LocalDateTime;

public interface ArticleShortInfoMapper {
    String getId();

    Integer getASId();

    String getAId();

    String getTitle();

    String getDescription();

    String getAttachId();

    LocalDateTime getPublished_date();
}
