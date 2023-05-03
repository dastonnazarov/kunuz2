package com.example.kunuz2.service;

import com.example.kunuz2.dto.article.ArticleFullInfoDTO;
import com.example.kunuz2.dto.articleType.ArticleTypeDto;
import com.example.kunuz2.dto.category.CategoryDTO;
import com.example.kunuz2.entity.ArticleTypeEntity;
import com.example.kunuz2.entity.CategoryEntity;
import com.example.kunuz2.enums.LangEnum;
import com.example.kunuz2.exps.ItemNotFoundException;
import com.example.kunuz2.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public Integer create(ArticleTypeDto dto, Integer adminId) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRU());
        entity.setNameEng(dto.getNameEng());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setPrtId(adminId);
        articleTypeRepository.save(entity); // save profile
        dto.setId(entity.getId());
        return entity.getId();
    }

    public Boolean update(Integer id, ArticleTypeDto articleTypeDto) {
        ArticleTypeEntity entity = get(id);
        entity.setNameUz(articleTypeDto.getNameUz());
        entity.setNameRu(articleTypeDto.getNameRU());
        entity.setNameEng(articleTypeDto.getNameEng());
        articleTypeRepository.save(entity);
        return true;
    }

    public ArticleTypeEntity get(Integer id) {
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Item not found: " + id);
        }
        return optional.get();
    }

    public Boolean deleteById(Integer id, Integer prtId) {
        ArticleTypeEntity entity = get(id);
        entity.setVisible(false);
        entity.setPrtId(prtId);
        articleTypeRepository.save(entity);
        return true;
    }

    public Page<ArticleTypeDto> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<ArticleTypeEntity> pageObj = articleTypeRepository.findAll(paging);

        long totalCount = pageObj.getTotalElements();

        List<ArticleTypeEntity> entityList = pageObj.getContent();
        List<ArticleTypeDto> dtoList = new LinkedList<>();

        for (ArticleTypeEntity entity : entityList) {
            ArticleTypeDto dto = new ArticleTypeDto();
            dto.setId(entity.getId());
            dto.setNameUz(entity.getNameUz());
            dto.setNameRU(entity.getNameRu());
            dto.setNameEng(entity.getNameEng());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setVisible(entity.getVisible());
            dtoList.add(dto);
        }
        return new PageImpl<ArticleTypeDto>(dtoList, paging, totalCount);
    }

    public ArticleTypeDto getByIdAndLang(Integer id, LangEnum lang) {
        ArticleTypeEntity entity = get(id);
        ArticleTypeDto dto = new ArticleTypeDto();
        dto.setId(entity.getId());
        switch (lang) {
            case en -> {
                dto.setNameEng(entity.getNameEng());
            }
            case ru -> {
                dto.setNameRU(entity.getNameRu());
            }
            case uz -> {
                dto.setNameUz(dto.getNameUz());
            }
        }
        return dto;
    }
}
