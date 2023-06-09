package com.example.kunuz2.controller;

import com.example.kunuz2.dto.articleSave.ArticleSaveDTO;
import com.example.kunuz2.dto.articleSave.ArticleSaveResponseDTO;
import com.example.kunuz2.dto.comment.CommentResponseDTO;
import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.service.ArticleSaveService;
import com.example.kunuz2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article_save")
@AllArgsConstructor
public class ArticleSaveController {
    private final ArticleSaveService articleSaveService;


    @PostMapping("/save")
    public ResponseEntity<?>articleSave(@RequestBody @Valid ArticleSaveDTO dto,
                                        HttpServletRequest request){
    JwtUtil.checkForRequiredRole(request, ProfileRole.MODERATOR,
                ProfileRole.ADMIN,ProfileRole.MODERATOR,ProfileRole.USER);
    Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleSaveService.save(dto,jwtId));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")Integer id,
                                   HttpServletRequest request){
        JwtUtil.checkForRequiredRole(request, ProfileRole.MODERATOR,
                ProfileRole.ADMIN,ProfileRole.MODERATOR,ProfileRole.USER);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleSaveService.delete(jwtId));
    }


   /* @GetMapping("/getList/{id}")
    public ResponseEntity<List<ArticleSaveResponseDTO>> getCommentList(@PathVariable("id") Integer id ) {
       return ResponseEntity.ok(articleSaveService.getSaveArticleList(id));
    }*/
}
