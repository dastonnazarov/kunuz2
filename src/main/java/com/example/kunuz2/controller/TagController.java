package com.example.kunuz2.controller;

import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.dto.tag.TagDTO;
import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.service.TagService;
import com.example.kunuz2.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    @Autowired
    private  TagService  tagService;


    @PostMapping("")
    public ResponseEntity<TagDTO> create(@RequestBody @Valid TagDTO dto,
                                         @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(tagService.create(dto, jwt.getId()));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody TagDTO dto,
                                                    @RequestHeader("Authorization") String authorization,
                                                    @PathVariable("id") String articleId) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(tagService.update(dto, articleId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization,
                                    @PathVariable("id") Integer id) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(tagService.deleteById(id));
    }


}
