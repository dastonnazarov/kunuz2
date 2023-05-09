package com.example.kunuz2.controller;

import com.example.kunuz2.dto.category.CategoryDTO;
import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.service.CategoryService;
import com.example.kunuz2.util.JwtUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerTypePredicate;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping({"", "/"})
    public ResponseEntity<Integer> create(@RequestBody CategoryDTO dto,
                                          HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(categoryService.create(dto, jwtId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @RequestBody CategoryDTO categoryDto,
                                          HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(categoryService.update(id, categoryDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(categoryService.deleteById(id, jwtId));
    }

    @GetMapping("/list-paging")
    public ResponseEntity<Page<CategoryDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                    @RequestParam(value = "size", defaultValue = "2") int size,
                                                    HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(categoryService.getAll(page, size));
    }
}
