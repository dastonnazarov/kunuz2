package com.example.kunuz2.dto.tag;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDTO {

   private Integer id;

    @NotNull(message = " name mustn't null")
    private String name;

    @NotNull(message = "moderatorId mustn't null")
    private Integer moderatorId;
}
