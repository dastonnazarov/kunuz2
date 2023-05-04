package com.example.kunuz2.dto.comment;


import com.example.kunuz2.dto.profile.ProfileResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDTO {
    private Integer id;
    private ProfileResponseDTO profile;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
