package com.example.kunuz2.dto.profile;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponseDTO {
    private Integer id;
    private String name;
    private String surname;

    public ProfileResponseDTO(Integer profileId, String profileName, String profileSurname) {
    }
}
