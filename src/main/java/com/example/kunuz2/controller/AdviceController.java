package com.example.kunuz2.controller;

import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.exps.AppBadRequestException;
import com.example.kunuz2.exps.ItemNotFoundException;

import com.example.kunuz2.exps.MethodNotAllowedException;
import com.example.kunuz2.service.ArticleLikeService;
import com.example.kunuz2.service.ArticleTypeService;
import com.example.kunuz2.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ap1/v1/article_like")
@AllArgsConstructor
public class AdviceController {
    private final ArticleLikeService articleLikeService;

    public ResponseEntity<?> like(@PathVariable("id") String id,
                                  @RequestHeader("Authorization")String authorization){
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.USER);
        return ResponseEntity.ok(articleLikeService.like(id, jwtDTO.getId()));
    }
}
