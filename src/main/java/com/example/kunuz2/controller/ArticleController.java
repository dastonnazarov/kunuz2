package com.example.kunuz2.controller;

import com.example.kunuz2.dto.article.ArticleRequestDTO;
import com.example.kunuz2.dto.article.ArticleShortInfoDTO;
import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.enums.ArticleStatus;
import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.service.ArticleService;
import com.example.kunuz2.util.JwtUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Negative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<ArticleRequestDTO> create(@RequestBody @Valid ArticleRequestDTO dto,
                                                    @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.create(dto, jwt.getId()));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ArticleRequestDTO> update(@RequestBody ArticleRequestDTO dto,
                                                    @RequestHeader("Authorization") String authorization,
                                                    @PathVariable("id") String articleId) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(dto, articleId));
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id,
                                    @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @PostMapping("/change-status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") String id,
                                          @RequestParam String status,
                                          @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatus(ArticleStatus.valueOf(status), id, jwt.getId()));
    }

    @GetMapping("/type/{id}/five")
    public ResponseEntity<List<ArticleShortInfoDTO>> get5ByTypeId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getLast5ByTypeId(id));
    }

    @GetMapping("/type/{id}/three")
    public ResponseEntity<List<ArticleShortInfoDTO>> get3ByTypeId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getLast3ByTypeId(id));
    }
}
