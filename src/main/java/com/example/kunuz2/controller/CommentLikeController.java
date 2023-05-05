package com.example.kunuz2.controller;

import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.service.CommentLikeService;
import com.example.kunuz2.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/comment_like")
@AllArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @GetMapping("/like/{id}")
    public ResponseEntity<Boolean> like(@PathVariable("id") Integer id,
                                        @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(commentLikeService.like(id, jwt.getId()));
    }

    @GetMapping("/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") Integer id,
                                        @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(commentLikeService.disLike(id, jwt.getId()));
    }


    @DeleteMapping("/deletelike/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                        @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(commentLikeService.delete(id, jwt.getId()));
    }
}
