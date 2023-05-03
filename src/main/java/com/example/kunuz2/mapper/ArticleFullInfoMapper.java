package com.example.kunuz2.mapper;

public interface ArticleFullInfoMapper {
    String getId();

    String getTitle();

    String getDescription();

    String getContent();

    Long getSharedCount();

    Integer getRegionId();

    String getRegionName();

    ///....
    Long getViewCount();

    Long getLikeCount();
}
