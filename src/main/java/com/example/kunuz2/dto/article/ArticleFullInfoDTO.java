package com.example.kunuz2.dto.article;


import com.example.kunuz2.dto.articleType.ArticleTypeDto;
import com.example.kunuz2.dto.attach.AttachDTO;
import com.example.kunuz2.dto.category.CategoryDTO;
import com.example.kunuz2.dto.region.RegionDTO;
import com.example.kunuz2.entity.RegionEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.jfr.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleFullInfoDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private Integer sharedCount = 0;
    private RegionDTO region;
    private CategoryDTO category;
    private ArticleTypeDto articleType;
    private LocalDateTime publishedDate;
    private Integer viewCount;
    private Integer likeCount;
    private AttachDTO attach;
}
