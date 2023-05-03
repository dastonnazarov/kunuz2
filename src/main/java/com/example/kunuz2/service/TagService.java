package com.example.kunuz2.service;


import com.example.kunuz2.dto.article.ArticleShortInfoDTO;
import com.example.kunuz2.dto.articleType.ArticleTypeDto;
import com.example.kunuz2.dto.tag.TagDTO;
import com.example.kunuz2.entity.ArticleTypeEntity;
import com.example.kunuz2.entity.TagEntity;
import com.example.kunuz2.enums.ArticleStatus;
import com.example.kunuz2.exps.ItemNotFoundException;
import com.example.kunuz2.mapper.ArticleShortInfoMapper;
import com.example.kunuz2.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private  TagRepository tagRepository;

    public TagEntity get(String id) {
        Optional<TagEntity> optional = tagRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("tag not found: " + id);
        }
        return optional.get();
    }


    public TagDTO create(TagDTO dto, Integer id) {
        TagEntity entity = new TagEntity();
        entity.setName(dto.getName());
        entity.setModeratorId(dto.getModeratorId());
        tagRepository.save(entity);
        return dto;
    }

    public TagDTO update(TagDTO dto, String id) {
        TagEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setModeratorId(dto.getModeratorId());
        tagRepository.save(entity);
        return dto;
    }

    public Boolean deleteById(Integer id) {
        tagRepository.deleteById(String.valueOf((id)));
        return true;
    }

/*    public Page<TagDTO> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC);
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<TagEntity> pageObj = tagRepository.findAll(paging);

        long totalCount = pageObj.getTotalElements();

        List<TagEntity> entityList = pageObj.getContent();
        List<TagDTO> dtoList = new LinkedList<>();

        for (TagEntity entity : entityList) {
            TagEntity dto = new TagEntity();
            dto.setId(entity.getId());

            dtoList.add(dto);
        }
        return new PageImpl<TagEntity>(dtoList, paging, totalCount);
    }*/


}
