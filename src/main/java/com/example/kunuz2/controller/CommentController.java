package com.example.kunuz2.controller;


import com.example.kunuz2.dto.article.ArticleRequestDTO;
import com.example.kunuz2.dto.comment.CommentDTO;
import com.example.kunuz2.dto.comment.CommentResponseDTO;
import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.service.CommentService;
import com.example.kunuz2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping({"","/create"})
    public ResponseEntity<CommentDTO> create(@RequestBody @Valid CommentDTO dto,
                                             HttpServletRequest request) {

        JwtUtil.checkForRequiredRole(request, ProfileRole.MODERATOR,
                ProfileRole.USER,ProfileRole.ADMIN,ProfileRole.PUBLISHER);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(commentService.create(dto, jwtId));
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable("id") Integer id,
                                             @RequestBody @Valid CommentDTO dto,
                                            HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.MODERATOR,
                ProfileRole.USER,ProfileRole.ADMIN,ProfileRole.PUBLISHER);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(commentService.update(dto,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.MODERATOR,
                ProfileRole.USER,ProfileRole.ADMIN,ProfileRole.PUBLISHER);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(commentService.deleteById(id, jwtId));
    }



    @GetMapping("/getList")
    public ResponseEntity<List<CommentResponseDTO>> getCommentList(@RequestParam("id") String id ) {
        return ResponseEntity.ok(commentService.getByArticleIdCommentList(id));
    }
}
