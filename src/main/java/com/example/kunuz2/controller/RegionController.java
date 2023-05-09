package com.example.kunuz2.controller;

import com.example.kunuz2.dto.region.RegionDTO;
import com.example.kunuz2.dto.jwt.JwtDTO;
import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.service.RegionService;
import com.example.kunuz2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping({"", "/"})
    public ResponseEntity<Integer> create(@RequestBody RegionDTO dto,
                                          HttpServletRequest request) {
     JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(regionService.create(dto, jwtId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @RequestBody RegionDTO regionDto,
                                        HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(regionService.update(id, regionDto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(regionService.deleteById(id, jwtId));
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<RegionDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "2") int size,
                                                 HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer jwtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(regionService.getAll(page, size));
    }
}
