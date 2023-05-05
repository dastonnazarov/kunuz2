package com.example.kunuz2.dto.articeLike;


import com.example.kunuz2.enums.ArticleLikeStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleLikeDTO {
    @NotNull(message = "profile_id required")
    private Integer profile_id;
    @NotNull(message = "article_id required")
    private String article_id;

}
