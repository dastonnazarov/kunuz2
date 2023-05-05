package com.example.kunuz2.controller;

import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.service.ArticleLikeService;
import com.example.kunuz2.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article_like")
@AllArgsConstructor
public class ArticleLikeController {
    private final ArticleLikeService articleLikeService;


    @GetMapping("/like/{id}")
    public ResponseEntity<Boolean> like(@PathVariable("id") String articleId,
                                        @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.like(articleId, jwt.getId()));
    }

    @GetMapping("/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") String id,
                                        @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.dislike(id, jwt.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id,
                                          @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.delete(id, jwt.getId()));
    }


}
