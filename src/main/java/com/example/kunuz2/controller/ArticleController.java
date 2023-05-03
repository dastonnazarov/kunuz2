package com.example.kunuz2.controller;

import com.example.kunuz2.dto.article.*;
import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.enums.ArticleStatus;
import com.example.kunuz2.enums.LangEnum;
import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.service.ArticleService;
import com.example.kunuz2.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


    //6
    @GetMapping("/type/{id}/three")
    public ResponseEntity<List<ArticleShortInfoDTO>> get3ByTypeId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getLast3ByTypeId(id));
    }


    //7
    @GetMapping("/getLast8notGivenList")
    public ResponseEntity<List<?>> getLast8notGivenList(ArticleGetByTypeRequestDTO dto) {
        return ResponseEntity.ok(articleService.getLast8notGivenList(dto.getIdList()));
    }


    //8 pustoy

    @GetMapping("/getById/{id}")
    public ResponseEntity<ArticleFullInfoDTO> getById(@PathVariable("id") String id,
                                                      @RequestHeader(value = "Accept-Language",defaultValue = "uz",
                                                              required = false) LangEnum lang) {
        return ResponseEntity.ok(articleService.getById(id,lang));
    }



    //9
    @GetMapping("/find4ByIdTypeByIdNative/{id}")
    public ResponseEntity<?> find4ByIdTypeByIdNative(@PathVariable("id")String id) {
        return ResponseEntity.ok(articleService.find4ByIdTypeByIdNative(id));
    }


    //10
    @GetMapping("/get4MostReadArticle")
    public ResponseEntity<?> get4MostReadArticle() {
        return ResponseEntity.ok(articleService.get4MostReadArticle());
    }

    //11

    @GetMapping("find4LastByTagId")
    public ResponseEntity<?> find4LastByTagId(@PathVariable("id")Integer id){
        return ResponseEntity.ok(articleService.find4LastByTagId(id));
    }



    //12
    @GetMapping("/get5by-type-region")
    public ResponseEntity<List<ArticleShortInfoDTO>> get5ByTypeAndRegion(@RequestParam("type") Integer typeId, @RequestParam("region") Integer regionId){
        return ResponseEntity.ok((articleService.get5ByTypeAndReg(typeId, regionId)));
    }


    //13
    @GetMapping(value = "/paging")
    public ResponseEntity<Page<ArticleShortInfoDTO>> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "30") int size,
                                                            @RequestParam(value = "id") Integer id) {
        return ResponseEntity.ok(articleService.getArticleByRegionIdPaging(page, size, id));
    }

    //14
    @GetMapping("/get5byCategory")
    public ResponseEntity<List<ArticleShortInfoDTO>> get5ByCategory(@RequestParam("categoryId") Integer categoryId){
        return ResponseEntity.ok(articleService.get5ByCategory(categoryId));
    }


    //15
    @GetMapping("/getCategoryIdPaging")
    public ResponseEntity<?> categoryIdPaging(@RequestParam(value = "page",defaultValue = "1")int page,
                                              @RequestParam(value = "size",defaultValue = "10")int size,
                                              @RequestParam(value = "id")Integer id){
        return ResponseEntity.ok(articleService.getArticleByCategoryIdPaging(page,size,id));
    }



    @PostMapping("/filter")
    public ResponseEntity<Page<ArticleShortInfoDTO>> filter(@RequestBody ArticleFilterDTO dto,
                                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(articleService.filter(dto, page, size));
    }


}
