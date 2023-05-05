package com.example.kunuz2.dto.commentLike;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentLikeDTO {
    @NotNull(message = "profile_id required")
    private Integer profile_id;
    @NotNull(message = "article_id required")
    private Integer comment_id;
}
