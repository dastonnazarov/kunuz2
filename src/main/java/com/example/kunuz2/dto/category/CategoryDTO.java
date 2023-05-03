package com.example.kunuz2.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private Integer id;
    private String nameUz;
    private String nameRU;
    private String nameEng;
    private LocalDateTime createdDate;
    private Boolean visible;
}
