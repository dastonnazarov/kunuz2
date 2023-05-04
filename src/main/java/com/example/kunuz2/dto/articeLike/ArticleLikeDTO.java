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
    @NotNull(message = "id required")
    private Integer id;
    @NotNull(message = "profile_id required")
    private Integer profile_id;
    @NotNull(message = "comment_id required")
    private Integer comment_id;
    @NotNull(message = "created_date required")
    private LocalDateTime created_date;
    @NotNull(message = "status required")
    private ArticleLikeStatus status;
}
