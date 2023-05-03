package com.example.kunuz2.dto.articleType;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTypeDto {
    private Integer id;
    @NotNull(message = "nameUz required")
    private String nameUz;
    @NotNull(message = "nameRU required")
    private String nameRU;
    @NotNull(message = "nameEng required")
    private String nameEng;
    @NotNull(message = "createdDate required")
    private LocalDateTime createdDate;
    private Boolean visible;
}
