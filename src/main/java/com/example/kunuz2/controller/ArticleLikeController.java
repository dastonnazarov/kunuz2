package com.example.kunuz2.controller;

import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.service.ArticleLikeService;
import com.example.kunuz2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
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
                                        HttpServletRequest request) {
       JwtUtil.checkForRequiredRole(request, ProfileRole.USER,
               ProfileRole.MODERATOR,ProfileRole.PUBLISHER,ProfileRole.MODERATOR);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleLikeService.like(articleId, jwtId));
    }

    @GetMapping("/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") String id,
                                        HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.USER,
                ProfileRole.MODERATOR,ProfileRole.PUBLISHER,ProfileRole.MODERATOR);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleLikeService.dislike(id, jwtId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id,
                                          HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.USER,
                ProfileRole.MODERATOR,ProfileRole.PUBLISHER,ProfileRole.MODERATOR);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleLikeService.delete(id, jwtId));
    }


}
