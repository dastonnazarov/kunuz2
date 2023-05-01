package com.example.kunuz2.dto.region;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegionDTO {
    private Integer id;
    private String nameUz;
    private String nameRU;
    private String nameEng;
    private LocalDateTime createdDate;
    private Boolean visible;
}
